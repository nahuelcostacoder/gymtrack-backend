package com.gymtrack.backend.controller;

import com.gymtrack.backend.dto.EntrenamientoDTO.ActualizarEntrenamientoDTO;
import com.gymtrack.backend.dto.EntrenamientoDTO.CrearEntrenamientoDTO;
import com.gymtrack.backend.dto.EntrenamientoDTO.EntrenamientoDTO;
import com.gymtrack.backend.security.UsuarioDetails;
import com.gymtrack.backend.service.EntrenamientoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/entrenamientos")
public class EntrenamientoController {

    private final EntrenamientoService entrenamientoService;

    @GetMapping
    public ResponseEntity<List<EntrenamientoDTO>> listarPorUsuario(@AuthenticationPrincipal UsuarioDetails usuarioDetails){

        return ResponseEntity.ok(entrenamientoService.listarPorUsuarioId(usuarioDetails.getId()));
    }

    @GetMapping("/{entrenamientoId}")
    public ResponseEntity<EntrenamientoDTO> buscarPorId(@PathVariable Long entrenamientoId, @AuthenticationPrincipal UsuarioDetails usuarioDetails){

        return ResponseEntity.ok(entrenamientoService.buscarPorUsuarioId(entrenamientoId, usuarioDetails.getId()));

    }

    @PostMapping
    public ResponseEntity<EntrenamientoDTO> crear(@AuthenticationPrincipal UsuarioDetails usuarioDetails, @RequestBody @Valid CrearEntrenamientoDTO dto){

        EntrenamientoDTO entrenamiento = entrenamientoService.crear(usuarioDetails.getId(), dto);

        return ResponseEntity.created(
                URI.create("/api/entrenamientos/" + entrenamiento.getId())).body(entrenamiento);

    }

    @PatchMapping("/{entrenamientoId}")
    public ResponseEntity<EntrenamientoDTO> actualizar(@PathVariable Long entrenamientoId,
                                                       @AuthenticationPrincipal UsuarioDetails usuarioDetails,
                                                       @RequestBody @Valid ActualizarEntrenamientoDTO dto){

        return ResponseEntity.ok(entrenamientoService.actualizar(entrenamientoId, usuarioDetails.getId(), dto));
    }

    @PatchMapping("/{entrenamientoId}/finalizar")
    public ResponseEntity<EntrenamientoDTO> finalizar(@PathVariable Long entrenamientoId,
                                                      @AuthenticationPrincipal UsuarioDetails usuarioDetails){

        return ResponseEntity.ok(entrenamientoService.finalizar(entrenamientoId, usuarioDetails.getId()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long entrenamientoId,
                                         @AuthenticationPrincipal UsuarioDetails usuarioDetails){

        entrenamientoService.eliminar(entrenamientoId, usuarioDetails.getId());

        return ResponseEntity.noContent().build();
    }

}
