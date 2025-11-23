package me.mtuc.conference.institute.login.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import me.mtuc.conference.institute.login.domain.Token;
import me.mtuc.conference.institute.login.dto.TokenResponse;
import me.mtuc.conference.institute.login.repository.LoginRepository;
import me.mtuc.conference.institute.user.domain.User;
import me.mtuc.conference.institute.login.dto.LoginDto;
import me.mtuc.conference.institute.user.repository.UserRepository;
import me.mtuc.conference.util.JwtProvider;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginRepository loginRepository;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final RedisTemplate<String, String> redisTemplate;

    public TokenResponse login(LoginDto loginDto) {

        // 인증
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
        );
        boolean authenticated = authenticate.isAuthenticated();
        System.out.println("authenticated = " + authenticated);
        String reflashToken = "";
        String accessToken = "";
        User user = null;
        // 인증성공하면 사용자 정보 가져 오고 reflash토큰 발급
        if (authenticated) {
            user = userRepository.loginUser(loginDto.getEmail(), loginDto.getPassword()).orElseThrow(() -> new IllegalArgumentException("해당하는 유저가 없습니다"));
            reflashToken = jwtProvider.generateRefreshTokenById(user.getId());

            // todo : 근데 여기서 만료 이전이면 궅이 save나 update를 할 필요가 있을까?
            Token refreshToken = Token.builder().user(user).refreshToken(reflashToken).dateOfExpired(LocalDateTime.now().plusDays(14)).build();
            loginRepository.save(refreshToken);

        }

        if (reflashToken != null && !reflashToken.isBlank()) {
            accessToken = UUID.randomUUID().toString();

            try {
                redisTemplate.opsForValue().set(createRedisKey(user.getId()), createRedisValue(user, reflashToken));
            } catch (JsonProcessingException e) {
                // todo: 오류 보내기
            }
        }

        return TokenResponse.builder().accessToken(accessToken).refreshToken(reflashToken).build();
    }

    public String createRedisKey(Long userId) {
        return "user:" + userId;
    }

    public String createRedisValue(User user, String refreshToken) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String value = objectMapper.writeValueAsString(refreshToken);
        return value;
    }
}
