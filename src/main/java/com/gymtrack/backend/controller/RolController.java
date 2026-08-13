package com.gymtrack.backend.controller;

import com.gymtrack.backend.dto.RolDTO.ActualizarRolDTO;
import com.gymtrack.backend.dto.RolDTO.CrearRolDTO;
import com.gymtrack.backend.dto.RolDTO.RolDTO;
import com.gymtrack.backend.service.RolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/roles")
public class RolController {

    private final RolService rolService;

    @PreAuthorize("hasAuthority('ROL_VER')")
    @GetMapping
    public ResponseEntity<List<RolDTO>> listar(){

        return ResponseEntity.ok(rolService.listar());
    }

    @PreAuthorize("hasAuthority('ROL_VER')")
    @GetMapping("/{rolId}")
    public ResponseEntity<RolDTO> buscarPorId(@PathVariable Long rolId){

        return ResponseEntity.ok(rolService.buscarPorId(rolId));
    }

    @PreAuthorize("hasAuthority('ROL_GESTIONAR')")
    @PostMapping
    public ResponseEntity<RolDTO> crear(@RequestBody @Valid CrearRolDTO dto){

        RolDTO rol = rolService.crear(dto);

        return ResponseEntity.created(URI.create("/api/rol/" + rol.getId())).body(rol);
    }

    @PreAuthorize("hasAuthority('ROL_GESTIONAR')")
    @PatchMapping("/{rolId}")
    public ResponseEntity<RolDTO> actualizar(@PathVariable Long rolId,
                                             @RequestBody @Valid ActualizarRolDTO dto){

        return ResponseEntity.ok(rolService.actualizar(rolId, dto));
    }

    @PreAuthorize("hasAuthority('ROL_GESTIONAR')")
    @PatchMapping("/{rolId}/permisos/{permisoId}/agregarPermiso")
    public ResponseEntity<RolDTO> agregarPermiso(@PathVariable Long rolId,
                                                 @PathVariable Long permisoId){


        System.out.println("ENTRO A AGREGAR PERMISO");
        return ResponseEntity.ok(rolService.agregarPermiso(rolId, permisoId));
    }

    @PreAuthorize("hasAuthority('ROL_GESTIONAR')")
    @PatchMapping("/{rolId}/permisos/{permisoId}/quitarPermiso")
    public ResponseEntity<RolDTO> quitarPermiso(@PathVariable Long rolId,
                                                @PathVariable Long permisoId){

        return ResponseEntity.ok(rolService.quitarPermiso(rolId, permisoId));
    }

    @PreAuthorize("hasAuthority('ROL_GESTIONAR')")
    @DeleteMapping("/{rolId}")
    public ResponseEntity<Void> eliminarRol(@PathVariable Long rolId){

        rolService.eliminar(rolId);

        return ResponseEntity.noContent().build();
    }
}
