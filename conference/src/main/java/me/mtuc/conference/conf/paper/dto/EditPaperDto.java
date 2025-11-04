package me.mtuc.conference.conf.paper.dto;

import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class EditPaperDto {

    @Valid
    public PaperDto paperDto;

    @Valid
    public List<AuthorAffiliationDto> authorAffiliationDto;

    @Valid
    public List<AuthorDto> authorDto;
}
