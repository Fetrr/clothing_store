package com.kulikov.clothing_store.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clothing")
@Data
@NoArgsConstructor
public class Clothing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clothing_id")
    private Long id;

    @Column(nullable = false, name = "name")
    private String name;

    @ElementCollection
    @CollectionTable(name = "clothing_sizes", joinColumns = @JoinColumn(name = "clothing_id"))
    @Column(name = "sizes")
    private List<String> sizes;

    @ElementCollection
    @CollectionTable(name = "clothing_photos", joinColumns = @JoinColumn(name = "clothing_id"))
    @Column(name = "photos")
    private List<String> photos;

    @ElementCollection
    @CollectionTable(name = "clothing_patterns", joinColumns = @JoinColumn(name = "clothing_id"))
    @Column(name = "patterns")
    private List<String> patterns;

    @OneToMany(mappedBy = "clothing", cascade = CascadeType.ALL)
    private List<ClothingMaterialProcess> materialProcesses = new ArrayList<>();
}
