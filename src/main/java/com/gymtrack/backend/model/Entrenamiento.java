package com.gymtrack.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
@Table(name = "entrenamientos")
@Entity
public class Entrenamiento extends EntidadAuditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fechaInicio;

    private LocalDateTime fechaFin;


    private Integer duracionMinutos;

    @Column(length = 500)
    private String observaciones;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rutina_id")
    private Rutina rutina;

    @Transient
    public Long getDuracionMinutos(){

        if (fechaInicio == null && fechaFin == null){
            return null;
        }
        return Duration.between(fechaInicio, fechaFin).toMinutes();
    }
}
