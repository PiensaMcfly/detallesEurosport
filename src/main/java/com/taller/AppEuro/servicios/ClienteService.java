/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.taller.AppEuro.servicios;

import com.taller.AppEuro.entities.Auto;
import com.taller.AppEuro.entities.Cliente;
import com.taller.AppEuro.entities.Cotizacion;
import com.taller.AppEuro.exepciones.MiException;
import com.taller.AppEuro.repository.IAutoRepository;
import com.taller.AppEuro.repository.IClienteRepository;
import com.taller.AppEuro.repository.ICotizacionRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private IAutoRepository autorepo;

    @Autowired
    private ICotizacionRepository cotizacionrepo;

    @Autowired
    IClienteRepository clienterepo;

    @Transactional
    public void crearCliente(String rut, String nombre, String apellido, String telefono, String mail, String numeroVin) throws MiException {

        validaciones(rut,nombre,apellido,telefono,mail,numeroVin);
        
        
        List<Auto> auto = (List<Auto>) autorepo.findById(Long.MIN_VALUE).get();
        List<Cotizacion> cotizacion = (List<Cotizacion>) cotizacionrepo.findById(Long.MIN_VALUE).get();

//        como le seteo la lista de auto y cotizaciones al cliente(en los modelos estan Como listas )
        Cliente cliente = new Cliente();

        cliente.setRut(rut);
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setTelefono(telefono);
        cliente.setMail(mail);
        cliente.setNumeroVin(numeroVin);

        clienterepo.save(cliente);

    }
     
//    Al este metodo  Modificar de modificar puedo agregarle validaciones, estar atento para agregar 
    
    public void modificarCliente(Long idCliente, String Rut, String nombre, String apellido, String telefono, String mail, String numeroVin, Long idAuto, Long idCotizacion) {
        Optional<Auto> respuestaAuto = autorepo.findById(idAuto);
        Optional<Cliente> respuestaCliente = clienterepo.findById(idCliente);
        Optional<Cotizacion> respuestaCoti = cotizacionrepo.findById(idCotizacion);

        Auto auto = new Auto();
        Cotizacion cotizacion = new Cotizacion();

        if (respuestaAuto.isPresent()) {
            auto = respuestaAuto.get();
        }

        if (respuestaCoti.isPresent()) {
            cotizacion = respuestaCoti.get();
        }

        if (respuestaCliente.isPresent()) {
            Cliente cliente = respuestaCliente.get();

            cliente.setRut(Rut);
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setTelefono(telefono);
            cliente.setMail(mail);
            cliente.setNumeroVin(numeroVin);

        }

    }

    public List<Cliente> listarClientes() {
        List<Cliente> listaClientes = clienterepo.findAll();
        return listaClientes;
    }

    @Transactional
    public void EliminarCliente() {
        clienterepo.deleteById(Long.MIN_VALUE);
    }

    public void encontrarCliente() {
        clienterepo.getOne(Long.MIN_VALUE).getIdCliente();
    }
    
    private void validaciones(String rut, String nombre, String apellido, String telefono, String mail, String numeroVin) throws MiException{
        
         if (rut.isEmpty() || rut == null) {
            throw new MiException("El numero RUT no puede estar Vacio o Nulo");
        }

        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("El Nombre del Cliente no puede estar Vacio o Nulo");
        }

        if (apellido.isEmpty() || apellido == null) {
            throw new MiException("El Apellido del Cliente no puede estar Vacio o Nulo");
        }
        if (telefono.isEmpty() || telefono == null) {
            throw new MiException("El numero de telefono no puede estar Vacio o Nulo");
        }

        if (mail.isEmpty() || mail == null) {
            throw new MiException("El email  no puede estar Vacio o Nulo");
        }
        
    }

    
    //------------------------------------------------------------------///
    
 
    @Transactional
    public void saveCliente(String rut, String nombre, String apellido, String telefono, String mail, String numeroVin) throws MiException {
        // Validaciones
        validaciones(rut, nombre, apellido, telefono, mail, numeroVin);

        // Crear cliente y asignar datos
        Cliente cliente = new Cliente();
        cliente.setRut(rut);
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setTelefono(telefono);
        cliente.setMail(mail);
        cliente.setNumeroVin(numeroVin);

        // Inicializar listas vacías
        cliente.setListaAuto(new ArrayList<>());
        cliente.setListaCotizacion(new ArrayList<>());

        // Guardar cliente
        clienterepo.save(cliente);
    }
    
    public Cliente Save(Cliente cliente) {
        return clienterepo.save(cliente);
    }

    public List<Cliente> obtenerTodosLosClientes() {
        return clienterepo.findAll();
    }

    public Optional<Cliente> obtenerClientePorId(Long id) {
        return clienterepo.findById(id);
    }

    public Cliente updateCliente(Long id, Cliente clienteActualizado) {
        return clienterepo.findById(id)
                .map(cliente -> {
                    cliente.setRut(clienteActualizado.getRut());
                    cliente.setNombre(clienteActualizado.getNombre());
                    cliente.setApellido(clienteActualizado.getApellido());
                    cliente.setTelefono(clienteActualizado.getTelefono());
                    cliente.setMail(clienteActualizado.getMail());
                    cliente.setNumeroVin(clienteActualizado.getNumeroVin());
                   // cliente.setListaAuto(clienteActualizado.getListaAuto());
                   // cliente.setListaCotizacion(clienteActualizado.getListaCotizacion());
                    return clienterepo.save(cliente);
                })
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    public void deleteCliente(Long id) {
        clienterepo.deleteById(id);
    }

    public Cliente actualizarCliente(Long id, Cliente clienteActualizado) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}


