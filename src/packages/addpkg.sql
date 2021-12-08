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