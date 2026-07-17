package com.gymtrack.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
@Table(name = "rutina_ejercicio")
@Entity
public class RutinaEjercicio extends EntidadAuditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer orden;

    private Integer seriesObjetivo;

    private Integer repeticionesObjetivo;

    private BigDecimal pesoObjetivo;

    private Integer descansoSegundos;

    @Column(length = 500)
    private String observaciones;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rutina_id", nullable = false)
    private Rutina rutina;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "ejercicio_id", nullable = false)
    private Ejercicio ejercicio;

}
