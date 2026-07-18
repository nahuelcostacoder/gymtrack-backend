package com.gymtrack.backend.dto.EntrenamientoDTO;

import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class CrearEntrenamientoDTO {

    private Long rutinaId; //no es obligatorio, puede empezar un entrenamiento sin rutina

    @Size(max = 500)
    private String observaciones;
}
