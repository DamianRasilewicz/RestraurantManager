INSERT INTO restaurant_manager.type_of_products (name) VALUE ('Pizza');
INSERT INTO restaurant_manager.type_of_products (name) VALUE ('Danie Główne');
INSERT INTO restaurant_manager.type_of_products (name) VALUE ('Zupy');
INSERT INTO restaurant_manager.type_of_products (name) VALUE ('Napoje');

INSERT INTO restaurant_manager.additions (description, name, price) VALUES ('Pizza', 'Podwójny ser', 2);
INSERT INTO restaurant_manager.additions (description, name, price) VALUES ('Pizza', 'Salami', 2);
INSERT INTO restaurant_manager.additions (description, name, price) VALUES ('Pizza', 'Szynka', 2);
INSERT INTO restaurant_manager.additions (description, name, price) VALUES ('Pizza', 'Pieczarki', 2);

INSERT INTO restaurant_manager.additions (description, name, price) VALUES ('MainCourse', 'Bar sałatkowy', 5);
INSERT INTO restaurant_manager.additions (description, name, price) VALUES ('MainCourse', 'Zestaw sosów', 6);

INSERT INTO restaurant_manager.products (name, type_of_product_id, price) VALUES ('Margheritta', 1, 20);
INSERT INTO restaurant_manager.products (name, type_of_product_id, price) VALUES ('Vegetariana', 1, 22);
INSERT INTO restaurant_manager.products (name, type_of_product_id, price) VALUES ('Tosca', 1, 25);
INSERT INTO restaurant_manager.products (name, type_of_product_id, price) VALUES ('Venecia', 1, 25);

INSERT INTO restaurant_manager.products (name, type_of_product_id, price) VALUES ('Schabowy z frytkami/ryżem/ziemniakami', 2, 30);
INSERT INTO restaurant_manager.products (name, type_of_product_id, price) VALUES ('Ryba z frytkami', 2, 28);
INSERT INTO restaurant_manager.products (name, type_of_product_id, price) VALUES ('Placek po wegiersku', 2, 27);

INSERT INTO restaurant_manager.products (name, type_of_product_id, price) VALUES ('Pomidorowa', 3, 12);
INSERT INTO restaurant_manager.products (name, type_of_product_id, price) VALUES ('Rosół    ', 3, 10);

INSERT INTO restaurant_manager.products (name, type_of_product_id, price) VALUES ('Kawa', 4, 5);
INSERT INTO restaurant_manager.products (name, type_of_product_id, price) VALUES ('Herbata', 4, 5);
INSERT INTO restaurant_manager.products (name, type_of_product_id, price) VALUES ('Cola', 4, 5);

INSERT INTO restaurant_manager.persons (name, last_name, email, phone_number, registered) VALUES ('Janusz', 'Kowalski', 'j.kowalski@gmail.com', '607710555', false);
INSERT INTO restaurant_manager.persons (name, last_name, email, phone_number, registered) VALUES ('Piotr', 'Kwiatkowski', 'p.kwiatkowski@onet.pl', '728956327', false);
INSERT INTO restaurant_manager.persons (name, last_name, email, phone_number, registered) VALUES ('Marcin', 'Jabłoński', 'marcinjablonski@wp.pl', '692532784', false);

INSERT INTO restaurant_manager.addresses (street, building_number, postcode, city, person_id) VALUES ('Głowackiego', '44/2', '65-425', 'Zielona Góra',1);
INSERT INTO restaurant_manager.addresses (street, building_number, postcode, city, person_id) VALUES ('Al. Wojska Polskiego', '67/5', '65-326', 'Zielona Góra',2);
INSERT INTO restaurant_manager.addresses (street, building_number, postcode, city, person_id) VALUES ('Sikorskiego', '29/2', '65-538', 'Zielona Góra',3);

INSERT INTO restaurant_manager.products_additions (product_id, additions_id) VALUES (1, 1);
INSERT INTO restaurant_manager.products_additions (product_id, additions_id) VALUES (1, 2);
INSERT INTO restaurant_manager.products_additions (product_id, additions_id) VALUES (3, 1);
INSERT INTO restaurant_manager.products_additions (product_id, additions_id) VALUES (5, 5);
INSERT INTO restaurant_manager.products_additions (product_id, additions_id) VALUES (6, 6);

INSERT INTO restaurant_manager.orders (order_date, order_time, order_cost, number_of_products, comment, person_id) VALUES ('2021-01-05', '15:01:24',  51.0, 2, '', 1);
INSERT INTO restaurant_manager.orders (order_date, order_time, order_cost, number_of_products, comment, person_id) VALUES ('2021-01-09', '10:20:42',  35.0, 1, '', 2);
INSERT INTO restaurant_manager.orders (order_date, order_time, order_cost, number_of_products, comment, person_id) VALUES ('2021-01-24', '12:34:09', 51.0 , 3, '', 3);

INSERT INTO restaurant_manager.orders_products (order_id, products_id) VALUES (1, 1);
INSERT INTO restaurant_manager.orders_products (order_id, products_id) VALUES (1, 3);
INSERT INTO restaurant_manager.orders_products (order_id, products_id) VALUES (2, 5);
INSERT INTO restaurant_manager.orders_products (order_id, products_id) VALUES (3, 6);
INSERT INTO restaurant_manager.orders_products (order_id, products_id) VALUES (3, 8);
INSERT INTO restaurant_manager.orders_products (order_id, products_id) VALUES (3, 11);


