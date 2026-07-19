package com.gymtrack.backend.dto.EjercicioDTO;

import com.gymtrack.backend.dto.GrupoMuscularDTO.GrupoMuscularDTO;
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
public class EjercicioDTO {

    private Long id;
    private String nombre;

    private String descripcion;

    private String videoUrl;

    @Builder.Default
    List<GrupoMuscularDTO> gruposMusculares = new ArrayList<>();
}
