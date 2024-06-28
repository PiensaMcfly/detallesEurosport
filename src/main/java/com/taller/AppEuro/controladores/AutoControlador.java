/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.taller.AppEuro.controladores;

import com.taller.AppEuro.entities.Auto;
import com.taller.AppEuro.entities.Cliente;
import com.taller.AppEuro.exepciones.MiException;
import com.taller.AppEuro.repository.IAutoRepository;
import com.taller.AppEuro.servicios.AutoService;
import com.taller.AppEuro.servicios.ClienteService;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/autos")
public class AutoControlador {

    @Autowired
    private AutoService autoService;
    
    @Autowired
    private ClienteService clienteService;
    
    @Autowired 
    private IAutoRepository autorepo;
    
    @GetMapping("/autos")
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
    
    //SEGUNDOS CONTROLERSSS------------con Thymeleaf----------------------------------
    
      @GetMapping("/nuevo")
    public String mostrarFormularioDeCreacion(Model model) {
        model.addAttribute("auto", new Auto());
        model.addAttribute("clientes", clienteService.obtenerTodosLosClientes());
        return "auto_form.html";
    }

    @PostMapping("/sumbit")
    public String crearAuto(@ModelAttribute Auto auto) throws MiException {
        autoService.saveAuto(auto.getCliente().getIdCliente(), auto.getModelo(), auto.getMarca(), auto.getPatente(), auto.getVin(), auto.getKilometraje(), auto.getComentario(), auto.getFechadeCreacion());
        return "redirect:/autos/nuevo";
    }
    
    
    //----------------------------------------------------


    @GetMapping
    public String obtenerTodosLosAutos(Model model) {
        List<Auto> autos = autoService.obtenerTodosLosAutos();
        model.addAttribute("autos", autos);
        return "lista-autos";
    }

    @GetMapping("/{id}")
    public String obtenerAutoPorId(@PathVariable Long id, Model model) throws MiException {
        Optional<Auto> auto = autoService.obtenerAutoPorId(id);
        if (auto.isPresent()) {
            model.addAttribute("auto", auto.get());
            return "detalle-auto";
        } else {
            throw new MiException("Auto no encontrado");
        }
    }

    @GetMapping("/{id}/editar")
    public String mostrarFormularioDeEdicion(@PathVariable Long id, Model model) throws MiException {
        Optional<Auto> auto = autoService.obtenerAutoPorId(id);
        if (auto.isPresent()) {
            model.addAttribute("auto", auto.get());
            model.addAttribute("clientes", clienteService.obtenerTodosLosClientes());
            return "editar-auto";
        } else {
            throw new MiException("Auto no encontrado");
        }
    }

    @PostMapping("/{id}/actualizar")
    public String actualizarAuto(@PathVariable Long id, @ModelAttribute Auto autoActualizado) throws MiException {
        autoService.actualizarAuto(id, autoActualizado.getModelo(), autoActualizado.getMarca(), autoActualizado.getPatente(), autoActualizado.getVin(), autoActualizado.getKilometraje(), autoActualizado.getComentario(), autoActualizado.getFechadeCreacion());
        return "redirect:/autos";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminarAuto(@PathVariable Long id) {
        autoService.deleteAuto(id);
        return "redirect:/autos";
    }
}
