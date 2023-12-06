package com.ar.gimnasio.domain.set;


import com.ar.gimnasio.domain.ejercicio.Ejercicio;
import com.ar.gimnasio.domain.entrenamiento.Entrenamiento;
import com.ar.gimnasio.domain.repeticion.Repeticion;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author marti
 */

@Entity
@Table(name="set_ejercicio")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Set {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer peso;
    private Integer series;
    
    @ManyToOne
    @JoinColumn(name = "ejercicio_id")
    private Ejercicio ejercicio;
    
    @ManyToOne
    @JoinColumn(name = "entrenamiento_id")
    @JsonBackReference
    private Entrenamiento entrenamiento;
    
    @OneToMany(mappedBy = "set", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Repeticion> repeticiones;
    
    public Set(DatosCargaSet dcs) {
        this.peso=dcs.peso();
        this.series=dcs.series();
        this.ejercicio=dcs.ejercicio();
        this.entrenamiento=dcs.entrenamiento();
        this.repeticiones = new ArrayList<>();
    }
    
}
