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
  `external_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ikj6efadn0l9sj6it2jk40ojc` (`external_id`)
);
--
-- Table structure for table `beer_mash_temp`
--
DROP TABLE IF EXISTS `beer_mash_temp`;
CREATE TABLE `beer_mash_temp` (
  `beer_id` bigint(20) NOT NULL,
  `mash_temp` int(11) NOT NULL,
  CONSTRAINT `FK711k8l504abcbqtksuvhtoaq2` FOREIGN KEY (`beer_id`) REFERENCES `beer` (`id`)
);