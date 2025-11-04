package me.mtuc.conference.institute.user.controller;

import lombok.RequiredArgsConstructor;
import me.mtuc.conference.institute.user.dto.LoginDto;
import me.mtuc.conference.institute.user.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LoginRestController {

    private final LoginService loginService;

    @PostMapping("/user/login")
    public void login(@RequestBody LoginDto loginDto){
        loginService.login(loginDto);
    }

}
