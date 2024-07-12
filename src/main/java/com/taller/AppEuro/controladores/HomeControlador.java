/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.taller.AppEuro.controladores;

import com.taller.AppEuro.entities.Usuario;
import com.taller.AppEuro.enumeraciones.Rol;
import com.taller.AppEuro.exepciones.MiException;
import com.taller.AppEuro.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class HomeControlador {
    
    @Autowired
    public UsuarioService usuarioservi;
    
    @GetMapping("/inicio")
    public String index(){
    return "index.hmtl";
    }
    
    @GetMapping("/v1/login")
    public String login(@RequestParam(required = false)String error, Model modelo){
       
    return "Login.html";}
    
    
     //Registro ADM-----------------
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/registrar")
    public String mostrarFormularioDeRegistro(Model model ,Rol rol) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("roles", Rol.values());
        return "registrouser.html";
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/registrar")
    public String registrarUsuario( Usuario usuario, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("roles", Rol.values());
            return "registrouser.html";
        }
        try {
            usuarioservi.registrar(usuario.getNombreUsuario(), usuario.getPassword(), usuario.getEmail(), usuario.getRol());
            return "redirect:/registrar";
        } catch (MiException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("roles", Rol.values());
            return "registrouser.html";
        }
    }


}
