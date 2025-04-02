package com.kulikov.clothing_store.repository;

import com.kulikov.clothing_store.models.WorkingProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkingProcessRepository extends JpaRepository<WorkingProcess, Long> {
}
