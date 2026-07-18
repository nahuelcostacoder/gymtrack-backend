package com.gymtrack.backend.dto.RutinaDTO;


import com.gymtrack.backend.dto.RutinaEjercicioDTO.RutinaEjercicioDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class RutinaDTO {

    private Long id;

    private String nombre;

    private String descripcion;

    private boolean publica;

    //esto de rutina ejercicio recordemos
    @Builder.Default
    private List<RutinaEjercicioDTO> ejercicios = new ArrayList<>();


}
