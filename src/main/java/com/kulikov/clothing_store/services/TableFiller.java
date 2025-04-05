package com.kulikov.clothing_store.services;

import com.github.javafaker.Faker;
import com.kulikov.clothing_store.models.*;
import com.kulikov.clothing_store.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Profile("filler")
public class TableFiller implements CommandLineRunner {

    private final ClothingRepository clothingRepository;
    private final MaterialRepository materialRepository;
    private final WorkingProcessRepository workingProcessRepository;
    private final ClothingMaterialProcessRepository cmpRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final RolePermissionRepository rolePermissionRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Faker faker;

    @Transactional
    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            try {
                // 1. Создаем базовые права доступа
                Permission read = createPermission("READ", "Read access");
                Permission write = createPermission("WRITE", "Write access");
                Permission delete = createPermission("DELETE", "Delete access");
                Permission admin = createPermission("ADMIN", "Admin privileges");

                // 2. Создаем роли
                Role userRole = createRole("USER", "Regular user");
                Role adminRole = createRole("ADMIN", "Administrator");

                // 3. Назначаем права ролям
                assignPermissionToRole(userRole, read);
                assignPermissionToRole(adminRole, read);
                assignPermissionToRole(adminRole, write);
                assignPermissionToRole(adminRole, delete);
                assignPermissionToRole(adminRole, admin);

                // 4. Создаем тестовых пользователей
                createUser(
                        "user@example.com",
                        "user123",
                        "John Doe",
                        "/images/avatar1.jpg",
                        userRole
                );

                createUser(
                        "admin@example.com",
                        "admin123",
                        "Admin Smith",
                        "/images/avatar2.jpg",
                        adminRole
                );

                fillClothingData();

                System.out.println("Database successfully initialized!");
            } catch (Exception e) {
                System.err.println("Error during database initialization:");
                e.printStackTrace();
                throw new RuntimeException("Database initialization failed", e);
            }
        } else {
            System.out.println("Database already contains data. Skipping initialization.");
        }
    }

    private Permission createPermission(String name, String description) {
        Permission permission = new Permission();
        permission.setName(name);
        permission.setDescription(description);
        return permissionRepository.save(permission);
    }

    private Role createRole(String name, String description) {
        Role role = new Role();
        role.setName(name);
        role.setDescription(description);
        return roleRepository.save(role);
    }

    private void assignPermissionToRole(Role role, Permission permission) {
        RolePermission rolePermission = new RolePermission();
        rolePermission.setId(new RolePermissionId(role.getId(), permission.getId()));
        rolePermission.setRole(role);
        rolePermission.setPermission(permission);
        rolePermissionRepository.save(rolePermission);
    }

    private void createUser(String email, String password, String username,
                            String profilePhoto, Role role) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setUsername(username);
        user.setProfilePhotoUrl(profilePhoto);
        user.setRole(role); // Устанавливаем связь с ролью

        userRepository.save(user);
    }

    private void fillClothingData() {
        List<Material> materials = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Material material = new Material();
            material.setName(faker.commerce().material());
            material.setAmountOfMaterial(faker.number().numberBetween(10, 1000));
            material.setColours(
                    List.of(
                            faker.color().name(),
                            faker.color().name(), faker.color().name()
                    ));
            material.setComposition(
                    List.of(
                            faker.lorem().word() + "material",
                            faker.lorem().word() + "material"
                    ));

            materials.add(materialRepository.save(material));
        }

        List<WorkingProcess> processes = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            WorkingProcess process = new WorkingProcess();
            process.setName(faker.job().title() + "_process");
            process.setDuration(faker.number().numberBetween(10, 120));
            process.setInstruments(
                    List.of(
                            faker.lorem().word() + "_instrument",
                            faker.lorem().word() + "_instrument"
                    ));
            processes.add(workingProcessRepository.save(process));
        }

        for (int i = 0; i < 100; i++) {
            Clothing clothing = new Clothing();
            clothing.setName(faker.commerce().productName());
            clothing.setSizes(
                    List.of("S", "M", "L", "XL"));
            clothing.setPhotos(
                    List.of(
                            "/photos/photo" + i + "_" + (i+1000) + ".jpg",
                            "/photos/photo" + i + "_" + (i+2000) + ".jpg"
                    ));
            clothing.setPatterns(
                    List.of(
                            "/photos/pattern" + i + "_" + (i+1000) + ".pdf",
                            "/photos/pattern" + i + "_" + (i+2000) + ".pdf"
                    ));
            clothing = clothingRepository.save(clothing);

            for (int j = 0; j < 100; j++) {
                Material material_ob = materials.get(faker.number().numberBetween(0, materials.size()));
                WorkingProcess process_ob = processes.get(faker.number().numberBetween(0, processes.size()));
                ClothingMaterialProcessId cmp_id = new ClothingMaterialProcessId(
                        clothing.getId(),
                        material_ob.getId(),
                        process_ob.getId()
                );

                ClothingMaterialProcess cmp = new ClothingMaterialProcess();
                cmp.setId(cmp_id);
                cmp.setClothing(clothing);
                cmp.setMaterial(material_ob);
                cmp.setProcess(process_ob);
                cmp.setRequiredAmountOfMaterial(faker.number().numberBetween(1, 9));

                cmpRepository.save(cmp);
            }
        }
    }
}
