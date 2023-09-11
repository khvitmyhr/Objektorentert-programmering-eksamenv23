USE universityDB;

INSERT INTO program (name, staff_id)
VALUES('Programmering', 1);

INSERT INTO program (name, staff_id)
VALUES('Cyber security', 2);

INSERT INTO program (name, staff_id)
VALUES('Interaktivt design', 3);

INSERT INTO program (name, staff_id)
VALUES('Databaser', 4);

INSERT INTO staff (teacher, program_responisble )
VALUES('Odd', 'Leif');

INSERT INTO staff (teacher, program_responisble)
VALUES('Bengt', 'Kjellfrid');

INSERT INTO staff (teacher, program_responisble )
VALUES('Thomas', 'Åshild');

INSERT INTO staff (teacher, program_responisble )
VALUES('Roald', 'Frida');

INSERT INTO student (name, program_id, program_name)
VALUES('Kjell', 1, 'programmering');

INSERT INTO student (name, program_id, program_name)
VALUES('Fredrik', 1, 'programmering');

INSERT INTO student (name, program_id, program_name)
VALUES('Marit', 2, 'cyber security');

INSERT INTO student (name, program_id, program_name)
VALUES('Gunvor', 2, 'cyber security');

INSERT INTO student (name, program_id, program_name)
VALUES('Elisabeth', 3, 'interaktivt design');

INSERT INTO student (name, program_id, program_name)
VALUES('Lise', 3, 'interaktivt design');

INSERT INTO student (name, program_id, program_name)
VALUES('Odd', 4, 'databaser');

INSERT INTO student (name, program_id, program_name)
VALUES('Bjørn', 4, 'databaser');

