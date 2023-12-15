/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.ar.gimnasio.domain.ejercicio;

import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 *
 * @author marti
 */
public record DatosHistoricosEjercicio(
        @NotNull Integer ejercicio_id,
        @NotNull String ejercicio_nombre,
        @NotNull List<DatoHistoricoEj> datosHistoricos) {
    
    public DatosHistoricosEjercicio(Ejercicio ejercicio, List<DatoHistoricoEj> datosHistoricos) {
        this(ejercicio.getId(), ejercicio.getNombre(), datosHistoricos);
    }

}
