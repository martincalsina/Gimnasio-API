/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.ar.gimnasio.domain.set;

import com.ar.gimnasio.domain.repeticion.Repeticion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marti
 */
public record DatosListadoSet(
    @NotNull Integer id,
    @NotNull Integer peso,
    @NotNull Integer series,
    @NotNull Integer ejercicio_id,
    @NotBlank String ejercicio_name,
    @NotNull Integer entrenamiento_id,
    @NotNull List<Repeticion> repeticiones) {
    
    public DatosListadoSet(Set set) {
        this(set.getId(), 
                set.getPeso(),
                set.getSeries(), 
                set.getEjercicio().getId(),
                set.getEjercicio().getNombre(),
                set.getEntrenamiento().getId(),
                set.getRepeticiones());
    }

}
