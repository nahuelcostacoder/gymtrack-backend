package com.gymtrack.backend.security;

import com.gymtrack.backend.dto.Auth.AuthLoginRequestDTO;
import com.gymtrack.backend.dto.Auth.AuthResponseDTO;
import com.gymtrack.backend.utils.JwtUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    //recibo del controller
    public AuthResponseDTO loguearUsuario(@Valid AuthLoginRequestDTO authLoginRequestDTO){

        //extraigo datos que me trajo la request del usuario
        String username = authLoginRequestDTO.username();
        String password = authLoginRequestDTO.password();

        Authentication authentication = autenticar(username, password);

        //EL authentication seria como la credencial del usuario autenticado para no
        //tener que mostrar los datos todo el tiempo, y eso se guarda en el holder.
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //el SecurityContextHolder no sirve para recordar al usuario entre requests. Sirve para recordar al usuario durante la request actual.

        //creamos token
        String accessToken = jwtUtils.crearToken(authentication);

        AuthResponseDTO authResponseDTO = new AuthResponseDTO(username, "Se ha logueado exitosamente", accessToken);

        return authResponseDTO;
    }

    private Authentication autenticar(String username, String password){

        //creamos un token sin autenticar
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);

        //llamamos al authenticate que esta configurado en el config
        return authenticationManager.authenticate(authToken);

        //el objeto aca adentro es el provider manager que como vimos usa el dao que usa el userdetailsservice que hice.

        //el authenticationProvider es para indicarle cual provider queremos usar, es el del dao.

        //el daoAuthenticationProvider obtiene el token este de arriba, con el username y password.

        //Entonces llama a userDeetailsService.loadUserByUsername(username) (esto dentro suyo)

        //luego dentro del details mio compara password con el encoder, y si anda bien retorna y contruye esto:

        /*new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );*/


    }

}

//orden es
// AuthenticationManager
//        │
//        ▼
//ProviderManager
//        │
//        ▼
//DaoAuthenticationProvider
//        │
//        ▼
//CustomUserDetailsService
