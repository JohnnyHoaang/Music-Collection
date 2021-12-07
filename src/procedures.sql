DROP PROCEDURE CREATE_CONTRIBUTOR;
--DROP PROCEDURE DELETE_CONTRIBUTOR;
DROP PROCEDURE UPDATE_RECORDING;
DROP PROCEDURE CREATE_COLLECTION;
DROP PROCEDURE CREATE_ALBUM;
DROP PROCEDURE CREATE_COMPILATION;

--insert contributor 
CREATE OR REPLACE PROCEDURE CREATE_CONTRIBUTOR (firstname IN VARCHAR2, lastname in VARCHAR2,
contributor_id in VARCHAR2, role_id IN VARCHAR2, rec_id IN VARCHAR2)
AS
BEGIN
    INSERT INTO CONTRIBUTOR
    VALUES(contributor_id, firstname, lastname);
    INSERT INTO RECORDING
    VALUES(rec_id, SYSDATE, 0, 0);
    INSERT INTO CONTRIBUTOR_REC
    VALUES(rec_id, contributor_id, role_id);
END;
/

--delete contributor 
--CREATE OR REPLACE PROCEDURE DELETE_CONTRIBUTOR(contributor_id IN VARCHAR2)
--AS
----use varray to access every recording and delete them
--recording_id recording.recid%type;
--BEGIN
--    SELECT 
--    DELETE FROM CONTRIBUTOR WHERE contributorid = contributor_id;
--END;

--update recording
CREATE OR REPLACE PROCEDURE UPDATE_RECORDING (rec_id IN VARCHAR2, vdate DATE, vduration IN NUMBER, voffset IN NUMBER)
AS
BEGIN
    UPDATE RECORDING 
    SET rec_date = vdate, 
    duration = vduration,
    offset = voffset
    WHERE recid = rec_id;
END;
/
--create collections for album
CREATE OR REPLACE PROCEDURE CREATE_COLLECTION(collection_id IN VARCHAR2, albName IN VARCHAR2)
AS
BEGIN
    INSERT INTO COLLECTION 
    VALUES(collection_id, albName);
END;
/
-- create albums 
CREATE OR REPLACE PROCEDURE CREATE_ALBUM(vtitle IN VARCHAR2, album_id in VARCHAR2, vcategory IN VARCHAR2, vpubdate IN DATE,
 collection_id IN VARCHAR2, vmarket IN VARCHAR2, vlabel IN VARCHAR2)
AS
BEGIN
    INSERT INTO ALBUM 
    VALUES (vtitle, album_id, vcategory, vpubdate, collection_id, vmarket, vlabel);
END;
/
-- creating compilation with existing fk
CREATE OR REPLACE PROCEDURE CREATE_COMPILATION(rec_id IN VARCHAR2, vdate IN DATE, album_id IN VARCHAR2)
AS
BEGIN
    INSERT INTO COMPILATION VALUES (rec_id,vdate, album_id);   
END;

-- Use cursors to retrive data