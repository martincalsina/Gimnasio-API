/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.ar.gimnasio.domain.rutina;

import com.ar.gimnasio.domain.persona.Persona;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author marti
 */
public record DatosCargaRutina(
        @NotNull String nombre,
        @NotNull Persona persona) {

}
