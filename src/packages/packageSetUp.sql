DROP PACKAGE addpkg;
DROP PACKAGE deletepkg;
/
CREATE OR REPLACE PACKAGE addpkg AS 
PROCEDURE CREATE_CONTRIBUTOR(firstname IN VARCHAR2, lastname in VARCHAR2,contributor_id in VARCHAR2);
PROCEDURE CREATE_RECORDING(rec_id IN VARCHAR2, vdate IN DATE, vduration IN NUMBER,
voffset IN NUMBER);
PROCEDURE CREATE_CONTRIBUTOR_REC(rec_id IN VARCHAR2, contributor_id IN VARCHAR2, role_id IN VARCHAR2);
PROCEDURE CREATE_ALBUM(album_id IN VARCHAR2, vtitle IN VARCHAR2, vcategory IN VARCHAR2, vpubdate IN DATE,
vmarket IN VARCHAR2, vlabel IN VARCHAR2);
PROCEDURE CREATE_COLLECTION(collection_id IN VARCHAR2, albName IN VARCHAR2);
PROCEDURE CREATE_COMPILATION(rec_id IN VARCHAR2, vdate IN DATE, album_id IN VARCHAR2);
END addpkg;
/
CREATE OR REPLACE PACKAGE BODY addpkg IS
--insert contributor 
PROCEDURE CREATE_CONTRIBUTOR (firstname IN VARCHAR2, lastname in VARCHAR2,
contributor_id in VARCHAR2)
AS
BEGIN
    INSERT INTO CONTRIBUTOR
    VALUES(contributor_id, firstname, lastname);
END CREATE_CONTRIBUTOR;
PROCEDURE CREATE_RECORDING(rec_id IN VARCHAR2, vdate IN DATE,
vduration IN NUMBER, voffset IN NUMBER)
AS
BEGIN
    INSERT INTO RECORDING
    VALUES(rec_id, vdate,vduration,voffset);
END CREATE_RECORDING;
PROCEDURE CREATE_CONTRIBUTOR_REC(rec_id IN VARCHAR2, contributor_id IN VARCHAR2, role_id IN VARCHAR2)
AS
BEGIN
    INSERT INTO CONTRIBUTOR_REC
    VALUES(rec_id, contributor_id, role_id);
END CREATE_CONTRIBUTOR_REC;
PROCEDURE CREATE_ALBUM(album_id IN VARCHAR2, vtitle IN VARCHAR2, vcategory IN VARCHAR2, vpubdate IN DATE,
vmarket IN VARCHAR2, vlabel IN VARCHAR2)
AS
BEGIN
    INSERT INTO ALBUM (albumid, title, category, pubdate, market, label)
    VALUES(album_id, vtitle, vcategory, vpubdate, vmarket, vlabel);
END CREATE_ALBUM;
PROCEDURE CREATE_COMPILATION(rec_id IN VARCHAR2, vdate IN DATE, album_id IN VARCHAR2)
AS
BEGIN
    INSERT INTO COMPILATION
    VALUES(rec_id, vdate, album_id);
END CREATE_COMPILATION;
PROCEDURE CREATE_COLLECTION(collection_id IN VARCHAR2, albName IN VARCHAR2)
AS
BEGIN
    INSERT INTO COLLECTION 
    VALUES(collection_id, albName);
END CREATE_COLLECTION;
END addpkg;
/
CREATE OR REPLACE PACKAGE deletepkg AS 
FUNCTION COUNT_RECID(contributor_id IN VARCHAR2)
RETURN NUMBER;
PROCEDURE DELETE_CONTRIBUTOR(contributor_id IN VARCHAR2);
PROCEDURE DELETE_SONG(album_id IN VARCHAR2);
PROCEDURE DELETE_COLLECTION(collection_id IN VARCHAR2);
PROCEDURE DELETE_RECORDING(recording_id IN VARCHAR2);
END deletepkg;
/
CREATE OR REPLACE PACKAGE BODY deletepkg IS
FUNCTION COUNT_RECID(contributor_id IN VARCHAR2)
RETURN NUMBER
IS 
countRecs NUMBER;
BEGIN 
    SELECT COUNT(recid) into countRecs 
    FROM CONTRIBUTOR 
    JOIN CONTRIBUTOR_REC USING(contributorid)
    WHERE contributorid = contributor_id;
    RETURN countRecs;

    exception
      when others then
        raise_application_error(-20001, 'Invalid Recid' || ' ' || SQLERRM);
END COUNT_RECID;
PROCEDURE DELETE_CONTRIBUTOR (contributor_id IN VARCHAR2)
AS
begin
  if count_recid(contributor_id) = 0 then
  delete from contributor where contributorid = contributor_id;
  else
  --delete all relations
  delete from contributor_rec where contributorid = contributor_id;
  delete from contributor where contributorid = contributor_id;
  end if;
end DELETE_CONTRIBUTOR;
PROCEDURE DELETE_SONG(album_id IN VARCHAR2)
AS
begin
  FOR arow in (SELECT * FROM compilation where albumid = album_id) 
  loop
    delete from compilation where recid = arow.recid;
  end loop;
      delete from album where albumid = album_id;
end DELETE_SONG;
PROCEDURE DELETE_COLLECTION(collection_id IN VARCHAR2)
AS
album_id compilation.albumid%type;
begin
  for arow in (SELECT albumid FROM album JOIN COMPILATION 
  USING (albumid) where collectionid = collection_id)
  loop
    delete from compilation where albumid = arow.albumid;
  end loop;
      for vrow IN (SELECT albumid FROM album where collectionid = collection_id)
      loop
        delete from album where albumid = vrow.albumid;
      end loop;
    delete from collection where collectionid = collection_id;
end DELETE_COLLECTION;
--Delete Recordings
PROCEDURE DELETE_RECORDING (recording_id IN VARCHAR2)AS
BEGIN
  for arow in (SELECT * FROM RECORDING JOIN COMPILATION 
              USING(recid) WHERE recid = recording_id) loop
      delete from compilation where recid = arow.recid;
  END LOOP;
  delete from recording where recid = recording_id;
END DELETE_RECORDING;
END deletepkg;