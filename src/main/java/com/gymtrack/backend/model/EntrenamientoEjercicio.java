package com.gymtrack.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
@Entity
@Table(
        name = "entrenamiento_ejercicio",
        uniqueConstraints = {
              @UniqueConstraint(
                      columnNames = {"entrenamiento_id", "orden"}
              )
        }
)
public class EntrenamientoEjercicio extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer orden;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "entrenamiento_id", nullable = false)
    private Entrenamiento entrenamiento;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ejercicio_id", nullable = false)
    private Ejercicio ejercicio;

    @Column(length = 500)
    private String observaciones;

    @OneToMany(
            mappedBy = "entrenamientoEjercicio",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @OrderBy("numeroSerie ASC")
    @Builder.Default
    private List<SerieEjercicio> series = new ArrayList<>();
}
