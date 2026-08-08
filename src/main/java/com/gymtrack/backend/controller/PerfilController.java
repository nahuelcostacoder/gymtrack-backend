package com.gymtrack.backend.controller;

import com.gymtrack.backend.dto.PerfilDTO.ActualizarPerfilDTO;
import com.gymtrack.backend.dto.PerfilDTO.CrearPerfilDTO;
import com.gymtrack.backend.dto.PerfilDTO.PerfilDTO;
import com.gymtrack.backend.model.Perfil;
import com.gymtrack.backend.model.Usuario;
import com.gymtrack.backend.security.UsuarioDetails;
import com.gymtrack.backend.service.PerfilService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @GetMapping("/miPerfil")
    public ResponseEntity<PerfilDTO> buscarPorUsuarioId(@AuthenticationPrincipal UsuarioDetails usuarioDetails){

        return ResponseEntity.ok(perfilService.buscarPorIdUsuario(usuarioDetails.getId()));
    }

    @PostMapping
    public ResponseEntity<PerfilDTO> crear(@AuthenticationPrincipal UsuarioDetails usuarioDetails,
                                           @RequestBody @Valid CrearPerfilDTO dto){

        PerfilDTO perfil = perfilService.crear(usuarioDetails.getId(), dto);

        return ResponseEntity.created(URI.create("/api/perfiles/" + perfil.getId())).body(perfil);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PerfilDTO> actualizar(@AuthenticationPrincipal UsuarioDetails usuarioDetails, @PathVariable Long id,
                                                @RequestBody @Valid ActualizarPerfilDTO dto){

        return ResponseEntity.ok(perfilService.actualizar(usuarioDetails.getId(), id, dto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@AuthenticationPrincipal UsuarioDetails usuarioDetails,
                                         @PathVariable Long id){

        perfilService.eliminar(usuarioDetails.getId(), id);

        return ResponseEntity.noContent().build();
    }


}
