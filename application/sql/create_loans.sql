CREATE TABLE loanproducts (
	id INT NOT NULL AUTO_INCREMENT,
	longName VARCHAR(255),
	maxInterestRate DOUBLE,
	minInterestRate DOUBLE,
	shortName VARCHAR(255),
	status INT,
    deletedStatus INT,
	PRIMARY KEY (id)
);

CREATE UNIQUE INDEX shortName ON loanproducts (shortName ASC);


CREATE TABLE loans (
	id INT NOT NULL AUTO_INCREMENT,
	amount DECIMAL(10 , 2),
	clientId INT,
	interestRate DECIMAL(10 , 2),
	loanProductId INT NOT NULL,
	PRIMARY KEY (id),
    disbursalDate DATE
);

CREATE INDEX FK625D983185434A3 ON loans (loanProductId ASC);

ALTER TABLE loans ADD CONSTRAINT FK625D983185434A3 FOREIGN KEY (loanProductId)
	REFERENCES loanproducts (id);