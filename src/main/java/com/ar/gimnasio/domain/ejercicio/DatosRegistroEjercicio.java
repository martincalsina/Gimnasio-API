/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.ar.gimnasio.domain.ejercicio;

import jakarta.validation.constraints.NotBlank;

/**
 *
 * @author marti
 */
public record DatosRegistroEjercicio(
        @NotBlank
        String nombre) {

}
