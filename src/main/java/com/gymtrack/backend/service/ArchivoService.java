package com.gymtrack.backend.service;

import com.gymtrack.backend.dto.ArchivoDTO.ArchivoDTO;
import com.gymtrack.backend.model.TipoMedia;
import org.springframework.web.multipart.MultipartFile;

public interface ArchivoService {

    //Recibo archivo y lo transformo a string
    ArchivoDTO subirArchivo(MultipartFile archivo);
    void eliminarArchivo(String publicId, TipoMedia tipo);
}
