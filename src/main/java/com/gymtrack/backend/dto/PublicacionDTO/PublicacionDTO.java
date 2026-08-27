package com.gymtrack.backend.dto.PublicacionDTO;

import com.gymtrack.backend.dto.EntrenamientoDTO.EntrenamientoDTO;
import com.gymtrack.backend.dto.MediaPublicacionDTO.MediaPublicacionDTO;
import com.gymtrack.backend.dto.UsuarioDTO.UsuarioResumenDTO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class PublicacionDTO {

    private Long id;

    private String contenido;

    //traigo todos los mediaPublicacionDTO
    private List<MediaPublicacionDTO> media;

    private EntrenamientoDTO entrenamiento;
    //tiene que ver todo porque de ahi es lo que saca info para mostrar tambien

    private UsuarioResumenDTO usuario;

    private Long cantidadLikes;

    private Long cantidadComentarios;

    private boolean dioLike; //si el usuario le dio like, el corazon cambiaria a rojo

    private LocalDateTime fechaCreacion;

}
