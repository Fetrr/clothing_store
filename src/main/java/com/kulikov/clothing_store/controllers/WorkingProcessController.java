package com.kulikov.clothing_store.controllers;

import com.kulikov.clothing_store.models.WorkingProcess;
import com.kulikov.clothing_store.repository.WorkingProcessRepository;
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
@RequestMapping("/working_processes")
@RequiredArgsConstructor
public class WorkingProcessController {

    private final WorkingProcessRepository processRepository;

    @GetMapping
    public String getAllProcesses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size,
            Model model
    ) {
        Page<WorkingProcess> workingProcessesPage = processRepository.findAll(
                PageRequest.of(page, size, Sort.by("name").ascending())
        );
        model.addAttribute("workingProcessesPage", workingProcessesPage);
        return "working-processes-list";
    }
}
