package me.mtuc.conference.registrations.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.mtuc.conference.common.repository.CommonRepository;
import me.mtuc.conference.registrations.domain.Registrations;
import me.mtuc.conference.registrations.dto.RegistrationRequestDto;
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
                .feeItems(commonRepository.findFeeItem(registrationRequestDto.getFeeItemId())) //repository에서 feeitemid를 찾아서 넣ㅇ줘야 함
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
}
