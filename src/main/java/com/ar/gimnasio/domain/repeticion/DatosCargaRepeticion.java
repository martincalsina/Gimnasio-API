/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.ar.gimnasio.domain.repeticion;

import com.ar.gimnasio.domain.set.Set;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author marti
 */
public record DatosCargaRepeticion(
        @NotNull Integer cantidad,
        @NotNull Integer numero_serie,
        @NotNull Set set) {

}
