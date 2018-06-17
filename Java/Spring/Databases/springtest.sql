-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema springtest
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema springtest
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `springtest` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `springtest` ;

-- -----------------------------------------------------
-- Table `springtest`.`offers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `springtest`.`offers` ;

CREATE TABLE IF NOT EXISTS `springtest`.`offers` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(150) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `text` TEXT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
