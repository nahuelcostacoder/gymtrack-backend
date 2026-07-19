package com.gymtrack.backend.dto.RutinaEjercicioDTO;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class CrearRutinaEjercicioDTO {

    @NotNull
    @Min(1)
    private Integer orden;

    @NotNull
    @Min(1)
    private Integer seriesObjetivo;

    @NotNull
    @Min(1)
    private Integer repeticionesObjetivo;

    @NotNull
    @PositiveOrZero
    private BigDecimal pesoObjetivo;

    @NotNull
    @PositiveOrZero
    private Integer descansoSegundos;

    @Size(max = 500)
    private String observaciones;

    @NotNull
    private Long ejercicioId;
}
