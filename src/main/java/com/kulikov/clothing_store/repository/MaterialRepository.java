package com.kulikov.clothing_store.repository;

import com.kulikov.clothing_store.models.Material;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
    Page<Material> findAll(Pageable pageable);
}
