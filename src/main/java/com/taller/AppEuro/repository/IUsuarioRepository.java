/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.taller.AppEuro.repository;

import com.taller.AppEuro.entities.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario,String> {

    
    public Optional<Usuario> findBynombreUsuario(Optional<Usuario> nombreUsuario); 
    
    public Optional <Usuario>findByemail(String email);
    
    @Query("SELECT u FROM Usuario u WHERE u.email = :email")
    public Usuario buscaremail(String email);
    
     @Query("SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario")
    public Usuario buscarPorusuario(@Param("nombreUsuario") String nombreUsuario);
    
}

