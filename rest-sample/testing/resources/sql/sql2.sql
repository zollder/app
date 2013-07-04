-- Dumping database structure for test
DROP DATABASE IF EXISTS `test`;
CREATE DATABASE IF NOT EXISTS `test`;
USE `test`;


DROP TABLE IF EXISTS `test`.`devices`;
CREATE  TABLE IF NOT EXISTS `test`.`devices` (
  `dev_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `dev_ip` CHAR(15) NOT NULL ,
  `mac` CHAR(12) NULL ,
  `type` ENUM('y1g', 'y1g-m', 'yfm', 'yfm2', 'yfm4') NOT NULL ,
  `y1g` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`dev_ip`) ,
  UNIQUE INDEX `id_UNIQUE` (`dev_id` ASC) ,
  UNIQUE INDEX `ip_UNIQUE` (`dev_ip` ASC) ,
  UNIQUE INDEX `mac_UNIQUE` (`mac` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'holds all the devices information';

INSERT INTO devices (type, dev_ip)
VALUES ('YFM2','192.168.168.5');
