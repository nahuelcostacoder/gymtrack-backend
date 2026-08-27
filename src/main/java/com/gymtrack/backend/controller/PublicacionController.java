package com.gymtrack.backend.controller;

import com.gymtrack.backend.dto.PublicacionDTO.ActualizarPublicacionDTO;
import com.gymtrack.backend.dto.PublicacionDTO.CrearPublicacionDTO;
import com.gymtrack.backend.dto.PublicacionDTO.PublicacionDTO;
import com.gymtrack.backend.model.Publicacion;
import com.gymtrack.backend.security.UsuarioDetails;
import com.gymtrack.backend.service.ImagenService;
import com.gymtrack.backend.service.PublicacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.data.autoconfigure.web.DataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/publicaciones")
public class PublicacionController {

    private final PublicacionService publicacionService;
    private final ImagenService imagenService;


    //una pagina tiene 10 publicaciones
    @GetMapping("/usuarios/{usuarioId}")
    public ResponseEntity<Page<PublicacionDTO>> listarPorUsuario(@PathVariable Long usuarioId,
                                                                 @AuthenticationPrincipal UsuarioDetails usuarioDetails,
                                                                 @PageableDefault(
                                                                         size = 10,
                                                                         sort = "fechaCreacion",
                                                                         direction = Sort.Direction.DESC
                                                                 ) //aca lo que hago es dar un defecto de cuantas cargar, sino puede cambiarlo el front por el endpoint

                                                                 Pageable pageable){

        return ResponseEntity.ok(publicacionService.listarPorUsuario(usuarioId, usuarioDetails.getId(), pageable));

    }

    @GetMapping
    public ResponseEntity<Page<PublicacionDTO>> listarFeed(@AuthenticationPrincipal UsuarioDetails usuarioDetails,
                                                           @PageableDefault(
                                                                   size = 10,
                                                                   sort = "fechaCreacion",
                                                                   direction = Sort.Direction.DESC
                                                           )Pageable pageable){


        return ResponseEntity.ok(publicacionService.listarFeed(usuarioDetails.getId(), pageable));

    }

    @GetMapping("/{publicacionId}")
    public ResponseEntity<PublicacionDTO> buscarPorId(@AuthenticationPrincipal UsuarioDetails usuarioDetails,
                                                      @PathVariable Long publicacionId){

        return ResponseEntity.ok(publicacionService.buscarPorId(usuarioDetails.getId(), publicacionId));
    }

    @PostMapping()
    public ResponseEntity<PublicacionDTO> crear(@AuthenticationPrincipal UsuarioDetails usuarioDetails,
                                                @RequestPart("publicacion") @Valid CrearPublicacionDTO dto,
                                                @RequestParam(value = "file", required = false) List<MultipartFile> archivos){

        PublicacionDTO publicacion = publicacionService.crear(usuarioDetails.getId(), dto, archivos);

        return ResponseEntity.created(URI.create("/api/publicaciones/" + publicacion.getId())).body(publicacion);
    }

    @PostMapping("/{publicacionId}/media")
    public ResponseEntity<PublicacionDTO> agregarMedia(@AuthenticationPrincipal UsuarioDetails usuarioDetails,
                                                       @PathVariable Long publicacionId,
                                                       @RequestParam(value = "file") List<MultipartFile> archivos){

        return ResponseEntity.ok(publicacionService.agregarMedia(usuarioDetails.getId(), publicacionId, archivos));
    }

    @PatchMapping("/{publicacionId}")
    public ResponseEntity<PublicacionDTO> actualizar(@AuthenticationPrincipal UsuarioDetails usuarioDetails,
                                                      @PathVariable Long publicacionId,
                                                      @RequestBody @Valid ActualizarPublicacionDTO dto){

        return ResponseEntity.ok(publicacionService.actualizar(usuarioDetails.getId(), publicacionId, dto));
    }


    @DeleteMapping("/{publicacionId}/media/{mediaId}")
    public ResponseEntity<PublicacionDTO> eliminarMedia(@AuthenticationPrincipal UsuarioDetails usuarioDetails,
                                                        @PathVariable Long publicacionId,
                                                        @PathVariable Long mediaId){

        return ResponseEntity.ok(publicacionService.eliminarMedia(usuarioDetails.getId(), publicacionId, mediaId));
    }

    @DeleteMapping("/{publicacionId}")
    public ResponseEntity<Void> eliminar(@AuthenticationPrincipal UsuarioDetails usuarioDetails,
                                         @PathVariable Long publicacionId){

        publicacionService.eliminar(usuarioDetails.getId(), publicacionId);

        return ResponseEntity.noContent().build();
    }

}
