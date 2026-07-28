package com.gymtrack.backend.controller;


import com.gymtrack.backend.dto.EntrenamientoEjercicioDTO.ActualizarEntrenamientoEjercicioDTO;
import com.gymtrack.backend.dto.EntrenamientoEjercicioDTO.CrearEntrenamientoEjercicioDTO;
import com.gymtrack.backend.dto.EntrenamientoEjercicioDTO.EntrenamientoEjercicioDTO;
import com.gymtrack.backend.model.EntrenamientoEjercicio;
import com.gymtrack.backend.service.EntrenamientoEjercicioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/entrenamientos")
public class EntrenamientoEjercicioController {

    private final EntrenamientoEjercicioService entrenamientoEjercicioService;

    @GetMapping("/{entrenamientoId}/ejercicios")
    public ResponseEntity<List<EntrenamientoEjercicioDTO>> listar(@PathVariable Long entrenamientoId){

        return ResponseEntity.ok(entrenamientoEjercicioService.listarPorEntrenamientoId(entrenamientoId));
    }

    @GetMapping("/{entrenamientoId}/ejercicios/{entrenamientoEjercicioId}")
    public ResponseEntity<EntrenamientoEjercicioDTO> buscarPorId(@PathVariable Long entrenamientoId, @PathVariable Long entrenamientoEjercicioId){

        return ResponseEntity.ok(entrenamientoEjercicioService.buscarPorId(entrenamientoId, entrenamientoEjercicioId));
    }


    @PostMapping("/{entrenamientoId}/ejercicios")
    public ResponseEntity<EntrenamientoEjercicioDTO> crear(@PathVariable Long entrenamientoId, @RequestBody @Valid CrearEntrenamientoEjercicioDTO dto){

        EntrenamientoEjercicioDTO entrenamientoEjercicio = entrenamientoEjercicioService.crear(entrenamientoId, dto);

        return ResponseEntity.created(
                URI.create("/api/entrenamientos/" + entrenamientoId + "/ejercicios/" + entrenamientoEjercicio.getId())
        ).body(entrenamientoEjercicio);
    }

    @PatchMapping("/{entrenamientoId}/ejercicios/{entrenamientoEjercicioId}")
    public ResponseEntity<EntrenamientoEjercicioDTO> actualizar(@PathVariable Long entrenamientoId, @PathVariable Long entrenamientoEjercicioId, @RequestBody @Valid ActualizarEntrenamientoEjercicioDTO dto){

        return ResponseEntity.ok(entrenamientoEjercicioService.actualizar(entrenamientoId, entrenamientoEjercicioId, dto));
    }

    @DeleteMapping("/{entrenamientoId}/ejercicios/{entrenamientoEjercicioId}")
    public ResponseEntity<Void> eliminar(@PathVariable Long entrenamientoId, @PathVariable Long entrenamientoEjercicioId){

        entrenamientoEjercicioService.eliminar(entrenamientoId, entrenamientoEjercicioId);

        return ResponseEntity.noContent().build();
    }

}
