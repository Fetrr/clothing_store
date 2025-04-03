package com.kulikov.clothing_store.repository;

import com.kulikov.clothing_store.models.Clothing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothingRepository extends JpaRepository<Clothing, Long> {
    Page<Clothing> findAll(Pageable pageable);
    Clothing findByName(String name);
}
