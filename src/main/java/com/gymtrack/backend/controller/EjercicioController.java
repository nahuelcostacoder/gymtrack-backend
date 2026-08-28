package com.gymtrack.backend.controller;

import com.gymtrack.backend.dto.EjercicioDTO.ActualizarEjercicioDTO;
import com.gymtrack.backend.dto.EjercicioDTO.CrearEjercicioDTO;
import com.gymtrack.backend.dto.EjercicioDTO.EjercicioDTO;
import com.gymtrack.backend.model.Ejercicio;
import com.gymtrack.backend.service.EjercicioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/ejercicios")
public class EjercicioController {

    private final EjercicioService ejercicioService;

    @PreAuthorize("hasAuthority('EJERCICIO_VER')")
    @GetMapping
    public ResponseEntity<List<EjercicioDTO>> listar(){

        return ResponseEntity.ok(ejercicioService.listar());
    }

    @PreAuthorize("hasAuthority('EJERCICIO_VER')")
    @GetMapping("/{id}")
    public ResponseEntity<EjercicioDTO> buscarPorId(@PathVariable Long id){

        return ResponseEntity.ok(ejercicioService.buscarPorId(id));
    }

    @PreAuthorize("hasAuthority('EJERCICIO_VER')")
    @GetMapping("/grupo-muscular/{nombre}")
    public ResponseEntity<List<EjercicioDTO>> buscarPorGrupoMuscular(@PathVariable String nombre){

        return ResponseEntity.ok(ejercicioService.buscarPorGrupoMuscular(nombre));
    }

    @PreAuthorize("hasAuthority('EJERCICIO_GESTIONAR')")
    @PostMapping
    public ResponseEntity<EjercicioDTO> crear(@RequestPart("dto") @Valid CrearEjercicioDTO dto,
                                              @RequestParam(value = "file", required = false) MultipartFile file){

        EjercicioDTO ejercicio = ejercicioService.crear(dto, file);

        return ResponseEntity.created(URI.create("/api/ejercicios/" + ejercicio.getId())).body(ejercicio);
    }

    @PreAuthorize("hasAuthority('EJERCICIO_GESTIONAR')")
    @PatchMapping("/{id}")
    public ResponseEntity<EjercicioDTO> actualizar(@PathVariable Long id, @RequestBody @Valid ActualizarEjercicioDTO dto){

        return ResponseEntity.ok(ejercicioService.actualizar(id, dto));
    }

    @PatchMapping("/{id}/video")
    public ResponseEntity<EjercicioDTO> actualizarVideo(@PathVariable Long id,
                                                        @RequestParam("file") MultipartFile file){


        return ResponseEntity.ok(ejercicioService.actualizarVideo(id, file));
    }

    @DeleteMapping("/{id}/video")
    public ResponseEntity<EjercicioDTO> eliminarVideo(@PathVariable Long id){

        return ResponseEntity.ok(ejercicioService.eliminarVideo(id));
    }

    @PreAuthorize("hasAuthority('EJERCICIO_GESTIONAR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){

        ejercicioService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}
