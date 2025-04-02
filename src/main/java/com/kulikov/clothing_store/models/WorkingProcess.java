package com.kulikov.clothing_store.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "working_processes")
@Data
@NoArgsConstructor
public class WorkingProcess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "process_id")
    private Long id;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "duration")
    private int duration;

    @ElementCollection
    @CollectionTable(name = "process_instruments", joinColumns = @JoinColumn(name = "process_id"))
    @Column(nullable = false, name = "instrument")
    private List<String> instruments;

    @OneToMany(mappedBy = "process", cascade = CascadeType.ALL)
    private List<ClothingMaterialProcess> clothingMaterial = new ArrayList<>();
}
