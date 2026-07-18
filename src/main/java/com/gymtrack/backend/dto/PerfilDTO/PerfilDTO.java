package com.gymtrack.backend.dto.PerfilDTO;

import com.gymtrack.backend.model.NivelEntrenamiento;
import com.gymtrack.backend.model.Objetivo;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
public class PerfilDTO {

    private Long id;

    private String biografia;

    private BigDecimal peso;

    private BigDecimal altura;

    private Objetivo objetivo;

    private NivelEntrenamiento nivelEntrenamiento;
}
