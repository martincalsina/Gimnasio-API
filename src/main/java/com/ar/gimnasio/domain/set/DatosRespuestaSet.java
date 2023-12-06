/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.ar.gimnasio.domain.set;

import jakarta.validation.constraints.NotNull;

/**
 *
 * @author marti
 */
public record DatosRespuestaSet(
        @NotNull Integer id,
        @NotNull Integer peso,
        @NotNull Integer series,
        @NotNull Integer ejercicio_id,
        @NotNull Integer entrenamiento_id) {
    
    public DatosRespuestaSet(Set set) {
        this(set.getId(), set.getPeso(), set.getSeries(), set.getEjercicio().getId(), set.getEntrenamiento().getId());
    }

}
