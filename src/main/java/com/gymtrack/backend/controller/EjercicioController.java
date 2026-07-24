package com.gymtrack.backend.controller;

import com.gymtrack.backend.dto.EjercicioDTO.ActualizarEjercicioDTO;
import com.gymtrack.backend.dto.EjercicioDTO.CrearEjercicioDTO;
import com.gymtrack.backend.dto.EjercicioDTO.EjercicioDTO;
import com.gymtrack.backend.model.Ejercicio;
import com.gymtrack.backend.service.EjercicioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/ejercicios")
public class EjercicioController {

    private final EjercicioService ejercicioService;

    @GetMapping
    public ResponseEntity<List<EjercicioDTO>> listar(){

        return ResponseEntity.ok(ejercicioService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EjercicioDTO> buscarPorId(@PathVariable Long id){

        return ResponseEntity.ok(ejercicioService.buscarPorId(id));
    }

    @GetMapping("/grupo-muscular/{nombre}")
    public ResponseEntity<List<EjercicioDTO>> buscarPorGrupoMuscular(@PathVariable String nombre){

        return ResponseEntity.ok(ejercicioService.buscarPorGrupoMuscular(nombre));
    }

    @PostMapping
    public ResponseEntity<EjercicioDTO> crear(@RequestBody CrearEjercicioDTO dto){

        EjercicioDTO ejercicio = ejercicioService.crear(dto);

        return ResponseEntity.created(URI.create("/api/ejercicios/" + ejercicio.getId())).body(ejercicio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EjercicioDTO> actualizar(@PathVariable Long id, @RequestBody ActualizarEjercicioDTO dto){

        return ResponseEntity.ok(ejercicioService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){

        ejercicioService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}
