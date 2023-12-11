/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ar.gimnasio.domain.rutina;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author marti
 */

@Repository
public interface RutinaRepository extends JpaRepository<Rutina, Integer>{
    
    List<Rutina> findByPersonaId(Integer personaId);
    
}
