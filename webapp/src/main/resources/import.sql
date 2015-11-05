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



INSERT INTO car (id,  version, category, emission, fiscalhp, fuel_type, brand, model, pk, delivery_time, ideal_km, max_km, list_price, benefit, amount_upgrade, amount_downgrade, car_type) VALUES('10','0','2','89','9','DIESEL','Audi','A3 sportback 1.6 tdi 110 pk ultra attraction','110','5','140000','180000','25048.99','104.17','0','3924.43','NORMAL');
INSERT INTO car_pictures(Car_id, pictures) VALUES('10','http://directlease.be/DLBe/img.do?path=Photo400%2FAUDI%2FA3%2F2014%2F5HA_315.JPG&height=250&width=400');
INSERT INTO car (id,  version, category, emission, fiscalhp, fuel_type, brand, model, pk, delivery_time, ideal_km, max_km, list_price, benefit, amount_upgrade, amount_downgrade, car_type) VALUES('11','0','2','109','7','DIESEL','Seat','Ibiza St 1.6 crtdi 105 pk Style ecomotive','105','3.5','140000','180000','19945.48','104.17','0','2479.69','BREAK');
INSERT INTO car_pictures(Car_id, pictures) VALUES('11','http://static.moniteurautomobile.be/imgcontrol/images_tmp/clients/moniteur/c680-d465/content/medias/images/cars/seat/ibiza/seat--ibiza-st--2010/seat--ibiza-st--2010-m-1.jpg');
INSERT INTO car (id,  version, category, emission, fiscalhp, fuel_type, brand, model, pk, delivery_time, ideal_km, max_km, list_price, benefit, amount_upgrade, amount_downgrade, car_type) VALUES('12','0','2','87','9','DIESEL','Seat','Leon style 1.6 Tdi 110 pk ecomotive','110','3','140000','180000','23178.00','104.17','0','5491.27','NORMAL');
INSERT INTO car_pictures(Car_id, pictures) VALUES('12','http://directlease.nl/DLNl/img.do?path=Photo400%2FSEAT%2FLEON%2F2016%2F5ES.JPG&height=250&width=400');
INSERT INTO car (id,  version, category, emission, fiscalhp, fuel_type, brand, model, pk, delivery_time, ideal_km, max_km, list_price, benefit, amount_upgrade, amount_downgrade, car_type) VALUES('13','0','2','90','9','DIESEL','Skoda ','Octavia Berline 1.6 tdi 110 pk  greenline','110','3','140000','180000','24566.82','104.17','0','4253.93','NORMAL');
INSERT INTO car_pictures(Car_id, pictures) VALUES('13','http://img.turbo.fr/015200BE07413939-c1-photo-skoda-octavia-1-6-tdi-110-ch-greenline.jpg');
INSERT INTO car (id,  version, category, emission, fiscalhp, fuel_type, brand, model, pk, delivery_time, ideal_km, max_km, list_price, benefit, amount_upgrade, amount_downgrade, car_type) VALUES('14','0','2','90','9','DIESEL','Skoda ','Octavia combi 1.6 tdi 110 pk  greenline','110','3.5','140000','180000','25343.22','104.17','0','4042.73','BREAK');
INSERT INTO car_pictures(Car_id, pictures) VALUES('14','http://directlease.be/DLBe/img.do?path=Photo400%2FSKODA%2FOCTAVIA%2F2016%2F5ES.JPG&height=250&width=400');
INSERT INTO car (id,  version, category, emission, fiscalhp, fuel_type, brand, model, pk, delivery_time, ideal_km, max_km, list_price, benefit, amount_upgrade, amount_downgrade, car_type) VALUES('15','0','2','109','7','DIESEL','Skoda ','Roomster 1.2 crtdi 75 pk','75','3.5','180000','200000','17187.91','104.17','0','1907.41','MONOVOLUME');
INSERT INTO car_pictures(Car_id, pictures) VALUES('15','http://gcm.moniteurautomobile.be/imgcontrol/c1008-d684/clients/moniteur/content/medias/images/occassions/646/317/317646_1.jpg');
INSERT INTO car (id,  version, category, emission, fiscalhp, fuel_type, brand, model, pk, delivery_time, ideal_km, max_km, list_price, benefit, amount_upgrade, amount_downgrade, car_type) VALUES('16','0','2','99','9','DIESEL','VW','Golf 7 Highline 1.6l CRTDI 110 pk BMT ','110','4','140000','180000','27476.95','123.65','0','1369.33','NORMAL');
INSERT INTO car_pictures(Car_id, pictures) VALUES('16','http://imganuncios.mitula.net/volkswagen_golf_variant_1_6_tdi_highline_executive_bluemoti_7030085437683640511.jpg');
INSERT INTO car (id,  version, category, emission, fiscalhp, fuel_type, brand, model, pk, delivery_time, ideal_km, max_km, list_price, benefit, amount_upgrade, amount_downgrade, car_type) VALUES('17','0','2','102','9','DIESEL','VW','Golf Variant Trendl.1.6 CRTDI 110 pk BMT','110','3.5','140000','180000','26696.57','125.86','0','1407.33','BREAK');
INSERT INTO car_pictures(Car_id, pictures) VALUES('17','http://78.138.119.49/userdata/1/23627/VkkSBhioB/kfz36858826_golfv1.jpg');
INSERT INTO car (id,  version, category, emission, fiscalhp, fuel_type, brand, model, pk, delivery_time, ideal_km, max_km, list_price, benefit, amount_upgrade, amount_downgrade, car_type) VALUES('18','0','3','107','11','DIESEL','VW','Beetle Design 2.0l TDI 110pk BMT','110','5','140000','180000','24240.16','122.93','2431.85','2795.35','NORMAL');
INSERT INTO car_pictures(Car_id, pictures) VALUES('18','http://www.leaselinq.nl/application/elements/media/cars/5203_0.jpg');
INSERT INTO car (id,  version, category, emission, fiscalhp, fuel_type, brand, model, pk, delivery_time, ideal_km, max_km, list_price, benefit, amount_upgrade, amount_downgrade, car_type) VALUES('19','0','3','99','9','DIESEL','Audi','A3 sportback 1.6 tdi 110 pk Ambiente','110','5','140000','180000','28218.01','126.98','2190.29','3036.91','NORMAL');
INSERT INTO car_pictures(Car_id, pictures) VALUES('19','http://directselectie.audi.nl/Content/images/audi/F8VABSED20166Y6YQF_L.jpg');
INSERT INTO car (id,  version, category, emission, fiscalhp, fuel_type, brand, model, pk, delivery_time, ideal_km, max_km, list_price, benefit, amount_upgrade, amount_downgrade, car_type) VALUES('20','0','3','102','9','DIESEL','VW','Golf Variant Highline 1.6CRTDI 110 pk BMT','110','4','180000','200000','28618.88','134.92','2061.30','3165.90','BREAK');
INSERT INTO car_pictures(Car_id, pictures) VALUES('20','http://www.bluesphere.be/images/sites/34/product/255/4/Golf%207%20Var%20TL%20Tungsten.JPG');
INSERT INTO car (id,  version, category, emission, fiscalhp, fuel_type, brand, model, pk, delivery_time, ideal_km, max_km, list_price, benefit, amount_upgrade, amount_downgrade, car_type) VALUES('21','0','3','109','9','DIESEL','Skoda ','Superb berline ambiente 1.6 crtdi greenline ','110','4','180000','200000','26400.74','137.66','2434.30','2792.90','NORMAL');
INSERT INTO car_pictures(Car_id, pictures) VALUES('21','http://www.parkers.co.uk/Images/PageFiles/75802/Skoda_Superb_210913(34).jpg');
INSERT INTO car (id,  version, category, emission, fiscalhp, fuel_type, brand, model, pk, delivery_time, ideal_km, max_km, list_price, benefit, amount_upgrade, amount_downgrade, car_type) VALUES('22','0','3','105','9','DIESEL','VW','Passat berline 1.6 TDI 120 pk DPG BMT comfortline','120','4','140000','180000','30887.44','152.23','3864.12','1363.08','NORMAL');
INSERT INTO car_pictures(Car_id, pictures) VALUES('22','http://www.automania.be/files/Image/articles/jpg/VWpassat_r36_db2006au01989_.jpg');
INSERT INTO car (id,  version, category, emission, fiscalhp, fuel_type, brand, model, pk, delivery_time, ideal_km, max_km, list_price, benefit, amount_upgrade, amount_downgrade, car_type) VALUES('23','0','3','113','9','DIESEL','Skoda ','Superb Combi 1.6 tdi 105 pk 5v geenline amb','105','4','180000','200000','27853.89','153.20','2877.96','2349.24','BREAK');
INSERT INTO car_pictures(Car_id, pictures) VALUES('23','http://i.auto-bild.de/ir_img/1/2/8/4/4/3/8/Skoda-Superb-Combi-IAA-2015-Test-Sitzprobe-1200x800-37be715d570a3384.jpg');
INSERT INTO car (id,  version, category, emission, fiscalhp, fuel_type, brand, model, pk, delivery_time, ideal_km, max_km, list_price, benefit, amount_upgrade, amount_downgrade, car_type) VALUES('24','0','3','115','9','DIESEL','VW','New Touran 1.6TDI Trendline 110pk BMT 6v','110','5','140000','180000','27937.26','157.65','3669.47','1557.73','MONOVOLUME');
INSERT INTO car_pictures(Car_id, pictures) VALUES('24','http://1.bp.blogspot.com/-Zw-D7oD78L4/VPYVRXkleeI/AAAAAAAAtnU/mbjYY2vC4hc/s1600/VW-Touran-New-2555.jpg');
INSERT INTO car (id,  version, category, emission, fiscalhp, fuel_type, brand, model, pk, delivery_time, ideal_km, max_km, list_price, benefit, amount_upgrade, amount_downgrade, car_type) VALUES('25','0','4','101','9','DIESEL','VW','Golf Sportsvan 1.6 CRTDI 110 Highline','110','4','140000','180000','29234.32','135.73','761.58','5046.42','NORMAL');
INSERT INTO car_pictures(Car_id, pictures) VALUES('25','http://media.caranddriver.com/images/13q3/524203/volkswagen-golf-sportsvan-concept-photos-and-info-news-car-and-driver-photo-535911-s-429x262.jpg');
INSERT INTO car (id,  version, category, emission, fiscalhp, fuel_type, brand, model, pk, delivery_time, ideal_km, max_km, list_price, benefit, amount_upgrade, amount_downgrade, car_type) VALUES('26','0','4','105','9','DIESEL','VW','Jetta Comfortline 2.0 tdi 110pk 5v BMT','110','5','140000','180000','28034.71','138.17','1196.12','4611.88','NORMAL');
INSERT INTO car_pictures(Car_id, pictures) VALUES('26','https://i.ytimg.com/vi/viJ97HUSidk/maxresdefault.jpg');
INSERT INTO car (id,  version, category, emission, fiscalhp, fuel_type, brand, model, pk, delivery_time, ideal_km, max_km, list_price, benefit, amount_upgrade, amount_downgrade, car_type) VALUES('27','0','4','109','9','DIESEL','Skoda ','Superb Greenline elegance 1.6 tdi 105 pk','105','4','180000','200000','29652.19','154.61','1524.15','4283.85','NORMAL');
INSERT INTO car_pictures(Car_id, pictures) VALUES('27','http://srv2.betterparts.org/images/skoda-superb-greenline-06.jpg');
INSERT INTO car (id,  version, category, emission, fiscalhp, fuel_type, brand, model, pk, delivery_time, ideal_km, max_km, list_price, benefit, amount_upgrade, amount_downgrade, car_type) VALUES('28','0','4','107','9','DIESEL','VW','Passat Variant 1.6 tdi 120 pk Comfortline ','120','4','140000','180000','32852.07','166.61','3657.14','2150.86','BREAK');
INSERT INTO car_pictures(Car_id, pictures) VALUES('28','https://i.ytimg.com/vi/ynf1UPuM8w4/maxresdefault.jpg');
INSERT INTO car (id,  version, category, emission, fiscalhp, fuel_type, brand, model, pk, delivery_time, ideal_km, max_km, list_price, benefit, amount_upgrade, amount_downgrade, car_type) VALUES('29','0','4','115','9','DIESEL','VW','New Touran 1.6TDI Trendline 110 pk BMT 6v','110','5','140000','180000','29655.48','167.34','3094.43','2713.57','MONOVOLUME');
INSERT INTO car_pictures(Car_id, pictures) VALUES('29','http://1.bp.blogspot.com/-Zw-D7oD78L4/VPYVRXkleeI/AAAAAAAAtnU/mbjYY2vC4hc/s1600/VW-Touran-New-2555.jpg');
INSERT INTO car (id,  version, category, emission, fiscalhp, fuel_type, brand, model, pk, delivery_time, ideal_km, max_km, list_price, benefit, amount_upgrade, amount_downgrade, car_type) VALUES('30','0','4','117','11','DIESEL','Audi','A4 berline 2.0 tdi 120 pk','120','4','180000','200000','29511.49','170.75','3972.64','1835.36','NORMAL');
INSERT INTO car_pictures(Car_id, pictures) VALUES('30','http://medias.autonews.fr/wp-content/uploads/2013/03/audi-A3-berline-home.jpg');
INSERT INTO car (id,  version, category, emission, fiscalhp, fuel_type, brand, model, pk, delivery_time, ideal_km, max_km, list_price, benefit, amount_upgrade, amount_downgrade, car_type) VALUES('31','0','4','113','9','DIESEL','Skoda ','Superb combi Greenline elegance 1.6 tdi 105 pk','105','4','180000','200000','31070.18','170.89','2259.60','3548.40','BREAK');
INSERT INTO car_pictures(Car_id, pictures) VALUES('31','http://i.auto-bild.de/ir_img/1/2/8/4/4/3/8/Skoda-Superb-Combi-IAA-2015-Test-Sitzprobe-1200x800-37be715d570a3384.jpg');
INSERT INTO car (id,  version, category, emission, fiscalhp, fuel_type, brand, model, pk, delivery_time, ideal_km, max_km, list_price, benefit, amount_upgrade, amount_downgrade, car_type) VALUES('32','0','5','95','8','GASOLINE','vw','Jetta hybride 1.4 tsi 150 pk dsg','150','5','180000','200000','32608.02','104.17','4806.64','3614.96','NORMAL');
INSERT INTO car_pictures(Car_id, pictures) VALUES('32','http://zombdrive.com/images/2013-volkswagen-jetta-10.jpg');
INSERT INTO car (id,  version, category, emission, fiscalhp, fuel_type, brand, model, pk, delivery_time, ideal_km, max_km, list_price, benefit, amount_upgrade, amount_downgrade, car_type) VALUES('33','0','5','105','11','DIESEL','Audi','A3 sportback 2.0 tdi 150 pk Attraction','150','5','140000','180000','28316.18','139.56','479.36','7942.24','NORMAL');
INSERT INTO car_pictures(Car_id, pictures) VALUES('33','http://directselectie.audi.nl/Content/images/audi/F8VABSED20166Y6YQF_L.jpg');
INSERT INTO car (id,  version, category, emission, fiscalhp, fuel_type, brand, model, pk, delivery_time, ideal_km, max_km, list_price, benefit, amount_upgrade, amount_downgrade, car_type) VALUES('34','0','5','104','9','DIESEL','Audi','A4 berline 2.0 tdi ultra 136 pk','136','4','180000','200000','30945.61','150.31','1063.74','7357.86','NORMAL');
INSERT INTO car_pictures(Car_id, pictures) VALUES('34','http://medias.autonews.fr/wp-content/uploads/2013/03/audi-A3-berline-home.jpg');
INSERT INTO car (id,  version, category, emission, fiscalhp, fuel_type, brand, model, pk, delivery_time, ideal_km, max_km, list_price, benefit, amount_upgrade, amount_downgrade, car_type) VALUES('35','0','5','107','9','DIESEL','VW','Passat variant 1.6 tdi 120 pk highline','120','4','140000','180000','34547.75','175.21','3001.27','5420.33','BREAK');
INSERT INTO car_pictures(Car_id, pictures) VALUES('35','https://i.ytimg.com/vi/ynf1UPuM8w4/maxresdefault.jpg');
INSERT INTO car (id,  version, category, emission, fiscalhp, fuel_type, brand, model, pk, delivery_time, ideal_km, max_km, list_price, benefit, amount_upgrade, amount_downgrade, car_type) VALUES('36','0','5','115','9','DIESEL','VW','New Touran 1.6TDI Highline 110 pk BMT 6v','110','5','140000','180000','31122.47','175.62','1450.67','6970.93','MONOVOLUME');
INSERT INTO car_pictures(Car_id, pictures) VALUES('36','http://1.bp.blogspot.com/-Zw-D7oD78L4/VPYVRXkleeI/AAAAAAAAtnU/mbjYY2vC4hc/s1600/VW-Touran-New-2555.jpg');
INSERT INTO car (id,  version, category, emission, fiscalhp, fuel_type, brand, model, pk, delivery_time, ideal_km, max_km, list_price, benefit, amount_upgrade, amount_downgrade, car_type) VALUES('37','0','5','106','11','DIESEL','VW','Passat berline 2.0 tdi 150 pk highline ','150','4','180000','200000','35187.49','175.94','3464.13','4957.47','NORMAL');
INSERT INTO car_pictures(Car_id, pictures) VALUES('37','http://www.automania.be/files/Image/articles/jpg/VWpassat_r36_db2006au01989_.jpg');
INSERT INTO car (id,  version, category, emission, fiscalhp, fuel_type, brand, model, pk, delivery_time, ideal_km, max_km, list_price, benefit, amount_upgrade, amount_downgrade, car_type) VALUES('38','0','5','121','11','DIESEL','Skoda ','superb combi 2.0 tdi 140 pk Greentec','140','4','180000','200000','30169.23','183.17','2785.89','5635.71','BREAK');
INSERT INTO car_pictures(Car_id, pictures) VALUES('38','http://i.auto-bild.de/ir_img/1/2/8/4/4/3/8/Skoda-Superb-Combi-IAA-2015-Test-Sitzprobe-1200x800-37be715d570a3384.jpg');
INSERT INTO car (id,  version, category, emission, fiscalhp, fuel_type, brand, model, pk, delivery_time, ideal_km, max_km, list_price, benefit, amount_upgrade, amount_downgrade, car_type) VALUES('39','0','5','123','11','DIESEL','Audi','A4 avant 2.0 tdi 120 pk','120','4','180000','200000','31180.52','193.76','4077.92','4343.68','BREAK');
INSERT INTO car_pictures(Car_id, pictures) VALUES('39','http://www.larevueautomobile.com/v6-Image/photo.php?src=/fiche-technique/photos/2013/Audi/A4-Avant/S4/Audi_A4_Avant_S4_001.jpg');
INSERT INTO car (id,  version, category, emission, fiscalhp, fuel_type, brand, model, pk, delivery_time, ideal_km, max_km, list_price, benefit, amount_upgrade, amount_downgrade, car_type) VALUES('40','0','6','109','11','DIESEL','Audi','A4 avant 2.0 tdi ultra 136 pk 6 v','136','4','180000','200000','34864.63','181.79','1501.75','0','BREAK');
INSERT INTO car_pictures(Car_id, pictures) VALUES('40','http://www.larevueautomobile.com/v6-Image/photo.php?src=/fiche-technique/photos/2013/Audi/A4-Avant/S4/Audi_A4_Avant_S4_001.jpg');
INSERT INTO car (id,  version, category, emission, fiscalhp, fuel_type, brand, model, pk, delivery_time, ideal_km, max_km, list_price, benefit, amount_upgrade, amount_downgrade, car_type) VALUES('41','0','6','114','11','DIESEL','Audi','Q3 2.0 tdi ultra 150 pk ','150','5','180000','200000','33739.60','187.98','2487.12','0','NORMAL');
INSERT INTO car_pictures(Car_id, pictures) VALUES('41','http://cdn1.autoexpress.co.uk/sites/autoexpressuk/files/styles/article_main_image/public/4/46/dsc_7848_0.jpg?itok=RN6QLdf8');
INSERT INTO car (id,  version, category, emission, fiscalhp, fuel_type, brand, model, pk, delivery_time, ideal_km, max_km, list_price, benefit, amount_upgrade, amount_downgrade, car_type) VALUES('42','0','6','109','11','DIESEL','Audi','A4 berline 2.0 tdi 163 pk ultra ','163','4','180000','200000','36170.39','188.60','2963.81','0','NORMAL');
INSERT INTO car_pictures(Car_id, pictures) VALUES('42','http://medias.autonews.fr/wp-content/uploads/2013/03/audi-A3-berline-home.jpg');
INSERT INTO car (id,  version, category, emission, fiscalhp, fuel_type, brand, model, pk, delivery_time, ideal_km, max_km, list_price, benefit, amount_upgrade, amount_downgrade, car_type) VALUES('43','0','6','114','11','DIESEL','Audi','A6 berline 2.0 tdi 136 pk','136','4','180000','200000','39108.50','217.89','3488.23','0','NORMAL');
INSERT INTO car_pictures(Car_id, pictures) VALUES('43','http://www.autoweek.nl/images/800/c/1f63572e28005bccb80a30e9aa0d0d4c.jpg');
INSERT INTO car (id,  version, category, emission, fiscalhp, fuel_type, brand, model, pk, delivery_time, ideal_km, max_km, list_price, benefit, amount_upgrade, amount_downgrade, car_type) VALUES('44','0','6','118','11','DIESEL','Audi','A5 sportback 2.0 tdi 150 pk ','150','4','180000','200000','38175.30','223.60','4376.24','0','NORMAL');
INSERT INTO car_pictures(Car_id, pictures) VALUES('44','http://carsreleasedateprice.com/wp-content/uploads/2015/08/2015-audi-a5-sportback-hd-desktop-9-abruk1.jpg');
INSERT INTO car (id,  version, category, emission, fiscalhp, fuel_type, brand, model, pk, delivery_time, ideal_km, max_km, list_price, benefit, amount_upgrade, amount_downgrade, car_type) VALUES('45','0','6','130','11','DIESEL','Seat','New Alhambra Style2.0 tdi 150 pk','150','4','140000','180000','35265.09','236.78','4827.21','0','MONOVOLUME');
INSERT INTO car_pictures(Car_id, pictures) VALUES('45','http://images.car.bauercdn.com/pagefiles/4162/seat_alhambra.jpg');
INSERT INTO car (id,  version, category, emission, fiscalhp, fuel_type, brand, model, pk, delivery_time, ideal_km, max_km, list_price, benefit, amount_upgrade, amount_downgrade, car_type) VALUES('46','0','6','118','11','FAIL','Audi','A6 avant 2.0 tdi 136 pk','136','4','180000','200000','41159.30','241.08','5689.32','0','BREAK');
INSERT INTO car_pictures(Car_id, pictures) VALUES('46','http://s1.cdn.autoevolution.com/images/gallery/AUDIA6Avant-4495_2.jpg');
INSERT INTO car (id,  version, category, emission, fiscalhp, fuel_type, brand, model, pk, delivery_time, ideal_km, max_km, list_price, benefit, amount_upgrade, amount_downgrade, car_type) VALUES('47','0','7','130','11','DIESEL','Seat','New Alhambra Style2.0 tdi 150 pk','150','4','140000','180000','35862.03','240.79','528.28','0','MONOVOLUME');
INSERT INTO car_pictures(Car_id, pictures) VALUES('47','http://images.car.bauercdn.com/pagefiles/4162/seat_alhambra.jpg');
INSERT INTO car (id,  version, category, emission, fiscalhp, fuel_type, brand, model, pk, delivery_time, ideal_km, max_km, list_price, benefit, amount_upgrade, amount_downgrade, car_type) VALUES('48','0','7','114','11','DIESEL','Audi','A6 berline 2.0 tdi 150 pk','150','4','180000','200000','43328.04','241.40','1090.44','0','NORMAL');
INSERT INTO car_pictures(Car_id, pictures) VALUES('48','http://s1.cdn.autoevolution.com/images/gallery/AUDIA6Avant-4495_2.jpg');
INSERT INTO car (id,  version, category, emission, fiscalhp, fuel_type, brand, model, pk, delivery_time, ideal_km, max_km, list_price, benefit, amount_upgrade, amount_downgrade, car_type) VALUES('49','0','7','114','11','DIESEL','Audi','A6 berline 2.0 tdi 190 pk','190','4','180000','200000','45574.16','253.91','3985.73','0','NORMAL');
INSERT INTO car_pictures(Car_id, pictures) VALUES('49','http://s1.cdn.autoevolution.com/images/gallery/AUDIA6Avant-4495_2.jpg');
INSERT INTO car (id,  version, category, emission, fiscalhp, fuel_type, brand, model, pk, delivery_time, ideal_km, max_km, list_price, benefit, amount_upgrade, amount_downgrade, car_type) VALUES('50','0','7','118','11','DIESEL','Audi','A6 avant 2.0 tdi 150 pk','150','4','180000','200000','45378.84','265.79','3464.29','0','BREAK');
INSERT INTO car_pictures(Car_id, pictures) VALUES('50','http://s1.cdn.autoevolution.com/images/gallery/AUDIA6Avant-4495_2.jpg');
INSERT INTO car (id,  version, category, emission, fiscalhp, fuel_type, brand, model, pk, delivery_time, ideal_km, max_km, list_price, benefit, amount_upgrade, amount_downgrade, car_type) VALUES('51','0','7','129','11','DIESEL','Audi','Q5 2.0 tdi150 pk','150','6','180000','200000','40044.08','266.01','3393.44','0','NORMAL');
INSERT INTO car_pictures(Car_id, pictures) VALUES('51','http://media.caranddriver.com/images/12q2/450432/2013-audi-q5-photos-and-info-news-car-and-driver-photo-453976-s-429x262.jpg');
INSERT INTO car (id,  version, category, emission, fiscalhp, fuel_type, brand, model, pk, delivery_time, ideal_km, max_km, list_price, benefit, amount_upgrade, amount_downgrade, car_type) VALUES('52','0','7','119','11','DIESEL','Audi','A6 Avant 2.0 tdi 190 pk','190','4','180000','200000','47624.93','282.35','5625.56','0','BREAK');
INSERT INTO car_pictures(Car_id, pictures) VALUES('52','http://s1.cdn.autoevolution.com/images/gallery/AUDIA6Avant-4495_2.jpg');
INSERT INTO car (id,  version, category, emission, fiscalhp, fuel_type, brand, model, pk, delivery_time, ideal_km, max_km, list_price, benefit, amount_upgrade, amount_downgrade, car_type) VALUES('53','0','7','130','11','DIESEL','VW','New Sharan Comfortline 2.0 tdi 150 pk ','150','5','140000','180000','43247.62','290.38','3313.17','0','MONOVOLUME');
INSERT INTO car_pictures(Car_id, pictures) VALUES('53','http://motoragents.co.uk/wp-content/uploads/2014/07/VW-Sharan-2.jpg');