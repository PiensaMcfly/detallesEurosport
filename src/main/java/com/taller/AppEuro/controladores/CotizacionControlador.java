/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.taller.AppEuro.controladores;

import com.taller.AppEuro.entities.Cliente;
import com.taller.AppEuro.entities.Cotizacion;
import com.taller.AppEuro.enumeraciones.CategoriaReparacion;
import com.taller.AppEuro.enumeraciones.Encargado;
import com.taller.AppEuro.enumeraciones.EstadoCotizacion;
import com.taller.AppEuro.exepciones.MiException;
import com.taller.AppEuro.repository.ICotizacionRepository;
import com.taller.AppEuro.servicios.ClienteService;
import com.taller.AppEuro.servicios.CotizacionService;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
 @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/nueva")
    public String mostrarFormularioDeCreacion(Model model) {
        List<Cliente> clientes= clienteService.obtenerTodosLosClientes();
        model.addAttribute("cotizacion", new Cotizacion());
        model.addAttribute("clientes", clientes);
        model.addAttribute("estados",EstadoCotizacion.values());
        model.addAttribute("encargados", Encargado.values());
       model.addAttribute("categorias",CategoriaReparacion.values());
        return "coti_form.html";
    }


     @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
      @PostMapping("/save1")
    public String saveCotizacion1(@ModelAttribute("cotizacion") Cotizacion cotizacion) {
         if (cotizacion.getFecha() == null) {
             cotizacion.setFecha(new Date()); // Establece la fecha actual si no se ingresó
         }
        cotirepo.save(cotizacion);
        return "redirect:/cotizaciones/lista";
    
    }
    
  
     @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
  @GetMapping("/lista")
    public String listarCotizaciones(Model model) {
        List<Cotizacion>cotizaciones = cotizacionService.obtenerTodasLasCotizaciones();
        model.addAttribute("cotizaciones", cotizaciones);
        return "lista_coti.html";
    }
 @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/editar/{id}")
    public String editarCotizacion(@PathVariable Long id, Model model) {
        Optional<Cotizacion> cotizacion = cotizacionService.obtenerCotizacionPorId(id);
   
        model.addAttribute("cotizacion", cotizacion.get());
        model.addAttribute("estados", EstadoCotizacion.values());
        model.addAttribute("encargados", Encargado.values());
        return "editar_coti.html";
    }
 @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/editar/{id}")
    public String updateCotizacion(@RequestParam("idCotizacion") Long idCotizacion,
                                   @RequestParam("monto") Long monto,
                                   @RequestParam("descripcion") String descripcion,
                                   @RequestParam("estado") EstadoCotizacion estado,
                                   @RequestParam("encargado") Encargado encargado,
                                   @RequestParam("formaPago") String formaPago,
                                   Model model) {
        try {
            cotizacionService.actualizarCotizacion(idCotizacion, monto, descripcion, estado, encargado, formaPago);
            return "redirect:/cotizaciones/lista";
        } catch (MiException e) {
            model.addAttribute("error", e.getMessage());
            return "editCotizacion";
        }
    }
 @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/eliminar/{id}")
    public String eliminarCotizacion(@PathVariable Long id) {
        cotizacionService.deleteCotizacion(id);
        return "redirect:/cotizaciones/lista";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/estadisticas")
    public String mostrarEstadisticas(Model model) {
        List<Map<String, Object>> estadisticas = cotizacionService.obtenerEstadisticasPorMes();
        model.addAttribute("estadisticas", estadisticas);
        return "estadisticas1";
    }



}