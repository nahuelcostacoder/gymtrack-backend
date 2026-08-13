package com.gymtrack.backend.mapper;

import com.gymtrack.backend.dto.AmistadDTO.AmistadDTO;
import com.gymtrack.backend.model.Amistad;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-08-12T21:01:15-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.2 (Oracle Corporation)"
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
