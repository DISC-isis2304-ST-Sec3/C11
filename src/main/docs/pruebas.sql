--Pruebas

--1.Unicidad PK

Insert into TIPOSDEUSUARIO (ID,NOMBRE) values ('5','Asociado');

--2. Integridad con FK

Insert into USUARIOS (ID,NOMBREUSUARIO, CONTRASENA, NOMBRE, NUMDOCUMENTO,EMAIL,TIPODOCUMENTO,TIPOSDEUSUARIO_ID) 
                        values ('20','edizkan','455792','Jane Doe', '778899663','janedoe@outlook.es','Cedula','6');

--3. Restricciones

Insert into tiposhabitaciones (ID,NOMBRE, CAPACIDAD) values ('30','Suite doble','9');