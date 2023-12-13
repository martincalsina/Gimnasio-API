/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ar.gimnasio.domain.entrenamiento;

import com.ar.gimnasio.domain.rutina.Rutina;
import com.ar.gimnasio.domain.set.Set;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
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
@Table(name="entrenamiento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Entrenamiento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate fecha;
    
    @OneToMany(mappedBy = "entrenamiento", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Set> sets;
    
    @ManyToOne
    @JoinColumn(name="rutina_id")
    @JsonBackReference
    private Rutina rutina;
    
    
    public Entrenamiento(DatosCargaEntrenamiento dce) {
        this.fecha = dce.fecha();
        this.rutina = dce.rutina();
        this.sets = new ArrayList<>();
    }
    
    public Entrenamiento(Integer entrenamiento_id, DatosCargaEntrenamiento dce) {
        this.id = entrenamiento_id;
        this.fecha = dce.fecha();
        this.rutina = dce.rutina();
    }
    
}
