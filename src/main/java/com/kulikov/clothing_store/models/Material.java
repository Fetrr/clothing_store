package com.kulikov.clothing_store.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "materials")
@Data
@NoArgsConstructor
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_id")
    private Long id;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "amount_of_material")
    private double amountOfMaterial;

    @ElementCollection
    @CollectionTable(name = "material_colours", joinColumns = @JoinColumn(name = "material_id"))
    @Column(nullable = false, name = "colours")
    private List<String> colours;

    @ElementCollection
    @CollectionTable(name = "material_composition", joinColumns = @JoinColumn(name = "material_id"))
    @Column(nullable = false, name = "composition")
    private List<String> composition;

    @OneToMany(mappedBy = "material", cascade = CascadeType.ALL)
    private List<ClothingMaterialProcess> materialProcesses = new ArrayList<>();
}
