
CREATE TABLE Client(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nom VARCHAR(50),
	prenom VARCHAR(50),
	age iNT,
	sexe CHAR,
	loc VARCHAR(20),
	login VARCHAR(20) NOT NULL,
	passwd VARCHAR(20) NOT NULL,
	poids INT,-- kg
	taille INT -- cm
);

CREATE TABLE Evolution(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	t date,
	imc INT,
	poids INT,
	id_client INT,
	CONSTRAINT fk_id_client FOREIGN KEY (id_client) REFERENCES Client(id),
);