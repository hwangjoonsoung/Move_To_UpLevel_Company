package me.mtuc.conference.institute.login.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    @GetMapping("/user/login")
    public String login(){
        return "/user/login";
    }
}
