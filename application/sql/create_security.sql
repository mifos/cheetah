create table users( 
  username varchar(20) not null,
  password varchar(32) not null,
  enabled  smallint,
  primary key (username)
);

create table authorities(
  username varchar(20) not null,
  authority varchar(20) not null,
  foreign key (username) references users(username)
);


