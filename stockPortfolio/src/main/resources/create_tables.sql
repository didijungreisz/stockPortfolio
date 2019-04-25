CREATE DATABASE  IF NOT EXISTS `stock_portfolio` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `stock_portfolio`;
-- MySQL dump 10.13  Distrib 5.6.13, for osx10.6 (i386)
--
-- Host: 127.0.0.1    Database: web_customer_tracker
-- ------------------------------------------------------
-- Server version	5.6.16

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


CREATE TABLE IF NOT EXISTS TRADERS (
	TRADER_ID BIGINT AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS STOCK_HISTORY (
	STOCK_NAME VARCHAR(10) NOT NULL,
    STOCK_VALUE DOUBLE NOT NULL,
    DATE_OF_VALUE DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS STOCKS (
	STOCK_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
	STOCK_NAME VARCHAR(10) NOT NULL,
	STOCK_AMOUNT DOUBLE,
	TRADER_ID BIGINT
);


ALTER TABLE STOCKS ADD FOREIGN KEY (TRADER_ID) REFERENCES TRADERS(TRADER_ID);
ALTER TABLE TRADERS AUTO_INCREMENT=10000;