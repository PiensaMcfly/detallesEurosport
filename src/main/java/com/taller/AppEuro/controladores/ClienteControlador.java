/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.taller.AppEuro.controladores;

import com.taller.AppEuro.entities.Cliente;
import com.taller.AppEuro.exepciones.MiException;
import com.taller.AppEuro.servicios.ClienteService;
import java.util.List;
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


@Controller
@RequestMapping("/clientes")
public class ClienteControlador {

    @Autowired
    private ClienteService clienteService;
 @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/nuevo")
    public String mostrarFormularioDeCreacion(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "Cliente_form.html";
    }
 @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/sumbit")
    public String crearCliente(Cliente cliente) throws MiException {
        clienteService.saveCliente(cliente.getRut(), cliente.getNombre(), cliente.getApellido(), cliente.getTelefono(), cliente.getMail(), cliente.getNumeroVin());
        return "redirect:/autos/nuevo";
    }
    
  
     @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
   @GetMapping ("/lista")
    public String obtenerTodosLosClientes(Model model) {
        List<Cliente> clientes = clienteService.obtenerTodosLosClientes();
        model.addAttribute("clientes", clientes);
        return "lista_clientes.html";
    }
  
 @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/editar/{id}")
    public String mostrarFormularioDeEditarCliente(@PathVariable Long id, Model model) throws MiException {
        Optional<Cliente> cliente = clienteService.obtenerClientePorId(id);
        if (cliente.isPresent()) {
            model.addAttribute("cliente", cliente.get());
            return "editar_cliente.html";
        } else {
            throw new MiException("Cliente no encontrado");
        }
    }
 @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/editar/{id}")
    public String actualizarCliente(@PathVariable Long id, @ModelAttribute Cliente clienteActualizado) throws MiException {
        clienteService.updateCliente(id, clienteActualizado);
        return "redirect:/clientes/lista";
    }
 @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return "redirect:/clientes/lista";
    }

 @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/detalle/{id}")
    public String mostrarDetalleCliente(@PathVariable Long id, Model model) throws MiException {
        Optional<Cliente> clienteOpt = clienteService.obtenerClientePorId(id);
        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            model.addAttribute("cliente", cliente);
            model.addAttribute("autos", cliente.getListaAuto());
            model.addAttribute("cotizaciones", cliente.getListaCotizacion());
            return "detalle_cliente.html";
        } else {
            throw new MiException("Cliente no encontrado");
        }
    }

}
    
   