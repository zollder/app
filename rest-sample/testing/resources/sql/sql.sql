-- Dumping database structure for test
DROP DATABASE IF EXISTS `test`;
CREATE DATABASE IF NOT EXISTS `test`;
USE `test`;

DROP TABLE IF EXISTS `contacts`;
CREATE TABLE  `contacts`
(
  id int(10) unsigned NOT NULL AUTO_INCREMENT,
  name varchar(45) NOT NULL,
  address varchar(45) DEFAULT NULL,
  gender char(1) DEFAULT 'M',
  dob datetime DEFAULT NULL,
  email varchar(45) DEFAULT NULL,
  mobile varchar(15) DEFAULT NULL,
  phone varchar(15) DEFAULT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS `user`;
CREATE TABLE  `user`
(
  primaryKey int(10) unsigned NOT NULL UNIQUE AUTO_INCREMENT,
  firstName varchar(45) NOT NULL,
  lastName varchar(45) NOT NULL,
  userName varchar(45) NOT NULL UNIQUE,
  password varchar(80) NOT NULL,
  email varchar(45) DEFAULT NULL,
  isEnabled boolean NOT NULL,
  canLogin boolean NOT NULL,
  isAdmin boolean NOT NULL,
  PRIMARY KEY (primaryKey)
);



DROP TABLE IF EXISTS `test`.`devices`;
CREATE  TABLE IF NOT EXISTS `test`.`devices` (
  `dev_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `dev_ip` CHAR(15) NOT NULL ,
  `mac` CHAR(12) NULL ,
  `type` ENUM('y1g', 'y1g-m', 'yfm', 'yfm2', 'yfm4') NOT NULL ,
  PRIMARY KEY (`dev_ip`) ,
  UNIQUE INDEX `id_UNIQUE` (`dev_id` ASC) ,
  UNIQUE INDEX `ip_UNIQUE` (`dev_ip` ASC) ,
  UNIQUE INDEX `mac_UNIQUE` (`mac` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'holds all the devices information';


DROP TABLE IF EXISTS `y1g`;
CREATE  TABLE IF NOT EXISTS `test`.`y1g` (
  `switchStatus` ENUM('ovr','off','auto') NOT NULL ,
  `ledOnColor` ENUM('none','red','green','blue','amber','cycle','toggle') NOT NULL ,
  `ledOffColor` ENUM('none','red','green','blue','amber','cycle','toggle') NOT NULL ,
  `latchActive` TINYINT(1) NULL ,
  `bPressLapse` INT UNSIGNED NOT NULL ,
  `networkOn` TINYINT(1) NULL ,
  `flickWarn` TINYINT(1) NULL ,
  `flickReps` INT UNSIGNED NOT NULL ,
  `firmwareVersion` VARCHAR(4) NOT NULL ,
  `name` VARCHAR(32) NOT NULL ,
  `location` VARCHAR(128) NOT NULL ,
  `dev_ip` CHAR(15) NOT NULL ,
  PRIMARY KEY (`dev_ip`) ,
  CONSTRAINT `fk_y1g_devices`
    FOREIGN KEY (`dev_ip` )
    REFERENCES `test`.`devices` (`dev_ip` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Y1G oBIX Variables';

INSERT INTO `user`
VALUES
    (1,'Admin','User','adminuser','d9acd48369a1a20280c8c3b6921d8d8a','admin@email.com',1,1,1);
INSERT INTO test.devices (type,dev_ip)
VALUES ('Y1G','192.168.168.1');
INSERT INTO test.devices (type,dev_ip)
VALUES ('Y1G-M','192.168.168.2');
INSERT INTO test.devices (type,dev_ip)
VALUES ('YFM','192.168.168.3');
INSERT INTO test.y1g (switchStatus,ledOnColor,ledOffColor,latchActive,bPressLapse,networkOn,flickWarn,flickReps,firmwareVersion,name,location,dev_ip)
VALUES ('auto','blue','red',true,50,true,false,2,'2.10','arcadian','House','192.168.168.1');

INSERT INTO test.devices (type,dev_ip)
VALUES ('YFM4','192.168.168.4');
