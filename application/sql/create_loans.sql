CREATE TABLE loans (
  id INTEGER AUTO_INCREMENT,
  clientId INTEGER,  
  loanProductId INTEGER,
  amount decimal(15,3),
  interestRate decimal(15,3),
  primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE INDEX loans_id ON loans(id);