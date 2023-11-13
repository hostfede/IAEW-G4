--Database: iaew

--DROP DATABASE IF EXISTS iaew;

--CREATE DATABASE iaew
  -- WITH
  --OWNER = postgres
-- ENCODING = 'UTF8';
	
	CREATE TABLE Cliente (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    apellido VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    fechavencimiento DATE,
    telefono VARCHAR(20),
    documento VARCHAR(20) UNIQUE NOT NULL
);
	CREATE TABLE PlanPuntos (
    id SERIAL PRIMARY KEY,
    cliente_id INT REFERENCES Cliente(id),
    puntos_acumulados INT,
    nivel VARCHAR(255)
);

