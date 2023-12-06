/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.ar.gimnasio.domain.entrenamiento;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 *
 * @author marti
 */
public record DatosRegistroEntrenamiento(
        @NotNull LocalDate fecha,
        @NotNull Integer rutina_id) {

}
