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
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutoService {

    @Autowired
    private IAutoRepository autorepo;

    @Autowired
    private IClienteRepository clienterepo;

    @Autowired
    private ICotizacionRepository cotizacionrepo;

    @Transactional
    public void CrearAuto(String modelo, String marca, String patente, String vin, String kilometraje, String comentario, Date fechaCreacion) throws MiException {

       validaciones(modelo,marca,patente,vin,kilometraje,comentario,fechaCreacion);

        Cliente cliente = clienterepo.findById(Long.MIN_VALUE).get();
        Cotizacion cotizacion = cotizacionrepo.findById(Long.MIN_VALUE).get();

        Auto auto = new Auto();

        auto.setModelo(modelo);
        auto.setMarca(marca);
        auto.setPatente(patente);
        auto.setVin(vin);
        auto.setKilometraje(kilometraje);
        auto.setComentario(comentario);
        auto.setFechadeCreacion(fechaCreacion);

        auto.setCliente(cliente);
//  Ver si hago la relacion con la cotizacion
        autorepo.save(auto);

    }

    public void modificarAuto(Long idAuto, String modelo, String marca, String patente, String kilometraje, String Comentario, Long idCliente) {
        Optional<Auto> respuesta = autorepo.findById(idAuto);
        Optional<Cliente> respuestaCliente = clienterepo.findById(idCliente);

        Cliente cliente = new Cliente();

        if (respuestaCliente.isPresent()) {
            cliente = respuestaCliente.get();
        }

        if (respuesta.isPresent()) {
            Auto auto = respuesta.get();

            auto.setModelo(modelo);
            auto.setMarca(marca);
            auto.setPatente(patente);
            auto.setKilometraje(kilometraje);
            auto.setComentario(Comentario);
            auto.setCliente(cliente);
        }

    }

    public List<Auto> listarAutos() {
        List<Auto> listaAutos = autorepo.findAll();
        return listaAutos;
    }

    @Transactional
    public void EliminarAuto() {
        autorepo.deleteById(Long.MIN_VALUE);
    }

    public void encontrarAuto() {
        autorepo.getOne(Long.MIN_VALUE).getIdAuto();
    }
    
    private void validaciones(String modelo, String marca, String patente, String vin, String kilometraje, String comentario, Date fechaCreacion) throws MiException{
        
        
     if (vin.isEmpty() || vin == null) {
            throw new MiException("El numero VIN no puede estar Vacio o Nulo");
        }

      if (modelo.isEmpty() || modelo == null) {
            throw new MiException("El Modelo del vehiculo no puede estar Vacio o Nulo");
        }
        if (marca.isEmpty() || marca == null) {
            throw new MiException("La Marca del vehiculo no puede estar Vacio o Nulo");
        }
        
        if (patente.isEmpty() || patente == null) {
            throw new MiException("La Marca del vehiculo no puede estar Vacio o Nulo");
        }

 
    }
    
    
    
    
    //------------------------------SEGUNDA MANERA -----------------------------///
    
     @Transactional
    public Auto saveAuto(Long clienteId, String modelo, String marca, String patente, String vin, String kilometraje, String comentario, Date fechadeCreacion) throws MiException {
        // Validar cliente
        Cliente cliente = clienterepo.findById(clienteId)
                .orElseThrow(() -> new MiException("Cliente no encontrado"));

        // Crear nuevo auto
        Auto auto = new Auto();
        auto.setModelo(modelo);
        auto.setMarca(marca);
        auto.setPatente(patente);
        auto.setVin(vin);
        auto.setKilometraje(kilometraje);
        auto.setComentario(comentario);
        auto.setFechadeCreacion(fechadeCreacion);
        auto.setCliente(cliente);

        return autorepo.save(auto);
    }

    public List<Auto> obtenerTodosLosAutos() {
        return autorepo.findAll();
    }
    
//     public List<Auto> obtenerAutosPorClienteId(Long idCliente) {
//        return autorepo.findbyidCliente(idCliente);
//     }
    public Optional<Auto> obtenerAutoPorId(Long id) {
        return autorepo.findById(id);
    }

    @Transactional
    public Auto actualizarAuto(Long id, String modelo, String marca, String patente, String vin, String kilometraje, String comentario, Date fechadeCreacion) throws MiException {
        return autorepo.findById(id)
                .map(auto -> {
                    auto.setModelo(modelo);
                    auto.setMarca(marca);
                    auto.setPatente(patente);
                    auto.setVin(vin);
                    auto.setKilometraje(kilometraje);
                    auto.setComentario(comentario);
                    auto.setFechadeCreacion(fechadeCreacion);
                    return autorepo.save(auto);
                })
                .orElseThrow(() -> new MiException("Auto no encontrado"));
    }

    @Transactional
    public void deleteAuto(Long id) {
        autorepo.deleteById(id);
    }
    
    
}
