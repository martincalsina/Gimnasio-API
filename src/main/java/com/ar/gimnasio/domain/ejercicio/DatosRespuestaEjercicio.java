/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.ar.gimnasio.domain.ejercicio;

/**
 *
 * @author marti
 */
public record DatosRespuestaEjercicio(
        Integer id,
        String nombre) {
    
    public DatosRespuestaEjercicio(Ejercicio ejercicio) {
        this(ejercicio.getId(), ejercicio.getNombre());
    }

}
