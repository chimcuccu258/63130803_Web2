drop database LogisticsSystem;
create database LogisticsSystem;
use LogisticsSystem;

CREATE TABLE `Customer` (
  `customerID` bigint PRIMARY KEY AUTO_INCREMENT,
  `fullName` varchar(255),
  `address` varchar(255),
  `phoneNumber` varchar(10),
  `created_at` timestamp DEFAULT (CURRENT_TIMESTAMP),
  `updated_at` timestamp DEFAULT (CURRENT_TIMESTAMP)
);

CREATE TABLE `Supplier` (
  `supplierID` bigint PRIMARY KEY AUTO_INCREMENT,
  `supplierName` varchar(255),
  `supplierStatus` ENUM ('Opening', 'Closed') DEFAULT 'Opening',
  `email` varchar(50),
  `address` varchar(255),
  `phoneNumber` varchar(10),
  `password` varchar(255),
  `created_at` timestamp DEFAULT (CURRENT_TIMESTAMP),
  `updated_at` timestamp DEFAULT (CURRENT_TIMESTAMP)
);

CREATE TABLE `Employee` (	
  `employeeID` bigint PRIMARY KEY AUTO_INCREMENT,
  `fullName` varchar(255),
  `avatar` int,
  `dateOfBirth` date,
  `gender` bool,
  `address` varchar(255),
  `phoneNumber` varchar(15),
  `email` varchar(255),
  `password` varchar(255),
  `created_at` timestamp DEFAULT (CURRENT_TIMESTAMP),
  `updated_at` timestamp DEFAULT (CURRENT_TIMESTAMP)
);

CREATE TABLE `ProductType` (
  `productTypeID` bigint PRIMARY KEY AUTO_INCREMENT,
  `productTypeName` varchar(100),
  `created_at` timestamp DEFAULT (CURRENT_TIMESTAMP),
  `updated_at` timestamp DEFAULT (CURRENT_TIMESTAMP)
);	

CREATE TABLE `Order` (
  `orderID` bigint PRIMARY KEY AUTO_INCREMENT,
  `customerID` bigint,
  `storeID` bigint,
  `employeeID` bigint,
  `origin` varchar(100),
  `destination` varchar(100),
  `startShippingTime` timestamp DEFAULT (CURRENT_TIMESTAMP),
  `estimatedTime` datetime,
  `fee` decimal(10,2),
  `orderStatus` ENUM ('Delivering', 'Delivered', 'Canceled', 'Pending') DEFAULT 'Pending',
  `payingStatus` ENUM ('Paid', 'Unpaid') DEFAULT 'Unpaid',
  `created_at` timestamp DEFAULT (CURRENT_TIMESTAMP),
  `updated_at` timestamp DEFAULT (CURRENT_TIMESTAMP)
);

CREATE TABLE `OrderDetails` (
  `orderDetailID` bigint PRIMARY KEY AUTO_INCREMENT,
  `orderID` bigint,
  `productTypeID` bigint,
  `nameOfProduct` varchar(100),
  `quantityOfProduct` int,
  `priceOfProduct` decimal(10,2),
  `noteOfProduct` varchar(100)
);

ALTER TABLE `Order` ADD FOREIGN KEY (`storeID`) REFERENCES `Supplier` (`supplierID`);

ALTER TABLE `Order` ADD FOREIGN KEY (`employeeID`) REFERENCES `Employee` (`employeeID`);

ALTER TABLE `Order` ADD FOREIGN KEY (`customerID`) REFERENCES `Customer` (`customerID`);

ALTER TABLE `OrderDetails` ADD FOREIGN KEY (`orderID`) REFERENCES `Order` (`orderID`); 

ALTER TABLE `OrderDetails` ADD FOREIGN KEY (`productTypeID`) REFERENCES `ProductType` (`productTypeID`);

INSERT INTO `Supplier` (`supplierName`, `supplierStatus`, `email`, `address`, `phoneNumber`, `password`)
VALUES
('Quán Phở Hà Nội', 'Opening', 'quanphohanoi@dls.su.com', '123 Trần Phú, Nha Trang', '0123456789', '$2a$12$/N2smQSDGQnlEF/O8/W5suZYMZrA34OqMMClzdT/7/eUfwmWAF1EK'), -- pn43U2kW
('Quán Cơm Niêu Sài Gòn', 'Opening', 'quancomsaigon@dls.su.com', '456 Nguyễn Thị Minh Khai, Nha Trang', '0987654321', '$2a$12$fXkKPpydMwY4EazIRu85wuJ3gi5FVXhjas0qO8sCiS6DUIujy3urO'), -- Mpc32kTF
('Quán Bún Bò Huế', 'Opening', 'quanbunbohue@dls.su.com', '789 Lê Hồng Phong, Nha Trang', '0321654987', '$2a$12$lV32PLJqMdhMKyeG9Mg/7e6DYF9p6glAVGudzIf5x.TAwoVuhgj8G'), -- bY58rC2G
('Quán Bánh Mì Hội An', 'Opening', 'quanbanhmihoin@dls.su.com', '456 Trần Quang Khải, Nha Trang', '0976543210', '$2a$12$BpWg.i5otD9NZsymaw.RsOIxTeyny8P/4/oBsB.sz3twUinfzVM7e'), -- rwZ7pDCn
('Quán Mì Quảng Đà Nẵng', 'Opening', 'quanmiquangdn@dls.su.com', '321 Nguyễn Thiện Thuật, Nha Trang', '0347890123', '$2a$12$uq/B7k4ghRbm0f42w9iFsuaklmJ.dIGMhIoy7V9Yxz7vMxNd4/PX6'), -- g6L5A8wN
('Quán Hủ Tiếu Sài Gòn', 'Opening', 'quanhutieusaigon@dls.su.com', '654 Hùng Vương, Nha Trang', '0369874512', '$2a$12$P1oYFQgnv8D0ZrnMrhF93.aBlLqMNV3v/5nXgZXd2DVYE/.rVnIGe'), -- pN5aLv96
('Quán Bún Riêu Cô Hà', 'Opening', 'quanbunrieuha@dls.su.com', '789 Thống Nhất, Nha Trang', '0912345678', '$2a$12$NOkyYHgmCj1SFmk8jYtPbuJGZo8kmHVmdBqWtOHn4hmdH71ukCyF2'), -- DXs5khfM
('Quán Phở Bắc, Hà Nội', 'Opening', 'quanphobac@dls.su.com', '147 Pasteur, Nha Trang', '0987654321', '$2a$12$XnXLau5zuMOLbakatWmEVuabqQkueEtAQiAFH0baXCYvDHHn8rGf.'); -- TRz6h3ua

INSERT INTO `Employee` (`fullName`, `avatar`, `dateOfBirth`, `gender`, `address`, `phoneNumber`, `email`, `password`)
VALUES
('Vũ Minh Nga', null, '2003-08-25', 0, 'Số 2 Nguyễn Đình Chiểu, Nha Trang', '0386506347', 'admin@dls.co.com', '$2a$12$EuYgP0ZfpDTfjPFYvFmpo.4khxoS1Bj3Dva7WFz1ONXbRSHqPWWxa'), -- admin123
('Nguyễn Văn Hoà', null, '1990-05-15', 1, '456 Lê Lợi, TP.HCM', '0923456789', 'hoanv@dls.em.com', '$2a$12$cq7I4/0j/K0iRA4YgZR6aONWwfNOcscNLE84CNnGBgZBZaWuaE0VK'), -- XEa43BLZ
('Trần Thị Thu', null, '1985-10-20', 0, '123 Nguyễn Lương Bằng, Hà Nội', '0987654321', 'thutt@dls.em.com', '$2a$12$jsq6uGeMa0qR6RfBiILEnO32Cy/3byt5BhsRzT61h8QyyrEa/2Q/O'), -- HUZJux8a
('Lê Văn Cường', null, '1995-03-25', 1, '789 Hải Phòng, Hải Phòng', '0365478912', 'cuonglv@dls.em.com', '$2a$12$uELiMWo5p7ad5XX1GKUYn.j7SJd/4g1rfDO6aUjH1eW8ta0vm5WLq'), -- A3azNWK2
('Phạm Thị Dung', null, '1992-07-12', 0, '456 Cửa Đại, Quảng Nam', '0976543210', 'dungpt@dls.em.com', '$2a$12$PYWSWbQAq.rOXUe4ps0kFO1SldGfgg8JEBRQPSscnRPzXpG5Y86FO'), -- XH6uUmS9
('Hoàng Văn Huy', null, '1998-12-30', 1, '321 Lê Duẩn, Đà Nẵng', '0347890123', 'huyhv@dls.em.com', '$2a$12$C3XzV.3/Yl4stNSC99gQW.ctg5S4jlANdqX/XWxK.qv08dqeyChSq'), -- CaVB9Z4u
('Trần Văn Thái', null, '1991-09-18', 1, '654 Nguyễn Tri Phương', '0369874512', 'thaitv@dls.em.com', '$2a$12$CoR.AKBZlEW6hQQeMefyGe3inpoqulkHXLzYrDDPiF83wKlEtIDt6'), -- tCM2xWv5
('Nguyễn Thị Giang', null, '1988-04-07', 0, '789 Trần Phú, Hà Nội', '0912345678', 'giangnt@dls.em.com', '$2a$12$vwm76efbyJhYVT94r1NnpOC.fQBacbsQu7Epqa.jHOz/.ziQFKWmu'), -- n3wYhB9q
('Lê Văn Hùng', null, '1993-11-22', 1, '147 Lê Thanh Nghị, Hà Nội', '0987654321', 'hunglv@dls.em.com', '$2a$12$7ehuJDoPF9O0CGXeC8oPNOGqJnV7mVxNzNxKkA59MVbfIVVt0OgzK'); -- Kt5SMHUw

INSERT INTO `Customer` (`fullName`, `address`, `phoneNumber`)
VALUES
('Lê Thị C', '789 Đường Hoàng Diệu, Đà Nẵng', '0365478912'),
('Phạm Văn D', '321 Trần Phú, Hải Phòng', '0987123456'),
('Nguyễn Thị E', '456 Hai Bà Trưng, Hà Nội', '0123456789'),
('Trần Văn F', '147 Nguyễn Văn Cừ, TP.HCM', '0987654321'),
('Lê Thị G', '258 Lý Thường Kiệt, Quảng Nam', '0365478912'),
('Phạm Văn H', '369 Lê Lợi, Hải Phòng', '0987123456'),
('Nguyễn Thị I', '456 Lê Duẩn, Đà Nẵng', '0123456789'),
('Trần Văn K', '789 Lê Thanh Nghị, TP.HCM', '0987654321');

INSERT INTO `ProductType` (`productTypeName`) 
VALUES
('Food'),
('Beverage');

INSERT INTO `Order` (`customerID`, `storeID`, `employeeID`, `origin`, `destination`, `startShippingTime`, `estimatedTime`, `fee`, `orderStatus`, `payingStatus`)
VALUES
(1, 1, 1, '123 Nguyễn Lương Bằng, Hà Nội', '789 Đường Hoàng Diệu, Đà Nẵng', '2024-05-07 09:30:00', DATE_ADD('2024-05-07 09:30:00', INTERVAL 15 + FLOOR(RAND() * 16) MINUTE), 160000, 'Delivered', 'Paid'),
(2, 2, 2, '456 Lê Lợi, TP.HCM', '321 Trần Phú, Hải Phòng', '2024-05-07 11:00:00', DATE_ADD('2024-05-07 11:00:00', INTERVAL 15 + FLOOR(RAND() * 16) MINUTE), 210000, 'Delivered', 'Paid'),
(3, 3, 3, '789 Hải Phòng, Hải Phòng', '456 Hai Bà Trưng, Hà Nội', '2024-05-07 10:45:00', DATE_ADD('2024-05-07 10:45:00', INTERVAL 15 + FLOOR(RAND() * 16) MINUTE), 190000, 'Delivered', 'Paid'),
(4, 4, 4, '456 Cửa Đại, Quảng Nam', '147 Nguyễn Văn Cừ, TP.HCM', '2024-05-07 11:30:00', DATE_ADD('2024-05-07 11:30:00', INTERVAL 15 + FLOOR(RAND() * 16) MINUTE), 230000, 'Delivered', 'Paid'),
(5, 5, 5, '321 Lê Duẩn, Đà Nẵng', '258 Lý Thường Kiệt, Quảng Nam', '2024-05-07 12:15:00', DATE_ADD('2024-05-07 12:15:00', INTERVAL 15 + FLOOR(RAND() * 16) MINUTE), 200000, 'Delivered', 'Paid'),
(6, 6, 6, '654 Nguyễn Tri Phương, TP.HCM', '369 Lê Lợi, Hải Phòng', '2024-05-07 09:00:00', DATE_ADD('2024-05-07 09:00:00', INTERVAL 15 + FLOOR(RAND() * 16) MINUTE), 220000, 'Delivered', 'Paid'),
(7, 7, 7, '789 Trần Phú, Hà Nội', '456 Lê Duẩn, Đà Nẵng', '2024-05-07 10:30:00', DATE_ADD('2024-05-07 10:30:00', INTERVAL 15 + FLOOR(RAND() * 16) MINUTE), 180000, 'Delivered', 'Paid'),
(8, 8, 8, '147 Lê Thanh Nghị, Hà Nội', '789 Lê Thanh Nghị, TP.HCM', '2024-05-07 12:45:00', DATE_ADD('2024-05-07 12:45:00', INTERVAL 15 + FLOOR(RAND() * 16) MINUTE), 240000, 'Delivered', 'Paid');

INSERT INTO `OrderDetails` (`orderID`, `productTypeID`, `nameOfProduct`, `quantityOfProduct`, `priceOfProduct`, `noteOfProduct`)
VALUES
(1, 1, 'Phở Gà', 2, 50000, 'Extra noodles'),
(1, 1, 'Chè Thái', 1, 25000, NULL),
(2, 1, 'Cơm Niêu Sài Gòn', 1, 80000, 'Extra vegetables'),
(2, 2, 'Trà Đào', 2, 20000, 'Less sugar'),
(3, 1, 'Bún Bò Huế', 1, 70000, 'Spicy'),
(3, 1, 'Bánh Mì Gà', 2, 30000, NULL),
(4, 1, 'Mì Quảng', 1, 60000, 'With shrimp'),
(4, 1, 'Hủ Tiếu Nam Vang', 1, 90000, 'Extra meat'),
(5, 1, 'Bún Riêu', 2, 55000, 'Extra crab'),
(5, 1, 'Phở Bắc', 1, 65000, 'With beef balls'),
(6, 1, 'Gỏi Cuốn', 1, 40000, NULL),
(6, 1, 'Cơm Tấm', 2, 35000, 'Grilled pork chop'),
(7, 1, 'Bún Riêu', 1, 55000, 'Extra tofu'),
(7, 1, 'Phở Bắc', 2, 65000, 'With rare beef'),
(8, 1, 'Bánh Mì Chảo', 1, 30000, NULL),
(8, 1, 'Mì Quảng', 2, 60000, 'Extra herbs');

