package me.mtuc.conference.registrations.controller;

import lombok.RequiredArgsConstructor;
import me.mtuc.conference.registrations.service.RegistrationsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class RegistrationsController {

    private final RegistrationsService registrationsService;

    @GetMapping("/registrations/")
    public String index() {
        return "/registrations/index";
    }

    @GetMapping("/registrations/new")
    public String newRegistrations() {
        return "/registrations/new";
    }

    @GetMapping("/registrations/edit")
    public String editRegistrations() {
        return "/registrations/edit";
    }

    @GetMapping("/registrations/{id}")
    public String showRegistrations(@PathVariable(name = "id") Long id) {
        registrationsService.getRegistrations(id);
        return "/registrations/edit";
    }

}
