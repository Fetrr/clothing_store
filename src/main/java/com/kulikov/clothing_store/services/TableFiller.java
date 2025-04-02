package com.kulikov.clothing_store.services;

import com.github.javafaker.Faker;
import com.kulikov.clothing_store.models.*;
import com.kulikov.clothing_store.repository.ClothingMaterialProcessRepository;
import com.kulikov.clothing_store.repository.ClothingRepository;
import com.kulikov.clothing_store.repository.MaterialRepository;
import com.kulikov.clothing_store.repository.WorkingProcessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
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
    private final Faker faker;

    public void run(String... args) throws Exception {
        if (clothingRepository.count() == 0) {
            List<Material> materials = new ArrayList<>();
            for (int i = 0; i < 1000; i++) {
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
            for (int i = 0; i < 1000; i++) {
                WorkingProcess process = new WorkingProcess();
                process.setName(faker.job().title() + "process");
                process.setDuration(faker.number().numberBetween(10, 120));
                process.setInstruments(
                        List.of(
                                faker.lorem().word() + "instrument",
                                faker.lorem().word() + "instrument"
                        ));
                processes.add(workingProcessRepository.save(process));
            }

            for (int i = 0; i < 1000; i++) {
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

                for (int j = 0; j < 1000; j++) {
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
            System.out.println("Database filling completed successfully!");
        } else {
            System.out.println("Database already contains data, skipping filling.");
        }

        // Завершаем работу приложения после заполнения
        System.exit(0);
    }
}
