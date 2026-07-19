package com.gymtrack.backend.dto.UsuarioDTO;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class UsuarioDTO {

    private Long id;

    @NotBlank //tampoco permite espacios
    @Size(min = 3, max = 30)
    private String username;

    @NotBlank
    @Email
    @Size(max = 100)
    private String email;

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

    private boolean habilitado;

    @Builder.Default
    private Set<String> roles = new HashSet<>();

}
