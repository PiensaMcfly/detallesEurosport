
package com.taller.AppEuro.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class Auto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idAuto;
    private String modelo;
    private String marca;
    private String patente;
    private String kilometraje;
    private String comentario;
  @ManyToOne
    private Cliente cliente;
}