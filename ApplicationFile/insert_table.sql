INSERT INTO role(rolename) VALUES("ADMIN");
INSERT INTO role(rolename) VALUES("USER");
/*insert product*/
 INSERT INTO `foot`.`product` (`nameproduct`, `cost`, `information`, `typeid`, `image`) VALUES ('Bánh Mì 24', 50000, 'Bánh mì sài gòn đặc biệt thơm ngon', 1, '1.jsp')
 INSERT INTO `foot`.`product` (`nameproduct`, `cost`, `information`, `typeid`, `image`) VALUES ('Đùi bò nướng', 200000, 'Đùi bò nướng tây bắc thơm ngon nức mũi', 2, '2.jsp')
 INSERT INTO `foot`.`product` (`nameproduct`, `cost`, `information`, `typeid`, `image`) VALUES ('Nước Chanh Lạnh', 20000, 'Nước chanh tươi mát sảng khoái giàu vitamin', 3, '3.jsp')
 INSERT INTO `foot`.`product` (`nameproduct`, `cost`, `information`, `typeid`, `image`) VALUES ('SanWich 10', 40000, 'San Wich ý nhập khẩu thơn ngon ,bổ dưỡng', 1, '4.jsp')
 INSERT INTO `foot`.`product` (`nameproduct`, `cost`, `information`, `typeid`, `image`) VALUES ('Pizza', 80000, 'Piza Mỹ đảm bảo chất lượng ,thơm ngon nhân thịt gà và cà chua', 1, '5.jsp')
 INSERT INTO `foot`.`product` (`nameproduct`, `cost`, `information`, `typeid`, `image`) VALUES ('Cơm Rang Muối', 30000, 'Thơm ngon bổ dưỡng ,nóng hổi', 2, '6.jsp')
 INSERT INTO `foot`.`product` (`nameproduct`, `cost`, `information`, `typeid`, `image`) VALUES ('Súp Trứng Cá', 50000, 'Trứng thơm,sản xuất trong ngày đặc biệt thơm ngon bổ dưỡng', 1, '7.jsp')

/*insert type*/
INSERT INTO `foot`.`type` (`typename`) VALUES ('Đồ Ăn Nhanh')
INSERT INTO `foot`.`type` (`typename`) VALUES ('Đồ Ăn Nóng')
INSERT INTO `foot`.`type` (`typename`) VALUES ('Đồ Uống')


/*insert image product*/
INSERT INTO `foot`.`imageproduct` (`idproduct`, `images`) VALUES (1, '/product/banh-mi.jpg');
INSERT INTO `foot`.`imageproduct` (`idproduct`, `images`) VALUES (1, '/product/banhmi-viet.jpg');
INSERT INTO `foot`.`imageproduct` (`idproduct`, `images`) VALUES (1, '/product/esdefault.jpg');
INSERT INTO `foot`.`imageproduct` (`idproduct`, `images`) VALUES (1, '/product/Instant-Pot-Banh-Mi-L1-Paint-the-Kitchen-Red-.jpg');

/*insert product bill*/

INSERT INTO `foot`.`bill` (`iduser`, `phonenumber`, `place`, `datetime`, `status`, `cost`, `count`, `idproduct`,online) VALUES (15, '09546445463', 'Nam Định ,24 Văn phong', '2019-07-23 11:08:59', 0, 400000, 2, 2,0);
INSERT INTO `foot`.`bill` (`iduser`, `phonenumber`, `place`, `datetime`, `status`, `cost`, `count`, `idproduct`,online) VALUES (4, '09652335623', 'Hà Nội', '2019-07-23 11:07:40', 0, 100000, 2, 1,0);
INSERT INTO `foot`.`bill` (`iduser`, `phonenumber`, `place`, `datetime`, `status`, `cost`, `count`, `idproduct`,online) VALUES (46, '095464342463', 'Nam Định ,24 Văn phong', '2019-07-23 12:08:59', 0, 400000, 2, 2,0);
INSERT INTO `foot`.`bill` (`iduser`, `phonenumber`, `place`, `datetime`, `status`, `cost`, `count`, `idproduct`,online) VALUES (54, '09546445499', 'Nam Hà ,24 Văn phong', '2019-07-23 7:08:59', 1, 80000, 2, ,4,0);
INSERT INTO `foot`.`bill` (`iduser`, `phonenumber`, `place`, `datetime`, `status`, `cost`, `count`, `idproduct`,online) VALUES (55, '09546445499', 'TP.HCM ,24 Văn Hùng', '2019-07-23 6:08:59', 1, 80000, 2, 4,0);
INSERT INTO `foot`.`bill` (`iduser`, `phonenumber`, `place`, `datetime`, `status`, `cost`, `count`, `idproduct`,online) VALUES (58, '09546441199', 'TP.HCM ,24 Văn Hùng', '2019-07-23 6:08:59', 1, 80000, 2, 4,0);
INSERT INTO `foot`.`bill` (`iduser`, `phonenumber`, `place`, `datetime`, `status`, `cost`, `count`, `idproduct`,online) VALUES (58, '09546441199', 'TP.HCM ,24 Văn Hùng', '2019-07-23 6:08:59', 0, 400000, 2, 2,0);
INSERT INTO `foot`.`bill` (`iduser`, `phonenumber`, `place`, `datetime`, `status`, `cost`, `count`, `idproduct`,online) VALUES (54, '09546441200', 'TP.HCM ,24 Văn Tùng', '2019-07-23 11:08:59', 0, 160000, 2, 5,0);
INSERT INTO `foot`.`bill` (`iduser`, `phonenumber`, `place`, `datetime`, `status`, `cost`, `count`, `idproduct`,online) VALUES (15, '0954442122', 'Nam Định ,24 Văn phong', '2019-07-23 11:08:59', 0, 400000, 2, 2,0);
INSERT INTO `foot`.`bill` (`iduser`, `phonenumber`, `place`, `datetime`, `status`, `cost`, `count`, `idproduct`,online) VALUES (4, '091111111', 'Hà Nội', '2019-07-23 11:07:40', 0, 100000, 2, 1,0);
INSERT INTO `foot`.`bill` (`iduser`, `phonenumber`, `place`, `datetime`, `status`, `cost`, `count`, `idproduct`,online) VALUES (46, '032333333', 'Nam Định ,24 Văn phong', '2019-09-23 12:08:59', 0, 400000, 2, 2,0);
INSERT INTO `foot`.`bill` (`iduser`, `phonenumber`, `place`, `datetime`, `status`, `cost`, `count`, `idproduct`,online) VALUES (54, '09511214443', 'Nam Hà ,24 Văn phong', '2019-09-25 7:08:59', 1, 80000, 2, 4,0);
INSERT INTO `foot`.`bill` (`iduser`, `phonenumber`, `place`, `datetime`, `status`, `cost`, `count`, `idproduct`,online) VALUES (71, '02132343349', 'TP.HCM ,24 Văn Hùng', '2019-09-23 6:08:59', 1, 80000, 2, 4,0);
INSERT INTO `foot`.`bill` (`iduser`, `phonenumber`, `place`, `datetime`, `status`, `cost`, `count`, `idproduct`,online) VALUES (72, '8434234234', 'TP.HCM ,24 Văn Hùng', '2019-09-30 6:08:59', 1, 80000, 2, 4,0);
INSERT INTO `foot`.`bill` (`iduser`, `phonenumber`, `place`, `datetime`, `status`, `cost`, `count`, `idproduct`,online) VALUES (73, '8423424112867', 'TP.HCM ,24 Toàn Hùng', '2019-09-28 6:08:59', 0, 400000, 2, 2,0);
INSERT INTO `foot`.`bill` (`iduser`, `phonenumber`, `place`, `datetime`, `status`, `cost`, `count`, `idproduct`,online) VALUES (74, '0987676546', 'Hà Nội ,25 Văn Cao', '2019-09-30 11:08:59', 0, 160000, 2, 5,0);



/*khuyến mãi sale*/
INSERT INTO `foot`.`sale` (`name`, `timestart`, `timeend`, `Detail`, `image`) VALUES ('Khuyến Mãi combo coca', '2019-07-27 00:00:00', '2019-07-31 00:00:00', 'Nhận dịp hè và nhà tài trợ coca-cola việt nam cùng đồng hành thực hiện chương trình khuyến mãi vô cùng lớn', '/sale/cach-uop-thit-bo.jpg');
INSERT INTO `foot`.`sale` (`name`, `timestart`, `timeend`, `Detail`, `image`) VALUES ('Khuyến mãi trà sữa phúc long', '2019-07-27 00:00:00', '2019-07-31 00:00:00', 'Nhận dịp hè và nhà tài trợa chat-time việt nam cùng đồng hành thực hiện chương trình khuyến mãi vô cùng lớn', '/sale/cach-uop-thit-bo.jpg');
INSERT INTO `foot`.`sale` (`name`, `timestart`, `timeend`, `Detail`, `image`) VALUES ('Giảm giá với thức ăn nhanh ', '2019-07-27 00:00:00', '2019-07-31 00:00:00', 'thúc đẩy phát triển mua sắm của khách hành cửa hàng giảm giá đồ ăn nhanh để tri ân khách hàng', '/sale/cach-uop-thit-bo.jpg');
INSERT INTO `foot`.`sale` (`name`, `timestart`, `timeend`, `Detail`, `image`) VALUES ('Mua hàng được phiếu giảm giá coca trên toàn quốc', '2019-07-27 00:00:00', '2019-07-31 00:00:00', 'Nhận dịp hè và nhà tài trợ coca-cola việt nam cùng đồng hành thực hiện chương trình khuyến mãi vô cùng lớn', '/sale/cach-uop-thit-bo.jpg');
INSERT INTO `foot`.`sale` (`name`, `timestart`, `timeend`, `Detail`, `image`) VALUES ('khuyến mãi giao đồ ăn ', '2019-07-27 00:00:00', '2019-07-31 00:00:00', 'Nhận dịp hè và nhà tài trợ coca-cola việt nam cùng đồng hành thực hiện chương trình khuyến mãi vô cùng lớn', '/sale/cach-uop-thit-bo.jpg');

/* insert user
*/
INSERT INTO `foot`.`user` (`idrole`, `email`, `namefull`, `place`, `sex`, `cardid`, `phonenumber`, `check`)
VALUES (2, ' hungNhung112131@gmail.com ',  'Tuan Nhung2', '1234 Lạc Long Quân ', 0, null, '0912321212 ', 1);
INSERT INTO `foot`.`user` (`idrole`, `email`, `namefull`, `place`, `sex`, `cardid`, `phonenumber`, `check`)
VALUES (2, ' hungNhung112123@gmail.com ',  'Tuan Nhung3', '1239 Lạc Long Quân ', 0, null, '0912321112 ', 1);
INSERT INTO `foot`.`user` (`idrole`, `email`, `namefull`, `place`, `sex`, `cardid`, `phonenumber`, `check`)
VALUES (2, ' hungNhung113221@gmail.com ',  'Tuan Nhung4', '123 Lạc Long Quân ', 0, null, '0912397766 ', 1);
INSERT INTO `foot`.`user` (`idrole`, `email`, `namefull`, `place`, `sex`, `cardid`, `phonenumber`, `check`)
VALUES (2, ' hungNhung11981@gmail.com ',  'Tuan Nhung5', '1211 Lạc Long Quân ', 0, null, '091209707 ', 1);
INSERT INTO `foot`.`user` (`idrole`, `email`, `namefull`, `place`, `sex`, `cardid`, `phonenumber`, `check`)
VALUES (2, ' hungNhung1121217@gmail.com ',  'Tuan Nhung6', '12 Lạc Long Quân ', 0, null, '0912305586 ', 1);
INSERT INTO `foot`.`user` (`idrole`, `email`, `namefull`, `place`, `sex`, `cardid`, `phonenumber`, `check`)
VALUES (2, ' hungNhung112129@gmail.com ',  'Tuan Nhung7', '1212 Lạc Long Quân ', 0, null, '0912038656 ', 1);
INSERT INTO `foot`.`user` (`idrole`, `email`, `namefull`, `place`, `sex`, `cardid`, `phonenumber`, `check`)
VALUES (2, ' hungNhung112016@gmail.com ',  'Tuan Nhung8', '1241 Lạc Long Quân ', 0, null, '0912392721 ', 1);


/*feed back
*/

INSERT INTO `foot`.`feedback` (`iduser`, `feedbackcontent`, `datetime`, `check`) VALUES (4, 'abcedf', '2019-08-12 11:24:14', 1);
INSERT INTO `foot`.`feedback` (`iduser`, `feedbackcontent`, `datetime`, `check`) VALUES (15, 'rewrwer', '2019-08-12 11:24:47', 1);
INSERT INTO `foot`.`feedback` (`iduser`, `feedbackcontent`, `datetime`, `check`) VALUES (55, 'rưerwerw', '2019-08-12 11:24:52', 0);
INSERT INTO `foot`.`feedback` (`iduser`, `feedbackcontent`, `datetime`, `check`) VALUES (4, 'ff', '2019-08-12 12:32:28', 0);
INSERT INTO `foot`.`feedback` (`iduser`, `feedbackcontent`, `datetime`, `check`) VALUES (15, 'ffffffffffffffff', '2019-08-12 12:32:30', 0);
INSERT INTO `foot`.`feedback` (`iduser`, `feedbackcontent`, `datetime`, `check`) VALUES (46, 'ffffffffffffdddddđ', '2019-08-12 12:32:29', 0);
INSERT INTO `foot`.`feedback` (`iduser`, `feedbackcontent`, `datetime`, `check`) VALUES (55, 'ffffffffffffffffffffffd', '2019-08-12 12:32:31', 0);
INSERT INTO `foot`.`feedback` (`iduser`, `feedbackcontent`, `datetime`, `check`) VALUES (59, 'fsfsdfsdfsdfsd', '2019-08-12 12:32:32', 0);
INSERT INTO `foot`.`feedback` (`iduser`, `feedbackcontent`, `datetime`, `check`) VALUES (60, 'ffsdbccvbcv', '2019-08-12 12:32:33', 0);
INSERT INTO `foot`.`feedback` (`iduser`, `feedbackcontent`, `datetime`, `check`) VALUES (61, '32rewfsdfsdfsd', '2019-08-12 12:32:33', 0);
INSERT INTO `foot`.`feedback` (`iduser`, `feedbackcontent`, `datetime`, `check`) VALUES (64, 'hgdgdsfsdfsdfss', '2019-08-12 12:32:38', 0);


/*comment table*/

INSERT INTO `foot`.`comment` (`iduser`, `idproduct`, `rate`, `datetime`, `content`) VALUES (3, 1, 5, '2019-08-18 12:29:42', 'fgdfgfdg'); INSERT INTO `foot`.`comment` (`iduser`, `idproduct`, `rate`, `datetime`, `content`) VALUES (4, 2, 4, '2019-08-18 12:30:53', 'sdfsdfdsd')
INSERT INTO `foot`.`comment` (`iduser`, `idproduct`, `rate`, `datetime`, `content`) VALUES (15, 3, 5, '2019-08-18 12:30:54', 'dsdsa');
 INSERT INTO `foot`.`comment` (`iduser`, `idproduct`, `rate`, `datetime`, `content`) VALUES (46, 4, 4, '2019-08-18 12:30:55', 'sds');
INSERT INTO `foot`.`comment` (`iduser`, `idproduct`, `rate`, `datetime`, `content`) VALUES (54, 1, 3, '2019-08-18 12:30:56', 'sds');
 INSERT INTO `foot`.`comment` (`iduser`, `idproduct`, `rate`, `datetime`, `content`) VALUES (55, 1, 4, '2019-08-18 12:30:57', 'dsd');
 INSERT INTO `foot`.`comment` (`iduser`, `idproduct`, `rate`, `datetime`, `content`) VALUES (58, 1, 5, '2019-08-18 12:30:58', 'dsdsdsd');
 INSERT INTO `foot`.`comment` (`iduser`, `idproduct`, `rate`, `datetime`, `content`) VALUES (73, 1, 4, '2019-08-22 02:49:40', 'Vì lý do nào đó bạn không muốn để lộ địa chỉ IP của máy bạn mỗi khi bạn truy cập vào Internet.
');
INSERT INTO `foot`.`comment` (`iduser`, `idproduct`, `rate`, `datetime`, `content`) VALUES (74, 1, 4, '2019-08-22 02:49:41', 'Vì lý do nào đó bạn không muốn để lộ địa chỉ IP của máy bạn mỗi khi bạn truy cập vào Internet.
');
INSERT INTO `foot`.`comment` (`iduser`, `idproduct`, `rate`, `datetime`, `content`) VALUES (75, 1, 5, '2019-08-22 02:49:44', 'Vì lý do nào đó bạn không muốn để lộ địa chỉ IP của máy bạn mỗi khi bạn truy cập vào Internet.
');
INSERT INTO `foot`.`comment` (`iduser`, `idproduct`, `rate`, `datetime`, `content`) VALUES (76, 1, 5, '2019-08-22 02:49:44', 'Vì lý do nào đó bạn không muốn để lộ địa chỉ IP của máy bạn mỗi khi bạn truy cập vào Internet.
');
INSERT INTO `foot`.`comment` (`iduser`, `idproduct`, `rate`, `datetime`, `content`) VALUES (77, 1, 5, '2019-08-22 02:49:46', 'Vì lý do nào đó bạn không muốn để lộ địa chỉ IP của máy bạn mỗi khi bạn truy cập vào Internet.
');
INSERT INTO `foot`.`comment` (`iduser`, `idproduct`, `rate`, `datetime`, `content`) VALUES (78, 1, 5, '2019-08-22 02:49:47', 'Vì lý do nào đó bạn không muốn để lộ địa chỉ IP của máy bạn mỗi khi bạn truy cập vào Internet.
');
INSERT INTO `foot`.`comment` (`iduser`, `idproduct`, `rate`, `datetime`, `content`) VALUES (79, 1, 4, '2019-08-22 02:49:48', 'Vì lý do nào đó bạn không muốn để lộ địa chỉ IP của máy bạn mỗi khi bạn truy cập vào Internet.
');
 INSERT INTO `foot`.`comment` (`iduser`, `idproduct`, `rate`, `datetime`, `content`) VALUES (null, 1, 4, '2019-08-22 02:49:49', 'Vì lý do nào đó bạn không muốn để lộ địa chỉ IP của máy bạn mỗi khi bạn truy cập vào Internet.
');
UPDATE `foot`.`comment` t SET t.`content` = 'Vì lý do nào đó bạn không muốn để lộ địa chỉ IP của máy bạn mỗi khi bạn truy cập vào Internet.
' WHERE t.`idcomment` = 14;
 UPDATE `foot`.`comment` t SET t.`content` = 'Trên internet hiện có rất nhiều Proxy Server, khi đăng nhập server này, nó sẽ hỗ trợ ta truy cập tới website khác internet mà không bị Firewall(Tường lửa) chặn lại. Ngoài ra, nó che giấu các IP thực của máy client(Máy chúng ta), nơi truy cập chỉ biết được IP của Proxy Server. Các hacker rất ưa chuộng sử dụng để che dấu vết.' WHERE t.`idcomment` = 9;
 UPDATE `foot`.`comment` t SET t.`content` = 'Đơn giản là 1 loại proxy xài tốt hơn http proxy vì nó hỗ trợ tốt hơn và an toàn hơn.được dùng chủ yếu trong công nghệ Proxy server và Firewall.' WHERE t.`idcomment` = 10
UPDATE `foot`.`comment` t SET t.`content` = 'Vì lý do nào đó bạn không muốn để lộ địa chỉ IP của máy bạn mỗi khi bạn truy cập vào Internet.' WHERE t.`idcomment` = 11;
 UPDATE `foot`.`comment` t SET t.`content` = 'Giúp nhiều máy truy cập Internet thông qua 1 máy, mà máy này gọi là Proxy. Chỉ duy nhất máy Proxy này cần modem và account truy cập internet, các máy client muốn truy cập internet qua máy này chỉ cần nối mạng LAN tới máy Proxy và truy cập địa chỉ yêu cầu' WHERE t.`idcomment` = 8;
UPDATE `foot`.`comment` t SET t.`content` = 'Vì lý do nào đó bạn không muốn để lộ địa chỉ IP của máy bạn mỗi khi bạn truy cập vào Internet.' WHERE t.`idcomment` = 12;
UPDATE `foot`.`comment` t SET t.`content` = 'Vì lý do nào đó bạn không muốn để lộ địa chỉ IP của máy bạn mỗi khi bạn truy cập vào Internet.' WHERE t.`idcomment` = 13;
INSERT INTO `foot`.`comment` (`iduser`, `idproduct`, `rate`, `datetime`, `content`) VALUES (71, 1, 4, '2019-08-22 02:49:36', 'Vì lý do nào đó bạn không muốn để lộ địa chỉ IP của máy bạn mỗi khi bạn truy cập vào Internet.
');
INSERT INTO `foot`.`comment` (`iduser`, `idproduct`, `rate`, `datetime`, `content`) VALUES (72, 1, 5, '2019-08-22 02:49:39', 'Vì lý do nào đó bạn không muốn để lộ địa chỉ IP của máy bạn mỗi khi bạn truy cập vào Internet.
');
INSERT INTO `foot`.`comment` (`iduser`, `idproduct`, `rate`, `datetime`, `content`) VALUES (73, 1, 4, '2019-08-22 02:49:40', 'Vì lý do nào đó bạn không muốn để lộ địa chỉ IP của máy bạn mỗi khi bạn truy cập vào Internet.
');
INSERT INTO `foot`.`comment` (`iduser`, `idproduct`, `rate`, `datetime`, `content`) VALUES (74, 1, 4, '2019-08-22 02:49:41', 'Vì lý do nào đó bạn không muốn để lộ địa chỉ IP của máy bạn mỗi khi bạn truy cập vào Internet.
');
INSERT INTO `foot`.`comment` (`iduser`, `idproduct`, `rate`, `datetime`, `content`) VALUES (75, 1, 5, '2019-08-22 02:49:44', 'Vì lý do nào đó bạn không muốn để lộ địa chỉ IP của máy bạn mỗi khi bạn truy cập vào Internet.
');
INSERT INTO `foot`.`comment` (`iduser`, `idproduct`, `rate`, `datetime`, `content`) VALUES (76, 1, 5, '2019-08-22 02:49:44', 'Vì lý do nào đó bạn không muốn để lộ địa chỉ IP của máy bạn mỗi khi bạn truy cập vào Internet.
');
INSERT INTO `foot`.`comment` (`iduser`, `idproduct`, `rate`, `datetime`, `content`) VALUES (77, 1, 5, '2019-08-22 02:49:46', 'Vì lý do nào đó bạn không muốn để lộ địa chỉ IP của máy bạn mỗi khi bạn truy cập vào Internet.
');
INSERT INTO `foot`.`comment` (`iduser`, `idproduct`, `rate`, `datetime`, `content`) VALUES (78, 1, 5, '2019-08-22 02:49:47', 'Vì lý do nào đó bạn không muốn để lộ địa chỉ IP của máy bạn mỗi khi bạn truy cập vào Internet.
');
INSERT INTO `foot`.`comment` (`iduser`, `idproduct`, `rate`, `datetime`, `content`) VALUES (79, 1, 4, '2019-08-22 02:49:48', 'Vì lý do nào đó bạn không muốn để lộ địa chỉ IP của máy bạn mỗi khi bạn truy cập vào Internet.
');

INSERT INTO `foot`.`comment` (`iduser`, `idproduct`, `rate`, `datetime`, `content`) VALUES (82, 1, 4, '2019-08-22 02:49:49', 'Vì lý do nào đó bạn không muốn để lộ địa chỉ IP của máy bạn mỗi khi bạn truy cập vào Internet.
');


/*inster transporter*/
INSERT INTO `foot`.`transporter` (`iduser`, `idbill`, `datetime`, `status`, `prioritize`) VALUES (58, 1, '2019-08-31 04:45:00', 0, 1)
INSERT INTO `foot`.`transporter` (`iduser`, `idbill`, `datetime`, `status`, `prioritize`) VALUES (58, 2, '2019-08-31 04:46:04', 0, 2)
INSERT INTO `foot`.`transporter` (`iduser`, `idbill`, `datetime`, `status`, `prioritize`) VALUES (58, 6, '2019-08-31 04:46:03', 0, 3)