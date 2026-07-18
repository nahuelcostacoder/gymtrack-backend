package com.gymtrack.backend.dto.ComentarioDTO;


import com.gymtrack.backend.dto.UsuarioDTO.UsuarioResumenDTO;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class ComentarioDTO {

    private Long id;

    private String contenido;

    private UsuarioResumenDTO usuario;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaActualizacion;
}
