package me.mtuc.conference.registrations.controller;

import lombok.RequiredArgsConstructor;
import me.mtuc.conference.registrations.dto.RegistrationRequestDto;
import me.mtuc.conference.registrations.dto.RegistrationsIdResponseDto;
import me.mtuc.conference.registrations.service.RegistrationsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RegistrationsRestController {

    private final RegistrationsService registrationsService;

    @PostMapping("/registrations/new")
    public ResponseEntity<RegistrationsIdResponseDto> newRegistrations(@RequestBody RegistrationRequestDto registrationRequestDto) throws URISyntaxException {
        Long id = registrationsService.newRegistrations(registrationRequestDto);
        URI uri = new URI("/registrations/");
        return ResponseEntity.created(uri).body(new RegistrationsIdResponseDto(id));
    }

    @PostMapping("/registrations/{id}/edit")
    public ResponseEntity<RegistrationsIdResponseDto> editRegistrations(@PathVariable(name = "id") Long id, @RequestBody RegistrationRequestDto registrationRequestDto) {
        Long editRegistrationsId = registrationsService.editRegistrations(id, registrationRequestDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new RegistrationsIdResponseDto(editRegistrationsId));
    }

    @PostMapping("/registrations/{id}/remove")
    public ResponseEntity<RegistrationsIdResponseDto> removeRegistrations(@PathVariable(name = "id") Long id) {
        Long removedRegistrationId = registrationsService.removeRegistration(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new RegistrationsIdResponseDto(removedRegistrationId));
    }

}
