package me.mtuc.conference.config;

import lombok.RequiredArgsConstructor;
import me.mtuc.conference.institute.user.domain.User;
import me.mtuc.conference.institute.user.dto.CustomUserDetails;
import me.mtuc.conference.institute.user.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
        User user = userRepository.findUserByEmail(userEmail).orElseThrow(() -> new IllegalArgumentException("계정정보를 잘못 입력하셨습니다."));

        CustomUserDetails userDetail = CustomUserDetails.builder()
                .email(user.getEmail())
                .name(user.getName())
                .password(user.getPassword())
                .id(user.getId())
                .authorities(List.of(new SimpleGrantedAuthority("user"))).build();

        return userDetail;
    }
}
