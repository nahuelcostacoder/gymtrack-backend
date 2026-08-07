package com.gymtrack.backend.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.gymtrack.backend.exception.InvalidTokenException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JwtUtils {

    @Value("${security.jwt.private.key}")
    private String secretKey; //private key genera la firma.

    @Value("${security.jwt.issuer}")
    private String issuer; //es el emisor

    @Value("${security.jwt.expiration-ms}")
    private long expirationMs;


    public String crearToken(Authentication authentication){

        //Authentication que contiene info del usuario: quien es, sus roles, si esta autenticado

        //Tiene varios metodos, entre ellos: getPrincipal() que es el usuario,
        //getCredentials() que tiene la constraseña o token y getAuthorities()
        //que contiene una colleccion de los roles.

        Algorithm algorithm = obtenerAlgoritmo(); //es el algoritmo con el
        //cual se usa el private key para firma y verificar el token.

        //dentro de authentication, esta toda la informacion del usuario que se acaba de autenticar,
        //nosotros la usamos para guardar esos datos en el jwt que asi lo mantiene en la pagina
        String username = authentication.getName();

        List<String> authorities = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        //Justamente GrantedAuthority es una interfaz de spring que contiene autoridades (roles) del usuario.

        //getAuthorities tiene una colleccion de GrantedAuthority que es basicamente un objeto que contiene el rol/roles del usuario.
        //Algo asi: GrantedAuthority("ROLE_USER"), GrantedAuthority("ROLE_ADMIN")


        String jwtToken = JWT.create()
                .withIssuer(this.issuer)
                .withSubject(username)
                .withClaim("authorities", authorities)
                .withIssuedAt((new Date()))
                .withExpiresAt(new Date(System.currentTimeMillis() + 1800000))
                .withJWTId(UUID.randomUUID().toString())
                .withNotBefore(new Date(System.currentTimeMillis()))
                .sign(algorithm); //la firma

        return jwtToken;
    }


    public DecodedJWT validarToken(String token) {

        try {

            Algorithm algorithm = obtenerAlgoritmo();
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(this.issuer).build();

            return verifier.verify(token);
        } catch (JWTVerificationException exception) {

            throw new InvalidTokenException("Token invalido. No autorizado", exception);
        }
    }

    public String obtenerUsername(DecodedJWT decodedJwt){

        return decodedJwt.getSubject();
    }

    public Claim obtenerClaimEspecifico(DecodedJWT decodedJWT, String claimName){

        return decodedJWT.getClaim(claimName);
    }

    public Map<String, Claim> devolverTodosLosClaims(DecodedJWT decodedJWT){

        return decodedJWT.getClaims();
    }

    private Algorithm obtenerAlgoritmo(){

        return Algorithm.HMAC256(secretKey);
    }

}


