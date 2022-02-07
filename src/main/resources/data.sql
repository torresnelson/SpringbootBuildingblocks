/*
use blocksdb;

-- Verity colum and insert queries
insert into user
values (101, 'nelson.torres@mercadolibre.com', 'Nelson', 'Torres', 'admin', 'ssn101', 'neltorres');
insert into user
values (102, 'terrence.malick@gmail.com', 'Terrence', 'Malick', 'buyer', 'ssn102', 'tmalick');
insert into user
values (103, 'stanley.kubrick@hotmail.com', 'Stanley', 'kubrick', 'buyer', 'ssn103', 'skubrick');
insert into user
values (104, 'julian.torres@yahoo.com', 'Julian', 'Torres', 'admin', 'ssn104', 'jtorres');

-- Verify columns and insert queries
insert into orders
values (2001, 'order 1', 101);
insert into orders
values (2002, 'order 2', 103);
insert into orders
values (2003, 'order 3', 102);
insert into orders
values (2004, 'order 4', 101);
insert into orders
values (2005, 'order 5', 103);
insert into orders
values (2006, 'order 6', 104);*/
/*
-- Verify foreing key name DB before creating queries
insert into orders (orderid, orderdescription, user_user_id)
values (2001, 'order 1', 101);
insert into orders (orderid, orderdescription, user_user_id)
values (2002, 'order 2', 103);
insert into orders (orderid, orderdescription, user_user_id)
values (2003, 'order 3', 102);
insert into orders (orderid, orderdescription, user_user_id)
values (2004, 'order 1', 101);
insert into orders (orderid, orderdescription, user_user_id)
values (2005, 'order 2', 103);
insert into orders (orderid, orderdescription, user_user_id)
values (2006, 'order 3', 104);
*/
