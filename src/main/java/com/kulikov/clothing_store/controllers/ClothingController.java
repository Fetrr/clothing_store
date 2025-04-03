package com.kulikov.clothing_store.controllers;

import com.kulikov.clothing_store.models.Clothing;
import com.kulikov.clothing_store.repository.ClothingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/clothing")
@RequiredArgsConstructor
public class ClothingController {

    private final ClothingRepository clothingRepository;

    @GetMapping
    public String getAllClothing(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size,
            Model model
    ) {
        Page<Clothing> clothingPage = clothingRepository.findAll(
                PageRequest.of(page, size, Sort.by("name").ascending())
        );
        model.addAttribute("clothingPage", clothingPage);
        return "clothing-list";
    }

}
