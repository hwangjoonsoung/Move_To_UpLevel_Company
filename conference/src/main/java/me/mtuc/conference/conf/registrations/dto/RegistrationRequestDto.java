package me.mtuc.conference.conf.registrations.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Builder
public class RegistrationRequestDto {

    @NotBlank(message = "상품명을 입력해 주세요")
    private String goodName;

    @NotNull(message = "가격 코드를 입력해 주세요")
    private Long feeItemId;

    @NotNull(message = "금액을 선택해 주세요")
    private Integer price;

    @NotBlank(message = "성명을 입력해 주세요")
    @Size(max = 100, message = "성명은 최대 100자 까지 입력가능하니다.")
    private String name;
    @NotBlank(message = "생년월일을 입력해 주시기 바랍니다.")
    @Size(min = 8,max = 8, message = "생년월일을 8자로 입력해 주시기 바랍니다. ex)19990909")
    @Pattern(regexp = "^[0-9]+$",message = "-을 제외하고 입력해 주시기 바랍니다.")
    private String birth;
    @NotBlank(message = "소속을 입력해 주시기 바랍니다")
    private String affiliation;
    @NotBlank(message = "직책을 입력해 주시기 바랍니다.")
    private String position;
    @NotBlank(message = "이메일을 입력해 주시기 바랍니다.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;
    @NotBlank(message = "전화번호를 입력해 주시기 바랍니다.")
    private String phone;

    public LocalDate stringToLocalDate(){
        return LocalDate.parse(this.birth, DateTimeFormatter.ofPattern("yyyyMMdd"));
    }
}
