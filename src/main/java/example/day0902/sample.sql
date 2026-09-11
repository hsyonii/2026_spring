DROP DATABASE IF EXISTS mydb0902;
CREATE DATABASE mydb0902;
USE mydb0902;
CREATE Table exam(
    eno INT AUTO_INCREMENT PRIMARY KEY,
    ename VARCHAR(255)
);

INSERT INTO exam (ename) values ('유재석');
INSERT INTO exam (ename) values ('강호동');
INSERT INTO exam (ename) values ('신동엽');