/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.taller.AppEuro.controladores;

import com.taller.AppEuro.exepciones.MiException;
import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cotizaciones")
public class CotizacionControlador {

    @GetMapping("/nuevaCotizacion")
    public String crearCotizacion() {
        return "auto_form.html";
    }
    
    @PostMapping("/sumbit")
    public String nuevacoti(@RequestParam String Rut,String nombre,String apellido, String telefono,String mail,String numeroVin)throws MiException {
        System.out.println("rut:"+ Rut + "nombre:"+ nombre + "Apellid: "+apellido + "tel:"+ telefono + "mail:"+ mail + "Vin"+numeroVin);
        return "auto_form.html";}


}