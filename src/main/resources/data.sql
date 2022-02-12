/*
-- Verity colum and INSERT queries
INSERT INTO user
VALUES (101, 'NY', 'nelson.torres@mercadolibre.com', 'Nelson', 'Torres', 'admin', 'ssn101', 'neltorres');
INSERT INTO user
VALUES (102, 'LA', 'terrence.malick@gmail.com', 'Terrence', 'Malick', 'buyer', 'ssn102', 'tmalick');
INSERT INTO user
VALUES (103, 'Montevideo', 'stanley.kubrick@hotmail.com', 'Stanley', 'kubrick', 'buyer', 'ssn103', 'skubrick');
INSERT INTO user
VALUES (104, 'Caracas', 'julia.torres@yahoo.com', 'Julia', 'Torres', 'admin', 'ssn104', 'jtorres');

-- Verify columns and INSERT queries
INSERT INTO orders
VALUES (2001, 'order 1', 101);
INSERT INTO orders
VALUES (2002, 'order 2', 103);
INSERT INTO orders
VALUES (2003, 'order 3', 102);
INSERT INTO orders
VALUES (2004, 'order 4', 101);
INSERT INTO orders
VALUES (2005, 'order 5', 103);
INSERT INTO orders
VALUES (2006, 'order 6', 104);


-- Verify foreing key name DB before creating queries
INSERT INTO orders (orderid, orderdescription, user_user_id)
VALUES (2001, 'order 1', 101);
INSERT INTO orders (orderid, orderdescription, user_user_id)
VALUES (2002, 'order 2', 103);
INSERT INTO orders (orderid, orderdescription, user_user_id)
VALUES (2003, 'order 3', 102);
INSERT INTO orders (orderid, orderdescription, user_user_id)
VALUES (2004, 'order 1', 101);
INSERT INTO orders (orderid, orderdescription, user_user_id)
VALUES (2005, 'order 2', 103);
INSERT INTO orders (orderid, orderdescription, user_user_id)
VALUES (2006, 'order 3', 104);
*/