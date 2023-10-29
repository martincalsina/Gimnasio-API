/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ar.gimnasio.domain.ejercicio;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author marti
 */
@Repository
public interface EjercicioRepository extends JpaRepository<Ejercicio, Integer>{
    
    Page<Ejercicio> findAll(Pageable paginacion);
    
}
