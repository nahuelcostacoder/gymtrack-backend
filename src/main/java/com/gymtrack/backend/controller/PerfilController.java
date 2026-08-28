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
import org.springframework.web.multipart.MultipartFile;

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
                                           @RequestPart("dto") @Valid CrearPerfilDTO dto,
                                           @RequestParam(value = "file", required = false) MultipartFile archivo){

        PerfilDTO perfil = perfilService.crear(usuarioDetails.getId(), dto, archivo);

        return ResponseEntity.created(URI.create("/api/perfiles/" + perfil.getId())).body(perfil);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PerfilDTO> actualizar(@AuthenticationPrincipal UsuarioDetails usuarioDetails, @PathVariable Long id,
                                                @RequestBody @Valid ActualizarPerfilDTO dto){

        return ResponseEntity.ok(perfilService.actualizar(usuarioDetails.getId(), id, dto));
    }

    @PatchMapping("/me/foto")
    public ResponseEntity<PerfilDTO> actualizarFotoPerfil(
            @AuthenticationPrincipal UsuarioDetails usuarioDetails,
            @RequestParam("file") MultipartFile file) { //el archivo que le paso del front

        return ResponseEntity.ok(
                perfilService.actualizarFotoPerfil(usuarioDetails.getId(), file)
        );
    }


    @DeleteMapping("/me/foto")
    public ResponseEntity<PerfilDTO> eliminarFotoPerfil(@AuthenticationPrincipal UsuarioDetails usuarioDetails){


        return ResponseEntity.ok(perfilService.eliminarFotoPerfil(usuarioDetails.getId()));
    }
    

}
