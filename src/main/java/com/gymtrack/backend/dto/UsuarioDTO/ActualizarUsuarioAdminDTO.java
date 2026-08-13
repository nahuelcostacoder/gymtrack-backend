package com.gymtrack.backend.dto.UsuarioDTO;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
public class ActualizarUsuarioAdminDTO {


    @Size(min = 3, max = 30)
    private String username;

    @Size(max = 50)
    private String nombre;

    @Size(max = 50)
    private String apellido;

    @Past
    private LocalDate fechaNacimiento;

    private boolean habilitado;
}
