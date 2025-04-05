package com.kulikov.clothing_store.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "role_permission")
@Data
@NoArgsConstructor
public class RolePermission {

    @EmbeddedId
    private RolePermissionId id;

    @ManyToOne
    @MapsId("role_id")
    @JoinColumn(name = "role_id", foreignKey = @ForeignKey(name = "fk_role_id"))
    private Role role;

    @ManyToOne
    @MapsId("permission_id")
    @JoinColumn(name = "permission_id", foreignKey = @ForeignKey(name = "fk_permission_id"))
    private Permission permission;
}


