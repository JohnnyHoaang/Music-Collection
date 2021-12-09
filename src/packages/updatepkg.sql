DROP PACKAGE updatepkg;
/
CREATE OR REPLACE PACKAGE updatepkg AS
PROCEDURE UPDATE_RECORDING(rec_id IN VARCHAR2, vdate DATE, vduration IN NUMBER, voffset IN NUMBER);
PROCEDURE UPDATE_ALBUM(valbum_id IN VARCHAR2, vtitle in varchar2, vcategory in varchar2, vpubdate in date, vcollectionid in varchar2, vmarket in varchar2, vlabel in varchar2);
PROCEDURE UPDATE_COLLECTION (vcollectionid IN VARCHAR2, vname IN VARCHAR2);
PROCEDURE UPDATE_CONTRIBUTOR_ROLE(vroleid IN VARCHAR2, vrolename IN VARCHAR2);
PROCEDURE UPDATE_CONTRIBUTOR_REC(rec_id IN VARCHAR2, vcontributorid IN VARCHAR2, vroleid in VARCHAR2);
PROCEDURE UPDATE_CONTRIBUTOR(vcontributorid IN VARCHAR2, vfirst IN VARCHAR2, vlast IN VARCHAR2);
PROCEDURE UPDATE_COMPILATION(rec_id IN VARCHAR2, vdate DATE, valbumid IN VARCHAR2);
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


PROCEDURE UPDATE_ALBUM (valbum_id IN VARCHAR2, vtitle in varchar2, vcategory in varchar2, vpubdate in date, vcollectionid in varchar2, vmarket in varchar2, vlabel in varchar2)
AS
BEGIN
    UPDATE ALBUM
    SET title = vtitle,
    category = vcategory,
    pubdate = vpubdate,
    collectionid = vcollectionid,
    market = vmarket,
    label = vlabel
    WHERE albumid = valbum_id;
END UPDATE_ALBUM;

PROCEDURE UPDATE_COLLECTION (vcollectionid IN VARCHAR2, vname IN VARCHAR2)
AS
BEGIN
    UPDATE ALBUM
    SET collection_id = vcollectionid,
    name = vname
    WHERE collection_id = vcollectionid;
END UPDATE_COLLECTION;
PROCEDURE UPDATE_CONTRIBUTOR (vcontributorid IN VARCHAR2, vfirst IN VARCHAR2, vlast IN VARCHAR2)
AS
BEGIN
    UPDATE CONTRIBUTOR
    SET contributorid = vcontributorid, 
    cfirst = vfirst,
    clast = vlast
    WHERE contributorid = vcontributorid;
END UPDATE_CONTRIBUTOR;
PROCEDURE UPDATE_CONTRIBUTOR_REC (rec_id IN VARCHAR2, vcontributorid IN VARCHAR2, vroleid in VARCHAR2)
AS
BEGIN
    UPDATE CONTRIBUTOR_REC
    SET  
    recId = rec_id,
    contributorid = vcontributorid
    roleId = vroleid
    WHERE recid = rec_id;
END UPDATE_CONTRIBUTOR_REC;
PROCEDURE UPDATE_CONTRIBUTOR_ROLE (vroleid IN VARCHAR2, vrolename IN VARCHAR2)
BEGIN
    UPDATE CONTRIBUTOR_ROLE
    SET roleId = vroleid,
    vrolename = vrolename
    WHERE roleId = vroleid,
END UPDATE_CONTRIBUTOR_ROLE;
PROCEDURE UPDATE_COMPILATION (rec_id IN VARCHAR2, vdate DATE, valbumid IN VARCHAR2)
AS
BEGIN
    UPDATE ALBUM
    SET rec_date = vdate, 
    recId = rec_id,
    albumId = valbum_id
    WHERE recid = rec_id;
END UPDATE_COMPILATION;
-- --Ashley ways of thinking for everything for once
-- public static void updateField(param){

-- }

END updatepkg;

CREATE OR REPLACE PROCEDURE UPDATE_COLLECTIONID(album_id IN VARCHAR2, collection_id IN VARCHAR2)
AS
BEGIN
    UPDATE FROM ALBUM SET collectionid = collection_id WHERE collectionid = collection_id;
END;


