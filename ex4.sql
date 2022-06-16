-- MySQL dump 10.13  Distrib 8.0.18, for macos10.14 (x86_64)
--
-- Host: 127.0.0.1    Database: ex4
-- ------------------------------------------------------
-- Server version	8.0.18

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
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `id` bigint(20) NOT NULL,
  `book_name` varchar(255) DEFAULT NULL,
  `discount` double NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (8,'asdadsasdasdasd ',0.01,'images/default-image.jpg',3.01,1),(9,'The Last Thing He Told Me: A Novel',20,'https://m.media-amazon.com/images/I/81823bTjKHL._AC_UY218_.jpg',70,0),(10,'Love and Other Words',5,'https://m.media-amazon.com/images/I/41qlslqAh-L._AC_UY218_.jpg',90,84),(12,'Project Hail Mary',10,'https://m.media-amazon.com/images/I/91vS2L5YfEL._AC_UY218_.jpg',20,40),(13,'The Love Hypothesis',5,'https://m.media-amazon.com/images/I/81kYj5Uyu8L._AC_UY218_.jpg',200,24),(15,'Untamed',1,'https://m.media-amazon.com/images/I/81hy1dggLcL._AC_UY218_.jpg',180,4),(16,'Principles: Life and Work',40,'https://m.media-amazon.com/images/I/81OlHz-7yPL._AC_UY218_.jpg',1000,29),(17,'asdasd',0,'https://islandpress.org/sites/default/files/default_book_cover_2015.jpg',1,1),(18,'asdasdasd',0,'https://islandpress.org/sites/default/files/default_book_cover_2015.jpg',1.03,0),(30,'Where the Crawdads Sing',5,'https://images-na.ssl-images-amazon.com/images/I/61m1Vxw8tiL._AC_UL254_SR254,254_.jpg',199,40),(31,'The Seven Husbands of Evelyn Hugo: A Novel',7,'https://images-na.ssl-images-amazon.com/images/I/71nCY2F3UKL._AC_UL127_SR127,127_.jpg',250,25),(32,'Horse: A Novel',5,'https://images-na.ssl-images-amazon.com/images/I/81Kc9t8XGWL._AC_UL210_SR195,210_.jpg',80,15),(35,'Book Lovers',2,'https://images-na.ssl-images-amazon.com/images/I/71gDtm1U0FL._AC_UL127_SR127,127_.jpg',44,33),(37,'The Very Hungry Caterpillar',1,'https://images-na.ssl-images-amazon.com/images/I/91cqEsyjd-L._AC_UL210_SR195,210_.jpg',100,4);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (19);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `id` bigint(20) NOT NULL,
  `amount` double NOT NULL,
  `payment_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (1,1341.5,'2022-06-16 16:38:39.889949'),(2,656,'2022-06-16 16:40:00.502991'),(7,18,'2022-06-16 17:27:22.558244');
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-16 21:28:18
