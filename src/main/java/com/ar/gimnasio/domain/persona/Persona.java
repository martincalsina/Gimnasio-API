/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ar.gimnasio.domain.persona;

import com.ar.gimnasio.domain.rutina.Rutina;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author marti
 */

@Entity
@Table(name="persona")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Persona {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String correo;
    private String password;
    private String nombre;
    private String apellido;
    
    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.LAZY)
    @JsonManagedReference
    private List<Rutina> rutinas;
    
    public Persona(DatosRegistroPersona drs) {
        this.correo = drs.correo();
        this.password = drs.password();
        this.nombre = drs.nombre();
        this.apellido = drs.apellido();
        this.rutinas = new ArrayList<>();
    }
    
}
