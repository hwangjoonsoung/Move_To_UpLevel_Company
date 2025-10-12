package me.mtuc.conference.booth.controller;

import lombok.RequiredArgsConstructor;
import me.mtuc.conference.booth.dto.BoothNewRequestDto;
import me.mtuc.conference.booth.service.BoothService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoothRestController {

    private final BoothService boothService;

    @PostMapping("/booth/new")
    public void newBooth(@RequestBody BoothNewRequestDto boothNewRequestDto) {
        boothService.newBooth(boothNewRequestDto);
    }
}
