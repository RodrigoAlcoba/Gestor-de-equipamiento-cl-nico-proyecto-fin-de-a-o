CREATE OR REPLACE DIRECTORY IMG_DIR AS 'dataBase\imagenes';--Como tenemos Oracle en Docker no sirve este path por no ser Oracle local.
GRANT READ, WRITE ON DIRECTORY IMG_DIR TO USUARIOPDT;
SELECT * FROM DBA_DIRECTORIES;
SELECT * FROM DBA_DIRECTORIES WHERE DIRECTORY_NAME = 'IMG_DIR';

SET SERVEROUTPUT ON;

DECLARE
    l_bfile         BFILE;
    l_blob          BLOB := empty_blob();
    l_dest_offset   INTEGER := 1;
    l_src_offset    INTEGER := 1;
    l_lobmaxsize    CONSTANT INTEGER := DBMS_LOB.LOBMAXSIZE;

BEGIN

    l_bfile := BFILENAME('IMG_DIR', 'img1.jpg');
    DBMS_LOB.FILEOPEN(l_bfile, DBMS_LOB.FILE_READONLY);
    DBMS_LOB.LOADBLOBFROMFILE(l_blob, l_bfile, l_lobmaxsize, l_dest_offset, l_src_offset);
    DBMS_LOB.FILECLOSE(l_bfile);
    DBMS_OUTPUT.PUT_LINE('SIZE OF THE IMAGE IS: ' || DBMS_LOB.GETLENGTH(l_blob));

END;