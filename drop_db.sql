USE journeys;

ALTER TABLE `orders` DROP FOREIGN KEY `orders_fk0`;

ALTER TABLE `orders` DROP FOREIGN KEY `orders_fk1`;

ALTER TABLE `orders` DROP FOREIGN KEY `orders_fk2`;

ALTER TABLE `orders` DROP FOREIGN KEY `orders_fk3`;

ALTER TABLE `journeys` DROP FOREIGN KEY `journeys_fk0`;

ALTER TABLE `journeys` DROP FOREIGN KEY `journeys_fk1`;

ALTER TABLE `routes` DROP FOREIGN KEY `routes_fk0`;

ALTER TABLE `routes` DROP FOREIGN KEY `routes_fk1`;

DROP TABLE IF EXISTS `companies`;

DROP TABLE IF EXISTS `clients`;

DROP TABLE IF EXISTS `orders`;

DROP TABLE IF EXISTS `directions`;

DROP TABLE IF EXISTS `stations`;

DROP TABLE IF EXISTS `journeys`;

DROP TABLE IF EXISTS `routes`;
