package com.kulikov.clothing_store.repository;

import com.kulikov.clothing_store.models.RolePermission;
import com.kulikov.clothing_store.models.RolePermissionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, RolePermissionId> {
}
