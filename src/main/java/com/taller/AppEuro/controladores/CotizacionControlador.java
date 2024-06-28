/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.taller.AppEuro.controladores;

import com.taller.AppEuro.entities.Cotizacion;
import com.taller.AppEuro.enumeraciones.Encargado;
import com.taller.AppEuro.enumeraciones.EstadoCotizacion;
import com.taller.AppEuro.exepciones.MiException;
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

    @GetMapping("/nueva")
    public String mostrarFormularioDeCreacion(Model model) {
        model.addAttribute("cotizacion", new Cotizacion());
        model.addAttribute("clientes", clienteService.obtenerTodosLosClientes());
        return "coti_form.html";
    }

    @PostMapping("/sumbit")
    public String crearCotizacion(Cotizacion cotizacion) throws MiException {
        cotizacionService.saveCotizacion(cotizacion.getCliente().getIdCliente(), cotizacion.getMonto(), cotizacion.getDescripcion(), cotizacion.getEstado(), cotizacion.getEncargado(), cotizacion.getFormaPago());
        return "redirect:/cotizaciones";
    }
    
    
    
    //----------------------------------Controladores 2 -------------------------------------------
    
      @GetMapping
    public ResponseEntity<List<Cotizacion>> obtenerTodasLasCotizaciones() {
        List<Cotizacion> cotizaciones = cotizacionService.obtenerTodasLasCotizaciones();
        return ResponseEntity.ok(cotizaciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cotizacion> obtenerCotizacionPorId(@PathVariable Long id) throws MiException {
        Optional<Cotizacion> cotizacion = cotizacionService.obtenerCotizacionPorId(id);
        return cotizacion.map(ResponseEntity::ok).orElseThrow(() -> new MiException("Cotización no encontrada"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cotizacion> actualizarCotizacion(@PathVariable Long id,
                                                           @RequestParam Long monto,
                                                           @RequestParam String descripcion,
                                                           @RequestParam EstadoCotizacion estado,
                                                           @RequestParam Encargado encargado,
                                                           @RequestParam String formaPago) throws MiException {
        Cotizacion cotizacionActualizada = cotizacionService.actualizarCotizacion(id, monto, descripcion, estado, encargado, formaPago);
        return ResponseEntity.ok(cotizacionActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCotizacion(@PathVariable Long id) {
        cotizacionService.deleteCotizacion(id);
        return ResponseEntity.noContent().build();
    }
}