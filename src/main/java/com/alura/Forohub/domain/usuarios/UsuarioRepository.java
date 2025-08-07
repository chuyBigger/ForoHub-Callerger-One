package com.alura.Forohub.domain.usuarios;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByCorreoElectronico(@NotBlank @Email String correoElectronico);

    UserDetails findByCorreoElectronico( String correo);
}
