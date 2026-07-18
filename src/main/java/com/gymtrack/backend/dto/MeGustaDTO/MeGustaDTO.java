package com.gymtrack.backend.dto.MeGustaDTO;

import com.gymtrack.backend.dto.UsuarioDTO.UsuarioResumenDTO;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class MeGustaDTO {

    private Long id;

    private UsuarioResumenDTO usuario;
}
