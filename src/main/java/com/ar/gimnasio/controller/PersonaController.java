/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ar.gimnasio.controller;

import com.ar.gimnasio.domain.persona.DatosBuscarPersona;
import com.ar.gimnasio.domain.persona.DatosEditarPersona;
import com.ar.gimnasio.domain.persona.DatosLoginPersona;
import com.ar.gimnasio.domain.persona.DatosRegistroPersona;
import com.ar.gimnasio.domain.persona.DatosRespuestaPersona;
import com.ar.gimnasio.domain.persona.DatosVerPersona;
import com.ar.gimnasio.domain.persona.Persona;
import com.ar.gimnasio.domain.persona.PersonaRepository;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author marti
 */

@RestController
@RequestMapping("/persona")
public class PersonaController {
    
    @Autowired
    private PersonaRepository personaRepository;
    
    @PostMapping("/crear")
    public ResponseEntity<DatosRespuestaPersona> registrarPersona(@RequestBody @Valid DatosRegistroPersona datos,
            UriComponentsBuilder uri) {
        
        Persona persona = new Persona(datos);
        
        persona = personaRepository.save(persona);
        
        DatosRespuestaPersona drp = new DatosRespuestaPersona(persona);
        
        return ResponseEntity.ok(drp);
    }
    
    @GetMapping("/ver/{persona_id}")
    public ResponseEntity<DatosVerPersona> buscarPersonaPorId(@PathVariable Integer persona_id) {
        
        Persona persona = personaRepository.getReferenceById(persona_id);
        
        DatosVerPersona dvp = new DatosVerPersona(persona);
        
        return ResponseEntity.ok(dvp);
    }
    
    @GetMapping("/correoDisponible/{correo}")
    public ResponseEntity<Boolean> verificarDisponibilidadCorreo(@PathVariable String correo) {
        boolean disponible = !this.personaRepository.existsByCorreo(correo.toLowerCase());
        return ResponseEntity.ok(disponible);
    }
    
    @PutMapping("/editar")
    public ResponseEntity<DatosVerPersona> editarPersona(@RequestBody @Valid DatosEditarPersona datos) {
        
        Persona personaExistente = personaRepository.getReferenceById(datos.persona_id());
        
        personaExistente.setNombre(datos.nombre());
        personaExistente.setApellido(datos.apellido());
        
        personaExistente = personaRepository.save(personaExistente);
        
        DatosVerPersona dvp = new DatosVerPersona(personaExistente);
        
        return ResponseEntity.ok(dvp);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody DatosLoginPersona datos) {
        
        String correo = datos.correo();
        String password = datos.password();
        
        Persona persona = this.autenticar(correo, password);
        
        if (persona != null) {
            DatosVerPersona dvp = new DatosVerPersona(persona);
            return ResponseEntity.ok(dvp);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
        
    }
    
    public Persona autenticar(String correo, String password) {
        return personaRepository.findByCorreoAndPassword(correo, password);
    }
    
}
