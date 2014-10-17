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
  `number` int(11) NOT NULL,
  `client_ID` int(11) NOT NULL,
  `tariff_ID` int(11) NOT NULL,
  `isBlocked` binary(1) NOT NULL,
  `blockedEmp_Id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_CONTRACTS_UNIQUE` (`id`),
  UNIQUE KEY `number_CONTRACTS_UNIQUE` (`number`),
  KEY `userID_CONTRACTS` (`client_ID`),
  KEY `tariffID_CONTRACTS` (`tariff_ID`),
  KEY `blockedEmpID_CONTRACTS` (`blockedEmp_Id`),
  KEY `client_ID` (`client_ID`),
  KEY `tariff_ID` (`tariff_ID`),
  KEY `blockedEmp_ID` (`blockedEmp_Id`),
  CONSTRAINT `blockedEmp_ID` FOREIGN KEY (`blockedEmp_Id`) REFERENCES `USERS` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `client_ID` FOREIGN KEY (`client_ID`) REFERENCES `USERS` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tariff_ID` FOREIGN KEY (`tariff_ID`) REFERENCES `TARIFFS` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CONTRACTS`
--

LOCK TABLES `CONTRACTS` WRITE;
/*!40000 ALTER TABLE `CONTRACTS` DISABLE KEYS */;
/*!40000 ALTER TABLE `CONTRACTS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CONTRACT_OPTION`
--

DROP TABLE IF EXISTS `CONTRACT_OPTION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CONTRACT_OPTION` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contract_ID` int(11) NOT NULL,
  `option_ID` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_CONTRACT_OPTION_UNIQUE` (`id`),
  KEY `contractID_CONTRACT_OPTION` (`contract_ID`),
  KEY `optionID_CONTRACT_OPTION` (`option_ID`),
  KEY `contract_ID` (`contract_ID`),
  KEY `option_ID` (`option_ID`),
  CONSTRAINT `contract_ID` FOREIGN KEY (`contract_ID`) REFERENCES `CONTRACTS` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `option_ID` FOREIGN KEY (`option_ID`) REFERENCES `OPTIONS` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CONTRACT_OPTION`
--

LOCK TABLES `CONTRACT_OPTION` WRITE;
/*!40000 ALTER TABLE `CONTRACT_OPTION` DISABLE KEYS */;
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
  `name` varchar(45) NOT NULL,
  `price` int(11) DEFAULT NULL,
  `initial_Price` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_OPTIONS_UNIQUE` (`id`),
  UNIQUE KEY `name_OPTIONS_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OPTIONS`
--

LOCK TABLES `OPTIONS` WRITE;
/*!40000 ALTER TABLE `OPTIONS` DISABLE KEYS */;
INSERT INTO `OPTIONS` VALUES (1,'20 МБ\\/сек скорость интернета',500,NULL),(2,'40 МБ\\/сек скорость интернета',1000,NULL),(3,'Мобильный интернет',100,50);
/*!40000 ALTER TABLE `OPTIONS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OPTIONS_INCOMPATIBLE`
--

DROP TABLE IF EXISTS `OPTIONS_INCOMPATIBLE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OPTIONS_INCOMPATIBLE` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `optionOne_ID` int(11) NOT NULL,
  `optionTwo_ID` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_OPTIONS_INCOMPATIBLE_UNIQUE` (`id`),
  KEY `optionOneID_OPTIONS_INCOMPATIBLE` (`optionOne_ID`),
  KEY `optionOne_ID` (`optionOne_ID`),
  KEY `optionTwo_ID` (`optionTwo_ID`),
  CONSTRAINT `optionOne_ID` FOREIGN KEY (`optionOne_ID`) REFERENCES `OPTIONS` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `optionTwo_ID` FOREIGN KEY (`optionTwo_ID`) REFERENCES `OPTIONS` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OPTIONS_INCOMPATIBLE`
--

LOCK TABLES `OPTIONS_INCOMPATIBLE` WRITE;
/*!40000 ALTER TABLE `OPTIONS_INCOMPATIBLE` DISABLE KEYS */;
/*!40000 ALTER TABLE `OPTIONS_INCOMPATIBLE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OPTIONS_TOGETHER`
--

DROP TABLE IF EXISTS `OPTIONS_TOGETHER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OPTIONS_TOGETHER` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `optionOne_ID` int(11) NOT NULL,
  `optionTwo_ID` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_OPTIONS_TOGETHER_UNIQUE` (`id`),
  KEY `optionOneID_OPTIONS_TOGETHER` (`optionOne_ID`),
  KEY `optionOne` (`optionOne_ID`),
  KEY `optionTwo` (`optionTwo_ID`),
  CONSTRAINT `optionOne` FOREIGN KEY (`optionOne_ID`) REFERENCES `OPTIONS` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `optionTwo` FOREIGN KEY (`optionTwo_ID`) REFERENCES `OPTIONS` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OPTIONS_TOGETHER`
--

LOCK TABLES `OPTIONS_TOGETHER` WRITE;
/*!40000 ALTER TABLE `OPTIONS_TOGETHER` DISABLE KEYS */;
/*!40000 ALTER TABLE `OPTIONS_TOGETHER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ROLES`
--

DROP TABLE IF EXISTS `ROLES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ROLES` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_ROLES_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ROLES`
--

LOCK TABLES `ROLES` WRITE;
/*!40000 ALTER TABLE `ROLES` DISABLE KEYS */;
INSERT INTO `ROLES` VALUES (1,'Customer'),(2,'Employee');
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
  `name` varchar(45) NOT NULL,
  `price` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_TARIFFS_UNIQUE` (`id`),
  UNIQUE KEY `name_TARIFFS_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TARIFFS`
--

LOCK TABLES `TARIFFS` WRITE;
/*!40000 ALTER TABLE `TARIFFS` DISABLE KEYS */;
/*!40000 ALTER TABLE `TARIFFS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TARIFF_RULES`
--

DROP TABLE IF EXISTS `TARIFF_RULES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TARIFF_RULES` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tariff_ID` int(11) NOT NULL,
  `option_ID` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_TARIFF_RULES_UNIQUE` (`id`),
  KEY `idTariff_TARIFF_RULES` (`tariff_ID`),
  KEY `idPossibleOption_TARIFF_RULES` (`option_ID`),
  KEY `tariffID` (`tariff_ID`),
  KEY `optionID` (`option_ID`),
  CONSTRAINT `optionID` FOREIGN KEY (`option_ID`) REFERENCES `OPTIONS` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tariffID` FOREIGN KEY (`tariff_ID`) REFERENCES `TARIFFS` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TARIFF_RULES`
--

LOCK TABLES `TARIFF_RULES` WRITE;
/*!40000 ALTER TABLE `TARIFF_RULES` DISABLE KEYS */;
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
  `name` varchar(45) CHARACTER SET latin1 NOT NULL,
  `surname` varchar(45) CHARACTER SET latin1 NOT NULL,
  `birthday` date DEFAULT NULL,
  `passport` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `address` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `email` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `login` varchar(45) CHARACTER SET latin1 NOT NULL,
  `password` varchar(16) CHARACTER SET latin1 NOT NULL,
  `role` int(11) NOT NULL,
  PRIMARY KEY (`id`,`login`),
  UNIQUE KEY `id_USERS_UNIQUE` (`id`),
  UNIQUE KEY `login_USERS_UNIQUE` (`login`),
  KEY `role_USERS` (`role`),
  KEY `role` (`role`),
  CONSTRAINT `role` FOREIGN KEY (`role`) REFERENCES `ROLES` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USERS`
--

LOCK TABLES `USERS` WRITE;
/*!40000 ALTER TABLE `USERS` DISABLE KEYS */;
INSERT INTO `USERS` VALUES (1,'German','Urikh','1992-04-14','40 05 961539','SPB','herman.urikh@aengel.ru','germanurikh','38de278aeff53d95',2);
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

-- Dump completed on 2014-10-17 15:22:47
