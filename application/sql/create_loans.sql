CREATE TABLE loans (
  id INTEGER AUTO_INCREMENT,
  loanProductId INTEGER,
  amount decimal(15,3),
  interestRate decimal(15,3),
  primary key (id)
);
CREATE INDEX loans_id ON loans(id);