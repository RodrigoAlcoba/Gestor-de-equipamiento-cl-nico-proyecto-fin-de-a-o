-- Insertar en FUNCIONALIDADES
INSERT INTO FUNCIONALIDADES (Tipo_Permiso)
VALUES ('Tipo_de_permiso');

-- Insertar en PERFILES
INSERT INTO PERFILES (ID_Funcionalidad, Nom_Perfil, Estado)
VALUES (1, 'Tecnico', 'Activo'); -- Suponiendo que el ID de Funcionalidad es 1

-- Insertar en INSTITUCIONES
INSERT INTO INSTITUCIONES (Nom_Institucion, Estado)
VALUES ('Nombre_de_institucion_ejemplo', 'Estado_ejemplo');

-- Insertar en USUARIOS
INSERT INTO USUARIOS (ID_Perfil, ID_Institucion, Cedula, Nombre, Nombre2, Apellido, Apellido2, Nom_Usuario, Fec_Nac, Email, Contrasenia, Estado)
VALUES (
          1,
        1,
           'Cedula',
           'Nombre_ejemplo',
           'Nombre2_ejemplo',
           'Apellido_ejemplo',
           'Apellido2_ejemplo',
           'Nom_Usuario_ejemplo',
           TO_DATE('2023-10-09', 'YYYY-MM-DD'),
           'Email_ejemplo',
           'Contrasenia_ejemplo',
           'Estado'
       );


INSERT INTO TELEFONOS_USUARIOS (Telefono, ID_Usuario)
VALUES ('0000000', (SELECT ID_Usuario FROM USUARIOS WHERE Nom_Usuario = 'Nom_Usuario_ejemplo'));

INSERT INTO OPERACIONES (Nom_Operacion)
VALUES ('Nombre_de_operacion_ejemplo');

INSERT INTO AUDITORIAS (ID_Usuario, ID_Operacion, Cedula, Fecha)
VALUES (
           1,
            1,
           'Cedula',
           TIMESTAMP '2023-10-09 12:34:56'
       );

INSERT INTO SECTORES (Nombre)
VALUES ('Policlinico');

INSERT INTO UBICACIONES (ID_Institucion, ID_Sector, Nom_Ubicacion, Numero, Piso, Cama)
VALUES (
           1,2,
           'Nombre_de_ubicacion_ejemplo',
           1, -- Número de ubicación
           'Piso_ejemplo',
           101  -- Número de cama, si corresponde
       );

INSERT INTO PAISES (Nom_Pais, Estado)
VALUES ('Nombre_del_pais_ejemplo', 'Estado_ejemplo');

INSERT INTO TIPO_EQUIPO (Nom_Tipo, Estado)
VALUES ('Nombre_del_tipo_equipo_ejemplo', 'Estado_ejemplo');

INSERT INTO PROVEEDORES (Nom_Proveedor, Email, Estado, Sitio)
VALUES ('Nombre_del_proveedor_ejemplo', 'correo@ejemplo.com', 'Estado_ejemplo', 'Sitio_web_ejemplo');

INSERT INTO MARCAS (Nom_Marca, Estado)
VALUES ('Nombre_de_marca_ejemplo', 'Estado_ejemplo');

INSERT INTO MODELOS (ID_Marca, Nom_Modelo, Estado)
VALUES (1, 'Nombre_del_modelo_ejemplo', 'Estado_ejemplo');

INSERT INTO MOV_EQUIPO (ID_Ubicacion, ID_Usuario, Fec_Entrada, Fec_Salida, Observaciones)
VALUES ( 1, 1, TO_DATE('2023-10-09', 'YYYY-MM-DD'), TO_DATE('2023-10-10', 'YYYY-MM-DD'), 'Observaciones_ejemplo');

INSERT INTO EQUIPOS (ID_Pais, ID_Tipo_Equipo, ID_Proveedor, ID_Modelo, ID_Mov_Equipo, Ide_Interna, Nom_Equipo, Num_Serie, Fec_Adquisicion, Imagen, Garantia, Estado)
VALUES (1, 1, 1, 1, 1, 123465, 'Nombre_del_equipo_ejemplo', 67890, TO_DATE('2023-10-09', 'YYYY-MM-DD'), EMPTY_BLOB(), TO_DATE('2023-10-09', 'YYYY-MM-DD'), 'Estado_ejemplo');

INSERT INTO TIPO_INTERVENCION (Nom_Tipo)
VALUES ('Falla');

INSERT INTO INTERVENCIONES (ID_Usuario, ID_Equipo, ID_Tipo_Intervencion, Motivo, Comentario, Fecha)
VALUES (1, 1, 1, 'Prevencion', 'Comentario_ejemplo', TIMESTAMP '2023-10-09 10:30:00');

INSERT INTO TELEFONOS_PROVEEDOR (Telefono, ID_Proveedor)
VALUES ('123456789', 1);

INSERT INTO BAJA_USUARIO_EQUIPO (ID_Usuario, ID_Equipo, Fec_Baja, Razon, Comentario)
VALUES (1, 1, TO_DATE('2023-10-09', 'YYYY-MM-DD'), 'Razon_de_baja_ejemplo', 'Comentario_ejemplo');

INSERT INTO BAJA_USUARIO_UBICACION (ID_Usuario, ID_Ubicacion, Fec_Baja, Razon, Comentario)
VALUES (1, 1, TO_DATE('2023-10-09', 'YYYY-MM-DD'), 'Razon_de_baja_ejemplo', 'Comentario_ejemplo');

INSERT INTO PERFIL_PERMISO_FUNCIONALIDAD (Id_Funcionalidad, Id_Perfil)
VALUES (1, 1);
------------------------------------------------------------------------------------
INSERT INTO PAISES (ID_PAIS, Nom_Pais, Estado)
VALUES (PAIS_ID_SEQ.nextval, 'URUGUAY', 'ACTIVO');
INSERT INTO PAISES (ID_PAIS, Nom_Pais, Estado)
VALUES (PAIS_ID_SEQ.nextval, 'BRASIL', 'ACTIVO');

