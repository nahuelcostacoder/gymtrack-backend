package com.gymtrack.backend.controller;

import com.gymtrack.backend.dto.AmistadDTO.AmistadDTO;
import com.gymtrack.backend.dto.AmistadDTO.EnviarSolicitudDTO;
import com.gymtrack.backend.model.Amistad;
import com.gymtrack.backend.service.AmistadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/usuarios/{usuarioId}/amistades")
public class AmistadController {

    /*
    private final AmistadService amistadService;

    @GetMapping
    public ResponseEntity<List<AmistadDTO>> listarAmigos(@PathVariable Long usuarioId){

        return ResponseEntity.ok(amistadService.listarAmigos(usuarioId));
    }

    @GetMapping("/solicitudes/recibidas")
    public ResponseEntity<List<AmistadDTO>> listarSolicitudesRecibidas(@PathVariable Long usuarioId){

        return ResponseEntity.ok(amistadService.listarSolicitudesRecibidas(usuarioId));
    }

    @GetMapping("/solicitudes/enviadas")
    public ResponseEntity<List<AmistadDTO>> listarSolicitudesEnviadas(@PathVariable Long usuarioId){

        return ResponseEntity.ok(amistadService.listarSolicitudesEnviadas(usuarioId));
    }

    @PostMapping("/solicitudes")
    public ResponseEntity<AmistadDTO> enviarSolicitud(@PathVariable Long emisorId, @RequestBody @Valid EnviarSolicitudDTO dto){

        AmistadDTO amistad = amistadService.enviarAmistad(emisorId, dto);

        return ResponseEntity.created(URI.create("/api/amistades/" + amistad.getId())).body(amistad);
    }

    @PatchMapping("{amistadId}/aceptar")
    public ResponseEntity<AmistadDTO> aceptarSolicitud(@PathVariable Long amistadId){

        return ResponseEntity.ok(amistadService.aceptarSolicitud(amistadId));

    }*/

}
