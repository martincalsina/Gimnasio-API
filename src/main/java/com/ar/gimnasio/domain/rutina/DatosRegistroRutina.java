/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.ar.gimnasio.domain.rutina;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author marti
 */
public record DatosRegistroRutina(
        @NotNull Integer persona_id,
        @NotNull @Size(min = 1, max = 20) String nombre) {

}
