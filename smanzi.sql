-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 25, 2016 at 03:59 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `smanzi`
--

-- --------------------------------------------------------

--
-- Table structure for table `advance`
--

CREATE TABLE IF NOT EXISTS `advance` (
  `advanceid` int(11) NOT NULL,
  `pay` varchar(50) DEFAULT NULL,
  `emp` text,
  `amount` double DEFAULT NULL,
  `recording_date` date DEFAULT NULL,
  PRIMARY KEY (`advanceid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `banking`
--

CREATE TABLE IF NOT EXISTS `banking` (
  `id` int(11) NOT NULL,
  `amount` double DEFAULT NULL,
  `emp` text,
  `recordingdate` date DEFAULT NULL,
  `banking_type` varchar(30) DEFAULT NULL,
  `details` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `banking`
--

INSERT INTO `banking` (`id`, `amount`, `emp`, `recordingdate`, `banking_type`, `details`) VALUES
(1, 1000, 'Unkown ', '2016-03-16', 'Deposit', 'No Comment\n');

-- --------------------------------------------------------

--
-- Table structure for table `closing`
--

CREATE TABLE IF NOT EXISTS `closing` (
  `id` int(11) NOT NULL,
  `prod_id` int(11) DEFAULT NULL,
  `close_type` varchar(20) DEFAULT NULL,
  `qty` float DEFAULT NULL,
  `close_date` date DEFAULT NULL,
  `close_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `closing`
--

INSERT INTO `closing` (`id`, `prod_id`, `close_type`, `qty`, `close_date`, `close_time`) VALUES
(1, 3, 'Opening', 60, '2015-11-11', '2015-11-11 20:10:54'),
(2, 1, 'Closing', 5, '2015-11-11', '2015-11-11 20:12:20'),
(3, 3, 'Opening', 20, '2015-11-19', '2015-11-19 20:30:51'),
(4, 3, 'Closing', 10, '2015-11-19', '2015-11-19 20:31:04'),
(5, 1, 'Opening', 40, '2015-11-24', '2015-11-24 19:59:24'),
(6, 1, 'Closing', 20, '2015-11-24', '2015-11-24 19:59:52'),
(7, 1, 'Opening', 10, '2015-12-01', '2015-12-01 20:36:06'),
(8, 3, 'Opening', 30, '2015-12-01', '2015-12-01 20:36:16');

-- --------------------------------------------------------

--
-- Table structure for table `expenses`
--

CREATE TABLE IF NOT EXISTS `expenses` (
  `expense_id` int(11) NOT NULL,
  `business` varchar(50) DEFAULT NULL,
  `description` text,
  `amount` double DEFAULT NULL,
  `recording_date` date DEFAULT NULL,
  PRIMARY KEY (`expense_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `expenses`
--

INSERT INTO `expenses` (`expense_id`, `business`, `description`, `amount`, `recording_date`) VALUES
(1, 'nail', 'No Details\n', 900, '2015-11-19');

-- --------------------------------------------------------

--
-- Table structure for table `nail`
--

CREATE TABLE IF NOT EXISTS `nail` (
  `prod_id` int(11) NOT NULL,
  `prodname` varchar(30) DEFAULT NULL,
  `qty` double DEFAULT NULL,
  `recording_date` date DEFAULT NULL,
  `details` text,
  PRIMARY KEY (`prod_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `nail`
--

INSERT INTO `nail` (`prod_id`, `prodname`, `qty`, `recording_date`, `details`) VALUES
(1, 'nail 3cm', 50, '2015-11-11', 'details'),
(2, 'nail 5cm', 100, '2015-11-11', 'details'),
(3, 'nail 3cm', 10, '2015-11-11', 'detailsuytuy'),
(4, 'nail 3cm', 12, '2015-11-10', 'detailsyufkjhf'),
(5, 'nail 5cm', 12, '2015-11-10', 'detailsyufkjhf'),
(6, 'nail 5cm', 15, '2015-11-03', 'detailsyufkjhf');

-- --------------------------------------------------------

--
-- Table structure for table `nail1`
--

CREATE TABLE IF NOT EXISTS `nail1` (
  `prod_id` int(11) NOT NULL,
  `prodname` varchar(30) DEFAULT NULL,
  `qty` double DEFAULT NULL,
  `recording_date` date DEFAULT NULL,
  PRIMARY KEY (`prod_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `nailsale`
--

CREATE TABLE IF NOT EXISTS `nailsale` (
  `saleid` int(11) NOT NULL,
  `material` varchar(50) NOT NULL,
  `emp` varchar(50) DEFAULT NULL,
  `client` varchar(50) DEFAULT NULL,
  `details` text,
  `amount` double DEFAULT NULL,
  `recordingdate` date NOT NULL,
  `qty` double DEFAULT NULL,
  `slip1` varchar(40) DEFAULT NULL,
  `slip2` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`saleid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `nailsale`
--

INSERT INTO `nailsale` (`saleid`, `material`, `emp`, `client`, `details`, `amount`, `recordingdate`, `qty`, `slip1`, `slip2`) VALUES
(2, 'nail 5cm', '', 'vvvv', 'make a commentuytiyu', 75000, '2015-11-11', 50, '55555', '6666'),
(3, 'nail 5cm', '', 'vvvv', 'make a commentuytiyu', 50000, '2015-11-11', 40, '55555', '6666');

-- --------------------------------------------------------

--
-- Table structure for table `nail_store`
--

CREATE TABLE IF NOT EXISTS `nail_store` (
  `purchase_id` int(11) NOT NULL,
  `prod_id` int(11) DEFAULT NULL,
  `description` text,
  `qty` double DEFAULT NULL,
  `selling_unitprice` double DEFAULT NULL,
  `purchasing_price` double DEFAULT NULL,
  `purchasing_date` date DEFAULT NULL,
  PRIMARY KEY (`purchase_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `nail_store`
--

INSERT INTO `nail_store` (`purchase_id`, `prod_id`, `description`, `qty`, `selling_unitprice`, `purchasing_price`, `purchasing_date`) VALUES
(1, 3, '***', 72, 0, 1000, '2015-11-11'),
(2, 1, '***', 32, 0, 1000, '2015-11-11');

-- --------------------------------------------------------

--
-- Stand-in structure for view `pay`
--
CREATE TABLE IF NOT EXISTS `pay` (
`paymentid` int(11)
,`pay` varchar(50)
,`emp` text
,`amount` double
,`recording_date` date
);
-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE IF NOT EXISTS `payment` (
  `paymentid` int(11) NOT NULL,
  `pay` varchar(50) DEFAULT NULL,
  `emp` text,
  `amount` double DEFAULT NULL,
  `recording_date` date DEFAULT NULL,
  PRIMARY KEY (`paymentid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE IF NOT EXISTS `product` (
  `prod_id` int(11) NOT NULL,
  `prodname` varchar(30) DEFAULT NULL,
  `business` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`prod_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`prod_id`, `prodname`, `business`) VALUES
(1, 'nail 5cm', 'Nail'),
(2, 'brosse', 'Hardware'),
(3, 'nail 3cm', 'Nail'),
(4, 'Petit Mutzing', 'Bar et Restaurent'),
(5, 'Grand Mutzing', 'Hardware'),
(6, 'XXXXX', 'Hardware'),
(7, 'AMSTEL', 'Workshop');

-- --------------------------------------------------------

--
-- Table structure for table `purchase`
--

CREATE TABLE IF NOT EXISTS `purchase` (
  `purchase_id` int(11) NOT NULL,
  `prod_id` int(11) DEFAULT NULL,
  `description` text,
  `qty` double DEFAULT NULL,
  `selling_unitprice` double DEFAULT NULL,
  `purchasing_price` double DEFAULT NULL,
  `purchasing_date` date DEFAULT NULL,
  PRIMARY KEY (`purchase_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `purchase`
--

INSERT INTO `purchase` (`purchase_id`, `prod_id`, `description`, `qty`, `selling_unitprice`, `purchasing_price`, `purchasing_date`) VALUES
(1, 5, 'No Details', 10, 1000, 950, '2015-11-29');

-- --------------------------------------------------------

--
-- Table structure for table `purchase1`
--

CREATE TABLE IF NOT EXISTS `purchase1` (
  `purchase_id` int(11) NOT NULL,
  `prod_id` int(11) DEFAULT NULL,
  `description` text,
  `qty` double DEFAULT NULL,
  `selling_unitprice` double DEFAULT NULL,
  `purchasing_price` double DEFAULT NULL,
  `purchasing_date` date DEFAULT NULL,
  PRIMARY KEY (`purchase_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `purchase1`
--

INSERT INTO `purchase1` (`purchase_id`, `prod_id`, `description`, `qty`, `selling_unitprice`, `purchasing_price`, `purchasing_date`) VALUES
(1, 5, 'No Details', 12, 1000, 950, '2015-11-29');

-- --------------------------------------------------------

--
-- Table structure for table `sales`
--

CREATE TABLE IF NOT EXISTS `sales` (
  `sale_id` int(11) NOT NULL,
  `prod_id` int(11) DEFAULT NULL,
  `qty` double DEFAULT NULL,
  `unitprice` double DEFAULT NULL,
  `received_amount` double DEFAULT NULL,
  `selling_date` date DEFAULT NULL,
  PRIMARY KEY (`sale_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sales`
--

INSERT INTO `sales` (`sale_id`, `prod_id`, `qty`, `unitprice`, `received_amount`, `selling_date`) VALUES
(1, 5, 2, 1000, 2000, '2015-11-29');

-- --------------------------------------------------------

--
-- Table structure for table `stockout`
--

CREATE TABLE IF NOT EXISTS `stockout` (
  `id` int(11) NOT NULL,
  `product` varchar(50) DEFAULT NULL,
  `qty` double DEFAULT NULL,
  `details` text,
  `recordingdate` date DEFAULT NULL,
  `emp` varchar(60) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int(11) NOT NULL,
  `usr_username` varchar(50) NOT NULL,
  `usr_password` varchar(50) NOT NULL,
  `usr_role` varchar(50) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `usr_username`, `usr_password`, `usr_role`) VALUES
(1, 'abc', '123', 'admin'),
(2, 'user ', '123', 'User'),
(3, ' Armando', 'iam', 'User'),
(4, ' ', '', 'Admin');

-- --------------------------------------------------------

--
-- Table structure for table `workshopsales`
--

CREATE TABLE IF NOT EXISTS `workshopsales` (
  `saleid` int(11) NOT NULL,
  `material` varchar(50) NOT NULL,
  `emp` varchar(50) DEFAULT NULL,
  `client` varchar(50) DEFAULT NULL,
  `details` text,
  `amount` double DEFAULT NULL,
  `recordingdate` date NOT NULL,
  PRIMARY KEY (`saleid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `workshopsales`
--

INSERT INTO `workshopsales` (`saleid`, `material`, `emp`, `client`, `details`, `amount`, `recordingdate`) VALUES
(1, 'AMSTEL', 'katy', 'Armand', 'no details', 5000, '2016-03-16');

-- --------------------------------------------------------

--
-- Structure for view `pay`
--
DROP TABLE IF EXISTS `pay`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `pay` AS select `payment`.`paymentid` AS `paymentid`,`payment`.`pay` AS `pay`,`payment`.`emp` AS `emp`,`payment`.`amount` AS `amount`,`payment`.`recording_date` AS `recording_date` from `payment`;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
