package com.gymtrack.backend.dto.MeGustaDTO;

import com.gymtrack.backend.dto.UsuarioDTO.UsuarioResumenDTO;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class MeGustaDTO {

    private Long id;

    private LocalDateTime fechaCreacion;

    private UsuarioResumenDTO usuario;
}
