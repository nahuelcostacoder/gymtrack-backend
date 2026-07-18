package com.gymtrack.backend.dto.PublicacionDTO;


import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.URL;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class ActualizarPublicacionDTO {

    //esto es una descripcion
    @Size(max = 500)
    private String contenido;

    @Size(max = 500)
    @URL
    private String imagenURL;
}
