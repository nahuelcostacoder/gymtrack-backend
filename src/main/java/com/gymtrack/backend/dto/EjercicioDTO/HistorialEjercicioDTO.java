package com.gymtrack.backend.dto.EjercicioDTO;

import com.gymtrack.backend.dto.EntrenamientoEjercicioDTO.EntrenamientoEjercicioDTO;
import com.gymtrack.backend.dto.SeriesEjercicioDTO.SerieEjercicioDTO;
import com.gymtrack.backend.model.EntrenamientoEjercicio;
import com.gymtrack.backend.model.SerieEjercicio;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
public class HistorialEjercicioDTO {

    private Long entrenamientoId;

    private LocalDateTime fechaEntrenamiento;

    private String nombreRutina;

    private List<SerieEjercicioDTO> series;


}
