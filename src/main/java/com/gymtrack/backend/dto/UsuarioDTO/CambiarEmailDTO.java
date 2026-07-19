package com.gymtrack.backend.dto.UsuarioDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class CambiarEmailDTO {

    @NotBlank
    @Email(message = "El formato del email no es válido")
    @Size(max = 50)
    private String email;
}
