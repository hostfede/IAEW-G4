
-- Eliminar la base de datos si existe
DROP DATABASE IF EXISTS iaew;

-- Crear la base de datos
CREATE DATABASE iaew CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Seleccionar la base de datos recién creada
USE iaew;

-- Crear la tabla Cliente
CREATE TABLE cliente (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         nombre VARCHAR(255) NOT NULL,
                         apellido VARCHAR(255) NOT NULL,
                         email VARCHAR(255) UNIQUE NOT NULL,
                         fecha_nacimiento DATE,
                         telefono VARCHAR(20),
                         documento VARCHAR(20) UNIQUE NOT NULL
);

-- Crear la tabla PlanPuntos
CREATE TABLE plan_puntos (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            cliente_id INT,
                            puntos_acumulados INT,
                            nivel VARCHAR(255),
                            FOREIGN KEY (cliente_id) REFERENCES cliente(id) on delete CASCADE
);


