package com.gymtrack.backend.repository;

import com.gymtrack.backend.model.GrupoMuscular;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoMuscularRepository extends JpaRepository<GrupoMuscular, Long> {

    boolean existsByNombre(String nombre);
}
