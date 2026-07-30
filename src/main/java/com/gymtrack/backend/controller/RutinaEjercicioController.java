package com.gymtrack.backend.controller;

import com.gymtrack.backend.dto.RutinaDTO.RutinaDTO;
import com.gymtrack.backend.dto.RutinaEjercicioDTO.ActualizarRutinaEjercicioDTO;
import com.gymtrack.backend.dto.RutinaEjercicioDTO.CrearRutinaEjercicioDTO;
import com.gymtrack.backend.dto.RutinaEjercicioDTO.RutinaEjercicioDTO;
import com.gymtrack.backend.model.RutinaEjercicio;
import com.gymtrack.backend.service.RutinaEjercicioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("api/rutinas")
public class RutinaEjercicioController {

    private final RutinaEjercicioService rutinaEjercicioService;

    @GetMapping("/{rutinaId}/ejercicios")
    public ResponseEntity<List<RutinaEjercicioDTO>> listar(@PathVariable Long rutinaId){

        return ResponseEntity.ok(rutinaEjercicioService.listarPorRutina(rutinaId));
    }

    @GetMapping("/{rutinaId}/ejercicios/{rutinaEjercicioId}")
    public ResponseEntity<RutinaEjercicioDTO> buscarPorId(@PathVariable Long rutinaId, @PathVariable Long rutinaEjercicioId){

        return ResponseEntity.ok(rutinaEjercicioService.buscarPorId(rutinaId, rutinaEjercicioId));
    }

    @PostMapping("/{rutinaId}/ejercicios")
    public ResponseEntity<RutinaEjercicioDTO> crear(@PathVariable Long rutinaId, @RequestBody @Valid CrearRutinaEjercicioDTO dto){

        RutinaEjercicioDTO rutinaEjercicio = rutinaEjercicioService.crear(rutinaId, dto);

        return ResponseEntity.created(
                URI.create("/api/rutinas/" + rutinaId + "/ejercicios/" + rutinaEjercicio.getId())
        ).body(rutinaEjercicio);

    }

    @PatchMapping("/{rutinaId}/ejercicios/{rutinaEjercicioId}")
    public ResponseEntity<RutinaEjercicioDTO> actualizar(@PathVariable Long rutinaId
            , @PathVariable Long rutinaEjercicioId
            , @RequestBody @Valid ActualizarRutinaEjercicioDTO dto){

        return ResponseEntity.ok(rutinaEjercicioService.actualizar(rutinaId, rutinaEjercicioId, dto));
    }

    @DeleteMapping("/{rutinaId}/ejercicios/{rutinaEjercicioId}")
    public ResponseEntity<Void> eliminar(@PathVariable Long rutinaId, @PathVariable Long rutinaEjercicioId){

        rutinaEjercicioService.eliminar(rutinaId, rutinaEjercicioId);

        return ResponseEntity.noContent().build();
    }
}
