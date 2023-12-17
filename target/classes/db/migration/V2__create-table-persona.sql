/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  marti
 * Created: 29 oct 2023
 */

CREATE TABLE PERSONA(
ID BIGINT NOT NULL AUTO_INCREMENT,
CORREO VARCHAR(150) UNIQUE NOT NULL,
PASSWORD VARCHAR(150) NOT NULL,
NOMBRE VARCHAR(100) NOT NULL,
APELLIDO VARCHAR(100) NOT NULL,
PRIMARY KEY(ID)
)