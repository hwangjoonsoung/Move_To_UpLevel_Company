package me.mtuc.conference.institute.user.controller;

import lombok.RequiredArgsConstructor;
import me.mtuc.conference.institute.user.dto.NewUserDto;
import me.mtuc.conference.institute.user.service.UserService;
import org.hibernate.query.NativeQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.Format;

@RestController
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @PostMapping("/user/new")
    public ResponseEntity<?> newUser(@RequestBody NewUserDto newUserDto) {

        userService.newUser(newUserDto);
        return ResponseEntity.ok().build();
    }

}
