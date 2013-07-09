-- Dumping database structure for test
DROP DATABASE IF EXISTS `HybridDB`;
CREATE DATABASE IF NOT EXISTS `HybridDB` DEFAULT CHARACTER SET latin1;
USE `HybridDB`;

CREATE  TABLE IF NOT EXISTS `HybridDB`.`devices` (
  `dev_id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `dev_ip` CHAR(15) NOT NULL ,
  `mac` CHAR(12) NULL ,
  `type` ENUM('y1g', 'y1g-m', 'yfm', 'yfm2', 'yfm4') NOT NULL ,
  PRIMARY KEY (`dev_ip`) ,
  UNIQUE INDEX `mac_UNIQUE` (`mac` ASC) ,
  UNIQUE INDEX `dev_id_UNIQUE` (`dev_id` ASC) ,
  UNIQUE INDEX `dev_ip_UNIQUE` (`dev_ip` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'holds all the devices information';

CREATE  TABLE IF NOT EXISTS `HybridDB`.`type_f` (
  `typef_id` INT NOT NULL AUTO_INCREMENT ,
  `devices_dev_ip` CHAR(15) NOT NULL ,
  `name` VARCHAR(32) NOT NULL ,
  `location` VARCHAR(128) NOT NULL ,
  `switchStatus` ENUM('ovr','off','auto') NOT NULL ,
  `latchActive` TINYINT(1) NULL ,
  `bPressLapse` INT UNSIGNED NOT NULL ,
  `networkOn` TINYINT(1) NULL ,
  `flickWarn` TINYINT(1) NULL ,
  `flickReps` INT UNSIGNED NOT NULL ,
  `firmwareVersion` VARCHAR(4) NOT NULL ,
  `dim` INT UNSIGNED NULL ,
  `dimMin` INT UNSIGNED NULL ,
  `dimMode` ENUM('off','on','onFade') NOT NULL ,
  `motionMuteDelay` INT UNSIGNED NULL ,
  `input` TINYINT(1) NULL ,
  `offDelay` INT UNSIGNED NULL ,
  PRIMARY KEY (`devices_dev_ip`) ,
  INDEX `fk_type_f_devices_idx` (`devices_dev_ip` ASC) ,
  UNIQUE INDEX `typef_id_UNIQUE` (`typef_id` ASC) ,
  UNIQUE INDEX `devices_dev_ip_UNIQUE` (`devices_dev_ip` ASC) ,
  CONSTRAINT `fk_type_f_devices`
    FOREIGN KEY (`devices_dev_ip` )
    REFERENCES `HybridDB`.`devices` (`dev_ip` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Device ceiling mount oBIX variables';

CREATE TABLE IF NOT EXISTS `HybridDB`.`type_g` (
  `typeg_id` INT NOT NULL AUTO_INCREMENT ,
  `devices_dev_ip` CHAR(15) NOT NULL ,
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
  UNIQUE INDEX `typeg_id_UNIQUE` (`typeg_id` ASC) ,
  UNIQUE INDEX `devices_dev_ip_UNIQUE` (`devices_dev_ip` ASC) ,
  PRIMARY KEY (`devices_dev_ip`) ,
  CONSTRAINT `fk_type_g_devices`
    FOREIGN KEY (`devices_dev_ip` )
    REFERENCES `HybridDB`.`devices` (`dev_ip` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Device no motion oBIX variables';

-- Creating testing Values for--
INSERT INTO devices (type, dev_ip)
VALUES ('y1g','192.168.168.1');
INSERT INTO devices (type, dev_ip)
VALUES ('y1g-m','192.168.168.2');
INSERT INTO devices (type, dev_ip)
VALUES ('yfm','192.168.168.3');
INSERT INTO devices (type, dev_ip)
VALUES ('yfm2','192.168.168.4');
INSERT INTO devices (type, dev_ip)
VALUES ('yfm4','192.168.168.5');

INSERT INTO type_f (name, devices_dev_ip,location,latchActive,networkOn,flickWarn,firmwareVersion,dim,dimMin,motionMuteDelay,input,offDelay)
VALUES ('yfm','192.168.168.3','test','1','1','0','2.10','10','5','90','0','30');

