package com.gymtrack.backend.dto.RolDTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
public class CrearRolDTO {

    @NotBlank
    @Size(max = 30)
    private String nombre;
}
