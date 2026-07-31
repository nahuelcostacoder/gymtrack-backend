package com.gymtrack.backend.repository;

import com.gymtrack.backend.model.Amistad;
import com.gymtrack.backend.model.EstadoAmistad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmistadRepository extends JpaRepository<Amistad, Long> {

    @Query("""
            SELECT a
            FROM Amistad a
            WHERE (a.emisor.id = :usuarioId OR a.receptor.id = :usuarioId)
                AND a.estado = com.gymtrack.backend.enums.EstadoAmistad.ACEPTADA
            """)
    List<Amistad> listarAmigos(Long usuarioId); //es mejor asi pq sino tendria que hacer dos consultas con jpa
    //una para emisor y otra para receptor

    @Query("""
            SELECT a
            FROM Amistad a
            WHERE a.receptor.id = usuarioId AND a.estado = com.gymtrack.backend.enums.EstadoAmistad.PENDIENTE
            """)
    List<Amistad> listarSolicitudesRecibidas(Long usuarioId);

    @Query("""
            SELECT a
            FROM Amistad a
            WHERE a.emisor.id = :usuarioId and a.estado = com.gymtrack.backend.enums.EstadoAmistad.PENDIENTE
            """)
    List<Amistad> listarSolicitudesEnviadas(Long usuarioId);
}
