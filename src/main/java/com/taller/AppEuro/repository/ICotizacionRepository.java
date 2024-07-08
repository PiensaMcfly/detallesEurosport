/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.taller.AppEuro.repository;


import com.taller.AppEuro.entities.Cotizacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ICotizacionRepository extends JpaRepository<Cotizacion, Long> {
    
//     List<Cotizacion>findbyidCliente(Long idCliente);
}
