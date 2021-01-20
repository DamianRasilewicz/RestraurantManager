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

INSERT INTO restaurant_manager.products (name, type_of_products_id, quantity, price, final_price) VALUES ('Margheritta', 1, 1, 20, 20);
INSERT INTO restaurant_manager.products (name, type_of_products_id, quantity, price, final_price) VALUES ('Vegetariana', 1, 1, 22, 22);
INSERT INTO restaurant_manager.products (name, type_of_products_id, quantity, price, final_price) VALUES ('Tosca', 1, 1, 25, 25);
INSERT INTO restaurant_manager.products (name, type_of_products_id, quantity, price, final_price) VALUES ('Venecia', 1, 1, 25, 25);

INSERT INTO restaurant_manager.products (name, type_of_products_id, quantity, price, final_price) VALUES ('Schabowy z frytkami/ryżem/ziemniakami', 2, 1, 30, 30);
INSERT INTO restaurant_manager.products (name, type_of_products_id, quantity, price, final_price) VALUES ('Ryba z frytkami', 2, 1, 28, 28);
INSERT INTO restaurant_manager.products (name, type_of_products_id, quantity, price, final_price) VALUES ('Placek po wegiersku', 2, 1, 27, 27);

INSERT INTO restaurant_manager.products (name, type_of_products_id, quantity, price, final_price) VALUES ('Pomidorowa', 3, 1, 12, 12);
INSERT INTO restaurant_manager.products (name, type_of_products_id, quantity, price, final_price) VALUES ('Rosół    ', 3, 1, 10, 10);

INSERT INTO restaurant_manager.products (name, type_of_products_id, quantity, price, final_price) VALUES ('Kawa', 4, 1, 5, 5);
INSERT INTO restaurant_manager.products (name, type_of_products_id, quantity, price, final_price) VALUES ('Herbata', 4, 1, 5, 5);
INSERT INTO restaurant_manager.products (name, type_of_products_id, quantity, price, final_price) VALUES ('Cola', 4, 1, 5, 5);