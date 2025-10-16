package me.mtuc.conference.common.service;

import lombok.RequiredArgsConstructor;
import me.mtuc.conference.registrations.domain.Registration;
import me.mtuc.conference.registrations.repository.RegistrationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PaymentService {

    private final RegistrationRepository registrationRepository;

    @Transactional(readOnly = false)
    public void paidRegistrationsFee(Long id) {
        Registration registrations = registrationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("사전등록 내역이 없습니다s"));
        registrations.setPayStatus(true);
    }
}
