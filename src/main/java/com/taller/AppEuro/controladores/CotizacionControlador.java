/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.taller.AppEuro.controladores;

import com.taller.AppEuro.entities.Cliente;
import com.taller.AppEuro.entities.Cotizacion;
import com.taller.AppEuro.enumeraciones.Encargado;
import com.taller.AppEuro.enumeraciones.EstadoCotizacion;
import com.taller.AppEuro.exepciones.MiException;
import com.taller.AppEuro.repository.ICotizacionRepository;
import com.taller.AppEuro.servicios.ClienteService;
import com.taller.AppEuro.servicios.CotizacionService;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cotizaciones")
public class CotizacionControlador {

    @Autowired
    private CotizacionService cotizacionService;

    @Autowired
    private ClienteService clienteService;
    
    @Autowired 
    private ICotizacionRepository cotirepo;

    @GetMapping("/nueva")
    public String mostrarFormularioDeCreacion(Model model) {
        List<Cliente> clientes= clienteService.obtenerTodosLosClientes();
        model.addAttribute("cotizacion", new Cotizacion());
        model.addAttribute("clientes", clientes);
        model.addAttribute("estados",EstadoCotizacion.values());
        model.addAttribute("encargados", Encargado.values());
        return "coti_form.html";
    }

    
     @PostMapping("/save")
    public String saveCotizacion(@ModelAttribute("cotizacion") Cotizacion cotizacion) {
        cotirepo.save(cotizacion);
        return "redirect:/cotizaciones/nueva";
    
    }
    

    
    //----------------------------------Controladores 2 -------------------------------------------
    
  @GetMapping("/lista")
    public String listarCotizaciones(Model model) {
        List<Cotizacion>cotizaciones = cotizacionService.listarCotizaciones();
        model.addAttribute("cotizaciones", cotizaciones);
        return "lista_coti.html";
    }

    @GetMapping("/editar/{id}")
    public String editarCotizacion(@PathVariable Long id, Model model) {
        Optional<Cotizacion> cotizacion = cotizacionService.obtenerCotizacionPorId(id);
        model.addAttribute("cotizacion", cotizacion);
        model.addAttribute("estados", EstadoCotizacion.values());
        model.addAttribute("encargados", Encargado.values());
        return "editar_coti.html";
    }

    @PostMapping("/actualizar")
    public String actualizarCotizacion(@ModelAttribute Cotizacion cotizacion) {
        return "redirect:/cotizaciones";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCotizacion(@PathVariable Long id) {
        cotizacionService.deleteCotizacion(id);
        return "redirect:/cotizaciones";
    }
}