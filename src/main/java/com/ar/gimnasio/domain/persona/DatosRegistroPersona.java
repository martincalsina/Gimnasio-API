/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.ar.gimnasio.domain.persona;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 *
 * @author marti
 */
public record DatosRegistroPersona(
        @NotBlank @Size(min = 0, max = 150) @Email String correo,
        @NotBlank @Size(min = 8, max = 150) String password,
        @NotBlank @Size(min = 0, max = 150) String nombre,
        @NotBlank @Size(min = 0, max = 150) String apellido
        ) {

}
