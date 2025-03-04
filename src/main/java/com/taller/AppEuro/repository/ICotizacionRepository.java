/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.taller.AppEuro.repository;


import com.taller.AppEuro.entities.Cotizacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ICotizacionRepository extends JpaRepository<Cotizacion, Long> {

    @Query("SELECT FUNCTION('MONTH', c.fecha), c.categoria, COUNT(c), AVG(c.monto) " +
            "FROM Cotizacion c " +
            "GROUP BY FUNCTION('MONTH', c.fecha), c.categoria " +
            "ORDER BY FUNCTION('MONTH', c.fecha) ASC")
    List<Object[]> getEstadisticasPorMesYCategoria();

}
