/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ar.gimnasio.controller;

import com.ar.gimnasio.domain.repeticion.DatosCargaRepeticion;
import com.ar.gimnasio.domain.repeticion.DatosEdicionRepeticion;
import com.ar.gimnasio.domain.repeticion.DatosRegistroRepeticion;
import com.ar.gimnasio.domain.repeticion.Repeticion;
import com.ar.gimnasio.domain.repeticion.RepeticionesRepository;
import com.ar.gimnasio.domain.set.Set;
import com.ar.gimnasio.domain.set.SetRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author marti
 */
@RestController
@RequestMapping("/repeticiones")
public class RepeticionesContoller {
    
    @Autowired
    private RepeticionesRepository repeticionesRepository;
    @Autowired
    private SetRepository setRepository;
    
    @Transactional
    @PostMapping("/crear")
    public void registrarRepeticion(@RequestBody @Valid DatosRegistroRepeticion datos) {
        
        Set set = setRepository.getReferenceById(datos.set_id());
        
        DatosCargaRepeticion dcr = new DatosCargaRepeticion(datos.cantidad(), datos.numero_serie(), set);
        
        Repeticion repeticion = new Repeticion(dcr);
        
        repeticion = repeticionesRepository.save(repeticion);
        
        set.getRepeticiones().add(repeticion);
    }
    
    @PutMapping("/editar")
    public void editarRepeticion(@RequestBody @Valid DatosEdicionRepeticion datos) {
        Repeticion repeticion = repeticionesRepository.getReferenceById(datos.id());
        repeticion.setCantidad(datos.cantidad());
        repeticionesRepository.save(repeticion);
    }
            

}
