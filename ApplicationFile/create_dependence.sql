ALTER TABLE `foot`.`product`
ADD COLUMN `image` VARCHAR(90) NOT NULL AFTER `typeid`,
CHANGE COLUMN `typeid` `typeid` INT(11) NOT NULL ;

ALTER TABLE `foot`.`bill`
ADD COLUMN `idproduct` INT NOT NULL AFTER `count`;

ALTER TABLE bill  ADD CONSTRAINT FK_product_bill FOREIGN KEY (idproduct) REFERENCES product(idproduct)
ALTER TABLE login  ADD CONSTRAINT FK_login_user FOREIGN KEY (iduser) REFERENCES user(iduser)
ALTER TABLE user ADD CONSTRAINT FK_role_user FOREIGN KEY (idrole) REFERENCES role(idrole)
ALTER TABLE product  ADD CONSTRAINT FK_product_type FOREIGN KEY (typeid) REFERENCES type(typeid)
ALTER TABLE imageproduct  ADD CONSTRAINT FK_product_image FOREIGN KEY (idproduct) REFERENCES product(idproduct)
ALTER TABLE bill  ADD CONSTRAINT FK_user_bill FOREIGN KEY (iduser) REFERENCES user(iduser)
ALTER TABLE comment  ADD CONSTRAINT FK_comment_user FOREIGN KEY (iduser) REFERENCES user(iduser)
ALTER TABLE comment  ADD CONSTRAINT FK_comment_pro FOREIGN KEY (idproduct) REFERENCES product(idproduct)
ALTER TABLE detailsale  ADD CONSTRAINT FK_detail_sale FOREIGN KEY (idsale) REFERENCES sale(idsale)
ALTER TABLE detailsale  ADD CONSTRAINT FK_detailsale_product FOREIGN KEY (idproduct) REFERENCES product(idproduct)
ALTER TABLE feedback  ADD CONSTRAINT FK_feedback_user FOREIGN KEY (iduser) REFERENCES user(iduser);

ALTER TABLE `foot`.`transporter`
ADD INDEX `fk_transporter_user_idx` (`iduser` ASC) VISIBLE,
ADD INDEX `fk_transporter_bill_idx` (`idbill` ASC) VISIBLE;
;
ALTER TABLE `foot`.`transporter`
ADD CONSTRAINT `fk_transporter_user`
  FOREIGN KEY (`iduser`)
  REFERENCES `foot`.`user` (`iduser`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_transporter_bill`
  FOREIGN KEY (`idbill`)
  REFERENCES `foot`.`bill` (`idbill`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
