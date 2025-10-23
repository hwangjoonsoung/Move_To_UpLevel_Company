package me.mtuc.conference.paper.dto;

import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
@Builder
public class NewPaperDTO {

    @Valid
    private PaperDto paperDto;
    @Valid
    private ArrayList<AuthorDto> authorDtoList;
    @Valid
    private ArrayList<AuthorAffiliationDto> authorAffiliationDtoList;

}
