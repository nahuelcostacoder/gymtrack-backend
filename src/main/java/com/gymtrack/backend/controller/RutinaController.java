package com.gymtrack.backend.controller;

import com.gymtrack.backend.dto.RutinaDTO.ActualizarRutinaDTO;
import com.gymtrack.backend.dto.RutinaDTO.CrearRutinaDTO;
import com.gymtrack.backend.dto.RutinaDTO.RutinaDTO;
import com.gymtrack.backend.model.Rutina;
import com.gymtrack.backend.security.UsuarioDetails;
import com.gymtrack.backend.service.RutinaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/rutinas")
public class RutinaController {

    private final RutinaService rutinaService;

    @GetMapping
    public ResponseEntity<List<RutinaDTO>> listar(){

        return ResponseEntity.ok(rutinaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RutinaDTO> buscarPorId(@PathVariable Long id){

        return ResponseEntity.ok(rutinaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<RutinaDTO> crear(@AuthenticationPrincipal UsuarioDetails usuarioDetails, @RequestBody @Valid CrearRutinaDTO dto){

        RutinaDTO rutina = rutinaService.crear(usuarioDetails.getId(), dto);

        return ResponseEntity.created(URI.create("/api/rutinas/" + rutina.getId())).body(rutina);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RutinaDTO> actualizar(@AuthenticationPrincipal UsuarioDetails usuarioDetails,
                                                @PathVariable Long id,
                                                @RequestBody @Valid ActualizarRutinaDTO dto){

        return ResponseEntity.ok(rutinaService.actualizar(usuarioDetails.getId(), id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@AuthenticationPrincipal UsuarioDetails usuarioDetails,
                                         @PathVariable Long id){

        rutinaService.eliminar(usuarioDetails.getId(), id);

        return ResponseEntity.noContent().build();
    }
}
