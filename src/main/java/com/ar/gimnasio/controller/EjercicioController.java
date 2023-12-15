/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ar.gimnasio.controller;

import com.ar.gimnasio.domain.ejercicio.DatoHistoricoEj;
import com.ar.gimnasio.domain.ejercicio.DatosBorradoEjercicio;
import com.ar.gimnasio.domain.ejercicio.DatosHistoricosEjercicio;
import com.ar.gimnasio.domain.ejercicio.DatosListadoEjercicio;
import com.ar.gimnasio.domain.ejercicio.DatosRegistroEjercicio;
import com.ar.gimnasio.domain.ejercicio.DatosRespuestaEjercicio;
import com.ar.gimnasio.domain.ejercicio.Ejercicio;
import com.ar.gimnasio.domain.ejercicio.EjercicioRepository;
import com.ar.gimnasio.domain.entrenamiento.Entrenamiento;
import com.ar.gimnasio.domain.persona.Persona;
import com.ar.gimnasio.domain.persona.PersonaRepository;
import com.ar.gimnasio.domain.rutina.Rutina;
import com.ar.gimnasio.domain.set.Set;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    
    @Autowired
    private PersonaRepository personaRepository;
    
    @Transactional
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
    public ResponseEntity<Page<DatosListadoEjercicio>> listadoEjercicios(@PageableDefault(size = 50) Pageable paginacion) {
        return ResponseEntity.ok(ejercicioRepository.findAll(paginacion).map(DatosListadoEjercicio::new));
    
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaEjercicio> buscarEjercicioPorId(@PathVariable Integer id) {
        Ejercicio ejercicio = ejercicioRepository.getReferenceById(id);
        var datosEjercicio = new DatosRespuestaEjercicio(ejercicio);
        return ResponseEntity.ok(datosEjercicio);
    }
    
    @GetMapping("/datosHistoricos/{persona_id}/{ejercicio_id}")
    public ResponseEntity<DatosHistoricosEjercicio> datosHistoricosEjercicio(@PathVariable Integer ejercicio_id,
            @PathVariable Integer persona_id) {
        
        Persona persona = personaRepository.findById(persona_id).get();
        Ejercicio ejercicio = ejercicioRepository.getReferenceById(ejercicio_id);
        
        List<DatoHistoricoEj> listaDatosHistoricos = this.obtenerDatosHistoricos(persona, ejercicio);
        
        DatosHistoricosEjercicio dhe = new DatosHistoricosEjercicio(ejercicio, listaDatosHistoricos);
        
        return ResponseEntity.ok(dhe);
    }
    
    private List<DatoHistoricoEj> obtenerDatosHistoricos(Persona persona, Ejercicio ejercicio) {
        
        List<Rutina> rutinas = persona.getRutinas();
        
        List<DatoHistoricoEj> setsConEjercicio = new LinkedList<>();
        
        for (Rutina rutina: rutinas) {
            
            List<Entrenamiento> entrenamientos = rutina.getEntrenamientos();
            
            for (Entrenamiento entrenamiento: entrenamientos) {
                
                LocalDate fecha = entrenamiento.getFecha();
                
                List<Set> sets = entrenamiento.getSets();
                
                List<DatoHistoricoEj> setsUtiles = this.obtenerSetsUtiles(sets, ejercicio, fecha);
                
                setsConEjercicio.addAll(setsUtiles);
                
            }
            
        }
        
        return setsConEjercicio;
    }
    
    private List<DatoHistoricoEj> obtenerSetsUtiles(List<Set> sets, Ejercicio ejercicio, LocalDate fecha) {
        
        List<DatoHistoricoEj> setsUtiles = new LinkedList<>();
        
        for (Set set: sets) {
            
            if (set.getEjercicio().equals(ejercicio)) {
                
                DatoHistoricoEj datoUtil = new DatoHistoricoEj(fecha, set);
                
                setsUtiles.add(datoUtil);
            }
            
        }
        
        return setsUtiles;
        
    }

    @DeleteMapping 
    public ResponseEntity<DatosRespuestaEjercicio> eliminarEjercicioPorId(@RequestBody @Valid DatosBorradoEjercicio datos) {
        
        Ejercicio ejercicio = ejercicioRepository.getReferenceById(datos.id());
        ejercicioRepository.delete(ejercicio);
        var datosEjercicio = new DatosRespuestaEjercicio(ejercicio);
        return ResponseEntity.ok(datosEjercicio);
        
    }
    
}
