package com.gymtrack.backend.dto.PermisoDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class PermisoDTO {

    private Long id;

    private String nombre;
}
