package com.gymtrack.backend.controller;

import com.gymtrack.backend.dto.Auth.AuthLoginRequestDTO;
import com.gymtrack.backend.dto.Auth.AuthResponseDTO;
import com.gymtrack.backend.security.AuthenticationService;
import com.gymtrack.backend.security.CustomUserDetailsService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private AuthenticationService authenticationService;

    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid AuthLoginRequestDTO dto){

        return ResponseEntity.ok(authenticationService.loguearUsuario(dto));

    }
}
