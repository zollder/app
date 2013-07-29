-- Dumping database structure for test
DROP DATABASE IF EXISTS `test`;
CREATE DATABASE IF NOT EXISTS `test` DEFAULT CHARACTER SET latin1;
USE `test`;

DROP TABLE IF EXISTS `device`;
CREATE  TABLE IF NOT EXISTS `device` (
  primaryKey int(10) unsigned NOT NULL AUTO_INCREMENT,
  devIp varchar(15) NOT NULL UNIQUE,
  devMac varchar(17) DEFAULT NULL UNIQUE,
  devType varchar(7) NOT NULL,
  devName varchar(20) NOT NULL UNIQUE,
  devLocation VARCHAR(128) default NULL,
  firmwareVersion VARCHAR(4) default NULL,
  devDescription varchar(240) default NULL,
  PRIMARY KEY (primaryKey)
);


DROP TABLE IF EXISTS `type_f`;
CREATE  TABLE IF NOT EXISTS `type_f` (
  primaryKey int(10) unsigned NOT NULL,
  latchActive boolean default NULL,
  bPressLapse int unsigned default NULL ,
  flickWarn boolean default NULL,
  flickReps int unsigned default NULL,
  offDelay int unsigned default NULL,
  motionMuteDelay int unsigned default NULL,
  dim int unsigned default NULL,
  dimMin int unsigned default NULL,
  dimMode varchar(6) NOT NULL,
  input boolean default NULL,
  switchStatus varchar(4) NOT NULL,
  networkOn boolean default NULL,
  CONSTRAINT `fk_type_f` FOREIGN KEY (`primaryKey`) REFERENCES `device` (`primaryKey`)
);

DROP TABLE IF EXISTS `type_g`;
CREATE  TABLE IF NOT EXISTS `type_g` (
  primaryKey int(10) unsigned NOT NULL,
  latchActive boolean default NULL,
  bPressLapse int unsigned default NULL,
  flickWarn boolean default NULL,
  flickReps int unsigned default NULL,
  ledOnColor varchar(6) NOT NULL,
  ledOffColor varchar(6) NOT NULL,
  switchStatus varchar(4) NOT NULL,
  networkOn boolean default NULL,
  CONSTRAINT `fk_type_g` FOREIGN KEY (`primaryKey`) REFERENCES `device` (`primaryKey`)
);

DROP TABLE IF EXISTS `type_gm`;
CREATE  TABLE IF NOT EXISTS `type_gm` (
  primaryKey int(10) unsigned NOT NULL,
  latchActive boolean default NULL,
  bPressLapse int unsigned default NULL,
  flickWarn boolean default NULL,
  flickReps int unsigned default NULL,
  ledOnColor varchar(6) NOT NULL,
  ledOffColor varchar(6) NOT NULL,
  pirSens int unsigned NULL ,
  motionMuteDelay int unsigned NULL ,
  noMotionTime int unsigned NULL ,
  luminosity int unsigned NULL ,
  lumFactor int unsigned NULL ,
  mode varchar(15) NOT NULL,
  switchStatus varchar(10) NOT NULL,
  networkOn boolean default NULL,
  CONSTRAINT `fk_type_gm` FOREIGN KEY (`primaryKey`) REFERENCES `device` (`primaryKey`)
);

-- Need to update with new version of contacts--
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

INSERT INTO `contacts`
VALUES (1,'John','6750 Des Moines','M','2013-08-07 9:00:00','admin@email.com','514-123-4568','514-456-7898');


-- need to update with new version of user--
DROP TABLE IF EXISTS `user`;
CREATE TABLE  `user`
(
  primaryKey int(10) unsigned NOT NULL AUTO_INCREMENT,
  firstName varchar(45) NOT NULL,
  lastName varchar(45) NOT NULL,
  userName varchar(45) NOT NULL,
  password varchar(80) NOT NULL,
  email varchar(45) DEFAULT NULL,
  isEnabled boolean NOT NULL,
  canLogin boolean NOT NULL,
  isAdmin boolean NOT NULL,
  PRIMARY KEY (primaryKey),
  UNIQUE (userName, email)
);


-- Creating testing Values for user--
INSERT INTO `user`
VALUES (1,'Admin','User','adminuser','d9acd48369a1a20280c8c3b6921d8d8a','admin@email.com',1,1,1);

