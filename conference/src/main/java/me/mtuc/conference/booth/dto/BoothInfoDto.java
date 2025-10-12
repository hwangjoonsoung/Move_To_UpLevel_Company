package me.mtuc.conference.booth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
public class BoothInfoDto {
    @NotBlank(message = "회사명을 입력해 주세요")
    @Size(max = 40,message = "회사명은 40자 까지 입력하실 수 있습니다.")
    private String companyName;

    @NotBlank(message = "대표자 성명을 입력해 주세요")
    @Size(max = 40,message = "대표자 성명은 40자 까지 입력하실 수 있습니다.")
    private String ceoName;

    @NotBlank(message = "회사 대표번호를 입력해 주세요")
    @Size(max = 20,message = "대표번호는 최대 20자 까지 입력하실 수 있습니다.")
    private String companyPhoneNumber;

    @NotBlank(message = "신청하실 부스의 수량을 선택해 주세요")
    private Integer boothCount;

    @NotBlank(message = "신청하실 부스를 선택해 주세요")
    @Size(max = 30,message = "대표자 성명은 30자 까지 입력하실 수 있습니다.")
    private String boothIds;

    @NotBlank(message = "담당자의 성명을 입력해 주세요")
    @Size(max = 20,message = "담당자 성명은 20자 까지 입력하실 수 있습니다.")
    private String managerName;

    @NotBlank(message = "담당자의 소속을 입력해 주세요")
    @Size(max = 40,message = "담당자 소속은 40자 까지 입력하실 수 있습니다.")
    private String managerAffiliations;

    @NotBlank(message = "담당자의 연락처를 입력해 주세요")
    @Size(max = 20,message = "대표자 연락처는 20자 까지 입력하실 수 있습니다.")
    private String managerPhoneNumber;

    @NotBlank(message = "담당자의 이메일을 입력해 주세요")
    @Size(max = 40,message = "담당자 이메일은 40자 까지 입력하실 수 있습니다.")
    private String managerEmail;

    @NotBlank(message = "비밀번호를 입력해 주세요")
    @Size(max = 20,message = "비밀번호는 최대 20자 까지 입력하실 수 있습니다.")
    private String password;

    @NotBlank(message = "금액을 입력해 주십시오")
    private Integer price;

    @NotNull(message = "가격 코드를 입력해 주세요")
    private Long feeItemId;
}
