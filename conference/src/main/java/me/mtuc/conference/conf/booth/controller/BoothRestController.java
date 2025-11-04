package me.mtuc.conference.conf.booth.controller;

import lombok.RequiredArgsConstructor;
import me.mtuc.conference.conf.booth.dto.BoothIdResponseDto;
import me.mtuc.conference.conf.booth.dto.BoothRequestDto;
import me.mtuc.conference.conf.booth.service.BoothService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoothRestController {

    private final BoothService boothService;

    @PostMapping("/booth/new")
    public ResponseEntity<BoothIdResponseDto> newBooth(@RequestBody BoothRequestDto boothRequestDto) throws URISyntaxException {
        Long savedId = boothService.newBooth(boothRequestDto);
        BoothIdResponseDto boothIdResponseDto = new BoothIdResponseDto(savedId);
        URI uri = new URI("/booth/" + savedId + "/edit");
        return ResponseEntity.created(uri).body(boothIdResponseDto);
    }

    @PostMapping("/booth/{id}/edit")
    public ResponseEntity<BoothIdResponseDto> editBooth(@PathVariable(name = "id") Long id, @RequestBody BoothRequestDto boothRequestDto) throws URISyntaxException {
        Long editedBoothId = boothService.editBooth(id, boothRequestDto);
        URI uri = new URI("/booth/" + editedBoothId + "/edit");
        BoothIdResponseDto boothIdResponseDto = new BoothIdResponseDto(editedBoothId);
        return ResponseEntity.created(uri).body(boothIdResponseDto);

    }
}
