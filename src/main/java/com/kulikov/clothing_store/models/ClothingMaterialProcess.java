package com.kulikov.clothing_store.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clothing_material_process")
@Data
@NoArgsConstructor
public class ClothingMaterialProcess {
    @EmbeddedId
    private ClothingMaterialProcessId id;

    @ManyToOne
    @MapsId("clothing_id")
    @JoinColumn(name = "clothing_id", foreignKey = @ForeignKey(name = "fk_clothing_id"))
    private Clothing clothing;

    @ManyToOne
    @MapsId("material_id")
    @JoinColumn(name = "material_id", foreignKey = @ForeignKey(name = "fk_material_id"))
    private Material material;

    @ManyToOne
    @MapsId("process_id")
    @JoinColumn(name = "process_id", foreignKey = @ForeignKey(name = "fk_process_id"))
    private WorkingProcess process;

    @Column(name = "required_amount_of_material")
    private double requiredAmountOfMaterial;
}
