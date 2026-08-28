package com.gymtrack.backend.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.gymtrack.backend.dto.ArchivoDTO.ArchivoDTO;
import com.gymtrack.backend.model.TipoMedia;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class CloudinaryImagenServiceImp implements ArchivoService {

    //inyectamos la dependencia, el bean que configuramos en el config
    // y que spring guardo a su application context
    private final Cloudinary cloudinary;

    @Override
    public ArchivoDTO subirArchivo(MultipartFile archivo) {

        try{

            //Pongo ? porque no se el tipo de datos que me va a devolver para la clave-> valor
            Map<?, ?> resultado = cloudinary.uploader().upload(
                    archivo.getBytes(), //mandamos los bytes de la imagen
                    ObjectUtils.asMap("resource_type", "auto") //por ahora no tenemos opciones extra
            );

            String url = resultado.get("secure_url").toString(); //url https de la imagen subida devuelve
            String publicId = resultado.get("public_id").toString();

            return new ArchivoDTO(url, publicId);

        } catch(IOException e){

            throw new RuntimeException("Error al subir la imagen", e);
        }
    }

    @Override
    public void eliminarArchivo(String publicId, TipoMedia tipo) {

        String resourceType = switch (tipo) {
            case IMAGEN -> "image";
            case VIDEO -> "video";
        };


        try {

            cloudinary.uploader().destroy(
                    publicId,
                    ObjectUtils.asMap("resource_type", resourceType)

            );
        } catch (IOException e){

            throw new RuntimeException("Error al eliminar la imagen", e);
        }

    }
}
