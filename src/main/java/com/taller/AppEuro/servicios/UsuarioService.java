package com.taller.AppEuro.servicios;

import com.taller.AppEuro.entities.Usuario;
import com.taller.AppEuro.enumeraciones.Rol;
import com.taller.AppEuro.exepciones.MiException;
import com.taller.AppEuro.repository.IUsuarioRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.ValidationException;
import java.util.Optional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class UsuarioService  implements UserDetailsService {

    @Autowired
   private IUsuarioRepository usuariorepo;


    @Override
   public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
        Usuario usuario = usuariorepo.buscarPorusuario(nombreUsuario);
        if (usuario != null) {

            List<GrantedAuthority> permisos = new ArrayList();
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());
            permisos.add(p);

            return new User(usuario.getNombreUsuario(), usuario.getPassword(), permisos);
        }else{
        return null;}
    }


@Transactional
    public void registrar(String nombreUsuario, String password, String email, Rol rol) throws MiException {
        validar(nombreUsuario, password, email);

        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setPassword(new BCryptPasswordEncoder().encode(password));
        usuario.setEmail(email);
        usuario.setRol(rol);

        usuariorepo.save(usuario);
    }

    private void validar(String nombreUsuario, String password, String email) throws MiException {
        if (nombreUsuario == null || nombreUsuario.isEmpty()) {
            throw new MiException("El nombre de usuario no puede estar vacío");
        }
        if (password == null || password.isEmpty() || password.length() < 6) {
            throw new MiException("La contraseña no puede estar vacía y debe tener al menos 6 caracteres");
        }
        if (email == null || email.isEmpty() || !email.contains("@")) {
            throw new MiException("El email no puede estar vacío y debe ser válido");

        }
    }
   

        @Transactional
        public void modificarUsuario(String nombreUsuario, String password, String password2, String email,Rol rol) throws MiException, ValidationException {//agrego el parametro para la imagen - gise

            // validar(nombreUsuario, nombreUsuario,password, password2, email);

            Optional<Usuario> respuesta = usuariorepo.findById(nombreUsuario);

            if (respuesta.isPresent()) {
                Usuario usuario = respuesta.get();
                usuario.setNombreUsuario(nombreUsuario);
                usuario.setPassword(password);
                usuario.setEmail(email);
                usuario.setRol(Rol.ADMIN);


                usuariorepo.save(usuario);
            }
        }

        @Transactional
        public void eliminarUsuario(String idUsuario) {
            usuariorepo.deleteById(idUsuario);
        }

}
