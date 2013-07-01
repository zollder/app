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

DROP TABLE IF EXISTS `devices`;
CREATE TABLE `devices`
(
  id INT(10) UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT ,
  type ENUM('Y1G','Y1G-M','YFM','YFM2','YFM4') NOT NULL ,
  ip INT(10) UNSIGNED NOT NULL UNIQUE,
  ip2 varchar(15) NOT NULL UNIQUE,
  PRIMARY KEY (id)
);

INSERT INTO `user`
VALUES
    (1,'Admin','User','adminuser','d9acd48369a1a20280c8c3b6921d8d8a','admin@email.com',1,1,1);
INSERT INTO test.devices (type,ip,ip2)
VALUES ('Y1G',125425,'192.168.168.1');
INSERT INTO test.devices (type,ip,ip2)
VALUES ('Y1G-M',125426,'192.168.168.2');
INSERT INTO test.devices (type,ip,ip2)
VALUES ('YFM',125427,'192.168.168.3');
INSERT INTO test.devices (type,ip,ip2)
VALUES ('YFM2',125428,'192.168.168.4');
