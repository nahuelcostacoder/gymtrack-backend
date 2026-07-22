package com.gymtrack.backend.repository;

import com.gymtrack.backend.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {

        Optional<Perfil> findByUsuarioId(Long id);

}
