package com.kulikov.clothing_store.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClothingMaterialProcessId implements Serializable {
    private Long clothing_id;
    private Long material_id;
    private Long process_id;
}
