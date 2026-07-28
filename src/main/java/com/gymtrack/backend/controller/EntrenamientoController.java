package com.gymtrack.backend.controller;

import com.gymtrack.backend.dto.EntrenamientoDTO.ActualizarEntrenamientoDTO;
import com.gymtrack.backend.dto.EntrenamientoDTO.CrearEntrenamientoDTO;
import com.gymtrack.backend.dto.EntrenamientoDTO.EntrenamientoDTO;
import com.gymtrack.backend.service.EntrenamientoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/usuarios/{usuarioId}/entrenamientos")
public class EntrenamientoController {

    private final EntrenamientoService entrenamientoService;

    @GetMapping
    public ResponseEntity<List<EntrenamientoDTO>> listarPorUsuario(@PathVariable Long usuarioId){

        return ResponseEntity.ok(entrenamientoService.listarPorUsuarioId(usuarioId));
    }

    @GetMapping("/{entrenamientoId}")
    public ResponseEntity<EntrenamientoDTO> buscarPorId(@PathVariable Long entrenamientoId, @PathVariable Long usuarioId){

        return ResponseEntity.ok(entrenamientoService.buscarPorUsuarioId(entrenamientoId, usuarioId));

    }

    @PostMapping
    public ResponseEntity<EntrenamientoDTO> crear(@PathVariable Long usuarioId, @RequestBody @Valid CrearEntrenamientoDTO dto){

        EntrenamientoDTO entrenamiento = entrenamientoService.crear(usuarioId, dto);

        return ResponseEntity.created(
                URI.create("/api/usuarios/" + usuarioId + "/entrenamientos/" + entrenamiento.getId())
        ).body(entrenamiento);

    }

    @PatchMapping("/{entrenamientoId}")
    public ResponseEntity<EntrenamientoDTO> actualizar(@PathVariable Long entrenamientoId,
                                                       @PathVariable Long usuarioId,
                                                       @RequestBody @Valid ActualizarEntrenamientoDTO dto){

        return ResponseEntity.ok(entrenamientoService.actualizar(entrenamientoId, usuarioId, dto));
    }

    @PatchMapping("/{entrenamientoId}/finalizar")
    public ResponseEntity<EntrenamientoDTO> finalizar(@PathVariable Long entrenamientoId, @PathVariable Long usuarioId){

        return ResponseEntity.ok(entrenamientoService.finalizar(entrenamientoId, usuarioId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long entrenamientoId, @PathVariable Long usuarioId){

        entrenamientoService.eliminar(entrenamientoId, usuarioId);

        return ResponseEntity.noContent().build();
    }

}
