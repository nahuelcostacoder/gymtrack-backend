package com.gymtrack.backend.dto.UsuarioDTO;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class ActualizarUsuarioDTO {

    @NotBlank //tampoco permite espacios
    @Size(min = 3, max = 30)
    private String username;

    @NotBlank
    @Size(max = 50)
    private String nombre;

    @NotBlank
    @Size(max = 50)
    private String apellido;

    @Size(max = 500)
    private String fotoPerfilUrl;

    @Past
    @NotNull
    private LocalDate fechaNacimiento;
}
