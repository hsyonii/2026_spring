DROP DATABASE IF EXISTS mydb0903;
CREATE DATABASE mydb0903;
USE mydb0903;
CREATE Table test(
    no INT PRIMARY KEY AUTO_INCREMENT,
    content VARCHAR(100),
    writer VARCHAR(20)
) 

INSERT INTO test(content, writer) VALUES ('안녕하세요.', '황소연'), ('만반잘부~','권유린');