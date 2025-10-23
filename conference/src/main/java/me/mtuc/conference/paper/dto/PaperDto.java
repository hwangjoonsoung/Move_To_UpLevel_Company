package me.mtuc.conference.paper.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import me.mtuc.conference.custom_annotation.WordCount;

@Getter
@Setter
public class PaperDto {

    @NotBlank(message = "논문의 선택해 주시기바랍니다.")
    private Integer category;
    @NotEmpty(message = "논문의 제목을 입력해 주시기 바랍니다")
    @Size(message = "논문 제목은 최대 90자 까지 입력하실 수 있습니다", max = 90)
    private String title;
    @NotEmpty(message = "발표 분야를 선택해 주시기 바랍니다.")
    private String presentationType;
    @NotEmpty(message = "초록 언어를 선택해 주시기 바랍니다.")
    private String abstractLanguage;
    @NotEmpty(message = "키워드를 입력해 주시기 바랍니다.")
    @Size(message = "논문 키워드는 최대 90자 까지 입력하실 수 있습니다", max = 90)
    private String keyword;
    @NotEmpty(message = "초록을 입력해 주시기 입력해 주시기 바랍니다.")
    @WordCount(max = 300)
    private String paperAbstract;

}
