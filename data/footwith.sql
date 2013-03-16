-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 14, 2013 at 10:55 AM
-- Server version: 5.5.24-log
-- PHP Version: 5.3.13

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+08:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

-- CREATE USER footwith IDENTIFIED BY PASSWORD '123';

CREATE DATABASE IF NOT EXISTS `footwith` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

GRANT ALL ON footwith.* TO footwith IDENTIFIED BY '123';

USE footwith;

--
-- Database: `footwith`
--

-- --------------------------------------------------------

--
-- Table structure for table `journal`
--

CREATE TABLE IF NOT EXISTS `journal` (
  `journalID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) NOT NULL,
  `title` varchar(80) NOT NULL,
  `body` text NOT NULL,
  `comments` int(11),
  `time` date NOT NULL,
  PRIMARY KEY (`journalID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `picture`
--

CREATE TABLE IF NOT EXISTS `picture` (
  `pictureID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) NOT NULL,
  `title` varchar(80) NOT NULL,
  `picturePath` varchar(80) NOT NULL,
  `comments` int(11),
  `time` date NOT NULL,
  PRIMARY KEY (`pictureID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `plan`
--

CREATE TABLE IF NOT EXISTS `plan` (
  `planID` int(11) NOT NULL AUTO_INCREMENT,
  `siteIDs` varchar(100) NOT NULL,
  `startTime` date NOT NULL,
  `endTime` date NOT NULL,
  `organizer` int(11) NOT NULL,
  `participants` varchar(100),
  `budget` int(11),
  `groupNum` int(11) NOT NULL,
  `groupNumMax` int(11) NOT NULL,
  `talkStreamID` int(11),
  `isDone` bit NOT NULL DEFAULT 0,
  PRIMARY KEY (`planID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `record`
--

CREATE TABLE IF NOT EXISTS `record` (
  `recordID` int(11) NOT NULL AUTO_INCREMENT,
  `siteIDs` varchar(100) NOT NULL,
  `startTime` date NOT NULL,
  `endTime` date,
  `userIDs` varchar(100) NOT NULL,
  `groupNum` int(11) NOT NULL,
  `journals` varchar(100),
  `pictures` varchar(100),
  `talkStreamID` int(11),
  `isDone` bit NOT NULL DEFAULT 0,
  PRIMARY KEY (`recordID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `site`
--

CREATE TABLE IF NOT EXISTS `site` (
  `siteID` int(11) NOT NULL AUTO_INCREMENT,
  `siteName` varchar(80) NOT NULL,
  `rate` int(11) NOT NULL,
  `location` varchar(80) NOT NULL,
  `brief` text,
  `picture` int(11),
  PRIMARY KEY (`siteID`),
  UNIQUE KEY `siteName` (`siteName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(32) NOT NULL,
  `nickName` varchar(32) NOT NULL,
  `passwd` varchar(32) NOT NULL,
  `otherInfo` int(11),
  `plans` varchar(80),
  `records` varchar(80),
  PRIMARY KEY (`userID`),
  UNIQUE KEY `userName` (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `userplan`
--

CREATE TABLE IF NOT EXISTS `userplan` (
  `userID` int(11) NOT NULL,
  `siteID` int(11) NOT NULL,
  `startTime` date NOT NULL,
  `endTime` date NOT NULL,
  `planID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `userrecord`
--

CREATE TABLE IF NOT EXISTS `userrecord` (
  `userID` int(11) NOT NULL,
  `siteID` int(11) NOT NULL,
  `startTime` int(11) NOT NULL,
  `endTime` int(11),
  `recordID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
