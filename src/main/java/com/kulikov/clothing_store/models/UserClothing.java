package com.kulikov.clothing_store.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_clothing")
@Data
@NoArgsConstructor
public class UserClothing {

    @EmbeddedId
    private UserClothingId id;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_id"))
    private User user;

    @ManyToOne
    @MapsId("clothing_id")
    @JoinColumn(name = "clothing_id", foreignKey = @ForeignKey(name = "fk_clothing_id"))
    private Clothing clothing;
}
