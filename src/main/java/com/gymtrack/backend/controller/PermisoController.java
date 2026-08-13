package com.gymtrack.backend.controller;

import com.gymtrack.backend.dto.PerfilDTO.CrearPerfilDTO;
import com.gymtrack.backend.dto.PermisoDTO.ActualizarPermisoDTO;
import com.gymtrack.backend.dto.PermisoDTO.CrearPermisoDTO;
import com.gymtrack.backend.dto.PermisoDTO.PermisoDTO;
import com.gymtrack.backend.service.PermisoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/permisos")
@RequiredArgsConstructor
public class PermisoController {

    private final PermisoService permisoService;

    @PreAuthorize("hasAuthority('PERMISO_VER')")
    @GetMapping
    public ResponseEntity<List<PermisoDTO>> listar(){

        return ResponseEntity.ok(permisoService.listar());
    }

    @PreAuthorize("hasAuthority('PERMISO_VER')")
    @GetMapping("/{permisoId}")
    public ResponseEntity<PermisoDTO> buscarPorId(@PathVariable Long permisoId){

        return ResponseEntity.ok(permisoService.buscarPorId(permisoId));
    }

    @PreAuthorize("hasAuthority('PERMISO_VER')")
    @GetMapping("/nombre/{nombrePermiso}")
    public ResponseEntity<PermisoDTO> buscarPorNombre(@PathVariable String nombrePermiso){

        return ResponseEntity.ok(permisoService.buscarPorNombre(nombrePermiso));
    }


    @PreAuthorize("hasAuthority('PERMISO_GESTIONAR')")
    @PostMapping
    public ResponseEntity<PermisoDTO> crear(@RequestBody @Valid CrearPermisoDTO dto){

        PermisoDTO permiso = permisoService.crear(dto);

        return ResponseEntity.created(URI.create("/api/permiso/" + permiso.getId())).body(permiso);
    }

    @PreAuthorize("hasAuthority('PERMISO_GESTIONAR')")
    @PatchMapping("/{permisoId}")
    public ResponseEntity<PermisoDTO> actualizar(@PathVariable Long permisoId,
                                                 @RequestBody @Valid ActualizarPermisoDTO dto){

        return ResponseEntity.ok(permisoService.actualizar(permisoId, dto));
    }

    @PreAuthorize("hasAuthority('PERMISO_GESTIONAR')")
    @DeleteMapping("/{permisoId}")
    public ResponseEntity<Void> eliminar(@PathVariable Long permisoId){

        permisoService.eliminar(permisoId);

        return ResponseEntity.noContent().build();
    }

}
