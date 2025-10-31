package me.mtuc.conference.paper.controller;

import lombok.RequiredArgsConstructor;
import me.mtuc.conference.paper.dto.NewPaperDTO;
import me.mtuc.conference.paper.service.PaperService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PaperRestController {

    private final PaperService paperService;

    @PostMapping("/paper/new")
    public void newPaper(@RequestBody NewPaperDTO newPaperDTO) {
        System.out.println("newPaperDTO = " + newPaperDTO);
        paperService.newPaper(newPaperDTO);
    }
}
