/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ar.gimnasio.controller;

import com.ar.gimnasio.domain.persona.Persona;
import com.ar.gimnasio.domain.persona.PersonaRepository;
import com.ar.gimnasio.domain.rutina.DatosBuscarRutina;
import com.ar.gimnasio.domain.rutina.DatosCargaRutina;
import com.ar.gimnasio.domain.rutina.DatosRegistroRutina;
import com.ar.gimnasio.domain.rutina.DatosRespuestaRutina;
import com.ar.gimnasio.domain.rutina.DatosVerRutina;
import com.ar.gimnasio.domain.rutina.Rutina;
import com.ar.gimnasio.domain.rutina.RutinaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/rutina")
public class RutinaController {
    
    @Autowired
    private RutinaRepository rutinaRepository;
    
    @Autowired
    private PersonaRepository personaRepository;
    
    @Transactional
    @PostMapping("/crear")
    public ResponseEntity<DatosRespuestaRutina> registrarRutina(@RequestBody @Valid DatosRegistroRutina datos, 
            UriComponentsBuilder uriComponentsBuilder) {
        
        Persona persona = personaRepository.getReferenceById(datos.persona_id());
        
        DatosCargaRutina datosCarga = new DatosCargaRutina(datos.nombre(), persona);
        
        Rutina rutina = new Rutina(datosCarga);
        rutina = rutinaRepository.save(rutina);
        
        persona.getRutinas().add(rutina);
        
        DatosRespuestaRutina drr = new DatosRespuestaRutina(rutina);
        
        return ResponseEntity.ok(drr);
    }
    
    @GetMapping("/ver")
    public ResponseEntity<DatosVerRutina> buscarRutinaPorId(@RequestBody @Valid DatosBuscarRutina datos) {
        
        Rutina rutina = rutinaRepository.getReferenceById(datos.id());
        
        DatosVerRutina dvr = new DatosVerRutina(rutina);
        
        return ResponseEntity.ok(dvr);
        
    }
    
}
