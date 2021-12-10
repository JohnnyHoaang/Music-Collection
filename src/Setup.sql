DROP TABLE RECORDING CASCADE CONSTRAINTS;
DROP TABLE CONTRIBUTOR_ROLE CASCADE CONSTRAINTS;
DROP TABLE CONTRIBUTOR CASCADE CONSTRAINTS;
DROP TABLE CONTRIBUTOR_REC CASCADE CONSTRAINTS;
DROP TABLE COLLECTION CASCADE CONSTRAINTS;
DROP TABLE ALBUM CASCADE CONSTRAINTS;
DROP TABLE COMPILATION CASCADE CONSTRAINTS;
DROP TABLE USER_LOGS CASCADE CONSTRAINTS;


CREATE TABLE RECORDING ( 
    recid VARCHAR2(10) PRIMARY KEY,
    rec_date DATE,
    duration NUMBER NOT NULL,
    offset NUMBER NOT NULL
    );
CREATE TABLE CONTRIBUTOR_ROLE (
    roleid VARCHAR2(10) PRIMARY KEY,
    rolename VARCHAR2(20) NOT NULL
    );
    
CREATE TABLE CONTRIBUTOR(
    contributorid VARCHAR2(10) PRIMARY KEY, 
    c_first VARCHAR2(15),
    c_last VARCHAR2(15)
    );
    
CREATE TABLE CONTRIBUTOR_REC (
    recid VARCHAR2(10) REFERENCES RECORDING(recid) ON DELETE CASCADE,
    contributorid VARCHAR2(10) REFERENCES CONTRIBUTOR(contributorid) ON DELETE CASCADE,
    roleid VARCHAR2(10) REFERENCES CONTRIBUTOR_ROLE(roleid) ON DELETE CASCADE
    );
    
    
CREATE TABLE COLLECTION (
    collectionid VARCHAR2(10)PRIMARY KEY,
    name VARCHAR2(10) NOT NULL
    );
    
    
CREATE TABLE ALBUM (
    title VARCHAR2(15),
    albumid VARCHAR2(10) PRIMARY KEY,
    category VARCHAR2(15),
    pubdate DATE,
    collectionid VARCHAR2(10) REFERENCES COLLECTION(collectionid) ON DELETE CASCADE,
    market VARCHAR2(10),
    label VARCHAR2(20)
    );
    
CREATE TABLE COMPILATION (
    recid VARCHAR2(10) REFERENCES RECORDING(recid) ON DELETE CASCADE,
    compilation_date DATE,
    albumid VARCHAR2(10) REFERENCES ALBUM(albumid) ON DELETE CASCADE
    );
    
CREATE TABLE USER_LOGS (
    username VARCHAR2(20),
    changes VARCHAR2(100)
    );
    
    
    