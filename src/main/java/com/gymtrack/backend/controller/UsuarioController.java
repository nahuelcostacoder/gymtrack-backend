package com.gymtrack.backend.controller;

import com.gymtrack.backend.dto.UsuarioDTO.*;
import com.gymtrack.backend.service.UsuarioService;
import com.gymtrack.backend.service.UsuarioServiceImp;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listar(){

        return ResponseEntity.ok(usuarioService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable Long id){

        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> crear(@RequestBody @Valid CrearUsuarioDTO dto){

        UsuarioDTO usuario = usuarioService.crear(dto);

        return ResponseEntity.created(URI.create("/api/usuarios/" + usuario.getId())).body(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> actualizar(@PathVariable Long id, @RequestBody @Valid ActualizarUsuarioDTO dto){

        return ResponseEntity.ok(usuarioService.actualizar(id, dto));
    }

    @PatchMapping("/{id}/email")
    public ResponseEntity<Void> cambiarEmail(@PathVariable Long id, @RequestBody @Valid CambiarEmailDTO dto){

        usuarioService.cambiarEmail(id, dto);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<Void> cambiarPassword(@PathVariable Long id, @RequestBody @Valid CambiarPasswordDTO dto){

        usuarioService.cambiarPassword(id, dto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){

        usuarioService.eliminar(id);

        return ResponseEntity.noContent().build();
    }


}
