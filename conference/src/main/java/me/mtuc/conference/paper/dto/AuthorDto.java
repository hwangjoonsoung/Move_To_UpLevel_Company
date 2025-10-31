package me.mtuc.conference.paper.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class AuthorDto {

    @NotEmpty(message = "소속정보를 입력해 주시기 바랍니다")
    private Integer affiliationCode;

    @NotBlank(message = "이름을 입력해 주시기 바랍니다")
    @Size(max = 90, message = "입력하신 이름이 너무 깁니다.")
    private String name;

}
