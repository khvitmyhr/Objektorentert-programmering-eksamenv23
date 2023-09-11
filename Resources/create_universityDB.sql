DROP SCHEMA IF EXISTS universityDB;

CREATE DATABASE universityDB;

USE universityDB;



CREATE TABLE IF NOT EXISTS program(
                                           program_id INT NOT NULL AUTO_INCREMENT,
                                           name VARCHAR(45) NOT NULL,
                                           staff_id INT,
                                           primary key (program_id)
);

CREATE TABLE IF NOT EXISTS student(
                                      student_id INT NOT NULL AUTO_INCREMENT,
                                      name VARCHAR(45) NOT NULL,
                                      program_id INT,
                                      program_name VARCHAR(45),
                                      primary key (student_id),
                                      FOREIGN KEY (program_id)
                                          REFERENCES program(program_id)
);

CREATE TABLE IF NOT EXISTS staff(
                                         id INT NOT NULL AUTO_INCREMENT,
                                         teacher VARCHAR(45) NOT NULL,
                                         program_responisble VARCHAR(45) NOT NULL,
                                         program_id INT,
                                         primary key (id),
                                             FOREIGN KEY (program_id)
                                             REFERENCES program(program_id)
);