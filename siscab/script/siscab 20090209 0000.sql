-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	6.0.6-alpha-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema siscab
--

CREATE DATABASE IF NOT EXISTS siscab;
USE siscab;

--
-- Definition of table `obm`
--

DROP TABLE IF EXISTS `obm`;
CREATE TABLE `obm` (
  `id` int(11) NOT NULL,
  `nome` varchar(40) DEFAULT NULL,
  `municipio` varchar(40) DEFAULT NULL,
  `bairro` varchar(40) DEFAULT NULL,
  `logradouro` varchar(40) DEFAULT NULL,
  `numCompl` varchar(40) DEFAULT NULL,
  `coordX` float DEFAULT NULL,
  `coordY` float DEFAULT NULL,
  `statusObm` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `obm`
--

/*!40000 ALTER TABLE `obm` DISABLE KEYS */;
INSERT INTO `obm` (`id`,`nome`,`municipio`,`bairro`,`logradouro`,`numCompl`,`coordX`,`coordY`,`statusObm`) VALUES 
 (1,'PracaTiradentes','Rio de janeiro','Centro','Rua A','Quadra15',178.8,174.9,1);
/*!40000 ALTER TABLE `obm` ENABLE KEYS */;


--
-- Definition of table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `numRegistro` int(11) NOT NULL,
  `nomeGuerra` varchar(40) DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL,
  `obm_id` int(11) DEFAULT NULL,
  `perfil` varchar(50) DEFAULT NULL,
  `senha` varchar(50) DEFAULT NULL,
  `statusUsu` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKF814F32E413BCBD2` (`obm_id`),
  CONSTRAINT `FKF814F32E413BCBD2` FOREIGN KEY (`obm_id`) REFERENCES `obm` (`id`),
  CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`obm_id`) REFERENCES `obm` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usuario`
--

/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`id`,`numRegistro`,`nomeGuerra`,`email`,`obm_id`,`perfil`,`senha`,`statusUsu`) VALUES 
 (1,123,'araujo','vandoaraujo@hotmail.com',1,'admin','123','ativo');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
