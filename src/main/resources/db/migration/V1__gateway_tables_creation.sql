-- gateways.gateway definition

CREATE TABLE `gateway` (
  `gateway_id` int NOT NULL AUTO_INCREMENT,
  `ipv4` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `serial_number` varchar(255) NOT NULL,
  PRIMARY KEY (`gateway_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
