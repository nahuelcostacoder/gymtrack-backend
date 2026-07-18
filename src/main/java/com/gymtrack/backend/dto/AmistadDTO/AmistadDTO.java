package com.gymtrack.backend.dto.AmistadDTO;

import com.gymtrack.backend.dto.UsuarioDTO.UsuarioResumenDTO;
import com.gymtrack.backend.model.EstadoAmistad;
import com.gymtrack.backend.model.Usuario;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class AmistadDTO {

    private Long id;

    private EstadoAmistad estado;

    private LocalDateTime fechaSolicitud;

    private UsuarioResumenDTO emisor;

    private UsuarioResumenDTO receptor;
}
