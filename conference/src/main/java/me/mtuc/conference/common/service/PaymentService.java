package me.mtuc.conference.common.service;

import lombok.RequiredArgsConstructor;
import me.mtuc.conference.enums.PayMethod;
import me.mtuc.conference.registrations.domain.Registrations;
import me.mtuc.conference.registrations.repository.RegistrationsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PaymentService {

    private final RegistrationsRepository registrationsRepository;

    @Transactional(readOnly = false)
    public void adminPaidRegistrationsFee(Long id) {
        Registrations registrations = registrationsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("사전등록 내역이 없습니다s"));
        registrations.setPayStatus(true);
        registrations.setAmount(registrations.getPrice());
        registrations.setPayMethod(PayMethod.ONLINE_CARD);
        registrations.setDateOfPayment(LocalDateTime.now());
    }

    @Transactional(readOnly = false)
    public void inicisPaidRegistrationsFee(Long id) {
        Registrations registrations = registrationsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("사전등록 내역이 없습니다s"));
        registrations.setPayStatus(true);
        registrations.setAmount(registrations.getPrice());
        registrations.setPayMethod(PayMethod.ONLINE_CARD);
        registrations.setDateOfPayment(LocalDateTime.now());
    }

}
