package com.gymtrack.backend.dto.SeriesEjercicioDTO;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
public class CrearSerieEjercicioDTO {

    @NotNull
    @Min(1)
    private Integer numeroSerie;

    @NotNull
    @Positive
    private Double peso;

    @NotNull
    @Positive
    private Integer repeticiones;

    @PositiveOrZero
    private Integer rir;

    @NotNull
    private Boolean completada;

    @Length(max = 500)
    private String observaciones;
}
