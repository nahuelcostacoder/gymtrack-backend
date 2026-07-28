package com.gymtrack.backend.dto.EntrenamientoDTO;

import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class ActualizarEntrenamientoDTO {

    private Long rutinaId;

    @PastOrPresent
    private LocalDateTime fechaInicio;

    @PastOrPresent
    private LocalDateTime fechaFin;

    @Size(max = 500)
    private String observaciones;
}
