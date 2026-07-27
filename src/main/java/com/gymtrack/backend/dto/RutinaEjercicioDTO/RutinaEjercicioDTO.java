package com.gymtrack.backend.dto.RutinaEjercicioDTO;

import com.gymtrack.backend.dto.EjercicioDTO.EjercicioDTO;
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

    private Integer repeticionesObjetivo;

    private BigDecimal pesoObjetivo;

    private Integer descansoSegundos;

    private String observaciones;

    private EjercicioDTO ejercicio;

    //no pongo rutina porque la idea es que desde rutina se llama a los
    // rutinaejercicio dto que es el set de ejercicios que tiene rutina
}
