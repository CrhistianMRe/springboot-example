CREATE DATABASE db_jpa_relationship;

USE db_jpa_relationship;

--TABLE CLIENTS
CREATE TABLE clients(
    client_id bigint NOT NULL,
    lastname varchar(255),
    name varchar(255)
);

--TABLE INVOICES
CREATE TABLE invoices(
    invoice_id bigint NOT NULL,
    client_id bigint NOT NULL,
    description varchar(255),
    total bigint NOT NULL
);

--PRIMARY
ALTER TABLE clients ADD PRIMARY KEY(client_id);
ALTER TABLE invoices ADD PRIMARY KEY(invoice_id);

--AUTO_INCREMENT con tipos
ALTER TABLE clients MODIFY client_id bigint NOT NULL AUTO_INCREMENT;
ALTER TABLE invoices MODIFY invoice_id bigint NOT NULL AUTO_INCREMENT;

--FK 
ALTER TABLE invoices ADD CONSTRAINT FK_ClientInvoice
FOREIGN KEY (client_id) REFERENCES clients(client_id)

--INSERTS 

INSERT INTO clients (name, lastname) VALUES ('Pepe', 'Doe');
INSERT INTO clients (name, lastname) VALUES ('Maria', 'Roe');


