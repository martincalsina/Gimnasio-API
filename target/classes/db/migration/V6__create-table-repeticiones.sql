/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  marti
 * Created: 1 dic 2023
 */

CREATE TABLE repeticiones(
ID BIGINT NOT NULL AUTO_INCREMENT,
SET_ID BIGINT NOT NULL,
PRIMARY KEY(ID),
CONSTRAINT fk_set_set_id FOREIGN KEY(SET_ID) REFERENCES `SET`(ID)
)
