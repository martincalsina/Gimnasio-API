/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  marti
 * Created: 29 oct 2023
 */

CREATE TABLE ENTRENAMIENTO(
ID BIGINT NOT NULL AUTO_INCREMENT,
FECHA TIMESTAMP NOT NULL,
RUTINA_ID BIGINT NOT NULL,
PRIMARY KEY(ID),
CONSTRAINT fk_entrenamiento_rutina_id FOREIGN KEY(RUTINA_ID) REFERENCES rutina(ID)
)