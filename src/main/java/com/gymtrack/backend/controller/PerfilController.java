package com.gymtrack.backend.controller;

import com.gymtrack.backend.dto.PerfilDTO.ActualizarPerfilDTO;
import com.gymtrack.backend.dto.PerfilDTO.CrearPerfilDTO;
import com.gymtrack.backend.dto.PerfilDTO.PerfilDTO;
import com.gymtrack.backend.model.Perfil;
import com.gymtrack.backend.service.PerfilService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/perfiles")
public class PerfilController {

    private final PerfilService perfilService;

    @GetMapping
    public ResponseEntity<List<PerfilDTO>> listar(){

        return ResponseEntity.ok(perfilService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerfilDTO> buscarPorId(@PathVariable Long id){

        return ResponseEntity.ok(perfilService.buscarPorId(id));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<PerfilDTO> buscarPorUsuarioId(@PathVariable Long id){

        return ResponseEntity.ok(perfilService.buscarPorIdUsuario(id));
    }

    @PostMapping
    public ResponseEntity<PerfilDTO> crear(@RequestBody CrearPerfilDTO dto){

        PerfilDTO perfil = perfilService.crear(dto);

        return ResponseEntity.created(URI.create("/api/perfiles/" + perfil.getId())).body(perfil);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PerfilDTO> actualizar(@PathVariable Long id, @RequestBody ActualizarPerfilDTO dto){

        return ResponseEntity.ok(perfilService.actualizar(id, dto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){

        perfilService.eliminar(id);

        return ResponseEntity.noContent().build();
    }


}
