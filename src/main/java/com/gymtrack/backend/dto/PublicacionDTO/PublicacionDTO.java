package com.gymtrack.backend.dto.PublicacionDTO;

import com.gymtrack.backend.dto.EntrenamientoDTO.EntrenamientoDTO;
import com.gymtrack.backend.dto.UsuarioDTO.UsuarioResumenDTO;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class PublicacionDTO {

    private Long id;

    private String contenido;

    private String imagenURL;

    private EntrenamientoDTO entrenamiento;
    //tiene que ver todo porque de ahi es lo que saca info para mostrar tambien

    private UsuarioResumenDTO usuario;

    private Long cantidadLikes;

    private Long cantidadComentarios;

    private boolean dioLike; //si el usuario le dio like, el corazon cambiaria a rojo

    private LocalDateTime fechaCreacion;

}
