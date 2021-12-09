DROP PROCEDURE UPDATE_RECORDING;

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
