INSERT INTO employee(email, functional_level, name, password, role) VALUES ('jeroen@skynet.be', '2', 'jeroen', '$2a$10$v8Y.TvnxrqZCfIScuzNchesRzpYAAfM1yfHGuW50l/qpWqLd9NERe', 'ROLE_FLEET');
INSERT INTO employee(email, functional_level, name, password, role) VALUES ('r.dhaese92@gmail.com', '4', 'robin', '$2a$10$2SfKBifGwHep6e5y79KDJOjfgq3CmLdsyoh44MEmYPiWFvevmbfmq', 'ROLE_FLEET');

INSERT INTO car_option(description, name) VALUES ('GPS van tomtom', 'GPS');
INSERT INTO car_option(description, name) VALUES ('achterlichten in LED-techniek', 'LED achterlichten');
INSERT INTO car_option(name) VALUES ('Cruise control');
INSERT INTO car_option(name) VALUES ('Dakrails');
INSERT INTO car_option(name) VALUES ('Trekhaak');
INSERT INTO car_option(name) VALUES ('Automaat');
INSERT INTO car_option(name) VALUES ('7-zits');
INSERT INTO car_option(description, name) VALUES ('indien mogelijkheid voorzien door constructeur', 'Volwaardig reservewiel');
INSERT INTO car_option(name) VALUES ('Parkeersensoren');
INSERT INTO car_option(name) VALUES ('lederen intereur');
INSERT INTO car_option(name) VALUES ('Verwarmde zetels');
INSERT INTO car_option(name) VALUES ('Getinte ruiten');


INSERT INTO pack(name, price, version) VALUES ('Pack Business @ GPS Discover Media', '299', '0');
INSERT INTO pack(name, price, version) VALUES ('Pack Business gps rns315 / pre gsm', '399', '0');
INSERT INTO pack(name, price, version) VALUES ('Business Media Pack', '255', '0');

INSERT INTO pack_car_options(pack_id, car_options_id) VALUES ('1', '4');
INSERT INTO pack_car_options(pack_id, car_options_id) VALUES ('1', '5');
INSERT INTO pack_car_options(pack_id, car_options_id) VALUES ('2', '6');
INSERT INTO pack_car_options(pack_id, car_options_id) VALUES ('2', '7');
INSERT INTO pack_car_options(pack_id, car_options_id) VALUES ('2', '8');
INSERT INTO pack_car_options(pack_id, car_options_id) VALUES ('2', '9');

INSERT INTO car (id, in_free_pool, version, amount_downgrade, amount_upgrade, benefit, brand, car_type, category, delivery_time, emission, fiscalhp, fuel_type, ideal_km, list_price, max_km, model, pk) VALUES ('1', 1, '0', '2000', '2000', '200', 'Audi', 'NORMAL', '4', '3', '80', '125', 'DIESEL', '120000', '40000', '150000', 'A4', '125');
INSERT INTO car (id, in_free_pool, version, amount_downgrade, amount_upgrade, benefit, brand, car_type, category, delivery_time, emission, fiscalhp, fuel_type, ideal_km, list_price, max_km, model, pk) VALUES ('2', 1, '1', '2000', '2000', '200', 'Audi', 'NORMAL', '3', '3', '80', '125', 'DIESEL', '120000', '40000', '150000', 'A3', '125');
INSERT INTO car (id, in_free_pool, version, amount_downgrade, amount_upgrade, benefit, brand, car_type, category, delivery_time, emission, fiscalhp, fuel_type, ideal_km, list_price, max_km, model, pk) VALUES ('3', 0, '0', '2000', '2000', '200', 'Passat', 'NORMAL', '5', '3', '80', '125', 'DIESEL', '120000', '40000', '150000', 'Berline', '125');

INSERT INTO car_pictures(car_id, pictures) VALUES ('1', 'http://media.caranddriver.com/images/15q2/657945/2017-audi-a4-official-photos-and-info-news-car-and-driver-photo-659636-s-429x262.jpg');
INSERT INTO car_pictures(car_id, pictures) VALUES ('2', 'http://www.larevueautomobile.com/images/Audi/A4/Exterieur/Audi_A4_001.jpg');
INSERT INTO car_pictures(car_id, pictures) VALUES ('3', 'http://www.garagethoen.be/media/126610/passat-berline.jpg');