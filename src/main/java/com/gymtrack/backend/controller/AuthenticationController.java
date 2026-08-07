package com.gymtrack.backend.controller;

import com.gymtrack.backend.dto.AuthDTO.AuthLoginRequestDTO;
import com.gymtrack.backend.dto.AuthDTO.AuthResponseDTO;
import com.gymtrack.backend.dto.UsuarioDTO.CrearUsuarioDTO;
import com.gymtrack.backend.dto.UsuarioDTO.UsuarioDTO;
import com.gymtrack.backend.security.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid AuthLoginRequestDTO dto){

        return ResponseEntity.ok(authenticationService.loguearUsuario(dto));

    }

    @PostMapping("/registro")
    public ResponseEntity<UsuarioDTO> registrar(@RequestBody @Valid CrearUsuarioDTO dto){

        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationService.registrar(dto));
    }
}
