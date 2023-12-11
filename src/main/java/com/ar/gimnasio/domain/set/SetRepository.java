/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ar.gimnasio.domain.set;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author marti
 */
@Repository
public interface SetRepository extends JpaRepository<Set, Integer> {
    
    Page<Set> findAll(Pageable paginacion);
    
    List<Set> findByEntrenamientoId(Integer entrenamientoId);
    
}
