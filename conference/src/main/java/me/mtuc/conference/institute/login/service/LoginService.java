package me.mtuc.conference.institute.login.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import me.mtuc.conference.institute.login.domain.Token;
import me.mtuc.conference.institute.login.dto.TokenResponse;
import me.mtuc.conference.institute.login.repository.LoginRepository;
import me.mtuc.conference.institute.user.domain.User;
import me.mtuc.conference.institute.login.dto.LoginDto;
import me.mtuc.conference.institute.user.dto.CustomUserDetails;
import me.mtuc.conference.institute.user.repository.UserRepository;
import me.mtuc.conference.util.JwtProvider;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Spliterator;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginRepository loginRepository;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final RedisTemplate<String, String> redisTemplate;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public TokenResponse login(LoginDto loginDto) {
        // 인증
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        boolean authenticated = authenticate.isAuthenticated();
        CustomUserDetails userDetails = (CustomUserDetails)authenticate.getPrincipal();
        String refreshToken = "";
        String accessToken = "";
        // 인증성공하면 사용자 정보 가져 오고 reflash토큰 발급
        if (authenticated) {
            // todo: refresh token DB에 있는지 찾아보고 만료 여부 확인
            Optional<Object> Token = loginRepository.findTokenByUserId(userDetails.getId());
            if(Token.isEmpty() || Token.isPresent()){
                refreshToken = jwtProvider.generateRefreshTokenById(userDetails.getId());

                refreshToken = Token.builder().userId(userDetails.getId()).refreshToken(refreshToken).dateOfExpired(LocalDateTime.now().plusDays(14)).build();
                loginRepository.save(refreshToken);
            }else{
                refreshToken = Token.getRefreshToken();
            }
        }

        if (refreshToken != null && !refreshToken.isBlank()) {
            // todo: 여기서 refresh token의 일치 여부 확인(새로 생성한 token이 아닌경우)
            accessToken = UUID.randomUUID().toString();

            try {
                redisTemplate.opsForValue().set(createRedisKey(userDetails.getId()), createRedisValue(refreshToken));
            } catch (JsonProcessingException e) {
                // todo: 오류 보내기
            }
        }

        return TokenResponse.builder().accessToken(accessToken).refreshToken(refreshToken).build();
    }

    public String createRedisKey(Long userId) {
        return "user:" + userId;
    }

    public String createRedisValue(String refreshToken) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String value = objectMapper.writeValueAsString(refreshToken);
        return value;
    }
}
