package com.gymtrack.backend.dto.EjercicioDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class CrearEjercicioDTO {

    @NotBlank
    @Size(max = 30)
    private String nombre;

    @NotBlank
    @Size(max = 500)
    private String descripcion;

    @NotBlank
    @Size(max = 500)
    @URL
    private String videoUrl;

    @NotEmpty
    private Set<Long> gruposMuscularesIds;
}
