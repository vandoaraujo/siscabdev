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
-- Definition of table `atendimentos`
--

DROP TABLE IF EXISTS `atendimentos`;
CREATE TABLE `atendimentos` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `numero` varchar(10) NOT NULL DEFAULT '',
  `chamado_id` int(10) NOT NULL DEFAULT '0',
  `municipio_id` int(10) NOT NULL DEFAULT '0',
  `bairro` varchar(40) NOT NULL DEFAULT '',
  `logradouro` varchar(70) NOT NULL,
  `numcompl` varchar(30) DEFAULT NULL,
  `coordx` double NOT NULL,
  `coordy` double NOT NULL,
  `obm_id` int(10) NOT NULL DEFAULT '0',
  `status_atendimento` varchar(30) NOT NULL DEFAULT '',
  `tipoocorrencia_id` int(10) DEFAULT NULL,
  `modofechamento_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_atendimentos_chamados` (`chamado_id`),
  KEY `FK_atendimentos_municipios` (`municipio_id`),
  KEY `FK_atendimentos_obm` (`obm_id`),
  KEY `FK_atendimentos_tiposocorrencias` (`tipoocorrencia_id`),
  KEY `bairro_index` (`bairro`) USING BTREE,
  CONSTRAINT `FK7E7914A31235C51D` FOREIGN KEY (`tipoocorrencia_id`) REFERENCES `tiposocorrencias` (`id`),
  CONSTRAINT `FK7E7914A32A076032` FOREIGN KEY (`chamado_id`) REFERENCES `chamados` (`id`),
  CONSTRAINT `FK7E7914A3413BCBD2` FOREIGN KEY (`obm_id`) REFERENCES `obm` (`id`),
  CONSTRAINT `FK7E7914A34D8464F2` FOREIGN KEY (`municipio_id`) REFERENCES `municipios` (`id`),
  CONSTRAINT `FK_atendimentos_chamados` FOREIGN KEY (`chamado_id`) REFERENCES `chamados` (`id`),
  CONSTRAINT `FK_atendimentos_municipios` FOREIGN KEY (`municipio_id`) REFERENCES `municipios` (`id`),
  CONSTRAINT `FK_atendimentos_obm` FOREIGN KEY (`obm_id`) REFERENCES `obm` (`id`),
  CONSTRAINT `FK_atendimentos_tiposocorrencias` FOREIGN KEY (`tipoocorrencia_id`) REFERENCES `tiposocorrencias` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1 COMMENT='Dados de atendimentos realizados';

--
-- Dumping data for table `atendimentos`
--

/*!40000 ALTER TABLE `atendimentos` DISABLE KEYS */;
INSERT INTO `atendimentos` (`id`,`numero`,`chamado_id`,`municipio_id`,`bairro`,`logradouro`,`numcompl`,`coordx`,`coordy`,`obm_id`,`status_atendimento`,`tipoocorrencia_id`,`modofechamento_id`) VALUES 
 (2,'20091',6,61,'Ilha do Governador','Estrada do Cacuia','20',10,0,2,'Finalizado',3,1),
 (3,'20093',8,61,'Ilha do Governador','Estrada do Galeão','2750',1,0,9,'Finalizado',3,1),
 (4,'20094',9,22,'centro','rua cordeiro','10',0,0,9,'Finalizado',1,1),
 (5,'20095',10,88,'centro','rua a','10',0,0,9,'Finalizado',1,4),
 (6,'20096',13,26,'centro','rua a','10',0,0,9,'Finalizado',1,2),
 (7,'20097',19,61,'Botafogo','Rua São Clemente','10',0,0,9,'Em andamento',1,0),
 (8,'20098',21,61,'Ilha do Governador','Rua Maestro Arturo Toscanini taua','10',0,0,9,'Finalizado',1,1),
 (9,'20099',22,61,'centro','Rua do Ouvidor','20',0,0,9,'Finalizado',1,1),
 (10,'200910',24,26,'centro','rua g','19',0,0,9,'Finalizado',1,1),
 (11,'200911',26,44,'centro','Rua Miguel de Frias','701',-22.90284538269043,-43.11512756347656,9,'Finalizado',1,1),
 (12,'200912',28,18,'centro','rua a','15',0,0,4,'Pendente',1,0),
 (13,'200913',34,61,'Copacabana','Rua Barata Ribeiro','60',0,0,9,'Em andamento',1,0),
 (14,'20081',37,61,'centro','rua do ouvidor','90',0,0,9,'Em andamento',1,0),
 (15,'200915',40,61,'Ilha do Governador','juciape','162',-22.793018341064453,-43.17264175415039,9,'Pendente',1,0);
/*!40000 ALTER TABLE `atendimentos` ENABLE KEYS */;


--
-- Definition of table `chamados`
--

DROP TABLE IF EXISTS `chamados`;
CREATE TABLE `chamados` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `naturezachamado_id` int(10) NOT NULL DEFAULT '0',
  `origem` varchar(50) DEFAULT NULL,
  `nomesolicitante` varchar(60) DEFAULT NULL,
  `telefonesolicitante` varchar(30) DEFAULT NULL,
  `numaproxvitimas` int(10) DEFAULT NULL,
  `infocomplementares` varchar(255) DEFAULT NULL,
  `obm_id` int(10) NOT NULL DEFAULT '0',
  `horainicio` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `horatermino` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  KEY `FK_chamados_naturezaschamados` (`naturezachamado_id`),
  KEY `FK_chamados_obm` (`obm_id`),
  KEY `horatermino_index` (`horatermino`) USING BTREE,
  CONSTRAINT `FK55502018413BCBD2` FOREIGN KEY (`obm_id`) REFERENCES `obm` (`id`),
  CONSTRAINT `FK5550201849BB2B9B` FOREIGN KEY (`naturezachamado_id`) REFERENCES `naturezaschamados` (`id`),
  CONSTRAINT `FK_chamados_naturezaschamados` FOREIGN KEY (`naturezachamado_id`) REFERENCES `naturezaschamados` (`id`),
  CONSTRAINT `FK_chamados_obm` FOREIGN KEY (`obm_id`) REFERENCES `obm` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=latin1 COMMENT='Registro de chamados de socorro';

--
-- Dumping data for table `chamados`
--

/*!40000 ALTER TABLE `chamados` DISABLE KEYS */;
INSERT INTO `chamados` (`id`,`naturezachamado_id`,`origem`,`nomesolicitante`,`telefonesolicitante`,`numaproxvitimas`,`infocomplementares`,`obm_id`,`horainicio`,`horatermino`) VALUES 
 (1,1,'Telefone','Amanda','89098765',0,'urgente...',9,'2009-03-22 23:41:00','2009-03-22 23:43:00'),
 (2,1,'Telefone','Guilherme','9087-0987',0,'',2,'2009-03-23 21:16:00','2009-03-23 21:18:00'),
 (5,1,'Telefone','Fatima','2467',0,'',2,'2009-03-23 23:47:00','2009-03-23 23:50:00'),
 (6,1,'Telefone','Joao','-',0,'',2,'2009-03-23 23:58:00','2009-03-23 23:59:00'),
 (7,2,'Telefone','Luciana','90897876',0,'',9,'2009-03-24 20:46:00','2009-03-24 20:50:00'),
 (8,1,'Telefone','Luciana','0',0,'bla...',9,'2009-03-24 20:50:00','2009-03-24 20:50:00'),
 (9,1,'Telefone','Bruno','9089-0987',0,'',9,'2009-03-27 01:15:00','2009-03-27 01:16:00'),
 (10,1,'Telefone','joao','0',0,'',9,'2009-03-27 14:00:00','2009-03-27 14:01:00'),
 (11,1,'Telefone','','',0,'',9,'2009-03-27 15:48:00','2009-03-27 15:48:00'),
 (12,1,'Telefone','a','0',0,'',9,'2009-03-27 15:50:00','2009-03-27 15:50:00'),
 (13,1,'Telefone','cecilia','90898776',0,'',9,'2009-03-28 16:10:00','2009-03-28 16:11:00'),
 (14,1,'Telefone','maria','0',0,'',9,'2009-03-30 14:33:00','2009-03-30 14:34:00'),
 (15,1,'Telefone','','',0,'',9,'2009-03-30 14:42:00','2009-03-30 14:43:00'),
 (16,1,'Telefone','Bruno','0',0,'',9,'2009-03-30 23:49:00','2009-03-30 23:59:00'),
 (17,1,'Telefone','bruno','0',0,'',9,'2009-03-31 17:32:00','2009-03-31 17:32:00'),
 (18,1,'Telefone','Vando','0',0,'',9,'2009-04-01 22:15:00','2009-04-01 22:16:00'),
 (19,1,'Telefone','Bruno','0',0,'',9,'2009-04-01 22:19:00','2009-04-01 22:20:00'),
 (20,2,'Telefone','bruno','0',0,'',9,'2009-04-01 22:23:00','2009-04-01 22:24:00'),
 (21,1,'Telefone','Vando','0',0,'',9,'2009-04-04 00:24:00','2009-04-04 00:25:00'),
 (22,1,'Telefone','Bruno','0',0,'',9,'2009-04-25 20:44:00','2009-04-25 20:44:00'),
 (23,1,'Telefone','Bruno Monteiro','0',0,'',9,'2009-04-25 21:16:00','2009-04-25 21:16:00'),
 (24,1,'Telefone','Bruno Meneguel','0',0,'',9,'2009-04-25 21:47:00','2009-04-25 21:47:00'),
 (25,4,'Telefone','vando','0',0,'',9,'2009-04-29 01:12:00','2009-04-29 01:21:00'),
 (26,1,'Telefone','amanda','0',0,'',9,'2009-04-29 20:51:00','2009-04-29 20:52:00'),
 (27,1,'Telefone','Luciana','8765-0987',0,'',9,'2009-05-06 18:21:00','2009-05-06 18:22:00'),
 (28,1,'Telefone','Camila','0',10,'bla',4,'2009-05-06 18:50:00','2009-05-06 18:50:00'),
 (29,1,'Telefone','Vando Ribeiro','9487-4930',10,'',9,'2009-05-12 19:42:00','2009-05-12 19:43:00'),
 (30,1,'Telefone','Vando Ribeiro','9487-4930',10,'',9,'2009-05-12 19:42:00','2009-05-12 19:44:00'),
 (31,2,'Telefone','Vando Ribeiro','9487-4930',10,'',9,'2009-05-12 19:42:00','2009-05-12 19:45:00'),
 (32,1,'Telefone','Vando Ribeiro','9487-4930',10,'',9,'2009-05-12 19:42:00','2009-05-12 19:57:00'),
 (33,1,'Telefone','Vando Ribeiro','9487-4930',10,'',9,'2009-05-12 19:42:00','2009-05-12 20:18:00'),
 (34,1,'Telefone','Vando Ribeiro','9487-4930',2,'',9,'2009-05-12 20:41:00','2009-05-12 20:42:00'),
 (35,5,'Telefone','Bruno','8976-0987',0,'',9,'2009-05-12 20:42:00','2009-05-12 20:45:00'),
 (36,1,'Telefone','Bruno Lombardi','6786-9876',7,'',9,'2008-11-06 20:25:00','2008-11-06 20:26:00'),
 (37,1,'Telefone','Bruno Lombardi','6786-9876',7,'',9,'2008-11-06 20:32:00','2008-11-06 20:33:00'),
 (38,2,'Telefone','Bruno Roque','9089-9877',1,'Atropelada a Laika. :D',9,'2008-11-06 20:33:00','2008-11-06 20:34:00'),
 (39,2,'Telefone','Carla','7898-0987',0,'',9,'2008-11-06 20:39:00','2008-11-06 20:40:00'),
 (40,1,'Telefone','Bruno Roque','(21)4556-6778',1,'',9,'2009-05-26 21:12:00','2009-05-26 21:13:00'),
 (41,5,'Telefone','Pedro','(21)8898-8776',0,'',9,'2009-06-03 00:55:00','2009-06-03 00:57:00');
/*!40000 ALTER TABLE `chamados` ENABLE KEYS */;


--
-- Definition of table `cronoatendimento`
--

DROP TABLE IF EXISTS `cronoatendimento`;
CREATE TABLE `cronoatendimento` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `atendimento_id` int(10) NOT NULL DEFAULT '0',
  `tipoevento` varchar(40) NOT NULL DEFAULT '',
  `horaevento` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  KEY `FK_cronoatendimento_atendimentos` (`atendimento_id`),
  KEY `horaevento_index` (`horaevento`) USING BTREE,
  CONSTRAINT `FKA197AF8F4DC4F935` FOREIGN KEY (`atendimento_id`) REFERENCES `atendimentos` (`id`),
  CONSTRAINT `FK_cronoatendimento_atendimentos` FOREIGN KEY (`atendimento_id`) REFERENCES `atendimentos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=latin1 COMMENT='Armazena horarios associados aos atendimentos';

--
-- Dumping data for table `cronoatendimento`
--

/*!40000 ALTER TABLE `cronoatendimento` DISABLE KEYS */;
INSERT INTO `cronoatendimento` (`id`,`atendimento_id`,`tipoevento`,`horaevento`) VALUES 
 (7,2,'início','2009-03-25 18:45:17'),
 (16,3,'início','2009-03-27 01:03:06'),
 (17,4,'acionamento','2009-03-27 01:16:55'),
 (18,4,'início','2009-03-27 01:17:13'),
 (19,4,'início','2009-03-27 01:17:17'),
 (20,4,'finalização','2009-03-27 01:24:51'),
 (21,5,'acionamento','2009-03-27 14:01:20'),
 (22,5,'repasse','2009-03-27 14:59:24'),
 (23,6,'acionamento','2009-03-28 16:11:44'),
 (24,6,'finalização','2009-03-28 16:12:17'),
 (25,5,'finalização','2009-03-28 16:12:28'),
 (26,7,'acionamento','2009-04-01 22:20:46'),
 (27,7,'início','2009-04-01 22:25:22'),
 (28,7,'chegada à cena','2009-04-01 22:26:02'),
 (30,7,'chegada à cena','2009-04-01 23:56:00'),
 (31,8,'acionamento','2009-04-04 00:25:41'),
 (32,8,'início','2009-04-06 23:31:12'),
 (33,8,'finalização','2009-04-07 19:17:59'),
 (34,9,'acionamento','2009-04-25 20:46:55'),
 (35,10,'acionamento','2009-04-25 21:47:59'),
 (36,10,'início','2009-04-27 19:04:56'),
 (37,10,'repasse','2009-04-28 18:43:45'),
 (38,9,'repasse','2009-04-29 01:58:08'),
 (39,11,'acionamento','2009-04-29 20:52:41'),
 (40,10,'finalização','2009-04-30 18:47:30'),
 (41,9,'início','2009-04-30 18:48:37'),
 (42,11,'início','2009-04-30 18:58:03'),
 (43,12,'acionamento','2009-05-06 18:50:59'),
 (44,12,'repasse','2009-05-06 18:51:23'),
 (45,12,'repasse','2009-05-06 18:52:11'),
 (46,12,'repasse','2009-05-06 18:52:54'),
 (47,13,'acionamento','2009-05-12 20:42:30'),
 (48,14,'acionamento','2008-11-06 20:33:12'),
 (49,7,'repasse','2009-05-26 20:18:39'),
 (50,7,'repasse','2009-05-26 20:21:03'),
 (51,11,'finalização','2009-05-26 20:35:46'),
 (52,13,'início','2009-05-26 20:44:27'),
 (53,15,'acionamento','2009-05-26 21:13:30'),
 (54,9,'finalização','2009-05-27 01:03:47'),
 (55,14,'início','2009-06-03 17:51:42'),
 (56,2,'finalização','2009-03-28 01:00:00'),
 (57,3,'finalização','2009-03-28 01:01:00');
/*!40000 ALTER TABLE `cronoatendimento` ENABLE KEYS */;


--
-- Definition of table `log`
--

DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `datahora` datetime DEFAULT NULL,
  `usuario` varchar(10) DEFAULT NULL,
  `chave` int(10) DEFAULT NULL,
  `operacao` varchar(50) DEFAULT NULL,
  `detalhes` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `chave_index` (`chave`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Registro de log do sistema';

--
-- Dumping data for table `log`
--

/*!40000 ALTER TABLE `log` DISABLE KEYS */;
/*!40000 ALTER TABLE `log` ENABLE KEYS */;


--
-- Definition of table `modosfechamento`
--

DROP TABLE IF EXISTS `modosfechamento`;
CREATE TABLE `modosfechamento` (
  `id` int(11) NOT NULL DEFAULT '0',
  `modofechamento_descricao` varchar(30) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Modos de fechamento de uma ocorrencia';

--
-- Dumping data for table `modosfechamento`
--

/*!40000 ALTER TABLE `modosfechamento` DISABLE KEYS */;
INSERT INTO `modosfechamento` (`id`,`modofechamento_descricao`) VALUES 
 (1,'Atendida'),
 (2,'Não atendida'),
 (3,'Cancelada pelo solicitante'),
 (4,'Falso aviso (trote)'),
 (5,'Sem atuação');
/*!40000 ALTER TABLE `modosfechamento` ENABLE KEYS */;


--
-- Definition of table `movimentaviatura`
--

DROP TABLE IF EXISTS `movimentaviatura`;
CREATE TABLE `movimentaviatura` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `atendimento_id` int(10) NOT NULL DEFAULT '0',
  `viatura_id` int(10) NOT NULL DEFAULT '0',
  `tipoevento` varchar(40) NOT NULL DEFAULT '',
  `horaevento` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  KEY `FK_movimentaviatura_atendimentos` (`atendimento_id`),
  KEY `FK_movimentaviatura_viaturas` (`viatura_id`),
  CONSTRAINT `FK93D18DB04DC4F935` FOREIGN KEY (`atendimento_id`) REFERENCES `atendimentos` (`id`),
  CONSTRAINT `FK93D18DB063B96552` FOREIGN KEY (`viatura_id`) REFERENCES `viaturas` (`id`),
  CONSTRAINT `FK_movimentaviatura_atendimentos` FOREIGN KEY (`atendimento_id`) REFERENCES `atendimentos` (`id`),
  CONSTRAINT `FK_movimentaviatura_viaturas` FOREIGN KEY (`viatura_id`) REFERENCES `viaturas` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=latin1 COMMENT='Armazenar horarios associados as movimentacoes das viaturas';

--
-- Dumping data for table `movimentaviatura`
--

/*!40000 ALTER TABLE `movimentaviatura` DISABLE KEYS */;
INSERT INTO `movimentaviatura` (`id`,`atendimento_id`,`viatura_id`,`tipoevento`,`horaevento`) VALUES 
 (10,3,7,'Saída da OBM','2009-03-27 01:03:01'),
 (11,3,1,'Saída da OBM','2009-03-27 01:03:06'),
 (12,4,1,'Saída da OBM','2009-03-27 01:17:13'),
 (14,7,7,'Saída da OBM','2009-04-01 22:25:22'),
 (15,7,7,'Chegada à Cena','2009-04-01 22:26:02'),
 (16,7,3,'Saída da OBM','2009-04-01 22:26:54'),
 (18,7,1,'Saída da OBM','2009-04-01 22:41:27'),
 (19,7,3,'Saída da OBM','2009-04-01 22:49:02'),
 (21,7,2,'Saída da OBM','2009-04-01 23:40:23'),
 (22,7,7,'Saída da OBM','2009-04-01 23:48:39'),
 (24,7,2,'Chegada à Cena','2009-04-01 23:56:00'),
 (25,8,1,'Saída da OBM','2009-04-06 23:31:12'),
 (26,7,1,'Saída da OBM','2009-04-23 22:16:05'),
 (27,10,2,'Saída da OBM','2009-04-27 19:04:56'),
 (28,10,2,'Chegada à Cena','2009-04-27 19:24:00'),
 (29,10,2,'Saída da Cena','2009-04-27 19:25:00'),
 (30,10,2,'Chegada ao Hospital','2009-04-27 19:25:00'),
 (31,10,2,'Saída do Hospital','2009-04-27 19:26:00'),
 (32,10,2,'Retorno à OBM','2009-04-27 19:26:00'),
 (33,10,3,'Saída da OBM','2009-04-28 18:47:00'),
 (34,10,7,'Saída da OBM','2009-04-29 22:08:14'),
 (35,9,7,'Saída da OBM','2009-04-30 18:48:37'),
 (36,9,7,'Chegada à Cena','2009-04-30 18:49:00'),
 (37,11,7,'Saída da OBM','2009-04-30 18:58:03'),
 (38,11,7,'Chegada à Cena','2009-05-06 00:21:00'),
 (39,11,7,'Saída da Cena','2009-05-06 00:32:00'),
 (40,11,7,'Chegada ao Hospital','2009-05-06 00:36:00'),
 (41,11,2,'Saída da OBM','2009-05-06 00:37:13'),
 (42,11,1,'Saída da OBM','2009-05-06 00:37:23'),
 (43,11,1,'Chegada à Cena','2009-05-06 00:37:00'),
 (44,11,1,'Saída da Cena','2009-05-06 18:25:00'),
 (45,7,2,'Saída da Cena','2009-05-12 21:39:00'),
 (46,11,1,'Chegada ao Hospital','2009-05-26 20:27:00'),
 (47,11,1,'Saída do Hospital','2009-05-26 20:27:00'),
 (48,11,2,'Saída da OBM','2009-05-26 20:28:06'),
 (49,11,8,'Saída da OBM','2009-05-26 20:29:05'),
 (50,13,3,'Saída da OBM','2009-05-26 20:44:27'),
 (51,9,8,'Saída da OBM','2009-05-27 00:28:49'),
 (52,9,8,'Retorno à OBM','2009-05-27 01:02:00'),
 (53,9,1,'Saída da OBM','2009-05-27 01:02:57'),
 (54,9,2,'Saída da OBM','2009-05-27 01:03:02'),
 (55,9,8,'Saída da OBM','2009-05-27 01:03:07'),
 (56,14,1,'Saída da OBM','2009-06-03 17:51:42');
/*!40000 ALTER TABLE `movimentaviatura` ENABLE KEYS */;


--
-- Definition of table `municipios`
--

DROP TABLE IF EXISTS `municipios`;
CREATE TABLE `municipios` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `municipio_nome` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `municipio_index` (`municipio_nome`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=latin1 COMMENT='Municipios do Estado do Rio de Janeiro';

--
-- Dumping data for table `municipios`
--

/*!40000 ALTER TABLE `municipios` DISABLE KEYS */;
INSERT INTO `municipios` (`id`,`municipio_nome`) VALUES 
 (1,'ANGRA DOS REIS'),
 (2,'APERIBE'),
 (3,'ARARUAMA'),
 (4,'AREAL'),
 (87,'ARMAÇÃO DE BÚZIOS'),
 (5,'ARRAIAL DO CABO'),
 (6,'BARRA DO PIRAÍ'),
 (7,'BARRA MANSA'),
 (8,'BELFORD ROXO'),
 (9,'BOM JARDIM'),
 (10,'BOM JESUS DO ITABAPOANA'),
 (11,'CABO FRIO'),
 (12,'CACHOEIRAS DE MACACU'),
 (13,'CAMBUCI'),
 (14,'CAMPOS DOS GOYTACAZES'),
 (15,'CANTAGALO'),
 (16,'CARAPEBUS'),
 (17,'CARDOSO MOREIRA'),
 (18,'CARMO'),
 (19,'CASIMIRO DE ABREU'),
 (20,'COMENDADOR LEVY GASPARIAN'),
 (21,'CONCEIÇÃO DE MACABU'),
 (22,'CORDEIRO'),
 (23,'DUAS BARRAS'),
 (24,'DUQUE DE CAXIAS'),
 (25,'ENGENHEIRO PAULO DE FRONTIN'),
 (26,'GUAPIMIRIM'),
 (88,'IGUABA GRANDE'),
 (27,'ITABORAÍ'),
 (84,'ITAGUAÍ'),
 (28,'ITALVA'),
 (29,'ITAOCARA'),
 (30,'ITAPERUNA'),
 (31,'ITATIAIA'),
 (32,'JAPERI'),
 (33,'LAJE DO MURIAÉ'),
 (34,'MACAÉ'),
 (83,'MACUCO'),
 (35,'MAGÉ'),
 (36,'MANGARATIBA'),
 (37,'MARICÁ'),
 (38,'MENDES'),
 (41,'MESQUITA'),
 (39,'MIGUEL PEREIRA'),
 (40,'MIRACEMA'),
 (42,'NATIVIDADE'),
 (43,'NILÓPOLIS'),
 (44,'NITERÓI'),
 (82,'NOVA FRIBURGO'),
 (45,'NOVA IGUAÇU'),
 (46,'PARACAMBI'),
 (47,'PARAÍBA DO SUL'),
 (48,'PARATI'),
 (49,'PATY DO ALFERES'),
 (50,'PETRÓPOLIS'),
 (89,'PINHEIRAL'),
 (51,'PIRAÍ'),
 (52,'PORCIÚNCULA'),
 (90,'PORTO REAL'),
 (53,'QUATIS'),
 (54,'QUEIMADOS'),
 (55,'QUISSAMÃ'),
 (56,'RESENDE'),
 (57,'RIO BONITO'),
 (58,'RIO CLARO'),
 (59,'RIO DAS FLORES'),
 (60,'RIO DAS OSTRAS'),
 (61,'RIO DE JANEIRO'),
 (62,'SANTA MARIA MADALENA'),
 (63,'SANTO ANTÔNIO DE PÁDUA'),
 (64,'SÃO FIDÉLIS'),
 (85,'SÃO FRANCISCO DE ITABAPOANA'),
 (65,'SÃO GONÇALO'),
 (66,'SÃO JOÃO DA BARRA'),
 (67,'SÃO JOÃO DE MERITI'),
 (91,'SÃO JOSÉ DE UBÁ'),
 (68,'SÃO JOSÉ DO VALE DO RIO PRETO'),
 (69,'SÃO PEDRO DA ALDEIA'),
 (70,'SÃO SEBASTIÃO DO ALTO'),
 (71,'SAPUCAIA'),
 (72,'SAQUAREMA'),
 (73,'SEROPÉDICA'),
 (74,'SILVA JARDIM'),
 (86,'SUMIDOURO'),
 (92,'TANGUÁ'),
 (75,'TERESÓPOLIS'),
 (76,'TRAJANO DE MORAIS'),
 (77,'TRÊS RIOS'),
 (78,'VALENÇA'),
 (79,'VARRE-SAI'),
 (80,'VASSOURAS'),
 (81,'VOLTA REDONDA');
/*!40000 ALTER TABLE `municipios` ENABLE KEYS */;


--
-- Definition of table `naturezaschamados`
--

DROP TABLE IF EXISTS `naturezaschamados`;
CREATE TABLE `naturezaschamados` (
  `id` int(11) NOT NULL DEFAULT '0',
  `naturezachamado_descricao` varchar(30) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Naturezas dos chamados de socorro';

--
-- Dumping data for table `naturezaschamados`
--

/*!40000 ALTER TABLE `naturezaschamados` DISABLE KEYS */;
INSERT INTO `naturezaschamados` (`id`,`naturezachamado_descricao`) VALUES 
 (1,'Solicitação de socorro'),
 (2,'Trote'),
 (3,'Engano/desistência'),
 (4,'Queda de ligação'),
 (5,'Orientação'),
 (6,'Informação sobre atendimento');
/*!40000 ALTER TABLE `naturezaschamados` ENABLE KEYS */;


--
-- Definition of table `obm`
--

DROP TABLE IF EXISTS `obm`;
CREATE TABLE `obm` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL DEFAULT '',
  `municipio_id` int(11) NOT NULL DEFAULT '0',
  `bairro` varchar(40) NOT NULL DEFAULT '',
  `logradouro` varchar(70) NOT NULL,
  `numcompl` varchar(30) DEFAULT NULL,
  `coordx` double NOT NULL,
  `coordy` double NOT NULL,
  `status_obm` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_obm_municipios` (`municipio_id`),
  CONSTRAINT `FK1ACFA4D8464F2` FOREIGN KEY (`municipio_id`) REFERENCES `municipios` (`id`),
  CONSTRAINT `FK_obm_municipios` FOREIGN KEY (`municipio_id`) REFERENCES `municipios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `obm`
--

/*!40000 ALTER TABLE `obm` DISABLE KEYS */;
INSERT INTO `obm` (`id`,`nome`,`municipio_id`,`bairro`,`logradouro`,`numcompl`,`coordx`,`coordy`,`status_obm`) VALUES 
 (1,'COCB',61,'Centro','Praça da República','45',-22.95838343909873,-43.200130462646484,1),
 (2,'1º GBM - Humaitá',61,'Humaitá','Rua Humaitá','S/N',-22.95838343909873,-43.200130462646484,1),
 (3,'2º GBM - Méier',61,'Méier','Rua Aristide Cairé','56',-22.8974351,-43.2764638,1),
 (4,'8º GBM - Campinho',61,'Campinho','Rua Domingos Lopes','336',-22.8799215496761,-43.34126830101013,1),
 (5,'11º GBM - Vila Isabel',61,'Vila Isabel','Rua Oito de Dezembro','456',-22.91019503816399,-43.241339921951294,1),
 (6,'12º GBM - Jacarepaguá',61,'Jacarepaguá','Rua Henriqueta','99 - Tanque',-22.919583092024975,-43.3580482006073,1),
 (7,'13º GBM - Campo Grande',61,'Campo Grande','AV. Cesário de Melo','3226',-22.907279668085742,-43.56295824050903,1),
 (8,'17º GBM - Copacabana',61,'Copacabana','Rua Xavier da Silveira','120',-22.976026160554113,-43.19354295730591,0),
 (9,'19º GBM - Ilha do Governador',61,'Ilha do Governador','Estrada do Galeão','S/N - Guarabu',-22.807437421190155,-43.19757699966431,1),
 (10,'24º GBM - Irajá',61,'Irajá','Av. Brasil','19001',-22.826108421544685,-43.33432674407959,1);
/*!40000 ALTER TABLE `obm` ENABLE KEYS */;


--
-- Definition of table `perfis`
--

DROP TABLE IF EXISTS `perfis`;
CREATE TABLE `perfis` (
  `id` int(11) NOT NULL DEFAULT '0',
  `perfil_descricao` varchar(30) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Perfis de usuarios';

--
-- Dumping data for table `perfis`
--

/*!40000 ALTER TABLE `perfis` DISABLE KEYS */;
INSERT INTO `perfis` (`id`,`perfil_descricao`) VALUES 
 (1,'Administrador do Sistema'),
 (2,'Atendente do COCB'),
 (3,'Operador da OBM'),
 (4,'Controlador do COCB'),
 (5,'Comandante');
/*!40000 ALTER TABLE `perfis` ENABLE KEYS */;


--
-- Definition of table `servicos`
--

DROP TABLE IF EXISTS `servicos`;
CREATE TABLE `servicos` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `tiposervico_id` int(10) NOT NULL DEFAULT '0',
  `atendimento_id` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_servicos_tiposservicos` (`tiposervico_id`),
  KEY `FK_servicos_atendimentos` (`atendimento_id`),
  CONSTRAINT `FK523511943C34A59A` FOREIGN KEY (`tiposervico_id`) REFERENCES `tiposservicos` (`id`),
  CONSTRAINT `FK523511944DC4F935` FOREIGN KEY (`atendimento_id`) REFERENCES `atendimentos` (`id`),
  CONSTRAINT `FK_servicos_atendimentos` FOREIGN KEY (`atendimento_id`) REFERENCES `atendimentos` (`id`),
  CONSTRAINT `FK_servicos_tiposservicos` FOREIGN KEY (`tiposervico_id`) REFERENCES `tiposservicos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1 COMMENT='Unidades operacionais do Corpo de Bombeiros';

--
-- Dumping data for table `servicos`
--

/*!40000 ALTER TABLE `servicos` DISABLE KEYS */;
INSERT INTO `servicos` (`id`,`tiposervico_id`,`atendimento_id`) VALUES 
 (1,10,2),
 (2,1,3),
 (3,1,8),
 (4,1,10),
 (5,2,10),
 (6,3,10),
 (7,1,11),
 (9,2,11),
 (10,3,11),
 (11,4,11),
 (12,7,11);
/*!40000 ALTER TABLE `servicos` ENABLE KEYS */;


--
-- Definition of table `tiposocorrencias`
--

DROP TABLE IF EXISTS `tiposocorrencias`;
CREATE TABLE `tiposocorrencias` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `tipoocorrencia_descricao` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1 COMMENT='Tipos de ocorrencias atendidas';

--
-- Dumping data for table `tiposocorrencias`
--

/*!40000 ALTER TABLE `tiposocorrencias` DISABLE KEYS */;
INSERT INTO `tiposocorrencias` (`id`,`tipoocorrencia_descricao`) VALUES 
 (1,'Colisão de veículo'),
 (2,'Atropelamento'),
 (3,'Mal súbito'),
 (4,'Incêndio em área urbana'),
 (5,'Incêndio florestal'),
 (6,'Animal em situação de risco');
/*!40000 ALTER TABLE `tiposocorrencias` ENABLE KEYS */;


--
-- Definition of table `tiposservicos`
--

DROP TABLE IF EXISTS `tiposservicos`;
CREATE TABLE `tiposservicos` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `tiposervico_descricao` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1 COMMENT='Tipos de servicos que podem ser executados';

--
-- Dumping data for table `tiposservicos`
--

/*!40000 ALTER TABLE `tiposservicos` DISABLE KEYS */;
INSERT INTO `tiposservicos` (`id`,`tiposervico_descricao`) VALUES 
 (1,'Atendimento pré-hospitalar'),
 (2,'Salvamento em altura'),
 (3,'Desencarceramento'),
 (4,'Extinção de incêndio'),
 (5,'Arrombamento'),
 (6,'Salvamento de animal'),
 (7,'Transporte de animal'),
 (8,'Proteção do local'),
 (9,'Lavagem de pista'),
 (10,'Iluminação do local');
/*!40000 ALTER TABLE `tiposservicos` ENABLE KEYS */;


--
-- Definition of table `tiposviaturas`
--

DROP TABLE IF EXISTS `tiposviaturas`;
CREATE TABLE `tiposviaturas` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `tipoviatura_abreviacao` varchar(5) NOT NULL DEFAULT '',
  `tipoviatura_descricao` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1 COMMENT='Tipos de viaturas existentes';

--
-- Dumping data for table `tiposviaturas`
--

/*!40000 ALTER TABLE `tiposviaturas` DISABLE KEYS */;
INSERT INTO `tiposviaturas` (`id`,`tipoviatura_abreviacao`,`tipoviatura_descricao`) VALUES 
 (1,'ABT','Auto Bomba Tanque'),
 (2,'ABS','Auto Busca e Salvamento'),
 (3,'ABSL','Auto Busca e Salvamento Leve'),
 (4,'ASE','Auto Socorro de Emergência (ambulância)'),
 (5,'APM','Auto Plataforma Mecânica'),
 (6,'APP','Auto Produtos Perigosos');
/*!40000 ALTER TABLE `tiposviaturas` ENABLE KEYS */;


--
-- Definition of table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `numregistro` int(11) NOT NULL DEFAULT '0',
  `nomeguerra` varchar(40) NOT NULL,
  `email` varchar(60) DEFAULT NULL,
  `obm_id` int(11) NOT NULL DEFAULT '0',
  `perfil_id` int(11) NOT NULL DEFAULT '0',
  `senha` varchar(50) NOT NULL DEFAULT '',
  `status_usuario` varchar(15) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_usuario_obm` (`obm_id`),
  KEY `FK_usuario_perfil` (`perfil_id`),
  KEY `numregistro_index` (`numregistro`) USING BTREE,
  KEY `senha_index` (`senha`) USING BTREE,
  CONSTRAINT `FKF814F32E413BCBD2` FOREIGN KEY (`obm_id`) REFERENCES `obm` (`id`),
  CONSTRAINT `FKF814F32E6E279288` FOREIGN KEY (`perfil_id`) REFERENCES `perfis` (`id`),
  CONSTRAINT `FK_usuario_obm` FOREIGN KEY (`obm_id`) REFERENCES `obm` (`id`),
  CONSTRAINT `FK_usuario_perfil` FOREIGN KEY (`perfil_id`) REFERENCES `perfis` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usuario`
--

/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`id`,`numregistro`,`nomeguerra`,`email`,`obm_id`,`perfil_id`,`senha`,`status_usuario`) VALUES 
 (1,123,'araujo','vandoaraujo@hotmail.com',1,1,'000','ATIVO'),
 (2,456,'Amaral','bruno.ramaral@gmail.com',9,3,'000','ATIVO'),
 (3,777,'Flavia','flaviauerj@yahoo.com.br',1,1,'999','ATIVO'),
 (15,890,'Carol','carol@gmail.com',1,2,'000','ATIVO'),
 (17,892,'Luciana','lucianaclemos@gmail.com',1,5,'123','ATIVO'),
 (18,876,'Ronaldo','ronaldo@gmail.com',2,3,'123','ATIVO'),
 (19,990,'Flavia','Flavia_uerj@yahoo.com.br',1,2,'123','INATIVO'),
 (21,908,'Cintia','cintia@cobm.com',4,3,'123','ATIVO'),
 (23,1000,'Reinaldo','reinaldo@gmail.com',1,4,'000','ATIVO'),
 (24,1001,'Marcelo','marcelo@cobm.com',2,3,'123','ATIVO');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;


--
-- Definition of table `viaturas`
--

DROP TABLE IF EXISTS `viaturas`;
CREATE TABLE `viaturas` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `tipoviatura_id` int(10) NOT NULL DEFAULT '0',
  `numero` varchar(3) NOT NULL DEFAULT '',
  `obm_id` int(10) NOT NULL DEFAULT '0',
  `obs` varchar(255) DEFAULT NULL,
  `status_viatura` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `FK_viaturas_tiposviaturas` (`tipoviatura_id`),
  KEY `FK_viaturas_obm` (`obm_id`),
  CONSTRAINT `FK40589EB5413BCBD2` FOREIGN KEY (`obm_id`) REFERENCES `obm` (`id`),
  CONSTRAINT `FK40589EB56C365DB2` FOREIGN KEY (`tipoviatura_id`) REFERENCES `tiposviaturas` (`id`),
  CONSTRAINT `FK_viaturas_obm` FOREIGN KEY (`obm_id`) REFERENCES `obm` (`id`),
  CONSTRAINT `FK_viaturas_tiposviaturas` FOREIGN KEY (`tipoviatura_id`) REFERENCES `tiposviaturas` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1 COMMENT='Lista de viaturas operacionais usadas no CBMERJ';

--
-- Dumping data for table `viaturas`
--

/*!40000 ALTER TABLE `viaturas` DISABLE KEYS */;
INSERT INTO `viaturas` (`id`,`tipoviatura_id`,`numero`,`obm_id`,`obs`,`status_viatura`) VALUES 
 (1,5,'C01',9,'nova viatura. ','Em atendimento'),
 (2,2,'V01',9,'teste','Em prontidão'),
 (3,1,'C03',9,'','Em atendimento'),
 (7,1,'C06',9,'','Em prontidão'),
 (8,1,'C09',9,'','Em prontidão'),
 (9,4,'D90',9,'Contactar manutenção preventiva.','Inoperante - Sem tripulação'),
 (10,1,'DA7',4,'','Inoperante - Sem tripulação'),
 (11,1,'E89',9,'','Inoperante - Sem tripulação'),
 (12,5,'A88',6,NULL,'Inoperante - Sem tripulação'),
 (13,4,'A89',6,NULL,'Inoperante - Sem tripulação');
/*!40000 ALTER TABLE `viaturas` ENABLE KEYS */;


--
-- Definition of table `vitimas`
--

DROP TABLE IF EXISTS `vitimas`;
CREATE TABLE `vitimas` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `atendimento_id` int(10) NOT NULL DEFAULT '0',
  `nome` varchar(60) NOT NULL,
  `idade` int(3) DEFAULT NULL,
  `sexo` varchar(1) DEFAULT NULL,
  `cor` int(1) DEFAULT NULL,
  `situacaofinal` int(1) NOT NULL DEFAULT '0',
  `hospitaldestino` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_vitimas_atendimentos` (`atendimento_id`),
  CONSTRAINT `FK1BE02FF74DC4F935` FOREIGN KEY (`atendimento_id`) REFERENCES `atendimentos` (`id`),
  CONSTRAINT `FK_vitimas_atendimentos` FOREIGN KEY (`atendimento_id`) REFERENCES `atendimentos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1 COMMENT='Dados de vítimas atendidas';

--
-- Dumping data for table `vitimas`
--

/*!40000 ALTER TABLE `vitimas` DISABLE KEYS */;
INSERT INTO `vitimas` (`id`,`atendimento_id`,`nome`,`idade`,`sexo`,`cor`,`situacaofinal`,`hospitaldestino`) VALUES 
 (1,2,'Marcela',20,'F',1,1,''),
 (2,3,'Pedro',10,'M',1,1,''),
 (3,10,'Marcelo',56,'M',4,1,''),
 (4,11,'Bruno',60,'M',1,2,'Paullino Werneck'),
 (5,11,'carla',87,'F',1,1,''),
 (12,13,'Carlos',60,'M',2,2,''),
 (14,13,'Bianca',28,'F',1,3,''),
 (15,13,'Marcos',0,'M',4,2,'');
/*!40000 ALTER TABLE `vitimas` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
