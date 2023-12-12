/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.ar.gimnasio.domain.set;

import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 *
 * @author marti
 */
public record DatosRegistroSetCompleto(
        @NotNull Integer peso,
        @NotNull Integer series,
        @NotNull Integer ejercicio_id,
        @NotNull List<RegistroRepeticiones> listaRepeticiones) {
    
    public record RegistroRepeticiones(
            @NotNull Integer cantidad,
            @NotNull Integer numero_serie) {}

}
