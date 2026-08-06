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
            WHERE (a.emisorSolicitud.id = :usuarioId OR a.receptorSolicitud.id = :usuarioId)
                AND a.estado = ACEPTADA
            """)
    List<Amistad> listarAmigos(Long usuarioId); //es mejor asi pq sino tendria que hacer dos consultas con jpa
    //una para emisor y otra para receptor

    @Query("""
            SELECT a
            FROM Amistad a
            WHERE a.receptorSolicitud.id = :usuarioId AND a.estado = PENDIENTE
            """)
    List<Amistad> listarSolicitudesRecibidas(Long usuarioId);

    @Query("""
            SELECT a
            FROM Amistad a
            WHERE a.emisorSolicitud.id = :usuarioId and a.estado = PENDIENTE
            """)
    List<Amistad> listarSolicitudesEnviadas(Long usuarioId);
}
