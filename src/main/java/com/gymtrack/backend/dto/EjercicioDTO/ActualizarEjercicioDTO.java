package com.gymtrack.backend.dto.EjercicioDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.URL;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class ActualizarEjercicioDTO {

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

}
