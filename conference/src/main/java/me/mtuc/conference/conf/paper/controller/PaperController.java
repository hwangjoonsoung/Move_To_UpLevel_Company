package me.mtuc.conference.conf.paper.controller;

import lombok.RequiredArgsConstructor;
import me.mtuc.conference.conf.paper.entity.Paper;
import me.mtuc.conference.conf.paper.service.PaperService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class PaperController {

    private final PaperService paperService;

    @GetMapping("/paper/")
    public String index() {
        return "/paper/index";
    }

    @GetMapping("/paper/new")
    public String newPaper() {
        return "/paper/new";
    }

    @GetMapping("/paper/{id}/edit")
    public String editPaper(@PathVariable(name = "id")Long id, Model model){
        Paper paper = paperService.getPaperById(id);
        model.addAttribute("paper", paper);
        return "/paper/edit";
    }
}
