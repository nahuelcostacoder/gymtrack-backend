package com.gymtrack.backend.mapper;

import com.gymtrack.backend.dto.AmistadDTO.AmistadDTO;
import com.gymtrack.backend.model.Amistad;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-31T19:41:04-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.11 (Microsoft)"
)
@Component
public class AmistadMapperImpl implements AmistadMapper {

    @Override
    public AmistadDTO toDto(Amistad amistad) {
        if ( amistad == null ) {
            return null;
        }

        AmistadDTO.AmistadDTOBuilder amistadDTO = AmistadDTO.builder();

        amistadDTO.id( amistad.getId() );
        amistadDTO.estado( amistad.getEstado() );
        amistadDTO.fechaSolicitud( amistad.getFechaSolicitud() );

        return amistadDTO.build();
    }
}
