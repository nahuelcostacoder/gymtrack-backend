package com.gymtrack.backend.controller;

import com.gymtrack.backend.dto.UsuarioDTO.*;
import com.gymtrack.backend.security.UsuarioDetails;
import com.gymtrack.backend.service.UsuarioService;
import com.gymtrack.backend.service.UsuarioServiceImp;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PreAuthorize("hasAuthority('USUARIO_VER')")
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listar(){

        return ResponseEntity.ok(usuarioService.listar());
    }

    @PreAuthorize("hasAuthority('USUARIO_VER')")
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable Long id){

        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @GetMapping("/me")
    public ResponseEntity<UsuarioDTO> obtenerMiUsuario(@AuthenticationPrincipal UsuarioDetails usuarioDetails){

        return ResponseEntity.ok(usuarioService.buscarPorId(usuarioDetails.getId()));
    }

    @PreAuthorize("hasAuthority('USUARIO_GESTIONAR')")
    @PostMapping
    public ResponseEntity<UsuarioDTO> crear(@RequestBody @Valid CrearUsuarioDTO dto){

        UsuarioDTO usuario = usuarioService.crear(dto);

        return ResponseEntity.created(URI.create("/api/usuarios/" + usuario.getId())).body(usuario);
    }

    @PreAuthorize("hasAuthority('USUARIO_GESTIONAR')")
    @PatchMapping("/{id}")
    public ResponseEntity<UsuarioDTO> actualizar(@PathVariable Long id,
                                                 @RequestBody @Valid ActualizarUsuarioAdminDTO dto){

        return ResponseEntity.ok(usuarioService.actualizar(id, dto));
    }


    @PatchMapping("/me")
    public ResponseEntity<UsuarioDTO> actualizarMiUsuario(@AuthenticationPrincipal UsuarioDetails usuarioDetails, @RequestBody @Valid ActualizarUsuarioDTO dto){

        return ResponseEntity.ok(usuarioService.actualizarMiUsuario(usuarioDetails.getId(), dto));
    }

    @PatchMapping("/{id}/email")
    public ResponseEntity<Void> cambiarEmail(@AuthenticationPrincipal UsuarioDetails usuarioDetails,
                                             @RequestBody @Valid CambiarEmailDTO dto){

        usuarioService.cambiarEmail(usuarioDetails.getId(), dto);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<Void> cambiarPassword(@AuthenticationPrincipal UsuarioDetails usuarioDetails,
                                                @RequestBody @Valid CambiarPasswordDTO dto){

        usuarioService.cambiarPassword(usuarioDetails.getId(), dto);

        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('USUARIO_GESTIONAR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){

        usuarioService.eliminar(id);

        return ResponseEntity.noContent().build();
    }


}
