CREATE DATABASE bibliotecadb;

USE bibliotecadb;

CREATE TABLE livro (
    id INT AUTO_INCREMENT NOT NULL,
    titulo varchar(100),
    autor varchar(100),
    genero varchar(100),
    primary key(id)
);

CREATE TABLE equipamento(
    id int AUTO_INCREMENT NOT NULL,
    nome varchar(100),
    fabricante varchar(100),
    numero_serie varchar (20),
    primary key (id)
);