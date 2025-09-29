package me.mtuc.conference.registrations.controller;

import lombok.RequiredArgsConstructor;
import me.mtuc.conference.registrations.dto.RegistrationRequestDto;
import me.mtuc.conference.registrations.service.RegistrationsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class RegistrationsController {

    private final RegistrationsService registrationsService;

    @GetMapping("/registrations/")
    public String index() {
        return "/registrations/";
    }

    @GetMapping("/registrations/new")
    public String newRegistrations() {
        return "/registrations/new";
    }

    @PostMapping("/registrations/new")
    public String newRegistrations(RegistrationRequestDto registrationRequestDto) {

        registrationsService.newRegistrations(registrationRequestDto);
        return "redirect:/registrations/";
    }

    @GetMapping("/registrations/edit")
    public String editRegistrations() {
        return "/registrations/edit";
    }

    @PostMapping("/registrations/edit")
    public String editRegistrations(RegistrationRequestDto registrationRequestDto) {
        return "redirect:/registrations/";
    }

    @PostMapping("/registrations/remove/{registrationsId}")
    public String removeRegistrations(@PathVariable(name = "registrationsId") Long id) {
        return "redirect:/registrations/";
    }

}
