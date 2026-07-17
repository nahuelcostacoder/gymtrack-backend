package com.gymtrack.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Table(
        name = "megustas",
        uniqueConstraints = {

                @UniqueConstraint(
                        columnNames = {"usuario_id", "publicacion_id"}
                )
        }) //evita que un mismo usuario le de me gusta varias veces a una publicacion
@Builder
@Entity
public class MeGusta extends EntidadAuditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "publicacion_id", nullable = false)
    private Publicacion publicacion;
}
