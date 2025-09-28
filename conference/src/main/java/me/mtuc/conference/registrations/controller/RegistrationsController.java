package me.mtuc.conference.registrations.controller;

import me.mtuc.conference.registrations.dto.RegistrationRequestDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationsController {

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
