package me.mtuc.conference.registrations.controller;

import jakarta.validation.Valid;
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

    @PostMapping(value = "/registrations/new", consumes = "application/json", produces = "application/json")
    public ResponseEntity<RegistrationsIdResponseDto> newRegistrations(@Valid @RequestBody RegistrationRequestDto registrationRequestDto) throws URISyntaxException {
        Long id = registrationsService.newRegistrations(registrationRequestDto);
        URI uri = new URI("/registrations/"+id+"/payment");
        return ResponseEntity.created(uri).body(new RegistrationsIdResponseDto(id));
    }

    @PostMapping(value = "/registrations/{id}/edit", consumes = "application/json", produces = "application/json")
    public ResponseEntity<RegistrationsIdResponseDto> editRegistrations(@PathVariable(name = "id") Long id, @Valid @RequestBody RegistrationRequestDto registrationRequestDto) {
        registrationsService.editRegistrations(id, registrationRequestDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new RegistrationsIdResponseDto(id));
    }

    @PostMapping(value = "/registrations/{id}/remove",consumes = "application/json", produces = "application/json")
    public ResponseEntity<RegistrationsIdResponseDto> removeRegistrations(@PathVariable(name = "id") Long id) {
        registrationsService.removeRegistration(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new RegistrationsIdResponseDto(id));
    }

}
