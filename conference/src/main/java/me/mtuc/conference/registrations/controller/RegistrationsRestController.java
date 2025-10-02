package me.mtuc.conference.registrations.controller;

import lombok.RequiredArgsConstructor;
import me.mtuc.conference.registrations.dto.RegistrationRequestDto;
import me.mtuc.conference.registrations.service.RegistrationsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class RegistrationsRestController {

    // todo: payload 적용 해서 status code와 message로 반환할 수 있도록 변경

    private final RegistrationsService registrationsService;

    @PostMapping("/registrations/new")
    public String newRegistrations(RegistrationRequestDto registrationRequestDto) {
        registrationsService.newRegistrations(registrationRequestDto);
        return "redirect:/registrations/";
    }

    @PostMapping("/registrations/{id}/edit")
    public String editRegistrations(@PathVariable(name = "id") Long id, RegistrationRequestDto registrationRequestDto) {
        registrationsService.editRegistrations(id, registrationRequestDto);
        return "redirect:/registrations/";
    }

    @PostMapping("/registrations/{id}/remove")
    public String removeRegistrations(@PathVariable(name = "id") Long id) {
        registrationsService.removeRegistration(id);
        return "redirect:/registrations/";
    }

}
