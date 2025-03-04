
package com.taller.AppEuro.entities;

import com.taller.AppEuro.enumeraciones.CategoriaReparacion;
import com.taller.AppEuro.enumeraciones.Encargado;
import com.taller.AppEuro.enumeraciones.EstadoCotizacion;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cotizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idCotizacion;
    private Long monto;
    private String descripcion;
    @Enumerated(EnumType.STRING)
    private EstadoCotizacion estado;
    @Enumerated(EnumType.STRING)
    private Encargado encargado;
    private String formaPago;
    @Enumerated(EnumType.STRING)
    private CategoriaReparacion categoria;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Temporal(TemporalType.DATE)  // Guarda solo la fecha (sin hora)
    private Date fecha;

}