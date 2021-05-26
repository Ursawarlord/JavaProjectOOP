CREATE TABLE `eticketing`.`audit` (
  `actionname` varchar(45) NOT NULL,
  `time` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`actionname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `eticketing`.`tickets` (
  `numeclient` varchar(45) NOT NULL,
  `denumirelocatie` varchar(45) NOT NULL,
  `dataeveniment` varchar(45) NOT NULL,
  `tipreducere` varchar(45) DEFAULT NULL,
  `discount` int DEFAULT NULL,
  `avemcard` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`numeclient`,`denumirelocatie`,`dataeveniment`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `eticketing`.`cards` (
  `numedetinator` varchar(45) DEFAULT NULL,
  `dataexpirare` varchar(45) DEFAULT NULL,
  `credit` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `eticketing`.`clients` (
  `nume` varchar(45) NOT NULL,
  `varsta` int DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `nrtelefon` varchar(45) DEFAULT NULL,
  `avemcard` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`nume`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `eticketing`.`events` (
  `denumirelocatie` varchar(45) NOT NULL,
  `dataeveniment` varchar(45) DEFAULT NULL,
  `prettichet` int DEFAULT NULL,
  PRIMARY KEY (`denumirelocatie`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `eticketing`.`locations` (
  `denumire` int NOT NULL,
  `numeoras` varchar(45) DEFAULT NULL,
  `nrlocuri` int DEFAULT NULL,
  PRIMARY KEY (`denumire`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



