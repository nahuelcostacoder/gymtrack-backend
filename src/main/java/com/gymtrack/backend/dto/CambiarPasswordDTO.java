package com.gymtrack.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class CambiarPasswordDTO {

    @NotBlank
    @Size(min = 8, max = 100)
    private String password;
}
