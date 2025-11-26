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

import java.time.Duration;
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
            Optional<Token> token = loginRepository.findTokenByUserId(userDetails.getId());
            if(token.isPresent() && isExpiredRefreshToken(token.get())){
                refreshToken = token.get().getRefreshToken();
            }else{
                refreshToken = jwtProvider.generateRefreshTokenById(userDetails.getId());
                Token newRefreshToken = Token.builder().userId(userDetails.getId()).refreshToken(refreshToken).dateOfExpired(LocalDateTime.now().plusDays(14)).build();
                loginRepository.save(newRefreshToken);
            }
        }

        if (refreshToken != null && !refreshToken.isBlank()) {
            accessToken = UUID.randomUUID().toString();

            try {
                Duration duration = Duration.ofMinutes(60);
                // todo: json형태로 저장해서 사용할 수 있도록 변경
                redisTemplate.opsForValue().set(createRedisKey(userDetails.getId()), createRedisValue(accessToken),duration);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("JsonProcessingException");
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

    public boolean isExpiredRefreshToken(Token token) {
        LocalDateTime dateOfExpired = token.getDateOfExpired();
        LocalDateTime now = LocalDateTime.now();
        return dateOfExpired.isAfter(now);
    }
}
