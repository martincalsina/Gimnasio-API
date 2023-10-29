/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ar.gimnasio.controller;

import com.ar.gimnasio.domain.ejercicio.DatosListadoEjercicio;
import com.ar.gimnasio.domain.ejercicio.DatosRegistroEjercicio;
import com.ar.gimnasio.domain.ejercicio.DatosRespuestaEjercicio;
import com.ar.gimnasio.domain.ejercicio.Ejercicio;
import com.ar.gimnasio.domain.ejercicio.EjercicioRepository;
import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author marti
 */

@RestController
@RequestMapping("/ejercicio")
public class EjercicioController {
    
    
    @Autowired
    private EjercicioRepository ejercicioRepository;
    
    @PostMapping
    public ResponseEntity<DatosRespuestaEjercicio> registrarEjercicio(@RequestBody @Valid DatosRegistroEjercicio datos, UriComponentsBuilder uriComponentsBuilder) {
        
        Ejercicio ejercicio = ejercicioRepository.save(new Ejercicio(datos));
        DatosRespuestaEjercicio datosRespuestaEjercicio = new DatosRespuestaEjercicio(
                ejercicio.getId(),
                ejercicio.getNombre());
        
        URI url = uriComponentsBuilder.path("/ejercicio/{id}").buildAndExpand(ejercicio.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaEjercicio);
        
        
    }
    
    @GetMapping
    public ResponseEntity<Page<DatosListadoEjercicio>> listadoEjercicios(@PageableDefault(size = 10) Pageable paginacion) {
        return ResponseEntity.ok(ejercicioRepository.findAll(paginacion).map(DatosListadoEjercicio::new));
    
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaEjercicio> buscarEjercicioPorId(@PathVariable Integer id) {
        Ejercicio ejercicio = ejercicioRepository.getReferenceById(id);
        var datosEjercicio = new DatosRespuestaEjercicio(ejercicio);
        return ResponseEntity.ok(datosEjercicio);
    }
    
}
