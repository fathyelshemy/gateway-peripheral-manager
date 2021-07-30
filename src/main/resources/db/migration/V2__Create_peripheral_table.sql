CREATE TABLE `peripheral` (
  `uid` int NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `vendor` varchar(255) DEFAULT NULL,
  `gateway_id` int DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
