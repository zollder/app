
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
VALUES
    (1,'name 1','address 1','F','2000-04-16','test1@email.com','5145145111','5145145121'),
    (2,'name 2','address 2','F','2000-04-17','test2@email.com','5145145112','5145145122'),
    (3,'name 3','address 3','M','2000-04-18','test3@email.com','5145145113','5145145123');

DROP TABLE IF EXISTS `user`;
CREATE TABLE  `user`
(
  primaryKey int(10) unsigned NOT NULL AUTO_INCREMENT,
  firstName varchar(45) NOT NULL,
  lastName varchar(45) NOT NULL,
  userName varchar(45) NOT NULL,
  password varchar(80),
  email varchar(45) DEFAULT NULL,
  canLogin boolean NOT NULL,
  isAdmin boolean NOT NULL,
  PRIMARY KEY (primaryKey)
);