/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ar.gimnasio.controller;

import com.ar.gimnasio.domain.entrenamiento.DatosBuscarEntrenamiento;
import com.ar.gimnasio.domain.entrenamiento.DatosCargaEntrenamiento;
import com.ar.gimnasio.domain.entrenamiento.DatosEntrenamientoDeUsuario;
import com.ar.gimnasio.domain.entrenamiento.DatosRegistroEntrenamiento;
import com.ar.gimnasio.domain.entrenamiento.DatosRespuestaEntrenamiento;
import com.ar.gimnasio.domain.entrenamiento.DatosVerEntrenamiento;
import com.ar.gimnasio.domain.entrenamiento.Entrenamiento;
import com.ar.gimnasio.domain.entrenamiento.EntrenamientoRepository;
import com.ar.gimnasio.domain.rutina.Rutina;
import com.ar.gimnasio.domain.rutina.RutinaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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

/*

TENGO CASI TODO CONECTADO CON TODO, FALTA:

-CONECTAR ESTE REPO CON EL DE rutina
-CONECTA rutina CON PERSONA
-ASEGURARSE DE QUE TODAS LAS RELACIONES SON BIDIRECCIONALES
-PROBAR TODO

*/

@RestController
@RequestMapping("/entrenamiento")
public class EntrenamientoController {
    
    @Autowired
    private EntrenamientoRepository entrenamientoRepository;
    @Autowired
    private RutinaRepository rutinaRepository;
    
    @Transactional
    @PostMapping("/crear")
    public ResponseEntity<DatosRespuestaEntrenamiento> registrarEntrenamineto(@RequestBody @Valid DatosRegistroEntrenamiento datos, 
            UriComponentsBuilder uriComponentsBuilder) {
        
        Rutina rutina = rutinaRepository.getReferenceById(datos.rutina_id());
        
        DatosCargaEntrenamiento datosCarga = new DatosCargaEntrenamiento(datos.fecha(), rutina);
        
        Entrenamiento entrenamiento = new Entrenamiento(datosCarga);
        
        entrenamiento = entrenamientoRepository.save(entrenamiento);
        
        rutina.getEntrenamientos().add(entrenamiento);
        
        DatosRespuestaEntrenamiento dre = new DatosRespuestaEntrenamiento(entrenamiento);
        
        return ResponseEntity.ok(dre);
    }
    
    @GetMapping("/ver")
    public ResponseEntity<DatosVerEntrenamiento> buscarEntrenamientoPorId(@RequestBody @Valid DatosBuscarEntrenamiento datos) {
        
        Entrenamiento entrenamiento = entrenamientoRepository.getReferenceById(datos.id());
        
        DatosVerEntrenamiento dve = new DatosVerEntrenamiento(entrenamiento);
        
        return ResponseEntity.ok(dve);
        
    }
    
    @GetMapping("/listar/persona_id/{persona_id}")
    public ResponseEntity<List<DatosEntrenamientoDeUsuario>> listarEntrenamientosDePersona(@PathVariable Integer persona_id) {
            
        List<Rutina> rutinas = rutinaRepository.findByPersonaId(persona_id);      
        
        List<Entrenamiento> entrenamientos = new ArrayList<>();
        
        for (Rutina rutina: rutinas) {
            
            Integer rutina_id = rutina.getId();
            
            List<Entrenamiento> entrenamientos_de_rutina = entrenamientoRepository.findByRutinaId(rutina_id);
            
            entrenamientos.addAll(entrenamientos_de_rutina);
            
        }

        
        List<DatosEntrenamientoDeUsuario> listaDedu = new ArrayList<>();
        
        for (Entrenamiento entrenamiento: entrenamientos) {
            
            DatosEntrenamientoDeUsuario dedu = new DatosEntrenamientoDeUsuario(entrenamiento);
            
            listaDedu.add(dedu);
            
        }
        
        return ResponseEntity.ok(listaDedu);
    }
    
    
}
