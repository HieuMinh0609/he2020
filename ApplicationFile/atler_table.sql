ALTER TABLE `foot`.`detailsale`
ADD COLUMN `iddetailsale` INT NOT NULL AUTO_INCREMENT FIRST,
ADD PRIMARY KEY (`iddetailsale`),
ADD UNIQUE INDEX `iddetailsale_UNIQUE` (`iddetailsale` ASC) VISIBLE;

ALTER TABLE `foot`.`imageproduct`
DROP FOREIGN KEY `FK_product_image`;
ALTER TABLE `foot`.`imageproduct`
ADD COLUMN `idimageproduct` INT NOT NULL AUTO_INCREMENT FIRST,
CHANGE COLUMN `idproduct` `idproduct` INT(11) NOT NULL ,
DROP PRIMARY KEY,
ADD PRIMARY KEY (`idimageproduct`);
;
ALTER TABLE `foot`.`imageproduct`
ADD CONSTRAINT `FK_product_image`
  FOREIGN KEY (`idproduct`)
  REFERENCES `foot`.`product` (`idproduct`);

ALTER TABLE `foot`.`login`
ADD COLUMN `idlogin` INT NOT NULL AUTO_INCREMENT FIRST,
DROP INDEX `iduser_UNIQUE` ,
ADD UNIQUE INDEX `iduser_UNIQUE` (`iduser` ASC) VISIBLE,
DROP PRIMARY KEY,
ADD PRIMARY KEY (`idlogin`),
ADD UNIQUE INDEX `idlogin_UNIQUE` (`idlogin` ASC) VISIBLE;
;

ALTER TABLE `foot`.`user`
ADD COLUMN `check` INT NULL AFTER `phonenumber`;


ALTER TABLE `foot`.`product`
ADD COLUMN `view` INT NOT NULL AFTER `image`;

ALTER TABLE `foot`.`sale`
ADD COLUMN `name` VARCHAR(95) NOT NULL AFTER `Detail`;

ALTER TABLE `foot`.`transporter`
ADD COLUMN `prioritize` INT(11) NOT NULL AFTER `status`;
