package com.gymtrack.backend.dto.RutinaDTO;

import com.gymtrack.backend.dto.RutinaEjercicioDTO.CrearRutinaEjercicioDTO;
import jakarta.validation.Valid;
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

    //esto es temporal hasta implementar JWT
    private Long usuarioId;

    @Valid
    @Builder.Default
    private List<CrearRutinaEjercicioDTO> ejercicios = new ArrayList<>();

}
