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
    public String formulario() {
        return "auto_form.html";
    }

    
    
      @GetMapping("/nuevo")
    public String mostrarFormularioDeCreacion(Model model) {
        
    List<Cliente> clientes= clienteService.obtenerTodosLosClientes();
        model.addAttribute("auto", new Auto());
        model.addAttribute("clientes", clientes);
        return "auto_form.html";
    }

    
     @PostMapping("/save")
    public String saveAuto(@ModelAttribute("auto") Auto auto) {
        autorepo.save(auto);
        return "redirect:/cotizaciones/nueva";
    
    }
    //----------------------------------------------------


    @GetMapping("/lista")
    public String obtenerTodosLosAutos(Model model) {
        List<Auto> autos = autoService.obtenerTodosLosAutos();
        model.addAttribute("autos", autos);
        return "lista_auto.html";
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

    @GetMapping("/editar/{id}")
    public String mostrarFormularioDeEdicion(@PathVariable Long id, Model model) throws MiException {
        Optional<Auto> auto = autoService.obtenerAutoPorId(id);
        if (auto.isPresent()) {
            model.addAttribute("auto", auto.get());
            model.addAttribute("clientes", clienteService.obtenerTodosLosClientes());
            return "editar_auto.html";
        } else {
            throw new MiException("Auto no encontrado");
        }
    }

    @PostMapping("/editar/{id}")
    public String actualizarAuto(@PathVariable Long id, @ModelAttribute Auto autoActualizado) throws MiException {
        autoService.actualizarAuto(id, autoActualizado.getModelo(), autoActualizado.getMarca(), autoActualizado.getPatente(), autoActualizado.getVin(), autoActualizado.getKilometraje(), autoActualizado.getComentario(), autoActualizado.getFechadeCreacion());
        return "redirect:/autos/lista";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarAuto(@PathVariable Long id) {
        autoService.deleteAuto(id);
        return "redirect:/autos/lista";
    }
}
