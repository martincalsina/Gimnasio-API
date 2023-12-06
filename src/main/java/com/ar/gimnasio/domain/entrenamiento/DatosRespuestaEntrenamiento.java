/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.ar.gimnasio.domain.entrenamiento;

import com.ar.gimnasio.domain.set.Set;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marti
 */


public record DatosRespuestaEntrenamiento(
        @NotNull LocalDate fecha, 
        @NotNull List<Set> sets
        ) {
    
    public DatosRespuestaEntrenamiento(Entrenamiento e) {
        this(e.getFecha(), e.getSets());
    }

}
