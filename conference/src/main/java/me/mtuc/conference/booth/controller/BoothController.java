package me.mtuc.conference.booth.controller;

import lombok.RequiredArgsConstructor;
import me.mtuc.conference.booth.dto.BoothEditResponseDto;
import me.mtuc.conference.booth.service.BoothService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class BoothController {

    private final BoothService boothService;

    @GetMapping("/booth/new")
    public String newBooth() {
        return "/booth/new";
    }

    @GetMapping("/booth/{id}/edit")
    public String editBooth(@PathVariable Long id, Model model) {
        BoothEditResponseDto booth = boothService.findBoothById(id);
        model.addAttribute("booth", booth);
        return "/booth/edit";
    }

    @GetMapping("/booth/{id}/show")
    public String showBooth(@PathVariable Long id, Model model) {
        BoothEditResponseDto booth = boothService.findBoothById(id);
        model.addAttribute("booth", booth);
        return "/booth/show";
    }

    @GetMapping("/booth/")
    public String indexBooth() {
        return "/booth/index";
    }

}
