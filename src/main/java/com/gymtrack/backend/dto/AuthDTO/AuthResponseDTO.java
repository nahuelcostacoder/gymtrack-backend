package com.gymtrack.backend.dto.AuthDTO;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

//obligo que siga ese orden
@JsonPropertyOrder({"username", "message", "jwt", "status"})
public record AuthResponseDTO(String username,
                              String message,
                              String token) {
}