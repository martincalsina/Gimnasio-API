/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.ar.gimnasio.domain.set;

import com.ar.gimnasio.domain.repeticion.Repeticion;
import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 *
 * @author marti
 */
public record DatosVerSet(
        @NotNull Integer id,
        @NotNull Integer peso,
        @NotNull Integer series,
        @NotNull String ejercicio,
        @NotNull List<Repeticion> repeticiones) {
    
    public DatosVerSet(Set set) {
        this(set.getId(),
                set.getPeso(),
                set.getSeries(),
                set.getEjercicio().getNombre(),
                set.getRepeticiones());
    }

}
