package me.mtuc.conference.institute.login.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.mtuc.conference.institute.login.dto.LoginDto;
import me.mtuc.conference.institute.login.dto.TokenResponse;
import me.mtuc.conference.institute.login.service.LoginService;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.channels.Pipe;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class LoginRestController {

    private final LoginService loginService;

    @PostMapping("/user/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginDto loginDto, HttpServletResponse httpServletResponse){
        System.out.println("LoginRestController.login");
        TokenResponse token = loginService.login(loginDto);

        ResponseCookie cookie = ResponseCookie.from("refreshToken", token.getRefreshToken()).httpOnly(true).secure(true)
                .path("/")
                .sameSite("none")
                .maxAge(60 * 60 * 24)
                .build();
        httpServletResponse.setHeader("Set-Cookie", cookie.toString());
        return ResponseEntity.ok(token);
    }


}
