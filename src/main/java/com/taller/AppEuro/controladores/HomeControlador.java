/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.taller.AppEuro.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeControlador {
    
    @GetMapping("/inicio")
    public String index(){
    return "index.hmtl";
    }
    
    @GetMapping("/login")
    public String login(){
    return "Login.html";}
}
