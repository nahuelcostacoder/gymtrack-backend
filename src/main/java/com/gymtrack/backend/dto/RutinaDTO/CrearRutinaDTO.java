package com.gymtrack.backend.dto.RutinaDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class CrearRutinaDTO {

    @Size(max = 30)
    private String nombre;

    @Size(max = 500)
    private String descripcion;

    private boolean publica;

    @Builder.Default
    private List<CrearRutinaEjercicioDTO> ejercicios = new ArrayList<>();

}
