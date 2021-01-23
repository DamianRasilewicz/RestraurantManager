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