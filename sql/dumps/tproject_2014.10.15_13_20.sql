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
  `id_CONTRACTS` int(11) NOT NULL AUTO_INCREMENT,
  `number_CONTRACTS` int(11) NOT NULL,
  `clientID_CONTRACTS` int(11) NOT NULL,
  `tariffID_CONTRACTS` int(11) NOT NULL,
  `isBlocked_CONTRACTS` binary(1) NOT NULL,
  `isBlockedByEmp_CONTRACTS` binary(1) NOT NULL,
  `blockedEmpId_CONTRACTS` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_CONTRACTS`),
  UNIQUE KEY `id_CONTRACTS_UNIQUE` (`id_CONTRACTS`),
  UNIQUE KEY `number_CONTRACTS_UNIQUE` (`number_CONTRACTS`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
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
  `id_CONTRACT_OPTION` int(11) NOT NULL AUTO_INCREMENT,
  `contractID_CONTRACT_OPTION` int(11) NOT NULL,
  `optionID_CONTRACT_OPTION` int(11) NOT NULL,
  PRIMARY KEY (`id_CONTRACT_OPTION`),
  UNIQUE KEY `id_CONTRACT_OPTION_UNIQUE` (`id_CONTRACT_OPTION`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
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
  `id_OPTIONS` int(11) NOT NULL AUTO_INCREMENT,
  `name_OPTIONS` varchar(45) NOT NULL,
  `price_OPTIONS` int(11) DEFAULT NULL,
  `initialPrice_OPTIONS` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_OPTIONS`),
  UNIQUE KEY `id_OPTIONS_UNIQUE` (`id_OPTIONS`),
  UNIQUE KEY `name_OPTIONS_UNIQUE` (`name_OPTIONS`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OPTIONS`
--

LOCK TABLES `OPTIONS` WRITE;
/*!40000 ALTER TABLE `OPTIONS` DISABLE KEYS */;
INSERT INTO `OPTIONS` VALUES (2,'40 МБ\\/сек скорость интернета',1000,NULL),(3,'Мобильный интернет',100,50),(1,'20 МБ\\/сек скорость интернета',500,NULL);
/*!40000 ALTER TABLE `OPTIONS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OPTIONS_INCOMPATIBLE`
--

DROP TABLE IF EXISTS `OPTIONS_INCOMPATIBLE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OPTIONS_INCOMPATIBLE` (
  `id_OPTIONS_INCOMPATIBLE` int(11) NOT NULL AUTO_INCREMENT,
  `optionOneID_OPTIONS_INCOMPATIBLE` int(11) NOT NULL,
  `optionTwoID_OPTIONS_INCOMPATIBLE` int(11) NOT NULL,
  PRIMARY KEY (`id_OPTIONS_INCOMPATIBLE`),
  UNIQUE KEY `id_OPTIONS_INCOMPATIBLE_UNIQUE` (`id_OPTIONS_INCOMPATIBLE`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
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
  `id_OPTIONS_TOGETHER` int(11) NOT NULL AUTO_INCREMENT,
  `optionOneID_OPTIONS_TOGETHER` int(11) NOT NULL,
  `optionTwoID_OPTIONS_TOGETHER` int(11) NOT NULL,
  PRIMARY KEY (`id_OPTIONS_TOGETHER`),
  UNIQUE KEY `id_OPTIONS_TOGETHER_UNIQUE` (`id_OPTIONS_TOGETHER`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
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
  `id_ROLES` int(11) NOT NULL,
  `name_ROLES` varchar(45) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`id_ROLES`),
  UNIQUE KEY `id_ROLES_UNIQUE` (`id_ROLES`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
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
  `id_TARIFFS` int(11) NOT NULL AUTO_INCREMENT,
  `name_TARIFFS` varchar(45) CHARACTER SET latin1 NOT NULL,
  `price_TARIFFS` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_TARIFFS`),
  UNIQUE KEY `id_TARIFFS_UNIQUE` (`id_TARIFFS`),
  UNIQUE KEY `name_TARIFFS_UNIQUE` (`name_TARIFFS`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
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
  `id_TARIFF_RULES` int(11) NOT NULL AUTO_INCREMENT,
  `idTariff_TARIFF_RULES` int(11) NOT NULL,
  `idOption_TARIFF_RULES` int(11) NOT NULL,
  PRIMARY KEY (`id_TARIFF_RULES`),
  UNIQUE KEY `id_TARIFF_RULES_UNIQUE` (`id_TARIFF_RULES`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
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
  `id_USERS` int(11) NOT NULL AUTO_INCREMENT,
  `name_USERS` varchar(45) CHARACTER SET latin1 NOT NULL,
  `surname_USERS` varchar(45) CHARACTER SET latin1 NOT NULL,
  `birthday_USERS` date DEFAULT NULL,
  `pasport_USERS` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `address_USERS` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `e-mail_USERS` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `login_USERS` varchar(45) CHARACTER SET latin1 NOT NULL,
  `password_USERS` varchar(16) CHARACTER SET latin1 NOT NULL,
  `role_USERS` enum('1','2') CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`id_USERS`,`login_USERS`),
  UNIQUE KEY `id_USERS_UNIQUE` (`id_USERS`),
  UNIQUE KEY `login_USERS_UNIQUE` (`login_USERS`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USERS`
--

LOCK TABLES `USERS` WRITE;
/*!40000 ALTER TABLE `USERS` DISABLE KEYS */;
INSERT INTO `USERS` VALUES (1,'German','Urikh','1992-04-14','40 05 961539','SPB','herman.urikh@aengel.ru','germanurikh','38de278aeff53d95','2');
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

-- Dump completed on 2014-10-15 13:20:06
