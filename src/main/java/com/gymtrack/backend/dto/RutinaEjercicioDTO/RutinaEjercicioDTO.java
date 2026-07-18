package com.gymtrack.backend.dto.RutinaEjercicioDTO;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class RutinaEjercicioDTO {

    private Long id;

    private Integer orden;

    private Integer seriesObjetivo;

    private BigDecimal pesoObjetivo;

    private Integer descansoSegundos;

    private String observaciones;

    private EjercicioDTO ejercicio;
}
