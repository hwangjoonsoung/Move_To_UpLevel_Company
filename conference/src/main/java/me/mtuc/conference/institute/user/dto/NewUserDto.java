package me.mtuc.conference.institute.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.WithBy;

@Setter
@Getter
@Builder
public class NewUserDto {

    private String name;
    private String password;
    private String affiliation;
    private String position;
    private String phone;
    private String email;
    private String member_type;
    private String roll;

}
