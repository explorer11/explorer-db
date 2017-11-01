drop schema if exists catalogue CASCADE;
CREATE SCHEMA CATALOGUE AUTHORIZATION operator;
SET SEARCH_PATH TO CATALOGUE;

DROP TABLE IF EXISTS MODELCLASS;

CREATE TABLE MODELCLASS (
  ID bigserial PRIMARY KEY,
  CODE varchar(256) NOT NULL,
  TITRE varchar(256) NOT NULL,
  DESCRIPTION varchar(256),
  DUREE int
);