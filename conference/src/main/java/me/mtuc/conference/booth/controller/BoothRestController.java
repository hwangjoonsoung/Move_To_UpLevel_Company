package me.mtuc.conference.booth.controller;

import lombok.RequiredArgsConstructor;
import me.mtuc.conference.booth.service.BoothService;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class BoothRestController {

    private final BoothService boothService;
}
