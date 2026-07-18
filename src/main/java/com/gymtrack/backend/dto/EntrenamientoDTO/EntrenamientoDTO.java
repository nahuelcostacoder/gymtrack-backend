package com.gymtrack.backend.dto.EntrenamientoDTO;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class EntrenamientoDTO {

    private Long id;

    private LocalDateTime fechaInicio;

    private LocalDateTime fechaFin;

    private Integer duracionMinutos;

    private String observaciones;

    private Long rutinaId;

    private String rutinaNombre; //ya con el id y nombre de la rutina alcanza

    //el id del usuario no es necesario, ya que es el logueado.
}
