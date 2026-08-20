package com.gymtrack.backend.mapper;

import com.gymtrack.backend.dto.PerfilDTO.ActualizarPerfilDTO;
import com.gymtrack.backend.dto.PerfilDTO.CrearPerfilDTO;
import com.gymtrack.backend.dto.PerfilDTO.PerfilDTO;
import com.gymtrack.backend.model.Perfil;
import com.gymtrack.backend.model.Usuario;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-08-19T22:06:19-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.4 (Microsoft)"
)
@Component
public class PerfilMapperImpl implements PerfilMapper {

    @Override
    public PerfilDTO toDTO(Perfil perfil) {
        if ( perfil == null ) {
            return null;
        }

        PerfilDTO.PerfilDTOBuilder perfilDTO = PerfilDTO.builder();

        perfilDTO.usuarioId( perfilUsuarioId( perfil ) );
        perfilDTO.id( perfil.getId() );
        perfilDTO.biografia( perfil.getBiografia() );
        perfilDTO.peso( perfil.getPeso() );
        perfilDTO.altura( perfil.getAltura() );
        perfilDTO.objetivo( perfil.getObjetivo() );
        perfilDTO.nivelEntrenamiento( perfil.getNivelEntrenamiento() );

        return perfilDTO.build();
    }

    @Override
    public Perfil toEntity(CrearPerfilDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Perfil.PerfilBuilder perfil = Perfil.builder();

        perfil.biografia( dto.getBiografia() );
        perfil.peso( dto.getPeso() );
        perfil.altura( dto.getAltura() );
        perfil.objetivo( dto.getObjetivo() );
        perfil.nivelEntrenamiento( dto.getNivelEntrenamiento() );

        return perfil.build();
    }

    @Override
    public void updateEntity(ActualizarPerfilDTO dto, Perfil perfil) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getBiografia() != null ) {
            perfil.setBiografia( dto.getBiografia() );
        }
        if ( dto.getPeso() != null ) {
            perfil.setPeso( dto.getPeso() );
        }
        if ( dto.getAltura() != null ) {
            perfil.setAltura( dto.getAltura() );
        }
        if ( dto.getObjetivo() != null ) {
            perfil.setObjetivo( dto.getObjetivo() );
        }
        if ( dto.getNivelEntrenamiento() != null ) {
            perfil.setNivelEntrenamiento( dto.getNivelEntrenamiento() );
        }
    }

    private Long perfilUsuarioId(Perfil perfil) {
        Usuario usuario = perfil.getUsuario();
        if ( usuario == null ) {
            return null;
        }
        return usuario.getId();
    }
}
