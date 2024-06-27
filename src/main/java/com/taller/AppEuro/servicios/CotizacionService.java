/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.taller.AppEuro.servicios;

import com.taller.AppEuro.entities.Cotizacion;
import com.taller.AppEuro.enumeraciones.Encargado;
import com.taller.AppEuro.enumeraciones.EstadoCotizacion;
import com.taller.AppEuro.exepciones.MiException;
import com.taller.AppEuro.repository.IAutoRepository;
import com.taller.AppEuro.repository.IClienteRepository;
import com.taller.AppEuro.repository.ICotizacionRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CotizacionService {

    @Autowired
    private IAutoRepository autorepo;

    @Autowired
    private ICotizacionRepository cotizacionrepo;

    @Autowired
    IClienteRepository clienterepo;

    @Transactional
    public void CrearCotizacion(Long monto, String descripcion, EstadoCotizacion estado, Encargado encargado, String formaPago) throws MiException {
        validaciones(monto, descripcion, estado, encargado, formaPago);

        Cotizacion cotizacion = new Cotizacion();

        cotizacion.setMonto(monto);
        cotizacion.setDescripcion(descripcion);
        cotizacion.setEstado(estado);
        cotizacion.setEncargado(encargado);
        cotizacion.setFormaPago(formaPago);

        cotizacionrepo.save(cotizacion);
    }

    public void modificarCotizacion(Long idCotizacion, Long monto, String descripcion,
            EstadoCotizacion estado, Encargado encargado, String formaPago) {

        Optional<Cotizacion> respuestaCoti = cotizacionrepo.findById(idCotizacion);

        Cotizacion cotizacion = new Cotizacion();

        if (respuestaCoti.isPresent()) {
            cotizacion = respuestaCoti.get();

            cotizacion.setMonto(monto);
            cotizacion.setDescripcion(descripcion);
            cotizacion.setEstado(estado);
            cotizacion.setEncargado(encargado);
            cotizacion.setFormaPago(formaPago);

        }

    }

    public List<Cotizacion> listarCotizaciones() {
        List<Cotizacion> cotizaciones = cotizacionrepo.findAll();
        return cotizaciones;
    }

    @Transactional
    public void EliminarCotizacion() {
        cotizacionrepo.deleteById(Long.MIN_VALUE);
    }

    public void encontrarCotizacion() {
        cotizacionrepo.getOne(Long.MIN_VALUE).getIdCotizacion();
    }

    private void validaciones(Long monto, String descripcion, EstadoCotizacion estado, Encargado encargado, String formaPago) throws MiException {

        if (monto == null) {
            throw new MiException("El monto de la operacion no puede estar Vacio o Nulo");
        }
        if (descripcion.isEmpty() || descripcion == null) {
            throw new MiException("El Servicio/Comentario no puede estar Vacio o Nulo, Describe el servicio a gestionar");
        }
        if (formaPago.isEmpty() || formaPago == null) {
            throw new MiException("La forma de pago no puede estar Vacio o Nulo, escriba una forma de pago ");
        }

    }
}
