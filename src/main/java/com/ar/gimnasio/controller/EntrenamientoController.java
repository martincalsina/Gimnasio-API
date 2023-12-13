/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ar.gimnasio.controller;

import com.ar.gimnasio.domain.ejercicio.Ejercicio;
import com.ar.gimnasio.domain.ejercicio.EjercicioRepository;
import com.ar.gimnasio.domain.entrenamiento.DatosBuscarEntrenamiento;
import com.ar.gimnasio.domain.entrenamiento.DatosCargaEntrenamiento;
import com.ar.gimnasio.domain.entrenamiento.DatosEntrenamientoDeUsuario;
import com.ar.gimnasio.domain.entrenamiento.DatosRegistroEntrenamiento;
import com.ar.gimnasio.domain.entrenamiento.DatosRegistroEntrenamientoCompleto;
import com.ar.gimnasio.domain.entrenamiento.DatosRespuestaEntrenamiento;
import com.ar.gimnasio.domain.entrenamiento.DatosVerEntrenamiento;
import com.ar.gimnasio.domain.entrenamiento.Entrenamiento;
import com.ar.gimnasio.domain.entrenamiento.EntrenamientoRepository;
import com.ar.gimnasio.domain.repeticion.DatosCargaRepeticion;
import com.ar.gimnasio.domain.repeticion.Repeticion;
import com.ar.gimnasio.domain.repeticion.RepeticionesRepository;
import com.ar.gimnasio.domain.rutina.Rutina;
import com.ar.gimnasio.domain.rutina.RutinaRepository;
import com.ar.gimnasio.domain.set.DatosCargaSet;
import com.ar.gimnasio.domain.set.DatosRegistroSetCompleto;
import com.ar.gimnasio.domain.set.DatosRegistroSetCompleto.RegistroRepeticiones;
import com.ar.gimnasio.domain.set.Set;
import com.ar.gimnasio.domain.set.SetRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private RepeticionesRepository repeticionesRepository;
    @Autowired
    private EjercicioRepository ejercicioRepository;
    @Autowired
    private SetRepository setRepository;
    
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
    
    @Transactional
    @PostMapping("/crear/completo")
    public ResponseEntity<DatosRespuestaEntrenamiento> registrarEntrenamiento(@RequestBody @Valid DatosRegistroEntrenamientoCompleto datos,
            UriComponentsBuilder uriComponentsBuilder) {
        
        Rutina rutina = rutinaRepository.getReferenceById(datos.rutina_id());
        
        DatosCargaEntrenamiento dce = new DatosCargaEntrenamiento(datos.fecha(), rutina);
        
        Entrenamiento entrenamiento = new Entrenamiento(dce);
        
        entrenamiento = entrenamientoRepository.save(entrenamiento);
        
        System.out.println("LLEGUÉ A CREAR EL ENTRENAMIENTO");
        
        for (DatosRegistroSetCompleto drsc : datos.sets()) {
            
            Ejercicio ejercicioDeSet = ejercicioRepository.getReferenceById(drsc.ejercicio_id());
            
            DatosCargaSet dcs = new DatosCargaSet(
                    drsc.peso(), 
                    drsc.series(), 
                    ejercicioDeSet,
                    entrenamiento
            );
            
            Set nuevoSet = new Set(dcs);
            
            nuevoSet = setRepository.save(nuevoSet);
            
            System.out.println("LLEGUÉ A CREAR UN SET");
            
            List<RegistroRepeticiones> listaRr = drsc.listaRepeticiones();
            
            for (RegistroRepeticiones datosRep: listaRr) {
                
                DatosCargaRepeticion dcr = new DatosCargaRepeticion(
                        datosRep.cantidad(),
                        datosRep.numero_serie(),
                        nuevoSet
                );
                
                Repeticion nuevaRepeticion = new Repeticion(dcr);
                
                nuevaRepeticion = repeticionesRepository.save(nuevaRepeticion);
                
                System.out.println("LLEGUÉ A CREAR UNA REPETICION");
                
                nuevoSet.getRepeticiones().add(nuevaRepeticion);
                
            }
            
            entrenamiento.getSets().add(nuevoSet);
            
        }
        
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
    
    @DeleteMapping("/borrar/{entrenamiento_id}")
    public ResponseEntity<Map<String, String>> borrarRutinaPorId(@PathVariable Integer entrenamiento_id) {
              
        entrenamientoRepository.deleteById(entrenamiento_id);
        
        Map<String, String> response = new HashMap<>();
        response.put("message", "Entrenamiento " + entrenamiento_id.toString() + " borrado");
        
        return ResponseEntity.ok(response);
    }
    
    
}
