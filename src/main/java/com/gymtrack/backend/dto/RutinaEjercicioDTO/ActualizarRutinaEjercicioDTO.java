package com.gymtrack.backend.dto.RutinaEjercicioDTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class ActualizarRutinaEjercicioDTO {

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
    private BigDecimal pesoOjetivo;

    @NotNull
    @PositiveOrZero
    private Integer descansoSegundos;

    @Size(max = 500)
    private String observaciones;

    @NotNull
    private Long ejercicio_id;
}
