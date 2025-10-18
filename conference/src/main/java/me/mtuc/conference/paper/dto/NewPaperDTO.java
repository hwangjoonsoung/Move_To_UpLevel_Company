package me.mtuc.conference.paper.dto;

import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class NewPaperDTO {

    @Valid
    private PaperDto paperDto;
    @Valid
    private AuthorDto authorDto;
    @Valid
    private AuthorAffiliationDto authorAffiliationDto;

}
