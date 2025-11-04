package me.mtuc.conference.conf.booth.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter@Builder
@ToString
public class StaffInfoDto {

    @NotEmpty(message = "현장 참여자 이름을 입력해 주세요")
    @Size(max = 5, message = "현장 참여자는 최대 5명까지 입력하실 수 있습니다.")
    private String name;

    @NotEmpty(message = "현장 참여자 소속을 입력해 주세요")
    @Size(max = 5, message = "현장 참여자는 최대 5명까지 입력하실 수 있습니다.")
    private String affiliation;

    @NotEmpty(message = "현장 참여자 직책을 입력해 주세요")
    @Size(max = 5, message = "현장 참여자는 최대 5명까지 입력하실 수 있습니다.")
    private String position;

}
