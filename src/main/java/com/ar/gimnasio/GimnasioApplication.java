package com.ar.gimnasio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/*
MUCHISIMOS AVANCES:

- Ya tengo todas las entidades con sus controllers con las operaciones de crear y ver
- Todas las relaciones son bidireccionales, conseguí que funcione el serializado al hacer un GET con
notaciones JsonManagedReference y JsonBackReference
- Hay una banda de Records
- Hay que agregar las operaciones necesarias de DELETE y UPDATE
- Hay que modificar las relaciones para que sean de tipo LAZY y no se traiga todo de golpe

*/
@SpringBootApplication
public class GimnasioApplication {

	public static void main(String[] args) {
		SpringApplication.run(GimnasioApplication.class, args);
	}

}
