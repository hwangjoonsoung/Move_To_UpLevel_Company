package me.mtuc.conference.registrations.dto;

import java.time.LocalDate;

public record RegistrationsEditResponseDto(
        String name
        , LocalDate birth
        , String affiliation
        , String position
        , String email
        , String phone
        , Long feeItemId
) {
}
