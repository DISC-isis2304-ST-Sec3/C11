Insert into TIPOSDEUSUARIO (ID,NOMBRE,PERMISOS) values (tiposusuariossecuencia.nextval,'Cliente','GH');
Insert into TIPOSDEUSUARIO (ID,NOMBRE,PERMISOS) values (tiposusuariossecuencia.nextval ,'Recepcionista','JST');
Insert into TIPOSDEUSUARIO (ID,NOMBRE,PERMISOS) values (tiposusuariossecuencia.nextval,'Empleado','I');
Insert into TIPOSDEUSUARIO (ID,NOMBRE, PERMISOS) values (tiposusuariossecuencia.nextval,'Administrador','ABCDEFKLMNOPQR');
Insert into TIPOSDEUSUARIO (ID,NOMBRE, PERMISOS) values (tiposusuariossecuencia.nextval,'Gerente','ABCDEFGHIJKLMNOPQRSTUVW');

Insert into USUARIOS (ID,nombreUsuario,CONTRASENA,NOMBRE,NUMDOCUMENTO,EMAIL,TIPODOCUMENTO,TIPOSDEUSUARIO_ID)
                     values (usuariossecuencia.nextval,'carvafel','261107','Felipe Carvajal','1000795803','carvafel@outlook.es','Cedula','1');
                     
Insert into USUARIOS (ID,nombreUsuario,CONTRASENA,NOMBRE,NUMDOCUMENTO,EMAIL,TIPODOCUMENTO,TIPOSDEUSUARIO_ID)
                     values (usuariossecuencia.nextval,'elfelo','45687','Manuel Parra','4592156782','mparra@gmail.com','Cedula','1');
                     
Insert into USUARIOS (ID,nombreUsuario,CONTRASENA,NOMBRE,NUMDOCUMENTO,EMAIL,TIPODOCUMENTO,TIPOSDEUSUARIO_ID)
                     values (usuariossecuencia.nextval,'m.pacheco','lun4568','Manuela Pacheco','798524682','m.pacheco@outlook.com','Cedula','1');
                     
Insert into USUARIOS (ID,nombreUsuario,CONTRASENA,NOMBRE,NUMDOCUMENTO,EMAIL,TIPODOCUMENTO,TIPOSDEUSUARIO_ID)
                     values (usuariossecuencia.nextval,'casanchez','gudMn7896','Camilo Sanchez','1000556248','camilo1999@gmail.com','Cedula','1');
                                       
Insert into USUARIOS (ID,nombreUsuario,CONTRASENA,NOMBRE,NUMDOCUMENTO,EMAIL,TIPODOCUMENTO,TIPOSDEUSUARIO_ID)
                     values (usuariossecuencia.nextval,'wvargas','sistrans99','Wilfredy Vargas','435938','w.vargas9@sonesta.com','Cedula','5');     
                                        
Insert into USUARIOS (ID,nombreUsuario,CONTRASENA,NOMBRE,NUMDOCUMENTO,EMAIL,TIPODOCUMENTO,TIPOSDEUSUARIO_ID)
                     values (usuariossecuencia.nextval,'nruiz','mkU456','Nicolas Ruiz','100098752','n.ruiz@sonesta.com','Cedula','4');     
                                           
 Insert into USUARIOS (ID,nombreUsuario,CONTRASENA,NOMBRE,NUMDOCUMENTO,EMAIL,TIPODOCUMENTO,TIPOSDEUSUARIO_ID)
                     values (usuariossecuencia.nextval,'nportilla','ale8963','Natalia Portilla','1025698716','n.portila@sonesta.com','Cedula','2');                     
                     
 Insert into USUARIOS (ID,nombreUsuario,CONTRASENA,NOMBRE,NUMDOCUMENTO,EMAIL,TIPODOCUMENTO,TIPOSDEUSUARIO_ID)
                     values (usuariossecuencia.nextval,'jlopz','tef591712','Juanita Lopez','10005687892','j.lopz@sonesta.com','Cedula','2');             
                     
 Insert into USUARIOS (ID,nombreUsuario,CONTRASENA,NOMBRE,NUMDOCUMENTO,EMAIL,TIPODOCUMENTO,TIPOSDEUSUARIO_ID)
                     values (usuariossecuencia.nextval,'gabisadick','lau7826','Gabriel Yepes','100889966','gxbx@sonesta.com','Cedula', '3');
                     
 Insert into USUARIOS (ID,nombreUsuario,CONTRASENA,NOMBRE,NUMDOCUMENTO,EMAIL,TIPODOCUMENTO,TIPOSDEUSUARIO_ID)
                     values (usuariossecuencia.nextval,'sebachos','almoj78962','Sebastian Avellaneda','45893213','sebachos@sonesta.com','Cedula', '3');
                     
 Insert into USUARIOS (ID,nombreUsuario,CONTRASENA,NOMBRE,NUMDOCUMENTO,EMAIL,TIPODOCUMENTO,TIPOSDEUSUARIO_ID)
                     values (usuariossecuencia.nextval,'hmalt','seth1408','Cristian Torres','123896489','cristiahm@sonesta.com','Cedula', '3');
                     
Insert into USUARIOS (ID,nombreUsuario,CONTRASENA,NOMBRE,NUMDOCUMENTO,EMAIL,TIPODOCUMENTO,TIPOSDEUSUARIO_ID)
                     values (usuariossecuencia.nextval,'llanitos','llanitos','Alejandro Llanos','666666666','llanitos@gmail.com','Cedula', '5');
                     
INSERT INTO USUARIOS (ID, nombreUsuario, CONTRASENA, NOMBRE, NUMDOCUMENTO, EMAIL, TIPODOCUMENTO, TIPOSDEUSUARIO_ID)
    VALUES (usuariossecuencia.nextval, 'leleor', 'malemur7895', 'Alejandra Orozco', '789523382', 'orozoclele@sonesta.com', 'Cedula', '3');
                  
                     
 Insert into TIPOSHABITACIONES (NOMBRE,CAPACIDAD,ID) values ('Suite presidencial','8',tiposhabitacionessecuencia.nextval);
 
 Insert into TIPOSHABITACIONES (NOMBRE,CAPACIDAD,ID) values ('Doble','3',tiposhabitacionessecuencia.nextval);
 
 Insert into TIPOSHABITACIONES (NOMBRE,CAPACIDAD,ID) values ('Sencilla','2',tiposhabitacionessecuencia.nextval); 
 
 
 Insert into HABITACIONES (ID,CAPACIDAD,COSTOALOJAMIENTO,NUMERO,TIPOSHABITACIONES_ID) 
                            values (habitacionessecuencia.nextval,'8','2500000','5','2'); 
 
 Insert into HABITACIONES (ID,CAPACIDAD,COSTOALOJAMIENTO,NUMERO,TIPOSHABITACIONES_ID) 
                            values (habitacionessecuencia.nextval,'8','2500000','2','2'); 
                            
 Insert into PLANESDECONSUMO (DESCRIPCION,DESCUENTO,NOMBRE,id) 
                                values ('genera un descuento (%) en el costo del alojamiento para estadías mayores a 7 noches',
                                        '10','Larga estadía',planesdeconsumosecuencia.nextval); 
                                        
 Insert into PLANESDECONSUMO (DESCRIPCION,DESCUENTO,NOMBRE,id) 
                                values ('tiene también un porcentaje de descuento en los consumos de bar y restaurante y en algunos servicios ',
                                        '20','Tiempo compartido',planesdeconsumosecuencia.nextval); 
      

                           