package com.gymtrack.backend.controller;

import com.gymtrack.backend.dto.RutinaDTO.ActualizarRutinaDTO;
import com.gymtrack.backend.dto.RutinaDTO.CrearRutinaDTO;
import com.gymtrack.backend.dto.RutinaDTO.RutinaDTO;
import com.gymtrack.backend.model.Rutina;
import com.gymtrack.backend.service.RutinaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<RutinaDTO> crear(@RequestBody @Valid CrearRutinaDTO dto){

        RutinaDTO rutina = rutinaService.crear(dto);

        return ResponseEntity.created(URI.create("/api/rutinas/" + rutina.getId())).body(rutina);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RutinaDTO> actualizar(@PathVariable Long id, @RequestBody @Valid ActualizarRutinaDTO dto){

        return ResponseEntity.ok(rutinaService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){

        rutinaService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}
