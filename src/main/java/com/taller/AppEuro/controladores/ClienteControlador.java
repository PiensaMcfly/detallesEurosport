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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        return "redirect:/clientes";
    }
    
    
    //Crontoladores 2  SIn THYMELEAF--------------------------------------------------------
    
     @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestParam String rut,
                                                @RequestParam String nombre,
                                                @RequestParam String apellido,
                                                @RequestParam String telefono,
                                                @RequestParam String mail,
                                                @RequestParam String numeroVin) throws MiException {
        clienteService.crearCliente(rut, nombre, apellido, telefono, mail, numeroVin);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> obtenerTodosLosClientes() {
        List<Cliente> clientes = clienteService.obtenerTodosLosClientes();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Long id) throws MiException {
        Optional<Cliente> cliente = clienteService.obtenerClientePorId(id);
        return cliente.map(ResponseEntity::ok).orElseThrow(() -> new MiException("Cliente no encontrado"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody Cliente clienteActualizado) throws MiException {
        Cliente cliente = clienteService.actualizarCliente(id, clienteActualizado);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }
}
    
   