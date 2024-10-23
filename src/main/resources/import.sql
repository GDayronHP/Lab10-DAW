
INSERT INTO curso (vchCurNombre,intCurCreditos) VALUES('Programmer', 5);
INSERT INTO curso (vchCurNombre,intCurCreditos) VALUES('Developer', 5);
INSERT INTO curso (vchCurNombre,intCurCreditos) VALUES('Expert', 5);

INSERT INTO curso (vchCurNombre,intCurCreditos) VALUES('Programmer', 5);
INSERT INTO curso (vchCurNombre,intCurCreditos) VALUES('Developer', 5);
INSERT INTO curso (vchCurNombre,intCurCreditos) VALUES('Expert', 5);

INSERT INTO `escuela`.`alumnos` (`fecha_nacimiento`, `id`, `apellidos`, `nombres`, `sexo`) VALUES ('1995/07/20', '1', 'González', 'María', 'Femenino');

INSERT INTO `escuela`.`alumnos` (`fecha_nacimiento`, `id`, `apellidos`, `nombres`, `sexo`) VALUES ('1990/03/14', '2', 'Fernández', 'Carlos', 'Masculino');

INSERT INTO `escuela`.`alumnos` (`fecha_nacimiento`, `id`, `apellidos`, `nombres`, `sexo`) VALUES ('1998/11/30', '3', 'Martínez', 'Lucía', 'Femenino');


/* Creamos algunos usuarios con sus roles */
/*2.- el password encriptado lo pasamos aqui*/
INSERT INTO `users` (username, password, enabled) VALUES ('jhuayra','$2a$10$q21RwpZsbAVK6As6w8jTPODG8qdPkWGCZkDeYhaMxrCRn6V/0exE6',1);
INSERT INTO `users` (username, password, enabled) VALUES ('admin','$2a$10$RqfC6a6UUnn1.fovfW7yD.ZuIMXVxoAXZWsi3Ve3xcsZUdujztQGa',1);

INSERT INTO `authorities` (userId, authority) VALUES (1,'ROLE_USER');
INSERT INTO `authorities` (userId, authority) VALUES (2,'ROLE_ADMIN');
INSERT INTO `authorities` (userId, authority) VALUES (2,'ROLE_USER');
















