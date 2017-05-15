CREATE DATABASE  IF NOT EXISTS `user_data` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `user_data`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: user_data
-- ------------------------------------------------------
-- Server version	5.7.16-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `Prod_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Post_User_Id` varchar(25) NOT NULL,
  `Prod_Name` varchar(25) NOT NULL,
  `P_Price` varchar(25) NOT NULL,
  `P_Description` varchar(150) NOT NULL,
  `P_Category` varchar(20) NOT NULL,
  `P_Quality` varchar(10) NOT NULL,
  `P_Address_Line1` varchar(35) DEFAULT NULL,
  `P_Address_Line2` varchar(35) DEFAULT NULL,
  `P_City` char(20) NOT NULL,
  `P_State` char(20) NOT NULL,
  `P_Country` varchar(20) NOT NULL,
  PRIMARY KEY (`Prod_Id`,`Prod_Name`),
  KEY `fk_product1` (`Post_User_Id`),
  CONSTRAINT `fk_product1` FOREIGN KEY (`Post_User_Id`) REFERENCES `users` (`Username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'amit','lenovo','45','brand new','laptop','old','east','turnpike','United States','Texas','Dickson'),(2,'amit','iphone','23','cool','mobile','new','east','turnpike','United States','Trimble','Dayton'),(3,'amit','galaxytab','35','brand new','mobile','new/old','mccalum','west','United States','Twickenham','Twickenham');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sale`
--

DROP TABLE IF EXISTS `sale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sale` (
  `Sale_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Sale_Username` varchar(25) NOT NULL,
  `Prod_Id` int(11) NOT NULL,
  PRIMARY KEY (`Sale_Id`),
  KEY `fk_sale1` (`Sale_Username`),
  KEY `fk_sale2` (`Prod_Id`),
  CONSTRAINT `fk_sale1` FOREIGN KEY (`Sale_Username`) REFERENCES `users` (`Username`),
  CONSTRAINT `fk_sale2` FOREIGN KEY (`Prod_Id`) REFERENCES `product` (`Prod_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sale`
--

LOCK TABLES `sale` WRITE;
/*!40000 ALTER TABLE `sale` DISABLE KEYS */;
/*!40000 ALTER TABLE `sale` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `Username` varchar(25) NOT NULL,
  `U_First_Name` varchar(25) NOT NULL,
  `U_Last_Name` varchar(25) NOT NULL,
  `Pass` char(32) NOT NULL,
  `Email_Id` varchar(35) NOT NULL,
  `Birth_date` date NOT NULL,
  `Gender` char(9) NOT NULL,
  `City` char(20) NOT NULL,
  `State` char(20) NOT NULL,
  `Country` varchar(20) NOT NULL,
  `Ph_No` varchar(10) DEFAULT NULL,
  `Address_Line1` varchar(35) DEFAULT NULL,
  `Address_Line2` varchar(35) DEFAULT NULL,
  `Last_login` datetime DEFAULT NULL,
  `No_failed_login` int(11) NOT NULL,
  `Last_login_location` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`Username`,`Email_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('amit','Amitabh','Bachan','amit2007','mastraghav@gmail.com','2016-11-25','male','Cookeville','Texas','United States','9803354444','Turnpike','#210','2016-11-30 10:31:18',12,'Dallas'),('deep','amita','Mathur','1910','deepanverma55@gmal.com','1996-10-25','male','Dallas','Texas','United States','9891230945','777','abc mcclam','2016-11-29 22:34:43',0,'Dallas'),('mastraghav','Raghav','Mathur','saubhagya','mastraghav@outlook.com','1993-05-05','male','Dallas','Texas','United States','9803357973','mccallum blvd','apt 142','2016-11-29 22:24:59',0,'Dallas');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `bid`
--

DROP TABLE IF EXISTS `bid`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bid` (
  `Bid_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Prod_Id` int(11) NOT NULL,
  `Bidder_Id` varchar(25) NOT NULL,
  `Post_User_Id` varchar(25) NOT NULL,
  `Exp_Description` varchar(150) NOT NULL,
  `Exp_Quality` varchar(10) NOT NULL,
  `Exp_Price` varchar(25) NOT NULL,
  `Act_Description` varchar(150) NOT NULL,
  `Act_Quality` varchar(10) NOT NULL,
  `Act_Price` varchar(25) NOT NULL,
  `Prod_Name` varchar(25) NOT NULL,
  `Post_Email` varchar(35) NOT NULL,
  `Bidder_Email` varchar(35) NOT NULL,
  PRIMARY KEY (`Bid_Id`),
  KEY `fk_bid1` (`Prod_Id`,`Prod_Name`),
  KEY `fk_bid2` (`Bidder_Id`,`Bidder_Email`),
  KEY `fk_bid3` (`Post_User_Id`,`Post_Email`),
  CONSTRAINT `fk_bid1` FOREIGN KEY (`Prod_Id`,`Prod_Name`) REFERENCES `product`(`Prod_Id`,`Prod_Name`),
  CONSTRAINT `fk_bid2` FOREIGN KEY (`Bidder_Id`,`Bidder_Email`) REFERENCES `users` (`Username`,`Email_Id`),
  CONSTRAINT `fk_bid3` FOREIGN KEY (`Post_User_Id`,`Post_Email`) REFERENCES `users` (`Username`,`Email_Id`)
  
  
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bid`
--

LOCK TABLES `bid` WRITE;
/*!40000 ALTER TABLE `bid` DISABLE KEYS */;
/*INSERT INTO `bid` VALUES (3,1,'mastraghav','amit','brand ','old','45','good condition','new','32'),(4,1,'deep','amit','brand ','old','45','awesome','new','50'),(5,1,'mastraghav','amit','brand','old','45','good','new','32'),(6,1,'mastraghav','amit','brand','old','45','good','new','32'),(7,1,'deep','amit','brand','old','45','awesome','new','50'),(8,1,'deep','amit','brand','old','45','awesome','new','50'),(9,1,'mastraghav','amit','brand','old','45','good','new','32'),(10,1,'mastraghav','amit','brand','old','45','good','new','32'),(11,1,'mastraghav','amit','brand','old','45','good','new','32'),(12,1,'deep','amit','brand','old','45','awesome','new','50');
/*!40000 ALTER TABLE `bid` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `shoppingcart`
--

DROP TABLE IF EXISTS `shoppingcart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shoppingcart` (
  `Cart_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Bid_Id` int(11) NOT NULL,
  `Prod_Id` int(11) NOT NULL,
  `Prod_Name` varchar(25) NOT NULL,
  `Bidder_Id` varchar(25) NOT NULL,
  `Post_User_Id` varchar(25) NOT NULL,
  `Exp_Description` varchar(150) NOT NULL,
  `Exp_Quality` varchar(10) NOT NULL,
  `Exp_Price` varchar(25) NOT NULL,
  `Act_Description` varchar(150) NOT NULL,
  `Act_Quality` varchar(10) NOT NULL,
  `Act_Price` varchar(25) NOT NULL,
  `Item_Count` int(11) DEFAULT NULL,
  `Bidder_Email` varchar(35) NOT NULL,
  `Post_Email` varchar(35) NOT NULL,
  PRIMARY KEY (`Cart_Id`),
  KEY `fk_cart1` (`Prod_Id`,`Prod_Name`),
  KEY `fk_cart2` (`Bidder_Id`,`Bidder_Email`),
  KEY `fk_cart3` (`Post_User_Id`,`Post_Email`),
  CONSTRAINT `fk_cart1` FOREIGN KEY (`Prod_Id`,`Prod_Name`) REFERENCES `product` (`Prod_Id`,`Prod_Name`),
  CONSTRAINT `fk_cart2` FOREIGN KEY (`Bidder_Id`,`Bidder_Email`) REFERENCES `users` (`Username`,`Email_Id`),
  CONSTRAINT `fk_cart3` FOREIGN KEY (`Post_User_Id`,`Post_Email`) REFERENCES `users` (`Username`,`Email_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shoppingcart`
--

LOCK TABLES `shoppingcart` WRITE;
/*!40000 ALTER TABLE `shoppingcart` DISABLE KEYS */;
/*!40000 ALTER TABLE `shoppingcart` ENABLE KEYS */;
UNLOCK TABLES;


DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `Order_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Prod_Id` int(11) NOT NULL,
  `Prod_Name` varchar(25) NOT NULL,
  `Bidder_Id` varchar(25) NOT NULL,
  `Post_User_Id` varchar(25) NOT NULL,
  `Act_Price` varchar(25) NOT NULL,
  `Item_Count` int(11) DEFAULT NULL,
  `Bidder_Email` varchar(35) NOT NULL,
  `Post_Email` varchar(35) NOT NULL,
  PRIMARY KEY (`Order_Id`),
  KEY `fk_order1` (`Prod_Id`,`Prod_Name`),
  KEY `fk_order2` (`Bidder_Id`,`Bidder_Email`),
  KEY `fk_order3` (`Post_User_Id`,`Post_Email`),
  CONSTRAINT `fk_order1` FOREIGN KEY (`Prod_Id`,`Prod_Name`) REFERENCES `product` (`Prod_Id`,`Prod_Name`),
  CONSTRAINT `fk_order2` FOREIGN KEY (`Bidder_Id`,`Bidder_Email`) REFERENCES `users` (`Username`,`Email_Id`),
  CONSTRAINT `fk_order3` FOREIGN KEY (`Post_User_Id`,`Post_Email`) REFERENCES `users` (`Username`,`Email_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;


/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-30 10:39:16
