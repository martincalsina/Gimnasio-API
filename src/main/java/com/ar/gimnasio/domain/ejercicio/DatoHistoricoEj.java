/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.ar.gimnasio.domain.ejercicio;

import com.ar.gimnasio.domain.set.Set;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 *
 * @author marti
 */
public record DatoHistoricoEj(
        @NotNull LocalDate fecha,
        @NotNull Integer peso) {
    
    public DatoHistoricoEj(LocalDate fecha, Set set) {
        this(fecha, set.getPeso());
    }

}
