package com.gymtrack.backend.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Value("${CLOUDINARY_CLOUD_NAME}")
    private String cloudName;

    @Value("${CLOUDINARY_API_KEY}")
    private String apiKey;

    @Value("${CLOUDINARY_API_SECRET}")
    private String apiSecret;

    @Bean //este objeto lo administra spring, o sea va a crear un Cloudinary al
    //iniciar la aplicacion y lo va a guardar en su application context
    public Cloudinary cloudinary(){

        //ObjectUtils viene de Cloudinary
        return new Cloudinary(ObjectUtils.asMap(

                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret
        ));
    }
}
