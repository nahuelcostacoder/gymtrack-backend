package com.gymtrack.backend.dto.SeriesEjercicioDTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class ActualizarNumeroSerieDTO {

    @NotNull
    @Min(1)
    private Integer numeroSerie;
}
