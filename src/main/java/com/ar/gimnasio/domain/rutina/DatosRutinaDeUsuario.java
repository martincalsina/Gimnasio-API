/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.ar.gimnasio.domain.rutina;

import jakarta.validation.constraints.NotNull;

/**
 *
 * @author marti
 */
public record DatosRutinaDeUsuario(
        @NotNull Integer rutina_id,
        @NotNull String nombre) {
    
    public DatosRutinaDeUsuario(Rutina rutina) {
        this(rutina.getId(), rutina.getNombre());
    }

}
