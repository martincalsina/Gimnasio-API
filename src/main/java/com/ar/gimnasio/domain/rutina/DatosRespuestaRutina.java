/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.ar.gimnasio.domain.rutina;

import com.ar.gimnasio.domain.entrenamiento.Entrenamiento;
import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 *
 * @author marti
 */
public record DatosRespuestaRutina(
        @NotNull String nombre,
        @NotNull List<Entrenamiento> entrenamientos) {
    
    public DatosRespuestaRutina(Rutina rutina) {
        this(rutina.getNombre(), rutina.getEntrenamientos());
    } 

}
