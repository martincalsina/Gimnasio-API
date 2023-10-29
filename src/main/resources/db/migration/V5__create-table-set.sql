/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  marti
 * Created: 29 oct 2023
 */

CREATE TABLE `SET` (
    ID BIGINT NOT NULL AUTO_INCREMENT,
    PESO INT NOT NULL,
    SERIES INT NOT NULL,
    REPETICIONES INT NOT NULL,
    EJERCICIO_ID BIGINT NOT NULL,
    ENTRENAMIENTO_ID BIGINT NOT NULL,
    PRIMARY KEY(ID),
    CONSTRAINT fk_set_ejercicio_id FOREIGN KEY(EJERCICIO_ID) REFERENCES ejercicio(ID),
    CONSTRAINT fk_set_entrenamiento_id FOREIGN KEY(ENTRENAMIENTO_ID) REFERENCES entrenamiento(ID)
);
