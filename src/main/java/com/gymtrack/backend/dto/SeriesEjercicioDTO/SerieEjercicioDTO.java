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
public class SerieEjercicioDTO {

    private Long id;

    private Integer numeroSerie;

    private Double peso;

    private Integer repeticiones;

    private Integer rir;

    private Boolean completada;

    private String observaciones;


}
