package me.mtuc.conference.registrations.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.mtuc.conference.common.repository.CommonRepository;
import me.mtuc.conference.registrations.domain.Registrations;
import me.mtuc.conference.registrations.dto.RegistrationRequestDto;
import me.mtuc.conference.registrations.dto.RegistrationsEditResponseDto;
import me.mtuc.conference.registrations.repository.RegistrationsRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class RegistrationsService {

    private final RegistrationsRepository registrationsRepository;
    private final CommonRepository commonRepository;

    public Long newRegistrations(RegistrationRequestDto registrationRequestDto) {
        Long id = 0L;

        Registrations registrations = Registrations.builder()
                .goodName(registrationRequestDto.getGoodName())
                .feeItems(commonRepository.findFeeItem(registrationRequestDto.getFeeItemId()))
                .price(registrationRequestDto.getPrice())
                .name(registrationRequestDto.getName())
                .birth(registrationRequestDto.getBirth())
                .affiliation(registrationRequestDto.getAffiliation())
                .position(registrationRequestDto.getPosition())
                .email(registrationRequestDto.getEmail())
                .phone(registrationRequestDto.getPhone()).build();

        // todo: repository에서 영속
        registrationsRepository.newRepository(registrations);
        return id;
    }

    public Long editRegistrations(Long id,RegistrationRequestDto registrationRequestDto) {

        Registrations registrations = registrationsRepository.findRegistrations(id);
        registrations.builder()
                .goodName(registrationRequestDto.getGoodName())
                .feeItems(commonRepository.findFeeItem(registrationRequestDto.getFeeItemId()))
                .price(registrationRequestDto.getPrice())
                .name(registrationRequestDto.getName())
                .birth(registrationRequestDto.getBirth())
                .affiliation(registrationRequestDto.getAffiliation())
                .position(registrationRequestDto.getPosition())
                .email(registrationRequestDto.getEmail())
                .phone(registrationRequestDto.getPhone()).build();
        Registrations editedRegistration = registrationsRepository.editRegistration(registrations);
        return editedRegistration.getId();
    }

    public Long removeRegistration(Long id) {

        Registrations registrations = registrationsRepository.findRegistrations(id);
        registrations.set_deleted(true);
        Registrations editedRegistration = registrationsRepository.editRegistration(registrations);
        return editedRegistration.getId();
    }

    public RegistrationsEditResponseDto getRegistrations(Long id) {
        Registrations registrations = registrationsRepository.findRegistrations(id);
        RegistrationsEditResponseDto registrationsEditResponseDto = new RegistrationsEditResponseDto(registrations.getName(), registrations.getBirth(), registrations.getAffiliation(), registrations.getPosition(), registrations.getEmail(), registrations.getPhone(), registrations.getFeeItems().getId());
        return registrationsEditResponseDto;
    }
}
