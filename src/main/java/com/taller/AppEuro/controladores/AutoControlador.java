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
@RequestMapping("/auto")
public class AutoControlador {

    @GetMapping("/registrar")
    public String crearAuto() {
        return "auto_form.html";
    }

    @PostMapping("/registrado")
    public String AutoCreado(String modelo, String marca, String patente,
            String vin, String kilometraje, String comentario, Date fechaCreacion)throws MiException {
        System.out.println("modelo:"+ modelo + "marca:"+ marca + "Patente: "+patente + "VIN:"+ vin + "Kilometraje:"+ kilometraje + "comentario"+comentario +
                "fecha"+fechaCreacion);
        return "auto_form.html";

    }
}
