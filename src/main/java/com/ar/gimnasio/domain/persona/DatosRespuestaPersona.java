/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.ar.gimnasio.domain.persona;

import jakarta.validation.constraints.NotBlank;

/**
 *
 * @author marti
 */
public record DatosRespuestaPersona(
        @NotBlank String correo,
        @NotBlank String password,
        @NotBlank String nombre,
        @NotBlank String apellido) {
    
        public DatosRespuestaPersona(Persona persona) {
            this(persona.getCorreo(), persona.getPassword(), 
                    persona.getNombre(), persona.getApellido());
        }

}
