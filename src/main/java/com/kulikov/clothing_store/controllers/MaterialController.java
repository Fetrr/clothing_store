package com.kulikov.clothing_store.controllers;

import com.kulikov.clothing_store.models.Material;
import com.kulikov.clothing_store.repository.MaterialRepository;
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
@RequestMapping("/materials")
@RequiredArgsConstructor
public class MaterialController {

    private final MaterialRepository materialRepository;

    @GetMapping
    public String getAllMaterials(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size,
            Model model
    ) {
        Page<Material> materialsPage = materialRepository.findAll(
                PageRequest.of(page, size, Sort.by("name").ascending())
        );
        model.addAttribute("materialsPage", materialsPage);
        return "materials-list";
    }
}
