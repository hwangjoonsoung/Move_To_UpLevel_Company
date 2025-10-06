package me.mtuc.conference.common.service;

import lombok.RequiredArgsConstructor;
import me.mtuc.conference.registrations.domain.Registrations;
import me.mtuc.conference.registrations.repository.RegistrationsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PaymentService {

    private final RegistrationsRepository registrationsRepository;

    @Transactional(readOnly = false)
    public void paidRegistrationsFee(Long id) {
        Registrations registrations = registrationsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("사전등록 내역이 없습니다s"));
        registrations.setPayStatus(true);
    }
}
