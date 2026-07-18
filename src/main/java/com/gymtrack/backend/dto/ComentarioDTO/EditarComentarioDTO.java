package com.gymtrack.backend.dto.ComentarioDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class EditarComentarioDTO {

    @NotBlank
    @Size(max = 1000)
    private String contenido;
}
