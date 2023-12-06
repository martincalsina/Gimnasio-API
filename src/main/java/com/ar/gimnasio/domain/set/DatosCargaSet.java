/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.ar.gimnasio.domain.set;

import com.ar.gimnasio.domain.ejercicio.Ejercicio;
import com.ar.gimnasio.domain.entrenamiento.Entrenamiento;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author marti
 */
public record DatosCargaSet(
        @NotNull Integer peso,
        @NotNull Integer series,
        @NotNull Ejercicio ejercicio,
        @NotNull Entrenamiento entrenamiento
        ) {

}
