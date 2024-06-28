package com.taller.AppEuro.entities;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Auto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idAuto;
    private String modelo;
    private String marca;
    private String patente;
    private String vin;
    private String kilometraje;
    private String comentario;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechadeCreacion;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
