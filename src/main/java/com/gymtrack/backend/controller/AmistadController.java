package com.gymtrack.backend.controller;

import com.gymtrack.backend.dto.AmistadDTO.AmistadDTO;
import com.gymtrack.backend.dto.AmistadDTO.EnviarSolicitudDTO;
import com.gymtrack.backend.model.Amistad;
import com.gymtrack.backend.security.UsuarioDetails;
import com.gymtrack.backend.service.AmistadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/amistades")
public class AmistadController {


    private final AmistadService amistadService;

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<AmistadDTO>> listarAmigos(@PathVariable Long usuarioId){

        return ResponseEntity.ok(amistadService.listarAmigos(usuarioId));
    }

    @GetMapping("/misAmigos")
    public ResponseEntity<List<AmistadDTO>> listarMisAmigos(@AuthenticationPrincipal UsuarioDetails usuarioDetails){

        return ResponseEntity.ok(amistadService.listarAmigos(usuarioDetails.getId()));
    }

    @GetMapping("/solicitudes/recibidas")
    public ResponseEntity<List<AmistadDTO>> listarSolicitudesRecibidas(@AuthenticationPrincipal UsuarioDetails usuarioDetails){

        return ResponseEntity.ok(amistadService.listarSolicitudesRecibidas(usuarioDetails.getId()));
    }

    @GetMapping("/solicitudes/enviadas")
    public ResponseEntity<List<AmistadDTO>> listarSolicitudesEnviadas(@AuthenticationPrincipal UsuarioDetails usuarioDetails){

        return ResponseEntity.ok(amistadService.listarSolicitudesEnviadas(usuarioDetails.getId()));
    }

    @PostMapping("/solicitudes")
    public ResponseEntity<AmistadDTO> enviarSolicitud(@AuthenticationPrincipal UsuarioDetails usuarioDetails, @RequestBody @Valid EnviarSolicitudDTO dto){

        AmistadDTO amistad = amistadService.enviarAmistad(usuarioDetails.getId(), dto);

        return ResponseEntity.created(URI.create("/api/amistades/" + amistad.getId())).body(amistad);
    }

    @PatchMapping("/{amistadId}/aceptar")
    public ResponseEntity<AmistadDTO> aceptarSolicitud(@AuthenticationPrincipal UsuarioDetails usuarioDetails,
                                                       @PathVariable Long amistadId){

        return ResponseEntity.ok(amistadService.aceptarSolicitud(usuarioDetails.getId(), amistadId));

    }

    @PatchMapping("/{amistadId}/rechazar")
    public ResponseEntity<Void> rechazarSolicitud(@AuthenticationPrincipal UsuarioDetails usuarioDetails,
                                                  @PathVariable Long amistadId){

        amistadService.rechazarSolicitud(usuarioDetails.getId(), amistadId);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{amistadId}/eliminar")
    public ResponseEntity<Void> eliminarSolicitud(@AuthenticationPrincipal UsuarioDetails usuarioDetails,
                                                  @PathVariable Long amistadId){

        amistadService.eliminarAmistad(usuarioDetails.getId(), amistadId);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{amistadId}/cancelar")
    public ResponseEntity<Void> cancelarSolicitud(@AuthenticationPrincipal UsuarioDetails usuarioDetails,
                                                  @PathVariable Long amistadId){

        amistadService.cancelarSolicitud(usuarioDetails.getId(), amistadId);

        return ResponseEntity.noContent().build();
    }

}
