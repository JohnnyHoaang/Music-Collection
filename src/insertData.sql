select * from contributor;

INSERT INTO CONTRIBUTOR
VALUES('C001', 'Mike', 'Truman');
INSERT INTO CONTRIBUTOR
VALUES('C002', 'Josh', 'Alexander');
INSERT INTO CONTRIBUTOR
VALUES('C003', 'Chris', 'Garcia');
INSERT INTO CONTRIBUTOR
VALUES('C004', 'Jarad', 'Higgins');
INSERT INTO CONTRIBUTOR
VALUES('C005', 'Arijan', 'Vujica');
INSERT INTO CONTRIBUTOR
VALUES('C006', 'Greg', 'Camp');
INSERT INTO CONTRIBUTOR
VALUES('C007', 'Micheal', 'Urbana');
INSERT INTO CONTRIBUTOR
VALUES('C008', 'Charles', 'Moniz');
INSERT INTO CONTRIBUTOR
VALUES('C009', 'Bruno', 'Mars');
INSERT INTO CONTRIBUTOR
VALUES('C0010', 'Anderson', 'Park');
INSERT INTO CONTRIBUTOR
VALUES('C0011', 'Jonathan', 'Kim');
INSERT INTO CONTRIBUTOR
VALUES('C0012', 'Yoshihiko', 'Nakano');
INSERT INTO CONTRIBUTOR
VALUES('C0013', 'Alan', 'Parsons');
INSERT INTO CONTRIBUTOR
VALUES('C0014', 'Ian', 'Bairnson');
INSERT INTO CONTRIBUTOR
VALUES('C0015', 'Stuart', 'Tosh');
INSERT INTO CONTRIBUTOR
VALUES('C0016', 'Eric', 'Woolfson');
INSERT INTO CONTRIBUTOR
VALUES('C0017', 'Yeri', 'Kang');
INSERT INTO CONTRIBUTOR
VALUES('C0018', 'Michael', 'Park');

INSERT INTO CONTRIBUTOR
VALUES('C0019', 'Lee', 'Taewook');
INSERT INTO CONTRIBUTOR
VALUES('C0020', 'Jungkook', '');


--Obadia
INSERT INTO RECORDING
VALUES('RE001', TO_DATE('1999-01-01 23:59:59', 'yyyy/mm/dd hh24:mi:ss'), 9.31, 0.0);
--Veloso
INSERT INTO RECORDING
VALUES('RE002', TO_DATE('2011-12-31 23:59:59', 'yyyy/mm/dd hh24:mi:ss'), 3.25,2.4);
--Tang
INSERT INTO RECORDING
VALUES('RE003', TO_DATE('1999-01-1 23:59:59', 'yyyy/mm/dd hh24:mi:ss'), 2.35, 6.0);
--Ivan
INSERT INTO RECORDING
VALUES('RE004', TO_DATE('1999-01-1 23:59:59', 'yyyy/mm/dd hh24:mi:ss'), 3.21, 10.0);
--Hoang
INSERT INTO RECORDING
VALUES('RE005', TO_DATE('2019-06-14 23:59:59', 'yyyy/mm/dd hh24:mi:ss'), 5.43, 4.21);
--Internoscia
INSERT INTO RECORDING
VALUES('RE006', TO_DATE('1976-12-31 23:59:59', 'yyyy/mm/dd hh24:mi:ss'), 3.23, 5.26);
--Vu
INSERT INTO RECORDING
VALUES('RE007', TO_DATE('2019-03-15 23:59:59', 'yyyy/mm/dd hh24:mi:ss'), 4.12, 1.43);


--ROLES
INSERT INTO CONTRIBUTOR_ROLE 
VALUES('R001', 'LYRICIST');
INSERT INTO CONTRIBUTOR_ROLE 
VALUES('R002', 'COMPOSER');
INSERT INTO CONTRIBUTOR_ROLE 
VALUES('R003', 'DRUMMER');
INSERT INTO CONTRIBUTOR_ROLE 
VALUES('R004', 'ENGINEER');
INSERT INTO CONTRIBUTOR_ROLE 
VALUES('R005', 'GUITARIST');
INSERT INTO CONTRIBUTOR_ROLE 
VALUES('R006', 'VIOLINIST');
INSERT INTO CONTRIBUTOR_ROLE 
VALUES('R007', 'RECORDER');
INSERT INTO CONTRIBUTOR_ROLE 
VALUES('R008', 'PRODUCER');



--Bridge Table
INSERT INTO CONTRIBUTOR_REC
VALUES('RE001', 'C001', 'R004');
INSERT INTO CONTRIBUTOR_REC
VALUES('RE002', 'C002', 'R004');
INSERT INTO CONTRIBUTOR_REC
VALUES('RE002', 'C002', 'R001');
INSERT INTO CONTRIBUTOR_REC
VALUES('RE002', 'C003', 'R001');
INSERT INTO CONTRIBUTOR_REC
VALUES('RE003', 'C004', 'R001');
INSERT INTO CONTRIBUTOR_REC
VALUES('RE003', 'C005', 'R001');
INSERT INTO CONTRIBUTOR_REC
VALUES('RE004', 'C006', 'R001');
INSERT INTO CONTRIBUTOR_REC
VALUES('RE004', 'C006', 'R005');
INSERT INTO CONTRIBUTOR_REC
VALUES('RE004', 'C007', 'R003');
INSERT INTO CONTRIBUTOR_REC
VALUES('RE005', 'C008', 'R004');
INSERT INTO CONTRIBUTOR_REC
VALUES('RE005', 'C008', 'R007');
INSERT INTO CONTRIBUTOR_REC
VALUES('RE005', 'C009', 'R002');
INSERT INTO CONTRIBUTOR_REC
VALUES('RE005', 'C009', 'R005');
INSERT INTO CONTRIBUTOR_REC
VALUES('RE005', 'C009', 'R008');
INSERT INTO CONTRIBUTOR_REC
VALUES('RE005', 'C0010', 'R001');
INSERT INTO CONTRIBUTOR_REC
VALUES('RE005', 'C0011', 'R006');
INSERT INTO CONTRIBUTOR_REC
VALUES('RE005', 'C0012', 'R006');
INSERT INTO CONTRIBUTOR_REC
VALUES('RE006', 'C0013', 'R004');
INSERT INTO CONTRIBUTOR_REC
VALUES('RE006', 'C0013', 'R008');
INSERT INTO CONTRIBUTOR_REC
VALUES('RE006', 'C0014', 'R005');
INSERT INTO CONTRIBUTOR_REC
VALUES('RE006', 'C0015', 'R003');
INSERT INTO CONTRIBUTOR_REC
VALUES('RE005', 'C0016', 'R002');
INSERT INTO CONTRIBUTOR_REC
VALUES('RE006', 'C0016', 'R001');
INSERT INTO CONTRIBUTOR_REC
VALUES('RE007', 'C0017', 'R005');
INSERT INTO CONTRIBUTOR_REC
VALUES('RE007', 'C0018', 'R004');
INSERT INTO CONTRIBUTOR_REC
VALUES('RE007', 'C0019', 'R005');
INSERT INTO CONTRIBUTOR_REC
VALUES('RE007', 'C0020', 'R001');

--Collection
--User enters Album Name
INSERT INTO COLLECTION
VALUES('COL01', 'MY MIX');


--Album -> Song
--User enters Song Name
--Both songs in one collection
INSERT INTO ALBUM
VALUES('CHICKEN TENDERS', 'AL001', null, SYSDATE, 'COL01', 'Worldwide', 'df');
INSERT INTO ALBUM
VALUES('MY SECOND SONG', 'AL002', null, SYSDATE, 'COL01', 'Spain', 's');


--Compilation

--First Song
INSERT INTO COMPILATION
VALUES('RE001', SYSDATE, 'AL001');
INSERT INTO COMPILATION
VALUES('RE002', SYSDATE, 'AL001');
INSERT INTO COMPILATION
VALUES('RE003', SYSDATE, 'AL001');

--Second Song
INSERT INTO COMPILATION
VALUES('RE001', SYSDATE, 'AL002');
INSERT INTO COMPILATION
VALUES('RE004', SYSDATE, 'AL002');
INSERT INTO COMPILATION
VALUES('RE002', SYSDATE, 'AL002');

select * from album JOIN COMPILATION USING(albumid) 
JOIN RECORDING USING (recid) JOIN CONTRIBUTOR_REC USING(recid) JOIN CONTRIBUTOR
USING(contributorid);



