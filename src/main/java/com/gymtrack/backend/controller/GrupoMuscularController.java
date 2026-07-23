package com.gymtrack.backend.controller;

import com.gymtrack.backend.dto.GrupoMuscularDTO.ActualizarGrupoMuscularDTO;
import com.gymtrack.backend.dto.GrupoMuscularDTO.CrearGrupoMuscularDTO;
import com.gymtrack.backend.dto.GrupoMuscularDTO.GrupoMuscularDTO;
import com.gymtrack.backend.service.GrupoMuscularService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/grupos_musculares")
public class GrupoMuscularController {

    private final GrupoMuscularService grupoMuscularService;

    @GetMapping
    public ResponseEntity<List<GrupoMuscularDTO>> listar(){

        return ResponseEntity.ok(grupoMuscularService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GrupoMuscularDTO> buscarPorId(@PathVariable Long id){

        return ResponseEntity.ok(grupoMuscularService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<GrupoMuscularDTO> crear(@RequestBody CrearGrupoMuscularDTO dto){

        GrupoMuscularDTO grupoMuscular = grupoMuscularService.crear(dto);

        return ResponseEntity.created(URI.create("/api/gruposMusculares/" + grupoMuscular.getId())).body(grupoMuscular);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GrupoMuscularDTO> actualizar(@PathVariable Long id, @RequestBody ActualizarGrupoMuscularDTO dto){

        return ResponseEntity.ok(grupoMuscularService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){

        grupoMuscularService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}
