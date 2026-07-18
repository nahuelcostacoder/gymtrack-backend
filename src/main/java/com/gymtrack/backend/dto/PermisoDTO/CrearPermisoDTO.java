package com.gymtrack.backend.dto.PermisoDTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
public class CrearPermisoDTO {

    @NotBlank
    @Size(max = 30)
    private String nombre;
}
