package com.gymtrack.backend.model;


import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
@Entity
@Table(name = "series_ejercicio",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {
                                "entrenamiento_ejercicio_id",
                                "numero_serie"
                        }
                )
        }

)
public class SerieEjercicio extends EntidadAuditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer numeroSerie;

    @Column(nullable = false)
    private Double peso;

    @Column(nullable = false)
    private Integer repeticiones;

    private Integer rir;

    @Column(nullable = false)
    private Boolean completada;

    @Column(length = 500)
    private String observaciones;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "entrenamiento_ejercicio_id", nullable = false)
    private EntrenamientoEjercicio entrenamientoEjercicio;
}
