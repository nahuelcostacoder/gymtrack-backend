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

    @NotNull
    private Long ejercicioId;

    @NotNull
    @Min(1)
    private Integer orden;

    @NotEmpty
    private String observaciones;
}
