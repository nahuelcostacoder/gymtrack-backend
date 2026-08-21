package com.gymtrack.backend.config;

import com.gymtrack.backend.model.Rol;
import com.gymtrack.backend.model.Usuario;
import com.gymtrack.backend.repository.RolRepository;
import com.gymtrack.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.HashSet;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final RolRepository rolRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initData() {
        return args -> {

            Rol rolUsuario = rolRepository.findByNombre("USUARIO")
                    .orElseGet(() -> rolRepository.save(
                            Rol.builder()
                                    .nombre("USUARIO")
                                    .build()
                    ));

            Rol rolAdmin = rolRepository.findByNombre("ADMIN")
                    .orElseGet(() -> rolRepository.save(
                            Rol.builder()
                                    .nombre("ADMIN")
                                    .build()
                    ));

            if (!usuarioRepository.existsByUsername("admin")) {

                Usuario admin = Usuario.builder()
                        .username("admin")
                        .email("admin@gymtrack.local")
                        .password(passwordEncoder.encode("12345678"))
                        .nombre("Admin")
                        .apellido("GymTrack")
                        .fechaNacimiento(LocalDate.of(2000, 1, 1))
                        .habilitado(true)
                        .roles(new HashSet<>())
                        .build();

                admin.getRoles().add(rolAdmin);

                usuarioRepository.save(admin);
            }
        };
    }
}