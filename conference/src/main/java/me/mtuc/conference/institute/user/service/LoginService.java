package me.mtuc.conference.institute.user.service;

import lombok.RequiredArgsConstructor;
import me.mtuc.conference.institute.user.domain.User;
import me.mtuc.conference.institute.user.dto.LoginDto;
import me.mtuc.conference.institute.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    public void login(LoginDto loginDto) {
        User user = userRepository.loginUser(loginDto.getEmail(), loginDto.getPassword()).orElseThrow(() -> new IllegalArgumentException("해당하는 사용자가 없습니다."));
        System.out.println("user = " + user);
    }

}
