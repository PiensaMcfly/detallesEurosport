/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.taller.AppEuro.controladores;

import com.taller.AppEuro.entities.Auto;
import com.taller.AppEuro.entities.Cliente;
import com.taller.AppEuro.entities.Cotizacion;
import com.taller.AppEuro.enumeraciones.Encargado;
import com.taller.AppEuro.enumeraciones.EstadoCotizacion;
import com.taller.AppEuro.repository.IAutoRepository;
import com.taller.AppEuro.repository.ICotizacionRepository;
import com.taller.AppEuro.servicios.AutoService;
import com.taller.AppEuro.servicios.ClienteService;
import com.taller.AppEuro.servicios.CotizacionService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardControlador {
   
    
    @Autowired
    private CotizacionService cotizacionService;

    @Autowired
    private ClienteService clienteService;
    
    @Autowired 
    private ICotizacionRepository cotirepo;
    
       @Autowired
    private AutoService autoService;
    
    @Autowired 
    private IAutoRepository autorepo;
    

 @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/panel")
    public String Dash(Model model){
         List<Cotizacion>cotizaciones = cotizacionService.obtenerTodasLasCotizaciones();
         List<Auto> autos = autoService.obtenerTodosLosAutos();
         List <Cliente> clientes = clienteService.obtenerTodosLosClientes();
         model.addAttribute("cotizaciones", cotizaciones);
         model.addAttribute("autos",autos);
         model.addAttribute("clientes", clientes);
         model.addAttribute("estados",EstadoCotizacion.values());
         model.addAttribute("encargados", Encargado.values());
//         model.addAttribute("cliente", new Cliente());
//         model.addAttribute("cotizacion", new Cotizacion());
//          model.addAttribute("auto", new Auto());
        
    return "dashboard.html";}
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/detalle/{id}")
  public String detallesCliente(@PathVariable Long id, Model model) {
        Optional<Cliente> cliente = clienteService.obtenerClientePorId(id);
        Optional<Auto> autos = autoService.obtenerAutoPorId(id);
       Optional<Cotizacion> cotizaciones = cotizacionService.obtenerCotizacionPorId(id);

        model.addAttribute("cliente", cliente.get());
        model.addAttribute("autos", autos);
        model.addAttribute("cotizaciones", cotizaciones);

        return "detalle_cliente.html";
    }
  
    
}
