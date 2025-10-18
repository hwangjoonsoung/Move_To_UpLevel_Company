package me.mtuc.conference.paper.service;

import lombok.RequiredArgsConstructor;
import me.mtuc.conference.paper.dto.NewPaperDTO;
import me.mtuc.conference.paper.entity.Paper;
import me.mtuc.conference.paper.repositroy.PaperRepository;
import org.springframework.stereotype.Service;

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
        
    }
}
