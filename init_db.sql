USE journeys;

INSERT INTO `clients` (`client_id`, `first_name`, `middle_name`, `last_name`, `address`, `phone_number`, `email`, `password`, `is_admin`) VALUES
(1, 'Владимир', 'Михайлович', 'Полушин', 'Москва, Зеленоградская ул., д. 1', '+74951410623', 'polushin@mail.ru', 'password', 1),
(2, 'Владислав', 'Васильевич', 'Иванов', 'Москва, Снежная ул., д. 27, корп. 1', '+74951802900', 'ivanov@mal.ru', 'password', 0),
(3, 'Алексей', 'Владимирович', 'Чернов', 'Москва, Дорожная ул., д. 12', '+74954666998', 'chernov@mail.ru', 'password', 0),
(4, 'Михаил', 'Александрович', 'Петров', 'Москва, Зеленая ул., д. 11', '+74957017849', 'petrov@mail.ru', 'password', 0),
(5, 'Глеб', 'Рудольфович', 'Самойлов', 'Москва, Дубнинская ул., д. 10', '+74959380648', 'samoilov@mail.ru', 'password', 0);


INSERT INTO `companies` (`company_id`, `company_name`) VALUES
(1, 'Автотранс'),
(2, 'Тревелбас'),
(3, 'Межтранс');


INSERT INTO `stations` (`station_id`, `station_name`) VALUES
(8, 'Сочи, ж/д вокзал'),
(5, 'Вильнюс, ж/д вокзал'),
(6, 'Рига, автостанция №2'),
(3, 'Санкт-Петербург, ж/д вокзал'),
(7, 'Ростов (трасса)'),
(2, 'Благое (трасса)'),
(4, 'Москва (автостанция Кантемировская)'),
(1, 'Москва (Павелецкий вокзал)');



INSERT INTO `directions` (`direction_id`, `direction_name`) VALUES
(1, 'Москва - Санкт-Петербург'),
(2, 'Москва - Рига'),
(3, 'Москва - Сочи');


INSERT INTO `journeys` (`journey_id`, `direction_id`, `company_id`, `number_of_places`, `start_date`, `start_time`) VALUES
(1, 1, 1, 10, '2014-06-01', '06:00:00'),
(2, 2, 2, 20, '2014-03-02', '06:00:00'),
(3, 3, 3, 30, '2014-03-01', '10:00:00');


INSERT INTO `routes` (`route_id`, `journey_id`, `station_id`, `time_of_stop`, `cost_offset`, `time_offset`) VALUES
(1, 1, 1, 0, 0, 0),
(2, 1, 2, 39, 145, 250),
(3, 1, 3, 134, 188, 450),
(4, 2, 4, 0, 0, 0),
(5, 2, 5, 29, 679, 1350),
(6, 2, 6, 35, 460, 400),
(7, 3, 1, 0, 0, 0),
(8, 3, 7, 51, 760, 860),
(9, 3, 8, 0, 540, 690);


INSERT INTO `orders` (`order_id`, `client_id`, `journey_id`, `route_start_id`, `route_end_id`, `date_of_order`) VALUES
(1, 1, 1, 1, 3, '2014-02-27 11:00:00'),
(2, 2, 2, 5, 6, '2014-02-27 12:00:00'),
(3, 3, 2, 4, 6, '2014-02-27 13:00:00'),
(4, 4, 3, 7, 9, '2014-02-27 14:00:00'),
(5, 5, 3, 7, 8, '2014-02-27 15:00:00');
