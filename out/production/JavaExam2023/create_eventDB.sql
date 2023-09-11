DROP SCHEMA IF EXISTS eventDB;

CREATE DATABASE eventDB;

USE eventDB;

CREATE TABLE IF NOT EXISTS attend(
                                      attendent_id INT NOT NULL AUTO_INCREMENT,
                                      studentName VARCHAR(45) NOT NULL,
                                      program VARCHAR(45) NOT NULL,
                                      guest VARCHAR(45),
                                      primary key (attendent_id)

);

CREATE TABLE IF NOT EXISTS ceremony_program(
                                     ceremony_id INT NOT NULL AUTO_INCREMENT,
                                     program VARCHAR(45) NOT NULL,
                                     program_responsible VARCHAR(45) NOT NULL,
                                     duration INT NOT NULL,
                                     program_responsible_speech INT NOT NULL,
                                     student_speech VARCHAR(45) NOT NULL,
                                     break_between_program INT,
                                     primary key (ceremony_id)

);