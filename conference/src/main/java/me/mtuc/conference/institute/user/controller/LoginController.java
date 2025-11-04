package me.mtuc.conference.institute.user.controller;

import lombok.RequiredArgsConstructor;
import me.mtuc.conference.institute.user.service.LoginService;
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
