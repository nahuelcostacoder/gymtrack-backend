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

    @Size(min = 3, max = 30)
    private String username;

    @Size(max = 50)
    private String nombre;

    @Size(max = 50)
    private String apellido;

    @Past
    private LocalDate fechaNacimiento;
}
