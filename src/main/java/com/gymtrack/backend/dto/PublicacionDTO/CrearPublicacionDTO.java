package com.gymtrack.backend.dto.PublicacionDTO;

import com.gymtrack.backend.dto.EntrenamientoDTO.EntrenamientoDTO;
import com.gymtrack.backend.dto.UsuarioDTO.UsuarioResumenDTO;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.URL;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class CrearPublicacionDTO {

    //esto es una descripcion
    @Size(max = 500)
    private String contenido;

    @Size(max = 500)
    @URL
    private String imagenURL;

    @NotNull
    private Long entrenamientoId;
}
