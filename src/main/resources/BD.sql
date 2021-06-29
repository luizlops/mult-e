CREATE DATABASE mult WITH OWNER = postgres ENCODING = 'UTF8';
USE mult;

CREATE TABLE estado_civil
(
	id SERIAL PRIMARY KEY,
	tipo VARCHAR(100)
);

INSERT INTO estado_civil(tipo) VALUES ('Solteiro(a)'), ('Casado(a)'), ('União Estável'), ('Divorciado(a)'), ('Viúvo(a)');

CREATE TABLE pessoas
(
	id SERIAL PRIMARY KEY,
	nome VARCHAR(200) NOT NULL,
	data_nascimento VARCHAR(20) NOT NULL,
	dependente INT DEFAULT NULL,
	estado_civil INT NOT NULL,
	CONSTRAINT fk_dependente FOREIGN KEY (dependente) REFERENCES pessoas (id),
	CONSTRAINT fk_estado_civil FOREIGN KEY (estado_civil) REFERENCES estado_civil (id)
);