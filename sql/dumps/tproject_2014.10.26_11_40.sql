-- MySQL dump 10.13  Distrib 5.5.38, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: tproject
-- ------------------------------------------------------
-- Server version	5.5.38-0ubuntu0.12.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES cp1251 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `CONTRACTS`
--

DROP TABLE IF EXISTS `CONTRACTS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CONTRACTS` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `isBlocked` bit(1) NOT NULL,
  `number` bigint(20) NOT NULL,
  `blockedEmp_id` int(11) DEFAULT NULL,
  `tariff_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_29ixwwkddmsdo40p3cryh3uqd` (`number`),
  KEY `FK_s9ag13s8fdk9qpe1dgg2wd5hg` (`blockedEmp_id`),
  KEY `FK_pg4ahlwhmb4djol48hjnsreup` (`tariff_id`),
  KEY `FK_igsvr2dna4sjv3od8q3syt5pm` (`user_id`),
  CONSTRAINT `FK_igsvr2dna4sjv3od8q3syt5pm` FOREIGN KEY (`user_id`) REFERENCES `USERS` (`id`),
  CONSTRAINT `FK_pg4ahlwhmb4djol48hjnsreup` FOREIGN KEY (`tariff_id`) REFERENCES `TARIFFS` (`id`),
  CONSTRAINT `FK_s9ag13s8fdk9qpe1dgg2wd5hg` FOREIGN KEY (`blockedEmp_id`) REFERENCES `USERS` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CONTRACTS`
--

LOCK TABLES `CONTRACTS` WRITE;
/*!40000 ALTER TABLE `CONTRACTS` DISABLE KEYS */;
INSERT INTO `CONTRACTS` VALUES (2,'\0',9817710204,NULL,7,1),(3,'\0',9512223344,NULL,7,1),(4,'\0',4567894563,NULL,7,2),(5,'\0',4444444444,NULL,7,2);
/*!40000 ALTER TABLE `CONTRACTS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CONTRACT_OPTION`
--

DROP TABLE IF EXISTS `CONTRACT_OPTION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CONTRACT_OPTION` (
  `contract_id` int(11) NOT NULL,
  `option_id` int(11) NOT NULL,
  KEY `FK_eu802eyu4bwvase921pu25dtp` (`option_id`),
  KEY `FK_tfc36ah6607isil0lli9nug25` (`contract_id`),
  CONSTRAINT `FK_eu802eyu4bwvase921pu25dtp` FOREIGN KEY (`option_id`) REFERENCES `OPTIONS` (`id`),
  CONSTRAINT `FK_tfc36ah6607isil0lli9nug25` FOREIGN KEY (`contract_id`) REFERENCES `CONTRACTS` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CONTRACT_OPTION`
--

LOCK TABLES `CONTRACT_OPTION` WRITE;
/*!40000 ALTER TABLE `CONTRACT_OPTION` DISABLE KEYS */;
INSERT INTO `CONTRACT_OPTION` VALUES (2,8),(4,8),(4,9),(5,6),(5,7),(5,8);
/*!40000 ALTER TABLE `CONTRACT_OPTION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OPTIONS`
--

DROP TABLE IF EXISTS `OPTIONS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OPTIONS` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `initialPrice` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OPTIONS`
--

LOCK TABLES `OPTIONS` WRITE;
/*!40000 ALTER TABLE `OPTIONS` DISABLE KEYS */;
INSERT INTO `OPTIONS` VALUES (6,10,'300 &#1057;&#1052;&#1057;',100),(7,20,'600 &#1057;&#1052;&#1057;',150),(8,10,'100 &#1052;&#1073; &#1048;&#1085;&#1090;&#1077;&#1088;&#1085;&#1077;&#1090;',50),(9,0,'3 &#1043;&#1041; &#1048;&#1085;&#1090;&#1077;&#1088;&#1085;&#1077;&#1090;',300),(10,100,'&#1058;&#1086;&#1083;&#1100;&#1082;&#1086; &#1080;&#1085;&#1090;&#1077;&#1088;&#1085;&#1077;&#1090;',500);
/*!40000 ALTER TABLE `OPTIONS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OPTIONS_INCOMPATIBLE`
--

DROP TABLE IF EXISTS `OPTIONS_INCOMPATIBLE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OPTIONS_INCOMPATIBLE` (
  `optionOne_id` int(11) NOT NULL,
  `optionTwo_id` int(11) NOT NULL,
  KEY `FK_mkqsk8px3b5ys5x8chij5frx5` (`optionTwo_id`),
  KEY `FK_fmcnsi80qucy0hw7rfpu4i0ub` (`optionOne_id`),
  CONSTRAINT `FK_fmcnsi80qucy0hw7rfpu4i0ub` FOREIGN KEY (`optionOne_id`) REFERENCES `OPTIONS` (`id`),
  CONSTRAINT `FK_mkqsk8px3b5ys5x8chij5frx5` FOREIGN KEY (`optionTwo_id`) REFERENCES `OPTIONS` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OPTIONS_INCOMPATIBLE`
--

LOCK TABLES `OPTIONS_INCOMPATIBLE` WRITE;
/*!40000 ALTER TABLE `OPTIONS_INCOMPATIBLE` DISABLE KEYS */;
INSERT INTO `OPTIONS_INCOMPATIBLE` VALUES (10,6),(10,7);
/*!40000 ALTER TABLE `OPTIONS_INCOMPATIBLE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OPTIONS_TOGETHER`
--

DROP TABLE IF EXISTS `OPTIONS_TOGETHER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OPTIONS_TOGETHER` (
  `optionOne_id` int(11) NOT NULL,
  `optionTwo_id` int(11) NOT NULL,
  KEY `FK_ji2225653rxx49p3n0y3sm5of` (`optionTwo_id`),
  KEY `FK_pn0nwy61mxhfmatlyuy1alpqq` (`optionOne_id`),
  CONSTRAINT `FK_ji2225653rxx49p3n0y3sm5of` FOREIGN KEY (`optionTwo_id`) REFERENCES `OPTIONS` (`id`),
  CONSTRAINT `FK_pn0nwy61mxhfmatlyuy1alpqq` FOREIGN KEY (`optionOne_id`) REFERENCES `OPTIONS` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OPTIONS_TOGETHER`
--

LOCK TABLES `OPTIONS_TOGETHER` WRITE;
/*!40000 ALTER TABLE `OPTIONS_TOGETHER` DISABLE KEYS */;
INSERT INTO `OPTIONS_TOGETHER` VALUES (10,8),(10,9);
/*!40000 ALTER TABLE `OPTIONS_TOGETHER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ROLES`
--

DROP TABLE IF EXISTS `ROLES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ROLES` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ROLES`
--

LOCK TABLES `ROLES` WRITE;
/*!40000 ALTER TABLE `ROLES` DISABLE KEYS */;
INSERT INTO `ROLES` VALUES (1,'User'),(2,'Employee');
/*!40000 ALTER TABLE `ROLES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TARIFFS`
--

DROP TABLE IF EXISTS `TARIFFS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TARIFFS` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `price` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TARIFFS`
--

LOCK TABLES `TARIFFS` WRITE;
/*!40000 ALTER TABLE `TARIFFS` DISABLE KEYS */;
INSERT INTO `TARIFFS` VALUES (7,'&#1048;&#1085;&#1090;&#1077;&#1088;&#1085;&#1077;&#1090;',400),(8,'SMS',350);
/*!40000 ALTER TABLE `TARIFFS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TARIFF_RULES`
--

DROP TABLE IF EXISTS `TARIFF_RULES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TARIFF_RULES` (
  `tariff_id` int(11) NOT NULL,
  `option_id` int(11) NOT NULL,
  KEY `FK_4y4garjxfs5gk4x8murvkw19r` (`option_id`),
  KEY `FK_ej03enpm76hyyf2vjdeixwe9s` (`tariff_id`),
  CONSTRAINT `FK_4y4garjxfs5gk4x8murvkw19r` FOREIGN KEY (`option_id`) REFERENCES `OPTIONS` (`id`),
  CONSTRAINT `FK_ej03enpm76hyyf2vjdeixwe9s` FOREIGN KEY (`tariff_id`) REFERENCES `TARIFFS` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TARIFF_RULES`
--

LOCK TABLES `TARIFF_RULES` WRITE;
/*!40000 ALTER TABLE `TARIFF_RULES` DISABLE KEYS */;
INSERT INTO `TARIFF_RULES` VALUES (7,8),(7,9),(7,6),(7,7),(7,10),(8,6),(8,7),(8,10);
/*!40000 ALTER TABLE `TARIFF_RULES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USERS`
--

DROP TABLE IF EXISTS `USERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USERS` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `balance` int(11) NOT NULL,
  `birthday` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `login` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `passport` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_e7tg79qhfyp29bvrrb77rs1w7` (`role`),
  CONSTRAINT `FK_e7tg79qhfyp29bvrrb77rs1w7` FOREIGN KEY (`role`) REFERENCES `ROLES` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USERS`
--

LOCK TABLES `USERS` WRITE;
/*!40000 ALTER TABLE `USERS` DISABLE KEYS */;
INSERT INTO `USERS` VALUES (1,'&#1072;&#1076;&#1088;&#1077;&#1089;',5000,'1992-04-14','herman.urikh@aengel.ru','hermanurikh','&#1043;&#1077;&#1088;&#1084;&#1072;&#1085;','&#1087;&#1072;&#1089;&#1087;&#1086;&#1088;&#1090;','1833584d5305bc838a1b8f65cef8a653','&#1059;&#1088;&#1080;&#1093;',2),(2,'&#1072;&#1076;&#1088;&#1077;&#1089;',2500,'2014-10-12','email@lol.ru','alex','&#1040;&#1083;&#1077;&#1082;&#1089;&#1072;&#1085;&#1076;&#1088;','&#1087;&#1072;&#1089;&#1087;&#1086;&#1088;&#1090;','3e47d11719a0a5bd911b29cffa422505','&#1057;&#1072;&#1085;&#1085;&#1080;&#1082;&#1086;&#1074;',1),(3,'&#1072;&#1076;&#1088;&#1077;&#1089;',200,'1999-01-28','log@sdf.ru','arseny','&#1040;&#1088;&#1089;&#1077;&#1085;&#1080;&#1081;','&#1087;&#1072;&#1089;&#1087;&#1086;&#1088;&#1090;','040596d9a6ca43f4aa954f07625fc77e','&#1059;&#1088;&#1080;&#1093;',1);
/*!40000 ALTER TABLE `USERS` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-10-26 11:40:23
