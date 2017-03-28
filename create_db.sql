CREATE TABLE `companies` (
	`company_id` bigint(11) AUTO_INCREMENT,
	`company_name` varchar(255) NOT NULL,
	PRIMARY KEY (`company_id`)
);

CREATE TABLE `clients` (
	`client_id` bigint(11) AUTO_INCREMENT,
	`first_name` varchar(55) NOT NULL,
	`middle_name` varchar(55) NOT NULL,
	`last_name` varchar(55) NOT NULL,
	`address` varchar(255) NOT NULL,
	`phone_number` varchar(55) NOT NULL,
	`email` varchar(75) NOT NULL UNIQUE,
	`password` varchar(55) NOT NULL,
	`is_admin` bit NOT NULL DEFAULT 0,
	PRIMARY KEY (`client_id`)
);

CREATE TABLE `orders` (
	`order_id` bigint(11) AUTO_INCREMENT,
	`client_id` bigint(11) NOT NULL,
	`journey_id` bigint(11) NOT NULL,
	`route_start_id` bigint(11) NOT NULL,
	`route_end_id` bigint(11) NOT NULL,
	`date_of_order` DATETIME NOT NULL,
	PRIMARY KEY (`order_id`)
);

CREATE TABLE `directions` (
	`direction_id` bigint(11) AUTO_INCREMENT,
	`direction_name` varchar(255) NOT NULL,
	PRIMARY KEY (`direction_id`)
);

CREATE TABLE `stations` (
	`station_id` bigint(11) AUTO_INCREMENT,
	`station_name` varchar(255) NOT NULL,
	PRIMARY KEY (`station_id`)
);

CREATE TABLE `journeys` (
	`journey_id` bigint(11) AUTO_INCREMENT,
	`direction_id` bigint(11) NOT NULL,
	`company_id` bigint(11) NOT NULL,
	`number_of_places` bigint(11) NOT NULL,
	`start_date` DATETIME NOT NULL,
	`start_time` DATETIME NOT NULL,
	PRIMARY KEY (`journey_id`)
);

CREATE TABLE `routes` (
	`route_id` bigint(11) AUTO_INCREMENT,
	`journey_id` bigint(11) NOT NULL,
	`station_id` bigint(11) NOT NULL,
	`time_of_stop` int(11) NOT NULL,
	`cost_offset` double NOT NULL DEFAULT '0.0',
	`time_offset` int(11) NOT NULL,
	PRIMARY KEY (`route_id`)
);

ALTER TABLE `orders` ADD CONSTRAINT `orders_fk0` FOREIGN KEY (`client_id`) REFERENCES `clients`(`client_id`);

ALTER TABLE `orders` ADD CONSTRAINT `orders_fk1` FOREIGN KEY (`journey_id`) REFERENCES `journeys`(`journey_id`);

ALTER TABLE `orders` ADD CONSTRAINT `orders_fk2` FOREIGN KEY (`route_start_id`) REFERENCES `routes`(`route_id`);

ALTER TABLE `orders` ADD CONSTRAINT `orders_fk3` FOREIGN KEY (`route_end_id`) REFERENCES `routes`(`route_id`);

ALTER TABLE `journeys` ADD CONSTRAINT `journeys_fk0` FOREIGN KEY (`direction_id`) REFERENCES `directions`(`direction_id`);

ALTER TABLE `journeys` ADD CONSTRAINT `journeys_fk1` FOREIGN KEY (`company_id`) REFERENCES `companies`(`company_id`);

ALTER TABLE `routes` ADD CONSTRAINT `routes_fk0` FOREIGN KEY (`journey_id`) REFERENCES `journeys`(`journey_id`);

ALTER TABLE `routes` ADD CONSTRAINT `routes_fk1` FOREIGN KEY (`station_id`) REFERENCES `stations`(`station_id`);
