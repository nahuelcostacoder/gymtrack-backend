package com.gymtrack.backend.repository;

import com.gymtrack.backend.dto.PermisoDTO.PermisoDTO;
import com.gymtrack.backend.model.Permiso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermisoRepository extends JpaRepository<Permiso, Long> {

    Optional<Permiso> findByNombre(String nombre);
}
