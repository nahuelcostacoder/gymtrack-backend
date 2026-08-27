package com.gymtrack.backend.service;

import com.gymtrack.backend.dto.ImagenDTO.ImagenDTO;
import org.springframework.web.multipart.MultipartFile;

public interface ImagenService {

    //Recibo archivo y lo transformo a string
    ImagenDTO subirImagen(MultipartFile archivo);
    void eliminarImagen(String publicId);
}
