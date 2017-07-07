-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.18-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping structure for function spider_raptor.FN_UPDATE_VISIT
DELIMITER //
CREATE DEFINER=`root`@`localhost` FUNCTION `FN_UPDATE_VISIT`(
	`URI` VARCHAR(500)
) RETURNS int(11)
BEGIN
	UPDATE tempvisit
		SET tempvisit.visited = CURRENT_TIMESTAMP()
		WHERE tempvisit.url = URI;
	RETURN 0;
END//
DELIMITER ;

-- Dumping structure for table spider_raptor.generic
CREATE TABLE IF NOT EXISTS `generic` (
  `title` varchar(255) DEFAULT NULL,
  `ogTitle` varchar(255) DEFAULT NULL,
  `ogDescription` varchar(255) DEFAULT NULL,
  `ogType` varchar(255) DEFAULT NULL,
  `ogUpdatedTime` varchar(255) DEFAULT NULL,
  `ogLocale` varchar(255) DEFAULT NULL,
  `description` longtext,
  `lastVisited` datetime DEFAULT NULL,
  `keywords` longtext,
  `content` longtext,
  `url` varchar(255) DEFAULT NULL,
  `active` tinyint(1) DEFAULT NULL,
  `orderNumber` longtext,
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `createdAt` datetime DEFAULT NULL,
  `updatedAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
-- Dumping structure for function spider_raptor.PR_INSERT_VISIT
DELIMITER //
CREATE DEFINER=`root`@`localhost` FUNCTION `PR_INSERT_VISIT`(
	`URI` VARCHAR(500)
) RETURNS int(11)
    READS SQL DATA
BEGIN
	DECLARE idt INT;
	DECLARE VALID INT;
	SET idt = -1;

	SELECT tempv.id INTO idt 
		FROM tempvisit as tempv
		WHERE tempv.url = URI
		LIMIT 1;

    IF (idt = -1) THEN
     	INSERT INTO tempvisit(url)
     		VALUES (URI);
    END IF;

	 RETURN VALID;
END//
DELIMITER ;

-- Dumping structure for table spider_raptor.tempvisit
CREATE TABLE IF NOT EXISTS `tempvisit` (
  `url` varchar(255) DEFAULT NULL,
  `visited` datetime DEFAULT NULL,
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `createdAt` datetime DEFAULT NULL,
  `updatedAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
