insert into users (username, password, enabled) values ('admin', '5f4dcc3b5aa765d61d8327deb882cf99', 1); 
insert into users (username, password, enabled) values ('user1', '5f4dcc3b5aa765d61d8327deb882cf99', 1);
insert into users (username, password, enabled) values ('user2', '5f4dcc3b5aa765d61d8327deb882cf99', 0); 

insert into authorities (username, authority) values ('admin', 'ROLE_ADMIN');
insert into authorities (username, authority) values ('admin', 'ROLE_USER');
insert into authorities (username, authority) values ('user1', 'ROLE_USER');
insert into authorities (username, authority) values ('user2', 'ROLE_USER'); 

