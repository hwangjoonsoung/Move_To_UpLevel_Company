package me.mtuc.conference.booth.controller;

import lombok.RequiredArgsConstructor;
import me.mtuc.conference.booth.service.BoothService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

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
        return "/booth/edit";
    }

    @GetMapping("/booth/")
    public String indexBooth() {
        return "/booth/index";
    }



}
