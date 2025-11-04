package me.mtuc.conference.conf.paper.dto;

import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Setter
@Getter
@Builder
@ToString
public class NewPaperDTO {

    @Valid
    private PaperDto paperDto;
    @Valid
    private ArrayList<AuthorDto> authorDtoList;
    @Valid
    private ArrayList<AuthorAffiliationDto> authorAffiliationDtoList;

}
