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

public class UsuarioService  implements UserDetailsService {

    @Autowired
   private IUsuarioRepository usuariorepo;


//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//    Usuario usuario = usuariorepo.findById(email);
//        if(usuario != null){
//            
//             List<GrantedAuthority> permisos = new ArrayList();
//            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_"+ usuario.get().;
//            permisos.add(p);
//            
//            User user = new User(usuario.get().getEmail(email);
//        }
//    
//    }


    @Transactional
    public void registrar( String nombreUsuario, String password, String password2, String email,Rol rol) throws MiException, ValidationException {

        // validar(nombreUsuario, nombreUsuario,password, password2, email);
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setPassword(password);
        usuario.setEmail(email);
        usuario.setRol(Rol.ADMIN);
        //  usuario.setPassword(new BCryptPasswordEncoder().encode(password));


        usuariorepo.save(usuario);
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }




}
