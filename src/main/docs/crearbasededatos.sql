
CREATE TABLE consumos (
    sumatotal               INTEGER,
    reservashabitaciones_id INTEGER NOT NULL,
    numconsumos             INTEGER NOT NULL,
    servicios_id    INTEGER NOT NULL,
    id                      INTEGER NOT NULL,
    nombre                  VARCHAR2(255) NOT NULL 
);

ALTER TABLE consumos ADD CONSTRAINT consumos_pk PRIMARY KEY ( id );

CREATE TABLE elementosatipos (
    elementoshab_id      INTEGER NOT NULL,
    tiposhabitaciones_id INTEGER NOT NULL
);

ALTER TABLE elementosatipos ADD CONSTRAINT elematip_pk PRIMARY KEY ( elementoshab_id,
                                                                     tiposhabitaciones_id );

CREATE TABLE elementoshabitaciones (
    id     INTEGER NOT NULL,
    nombre VARCHAR2(55)
);

ALTER TABLE elementoshabitaciones ADD CONSTRAINT elementoshab_pk PRIMARY KEY ( id );

CREATE TABLE habitaciones (
    id                   INTEGER NOT NULL,
    capacidad            INTEGER,
    costoalojamiento     INTEGER,
    numero               INTEGER,
    tiposhabitaciones_id INTEGER NOT NULL
);

ALTER TABLE habitaciones
    ADD CONSTRAINT capacidadpermitida CHECK ( capacidad BETWEEN 0 AND 1000 );

ALTER TABLE habitaciones ADD CHECK ( costoalojamiento BETWEEN 0 AND 100000000000 );

ALTER TABLE habitaciones ADD CONSTRAINT habitaciones_pk PRIMARY KEY ( id );

CREATE TABLE planesdeconsumo (
    descripcion VARCHAR2(255 CHAR),
    descuento   INTEGER,
    nombre      VARCHAR2(255) NOT NULL,
    id          INTEGER NOT NULL
);

ALTER TABLE planesdeconsumo ADD CONSTRAINT planesdeconsumo_pk PRIMARY KEY ( id );

CREATE TABLE productos (
    nombre       VARCHAR2(255 CHAR) NOT NULL,
    costo        INTEGER,
    servicios_id INTEGER NOT NULL,
    id           INTEGER NOT NULL
);

ALTER TABLE productos ADD CONSTRAINT productos_pk PRIMARY KEY ( id );

CREATE TABLE reservashabitaciones (
    id                     INTEGER NOT NULL,
    numpersonas            INTEGER,
    fechainicio            DATE,
    fechafin               DATE,
    fechacheckin           DATE,
    fechacheckout          DATE,
    habitaciones_id        INTEGER NOT NULL,
    usuarios_id            INTEGER NOT NULL,
    planesdeconsumo_id  Integer NOT NULL
);

ALTER TABLE reservashabitaciones ADD CONSTRAINT reservashabitaciones_pk PRIMARY KEY ( id );

CREATE TABLE reservasservicios (
    id           INTEGER NOT NULL,
    numpersonas  INTEGER,
    fechainicio  DATE,
    fechafin     DATE,
    usuarios_id  INTEGER NOT NULL,
    servicios_id INTEGER NOT NULL
);

ALTER TABLE reservasservicios ADD CONSTRAINT reservasservicios_pk PRIMARY KEY ( id );

CREATE TABLE servicios (
    nombre         VARCHAR2(255 CHAR) NOT NULL,
    descripcion    VARCHAR2(255 CHAR),
    costoporunidad INTEGER,
    unidad         VARCHAR2(255 CHAR),
    horario        VARCHAR2(255),
    tiposervicio   VARCHAR2(255 CHAR),
    capacidad      INTEGER,
    id             INTEGER NOT NULL
);

ALTER TABLE servicios ADD CONSTRAINT servicios_pk PRIMARY KEY ( id );

CREATE TABLE tiposdeusuario (
    nombre VARCHAR2(255 CHAR) NOT NULL,
    id     INTEGER NOT NULL,
    permisos VARCHAR(55) NOT NULL
);

ALTER TABLE tiposdeusuario ADD CONSTRAINT tiposdeusuario_pk PRIMARY KEY ( id );

CREATE TABLE tiposhabitaciones (
    nombre    VARCHAR2(255) NOT NULL,
    capacidad INTEGER,
    id        INTEGER NOT NULL
);

ALTER TABLE tiposhabitaciones ADD CHECK ( capacidad BETWEEN 0 AND 1000 );

ALTER TABLE tiposhabitaciones ADD CONSTRAINT tiposhabitaciones_pk PRIMARY KEY ( id );

CREATE TABLE usuarios (
    id                INTEGER NOT NULL,
    nombreUsuario     VARCHAR2(55),
    contrasena        VARCHAR2(55),
    nombre            VARCHAR2(255 CHAR),
    numdocumento      INTEGER,
    email             VARCHAR2(255 CHAR),
    tipodocumento     VARCHAR2(255 CHAR),
    tiposdeusuario_id INTEGER NOT NULL
);

ALTER TABLE usuarios ADD CONSTRAINT usuarios_pk PRIMARY KEY ( id );

ALTER TABLE consumos
    ADD CONSTRAINT consumos_reservashabitaciones_fk FOREIGN KEY ( reservashabitaciones_id )
        REFERENCES reservashabitaciones ( id );

ALTER TABLE consumos
    ADD CONSTRAINT servicios_fk FOREIGN KEY ( servicios_id )
        REFERENCES servicios ( id );

ALTER TABLE elementosatipos
    ADD CONSTRAINT elematip_elemhab_fk FOREIGN KEY ( elementoshab_id )
        REFERENCES elementoshabitaciones ( id );

ALTER TABLE elementosatipos
    ADD CONSTRAINT elematip_tiphab_fk FOREIGN KEY ( tiposhabitaciones_id )
        REFERENCES tiposhabitaciones ( id );
ALTER TABLE habitaciones
    ADD CONSTRAINT habitaciones_tiposhabitaciones_fk FOREIGN KEY ( tiposhabitaciones_id )
        REFERENCES tiposhabitaciones ( id );

ALTER TABLE productos
    ADD CONSTRAINT productos_servicios_fk FOREIGN KEY ( servicios_id )
        REFERENCES servicios ( id );
ALTER TABLE reservashabitaciones
    ADD CONSTRAINT reservashabitaciones_habitaciones_fk FOREIGN KEY ( habitaciones_id )
        REFERENCES habitaciones ( id );
ALTER TABLE reservashabitaciones
    ADD CONSTRAINT reservashabitaciones_planesdeconsumo_fk FOREIGN KEY ( planesdeconsumo_id)
        REFERENCES planesdeconsumo ( id );

ALTER TABLE reservashabitaciones
    ADD CONSTRAINT reservashabitaciones_usuarios_fk FOREIGN KEY ( usuarios_id )
        REFERENCES usuarios ( id );

ALTER TABLE reservasservicios
    ADD CONSTRAINT reservasservicios_servicios_fk FOREIGN KEY ( servicios_id )
        REFERENCES servicios ( id );

ALTER TABLE reservasservicios
    ADD CONSTRAINT reservasservicios_usuarios_fk FOREIGN KEY ( usuarios_id )
        REFERENCES usuarios ( id );

ALTER TABLE usuarios
    ADD CONSTRAINT usuarios_tiposdeusuario_fk FOREIGN KEY ( tiposdeusuario_id )
        REFERENCES tiposdeusuario ( id );


CREATE SEQUENCE consumossecuencia START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE elementosatipossecuencia START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE elementoshabitacionessecuencia START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE habitacionessecuencia START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE planesdeconsumosecuencia START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE productossecuencia START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE reservashabitacionessecuencia START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE reservasserviciossecuencia START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE tiposusuariossecuencia START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE tiposhabitacionessecuencia START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE usuariossecuencia START WITH 1 INCREMENT BY 1;



