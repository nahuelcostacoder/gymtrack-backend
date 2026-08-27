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

    private String username;


    private String email;


    private String nombre;

    private String apellido;

    private LocalDate fechaNacimiento;

    private boolean habilitado;

    @Builder.Default
    private Set<String> roles = new HashSet<>();

}
