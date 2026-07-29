package com.gymtrack.backend.controller;


import com.gymtrack.backend.dto.SeriesEjercicioDTO.ActualizarNumeroSerieDTO;
import com.gymtrack.backend.dto.SeriesEjercicioDTO.ActualizarSerieEjercicioDTO;
import com.gymtrack.backend.dto.SeriesEjercicioDTO.CrearSerieEjercicioDTO;
import com.gymtrack.backend.dto.SeriesEjercicioDTO.SerieEjercicioDTO;
import com.gymtrack.backend.model.SerieEjercicio;
import com.gymtrack.backend.service.SerieEjercicioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/entrenamientos")
public class SerieEjercicioController {

    private final SerieEjercicioService serieEjercicioService;

    @GetMapping("/{entrenamientoId}/ejercicios/{entrenamientoEjercicioId}/series")
    public ResponseEntity<List<SerieEjercicioDTO>> listar(@PathVariable Long entrenamientoId,
                                                         @PathVariable Long entrenamientoEjercicioId){

        return ResponseEntity.ok(serieEjercicioService.
                listarPorEntrenamientoEjercicio(entrenamientoId, entrenamientoEjercicioId));
    }

    @GetMapping("/{entrenamientoId}/ejercicios/{entrenamientoEjercicioId}/series/{serieEjercicioId}")
    public ResponseEntity<SerieEjercicioDTO> obtenerPorId(@PathVariable Long entrenamientoId,
                                                       @PathVariable Long entrenamientoEjercicioId,
                                                       @PathVariable Long serieEjercicioId){


        return ResponseEntity.ok(serieEjercicioService.buscarPorId(entrenamientoId,
                entrenamientoEjercicioId,
                serieEjercicioId));
    }

    @PostMapping("/{entrenamientoId}/ejercicios/{entrenamientoEjercicioId}/series")
    public ResponseEntity<SerieEjercicioDTO> crear(@PathVariable Long entrenamientoId,
                                                   @PathVariable Long entrenamientoEjercicioId,
                                                   @RequestBody @Valid CrearSerieEjercicioDTO dto){

        SerieEjercicioDTO serieEjercicio = serieEjercicioService.crear(entrenamientoId, entrenamientoEjercicioId, dto);

        return ResponseEntity.created(
                URI.create("/api/entrenamientos/" + entrenamientoId + "/ejercicios/" +
                        entrenamientoEjercicioId + "/series/" + serieEjercicio.getId())
        ).body(serieEjercicio);
    }

    @PatchMapping("/{entrenamientoId}/ejercicios/{entrenamientoEjercicioId}/series/{serieEjercicioId}")
    public ResponseEntity<SerieEjercicioDTO> actualizar(@PathVariable Long entrenamientoId,
                                                        @PathVariable Long entrenamientoEjercicioId,
                                                        @PathVariable Long serieEjercicioId,
                                                        @RequestBody @Valid ActualizarSerieEjercicioDTO dto){

        return ResponseEntity.ok(serieEjercicioService.actualizar(entrenamientoId,
                entrenamientoEjercicioId,
                serieEjercicioId,
                dto));

    }

    @PatchMapping("/{entrenamientoId}/ejercicios/{entrenamientoEjercicioId}/series/{serieEjercicioId}/numeroSerie")
    public ResponseEntity<SerieEjercicioDTO> actualizarNumeroSerie(@PathVariable Long entrenamientoId,
                                                                   @PathVariable Long entrenamientoEjercicioId,
                                                                   @PathVariable Long serieEjercicioId,
                                                                   @RequestBody @Valid ActualizarNumeroSerieDTO dto){

        return ResponseEntity.ok(serieEjercicioService.actualizarNumeroSerie(entrenamientoId,
                entrenamientoEjercicioId,
                serieEjercicioId,
                dto));
    }

    @PostMapping("/{entrenamientoId}/ejercicios/{entrenamientoEjercicioId}/series/{serieEjercicioId}")
    public ResponseEntity<Void> eliminar(@PathVariable Long entrenamientoId,
                                         @PathVariable Long entrenamientroEjercicioId,
                                         @PathVariable Long serieEjercicioId){


        serieEjercicioService.eliminar(entrenamientoId, entrenamientroEjercicioId, serieEjercicioId);

        return ResponseEntity.noContent().build();
    }

}
