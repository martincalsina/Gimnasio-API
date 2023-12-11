/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ar.gimnasio.controller;

import com.ar.gimnasio.domain.ejercicio.DatosListadoEjercicio;
import com.ar.gimnasio.domain.ejercicio.Ejercicio;
import com.ar.gimnasio.domain.ejercicio.EjercicioRepository;
import com.ar.gimnasio.domain.entrenamiento.Entrenamiento;
import com.ar.gimnasio.domain.entrenamiento.EntrenamientoRepository;
import com.ar.gimnasio.domain.set.DatosBuscarSet;
import com.ar.gimnasio.domain.set.DatosCargaSet;
import com.ar.gimnasio.domain.set.DatosListadoSet;
import com.ar.gimnasio.domain.set.DatosRegistroSet;
import com.ar.gimnasio.domain.set.DatosRespuestaSet;
import com.ar.gimnasio.domain.set.DatosVerSet;
import com.ar.gimnasio.domain.set.Set;
import com.ar.gimnasio.domain.set.SetRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author marti
 */
@RestController
@RequestMapping("/set")
public class SetController {
    
    @Autowired
    private SetRepository setRepository;
    @Autowired
    private EjercicioRepository ejercicioRepository;
    @Autowired
    private EntrenamientoRepository entrenamientoRepository;
    
    @Transactional
    @PostMapping("/crear")
    public ResponseEntity<DatosRespuestaSet> registrarSet(@RequestBody @Valid DatosRegistroSet datos, UriComponentsBuilder uriComponentsBuilder) {
        
        Ejercicio ejercicio = ejercicioRepository.getReferenceById(datos.ejercicio_id());
        Entrenamiento entrenamiento = entrenamientoRepository.getReferenceById(datos.entrenamiento_id());
        
        DatosCargaSet dcs = new DatosCargaSet(datos.peso(),datos.series(), ejercicio, entrenamiento);
        
        Set set = setRepository.save(new Set(dcs));
        
        entrenamiento.getSets().add(set);
        
        DatosRespuestaSet datosRespuestaSet = new DatosRespuestaSet(set);
        
        URI url = uriComponentsBuilder.path("/set/{id}").buildAndExpand(set.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaSet);
    }
    
    @GetMapping("/ver")
    public ResponseEntity<DatosVerSet> buscarSetPorId(@RequestBody @Valid DatosBuscarSet datos) {
        
        Set set = setRepository.getReferenceById(datos.id());
        
        DatosVerSet dvs = new DatosVerSet(set);
        
        return ResponseEntity.ok(dvs);
        
    }
    
    @GetMapping("/listar")
    public ResponseEntity<Page<DatosListadoSet>> listarSets(@PageableDefault(size = 10) Pageable paginacion) {
        return ResponseEntity.ok(setRepository.findAll(paginacion).map(DatosListadoSet::new));
    }
    
    @GetMapping("listar/entrenamiento_id/{entrenamiento_id}")
    public ResponseEntity<List<DatosListadoSet>> listarSetsDeEntrenamiento(@PathVariable Integer entrenamiento_id) {
        
        List<Set> sets = setRepository.findByEntrenamientoId(entrenamiento_id);
        
        List<DatosListadoSet> listaDls = new ArrayList<>();
        
        for (Set set: sets) {
            
            DatosListadoSet dls = new DatosListadoSet(set);
            
            listaDls.add(dls);
            
        }
        
        return ResponseEntity.ok(listaDls);
        
    }
    
}
