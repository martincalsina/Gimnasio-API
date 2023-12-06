/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.ar.gimnasio.domain.persona;

import com.ar.gimnasio.domain.rutina.Rutina;
import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 *
 * @author marti
 */
public record DatosVerPersona(
        @NotNull Integer persona_id,
        @NotNull String nombre,
        @NotNull String apellido,
        @NotNull List<Rutina> rutinas) {
    
    public DatosVerPersona(Persona p) {
        this(p.getId(), p.getNombre(), p.getApellido(), p.getRutinas());
    }

}
