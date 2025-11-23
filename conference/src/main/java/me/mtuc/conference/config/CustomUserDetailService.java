package me.mtuc.conference.config;

import lombok.RequiredArgsConstructor;
import me.mtuc.conference.institute.user.domain.User;
import me.mtuc.conference.institute.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        System.out.println("CustomUserDetailService.loadUserByUsername");
        User user = userRepository.findUserByEmail(userEmail).orElseThrow(() -> new IllegalArgumentException("계정정보를 잘못 입력하셨습니다."));
        System.out.println("user = " + user);
        org.springframework.security.core.userdetails.User userDetail = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), List.of());
        return userDetail;
    }
}
