package com.gymtrack.backend.dto.UsuarioDTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class UsuarioResumenDTO {

    private Long id;
    private String username;
    private String nombre;
    private String fotoPerfilUrl;
}
