CREATE DATABASE IF NOT EXISTS db_jpa_crud;

USE db_jpa_crud;

CREATE TABLE IF NOT EXISTS products(
    product_id bigint NOT NULL,
    name varchar(45) DEFAULT NULL,
    price int DEFAULT NULL,
    description TEXT DEFAULT NULL
);

ALTER TABLE products ADD PRIMARY KEY (product_id);

ALTER TABLE products MODIFY product_id bigint NOT NULL AUTO_INCREMENT;


























































































