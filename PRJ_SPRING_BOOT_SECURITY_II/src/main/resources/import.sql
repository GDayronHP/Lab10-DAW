
INSERT INTO curso (vchCurNombre,intCurCreditos) VALUES('Programmer', 5);
INSERT INTO curso (vchCurNombre,intCurCreditos) VALUES('Developer', 5);
INSERT INTO curso (vchCurNombre,intCurCreditos) VALUES('Expert', 5);


/* Creamos algunos usuarios con sus roles */
/*2.- el password encriptado lo pasamos aqui*/
INSERT INTO `users` (username, password, enabled) VALUES ('rcoello','$2a$10$q21RwpZsbAVK6As6w8jTPODG8qdPkWGCZkDeYhaMxrCRn6V/0exE6',1);
INSERT INTO `users` (username, password, enabled) VALUES ('admin','$2a$10$RqfC6a6UUnn1.fovfW7yD.ZuIMXVxoAXZWsi3Ve3xcsZUdujztQGa',1);

INSERT INTO `authorities` (userId, authority) VALUES (1,'ROLE_USER');
INSERT INTO `authorities` (userId, authority) VALUES (2,'ROLE_ADMIN');
INSERT INTO `authorities` (userId, authority) VALUES (2,'ROLE_USER');
















