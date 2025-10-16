package me.mtuc.conference.registrations.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.mtuc.conference.registrations.dto.RegistrationRequestDto;
import me.mtuc.conference.registrations.dto.RegistrationIdResponseDto;
import me.mtuc.conference.registrations.service.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RegistrationRestController {

    private final RegistrationService registrationService;

    @PostMapping(value = "/registrations/new", consumes = "application/json", produces = "application/json")
    public ResponseEntity<RegistrationIdResponseDto> newRegistration(@Valid @RequestBody RegistrationRequestDto registrationRequestDto) throws URISyntaxException {
        Long id = registrationService.newRegistration(registrationRequestDto);
        URI uri = new URI("/registrations/"+id+"/payment");
        return ResponseEntity.created(uri).body(new RegistrationIdResponseDto(id));
    }

    @PostMapping(value = "/registrations/{id}/edit", consumes = "application/json", produces = "application/json")
    public ResponseEntity<RegistrationIdResponseDto> editRegistration(@PathVariable(name = "id") Long id, @Valid @RequestBody RegistrationRequestDto registrationRequestDto) {
        registrationService.editRegistration(id, registrationRequestDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new RegistrationIdResponseDto(id));
    }

    @PostMapping(value = "/registrations/{id}/remove",consumes = "application/json", produces = "application/json")
    public ResponseEntity<RegistrationIdResponseDto> removeRegistration(@PathVariable(name = "id") Long id) {
        registrationService.removeRegistration(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new RegistrationIdResponseDto(id));
    }

}
