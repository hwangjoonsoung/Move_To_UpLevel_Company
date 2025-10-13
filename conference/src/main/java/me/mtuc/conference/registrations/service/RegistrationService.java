package me.mtuc.conference.registrations.service;

import lombok.RequiredArgsConstructor;
import me.mtuc.conference.registrations.domain.Registration;
import me.mtuc.conference.util.Util;
import me.mtuc.conference.common.entity.FeeItem;
import me.mtuc.conference.common.repository.FeeItemsRepository;
import me.mtuc.conference.registrations.dto.RegistrationRequestDto;
import me.mtuc.conference.registrations.dto.RegistrationEditResponseDto;
import me.mtuc.conference.registrations.repository.RegistrationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final FeeItemsRepository feeItemsRepository;

    @Transactional(readOnly = false)
    public Long newRegistration(RegistrationRequestDto registrationRequestDto) {
        Long id = 0L;

        FeeItem feeItem = feeItemsRepository.findById(registrationRequestDto.getFeeItemId()).orElseThrow(() -> new IllegalArgumentException("해당 금액이 없습니다"));

        Registration registration = Registration.builder()
                .goodName(registrationRequestDto.getGoodName())
                .feeItem(feeItem)
                .price(registrationRequestDto.getPrice())
                .name(registrationRequestDto.getName())
                .birth(Util.stringToLocalDate(registrationRequestDto.getBirth()))
                .affiliation(registrationRequestDto.getAffiliation())
                .position(registrationRequestDto.getPosition())
                .email(registrationRequestDto.getEmail())
                .phone(registrationRequestDto.getPhone()).build();
        registrationRepository.save(registration);
        return id;
    }

    @Transactional(readOnly = false)
    public void editRegistration(Long id,RegistrationRequestDto registrationRequestDto) {

        Registration registration = registrationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("사전등록 내역이 없습니다."));
        FeeItem feeItem = feeItemsRepository.findById(registrationRequestDto.getFeeItemId()).orElseThrow(() -> new IllegalArgumentException("해당 금액이 없습니다"));

        registration.updateRegistrations(registrationRequestDto,feeItem);

    }

    @Transactional(readOnly = false)
    public void removeRegistration(Long id) {
        Registration registration = registrationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("사전등록 내역이 없습니다."));
        registration.setDeleted(true);
    }

    public RegistrationEditResponseDto getRegistration(Long id) {
        Registration registration = registrationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("사전등록 내역이 없습니다."));
        RegistrationEditResponseDto registrationEditResponseDto = new RegistrationEditResponseDto(registration.getName(), registration.getBirth(), registration.getAffiliation(), registration.getPosition(), registration.getEmail(), registration.getPhone(), registration.getFeeItem().getId());
        return registrationEditResponseDto;
    }
}
