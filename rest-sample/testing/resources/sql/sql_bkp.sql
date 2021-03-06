-- Dumping database structure for test
DROP DATABASE IF EXISTS `test`;
CREATE DATABASE IF NOT EXISTS `test` DEFAULT CHARACTER SET latin1;
USE `test`;

DROP TABLE IF EXISTS `device`;
CREATE  TABLE IF NOT EXISTS `device` (
  primaryKey int(10) unsigned NOT NULL AUTO_INCREMENT,
  devIp varchar(15) NOT NULL UNIQUE,
  devMac varchar(17) DEFAULT NULL UNIQUE,
  devType bigint NOT NULL,
  devName varchar(20) NOT NULL UNIQUE,
  devLocation VARCHAR(128) default NULL,
  firmwareVersion VARCHAR(4) default NULL,
  devDescription varchar(240) default NULL,
  PRIMARY KEY (primaryKey)
);

DROP TABLE IF EXISTS `device_bck`;
CREATE  TABLE IF NOT EXISTS `device_bck` (
  `primaryKey` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `dev_ip` CHAR(15) NOT NULL ,
  `dev_mac` CHAR(17) NULL ,
  `dev_type` ENUM('TYPE_G', 'TYPE_GM', 'TYPE_F', 'TYPE_F2', 'TYPE_F4') NOT NULL ,
  UNIQUE INDEX `dev_mac_UNIQUE` (`dev_mac` ASC) ,
  UNIQUE INDEX `primaryKey_UNIQUE` (`primaryKey` ASC) ,
  UNIQUE INDEX `dev_ip_UNIQUE` (`dev_ip` ASC) ,
  PRIMARY KEY (`primaryKey`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'holds all the devices information';


DROP TABLE IF EXISTS `type_f`;
CREATE  TABLE IF NOT EXISTS `type_f` (
  primaryKey int(10) unsigned NOT NULL AUTO_INCREMENT,
  deviceId int(10) unsigned NOT NULL,
  latchActive boolean default NULL,
  bPressLapse int unsigned default NULL ,
  flickWarn boolean default NULL,
  flickReps int unsigned default NULL,
  offDelay int unsigned default NULL,
  motionMuteDelay int unsigned default NULL,
  dim int unsigned default NULL,
  dimMin int unsigned default NULL,
  dimMode ENUM('off','on','onFade') NOT NULL,
  input boolean default NULL,
  switchStatus ENUM('ovr','off','auto') NOT NULL,
  networkOn boolean default NULL,
  PRIMARY KEY (primaryKey),
  CONSTRAINT `fk_type_g` FOREIGN KEY (`deviceId`) REFERENCES `device` (`primaryKey`)
);

DROP TABLE IF EXISTS `type_f_bck`;
CREATE  TABLE IF NOT EXISTS `type_f_bck` (
  `device_dev_ip` CHAR(15) NOT NULL ,
  `typef_id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(32) NOT NULL ,
  `location` VARCHAR(128) NOT NULL ,
  `firmwareVersion` VARCHAR(4) NOT NULL ,
  `latchActive` TINYINT(1) NULL ,
  `bPressLapse` INT UNSIGNED NOT NULL ,
  `flickWarn` TINYINT(1) NULL ,
  `flickReps` INT UNSIGNED NOT NULL ,
  `offDelay` INT UNSIGNED NULL ,
  `motionMuteDelay` INT UNSIGNED NULL ,
  `dim` INT UNSIGNED NULL ,
  `dimMin` INT UNSIGNED NULL ,
  `dimMode` ENUM('off','on','onFade') NOT NULL ,
  `input` TINYINT(1) NULL ,
  `switchStatus` ENUM('ovr','off','auto') NOT NULL ,
  `networkOn` TINYINT(1) NULL ,
  PRIMARY KEY (`device_dev_ip`) ,
  INDEX `fk_type_f_device_idx` (`device_dev_ip` ASC) ,
  UNIQUE INDEX `typef_id_UNIQUE` (`typef_id` ASC) ,
  UNIQUE INDEX `device_dev_ip_UNIQUE` (`device_dev_ip` ASC) ,
  CONSTRAINT `fk_type_f_device`
    FOREIGN KEY (`device_dev_ip` )
    REFERENCES `device` (`dev_ip` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Device ceiling mount oBIX variables';

-- Creating testing Values for type_f--
INSERT INTO type_f_bck
VALUES ('192.168.0.2','1','typeFTest1','Virtual1','0.10','1','65534','0','2','600','60','10','0','off','0','auto','1');


DROP TABLE IF EXISTS `type_g`;
CREATE  TABLE IF NOT EXISTS `type_g` (
  `device_dev_ip` CHAR(15) NOT NULL ,
  `typeg_id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(32) NOT NULL ,
  `location` VARCHAR(128) NOT NULL ,
  `firmwareVersion` VARCHAR(4) NOT NULL ,
  `latchActive` TINYINT(1) NULL ,
  `bPressLapse` INT UNSIGNED NOT NULL ,
  `flickWarn` TINYINT(1) NULL ,
  `flickReps` INT UNSIGNED NOT NULL ,
  `ledOnColor` ENUM('none','red','green','blue','amber','cycle','toggle') NOT NULL ,
  `ledOffColor` ENUM('none','red','green','blue','amber','cycle','toggle') NOT NULL ,
  `switchStatus` ENUM('ovr','off','auto') NOT NULL ,
  `networkOn` TINYINT(1) NULL ,
  UNIQUE INDEX `typeg_id_UNIQUE` (`typeg_id` ASC) ,
  UNIQUE INDEX `device_dev_ip_UNIQUE` (`device_dev_ip` ASC) ,
  PRIMARY KEY (`device_dev_ip`) ,
  CONSTRAINT `fk_type_g_device`
    FOREIGN KEY (`device_dev_ip` )
    REFERENCES `device` (`dev_ip` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Device no motion oBIX variables';

-- Creating testing Values for type_g--
INSERT INTO type_g
VALUES ('192.168.0.3','1','typeGTest1','virtual2','1.7','0','45220','0','3','blue','red','auto','1');


DROP TABLE IF EXISTS `type_gm`;
CREATE  TABLE IF NOT EXISTS `type_gm` (
  `device_dev_ip` CHAR(15) NOT NULL ,
  `typegm_id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(32) NULL ,
  `location` VARCHAR(128) NULL ,
  `firmwareVersion` VARCHAR(4) NULL ,
  `latchActive` TINYINT(1) NULL ,
  `bPressLapse` INT UNSIGNED NULL ,
  `flickWarn` TINYINT(1) NULL ,
  `flickReps` INT UNSIGNED NULL ,
  `offDelay` INT UNSIGNED NULL ,
  `ledOnColor` ENUM('none','red','green','blue','amber','cycle','toggle') NOT NULL ,
  `ledOffColor` ENUM('none','red','green','blue','amber','cycle','toggle') NOT NULL ,
  `pirSens` INT UNSIGNED NULL ,
  `motionMuteDelay` INT UNSIGNED NULL ,
  `noMotionTime` INT UNSIGNED NULL ,
  `luminosity` INT UNSIGNED NULL ,
  `lumFactor` INT UNSIGNED NULL ,
  `mode` ENUM('man','autoOnOff','manOnAutoOff') NOT NULL ,
  `switchStatus` ENUM('ovr','off','auto') NOT NULL ,
  `networkOn` TINYINT(1) NULL ,
  PRIMARY KEY (`device_dev_ip`) ,
  UNIQUE INDEX `typegm_id_UNIQUE` (`typegm_id` ASC) ,
  UNIQUE INDEX `device_dev_ip_UNIQUE` (`device_dev_ip` ASC) ,
  CONSTRAINT `fk_type_gm_device1`
    FOREIGN KEY (`device_dev_ip` )
    REFERENCES `device` (`dev_ip` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Device motion sensor oBIX variables';

-- Creating testing Values for type_gm--
INSERT INTO test.type_gm
VALUES ('192.168.0.4','1','typeGmTest1','virtual3','1.7','1','240','0','3','900','green','amber','12','60','600','954','100','autoOnOff','auto','1');



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

-- need to update with new version of user--
DROP TABLE IF EXISTS `user_bck`;
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

-- Creating testing Values for user--
INSERT INTO `user`
VALUES (1,'Admin','User','adminuser','d9acd48369a1a20280c8c3b6921d8d8a','admin@email.com',1,1,1);

