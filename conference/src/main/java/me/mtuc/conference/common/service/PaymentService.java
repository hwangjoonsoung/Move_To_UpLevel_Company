package me.mtuc.conference.common.service;

import lombok.RequiredArgsConstructor;
import me.mtuc.conference.registrations.domain.Registrations;
import me.mtuc.conference.registrations.repository.RegistrationsRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final RegistrationsRepository registrationsRepository;

    public void paidRegistrationsFee(Long id) {
        Registrations registrations = registrationsRepository.findRegistrations(id);
        registrations.setPayStatus(true);

        registrationsRepository.editRegistration(registrations);
    }
}
