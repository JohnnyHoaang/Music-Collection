DROP FUNCTION COUNT_RECID;
DROP PROCEDURE DELETE_CONTRIBUTOR;
DROP PROCEDURE DELETE_SONG;
DROP PROCEDURE DELETE_COLLECTION;

CREATE OR REPLACE FUNCTION COUNT_RECID(contributor_id IN VARCHAR2)
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
  end loop;
      for vrow IN (SELECT albumid FROM album where collectionid = collection_id)
      loop
        delete from album where albumid = vrow.albumid;
      end loop;
    delete from collection where collectionid = collection_id;
end;
