CREATE DATABASE IF NOT EXISTS `localhost`;
USE `dib`;
SET NAMES utf8 ;

--
-- Table structure for table `beer`
--
DROP TABLE IF EXISTS `beer`;
CREATE TABLE `beer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `description` longtext,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
);

--
-- Table structure for table `beer_mash_temp`
--
DROP TABLE IF EXISTS `beer_mash_temp`;
CREATE TABLE `beer_mash_temp` (
  `beer_id` bigint(20) NOT NULL,
  `mash_temp` int(11) NOT NULL,
  KEY `FK711k8l504abcbqtksuvhtoaq2` (`beer_id`),
  CONSTRAINT `FK711k8l504abcbqtksuvhtoaq2` FOREIGN KEY (`beer_id`) REFERENCES `beer` (`id`)
);