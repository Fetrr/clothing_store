package com.kulikov.clothing_store.repository;

import com.kulikov.clothing_store.models.UserClothing;
import com.kulikov.clothing_store.models.UserClothingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserClothingRepository extends JpaRepository<UserClothing, UserClothingId> {
}
