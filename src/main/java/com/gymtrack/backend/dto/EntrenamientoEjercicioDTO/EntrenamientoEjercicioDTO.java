package com.gymtrack.backend.dto.EntrenamientoEjercicioDTO;

import com.gymtrack.backend.dto.EjercicioDTO.EjercicioDTO;
import com.gymtrack.backend.model.Ejercicio;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class EntrenamientoEjercicioDTO {

    private Long id;

    private EjercicioDTO ejercicio;

    private Integer orden;

    private String observaciones;

    //no pongo entrenamiento ya que la idea es que desde entrenamiento se puedan consultar los ejercicios del mismo
}
