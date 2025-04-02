package com.kulikov.clothing_store.repository;

import com.kulikov.clothing_store.models.ClothingMaterialProcess;
import com.kulikov.clothing_store.models.ClothingMaterialProcessId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClothingMaterialProcessRepository
        extends JpaRepository<ClothingMaterialProcess, ClothingMaterialProcessId> {

    List<ClothingMaterialProcess> findByClothingId(Long clothing_id);
}
