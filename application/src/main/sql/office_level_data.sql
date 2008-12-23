insert into OFFICE_LEVEL (ID, NAME, LEVEL_ABOVE_ID, LEVEL_BELOW_ID) values (1,'Head Office', null, null); 
insert into OFFICE_LEVEL (ID, NAME, LEVEL_ABOVE_ID, LEVEL_BELOW_ID) values (2,'Branch Office', 1, null ); 
update OFFICE_LEVEL set LEVEL_BELOW_ID=2 where ID=1;


