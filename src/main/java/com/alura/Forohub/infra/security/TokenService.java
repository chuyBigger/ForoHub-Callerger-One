package com.alura.Forohub.infra.security;

import com.alura.Forohub.domain.usuarios.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generarToken(Usuario usuario){
        try {
            var algiritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("Api Foro_Hub")
                    .withSubject(usuario.getCorreoElectronico())
                    .withExpiresAt(fechaExpiracion())
                    .sign(algiritmo);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error al generar el token JWT ", exception);
        }

    }

    private Instant fechaExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-06:00"));
    }

    public String getSubject(String tokenJWT){

        try {
            var algiritmo = Algorithm.HMAC256(secret);
            return JWT.require(algiritmo)
                    // specify any specific claim validations
                    .withIssuer("Api Foro_Hub")
                    // reusable verifier instance
                    .build()
                    .verify(tokenJWT)
                    .getSubject();


        } catch (JWTVerificationException exception){
           throw new RuntimeException("⚠️ Token JWT invalido o expirado.");
        }

    }

}
