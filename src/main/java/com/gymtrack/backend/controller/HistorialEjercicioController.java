package com.gymtrack.backend.controller;

import com.gymtrack.backend.dto.EjercicioDTO.HistorialEjercicioDTO;
import com.gymtrack.backend.service.HistorialEjercicioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/usuarios/{usuarioId}/ejercicio/{ejercicioId}/historial")
public class HistorialEjercicioController {

    private final HistorialEjercicioService historialEjercicioService;

    @GetMapping
    public ResponseEntity<List<HistorialEjercicioDTO>> obtenerHistorial(@PathVariable Long usuarioId,
                                                                        @PathVariable Long ejercicioId){


        return ResponseEntity.ok(historialEjercicioService.obtenerHistorial(usuarioId, ejercicioId));
    }
}
