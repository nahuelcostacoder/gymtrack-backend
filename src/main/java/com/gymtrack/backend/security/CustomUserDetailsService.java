package com.gymtrack.backend.security;

import com.gymtrack.backend.model.Usuario;
import com.gymtrack.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override //recibimos el nombre del usuario
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No se ha encontrado un usuario con nombre de usuario: " + username));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();


        //aqui lo que estoy haciendo es guardar dentro de la authority list los roles del usuario,
        //pero se guardan como SimpleGrantedAuthority y para ello se guarda con el formato "ROLE_NOMBREROL"
        usuario.getRoles().forEach(role -> authorityList
                .add(new SimpleGrantedAuthority("ROLE_".concat(role.getNombre()))));

        //SimpleGrantedAuthority es nada mas y nada menos que una clase que implementa GranthedAuthority
        //y que guarda un rol, en formato spring.

        usuario.getRoles().stream().flatMap(role -> role.getPermisos().stream())
                .distinct()
                .forEach(permiso -> authorityList
                        .add(new SimpleGrantedAuthority(permiso.getNombre())));

        return new UsuarioDetails( //uso un usuarioDetails propio y no el base, ya que asi puedo agregarle atributos como el id

                //esto me va a ahorrar en el controller al obtener el id del usuario autenticado, hacer otra llamada a la bd.

                usuario.getId(),
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.isHabilitado(),
                authorityList
        );
    }
}
