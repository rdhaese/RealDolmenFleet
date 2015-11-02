INSERT INTO employee(version, email, functional_level, name, password, role, permission_to_order_new_car) VALUES ('1','jeroen@skynet.be', '2', 'jeroen', '$2a$10$v8Y.TvnxrqZCfIScuzNchesRzpYAAfM1yfHGuW50l/qpWqLd9NERe', 'ROLE_FLEET', false);
INSERT INTO employee(version, email, functional_level, name, password, role, permission_to_order_new_car) VALUES ('1','r.dhaese92@gmail.com', '4', 'robin', '$2a$10$2SfKBifGwHep6e5y79KDJOjfgq3CmLdsyoh44MEmYPiWFvevmbfmq', 'ROLE_FLEET', false);

INSERT INTO car_option(description, name, version) VALUES ('GPS van tomtom', 'GPS','0');
INSERT INTO car_option(description, name, version) VALUES ('achterlichten in LED-techniek', 'LED achterlichten','0');
INSERT INTO car_option(name, version) VALUES ('Cruise control','0');
INSERT INTO car_option(description, name, version) VALUES ('Stevige rails op het dak', 'Dakrails','0');
INSERT INTO car_option(description, name, version) VALUES ('Robuust en onmisbaar', 'Trekhaak','0');
INSERT INTO car_option(name, version) VALUES ('Automaat','0');
INSERT INTO car_option(name, version) VALUES ('7-zits','0');
INSERT INTO car_option(description, name, version) VALUES ('indien mogelijkheid voorzien door constructeur', 'Volwaardig reservewiel','0');
INSERT INTO car_option(name, version) VALUES ('Parkeersensoren','0');
INSERT INTO car_option(description, name, version) VALUES ('100 procent het beste leer', 'lederen intereur','0');
INSERT INTO car_option(description, name, version) VALUES ('Enkel Vooraan', 'Verwarmde zetels','0');
INSERT INTO car_option(description, name, version) VALUES ('Onherkenbaarheid is verzekerd','Getinte ruiten','0');


INSERT INTO pack(name, price, version) VALUES ('Pack Business @ GPS Discover Media', '299', '0');
INSERT INTO pack(name, price, version) VALUES ('Pack Business gps rns315 / pre gsm', '399', '0');
INSERT INTO pack(name, price, version) VALUES ('Business Media Pack', '255', '0');

INSERT INTO pack_car_options(pack_id, car_options_id) VALUES ('1', '4');
INSERT INTO pack_car_options(pack_id, car_options_id) VALUES ('1', '5');
INSERT INTO pack_car_options(pack_id, car_options_id) VALUES ('2', '6');
INSERT INTO pack_car_options(pack_id, car_options_id) VALUES ('2', '7');
INSERT INTO pack_car_options(pack_id, car_options_id) VALUES ('2', '8');
INSERT INTO pack_car_options(pack_id, car_options_id) VALUES ('2', '9');

INSERT INTO car (id, in_free_pool, version, amount_downgrade, amount_upgrade, benefit, brand, car_type, category, delivery_time, emission, fiscalhp, fuel_type, ideal_km, list_price, max_km, model, pk, base_pack_id) VALUES ('1', 1, '0', '2000', '2000', '200', 'Audi', 'NORMAL', '4', '3', '80', '125', 'DIESEL', '120000', '40000', '150000', 'A4', '125', 1);
INSERT INTO car (id, in_free_pool, version, amount_downgrade, amount_upgrade, benefit, brand, car_type, category, delivery_time, emission, fiscalhp, fuel_type, ideal_km, list_price, max_km, model, pk, base_pack_id) VALUES ('2', 1, '1', '2000', '2000', '200', 'Audi', 'NORMAL', '3', '3', '80', '125', 'DIESEL', '120000', '40000', '150000', 'A3', '125', 2);
INSERT INTO car (id, in_free_pool, version, amount_downgrade, amount_upgrade, benefit, brand, car_type, category, delivery_time, emission, fiscalhp, fuel_type, ideal_km, list_price, max_km, model, pk, base_pack_id) VALUES ('3', 0, '0', '2000', '2000', '200', 'Passat', 'NORMAL', '5', '3', '80', '125', 'DIESEL', '120000', '40000', '150000', 'Berline', '125', 3);

INSERT INTO car_pictures(car_id, pictures) VALUES ('1', 'http://media.caranddriver.com/images/15q2/657945/2017-audi-a4-official-photos-and-info-news-car-and-driver-photo-659636-s-429x262.jpg');
INSERT INTO car_pictures(car_id, pictures) VALUES ('2', 'http://www.larevueautomobile.com/images/Audi/A4/Exterieur/Audi_A4_001.jpg');
INSERT INTO car_pictures(car_id, pictures) VALUES ('3', 'http://www.garagethoen.be/media/126610/passat-berline.jpg');

INSERT INTO car_extra_packs(car_id, extra_packs_id) VALUES ('1','2');

INSERT INTO car_extra_options(car_id, extra_options_id) VALUES ('1','10');
INSERT INTO car_extra_options(car_id, extra_options_id) VALUES ('1','11');
INSERT INTO car_extra_options(car_id, extra_options_id) VALUES ('1','12');
