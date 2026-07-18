package com.gymtrack.backend.dto.PerfilDTO;

import com.gymtrack.backend.model.NivelEntrenamiento;
import com.gymtrack.backend.model.Objetivo;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class ActualizarPerfilDTO {

    @Size(max = 500)
    private String biografia;

    @NotNull
    @DecimalMin(value = "20.0")
    @DecimalMax(value = "400.0")
    private BigDecimal peso;

    @NotNull
    @DecimalMin(value = "0.80")
    @DecimalMax(value = "2.80")
    private BigDecimal altura;

    @NotNull
    private Objetivo objetivo;

    @NotNull
    private NivelEntrenamiento nivelEntrenamiento;
}
