package com.gymtrack.backend.controller;

import com.gymtrack.backend.dto.ComentarioDTO.ComentarioDTO;
import com.gymtrack.backend.dto.ComentarioDTO.CrearComentarioDTO;
import com.gymtrack.backend.dto.ComentarioDTO.EditarComentarioDTO;
import com.gymtrack.backend.security.UsuarioDetails;
import com.gymtrack.backend.service.ComentarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;

import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/comentarios")
public class ComentarioController {

    private final ComentarioService comentarioService;

    @GetMapping("/historial")
    public ResponseEntity<Page<ComentarioDTO>> listarPorUsuario(@AuthenticationPrincipal UsuarioDetails usuarioDetails,
                                                                @PageableDefault(
                                                                        size = 10,
                                                                        sort = "fechaCreacion",
                                                                        direction = Sort.Direction.DESC
                                                                )Pageable pageable) {

        return ResponseEntity.ok(comentarioService.listarPorUsuario(usuarioDetails.getId(), pageable));
    }

    @GetMapping("/publicaciones/{publicacionId}")
    public ResponseEntity<Page<ComentarioDTO>> listarPorPublicacion(@PathVariable Long publicacionId,
                                                                    @PageableDefault(
                                                                            size = 10,
                                                                            sort = "fechaCreacion",
                                                                            direction = Sort.Direction.DESC
                                                                    )Pageable pageable){

        return ResponseEntity.ok(comentarioService.listarPorPublicacion(publicacionId, pageable));
    }

    @PostMapping("/publicaciones/{publicacionId}")
    public ResponseEntity<ComentarioDTO> crear(@AuthenticationPrincipal UsuarioDetails usuarioDetails,
                                               @PathVariable Long publicacionId,
                                               @RequestBody @Valid CrearComentarioDTO dto){

        ComentarioDTO comentario = comentarioService.crear(usuarioDetails.getId(), publicacionId, dto);


        return ResponseEntity.created(URI.create("/api/comentarios/" + comentario.getId())).body(comentario);
    }

    @PatchMapping("/{comentarioId}")
    public ResponseEntity<ComentarioDTO> editar(@PathVariable Long comentarioId,
                                                @AuthenticationPrincipal UsuarioDetails usuarioDetails,
                                                @RequestBody @Valid EditarComentarioDTO dto){

        return ResponseEntity.ok(comentarioService.editar(comentarioId, usuarioDetails.getId(), dto));
    }

    @DeleteMapping("/{comentarioId}")
    public ResponseEntity<Void> eliminar(@PathVariable Long comentarioId,
                                         @AuthenticationPrincipal UsuarioDetails usuarioDetails){

        comentarioService.eliminar(comentarioId, usuarioDetails.getId());

        return ResponseEntity.noContent().build();
    }
}
