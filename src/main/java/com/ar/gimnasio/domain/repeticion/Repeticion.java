/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ar.gimnasio.domain.repeticion;

import com.ar.gimnasio.domain.set.Set;
import com.ar.gimnasio.domain.set.SetRepository;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author marti
 */

@Entity
@Table(name="repeticiones")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Repeticion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer cantidad;
    private Integer numero_serie;
    
    @ManyToOne
    @JoinColumn(name = "set_id")
    @JsonBackReference
    private Set set;
    
    public Repeticion(DatosCargaRepeticion dcp) {
        this.cantidad = dcp.cantidad();
        this.numero_serie = dcp.numero_serie();
        this.set = dcp.set();
    }
    
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    
}
