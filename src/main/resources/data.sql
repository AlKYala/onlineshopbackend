/*
INSERT INTO user(id, email, username, password, is_banned, is_email_confirmed, rating) VALUES (0, "aaa@googlemail.com", "StinkiStink", "$2a$10$FcUqjy.ty/.p/mh4VFBinuz1JWR1CIRrQqTKkcTDAkn34cuncOa/q", false, false, 0);
INSERT INTO user(id, email, username, password, is_banned, is_email_confirmed, rating) VALUES (0, "aaa2@googlemail.com", "StinkiStink2", "$2a$10$FcUqjy.ty/.p/mh4VFBinuz1JWR1CIRrQqTKkcTDAkn34cuncOa/q", false, false, 0);

INSERT INTO category(id, name) values (0, 'Japanese Mafia');
INSERT INTO category(id, name) values (0, 'Auto');
INSERT INTO category(id, name) values (0, 'Celebrities')

INSERT INTO marke(id, name, category_id) values(0, 'Tokyo Mafia', 1);
INSERT INTO marke(id, name, category_id) values(0, 'Porsche', 2);
INSERT INTO marke(id, name, category_id) values(0, 'Judas Priest', 3)

INSERT INTO advertisement(id, description, price, title, featured, featured_description, featured_title, featured_picture_url, marke_id, seller_id)
values (0, 'Kiryu beschützt dich vor bösen Leuten', 50, 'KAZUMA KIRYU', true, 'Jetzt Kiryu als Bodygurd engagieren', 'Bodyguard: Kazuma Kiryu', 'https://twinfinite.net/wp-content/uploads/2020/01/Yakuza-7-1.jpg', 1, null)

INSERT INTO advertisement(id, description, price, title, featured, featured_description, featured_title, featured_picture_url, marke_id, seller_id)
values (0, 'Die Kids im Internet stehen voll drauf', 50, 'Porsche Cayman S', true, 'Porsche Cayman S zu Verkaufen', 'Angebot: Porsche Cayman S', 'https://imgr1.auto-motor-und-sport.de/Porsche-718-PDK-169FullWidth-3bb8077e-1701473.jpg', 2, null)

INSERT INTO advertisement(id, description, price, title, featured, featured_description, featured_title, featured_picture_url, marke_id, seller_id)
values (0, 'Die Versicherung wird teuer', 50, '2021 911er GT3', false, 'Der neue 911er GT3', 'Porsche 911 GT3 2021', 'https://cdn.motor1.com/images/mgl/nYWJy/s3/2021-porsche-911-gt3-prototype-rear-quarter-dynamic.jpg', 2, null)

INSERT INTO advertisement(id, description, featured, featured_description, featured_picture_url, price, title, marke_id, seller_id)
values (0, 'Saejima beschützt dich für eine ganze Woche', false, 'Saejima beschützt dich für eine ganze Woche', 'https://i.ytimg.com/vi/45VxNsYwW_o/maxresdefault.jpg', 1000, 'Ex Yakuza: Taiga Saejima', 1, 1)

INSERT INTO advertisement(id, description, price, title, featured, featured_description, featured_title, featured_picture_url, marke_id, seller_id)
values (0, 'Judas Priest kommt zu dir in die Ortschaft', 50, 'Judas Priest', true, 'Judas Priest', 'Tritt bei dir in der Stadt auf', 'https://images.kerrangcdn.com/Judas-Priest-2019-promo-shot.jpg?auto=compress&fit=crop&w=1200', 3, 2)

update marke
set marken_pic_url = 'https://i0.wp.com/twinfinite.net/wp-content/uploads/2020/11/yakuzalikeadragon-kiryu1.jpg?fit=1000%2C563&ssl=1'
where name in ('Tokyo Mafia')

update marke
set marken_pic_url = 'https://www.pikpng.com/pngl/b/490-4900168_shedding-the-light-and-darkness-on-this-epic.png'
where name in ('Judas Priest')

update marke
set marken_pic_url = 'https://pngimg.com/uploads/porsche_logo/porsche_logo_PNG1.png'
where name in ('Porsche')

update advertisement
set featured_picture_url = 'https://i0.wp.com/twinfinite.net/wp-content/uploads/2020/11/yakuzalikeadragon-kiryu1.jpg?fit=1000%2C563&ssl=1'
where id in (1)

INSERT INTO payment_method(id, name) values (0, 'Credit Card');
INSERT INTO payment_method(id, name) values (0, 'Pay Pal');
INSERT INTO payment_method(id, name) values (0, 'Bitcoin');

INSERT INTO picture(id, picture_url, advertisement_of_image_id) values (0, 'https://static.wikia.nocookie.net/yakuza/images/5/54/Kiryu_%28Y7%29.jpg/revision/latest?cb=20191113051410', 1);

INSERT INTO picture(id, picture_url, advertisement_of_image_id) values (0, 'https://static.wikia.nocookie.net/yakuza/images/5/54/Kiryu_%28Y7%29.jpg/revision/latest?cb=20191113051410', 1);

update advertisement
set seller_id = 1
where seller_id is null

INSERT INTO payment_information (id, additional_information1, additional_information2, address, payment_method_id, seller_id)
VALUES(0, 'Placeholder1', 'Placeholder2', 'ASKLDJASKL478676789D', 1, 1)

INSERT INTO payment_information (id, additional_information1, additional_information2, address, payment_method_id, seller_id)
VALUES(0, '', '', 'BITCASKLDJASKL478676789D', 2, 1)

INSERT INTO payment_information (id, additional_information1, additional_information2, address, payment_method_id, seller_id)
VALUES(0, 'Placeholder1', 'Placeholder2', 'sample@sample.com', 3, 1)

INSERT INTO payment_information (id, additional_information1, additional_information2, address, payment_method_id, seller_id)
VALUES(0, 'Placeholder1', 'Placeholder2', 'ASKLDJASKL478676789D', 1, 2)

INSERT INTO payment_information (id, additional_information1, additional_information2, address, payment_method_id, seller_id)
VALUES(0, 'Placeholder1', 'Placeholder2', 'sample2@sample.com', 2, 2)

INSERT INTO accepted_payment_method(id, payment_information_id, payment_method_id, seller_id) values (0, 1, 1, 1);
INSERT INTO accepted_payment_method(id, payment_information_id, payment_method_id, seller_id) values (0, 2, 2, 1);
INSERT INTO accepted_payment_method(id, payment_information_id, payment_method_id, seller_id) values (0, 3, 3, 1);
INSERT INTO accepted_payment_method(id, payment_information_id, payment_method_id, seller_id) values (0, 4, 1, 2);
INSERT INTO accepted_payment_method(id, payment_information_id, payment_method_id, seller_id) values (0, 5, 2, 2);

*/