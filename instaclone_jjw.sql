-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: instaclonedb
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `board`
--

DROP TABLE IF EXISTS `board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `board` (
  `idx` int NOT NULL AUTO_INCREMENT,
  `contents` text NOT NULL,
  `created_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `member_idx` int NOT NULL,
  PRIMARY KEY (`idx`),
  KEY `fk_board_member_idx` (`member_idx`),
  CONSTRAINT `fk_board_member` FOREIGN KEY (`member_idx`) REFERENCES `member` (`idx`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board`
--

LOCK TABLES `board` WRITE;
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` VALUES (16,'테스트중입니다','2022-01-19 12:49:02',2),(17,'testststragajklsfd','2022-01-19 14:07:08',2),(18,'여러 파일 업로드 테스트','2022-01-19 14:07:57',2),(21,'로봇','2022-01-21 11:42:43',13),(22,'여러 사진','2022-01-21 11:42:57',13),(23,'05게시물','2022-01-21 14:50:41',5),(24,'05게시물 2','2022-01-21 14:51:11',5),(25,'테스트','2022-01-22 10:03:13',2),(26,'레몬','2022-01-22 10:05:36',6),(27,'조류','2022-01-22 10:05:57',6),(28,'7 퍼핀','2022-01-22 10:44:58',7),(29,'해바라기','2022-01-22 10:45:44',7),(31,'테스트중','2022-01-25 14:01:13',8),(32,'ㅁㅁㅁ','2022-01-25 14:01:53',8),(33,'ㅁㄴㄴ','2022-01-25 14:02:05',8),(34,'ㅋㅋ','2022-01-25 14:02:22',8),(35,'나무','2022-01-25 14:03:17',5),(36,'테스트','2022-01-25 14:31:20',2);
/*!40000 ALTER TABLE `board` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
  `idx` int NOT NULL AUTO_INCREMENT,
  `comments_text` text NOT NULL,
  `created_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `member_idx` int NOT NULL,
  `board_idx` int NOT NULL,
  PRIMARY KEY (`idx`),
  KEY `fk_comments_member1_idx` (`member_idx`),
  KEY `fk_comments_board1_idx` (`board_idx`),
  CONSTRAINT `fk_comments_board1` FOREIGN KEY (`board_idx`) REFERENCES `board` (`idx`) ON DELETE CASCADE,
  CONSTRAINT `fk_comments_member1` FOREIGN KEY (`member_idx`) REFERENCES `member` (`idx`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (12,'1234','2022-01-24 11:38:14',2,25),(13,'1234','2022-01-24 11:40:00',2,25),(14,'1234','2022-01-24 11:40:45',2,25),(15,'댓글1','2022-01-24 12:10:54',2,25),(16,'댓글2','2022-01-24 12:52:26',2,24),(17,'댓글2','2022-01-24 13:08:22',2,25),(18,'댓글3','2022-01-24 13:23:52',2,25),(19,'댓글3','2022-01-24 16:31:10',2,24),(20,'1111','2022-01-24 16:42:10',2,24),(21,'1111','2022-01-24 16:44:21',2,24),(22,'댓글 테스트','2022-01-24 16:44:47',2,25),(23,'테스트','2022-01-24 16:45:04',2,25),(24,'111','2022-01-24 16:46:14',2,25),(25,'12341234','2022-01-24 17:03:57',2,27),(26,'안녀하세요','2022-01-24 17:04:55',2,29),(27,'테스트중','2022-01-25 14:01:25',8,31),(28,'테스트~','2022-01-25 14:01:31',8,31),(29,'ㅁㅁㅁ','2022-01-25 16:58:59',6,36),(30,'ㄴㄴㄴ','2022-01-25 17:07:13',6,36),(31,'안녕하세요','2022-01-25 17:35:12',6,35),(32,'안녕하세요','2022-01-25 17:35:37',6,35),(33,'안녕하세요','2022-01-25 17:36:57',2,35),(34,'안녕하세요','2022-01-25 17:57:53',2,36),(35,'안녕하세요22','2022-01-25 18:20:32',2,36),(36,'aaa','2022-01-25 18:26:04',2,36),(37,'bbb','2022-01-25 18:27:07',2,36),(38,'헬로우','2022-01-25 18:31:28',2,35);
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `file`
--

DROP TABLE IF EXISTS `file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `file` (
  `file_idx` int NOT NULL AUTO_INCREMENT,
  `original_file_name` varchar(255) NOT NULL,
  `stored_file_path` varchar(500) NOT NULL,
  `file_size` int NOT NULL,
  `created_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `board_idx` int NOT NULL,
  `member_idx` int NOT NULL,
  PRIMARY KEY (`file_idx`),
  KEY `fk_file_board1_idx` (`board_idx`),
  KEY `fk_file_member1_idx` (`member_idx`),
  CONSTRAINT `fk_file_board1` FOREIGN KEY (`board_idx`) REFERENCES `board` (`idx`) ON DELETE CASCADE,
  CONSTRAINT `fk_file_member1` FOREIGN KEY (`member_idx`) REFERENCES `member` (`idx`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file`
--

LOCK TABLES `file` WRITE;
/*!40000 ALTER TABLE `file` DISABLE KEYS */;
INSERT INTO `file` VALUES (4,'pic1.jpg','images/20220119/479489923106600.jpg',34987,'2022-01-19 12:49:02',16,2),(5,'robot.jpeg','images/20220119/479489923871600.jpg',8474,'2022-01-19 12:49:02',16,2),(6,'shampoo.jpg','images/20220119/479489924405000.jpg',49028,'2022-01-19 12:49:02',16,2),(7,'sky2.jpg','images/20220119/479489924867400.jpg',25898,'2022-01-19 12:49:02',16,2),(8,'w3c.jpg','images/20220119/479489925288200.jpg',37685,'2022-01-19 12:49:02',16,2),(9,'강아지.jpeg','images/20220119/479489925791000.jpg',80719,'2022-01-19 12:49:02',16,2),(10,'고양이.jpg','images/20220119/479489926361600.jpg',572400,'2022-01-19 12:49:02',16,2),(11,'pic1.jpg','images/20220119/484176669896200.jpg',34987,'2022-01-19 14:07:08',17,2),(12,'shampoo.jpg','images/20220119/484224839720400.jpg',49028,'2022-01-19 14:07:57',18,2),(13,'sky2.jpg','images/20220119/484224842558500.jpg',25898,'2022-01-19 14:07:57',18,2),(14,'w3c.jpg','images/20220119/484224843208300.jpg',37685,'2022-01-19 14:07:57',18,2),(19,'robot.jpeg','images/20220121/648314298569100.jpg',8474,'2022-01-21 11:42:43',21,13),(20,'pic1.jpg','images/20220121/648327708457400.jpg',34987,'2022-01-21 11:42:57',22,13),(21,'robot.jpeg','images/20220121/648327709566800.jpg',8474,'2022-01-21 11:42:57',22,13),(22,'shampoo.jpg','images/20220121/648327710459100.jpg',49028,'2022-01-21 11:42:57',22,13),(23,'sky2.jpg','images/20220121/648327712830800.jpg',25898,'2022-01-21 11:42:57',22,13),(24,'w3c.jpg','images/20220121/648327713457000.jpg',37685,'2022-01-21 11:42:57',22,13),(25,'고양이.jpg','images/20220121/648327713956600.jpg',572400,'2022-01-21 11:42:57',22,13),(26,'robot.jpeg','images/20220121/659591469376600.jpg',8474,'2022-01-21 14:50:41',23,5),(27,'shampoo.jpg','images/20220121/659591470937700.jpg',49028,'2022-01-21 14:50:41',23,5),(28,'sky2.jpg','images/20220121/659591475286200.jpg',25898,'2022-01-21 14:50:41',23,5),(29,'sky2.jpg','images/20220121/659621849922700.jpg',25898,'2022-01-21 14:51:11',24,5),(30,'w3c.jpg','images/20220121/659621850655700.jpg',37685,'2022-01-21 14:51:11',24,5),(31,'animal-4534480_1920.jpg','images/20220122/728747234479500.jpg',340408,'2022-01-22 10:03:13',25,2),(32,'bear-1903101_1920.jpg','images/20220122/728747235735200.jpg',724211,'2022-01-22 10:03:13',25,2),(33,'bird-6930700_1920.jpg','images/20220122/728747236191000.jpg',472563,'2022-01-22 10:03:13',25,2),(34,'lemons-6950278_1920.jpg','images/20220122/728889520450100.jpg',485569,'2022-01-22 10:05:36',26,6),(35,'mountain-3807667_1920.jpg','images/20220122/728889520968700.jpg',527087,'2022-01-22 10:05:36',26,6),(36,'bird-6930700_1920.jpg','images/20220122/728910626312900.jpg',472563,'2022-01-22 10:05:57',27,6),(37,'border-collie-6391794_1920.jpg','images/20220122/728910626933400.jpg',304754,'2022-01-22 10:05:57',27,6),(38,'puffins-3567175_1920.jpg','images/20220122/731251523526000.jpg',281826,'2022-01-22 10:44:58',28,7),(39,'sauerland-6787215_1920.jpg','images/20220122/731251524575700.jpg',751959,'2022-01-22 10:44:58',28,7),(40,'sunflowers-6677724_1920.jpg','images/20220122/731297476978200.jpg',413471,'2022-01-22 10:45:44',29,7),(41,'whitby-abbey-1596666_1920.jpg','images/20220122/731297477692400.jpg',1123061,'2022-01-22 10:45:44',29,7),(43,'whitby-abbey-1596666_1920.jpg','images/20220125/1002232897382700.jpg',1123061,'2022-01-25 14:01:13',31,8),(44,'white-wolf-1903107_1920.jpg','images/20220125/1002232898276600.jpg',398346,'2022-01-25 14:01:13',31,8),(45,'bear-1903101_1920.jpg','images/20220125/1002272775158100.jpg',724211,'2022-01-25 14:01:53',32,8),(46,'bird-6930700_1920.jpg','images/20220125/1002272775877100.jpg',472563,'2022-01-25 14:01:53',32,8),(47,'animal-4534480_1920.jpg','images/20220125/1002284755632900.jpg',340408,'2022-01-25 14:02:05',33,8),(48,'bear-1903101_1920.jpg','images/20220125/1002284760331300.jpg',724211,'2022-01-25 14:02:05',33,8),(49,'border-collie-6391794_1920.jpg','images/20220125/1002301849845400.jpg',304754,'2022-01-25 14:02:22',34,8),(50,'lemons-6950278_1920.jpg','images/20220125/1002301850334800.jpg',485569,'2022-01-25 14:02:22',34,8),(51,'sauerland-6787215_1920.jpg','images/20220125/1002356831003200.jpg',751959,'2022-01-25 14:03:17',35,5),(52,'town-6947538_1920.jpg','images/20220125/1002356831626300.jpg',675671,'2022-01-25 14:03:17',35,5),(53,'w3c.jpg','images/20220125/1002356843800500.jpg',37685,'2022-01-25 14:03:17',35,5),(54,'animal-4534480_1920.jpg','images/20220125/1004039760710400.jpg',340408,'2022-01-25 14:31:20',36,2),(55,'bear-1903101_1920.jpg','images/20220125/1004039767063100.jpg',724211,'2022-01-25 14:31:20',36,2);
/*!40000 ALTER TABLE `file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `follow`
--

DROP TABLE IF EXISTS `follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `follow` (
  `idx` int NOT NULL AUTO_INCREMENT,
  `follow_idx` int NOT NULL,
  `followed_idx` int NOT NULL,
  PRIMARY KEY (`idx`),
  KEY `fk_follow_member2_idx` (`idx`),
  KEY `fk_follow_member1_idx` (`follow_idx`,`followed_idx`),
  KEY `fk_follow_member2_idx1` (`followed_idx`),
  CONSTRAINT `fk_follow_member1` FOREIGN KEY (`follow_idx`) REFERENCES `member` (`idx`) ON DELETE CASCADE,
  CONSTRAINT `fk_follow_member2` FOREIGN KEY (`followed_idx`) REFERENCES `member` (`idx`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow`
--

LOCK TABLES `follow` WRITE;
/*!40000 ALTER TABLE `follow` DISABLE KEYS */;
INSERT INTO `follow` VALUES (25,2,2),(29,5,2),(26,5,5),(61,6,2),(62,6,5),(27,6,6),(65,6,7),(56,7,2),(34,7,6),(32,7,7),(43,7,8),(52,7,9),(55,7,11),(58,7,13),(35,8,8),(36,9,9),(37,10,10),(38,11,11),(39,12,12),(28,13,13),(24,14,14),(64,16,5),(63,16,16);
/*!40000 ALTER TABLE `follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `likes`
--

DROP TABLE IF EXISTS `likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `likes` (
  `idx` int NOT NULL AUTO_INCREMENT,
  `board_idx` int NOT NULL,
  `member_idx` int NOT NULL,
  PRIMARY KEY (`idx`),
  KEY `fk_likes_board1_idx` (`idx`),
  KEY `fk_likes_member1_idx` (`member_idx`),
  KEY `fk_likes_board1_idx1` (`board_idx`),
  CONSTRAINT `fk_likes_board1` FOREIGN KEY (`board_idx`) REFERENCES `board` (`idx`) ON DELETE CASCADE,
  CONSTRAINT `fk_likes_member1` FOREIGN KEY (`member_idx`) REFERENCES `member` (`idx`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `likes`
--

LOCK TABLES `likes` WRITE;
/*!40000 ALTER TABLE `likes` DISABLE KEYS */;
INSERT INTO `likes` VALUES (8,25,2),(10,25,6),(11,27,6),(12,26,6),(13,35,6),(15,24,6),(56,36,6),(57,24,2),(58,23,2),(67,36,2);
/*!40000 ALTER TABLE `likes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `idx` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `original_name` varchar(45) NOT NULL,
  `pwd` varchar(45) NOT NULL,
  `email` varchar(100) NOT NULL,
  `website` varchar(45) DEFAULT NULL,
  `website_contents` text,
  `phone` varchar(45) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idx`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (2,'test02','test22','1234','test02@email.com','','','',''),(5,'test05','test','1234','test05@email.com',NULL,NULL,NULL,NULL),(6,'test06','testst','1234','test06@email.com',NULL,NULL,NULL,NULL),(7,'test07','testst','1234','test07@email.com',NULL,NULL,NULL,NULL),(8,'test08','testst','1234','test08@email.com',NULL,NULL,NULL,NULL),(9,'test09','testst','1234','test09@email.com',NULL,NULL,NULL,NULL),(10,'test10','tests','1234','test10@email.com',NULL,NULL,NULL,NULL),(11,'테스터','test','1234','test01@email.com',NULL,NULL,NULL,NULL),(12,'test12','abcd','1234','test12@email.com',NULL,NULL,NULL,NULL),(13,'test11','테스터','1234','test11@email.com','','','',''),(14,'test중','테스터','1234','followtest@email.com',NULL,NULL,NULL,NULL),(16,'ttesstt','aaaa','1234','test13@email.com',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profile`
--

DROP TABLE IF EXISTS `profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profile` (
  `idx` int NOT NULL AUTO_INCREMENT,
  `member_idx` int NOT NULL,
  `original_file_name` varchar(255) NOT NULL,
  `stored_file_path` varchar(500) NOT NULL,
  `file_size` int NOT NULL,
  `created_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idx`),
  KEY `fk_profile_member_idx` (`member_idx`),
  CONSTRAINT `fk_profile_member` FOREIGN KEY (`member_idx`) REFERENCES `member` (`idx`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profile`
--

LOCK TABLES `profile` WRITE;
/*!40000 ALTER TABLE `profile` DISABLE KEYS */;
INSERT INTO `profile` VALUES (5,2,'고양이.jpg','images/20220119/493184348580500.jpg',572400,'2022-01-19 16:37:16'),(6,2,'pic1.jpg','images/20220119/493190230552400.jpg',34987,'2022-01-19 16:37:22'),(7,2,'robot.jpeg','images/20220119/493223409639800.jpg',8474,'2022-01-19 16:37:55'),(8,2,'고양이.jpg','images/20220120/550720978600400.jpg',572400,'2022-01-20 08:36:10'),(9,2,'pic1.jpg','images/20220120/550731521771300.jpg',34987,'2022-01-20 08:36:20'),(10,2,'고양이.jpg','images/20220120/556135233423800.jpg',572400,'2022-01-20 10:06:24'),(11,2,'pic1.jpg','images/20220120/569753578112200.jpg',34987,'2022-01-20 13:53:22'),(13,2,'고양이.jpg','images/20220121/642477516818400.jpg',572400,'2022-01-21 10:05:27'),(14,2,'robot.jpeg','images/20220121/647814292377400.jpg',8474,'2022-01-21 11:34:23'),(15,13,'고양이.jpg','images/20220121/648651037872800.jpg',572400,'2022-01-21 11:48:20'),(16,6,'bird-6930700_1920.jpg','images/20220122/728926580235000.jpg',472563,'2022-01-22 10:06:13'),(17,7,'puffins-3567175_1920.jpg','images/20220122/731235290549600.jpg',281826,'2022-01-22 10:44:41'),(18,8,'강아지.jpeg','images/20220125/1002217499443800.jpg',80719,'2022-01-25 14:00:58'),(19,5,'lemons-6950278_1920.jpg','images/20220125/1002343839742500.jpg',485569,'2022-01-25 14:03:04'),(20,2,'bear-1903101_1920.jpg','images/20220125/1005111058488600.jpg',724211,'2022-01-25 14:49:11');
/*!40000 ALTER TABLE `profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'instaclonedb'
--

--
-- Dumping routines for database 'instaclonedb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-25 18:38:44
