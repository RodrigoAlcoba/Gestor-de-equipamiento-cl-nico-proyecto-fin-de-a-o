----------------------------USUARIOS----------------------------

CREATE TABLE USUARIOS(
    ID_Usuario INTEGER NOT NULL,
    ID_Perfil INTEGER,
    ID_Institucion INTEGER,
    Cedula VARCHAR(8) NOT NULL, 
    Nombre VARCHAR(20) NOT NULL, 
    Nombre2 VARCHAR(20), 
    Apellido VARCHAR(21) NOT NULL, 
    Apellido2 VARCHAR(21) NOT NULL,
    Nom_Usuario VARCHAR(51) NOT NULL,
    Fec_Nac DATE NOT NULL, 
    Email VARCHAR(50) NOT NULL, 
    Contrasenia VARCHAR(50) NOT NULL,
    Estado VARCHAR(9),
    CONSTRAINT PK_Usuario PRIMARY KEY(ID_Usuario),
    CONSTRAINT UK_Usuario_CI UNIQUE(Cedula),
    CONSTRAINT UK_Usuario_Email UNIQUE(Email),
    CONSTRAINT FK_Usuario_Perfil FOREIGN KEY(ID_Perfil) REFERENCES PERFILES(ID_Perfil),
    CONSTRAINT FK_Usuario_Institucion FOREIGN KEY(ID_Institucion) REFERENCES INSTITUCIONES(ID_Institucion)
) TABLESPACE USERS;


-- SECUENCIA
CREATE SEQUENCE USUARIOS_ID_SEQ
    MINVALUE 1
    MAXVALUE 9999999999999999999999999999
    INCREMENT BY 1
    START WITH 1
    CACHE 20
    NOORDER
    NOCYCLE
    NOKEEP
    NOSCALE;

-- TRIGGER
CREATE OR REPLACE TRIGGER USUARIOS_ID_TRIGGER
BEFORE
INSERT ON USUARIOS
FOR EACH ROW
BEGIN
SELECT USUARIOS_ID_SEQ.NEXTVAL
INTO :NEW.ID_Usuario
FROM DUAL;
END;

----------------------------TELEFONOS USUARIOS----------------------------

CREATE TABLE TELEFONOS_USUARIOS(
    ID_Tel INTEGER NOT NULL, 
    Telefono VARCHAR(20) NOT NULL,
    ID_Usuario INTEGER,
    CONSTRAINT PK_Tel PRIMARY KEY(ID_Tel),
    CONSTRAINT FK_Tel_Usuario FOREIGN KEY(ID_Usuario) REFERENCES USUARIOS(ID_Usuario)
)TABLESPACE USERS;

----------------------------PERFILES----------------------------

CREATE TABLE PERFILES(
    ID_Perfil INTEGER NOT NULL,
    ID_Funcionalidad INTEGER,
    Nom_Perfil VARCHAR(40) NOT NULL, 
    Estado VARCHAR(10) NOT NULL,
    CONSTRAINT PK_Perfil PRIMARY KEY(ID_Perfil),
    CONSTRAINT FK_Perfil_Funcionalidad FOREIGN KEY(ID_Funcionalidad) REFERENCES FUNCIONALIDADES(ID_Funcionalidad)
)TABLESPACE USERS;

ALTER TABLE PERFILES 
ADD CONSTRAINT chk_perfil_nombre
CHECK(UPPER(Nom_Perfil) 
IN(UPPER('Auxiliar Administrativo', 'Ingeniero
biomédico', 'Tecnólogo, Técnico')));

-- SECUENCIA
CREATE SEQUENCE PERFILES_ID_SEQ
    MINVALUE 1
    MAXVALUE 9999999999999999999999999999
    INCREMENT BY 1
    START WITH 1
    CACHE 20
    NOORDER
    NOCYCLE
    NOKEEP
    NOSCALE;

-- TRIGGER
CREATE OR REPLACE TRIGGER PERFILES_ID_TRIGGER
    BEFORE
        INSERT ON PERFILES
    FOR EACH ROW
BEGIN
    SELECT PERFILES_ID_SEQ.NEXTVAL
    INTO :NEW.ID_Perfil
    FROM DUAL;
END;


----------------------------PERMISOS----------------------------

CREATE TABLE FUNCIONALIDADES(
    ID_Funcionalidad INTEGER NOT NULL,
    Tipo_Permiso VARCHAR(50) NOT NULL,
    CONSTRAINT PK_Funcionalidad PRIMARY KEY(ID_Funcionalidad)
)TABLESPACE USERS;

-- SECUENCIA
CREATE SEQUENCE FUNCIONALIDADES_ID_SEQ
    MINVALUE 1
    MAXVALUE 9999999999999999999999999999
    INCREMENT BY 1
    START WITH 1
    CACHE 20
    NOORDER
    NOCYCLE
    NOKEEP
    NOSCALE;

-- TRIGGER
CREATE OR REPLACE TRIGGER PERMISOS_ID_TRIGGER
    BEFORE
        INSERT ON FUNCIONALIDADES
    FOR EACH ROW
BEGIN
    SELECT FUNCIONALIDADES_ID_SEQ.NEXTVAL
    INTO :NEW.ID_Funcionalidad
    FROM DUAL;
END;


----------------------------AUDITORIA----------------------------

CREATE TABLE AUDITORIAS(
    ID_Auditoria INTEGER NOT NULL, 
    ID_Usuario INTEGER NOT NULL,
    ID_Operacion INTEGER NOT NULL,
    Cedula VARCHAR(8) NOT NULL,
    Hora TIMESTAMP(0) NOT NULL,
    Fecha DATE NOT NULL, 
    CONSTRAINT PK_Auditoria PRIMARY KEY(ID_Auditoria),
    CONSTRAINT UK_Auditoria_Cedula UNIQUE(Cedula),
    CONSTRAINT FK_Auditoria_Usuario FOREIGN KEY(ID_Usuario) REFERENCES USUARIOS(ID_Usuario),
    CONSTRAINT FK_Auditoria_Operacion FOREIGN KEY(ID_Operacion) REFERENCES OPERACIONES(ID_Operacion)
)TABLESPACE USERS;

-- SECUENCIA
CREATE SEQUENCE AUDITORIAS_ID_SEQ
    MINVALUE 1
    MAXVALUE 9999999999999999999999999999
    INCREMENT BY 1
    START WITH 1
    CACHE 20
    NOORDER
    NOCYCLE
    NOKEEP
    NOSCALE;

-- TRIGGER
CREATE OR REPLACE TRIGGER AUDITORIAS_ID_TRIGGER
    BEFORE
        INSERT ON AUDITORIAS
    FOR EACH ROW
BEGIN
    SELECT AUDITORIAS_ID_SEQ.NEXTVAL
    INTO :NEW.ID_Auditoria
    FROM DUAL;
END;

----------------------------OPERACIONES----------------------------

CREATE TABLE OPERACIONES(
    ID_Operacion INTEGER NOT NULL,
    Nom_Operacion VARCHAR(50) NOT NULL, 
    CONSTRAINT PK_Operacion PRIMARY KEY(ID_Operacion)
)TABLESPACE USERS;

-- SECUENCIA
CREATE SEQUENCE OPERACIONES_ID_SEQ
    MINVALUE 1
    MAXVALUE 9999999999999999999999999999
    INCREMENT BY 1
    START WITH 1
    CACHE 20
    NOORDER
    NOCYCLE
    NOKEEP
    NOSCALE;

-- TRIGGER
CREATE OR REPLACE TRIGGER OPERACIONES_ID_TRIGGER
    BEFORE
        INSERT ON OPERACIONES
    FOR EACH ROW
BEGIN
    SELECT OPERACIONES_ID_SEQ.NEXTVAL
    INTO :NEW.ID_Operacion
    FROM DUAL;
END;


----------------------------INTERVENCIONES----------------------------

CREATE TABLE INTERVENCIONES(
    ID_Intervencion INTEGER NOT NULL,  --Se supone que no se puede tratar de una pk compuesta debido a la funcionalidad "Trabajar intervencion"
    ID_Usuario INTEGER,
    ID_Equipo INTEGER NOT NULL,
    ID_Tipo_Intervencion INTEGER NOT NULL,
    Motivo VARCHAR(50),
    Comentario VARCHAR(50),
    Fecha DATE DEFAULT SYSDATE NOT NULL,
    Hora TIMESTAMP(0) DEFAULT SYSTIMESTAMP NOT NULL,
    CONSTRAINT PK_Intervencion PRIMARY KEY(ID_Intervencion),
    CONSTRAINT FK_Intervencion_Equipo FOREIGN KEY(ID_Equipo) REFERENCES EQUIPOS(ID_Equipo),
    CONSTRAINT FK_Intervencion_Usuario FOREIGN KEY(ID_Usuario) REFERENCES USUARIOS(ID_Usuario)
)TABLESPACE USERS;

-- SECUENCIA
CREATE SEQUENCE INTERVENCIONES_ID_SEQ
    MINVALUE 1
    MAXVALUE 9999999999999999999999999999
    INCREMENT BY 1
    START WITH 1
    CACHE 20
    NOORDER
    NOCYCLE
    NOKEEP
    NOSCALE;

-- TRIGGER
CREATE OR REPLACE TRIGGER INTERVENCIONES_ID_TRIGGER
    BEFORE
        INSERT ON INTERVENCIONES
    FOR EACH ROW
BEGIN
    SELECT INTERVENCIONES_ID_SEQ.NEXTVAL
    INTO :NEW.ID_Intervencion
    FROM DUAL;
END;


----------------------------TIPO_INTERVENCION----------------------------

CREATE TABLE TIPO_INTERVENCION(
    ID_Tipo_Intervencion INTEGER NOT NULL,
    Nom_Tipo VARCHAR(50) NOT NULL,
    CONSTRAINT PK_Tipo_Intervencion PRIMARY KEY(ID_Tipo_Intervencion)
)TABLESPACE USERS;

ALTER TABLE TIPO_INTERVENCION 
ADD CONSTRAINT chk_intervencion_tipo  
CHECK(UPPER(Nom_Tipo) 
IN(UPPER('Prevencion', 'Falla', 'Resolucion')));

-- SECUENCIA
CREATE SEQUENCE TIPO_INTERVENCIONES_ID_SEQ
    MINVALUE 1
    MAXVALUE 9999999999999999999999999999
    INCREMENT BY 1
    START WITH 1
    CACHE 20
    NOORDER
    NOCYCLE
    NOKEEP
    NOSCALE;

-- TRIGGER
CREATE OR REPLACE TRIGGER TIPO_INTERVENCIONES_ID_TRIGGER
    BEFORE
        INSERT ON TIPO_INTERVENCION
    FOR EACH ROW
BEGIN
    SELECT TIPO_INTERVENCIONES_ID_SEQ.NEXTVAL
    INTO :NEW.ID_Tipo_Intervencion
    FROM DUAL;
END;


----------------------------INSTITUCIONES----------------------------

CREATE TABLE INSTITUCIONES(
    ID_Institucion INTEGER NOT NULL,
    Nom_Institucion VARCHAR(60) NOT NULL,
    Estado VARCHAR(50) NOT NULL,
    CONSTRAINT PK_Institucion PRIMARY KEY(ID_Institucion),
    CONSTRAINT UK_Insti_Nom UNIQUE(Nom_Institucion)
)TABLESPACE USERS;

-- SECUENCIA
CREATE SEQUENCE INSTITUCIONES_ID_SEQ
    MINVALUE 1
    MAXVALUE 9999999999999999999999999999
    INCREMENT BY 1
    START WITH 1
    CACHE 20
    NOORDER
    NOCYCLE
    NOKEEP
    NOSCALE;

-- TRIGGER
CREATE OR REPLACE TRIGGER INSTITUCIONES_ID_TRIGGER
    BEFORE
        INSERT ON INSTITUCIONES
    FOR EACH ROW
BEGIN
    SELECT INSTITUCIONES_ID_SEQ.NEXTVAL
    INTO :NEW.ID_Institucion
    FROM DUAL;
END;

----------------------------UBICACIONES----------------------------

CREATE TABLE UBICACIONES(
    ID_Ubicacion INTEGER NOT NULL,
    ID_Institucion INTEGER NOT NULL,
    ID_Sector INTEGER NOT NULL,
    Nom_Ubicacion VARCHAR(80) NOT NULL,
    Numero NUMBER NOT NULL,
    Piso VARCHAR(50) NOT NULL,
    Cama NUMBER,
    CONSTRAINT PK_Ubicacion PRIMARY KEY(ID_Ubicacion),
    CONSTRAINT UK_Ubicacion_Nom UNIQUE(Nom_Ubicacion),
    CONSTRAINT FK_Ubicacion_Sector FOREIGN KEY(ID_Sector) REFERENCES SECTORES(ID_Sector)
)TABLESPACE USERS;

ALTER TABLE SECTORES
ADD CONSTRAINT chk_nombre_sector
CHECK(UPPER(Nombre)
IN(UPPER('Policlinico', 'Internacion', 'Emergencia', 'CTI',
'otro')));

-- SECUENCIA
CREATE SEQUENCE Ubicaciones_ID_SEQ
    MINVALUE 1
    MAXVALUE 9999999999999999999999999999
    INCREMENT BY 1
    START WITH 1
    CACHE 20
    NOORDER
    NOCYCLE
    NOKEEP
    NOSCALE;

-- TRIGGER
CREATE OR REPLACE TRIGGER UBICACIONES_ID_TRIGGER
    BEFORE
        INSERT ON UBICACIONES
    FOR EACH ROW
BEGIN
    SELECT UBICACIONES_ID_SEQ.NEXTVAL
    INTO :NEW.ID_Ubicacion
    FROM DUAL;
END;

----------------------------SECTORES----------------------------
CREATE TABLE SECTORES(
    ID_Sector INTEGER NOT NULL,
    Nombre VARCHAR(50) NOT NULL,
    CONSTRAINT PK_Sector PRIMARY KEY(ID_Sector),
    CONSTRAINT UK_Sector_Nombre UNIQUE(Nombre)
)TABLESPACE USERS;

----------------------------EQUIPOS----------------------------

CREATE TABLE EQUIPOS(
    ID_Equipo INTEGER NOT NULL, 
    ID_Ubicacion INTEGER,
    ID_Pais INTEGER, 
    ID_Tipo_Equipo INTEGER,
    ID_Proveedor INTEGER,
    ID_Modelo INTEGER,
    Ide_Interna INTEGER,
    Nom_Equipo VARCHAR(80) NOT NULL, 
    Num_Serie NUMBER NOT NULL, 
    Fec_Adquisicion DATE NOT NULL, 
    Imagen BLOB NOT NULL, 
    Garantia DATE NOT NULL, 
    CONSTRAINT PK_Equipo PRIMARY KEY(ID_Equipo),
    CONSTRAINT UK_Equipos_Ide UNIQUE(Ide_Interna),
    CONSTRAINT FK_Equipo_Ubicacion FOREIGN KEY(ID_Ubicacion) REFERENCES UBICACIONES(ID_Ubicacion),
    CONSTRAINT FK_Equipo_Pais FOREIGN KEY(ID_Pais) REFERENCES PAISES(ID_Pais),
    CONSTRAINT FK_Equipo_Tipo FOREIGN KEY(ID_Tipo_Equipo) REFERENCES TIPO_EQUIPO(ID_Tipo_Equipo),
    CONSTRAINT FK_Equipo_Proveedor FOREIGN KEY(ID_Proveedor) REFERENCES PROVEEDORES(ID_Proveedor),
    CONSTRAINT FK_Equipo_Modelo FOREIGN KEY(ID_Modelo) REFERENCES MODELOS(ID_Modelo)
)TABLESPACE USERS;

-- SECUENCIA
CREATE SEQUENCE EQUIPOS_ID_SEQ
    MINVALUE 1
    MAXVALUE 9999999999999999999999999999
    INCREMENT BY 1
    START WITH 1
    CACHE 20
    NOORDER
    NOCYCLE
    NOKEEP
    NOSCALE;

-- TRIGGER
CREATE OR REPLACE TRIGGER EQUIPOS_ID_TRIGGER
    BEFORE
        INSERT ON EQUIPOS
    FOR EACH ROW
BEGIN
    SELECT EQUIPOS_ID_SEQ.NEXTVAL
    INTO :NEW.ID_Equipo
    FROM DUAL;
END;

----------------------------MARCAS----------------------------

CREATE TABLE MARCAS(
    ID_Marca INTEGER NOT NULL,
    Nom_Marca VARCHAR(50) NOT NULL,
    Estado VARCHAR(9) NOT NULL,
    CONSTRAINT PK_Marca PRIMARY KEY(ID_Marca),
    CONSTRAINT UK_Marca_Nom UNIQUE(Nom_Marca)
)TABLESPACE USERS;

CREATE SEQUENCE MARCAS_ID_SEQ
    MINVALUE 1
    MAXVALUE 9999999999999999999999999999
    INCREMENT BY 1
    START WITH 1
    CACHE 20
    NOORDER
    NOCYCLE
    NOKEEP
    NOSCALE;

-- TRIGGER
CREATE OR REPLACE TRIGGER MARCAS_ID_TRIGGER
    BEFORE
        INSERT ON MARCAS
    FOR EACH ROW
BEGIN
    SELECT MARCAS_ID_SEQ.NEXTVAL
    INTO :NEW.ID_Marca
    FROM DUAL;
END;
--------

----------------------------PAISES----------------------------

CREATE TABLE PAISES(
    ID_Pais INTEGER NOT NULL,
    Nom_Pais VARCHAR(50) NOT NULL,
    Estado VARCHAR(50) NOT NULL,
    CONSTRAINT PK_Pais PRIMARY KEY(ID_Pais),
    CONSTRAINT UK_Pais_Nom UNIQUE(Nom_Pais)
)TABLESPACE USERS;

-- SECUENCIA
CREATE SEQUENCE PAISES_ID_SEQ
    MINVALUE 1
    MAXVALUE 9999999999999999999999999999
    INCREMENT BY 1
    START WITH 1
    CACHE 20
    NOORDER
    NOCYCLE
    NOKEEP
    NOSCALE;

-- TRIGGER
CREATE OR REPLACE TRIGGER PAISES_ID_TRIGGER
    BEFORE
        INSERT ON PAISES
    FOR EACH ROW
BEGIN
    SELECT PAISES_ID_SEQ.NEXTVAL
    INTO :NEW.ID_Pais
    FROM DUAL;
END;

----------------------------TIPO_EQUIPO----------------------------

CREATE TABLE TIPO_EQUIPO(
    ID_Tipo_Equipo INTEGER NOT NULL,
    Nom_Tipo VARCHAR(50) NOT NULL,
    Estado VARCHAR(9) NOT NULL,
    CONSTRAINT PK_Tipo_Equipo PRIMARY KEY(ID_Tipo_Equipo),
    CONSTRAINT UK_Tipo_Equipo_Nom UNIQUE(Nom_Tipo)
)TABLESPACE USERS;

-- SECUENCIA
CREATE SEQUENCE TIPOS_EQUIPO_ID_SEQ
    MINVALUE 1
    MAXVALUE 9999999999999999999999999999
    INCREMENT BY 1
    START WITH 1
    CACHE 20
    NOORDER
    NOCYCLE
    NOKEEP
    NOSCALE;

-- TRIGGER
CREATE OR REPLACE TRIGGER TIPOS_EQUIPO_ID_TRIGGER
    BEFORE
        INSERT ON TIPO_EQUIPO
    FOR EACH ROW
BEGIN
    SELECT TIPOS_EQUIPO_ID_SEQ.NEXTVAL
    INTO :NEW.ID_Tipo_Equipo
    FROM DUAL;
END;

----------------------------PROVEEDORES----------------------------

CREATE TABLE PROVEEDORES(
    ID_Proveedor INTEGER NOT NULL, 
    Nom_Proveedor VARCHAR(50) NOT NULL,
    Email VARCHAR(50) NOT NULL,
    Estado VARCHAR(9) NOT NULL,
    Sitio VARCHAR(80) NOT NULL,
    CONSTRAINT PK_Proveedor PRIMARY KEY(ID_Proveedor),
    CONSTRAINT UK_Proveedor_Nom UNIQUE(Nom_Proveedor)
)TABLESPACE USERS;

-- SECUENCIA
CREATE SEQUENCE PROVEEDORES_ID_SEQ
    MINVALUE 1
    MAXVALUE 9999999999999999999999999999
    INCREMENT BY 1
    START WITH 1
    CACHE 20
    NOORDER
    NOCYCLE
    NOKEEP
    NOSCALE;

-- TRIGGER
CREATE OR REPLACE TRIGGER PROVEEDORES_ID_TRIGGER
    BEFORE
        INSERT ON PROVEEDORES
    FOR EACH ROW
BEGIN
    SELECT PROVEEDORES_ID_SEQ.NEXTVAL
    INTO :NEW.ID_Proveedor
    FROM DUAL;
END;


----------------------------TELEFONOS USUARIOS----------------------------
CREATE TABLE TELEFONOS_PROVEEDOR(
    ID_Tel INTEGER NOT NULL,
    Telefono VARCHAR(20) NOT NULL,
    ID_Proveedor INTEGER,
    CONSTRAINT PK_Tel PRIMARY KEY(ID_Tel),
    CONSTRAINT FK_Tel_Proveedor FOREIGN KEY(ID_Proveedor) REFERENCES PROVEEDORES(ID_Proveedor)
)TABLESPACE USERS;


----------------------------MODELOS----------------------------

CREATE TABLE MODELOS(
    ID_Modelo INTEGER NOT NULL,
    ID_Marca INTEGER, 
    Nom_Modelo VARCHAR(50) NOT NULL,
    Estado VARCHAR(9) NOT NUll,
    CONSTRAINT PK_Modelo PRIMARY KEY(ID_Modelo),
    CONSTRAINT UK_Modelo_Nom UNIQUE(Nom_Modelo),
    CONSTRAINT FK_Modelo_Marca FOREIGN KEY(ID_Marca) REFERENCES MARCAS(ID_Marca)
)TABLESPACE USERS;

-- SECUENCIA
CREATE SEQUENCE MODELOS_ID_SEQ
    MINVALUE 1
    MAXVALUE 9999999999999999999999999999
    INCREMENT BY 1
    START WITH 1
    CACHE 20
    NOORDER
    NOCYCLE
    NOKEEP
    NOSCALE;

-- TRIGGER
CREATE OR REPLACE TRIGGER MODELOS_ID_TRIGGER
    BEFORE
        INSERT ON MODELOS
    FOR EACH ROW
BEGIN
    SELECT MODELOS_ID_SEQ.NEXTVAL
    INTO :NEW.ID_MODELO
    FROM DUAL;
END;


----------------------------BAJA_USUARIO_EQUIPO----------------------------

CREATE TABLE BAJA_USUARIO_EQUIPO(
    ID_Usuario INTEGER NOT NULL,
    ID_Equipo INTEGER NOT NULL,
    Fec_Baja DATE NOT NULL,
    Razon VARCHAR(80) NOT NULL,
    Comentario VARCHAR(200) NOT NULL,
    CONSTRAINT PK_Baja_UE PRIMARY KEY(ID_Equipo, ID_Usuario, Fec_Baja),
    CONSTRAINT FK_Baja_Usuario FOREIGN KEY(ID_Usuario) REFERENCES USUARIOS(ID_Usuario),
    CONSTRAINT FK_Baja_Equipo FOREIGN KEY(ID_Equipo) REFERENCES EQUIPOS(ID_Equipo)
)TABLESPACE USERS;


----------------------------BAJA_USUARIO_UBICACION----------------------------

CREATE TABLE BAJA_USUARIO_UBICACION(
    ID_Usuario INTEGER NOT NULL,
    ID_Ubicacion INTEGER NOT NULL,
    Fecha DATE NOT NULL,
    Razon VARCHAR(80) NOT NULL,
    Comentario VARCHAR(200) NOT NULL,
    CONSTRAINT PK_Baja_U PRIMARY KEY(ID_Usuario, ID_Ubicacion, Fecha),
    CONSTRAINT FK_Baja_Usuario FOREIGN KEY(ID_Usuario) REFERENCES USUARIOS(ID_Usuario),
    CONSTRAINT FK_Baja_Ubicacion FOREIGN KEY(ID_Ubicacion) REFERENCES UBICACIONES(ID_Ubicacion)
)TABLESPACE USERS;

----------------------------PERFIL_PERMISO_FUNCIONALIDAD----------------------------

CREATE TABLE PERFIL_PERMISO_FUNCIONALIDAD (
      Id_Funcionalidad INTEGER NOT NULL,
      Id_Perfil INTEGER NOT NULL,
      CONSTRAINT FK_PERFIL_PERMISO_FUNCIONALIDAD FOREIGN KEY(Id_Funcionalidad)
          REFERENCES FUNCIONALIDADES(Id_Funcionalidad),
      CONSTRAINT FK_PERFIL_PERMISO_PERFIL FOREIGN KEY(Id_Perfil)
          REFERENCES PERFILES(Id_Perfil)
) TABLESPACE USERS;