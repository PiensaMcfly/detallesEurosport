/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.taller.AppEuro.repository;

import com.taller.AppEuro.entities.Auto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface IAutoRepository extends JpaRepository<Auto, Long> {
    //List<Object> findAllById(Long idAuto); // se agrego luego de que estuviera haciendo el metodo de cliente cmpleto
   
    @Query("SELECT a FROM Auto a WHERE a.idAuto = :idAuto")
    public Long buscarAuto(@Param("idAuto") Long idAuto);
    
     @Query("SELECT a FROM Auto a WHERE a.modelo = :idAuto")
    public Long buscarCliente(@Param("idAuto") Long idAuto);
    
    
}