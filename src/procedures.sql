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
--create a function that counts recid,used for deletes, if recid = 0, 
--no need to delete all the relations
CREATE OR REPLACE FUNCTION COUNT_RECID(contributor_id IN VARCHAR2)
RETURN NUMBER
IS 
countRecs NUMBER;
BEGIN 
    SELECT COUNT(rec_id) into countRecs 
    FROM CONTRIBUTOR 
    JOIN CONTRIBUTOR_REC USING(rec_id)
    WHERE contributorid = contributor_id;
    RETURN countRecs;

    exception
      when others then
        raise_application_error(-20001, 'Invalid Recid' || ' ' || SQLERRM);
END;
/
CREATE OR REPLACE PROCEDURE DELETE_CONTRIBUTOR (contributor_id IN VARCHAR2)
AS
begin
  if count_recid(contributor_id) = 0 then
  delete from contributor where contributorid = contributor_id;
  else
  --delete all relations
  delete from contributor_rec where contributorid = contributor_id;
  delete from contributor where contributorid = contributor_id;
  end if;
end;
/
--asumming album is one song
CREATE OR REPLACE PROCEDURE DELETE_SONG(album_id IN VARCHAR2)
AS
begin
  FOR arow in (SELECT * FROM compilation where albumid = album_id) 
  loop
    delete from compilation where recid = arow.recid;
  end loop;
      delete from album where albumid = album_id;
end;
/
--deletes everything from compilation then will delete all its albums then the collection
CREATE OR REPLACE PROCEDURE DELETE_COLLECTION(collection_id IN VARCHAR2)
AS
album_id compilation.albumid%type;
begin
  for arow in (SELECT albumid FROM album JOIN COMPILATION 
  USING (albumid) where collectionid = collection_id)
  loop
    delete from compilation where albumid = arow.albumid;
  end loop
      for vrow IN (SELECT albumid FROM album where collectionid = collection_id)
      loop
        delete from album where albumid = vrow.albumid;
      end loop
    delete from collection where collectionid = collection_id;
end;