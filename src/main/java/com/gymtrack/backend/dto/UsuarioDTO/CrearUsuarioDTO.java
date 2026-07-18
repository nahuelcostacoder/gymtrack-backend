package com.gymtrack.backend.dto.UsuarioDTO;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class CrearUsuarioDTO {

    @NotBlank(message = "El nombre de usuario es obligatorio") //tampoco permite espacios
    @Size(min = 3, max = 30)
    private String username;

    @NotBlank(message = "El mail es obligatorio")
    @Email
    @Size(max = 100)
    private String email;

    @NotBlank
    @Size(min = 8, max = 100)
    private String password;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 50)
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(max = 50)
    private String apellido;

    @Past
    @NotNull
    private LocalDate fechaNacimiento;
}
