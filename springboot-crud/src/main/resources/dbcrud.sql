CREATE DATABASE IF NOT EXISTS db_jpa_crud;

USE db_jpa_crud;

CREATE TABLE IF NOT EXISTS products(
    product_id bigint NOT NULL,
    name varchar(45) DEFAULT NULL,
    price int DEFAULT NULL,
    description TEXT DEFAULT NULL,
    sku varchar(45) DEFAULT NULL
);

ALTER TABLE products ADD PRIMARY KEY (product_id);
ALTER TABLE products MODIFY product_id bigint NOT NULL AUTO_INCREMENT;


CREATE TABLE IF NOT EXISTS users(
    user_id bigint NOT NULL,
    username varchar(18) NOT NULL,
    password varchar(60) NOT NULL,
    enabled boolean NOT NULL DEFAULT 1
     
);

ALTER TABLE users ADD PRIMARY KEY (user_id);
ALTER TABLE users MODIFY user_id bigint NOT NULL AUTO_INCREMENT;
ALTER TABLE users ADD UNIQUE INDEX username_UNIQUE (username ASC) VISIBLE;

CREATE TABLE IF NOT EXISTS roles(
    role_id bigint NOT NULL,
    name varchar(45) NOT NULL
);

ALTER TABLE roles ADD PRIMARY KEY (role_id);
ALTER TABLE roles MODIFY role_id bigint NOT NULL AUTO_INCREMENT;
ALTER TABLE roles ADD UNIQUE INDEX name_UNIQUE (name ASC) VISIBLE;


CREATE TABLE IF NOT EXISTS users_roles(
    user_id bigint NOT NULL,
    role_id bigint NOT NULL
);

ALTER TABLE users_roles ADD PRIMARY KEY(user_id, role_id);
ALTER TABLE users_roles ADD CONSTRAINT FK_users FOREIGN KEY (user_id) REFERENCES users(user_id);
ALTER TABLE users_roles ADD CONSTRAINT FK_roles FOREIGN KEY (role_id) REFERENCES roles(role_id);


INSERT INTO roles(name) VALUES('ROLE_ADMIN');
INSERT INTO roles(name) VALUES('ROLE_USER');


    

























































































