package com.kulikov.clothing_store.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserClothingId implements Serializable {
    private Long user_id;
    private Long clothing_id;
}
