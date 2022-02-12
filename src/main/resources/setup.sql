use
blocksdb;

CREATE TABLE order
(
    `order_id`          BIGINT UNSIGNED NOT NULL,
    `order_description` varchar(50) DEFAULT NULL,
    `user_id`           BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY (`order_id`),
    CONSTRAINT `fk_order_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

create table user
(
    `user_id`   BIGINT UNSIGNED NOT NULL,
    `address`   varchar(255) DEFAULT NULL,
    `email`     varchar(50) NOT NULL,
    `firstname` varchar(50) NOT NULL,
    `lastname`  varchar(50) NOT NULL,
    `role`      varchar(50) NOT NULL,
    `ssn`       varchar(50) NOT NULL,
    `order_id`  BIGINT UNSIGNED DEFAULT NULL,
    `username`  varchar(50) NOT NULL,

    PRIMARY KEY (`user_id`),
    CONSTRAINT `fk_user_order` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`)
);

INSERT INTO user
VALUES (101, 'NY', 'nelson.torres@mercadolibre.com', 'Nelson', 'Torres', 'admin', 'ssn101',
        'neltorres');
INSERT INTO user
VALUES (102, 'LA', 'terrence.malick@gmail.com', 'Terrence', 'Malick', 'buyer', 'ssn102', 'tmalick');
INSERT INTO user
VALUES (103, 'Montevideo', 'stanley.kubrick@hotmail.com', 'Stanley', 'kubrick', 'buyer', 'ssn103',
        'skubrick');
INSERT INTO user
VALUES (104, 'Caracas', 'julian.torres@yahoo.com', 'Julian', 'Torres', 'admin', 'ssn104', 'jtorres');

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

