CREATE TABLE `foot`.`role` (
  `idrole` INT NOT NULL AUTO_INCREMENT,
  `rolename` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idrole`),
  UNIQUE INDEX `rolename_UNIQUE` (`rolename` ASC) VISIBLE,
  UNIQUE INDEX `idrole_UNIQUE` (`idrole` ASC) VISIBLE);


  CREATE TABLE `foot`.`user` (
  `iduser` INT NOT NULL,
  `idrole` INT NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `namefull` VARCHAR(45) NOT NULL,
  `place` VARCHAR(90) NOT NULL,
  `sex` INT NOT NULL,
  `cardid` VARCHAR(45) NOT NULL,
  `phonenumber` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`iduser`),
  UNIQUE INDEX `idmember_UNIQUE` (`iduser` ASC) VISIBLE);

  CREATE TABLE `foot`.`product` (
  `idproduct` INT NOT NULL AUTO_INCREMENT,
  `nameproduct` VARCHAR(90) NOT NULL,
  `cost` INT NOT NULL,
  `information` LONGTEXT NOT NULL,
  `typeid` INT NULL,
  PRIMARY KEY (`idproduct`),
  UNIQUE INDEX `idproduct_UNIQUE` (`idproduct` ASC) VISIBLE);

  CREATE TABLE `foot`.`imageproduct` (
  `idproduct` INT NOT NULL AUTO_INCREMENT,
  `images` VARCHAR(90) NOT NULL,
  PRIMARY KEY (`idproduct`));


  CREATE TABLE `foot`.`type` (
  `typeid` INT NOT NULL AUTO_INCREMENT,
  `typename` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`typeid`));

  CREATE TABLE `foot`.`bill` (
  `idbill` INT NOT NULL AUTO_INCREMENT,
  `iduser` INT NOT NULL,
  `phonenumber` VARCHAR(45) NOT NULL,
  `place` VARCHAR(45) NOT NULL,
  `datetime` TIMESTAMP NOT NULL,
  `status` INT NOT NULL,
  `cost` INT NOT NULL,
  `count` INT NOT NULL,
  PRIMARY KEY (`idbill`),
  UNIQUE INDEX `idbill_UNIQUE` (`idbill` ASC) VISIBLE);

  CREATE TABLE `foot`.`login` (
  `iduser` INT NOT NULL,
  `namelogin` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`iduser`),
  UNIQUE INDEX `iduser_UNIQUE` (`iduser` ASC) VISIBLE);

  CREATE TABLE `foot`.`sale` (
  `idsale` INT NOT NULL AUTO_INCREMENT,
  `timesrart` TIMESTAMP NOT NULL,
  `timeend` TIMESTAMP NOT NULL,
  `Detail` LONGTEXT NOT NULL,
  PRIMARY KEY (`idsale`),
  UNIQUE INDEX `idsale_UNIQUE` (`idsale` ASC) VISIBLE);

  CREATE TABLE `foot`.`detailsale` (
  `idsale` INT NOT NULL,
  `idproduct` INT NOT NULL,
  `downpercen` INT NOT NULL);

  CREATE TABLE `foot`.`comment` (
  `idcomment` INT NOT NULL AUTO_INCREMENT,
  `iduser` INT NOT NULL,
  `idproduct` INT NOT NULL,
  `rate` INT NOT NULL,
  `datetime` TIMESTAMP NOT NULL,
  `content` LONGTEXT NOT NULL,
  PRIMARY KEY (`idcomment`),
  UNIQUE INDEX `idcomment_UNIQUE` (`idcomment` ASC) VISIBLE);

  CREATE TABLE `foot`.`myhistory` (
  `idhistory` INT NOT NULL AUTO_INCREMENT,
  `iduser` INT NOT NULL,
  `dateTime` TIMESTAMP NOT NULL,
  `content` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`idhistory`));



CREATE TABLE `foot`.`feedback` (
   `idfeedback` INT NOT NULL AUTO_INCREMENT,
   `iduser` INT NOT NULL,
   `feedbackcontent` LONGTEXT NOT NULL,
   `datetime` TIMESTAMP NOT NULL,
    `check` INT NOT NULL,

   PRIMARY KEY (`idfeedback`));


CREATE TABLE `foot`.`transporter` (
  `idtransporter` INT NOT NULL AUTO_INCREMENT,
  `iduser` INT NOT NULL,
  `idbill` INT NOT NULL,
  `datetime` TIMESTAMP NOT NULL,
  `status` INT NOT NULL,
  PRIMARY KEY (`idtransporter`));


CREATE TABLE `foot`.`location` (
  `idlocation` INT NOT NULL AUTO_INCREMENT,
  `idlogin` INT NOT NULL,
  `longitude` VARCHAR(45) NOT NULL,
  `longitude` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idlocation`));


CREATE TABLE `foot`.`configuration` (
  `key` VARCHAR(90) NOT NULL,
  `mail.username` VARCHAR(45) NULL,
  `mail.password` VARCHAR(45) NULL,
  `mail.host` VARCHAR(45) NULL,
  `subject.user.create` VARCHAR(45) NULL,
  PRIMARY KEY (`key`));

ALTER TABLE `foot`.`configuration`
DROP COLUMN `subject.user.create`,
CHANGE COLUMN `mail.username` `type` VARCHAR(90) NOT NULL ,
CHANGE COLUMN `mail.password` `name` VARCHAR(90) NOT NULL ,
CHANGE COLUMN `mail.host` `text` VARCHAR(90) NULL DEFAULT NULL ;