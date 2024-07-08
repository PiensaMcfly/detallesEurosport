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

    @GetMapping("/nuevo")
    public String mostrarFormularioDeCreacion(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "Cliente_form.html";
    }

    @PostMapping("/sumbit")
    public String crearCliente(Cliente cliente) throws MiException {
        clienteService.saveCliente(cliente.getRut(), cliente.getNombre(), cliente.getApellido(), cliente.getTelefono(), cliente.getMail(), cliente.getNumeroVin());
        return "redirect:/autos/nuevo";
    }
    
  
    
   @GetMapping ("/lista")
    public String obtenerTodosLosClientes(Model model) {
        List<Cliente> clientes = clienteService.obtenerTodosLosClientes();
        model.addAttribute("clientes", clientes);
        return "lista_clientes.html";
    }
  

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

    @PostMapping("/editar/{id}")
    public String actualizarCliente(@PathVariable Long id, @ModelAttribute Cliente clienteActualizado) throws MiException {
        clienteService.updateCliente(id, clienteActualizado);
        return "redirect:/clientes/lista";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return "redirect:/clientes/lista";
    }
}
    
   