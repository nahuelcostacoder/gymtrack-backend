package com.gymtrack.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
@Table(
        name = "amistades",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"emisor_id", "receptor_id"}
                )
        }
) //aca lo que hacemos es que la bd rechace dos solicitudes de amistad iguales
@Entity
public class Amistad extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoAmistad estado;

    @Column(nullable = false)
    private LocalDateTime fechaSolicitud;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emisor_id", nullable = false)
    private Usuario emisorSolicitud;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receptor_id", nullable = false)
    private Usuario receptorSolicitud;
}
