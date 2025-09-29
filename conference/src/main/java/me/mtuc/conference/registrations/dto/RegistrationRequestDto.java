package me.mtuc.conference.registrations.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class RegistrationRequestDto {

    private String goodName;
    private Long feeItemId;
    private int price;
    private String name;
    private LocalDate birth;
    private String affiliation;
    private String position;
    private String email;
    private String phone;

}
