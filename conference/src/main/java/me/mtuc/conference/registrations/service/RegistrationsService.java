package me.mtuc.conference.registrations.service;

import lombok.RequiredArgsConstructor;
import me.mtuc.conference.Util;
import me.mtuc.conference.common.entity.FeeItems;
import me.mtuc.conference.common.repository.FeeItemsRepository;
import me.mtuc.conference.registrations.domain.Registrations;
import me.mtuc.conference.registrations.dto.RegistrationRequestDto;
import me.mtuc.conference.registrations.dto.RegistrationsEditResponseDto;
import me.mtuc.conference.registrations.repository.RegistrationsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RegistrationsService {

    private final RegistrationsRepository registrationsRepository;
    private final FeeItemsRepository feeItemsRepository;

    @Transactional(readOnly = false)
    public Long newRegistrations(RegistrationRequestDto registrationRequestDto) {
        Long id = 0L;

        FeeItems feeItems = feeItemsRepository.findById(registrationRequestDto.getFeeItemId()).orElseThrow(() -> new IllegalArgumentException("해당 금액이 없습니다"));

        Registrations registrations = Registrations.builder()
                .goodName(registrationRequestDto.getGoodName())
                .feeItems(feeItems)
                .price(registrationRequestDto.getPrice())
                .name(registrationRequestDto.getName())
                .birth(Util.stringToLocalDate(registrationRequestDto.getBirth()))
                .affiliation(registrationRequestDto.getAffiliation())
                .position(registrationRequestDto.getPosition())
                .email(registrationRequestDto.getEmail())
                .phone(registrationRequestDto.getPhone()).build();
        registrationsRepository.save(registrations);
        return id;
    }

    @Transactional(readOnly = false)
    public void editRegistrations(Long id,RegistrationRequestDto registrationRequestDto) {

        Registrations registrations = registrationsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("사전등록 내역이 없습니다."));
        FeeItems feeItem = feeItemsRepository.findById(registrationRequestDto.getFeeItemId()).orElseThrow(() -> new IllegalArgumentException("해당 금액이 없습니다"));

        registrations.updateRegistrations(registrationRequestDto,feeItem);

    }

    @Transactional(readOnly = false)
    public void removeRegistration(Long id) {
        Registrations registrations = registrationsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("사전등록 내역이 없습니다."));
        registrations.setDeleted(true);
    }

    public RegistrationsEditResponseDto getRegistrations(Long id) {
        Registrations registrations = registrationsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("사전등록 내역이 없습니다."));
        RegistrationsEditResponseDto registrationsEditResponseDto = new RegistrationsEditResponseDto(registrations.getName(), registrations.getBirth(), registrations.getAffiliation(), registrations.getPosition(), registrations.getEmail(), registrations.getPhone(), registrations.getFeeItems().getId());
        return registrationsEditResponseDto;
    }
}
