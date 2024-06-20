/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.taller.AppEuro.repository;

import com.taller.AppEuro.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Long> {

    //@Query("SELECT c FROM Cliente c WHERE c.id = :id")
    //public String buscarcliente(@Param("id") String id);

    //@Query("SELECT c FROM Cotizacion c WHERE c. = :id")
    //public List <Cotizacion> (@Param("id") String id);s

    //@Query("SELECT c FROM Cliente c WHERE c.id = :isd")
    //public List <Auto>(@Param("id") String id);

}