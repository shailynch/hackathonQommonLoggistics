drop schema logistics;

CREATE SCHEMA IF NOT EXISTS `logistics`;

USE `logistics` ;

CREATE TABLE IF NOT EXISTS `logistics`.`manager` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `last_name` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `logistics`.`driver` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `last_name` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `logistics`.`schedule` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `driver_id` INT(11) DEFAULT NULL,
    `lorry_id` VARCHAR(40) DEFAULT NULL,
    `area` VARCHAR(40) DEFAULT NULL,
    `schedule_date` DATE DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `logistics`.`lorry` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `registration` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `logistics`.`crate` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `schedule_id` INT(11) DEFAULT NULL,
    `area` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `logistics`.`product` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `address` VARCHAR(100) DEFAULT NULL,
    `crate_id` INT(11) DEFAULT NULL,
    `delivery_status` VARCHAR(100) DEFAULT NULL,
    `last_updated` DATETIME DEFAULT NULL,
    PRIMARY KEY (`id`)
);
