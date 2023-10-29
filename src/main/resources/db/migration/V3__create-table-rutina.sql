/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  marti
 * Created: 29 oct 2023
 */

CREATE TABLE RUTINA(
ID BIGINT NOT NULL AUTO_INCREMENT,
NOMBRE VARCHAR(100) NOT NULL,
PERSONA_ID BIGINT NOT NULL,
PRIMARY KEY(ID),
CONSTRAINT fk_rutina_persona_id FOREIGN KEY(PERSONA_ID) REFERENCES persona(ID) 
)