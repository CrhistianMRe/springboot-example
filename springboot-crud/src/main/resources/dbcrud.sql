CREATE DATABASE IF NOT EXISTS db_jpa_crud;

USE db_jpa_crud;

CREATE TABLE IF NOT EXISTS products(
    product_id bigint NOT NULL,
    name varchar(45) DEFAULT NULL,
    price int DEFAULT NULL,
    description TEXT DEFAULT NULL,
    sku varchar(45) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS users(
    user_id bigint NOT NULL,
    username varchar(18) NOT NULL,
    password varchar(60) NOT NULL,
    enabled boolean NOT NULL DEFAULT 1
     
);

ALTER TABLE products ADD PRIMARY KEY (product_id);
ALTER TABLE users ADD PRIMARY KEY (user_id);

ALTER TABLE products MODIFY product_id bigint NOT NULL AUTO_INCREMENT;
ALTER TABLE users MODIFY user_id bigint NOT NULL AUTO_INCREMENT;


























































































