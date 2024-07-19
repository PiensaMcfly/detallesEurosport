/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.taller.AppEuro.repository;

import com.taller.AppEuro.entities.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Long> {

    //@Query("SELECT c FROM Cliente c WHERE c.id = :id")
    //public String buscarcliente(@Param("id") String id);

    //@Query("SELECT c FROM Cotizacion c WHERE c. = :id")
    //public List <Cotizacion> (@Param("id") String id);s

    //@Query("SELECT c FROM Cliente c WHERE c.id = :isd")
    //public List <Auto>(@Param("id") String id);

    @Query("SELECT DISTINCT c FROM Cliente c " +
            "LEFT JOIN c.listaAuto a " +
            "LEFT JOIN c.listaCotizacion cot " +
            "WHERE c.rut LIKE %?1% OR " +
            "c.nombre LIKE %?1% OR " +
            "c.apellido LIKE %?1% OR " +
            "c.telefono LIKE %?1% OR " +
            "c.mail LIKE %?1% OR " +
            "cot.descripcion LIKE %?1% OR " +
            "CAST(cot.monto AS string) LIKE %?1% OR " +
            "a.marca LIKE %?1% OR " +
            "a.modelo LIKE %?1% OR " +
            "a.vin LIKE %?1%")
    Page<Cliente> findByKeyword(String keyword, Pageable pageable);
}

