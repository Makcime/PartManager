CREATE DATABASE IF NOT EXISTS part_manager;

USE part_manager;

CREATE TABLE Package(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(50) UNIQUE,
	type_id INT
);

CREATE TABLE Category(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(50) UNIQUE
);

CREATE TABLE Supplier(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(50) UNIQUE,
	url VARCHAR(50)
);

CREATE TABLE Part(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	value VARCHAR(50),
	unit_price DOUBLE(8, 3),
	name VARCHAR(50),
	in_stock INT NOT NULL, 
	sup_ref VARCHAR(50) UNIQUE,
	sup_id INT,
	cat_id INT,
	pack_id INT,
	CONSTRAINT fk_sup_id FOREIGN KEY (sup_id) REFERENCES Supplier(id),
	CONSTRAINT fk_cat_id FOREIGN KEY (cat_id) REFERENCES Category(id),
	CONSTRAINT fk_pack_id FOREIGN KEY (pack_id) REFERENCES Package(id)
);

CREATE TABLE User(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(50) ,
	first_name VARCHAR(50),
	login VARCHAR(50) NOT NULL UNIQUE,
	passwd VARCHAR(25) NOT NULL,
	cp VARCHAR(10)
);

CREATE TABLE Project(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	user_id INT DEFAULT 0, -- default user is John Doe (admin)
	name VARCHAR(50) UNIQUE,
	description TEXT,
	CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES User(id)
);

CREATE TABLE Bom(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	project_id INT,
	part_id INT,
	qty INT,
	CONSTRAINT fk_project_id FOREIGN KEY (project_id) REFERENCES Project(id),
	CONSTRAINT fk_part_id FOREIGN KEY (part_id) REFERENCES Part(id)
);

INSERT INTO User(name, first_name, login, passwd, cp)
	VALUES	('Doe', 'John', 'admin', 'passwd', 1000),
			('Dupont', 'Jean', 'jdupont', 'azerty', 6001);	

INSERT INTO Supplier(name, url)
	VALUES	('Farnell', 'http://be.farnell.com/'),
			('RS', 'befr.rs-online.com/');	

INSERT INTO Part(name, value, unit_price, in_stock, cat_id, sup_id, sup_ref)
	VALUES	('R100k', '100k', 0.3, 10, 1, 1, '21144');

-- INSERT INTO Category(name)
-- 	VALUES	();

-- INSERT INTO Package(name, type_id)
-- 	VALUES	();	

-- INSERT INTO Project(name, user_id, description)
-- 	VALUES	();	
