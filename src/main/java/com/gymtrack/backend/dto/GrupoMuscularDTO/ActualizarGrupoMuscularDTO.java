package com.gymtrack.backend.dto.GrupoMuscularDTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class ActualizarGrupoMuscularDTO {

    @NotEmpty
    @Size(max = 30)
    private String nombre;
}
