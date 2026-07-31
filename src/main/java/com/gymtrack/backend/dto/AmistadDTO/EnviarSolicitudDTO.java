package com.gymtrack.backend.dto.AmistadDTO;


import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class EnviarSolicitudDTO {

    @NotNull
    private Long receptorId;
}
