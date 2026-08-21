package com.gymtrack.backend.controller;

import com.gymtrack.backend.dto.MeGustaDTO.MeGustaDTO;
import com.gymtrack.backend.model.MeGusta;
import com.gymtrack.backend.service.MeGustaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/meGusta")
public class MeGustaController {

    private MeGustaService meGustaService;

    @GetMapping
    public ResponseEntity<List<MeGustaDTO>> listar(@AuthenticationPrincipal Long usuarioId){

        return ResponseEntity.ok(meGustaService.listarPorUsuario(usuarioId));
    }

    public ResponseEntity<Page<MeGustaDTO>> listarPorPublicacion(@PathVariable Long publicacionId,
                                                                 @PageableDefault(
                                                                         size = 10,
                                                                         sort = "fechaCreacion",
                                                                         direction = Sort.Direction.DESC
                                                                 ) //aca lo que hago es dar un defecto de cuantas cargar, sino puede cambiarlo el front por el endpoint

                                                                 Pageable pageable){
        return ResponseEntity.ok(meGustaService.listarPorPublicacion(publicacionId, pageable));

    }

    @PostMapping("/publicaciones/{publicacionId}")
    public ResponseEntity<MeGustaDTO> darMeGusta(@AuthenticationPrincipal Long usuarioId,
                                                 @PathVariable Long publicacionId){

        MeGustaDTO meGusta = meGustaService.darMeGusta(usuarioId, publicacionId);

        return ResponseEntity.created(URI.create("/api/meGusta/" + meGusta.getId())).body(meGusta);

    }

    @DeleteMapping("/publicaciones/{publicacionId}")
    public ResponseEntity<Void> eliminarMeGusta(@AuthenticationPrincipal Long usuarioId,
                                                @PathVariable Long publicacionId){

        meGustaService.eliminarMeGusta(usuarioId, publicacionId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/publicaciones/{publicacionId}/me-gusta")
    public ResponseEntity<Boolean> dioMeGusta(@AuthenticationPrincipal Long usuarioId,
                                              @PathVariable Long publicacionId){

        return ResponseEntity.ok(meGustaService.dioMeGusta(usuarioId, publicacionId));
    }

    @GetMapping("/publicaciones/{publicacionId}/total-me-gusta")
    public ResponseEntity<Long> contarPorPublicacion(@PathVariable Long publicacionId){

        return ResponseEntity.ok(meGustaService.contarPorPublicacion(publicacionId));
    }

}
