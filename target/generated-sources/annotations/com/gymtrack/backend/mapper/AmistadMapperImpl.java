package com.gymtrack.backend.mapper;

import com.gymtrack.backend.dto.AmistadDTO.AmistadDTO;
import com.gymtrack.backend.model.Amistad;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-08-26T20:57:26-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.12.1 (Microsoft)"
)
@Component
public class AmistadMapperImpl implements AmistadMapper {

    @Autowired
    private UsuarioResumenMapper usuarioResumenMapper;

    @Override
    public AmistadDTO toDto(Amistad amistad) {
        if ( amistad == null ) {
            return null;
        }

        AmistadDTO.AmistadDTOBuilder amistadDTO = AmistadDTO.builder();

        amistadDTO.emisorSolicitud( usuarioResumenMapper.toDto( amistad.getEmisorSolicitud() ) );
        amistadDTO.receptorSolicitud( usuarioResumenMapper.toDto( amistad.getReceptorSolicitud() ) );
        amistadDTO.id( amistad.getId() );
        amistadDTO.estado( amistad.getEstado() );
        amistadDTO.fechaSolicitud( amistad.getFechaSolicitud() );

        return amistadDTO.build();
    }
}
