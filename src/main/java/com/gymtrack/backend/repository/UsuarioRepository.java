package com.gymtrack.backend.repository;

import com.gymtrack.backend.model.Usuario;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    @EntityGraph(attributePaths = {
            "roles",
            "roles.permisos"
    }) //por mas que sea lazy quiero que para este metodo si me traiga todo
    Optional<Usuario> findByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}
