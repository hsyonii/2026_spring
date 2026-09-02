DROP DATABASE IF EXISTS sample;
CREATE DATABASE sample;
USE sample;

CREATE TABLE waiting (
    no INT AUTO_INCREMENT PRIMARY KEY,
    phone VARCHAR(20) NOT NULL,
    headcount INT NOT NULL
);

INSERT INTO waiting (phone, headcount) VALUES ('010-1234-1234', 2);
INSERT INTO waiting (phone, headcount) VALUES ('010-1111-2222', 3);
INSERT INTO waiting (phone, headcount) VALUES ('010-0000-0000', 4);