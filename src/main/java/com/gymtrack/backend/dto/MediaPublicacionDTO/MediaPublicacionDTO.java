package com.gymtrack.backend.dto.MediaPublicacionDTO;

import com.gymtrack.backend.model.TipoMedia;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
public class MediaPublicacionDTO {

    private Long id;

    private String url;

    private TipoMedia tipo;

    //no expongo el public id al front
}

