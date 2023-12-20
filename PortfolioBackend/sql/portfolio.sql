-- MySQL dump 10.13  Distrib 8.2.0, for macos14.0 (arm64)
--
-- Host: localhost    Database: portfolio
-- ------------------------------------------------------
-- Server version	8.2.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `occupation`
--

DROP TABLE IF EXISTS `occupation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `occupation` (
  `occupation_id` int NOT NULL AUTO_INCREMENT,
  `occupation_name` text NOT NULL,
  PRIMARY KEY (`occupation_id`)
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `occupation`
--

LOCK TABLES `occupation` WRITE;
/*!40000 ALTER TABLE `occupation` DISABLE KEYS */;
INSERT INTO `occupation` VALUES (1,'Student'),(2,'Developer'),(3,'Other'),(6,'Civil Engineer'),(7,'Civil Engineer'),(8,'Civil Engineer'),(9,'Civil Engineer'),(10,'Civil Engineer'),(11,'Civil Engineer'),(12,'Civil Engineer'),(13,'Civil Engineer'),(14,'Civil Engineer'),(15,'Civil Engineer'),(16,'Civil Engineer'),(17,'Civil Engineer'),(18,'Civil Engineer'),(19,'Civil Engineer'),(20,'Civil Engineer'),(21,'Civil Engineer'),(22,'Civil Engineer'),(23,'Civil Engineer'),(24,'Civil Engineer'),(25,'Civil Engineer'),(26,'Civil Engineer'),(27,'Civil Engineer'),(28,'Civil Engineer'),(29,'Civil Engineer'),(30,'Civil Engineer'),(31,'Civil Engineer'),(32,'Civil Engineer'),(33,'Civil Engineer'),(34,'Civil Engineer'),(35,'Civil Engineer'),(36,'Civil '),(37,'Civil Engineer'),(38,'Civil Engineer'),(39,'Civil Engineer'),(40,'Civil Engineer'),(41,'Civil Engineer'),(42,'Civil Engineer'),(43,'Civil Engineer'),(44,'Civil Engineer'),(45,'Civil Engineer'),(46,'Civil Engineer'),(47,'Civil Engineer'),(48,'Civil Engineer'),(49,'Civil Engineer'),(50,'Civil Engineer'),(51,'Civil Engineer'),(52,'Civil Engineer'),(53,'Civil Engineer'),(54,'Civil Engineer'),(55,'Civil Engineer'),(56,'Civil Engineer'),(57,'Civil Engineer'),(58,'Civil Engineer'),(59,'Civil Engineer'),(60,'Civil Engineer'),(61,'Civil Engineer'),(62,'Civil Engineer'),(63,'cvs'),(64,'Civil Engineer'),(66,'cvs'),(67,'Civil Engineer'),(68,'cvs'),(69,'Civil Engineer'),(70,'cvs'),(71,'Civil Engineer'),(72,'cvs'),(73,'Civil Engineer'),(74,'cvs'),(75,'Civil Engineer'),(76,'cvs'),(77,'Civil Engineer'),(78,'cvs'),(79,'Civil Engineer'),(80,'cvs'),(81,'Civil Engineer'),(82,'cvs'),(83,'Civil Engineer'),(84,'cvs'),(85,'Civil Engineer'),(86,'cvs'),(87,'Civil Engineer'),(88,'cvs'),(89,'Civil Engineer'),(90,'cvs'),(91,'Civil Engineer'),(92,'cvs'),(93,'Civil Engineer'),(94,'cvs'),(95,'Civil Engineer'),(96,'cvs'),(97,'Civil Engineer'),(98,'cvs'),(99,'Civil Engineer'),(100,'cvs'),(101,'Civil Engineer'),(102,'cvs'),(103,'Civil Engineer'),(104,'cvs'),(105,'Civil Engineer'),(106,'Civil Engineer'),(107,'cvs'),(108,'Civil Engineer'),(109,'cvs'),(110,'Civil Engineer'),(111,'cvs'),(112,'Civil Engineer'),(113,'cvs'),(114,'Civil Engineer'),(115,'cvs'),(116,'Civil Engineer');
/*!40000 ALTER TABLE `occupation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `viewer`
--

DROP TABLE IF EXISTS `viewer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `viewer` (
  `viewer_id` int NOT NULL AUTO_INCREMENT,
  `firstname` varchar(100) NOT NULL,
  `lastname` varchar(100) NOT NULL,
  `birthdate` date NOT NULL,
  `gender` varchar(100) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `retriever` varchar(100) NOT NULL,
  `country` varchar(100) NOT NULL,
  `occupation` int DEFAULT NULL,
  PRIMARY KEY (`viewer_id`),
  KEY `fk_viewer_occupation` (`occupation`),
  CONSTRAINT `fk_viewer_occupation` FOREIGN KEY (`occupation`) REFERENCES `occupation` (`occupation_id`)
) ENGINE=InnoDB AUTO_INCREMENT=213 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `viewer`
--

LOCK TABLES `viewer` WRITE;
/*!40000 ALTER TABLE `viewer` DISABLE KEYS */;
INSERT INTO `viewer` VALUES (1,'Nasrin','Jafari','1997-01-21','Female','nasrin.jafari@powercoders.org','Dela9090!','Dela9090!','Switzerland',2),(66,'David','Schneider','1800-01-21','Male','David.Schneider@ubs.com','Daivd123}','Daivd123!','Switzerland',2),(67,'David','Schneider','1800-01-21','Male','David.Schneider@ubs.com','Daivd123}','Daivd123!','Switzerland',2),(69,'David','Schneider','1800-01-21','Male','David.Schneider@ubs.com','Daivd123}','Daivd123!','Switzerland',3),(71,'David','Schneider','1800-01-21','Male','David.Schneider@ubs.com','Daivd123}','Daivd123!','Switzerland',2),(72,'David','Schneider','1800-01-21','Male','David.Schneider@ubs.com','Daivd123}','Daivd123!','Switzerland',2),(73,'Sebastian','Mueller','1997-01-01','Male','david.mueller@ubs.com','1234','1236','Switzerland',1),(74,'Sebastian','Mueller','1997-01-01','Male','nasrin.jafari@ubs.com','1234','1236','Switzerland',1),(75,'David','Schneider','1800-01-21','Male','David.Schneider@ubs.com','Daivd123}','Daivd123!','Switzerland',2),(76,'David','Schneider','1800-01-21','Male','David.Schneider@ubs.com','Daivd123}','Daivd123!','Switzerland',2),(77,'David','Schneider','1800-01-21','Male','David.Schneider@ubs.com','Daivd123}','Daivd123!','Switzerland',2),(104,'Nasrin','Jafari','1997-01-21','Female','nasrin.jafari@powercoders.org','Dela9090!','Dela9090!','Switzerland',2),(105,'Nasrin','Jafari','1997-01-21','Female','nasrin.jafari@powercoders.org','Dela9090!','Dela9090!','Switzerland',2),(106,'Nasrin','Jafari','1997-01-21','Female','nasrin.jafari@powercoders.org','Dela9090!','Dela9090!','Switzerland',2),(129,'Sebastian','Mueller','1997-01-01','Male','davidmueller','1234','1236','Switzerland',2),(130,'Sebastian','Mueller','1997-01-01','Male','david.mueller@ubs.com','Dela9090!','Dela9090!','Switzerland',2),(131,'Sebastian','Mueller','1997-01-01','Male','davidmueller','1234','1236','Switzerland',10),(132,'Sebastian','Mueller','1997-01-01','Male','david.mueller@ubs.com','Dela9090!','Dela9090!','Switzerland',2),(133,'Nasrin','Jafari','1997-01-21','Female','nasrin.jafari@powercod','Dela9090!','Dela9090!','Switzerland',2),(134,'Nasrin','Jafari','1997-01-21','Female','nasrin.jafari@powercoders.org','Dela9090!','Dela9090!','Switzerland',2),(135,'Nasrin','Jafari','1997-01-21','Female','nasrin.jafari@powercoderg','Dela9090!','Dela9090!','Switzerland',2),(136,'Nasrin','Jafari','1997-01-21','Female','nasrin.jafari@powercoderg','Dela9090!','Dela9090!','Switzerland',2),(137,'Nasrin','Jafari','1997-01-21','Female','nasrin.jafari@powercoderg','Dela9090!','Dela9090!','Switzerland',2),(138,'Nasrin','Jafari','1997-01-21','Female','nasrin.jafari@powercoderg','Dela9090!','Dela9090!','Switzerland',2),(139,'Nasrin','Jafari','1997-01-21','Female','nasrin.jafari@powercoderg','Dela9090!','Dela9090!','Switzerland',2),(140,'Nasrin','Jafari','1997-01-21','Female','nasrin.jafari@powercoderg','Dela9090!','Dela9090!','Switzerland',2),(141,'Nasrin','Jafari','1997-01-21','Female','nasrin.jafari@powercoderg','Dela9090!','Dela9090!','Switzerland',2),(142,'Sebastian','Mueller','1997-01-01','Male','davidmueller','1234','1236','Switzerland',10),(143,'Sebastian','Mueller','1997-01-01','Male','david.mueller@ubs.com','Dela9090!','Dela9090!','Switzerland',2),(144,'Nasrin','Jafari','1997-01-21','Female','nasrin.jafari@powercoderg','Dela9090!','Dela9090!','Switzerland',2),(145,'Nasrin','Jafari','1997-01-21','Female','nasrin.jafari','Dela9090!','Dela9090!','Switzerland',2),(146,'Nasrin','Jafari','1997-01-21','Female','nasrin.jafari','Dela9090!','Dela9090!','Switzerland',2),(147,'Nasrin','Jafari','1997-01-21','Female','nasrin.jafari','Dela9090!','Dela9090!','Switzerland',2),(148,'Nasrin','Jafari','1997-01-21','Female','nasrin.jafari','Dela9090!','Dela9090!','Switzerland',2),(149,'Sebastian','Mueller','1997-01-01','Male','david.mueller@ubs.com','Dela9090!','Dela9090!','Switzerland',2),(150,'Sebastian','Mueller','1997-01-01','Male','david.mueller@ubs.com','Dela9090!','Dela9090!','Switzerland',2),(151,'Sebastian','Mueller','1997-01-01','Male','david.mueller@ubs.com','Dela9090!','Dela9090!','Switzerland',2),(152,'Sebastian','Mueller','1997-01-01','Male','david.mueller@ubs.com','Dela9090!','Dela9090!','Switzerland',2),(153,'Sebastian','Mueller','1997-01-01','Male','david.mueller@ubs.com','Dela9090!','Dela9090!','Switzerland',2),(154,'Sebastian','Mueller','1997-01-01','Male','david.mueller@ubs.com','Dela9090!','Dela9090!','Switzerland',2),(155,'Sebastian','Mueller','1997-01-01','Male','david.mueller@ubs.com','Dela9090!','Dela9090!','Switzerland',2),(156,'Sebastian','Mueller','1997-01-01','Male','david.mueller@ubs.com','Dela9090!','Dela9090!','Switzerland',2),(157,'Sebastian','Mueller','1997-01-01','Male','david.mueller@ubs.com','Dela9090!','Dela9090!','Switzerland',2),(158,'Sebastian','Mueller','1997-01-01','Male','david.mueller@ubs.com','Dela9090!','Dela9090!','Switzerland',2),(159,'Sebastian','Mueller','1997-01-01','Male','david.mueller@ubs.com','Dela9090!','Dela9090!','Switzerland',2),(160,'Sebastian','Mueller','1997-01-01','Male','david.mueller@ubs.com','Dela9090!','Dela9090!','Switzerland',2),(161,'Sebastian','Mueller','1997-01-01','Male','david.mueller@ubs.com','Dela9090!','Dela9090!','Switzerland',2),(162,'Sebastian','Mueller','1997-01-01','Male','david.mueller@ubs.com','Dela9090!','Dela9090!','Switzerland',2),(163,'Sebastian','Mueller','1997-01-01','Male','david.mueller@ubs.com','Dela9090!','Dela9090!','Switzerland',2),(164,'Sebastian','Mueller','1997-01-01','Male','david.mueller@ubs.com','Dela9090!','Dela9090!','Switzerland',2),(166,'Nasrin','Jafari','1997-01-21','Female','nasrin.jafari@gmail.com','Dela9090!','Dela9090!','Switzerland',2),(167,'Sebastian','Mueller','1997-01-01','Male','david.mueller@ubs.com','Dela9090!','Dela9090!','Switzerland',2),(168,'Sebastian','Mueller','1997-01-01','Male','david.mueller@ubs.com','Dela9090!','Dela9090!','Switzerland',2),(169,'Sebastian','Mueller','1997-01-01','Male','david.mueller@ubs.com','Dela9090!','Dela9090!','Switzerland',2),(170,'Sebastian','Mueller','1997-01-01','Male','david.mueller@ubs.com','Dela9090!','Dela9090!','Switzerland',2),(171,'Sebastian','Mueller','1997-01-01','Male','david.mueller@ubs.com','Dela9090!','Dela9090!','Switzerland',2),(172,'Sebastian','Mueller','1997-01-01','Male','david.mueller@ubs.com','Dela9090!','Dela9090!','Switzerland',2),(173,'Sebastian','Mueller','1997-01-01','Male','david.mueller@ubs.com','Dela9090!','Dela9090!','Switzerland',2),(174,'Sebastian','Mueller','1997-01-01','Male','david.mueller@ubs.com','Dela9090!','Dela9090!','Switzerland',2),(176,'Nasrin','Jafari','1997-01-21','Female','nasrin.jafari@powercoders.org','Dela9090!','Dela9090!','Switzerland',2),(177,'Sebastian','Mueller','1997-01-01','Male','david.mueller@ubs.com','Dela9090!','Dela9090!','Switzerland',2),(178,'Sebastian','Mueller','1997-01-01','Male','david.mueller@ubs.com','Dela9090!','Dela9090!','Switzerland',2),(180,'Sebastian','Mueller','1997-01-01','Male','david.mueller@ubs.com','Dela9090!','Dela9090!','Switzerland',2),(181,'Sebastian','Mueller','1997-01-01','Male','david.mueller@ubs.com','Dela9090!','Dela9090!','Switzerland',2),(182,'Sebastian','Mueller','1997-01-01','Male','david.mueller@ubs.com','Dela9090!','Dela9090!','Switzerland',2),(183,'Sebastian','Mueller','1997-01-01','Male','david.mueller@ubs.com','Dela9090!','Dela!','Switzerland',2),(184,'Sebastian','Mueller','1997-01-01','Male','david.mueller@ubs.com','Dela9090!','Dela9090!','Switzerland',2),(185,'Sebastian','Mueller','1997-01-01','Male','david.mueller@ubs.com','Dela9090!','1ela!','Switzerland',2),(186,'Sebastian','Mueller','1997-01-01','Male','david.mueller@ubs.com','Dela9090!','Dela9090!','Switzerland',2),(187,'Sebastian','Mueller','1997-01-01','Male','david.mueller@ubs.com','Dela9090!','1ela!','Switzerland',2),(188,'Sebastian','Mueller','1997-01-01','Male','david.mueller@ubs.com','Dela9090!','Dela9090!','Switzerland',2),(189,'Nasrin','Jafari','1997-01-21','Female','nasrin.jafari@powercoders.org','Dela9090!','Dela','Switzerland',2),(190,'Nasrin','Jafari','1997-01-21','Female','nasrin.jafari@powercoders.org','Dela9090!','Dela90','Switzerland',2),(191,'Nasrin','Jafari','1997-01-21','Female','nasrin.jafari@powercoders.org','Dela909089!!','Dela9090!89','Switzerland',2),(192,'Nasrin','Jafari','1997-01-21','Female','nasrin.jafari@powercoders.org','Dela909089!!','Del','Switzerland',2),(193,'Nasrin','Jafari','1997-01-21','Female','nasrin.jafari@powercoders.org','Dela909089!!','Del','Switzerland',2),(194,'Nasrin','Jafari','1997-01-21','Female','nasrin.jafari@powercoders.org','Dela909089!!','Del','Switzerland',2),(195,'Nasrin','Jafari','1997-01-21','Female','nasrin.jafari@powercoders.org','Dela909089!!','Dea9','Switzerland',2),(196,'Nasrin','Jafari','1997-01-21','Female','nasrin.jafari@powercoders.org','Dela909089!!','Dea9090!','Switzerland',2),(197,'Nasrin','Jafari','1997-01-21','Female','nasrin.jafari@powercoders.org','Dela909089!!','Dea9090!vbbn mbn ','Switzerland',2),(198,'Nasrin','Jafari','1997-01-21','Female','nasrin.jafari@powercoders.org','Dela909089!!','De ','Switzerland',2),(199,'Nasrin','Jafari','1997-01-21','Female','nasrin.jafari@powercoders.org','Dela909089!!','De ','Switzerland',2),(200,'Nasrin','Jafari','1997-01-21','Female','nasrin.jafari@powercoders.org','Dela909089!!','De ','Switzerland',2),(201,'Nasrin','Jafari','1997-01-21','Female','nasrin.jafari@powercoders.org','Dela909089!!','De','Switzerland',2),(202,'Sebastian','Mueller','1997-01-01','Male','david.mueller@ubs.com','Dela9090!','Dela9090!','Switzerland',2),(203,'Sebastian','Mueller','1997-01-01','Male','david.mueller@ubs.com','Dela9090!','Dela9090!','Switzerland',2),(204,'Sebastian','Mueller','1997-01-01','Male','david.mueller@ubs.com','Dela9090!','Dela!9090','Switzerland',100),(205,'Sebastian','Mueller','1997-01-01','Male','david.mueller@ubs.com','Dela9090!','Dela9090!','Switzerland',2),(206,'Sebastian','Mueller','1997-01-01','Male','david.mueller@ubs.com','Dela9090!','Dela9090!','Switzerland',2),(207,'Sebastian','Mueller','1997-01-01','Male','david.mueller@ubs.com','Dela9090!','Dela9090!','Switzerland',2),(208,'Sebastian','Mueller','1997-01-01','Male','david.mueller@ubs.com','Dela9090!','Dela9090!','Switzerland',2),(209,'Sebastian','Mueller','1997-01-01','Male','david.mueller@ubs.com','Dela9090!','Dela9090!','Switzerland',2),(210,'Sebastian','Mueller','1997-01-01','Male','david.mueller@ubs.com','Dela9090!','Dela9090!','Switzerland',2),(211,'Sebastian','Mueller','1997-01-01','Male','david.mueller@ubs.com','Dela9090!','Dela9090!','Switzerland',2),(212,'Sebastian','Mueller','1997-01-01','Male','david.mueller@ubs.com','Dela9090!','Dela9090!','Switzerland',2);
/*!40000 ALTER TABLE `viewer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-20 15:33:46
