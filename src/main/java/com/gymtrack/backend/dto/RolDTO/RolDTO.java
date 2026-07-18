package com.gymtrack.backend.dto.RolDTO;

import com.gymtrack.backend.model.Permiso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class RolDTO {

    private Long id;

    private String nombre;

    @Builder.Default
    private Set<String> permisos = new HashSet<>();
}
