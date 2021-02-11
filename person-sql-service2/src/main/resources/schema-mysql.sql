--
DROP TABLE IF EXISTS person;

CREATE TABLE person (
    id BIGINT NOT NULL AUTO_INCREMENT primary key,
    age INT NOT NULL,
    name VARCHAR(50) NOT NULL
);