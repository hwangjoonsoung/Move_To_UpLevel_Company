package me.mtuc.conference.conf.registrations.controller;

import lombok.RequiredArgsConstructor;
import me.mtuc.conference.conf.registrations.dto.RegistrationEditResponseDto;
import me.mtuc.conference.conf.registrations.service.RegistrationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @GetMapping("/registrations/")
    public String index() {
        return "/registrations/index";
    }

    @GetMapping("/registrations/new")
    public String newRegistration() {
        return "/registrations/new";
    }

    @GetMapping("/registrations/{id}/edit")
    public String editRegistration(@PathVariable Long id, Model model) {
        RegistrationEditResponseDto registration = registrationService.getRegistration(id);
        model.addAttribute("registration", registration);
        return "/registrations/edit";
    }

    @GetMapping("/registrations/{id}")
    public String showRegistration(@PathVariable(name = "id") Long id) {
        registrationService.getRegistration(id);
        return "/registrations/edit";
    }

}
