package com.gymtrack.backend.dto.SeriesEjercicioDTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class ActualizarSerieEjercicioDTO {

    @Min(1)
    private Integer numeroSerie;
    @Positive
    private Double peso;

    @Positive
    private Integer repeticiones;

    @PositiveOrZero
    private Integer rir;

    private Boolean completada;

    @Length(max = 500)
    private String observaciones;
}
