--Contributor
--INSERT
CREATE OR REPLACE TRIGGER LOG_CONTRIBUTOR_INSERT
BEFORE INSERT
ON CONTRIBUTOR
DECLARE
    username VARCHAR2(50):= 'USER';
BEGIN
    SELECT user into username 
    from dual;
    INSERT INTO USER_LOGS
    VALUES(username,'Inserted into the Contributor Table');
END;

/
--DELETE
CREATE OR REPLACE TRIGGER LOG_CONTRIBUTOR_DELETE
BEFORE DELETE
ON CONTRIBUTOR
DECLARE
    username VARCHAR2(50):= 'USER';
BEGIN
    SELECT user into username 
    from dual;
    INSERT INTO USER_LOGS
    VALUES(username,'Delete into the Contributor Table');
END;
/

--UPDATE
CREATE OR REPLACE TRIGGER LOG_CONTRIBUTOR_UPDATE
BEFORE UPDATE
ON CONTRIBUTOR
DECLARE
    username VARCHAR2(50):= 'USER';
BEGIN
    SELECT user into username 
    from dual;
    INSERT INTO USER_LOGS
    VALUES(username,'Updated into the Contributor Table');
END;
/


--Contributor_Role

--INSERT
CREATE OR REPLACE TRIGGER LOG_CONTRIBUTOR_ROLE_INSERT
BEFORE INSERT
ON CONTRIBUTOR_ROLE
DECLARE
    username VARCHAR2(50):= 'USER';
BEGIN
    SELECT user into username 
    from dual;
    INSERT INTO USER_LOGS
    VALUES(username,'Inserted into the Contributor Role Table');
END;
/

--DELETE
CREATE OR REPLACE TRIGGER LOG_CONTRIBUTOR_ROLE_DELETE
BEFORE DELETE
ON CONTRIBUTOR_ROLE
DECLARE
    username VARCHAR2(50):= 'USER';
BEGIN
    SELECT user into username 
    from dual;
    INSERT INTO USER_LOGS
    VALUES(username,'Delete into the Contributor Role Table');
END;
/

--UPDATE
CREATE OR REPLACE TRIGGER LOG_CONTRIBUTOR_ROLE_UPDATE
BEFORE UPDATE
ON CONTRIBUTOR_ROLE
DECLARE
    username VARCHAR2(50):= 'USER';
BEGIN
    SELECT user into username 
    from dual;
    INSERT INTO USER_LOGS
    VALUES(username,'Updated into the Contributor Role Table');
END;
/



--Recording

--INSERT
CREATE OR REPLACE TRIGGER LOG_RECORDING_INSERT
BEFORE INSERT
ON RECORDING
DECLARE
    username VARCHAR2(50):= 'USER';
BEGIN
    SELECT user into username 
    from dual;
    INSERT INTO USER_LOGS
    VALUES(username,'Inserted into the Recording Table');
END;
/

--DELETE
CREATE OR REPLACE TRIGGER LOG_RECORDING_DELETE
BEFORE DELETE
ON RECORDING
DECLARE
    username VARCHAR2(50):= 'USER';
BEGIN
    SELECT user into username 
    from dual;
    INSERT INTO USER_LOGS
    VALUES(username,'Delete into the Recording Table');
END;
/

--UPDATE
CREATE OR REPLACE TRIGGER LOG_RECORDING_UPDATE
BEFORE UPDATE
ON RECORDING
DECLARE
    username VARCHAR2(50):= 'USER';
BEGIN
    SELECT user into username 
    from dual;
    INSERT INTO USER_LOGS
    VALUES(username,'Updated into the Recording Table');
END;
/

--Collection

--INSERT
CREATE OR REPLACE TRIGGER LOG_COLLECTION_INSERT
BEFORE INSERT
ON COLLECTION
DECLARE
    username VARCHAR2(50):= 'USER';
BEGIN
    SELECT user into username 
    from dual;
    INSERT INTO USER_LOGS
    VALUES(username,'Inserted into the Collection Table');
END;
/

--DELETE
CREATE OR REPLACE TRIGGER LOG_COLLECTION_DELETE
BEFORE DELETE
ON COLLECTION
DECLARE
    username VARCHAR2(50):= 'USER';
BEGIN
    SELECT user into username 
    from dual;
    INSERT INTO USER_LOGS
    VALUES(username,'Delete into the Collection Table');
END;
/

--UPDATE
CREATE OR REPLACE TRIGGER LOG_COLLECTION_UPDATE
BEFORE UPDATE
ON COLLECTION
DECLARE
    username VARCHAR2(50):= 'USER';
BEGIN
    SELECT user into username 
    from dual;
    INSERT INTO USER_LOGS
    VALUES(username,'Updated into the Collection Table');
END;

/
--Album
--INSERT
CREATE OR REPLACE TRIGGER LOG_ALBUM_INSERT
BEFORE INSERT
ON ALBUM
DECLARE
    username VARCHAR2(50):= 'USER';
BEGIN
    SELECT user into username 
    from dual;
    INSERT INTO USER_LOGS
    VALUES(username,'Inserted into the Album Table');
END;
/

--DELETE
CREATE OR REPLACE TRIGGER LOG_ALBUM_DELETE
BEFORE DELETE
ON ALBUM
DECLARE
    username VARCHAR2(50):= 'USER';
BEGIN
    SELECT user into username 
    from dual;
    INSERT INTO USER_LOGS
    VALUES(username,'Delete into the Album Table');
END;
/

--UPDATE
CREATE OR REPLACE TRIGGER LOG_ALBUM_UPDATE
BEFORE UPDATE
ON ALBUM
DECLARE
    username VARCHAR2(50):= 'USER';
BEGIN
    SELECT user into username 
    from dual;
    INSERT INTO USER_LOGS
    VALUES(username,'Updated into the Album Table');
END;
/
--Compilation
--INSERT
CREATE OR REPLACE TRIGGER LOG_COMPILATION_INSERT
BEFORE INSERT
ON COMPILATION
DECLARE
    username VARCHAR2(50):= 'USER';
BEGIN
    SELECT user into username 
    from dual;
    INSERT INTO USER_LOGS
    VALUES(username,'Inserted into the Compilation Table');
END;
/

--DELETE
CREATE OR REPLACE TRIGGER LOG_COMPILATION_DELETE
BEFORE DELETE
ON COMPILATION
DECLARE
    username VARCHAR2(50):= 'USER';
BEGIN
    SELECT user into username 
    from dual;
    INSERT INTO USER_LOGS
    VALUES(username,'Delete into the Compilation Table');
END;
/

--UPDATE
CREATE OR REPLACE TRIGGER LOG_COMPILATION_UPDATE
BEFORE UPDATE
ON COMPILATION
DECLARE
    username VARCHAR2(50):= 'USER';
BEGIN
    SELECT user into username 
    from dual;
    INSERT INTO USER_LOGS
    VALUES(username,'Updated into the Compilation Table');
END;

