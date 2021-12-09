DROP PACKAGE updatepkg;
/
CREATE OR REPLACE PACKAGE updatepkg AS
PROCEDURE UPDATE_RECORDING(rec_id IN VARCHAR2, vdate DATE, vduration IN NUMBER, voffset IN NUMBER);
END updatepkg;
/
CREATE OR REPLACE PACKAGE BODY updatepkg IS
PROCEDURE UPDATE_RECORDING (rec_id IN VARCHAR2, vdate DATE, vduration IN NUMBER, voffset IN NUMBER)
AS
BEGIN
    UPDATE RECORDING 
    SET rec_date = vdate, 
    duration = vduration,
    offset = voffset
    WHERE recid = rec_id;
END UPDATE_RECORDING;
END updatepkg;

CREATE OR REPLACE PROCEDURE UPDATE_COLLECTIONID(album_id IN VARCHAR2, collection_id IN VARCHAR2)
AS
BEGIN
    UPDATE FROM ALBUM SET collectionid = collection_id WHERE collectionid = collection_id;
END;


