INSERT INTO employee(email, functional_level, name, password, role) VALUES ('jeroen@skynet.be', '2', 'jeroen', '123', 'FLEET');

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






INSERT INTO pack(name, price) VALUES ('Pack Business @ GPS Discover Media', '299');
INSERT INTO pack(name, price) VALUES ('Pack Business gps rns315 / pre gsm', '399');
INSERT INTO pack(name, price) VALUES ('Business Media Pack', '255');

INSERT INTO pack_car_options(pack_id, car_options_id) VALUES ('1', '4');
INSERT INTO pack_car_options(pack_id, car_options_id) VALUES ('1', '5');
INSERT INTO pack_car_options(pack_id, car_options_id) VALUES ('2', '6');
INSERT INTO pack_car_options(pack_id, car_options_id) VALUES ('2', '7');
INSERT INTO pack_car_options(pack_id, car_options_id) VALUES ('2', '8');
INSERT INTO pack_car_options(pack_id, car_options_id) VALUES ('2', '9');
