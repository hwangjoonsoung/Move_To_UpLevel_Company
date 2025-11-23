package me.mtuc.conference.institute.user.service;

import lombok.RequiredArgsConstructor;
import me.mtuc.conference.institute.user.domain.User;
import me.mtuc.conference.institute.user.dto.NewUserDto;
import me.mtuc.conference.institute.user.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void newUser(NewUserDto newUserDto) {
        User user = User.builder().name(newUserDto.getName())
                .password(bCryptPasswordEncoder.encode(newUserDto.getPassword()))
                .affiliations(newUserDto.getAffiliation())
                .position(newUserDto.getPosition())
                .phone(newUserDto.getPhone())
                .member_type(newUserDto.getMember_type())
                .roll(newUserDto.getRoll()).build();

        userRepository.save(user);
    }
}
