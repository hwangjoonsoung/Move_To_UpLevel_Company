package me.mtuc.conference.booth.dto;

import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter@Getter@Builder
public class BoothRequestDto {

    @Valid
    private BoothInfoDto boothInfo;

    @Valid
    private List<StaffInfoDto> staffs;

}
