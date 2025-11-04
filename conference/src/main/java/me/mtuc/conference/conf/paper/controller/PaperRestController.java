package me.mtuc.conference.conf.paper.controller;

import lombok.RequiredArgsConstructor;
import me.mtuc.conference.conf.paper.dto.EditPaperDto;
import me.mtuc.conference.conf.paper.dto.NewPaperDTO;
import me.mtuc.conference.conf.paper.service.PaperService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PaperRestController {

    private final PaperService paperService;

    @PostMapping("/paper/new")
    public void newPaper(@RequestBody NewPaperDTO newPaperDTO) {
        paperService.newPaper(newPaperDTO);
    }

    @PostMapping("/paper/{id}/edit")
    public void editPaper(@PathVariable(name = "id") Long id, @RequestBody EditPaperDto paperDto) {
        paperService.editPaper(id , paperDto);
    }

    @PostMapping("/paper/{id}/remove")
    public void removePaper(@PathVariable(name = "id") Long id) {
        paperService.removePaper(id);
    }
}
