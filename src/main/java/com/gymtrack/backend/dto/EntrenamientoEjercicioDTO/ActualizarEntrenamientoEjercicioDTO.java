package com.gymtrack.backend.dto.EntrenamientoEjercicioDTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class ActualizarEntrenamientoEjercicioDTO {

    private Long ejercicioId;

    @Min(1)
    private Integer orden;

    private String observaciones;
}
