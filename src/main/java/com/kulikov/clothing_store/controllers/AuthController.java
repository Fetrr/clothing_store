package com.kulikov.clothing_store.controllers;

import com.kulikov.clothing_store.dto.request.UserRegistrationRequest;
import com.kulikov.clothing_store.models.Role;
import com.kulikov.clothing_store.models.User;
import com.kulikov.clothing_store.repository.RoleRepository;
import com.kulikov.clothing_store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Страница входа
    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    // Страница регистрации
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new UserRegistrationRequest("", "", "", 2L)); // 2L = ROLE_USER
        return "auth/register";
    }

    // Обработка регистрации
    @PostMapping("/register")
    public String registerUser(
            @ModelAttribute("user") UserRegistrationRequest request,
            RedirectAttributes redirectAttributes
    ) {
        if (userRepository.findByEmail(request.email()).isPresent()) {
            redirectAttributes.addFlashAttribute("error", "Email уже занят");
            return "redirect:/auth/register";
        }

        User user = new User();
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));

        Role userRole = roleRepository.findById(request.roleId())
                .orElseThrow(() -> new RuntimeException("Роль не найдена"));
        user.setRole(userRole);

        userRepository.save(user);
        return "redirect:/auth/login?success";
    }
}