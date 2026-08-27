package com.gymtrack.backend.mapper;

import com.gymtrack.backend.dto.MeGustaDTO.MeGustaDTO;
import com.gymtrack.backend.model.MeGusta;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-08-27T18:59:51-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.12.1 (Microsoft)"
)
@Component
public class MeGustaMapperImpl implements MeGustaMapper {

    @Autowired
    private UsuarioResumenMapper usuarioResumenMapper;

    @Override
    public MeGustaDTO toDTO(MeGusta meGusta) {
        if ( meGusta == null ) {
            return null;
        }

        MeGustaDTO.MeGustaDTOBuilder meGustaDTO = MeGustaDTO.builder();

        meGustaDTO.id( meGusta.getId() );
        meGustaDTO.fechaCreacion( meGusta.getFechaCreacion() );
        meGustaDTO.usuario( usuarioResumenMapper.toDto( meGusta.getUsuario() ) );

        return meGustaDTO.build();
    }

    @Override
    public MeGusta toEntity(MeGustaDTO dto) {
        if ( dto == null ) {
            return null;
        }

        MeGusta.MeGustaBuilder meGusta = MeGusta.builder();

        meGusta.id( dto.getId() );

        return meGusta.build();
    }
}
