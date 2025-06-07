--hay que usar el use por separado

USE db_springboot

CREATE TABLE persons(
    person_id bigint NOT NULL AUTO_INCREMENT,
    lastname varchar(255),
    name varchar(255),
    programming_language varchar(255),
    PRIMARY KEY(person_id)
);

DROP TABLE persons;


ALTER TABLE persons
ADD update_at DATETIME DEFAULT NUll;

ALTER TABLE persons
ADD create_at DATETIME DEFAULT NUll;