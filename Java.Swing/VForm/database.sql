CREATE DATABASE  IF NOT EXISTS `swingtest`;
USE `swingtest`;

DROP TABLE IF EXISTS `people`;

CREATE TABLE `people` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `age` enum('CHILD','ADULT','SENIOR') NOT NULL,
  `employment` varchar(45) NOT NULL,
  `docid` varchar(45) DEFAULT NULL,
  `plcitizen` tinyint(1) NOT NULL,
  `gender` enum('MALE','FEMALE') NOT NULL,
  `occupation` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;


LOCK TABLES `people` WRITE;

INSERT INTO `people` VALUES (1,'Setzo','ADULT','SELF-EMPLOYED','AMW12345',1,'MALE','Java Developer','790727182');

UNLOCK TABLES;
