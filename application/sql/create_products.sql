CREATE TABLE products (
  id INTEGER AUTO_INCREMENT,
  description varchar(255),
  price decimal(15,2),
  primary key (id)
);
CREATE INDEX products_id ON products(id);