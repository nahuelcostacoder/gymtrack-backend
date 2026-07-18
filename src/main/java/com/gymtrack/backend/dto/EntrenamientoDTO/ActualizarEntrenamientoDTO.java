package com.gymtrack.backend.dto.EntrenamientoDTO;

import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class ActualizarEntrenamientoDTO {

    @Size(max = 500)
    private String observaciones;
}
