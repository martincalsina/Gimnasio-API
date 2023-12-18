/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.ar.gimnasio.domain.persona;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author marti
 */
public record DatosEditarPersona(
        @NotNull Integer persona_id,
        @NotBlank @Size(max=100) String nombre,
        @NotBlank @Size(max=100) String apellido,
        @NotNull @Email @Size(max=150) String correo,
        @NotNull @Size(min=8, max=150) String password) {
}
