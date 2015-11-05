INSERT INTO employee(version, email, functional_level, name, password, role, permission_to_order_new_car, in_service_date) VALUES ('1','jeroen@skynet.be', '2', 'Jeroen Van Den Haute', '$2a$10$v8Y.TvnxrqZCfIScuzNchesRzpYAAfM1yfHGuW50l/qpWqLd9NERe', 'ROLE_FLEET', false, '2015-09-01');
INSERT INTO employee(version, email, functional_level, name, password, role, permission_to_order_new_car, in_service_date) VALUES ('1','r.dhaese92@gmail.com', '4', 'Robin D''Haese', '$2a$10$2SfKBifGwHep6e5y79KDJOjfgq3CmLdsyoh44MEmYPiWFvevmbfmq', 'ROLE_FLEET', false, '2015-09-01');

INSERT INTO car_option(description, name) VALUES ('GPS van tomtom', 'GPS');
INSERT INTO car_option(description, name) VALUES ('achterlichten in LED-techniek', 'LED achterlichten');
INSERT INTO car_option(name) VALUES ('Cruise control');
INSERT INTO car_option(description, name) VALUES ('Stevige rails op het dak', 'Dakrails');
INSERT INTO car_option(description, name) VALUES ('Robuust en onmisbaar', 'Trekhaak');
INSERT INTO car_option(name) VALUES ('Automaat');
INSERT INTO car_option(name) VALUES ('7-zits');
INSERT INTO car_option(description, name) VALUES ('indien mogelijkheid voorzien door constructeur', 'Volwaardig reservewiel');
INSERT INTO car_option(name) VALUES ('Parkeersensoren');
INSERT INTO car_option(description, name) VALUES ('100 procent het beste leer', 'lederen intereur');
INSERT INTO car_option(description, name) VALUES ('Enkel Vooraan', 'Verwarmde zetels');
INSERT INTO car_option(description, name) VALUES ('Onherkenbaarheid is verzekerd', 'Getinte ruiten');


INSERT INTO pack(name, price, version) VALUES ('Pack Business @ GPS Discover Media', '299', '0');
INSERT INTO pack(name, price, version) VALUES ('Pack Business gps rns315 / pre gsm', '399', '0');
INSERT INTO pack(name, price, version) VALUES ('Business Media Pack', '255', '0');

INSERT INTO pack_car_options(pack_id, car_options_id) VALUES ('1', '4');
INSERT INTO pack_car_options(pack_id, car_options_id) VALUES ('1', '5');
INSERT INTO pack_car_options(pack_id, car_options_id) VALUES ('2', '6');
INSERT INTO pack_car_options(pack_id, car_options_id) VALUES ('2', '7');
INSERT INTO pack_car_options(pack_id, car_options_id) VALUES ('2', '8');
INSERT INTO pack_car_options(pack_id, car_options_id) VALUES ('2', '9');

INSERT INTO car (id,  version, amount_downgrade, amount_upgrade, benefit, brand, car_type, category, delivery_time, emission, fiscalhp, fuel_type, ideal_km, list_price, max_km, model, pk, base_pack_id) VALUES ('1',  '0', '2000', '2000', '200', 'Audi', 'NORMAL', '4', '3', '80', '125', 'DIESEL', '120000', '40000', '150000', 'A4', '125', 1);
INSERT INTO car (id,  version, amount_downgrade, amount_upgrade, benefit, brand, car_type, category, delivery_time, emission, fiscalhp, fuel_type, ideal_km, list_price, max_km, model, pk, base_pack_id) VALUES ('2',  '1', '2000', '2000', '200', 'Audi', 'NORMAL', '3', '3', '80', '125', 'DIESEL', '120000', '40000', '150000', 'A3', '125', 2);
INSERT INTO car (id,  version, amount_downgrade, amount_upgrade, benefit, brand, car_type, category, delivery_time, emission, fiscalhp, fuel_type, ideal_km, list_price, max_km, model, pk, base_pack_id) VALUES ('3', '0', '2000', '2000', '200', 'Passat', 'NORMAL', '5', '3', '80', '125', 'DIESEL', '120000', '40000', '150000', 'Berline', '125', 3);

INSERT INTO car_pictures(car_id, pictures) VALUES ('1', 'http://media.caranddriver.com/images/15q2/657945/2017-audi-a4-official-photos-and-info-news-car-and-driver-photo-659636-s-429x262.jpg');
INSERT INTO car_pictures(car_id, pictures) VALUES ('2', 'http://www.larevueautomobile.com/images/Audi/A4/Exterieur/Audi_A4_001.jpg');
INSERT INTO car_pictures(car_id, pictures) VALUES ('3', 'http://www.garagethoen.be/media/126610/passat-berline.jpg');

INSERT INTO car_extra_packs(car_id, extra_packs_id) VALUES ('1','2');
INSERT INTO car_extra_packs(car_id, extra_packs_id) VALUES ('2','3');
INSERT INTO car_extra_packs(car_id, extra_packs_id) VALUES ('2','1');

INSERT INTO car_extra_options(car_id, extra_options_id) VALUES ('1','10');
INSERT INTO car_extra_options(car_id, extra_options_id) VALUES ('1','11');
INSERT INTO car_extra_options(car_id, extra_options_id) VALUES ('1','12');

INSERT INTO ordered_car (id, version, color, price, car_id) VALUES ('1', '1', 'Black', '5000', '1');

INSERT INTO ordered_car_options (ordered_car_id, options_id) VALUES ('1', '1');

INSERT INTO ordered_car_packs (ordered_car_id, packs_id) VALUES ('1', '1');

INSERT INTO car_usage(id,version,initial_end_date,order_date,start_date,ordered_car_id) VALUES ('1', '0', '2019-11-02', '2015-11-03', '2016-02-04', '1')

INSERT INTO periodic_usage_update (id,version,update_date,new_total_km,total_fuelled_for_period, total_fuel_price)VALUES('1', '0', '2016-02-04', '1', 0, 0);
INSERT INTO periodic_usage_update (id,version,update_date,new_total_km,total_fuelled_for_period, total_fuel_price)VALUES('2', '0', '2016-02-11', '10', 0, 0);

INSERT INTO car_usage_usage_updates (car_usage_id, usage_updates_id) VALUES ('1', '1');
INSERT INTO car_usage_usage_updates (car_usage_id, usage_updates_id) VALUES ('1', '2');
