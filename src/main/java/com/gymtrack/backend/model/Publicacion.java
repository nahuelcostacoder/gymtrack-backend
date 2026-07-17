package com.gymtrack.backend.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
@Table(name = "publicaciones")
@Entity
public class Publicacion extends EntidadAuditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500)
    private String contenido;

    @Column(length = 500)
    private String fotoUrl;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "entrenamiento_id", nullable = false, unique = true)
    private Entrenamiento entrenamiento;

}
