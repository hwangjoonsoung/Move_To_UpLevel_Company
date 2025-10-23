package me.mtuc.conference.paper.service;

import lombok.RequiredArgsConstructor;
import me.mtuc.conference.enums.AbstractLanguage;
import me.mtuc.conference.enums.PresentationType;
import me.mtuc.conference.paper.dto.AuthorAffiliationDto;
import me.mtuc.conference.paper.dto.AuthorDto;
import me.mtuc.conference.paper.dto.NewPaperDTO;
import me.mtuc.conference.paper.dto.PaperDto;
import me.mtuc.conference.paper.entity.Author;
import me.mtuc.conference.paper.entity.Paper;
import me.mtuc.conference.paper.repositroy.PaperRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaperService {

    private final PaperRepository paperRepository;

    public Paper getPaperById(Long id) {
        Paper paper = paperRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당하는 논문이 없습니다"));
        return paper;
    }

    public void newPaper(NewPaperDTO newPaperDTO) {
        PaperDto paperDto = newPaperDTO.getPaperDto();
        List<AuthorDto> authorDtoList = newPaperDTO.getAuthorDtoList();
        List<AuthorAffiliationDto> authorAffiliationDtoList = newPaperDTO.getAuthorAffiliationDtoList();
        Paper paper = Paper.builder()
                .title(paperDto.getTitle())
                .presentationType(PresentationType.valueOf(paperDto.getPresentationType()))
                .abstractLanguage(AbstractLanguage.valueOf(paperDto.getAbstractLanguage()))
                .keyword(paperDto.getKeyword())
                .paperAbstract(paperDto.getPaperAbstract())
                .build();

        authorDtoList.forEach(authorDto -> {
            Author author = Author.builder()
                    .affiliationCode(authorDto.getAffiliationCode())
                    .name(authorDto.getName())
                    .build();
            paper.addAuthor(author);
        });

        authorAffiliationDtoList.forEach(authorAffiliationDto -> {
            
        });

    }
}
