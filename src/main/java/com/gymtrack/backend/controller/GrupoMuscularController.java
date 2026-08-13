package com.gymtrack.backend.controller;

import com.gymtrack.backend.dto.GrupoMuscularDTO.ActualizarGrupoMuscularDTO;
import com.gymtrack.backend.dto.GrupoMuscularDTO.CrearGrupoMuscularDTO;
import com.gymtrack.backend.dto.GrupoMuscularDTO.GrupoMuscularDTO;
import com.gymtrack.backend.service.GrupoMuscularService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/grupos_musculares")
public class GrupoMuscularController {

    private final GrupoMuscularService grupoMuscularService;

    @PreAuthorize("hasAuthority('GRUPO_MUSCULAR_VER')")
    @GetMapping
    public ResponseEntity<List<GrupoMuscularDTO>> listar(){

        return ResponseEntity.ok(grupoMuscularService.listar());
    }

    @PreAuthorize("hasAuthority('GRUPO_MUSCULAR_VER')")
    @GetMapping("/{id}")
    public ResponseEntity<GrupoMuscularDTO> buscarPorId(@PathVariable Long id){

        return ResponseEntity.ok(grupoMuscularService.buscarPorId(id));
    }

    @PreAuthorize("hasAuthority('GRUPO_MUSCULAR_GESTIONAR')")
    @PostMapping
    public ResponseEntity<GrupoMuscularDTO> crear(@RequestBody @Valid CrearGrupoMuscularDTO dto){

        GrupoMuscularDTO grupoMuscular = grupoMuscularService.crear(dto);

        return ResponseEntity.created(URI.create("/api/gruposMusculares/" + grupoMuscular.getId())).body(grupoMuscular);
    }

    @PreAuthorize("hasAuthority('GRUPO_MUSCULAR_GESTIONAR')")
    @PatchMapping("/{id}")
    public ResponseEntity<GrupoMuscularDTO> actualizar(@PathVariable Long id, @RequestBody @Valid ActualizarGrupoMuscularDTO dto){

        return ResponseEntity.ok(grupoMuscularService.actualizar(id, dto));
    }

    @PreAuthorize("hasAuthority('GRUPO_MUSCULAR_GESTIONAR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){

        grupoMuscularService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}
