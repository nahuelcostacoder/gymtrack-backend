package com.gymtrack.backend.repository;

import com.gymtrack.backend.model.Rutina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RutinaRepository extends JpaRepository<Rutina, Long> {

    boolean existsByNombre(String nombre);
    long countByUsuarioId(Long usuarioId);
    boolean existsByNombreAndUsuarioIdAndIdNot(String nombre, Long usuarioId, Long id);
    boolean existsByNombreIgnoreCaseAndUsuarioId(String nombre, Long usuarioId);

}

