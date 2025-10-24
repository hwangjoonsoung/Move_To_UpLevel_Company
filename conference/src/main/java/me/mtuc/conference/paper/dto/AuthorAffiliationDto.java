package me.mtuc.conference.paper.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthorAffiliationDto {

    @NotEmpty(message = "코드가 정상적으로 입력되지 않았습니다")
    private Integer code;
    @NotEmpty(message = "소속을 입력해 주시기 바랍니다")
    @Size(max = 90, message = "입력된 소속정보가 너무 깁니다.")
    private String affiliation;
}
