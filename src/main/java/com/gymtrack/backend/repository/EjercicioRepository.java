package com.gymtrack.backend.repository;

import com.gymtrack.backend.dto.EjercicioDTO.EjercicioDTO;
import com.gymtrack.backend.model.Ejercicio;
import com.gymtrack.backend.model.GrupoMuscular;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EjercicioRepository extends JpaRepository<Ejercicio, Long> {

    boolean existsByNombre(String nombre);
    List<Ejercicio> findByGruposMuscularesNombre(String nombre);
}
