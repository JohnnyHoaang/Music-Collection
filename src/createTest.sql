
INSERT INTO CONTRIBUTOR
VALUES('C004','ASHLEY','VU');
INSERT INTO CONTRIBUTOR
VALUES('C002','JOHNNY','HOANG');
INSERT INTO CONTRIBUTOR
VALUES('C003','DOMENICO','CUSCUNA');

--INSERT INTO CONTRIBUTOR_ROLE 
--VALUES('R001', 'SINGER');
--INSERT INTO CONTRIBUTOR_ROLE
--VALUES('R002', 'DANCER');
--INSERT INTO CONTRIBUTOR_ROLE
--VALUES('R003', 'COMPOSER');
--INSERT INTO CONTRIBUTOR_ROLE
--VALUES('R004', 'DIRECTOR');

INSERT INTO RECORDING
VALUES('RE001', SYSDATE, 230, 15);
INSERT INTO RECORDING
VALUES('RE002', SYSDATE, 134,0);
INSERT INTO RECORDING
VALUES('RE003', SYSDATE, 56, 30);

INSERT INTO CONTRIBUTOR_REC
VALUES('RE001', 'C004', 'R001');
INSERT INTO CONTRIBUTOR_REC
VALUES('RE002', 'C002', 'R002');
INSERT INTO CONTRIBUTOR_REC
VALUES('RE001', 'C003', 'R003');
INSERT INTO CONTRIBUTOR_REC
VALUES('RE001', 'C001', 'R004');

INSERT INTO COLLECTION
VALUES('COL01', 'BIGMAC');
INSERT INTO COLLECTION
VALUES('COL02', 'LILMAC');
INSERT INTO COLLECTION
VALUES('COL03', 'MEDIUMMAC');
INSERT INTO COLLECTION
VALUES ('COL04', 'SUPAHBIG');


INSERT INTO ALBUM
VALUES('MOMMAMOMMA', 'AL004', 'POP', SYSDATE, 'COL04', 'ITALIA', 'SPAGHETTI MAFIA');
INSERT INTO ALBUM
VALUES('PAPAJOHNS','AL001', 'ROCK', SYSDATE, 'COL01', 'WORLDWIDE', 'SOMERECORDS');
INSERT INTO ALBUM
VALUES('EEE','AL002', 'ROCK', SYSDATE, 'COL02', 'WORLDWIDE', 'SRECORDS');
INSERT INTO ALBUM
VALUES('MUSIC','AL003', 'ROCK', SYSDATE, 'COL03', 'CANADA', 'SOMERECORDS');


INSERT INTO COMPILATION
VALUES('RE001', SYSDATE, 'AL003');
INSERT INTO COMPILATION
VALUES('RE003', SYSDATE, 'AL002');
INSERT INTO COMPILATION
VALUES('RE003', SYSDATE, 'AL003');


INSERT INTO COMPILATION
VALUES('RE001', SYSDATE, 'AL004');
INSERT INTO COMPILATION
VALUES('RE002', SYSDATE, 'AL004');
INSERT INTO COMPILATION
VALUES('RE003', SYSDATE, 'AL004');

--select (c_first || ' ' || c_last) AS FULLNAME, title, recid, offset, duration from album JOIN COMPILATION USING(albumid) 
--JOIN RECORDING USING (recid) JOIN CONTRIBUTOR_REC USING(recid) JOIN CONTRIBUTOR
--USING(contributorid) where albumid = 'AL004';

select * from album JOIN COMPILATION USING(albumid) 
JOIN RECORDING USING (recid) JOIN CONTRIBUTOR_REC USING(recid) JOIN CONTRIBUTOR
USING(contributorid) where albumid = 'AL004';

select * from collection;
execute delete_collection('COL04');


SELECT * FROM USER_LOGS;
--they work
execute deletepkg.delete_contributor('C001');
execute deletepkg.delete_song('AL004');
execute deletepkg.delete_collection('COL01');
execute deletepkg.delete_collection('COL04');

select * from contributor;
Select * from album;
select * from collection;

INSERT INTO ALBUM (albumid,title,category)
VALUES('AL009', 'RANDOM SONG', 'TESTER');
select * from album where collectionid IS NULL;
SELECT * FROM ALBUM;
--ON UPDATE--
--ON DELETE--

--SELECT * FROM COMPILATION;

--SELECT * FROM ALBUM;
--SELECT * FROM COLLECTION;
--
--
--
--SELECT TITLE 
--FROM ALBUM
--INNER JOIN COLLECTION
--    USING(COLLECTIONID)
--WHERE collectionid = 'COL03';



--SELECT * FROM CONTRIBUTOR;
--SELECT * FROM CONTRIBUTOR_REC;
--SELECT * FROM CONTRIBUTOR_ROLE;
--SELECT * FROM RECORDING;
--
--select c_first, rolename from contributor_role JOIN CONTRIBUTOR_REC 
--USING(roleid) JOIN CONTRIBUTOR USING(contributorid);


