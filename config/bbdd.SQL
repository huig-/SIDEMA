-- bbdd.SQL
CREATE DATABASE IF NOT EXISTS bbddejemplo;
USE bbddejemplo;

DROP TABLE IF EXISTS TB_ACCESO;

CREATE TABLE TB_ACCESO
(
	user VARCHAR(20) NOT NULL,
	password VARCHAR(10) NOT NULL,
	PRIMARY KEY (user),
	KEY (user)
)
;

INSERT INTO TB_ACCESO VALUES ('root', 'root')
;

INSERT INTO TB_ACCESO VALUES ('user1', 'user1')
;

INSERT INTO TB_ACCESO VALUES ('user2', 'user2')
;