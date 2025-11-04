package me.mtuc.conference.conf.registrations.dto;

import java.time.LocalDate;

public record RegistrationEditResponseDto(
        String name
        , LocalDate birth
        , String affiliation
        , String position
        , String email
        , String phone
        , Long feeItemId
) {
}
