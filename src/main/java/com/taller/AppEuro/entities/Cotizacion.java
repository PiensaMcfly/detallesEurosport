
package com.taller.AppEuro.entities;

import com.taller.AppEuro.enumeraciones.Encargado;
import com.taller.AppEuro.enumeraciones.EstadoCotizacion;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
   
@ManyToOne
 @JoinColumn(name = "cliente_id")
 private Cliente cliente;
}