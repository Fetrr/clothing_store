package com.kulikov.clothing_store.controllers;

import com.kulikov.clothing_store.dto.request.UserUpdateRequestDto;
import com.kulikov.clothing_store.models.User;
import com.kulikov.clothing_store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserRepository userRepository;

    // Страница профиля
    @GetMapping
    public String profilePage(Authentication authentication, Model model) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

        model.addAttribute("user", user);
        return "profile/index";
    }

    // Обновление профиля
    @PostMapping("/update")
    public String updateProfile(
            @ModelAttribute("user") UserUpdateRequestDto request,
            @RequestParam(value = "profilePhoto", required = false) MultipartFile file,
            Authentication authentication,
            RedirectAttributes redirectAttributes
    ) throws IOException {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

        if (file != null && !file.isEmpty()) {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path uploadPath = Paths.get("uploads/profile-photos");
            Files.createDirectories(uploadPath);
            Files.copy(file.getInputStream(), uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            user.setProfilePhotoUrl("/uploads/profile-photos/" + fileName);
        }

        user.setUsername(request.username());
        userRepository.save(user);

        redirectAttributes.addFlashAttribute("success", "Профиль обновлен");
        return "redirect:/profile";
    }
}
