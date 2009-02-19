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
  `atendimento_numero` varchar(10) NOT NULL DEFAULT '',
  `chamado_id` int(10) NOT NULL DEFAULT '1',
  `municipio_id` int(10) NOT NULL DEFAULT '0',
  `bairro` varchar(40) NOT NULL DEFAULT '',
  `logradouro` varchar(70) DEFAULT NULL,
  `numcompl` varchar(30) DEFAULT NULL,
  `coordx` double DEFAULT NULL,
  `coordy` double DEFAULT NULL,
  `obm_id` int(10) NOT NULL DEFAULT '0',
  `status_atendimento` varchar(30) NOT NULL DEFAULT '1',
  `tipoocorrencia` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7E7914A34D8464F2` (`municipio_id`),
  KEY `FK7E7914A32A076032` (`chamado_id`),
  KEY `FK7E7914A3413BCBD2` (`obm_id`),
  CONSTRAINT `FK7E7914A32A076032` FOREIGN KEY (`chamado_id`) REFERENCES `chamados` (`id`),
  CONSTRAINT `FK7E7914A3413BCBD2` FOREIGN KEY (`obm_id`) REFERENCES `obm` (`id`),
  CONSTRAINT `FK7E7914A34D8464F2` FOREIGN KEY (`municipio_id`) REFERENCES `municipios` (`id`),
  CONSTRAINT `FK_atendimentos_1` FOREIGN KEY (`obm_id`) REFERENCES `chamados` (`id`),
  CONSTRAINT `FK_atendimentos_municipio` FOREIGN KEY (`municipio_id`) REFERENCES `municipios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COMMENT='Dados de atendimentos realizados';

--
-- Dumping data for table `atendimentos`
--

/*!40000 ALTER TABLE `atendimentos` DISABLE KEYS */;
INSERT INTO `atendimentos` (`id`,`atendimento_numero`,`chamado_id`,`municipio_id`,`bairro`,`logradouro`,`numcompl`,`coordx`,`coordy`,`obm_id`,`status_atendimento`,`tipoocorrencia`) VALUES 
 (1,'1',1,1,'taua','rua A','10',145.67,189.87,1,'Pendente','Orientação');
/*!40000 ALTER TABLE `atendimentos` ENABLE KEYS */;


--
-- Definition of table `avisossistema`
--

DROP TABLE IF EXISTS `avisossistema`;
CREATE TABLE `avisossistema` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `avisosistema_titulo` varchar(120) NOT NULL DEFAULT '',
  `avisosistema_conteudo` text,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Avisos do sistema aos usuarios';

--
-- Dumping data for table `avisossistema`
--

/*!40000 ALTER TABLE `avisossistema` DISABLE KEYS */;
/*!40000 ALTER TABLE `avisossistema` ENABLE KEYS */;


--
-- Definition of table `chamados`
--

DROP TABLE IF EXISTS `chamados`;
CREATE TABLE `chamados` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `naturezachamado` varchar(50) NOT NULL DEFAULT '0',
  `origem` varchar(50) DEFAULT NULL,
  `nomesolicitante` varchar(60) DEFAULT NULL,
  `telefonesolicitante` varchar(30) DEFAULT NULL,
  `numaproxvitimas` int(10) DEFAULT NULL,
  `infocomplementares` varchar(255) DEFAULT NULL,
  `obm_id` int(10) NOT NULL DEFAULT '0',
  `horainicio` datetime NOT NULL,
  `horatermino` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK55502018413BCBD2` (`obm_id`),
  CONSTRAINT `FK55502018413BCBD2` FOREIGN KEY (`obm_id`) REFERENCES `obm` (`id`),
  CONSTRAINT `FK_tbchamados_1` FOREIGN KEY (`obm_id`) REFERENCES `obm` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COMMENT='Registro de chamados de socorro';

--
-- Dumping data for table `chamados`
--

/*!40000 ALTER TABLE `chamados` DISABLE KEYS */;
INSERT INTO `chamados` (`id`,`naturezachamado`,`origem`,`nomesolicitante`,`telefonesolicitante`,`numaproxvitimas`,`infocomplementares`,`obm_id`,`horainicio`,`horatermino`) VALUES 
 (1,'Orientacao','telefone','Vando Araujo','33630507',0,'orientacao',1,'2011-00-00 00:00:00','2011-03-00 00:00:00');
/*!40000 ALTER TABLE `chamados` ENABLE KEYS */;


--
-- Definition of table `cronoatendimento`
--

DROP TABLE IF EXISTS `cronoatendimento`;
CREATE TABLE `cronoatendimento` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `atendimento_id` int(10) NOT NULL,
  `cronoatendimento_tipoevento` int(2) NOT NULL,
  `cronoatendimento_horaevento` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_atendimento` (`atendimento_id`),
  CONSTRAINT `cronoatendimento_ibfk_1` FOREIGN KEY (`atendimento_id`) REFERENCES `atendimentos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Armazena horarios associados aos atendimentos';

--
-- Dumping data for table `cronoatendimento`
--

/*!40000 ALTER TABLE `cronoatendimento` DISABLE KEYS */;
/*!40000 ALTER TABLE `cronoatendimento` ENABLE KEYS */;


--
-- Definition of table `log`
--

DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `log_datahora` datetime DEFAULT NULL,
  `log_usuario` varchar(10) DEFAULT NULL,
  `log_chave` int(10) DEFAULT NULL,
  `log_operacao` varchar(50) DEFAULT NULL,
  `log_detalhes` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Registro de log do sistema';

--
-- Dumping data for table `log`
--

/*!40000 ALTER TABLE `log` DISABLE KEYS */;
/*!40000 ALTER TABLE `log` ENABLE KEYS */;


--
-- Definition of table `movimentaviatura`
--

DROP TABLE IF EXISTS `movimentaviatura`;
CREATE TABLE `movimentaviatura` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `atendimento_id` int(10) NOT NULL,
  `viatura_id` int(10) NOT NULL,
  `movimentaviatura_tipoevento` int(2) NOT NULL,
  `movimentaviatura_horaevento` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_movimentaviatura_ATENDIMENTO` (`atendimento_id`),
  KEY `FK_movimentaviatura_viatura` (`viatura_id`),
  CONSTRAINT `FK_movimentaviatura_ATENDIMENTO` FOREIGN KEY (`atendimento_id`) REFERENCES `atendimentos` (`id`),
  CONSTRAINT `FK_movimentaviatura_viatura` FOREIGN KEY (`viatura_id`) REFERENCES `viaturas` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Armazenar horarios associados as movimentacoes das viaturas';

--
-- Dumping data for table `movimentaviatura`
--

/*!40000 ALTER TABLE `movimentaviatura` DISABLE KEYS */;
/*!40000 ALTER TABLE `movimentaviatura` ENABLE KEYS */;


--
-- Definition of table `municipios`
--

DROP TABLE IF EXISTS `municipios`;
CREATE TABLE `municipios` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `municipio_nome` varchar(50) CHARACTER SET latin1 COLLATE latin1_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=latin1 COMMENT='Municipios do Estado do Rio de Janeiro';

--
-- Dumping data for table `municipios`
--

/*!40000 ALTER TABLE `municipios` DISABLE KEYS */;
INSERT INTO `municipios` (`id`,`municipio_nome`) VALUES 
 (1,0x414E47524120444F532052454953),
 (2,0x41504552494245),
 (3,0x4152415255414D41),
 (4,0x415245414C),
 (5,0x4152524149414C20444F204341424F),
 (6,0x424152524120444F2050495241C38D),
 (7,0x4241525241204D414E5341),
 (8,0x42454C464F524420524F584F),
 (9,0x424F4D204A415244494D),
 (10,0x424F4D204A4553555320444F204954414241504F414E41),
 (11,0x4341424F204652494F),
 (12,0x434143484F4549524153204445204D4143414355),
 (13,0x43414D42554349),
 (14,0x43414D504F5320444F5320474F59544143415A4553),
 (15,0x43414E544147414C4F),
 (16,0x434152415045425553),
 (17,0x434152444F534F204D4F5245495241),
 (18,0x4341524D4F),
 (19,0x434153494D49524F204445204142524555),
 (20,0x434F4D454E4441444F52204C4556592047415350415249414E),
 (21,0x434F4E434549C387C3834F204445204D4143414255),
 (22,0x434F52444549524F),
 (23,0x4455415320424152524153),
 (24,0x445551554520444520434158494153),
 (25,0x454E47454E484549524F205041554C4F2044452046524F4E54494E),
 (26,0x47554150494D4952494D),
 (27,0x495441424F5241C38D),
 (28,0x4954414C5641),
 (29,0x4954414F43415241),
 (30,0x495441504552554E41),
 (31,0x4954415449414941),
 (32,0x4A4150455249),
 (33,0x4C414A4520444F204D55524941C389),
 (34,0x4D414341C389),
 (35,0x4D4147C389),
 (36,0x4D414E4741524154494241),
 (37,0x4D41524943C381),
 (38,0x4D454E444553),
 (39,0x4D494755454C2050455245495241),
 (40,0x4D49524143454D41),
 (41,0x4D45535155495441),
 (42,0x4E415449564944414445),
 (43,0x4E494CC393504F4C4953),
 (44,0x4E49544552C39349),
 (45,0x4E4F56412049475541C38755),
 (46,0x5041524143414D4249),
 (47,0x50415241C38D424120444F2053554C),
 (48,0x504152415449),
 (49,0x5041545920444F20414C4645524553),
 (50,0x50455452C393504F4C4953),
 (51,0x50495241C38D),
 (52,0x504F524349C39A4E43554C41),
 (53,0x515541544953),
 (54,0x515545494D41444F53),
 (55,0x5155495353414DC383),
 (56,0x524553454E4445),
 (57,0x52494F20424F4E49544F),
 (58,0x52494F20434C41524F),
 (59,0x52494F2044415320464C4F524553),
 (60,0x52494F20444153204F5354524153),
 (61,0x52494F204445204A414E4549524F),
 (62,0x53414E5441204D41524941204D4144414C454E41),
 (63,0x53414E544F20414E54C3944E494F2044452050C381445541),
 (64,0x53C3834F20464944C3894C4953),
 (65,0x53C3834F20474F4EC387414C4F),
 (66,0x53C3834F204A4FC3834F204441204241525241),
 (67,0x53C3834F204A4FC3834F204445204D4552495449),
 (68,0x53C3834F204A4F53C38920444F2056414C4520444F2052494F20505245544F),
 (69,0x53C3834F20504544524F20444120414C44454941),
 (70,0x53C3834F2053454241535449C3834F20444F20414C544F),
 (71,0x5341505543414941),
 (72,0x534151554152454D41),
 (73,0x5345524F50C38944494341),
 (74,0x53494C5641204A415244494D),
 (75,0x5445524553C393504F4C4953),
 (76,0x5452414A414E4F204445204D4F52414953),
 (77,0x5452C38A532052494F53),
 (78,0x56414C454EC38741),
 (79,0x56415252452D534149),
 (80,0x564153534F55524153),
 (81,0x564F4C5441205245444F4E4441),
 (82,0x4E4F564120465249425552474F),
 (83,0x4D414355434F),
 (84,0x495441475541C38D),
 (85,0x53C3834F204652414E434953434F204445204954414241504F414E41),
 (86,0x53554D49444F55524F),
 (87,0x41524D41C387C3834F2044452042C39A5A494F53),
 (88,0x494755414241204752414E4445),
 (89,0x50494E48454952414C),
 (90,0x504F52544F205245414C),
 (91,0x53C3834F204A4F53C389204445205542C381),
 (92,0x54414E4755C381);
/*!40000 ALTER TABLE `municipios` ENABLE KEYS */;


--
-- Definition of table `naturezaschamados`
--

DROP TABLE IF EXISTS `naturezaschamados`;
CREATE TABLE `naturezaschamados` (
  `id` int(11) NOT NULL DEFAULT '0',
  `naturezachamado_descricao` varchar(30) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
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
  `nome` varchar(40) DEFAULT NULL,
  `municipio_id` int(11) DEFAULT NULL,
  `bairro` varchar(40) DEFAULT NULL,
  `logradouro` varchar(40) DEFAULT NULL,
  `numCompl` varchar(40) DEFAULT NULL,
  `coordX` float DEFAULT NULL,
  `coordY` float DEFAULT NULL,
  `statusObm` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1ACFA4D8464F2` (`municipio_id`),
  CONSTRAINT `FK1ACFA4D8464F2` FOREIGN KEY (`municipio_id`) REFERENCES `municipios` (`id`),
  CONSTRAINT `FK_obm_municipio` FOREIGN KEY (`municipio_id`) REFERENCES `municipios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `obm`
--

/*!40000 ALTER TABLE `obm` DISABLE KEYS */;
INSERT INTO `obm` (`id`,`nome`,`municipio_id`,`bairro`,`logradouro`,`numCompl`,`coordX`,`coordY`,`statusObm`) VALUES 
 (1,'PracaTiradentes',6,'Centro','Praça','lote 5',178.8,174.9,1),
 (3,'Univercidade',44,'centro','rua a','16',178,179,0);
/*!40000 ALTER TABLE `obm` ENABLE KEYS */;


--
-- Definition of table `perfis`
--

DROP TABLE IF EXISTS `perfis`;
CREATE TABLE `perfis` (
  `id` int(11) NOT NULL DEFAULT '0',
  `perfil_descricao` varchar(30) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
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
  `id` int(10) NOT NULL,
  `tiposervico_id` int(10) NOT NULL,
  PRIMARY KEY (`id`,`tiposervico_id`),
  KEY `fk_tipoServicos` (`tiposervico_id`),
  CONSTRAINT `servicos_ibfk_1` FOREIGN KEY (`tiposervico_id`) REFERENCES `tiposservicos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Unidades operacionais do Corpo de Bombeiros';

--
-- Dumping data for table `servicos`
--

/*!40000 ALTER TABLE `servicos` DISABLE KEYS */;
/*!40000 ALTER TABLE `servicos` ENABLE KEYS */;


--
-- Definition of table `tiposocorrencias`
--

DROP TABLE IF EXISTS `tiposocorrencias`;
CREATE TABLE `tiposocorrencias` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `tipoocorrencia_descricao` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
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
  PRIMARY KEY (`id`) USING BTREE
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
  PRIMARY KEY (`id`) USING BTREE
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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usuario`
--

/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`id`,`numRegistro`,`nomeGuerra`,`email`,`obm_id`,`perfil`,`senha`,`statusUsu`) VALUES 
 (1,123,'araujo','tecladista.vando@gmail.com',1,'ADMIN','000','ativo'),
 (5,456,'bruno','bruno@gmail.com',1,'Comandante','123',NULL),
 (10,777,'Capitao','capitao@gmail.com',1,'OPERADOR','123',NULL);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;


--
-- Definition of table `viaturas`
--

DROP TABLE IF EXISTS `viaturas`;
CREATE TABLE `viaturas` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `tipoviatura_id` int(10) NOT NULL,
  `numero` varchar(3) NOT NULL DEFAULT '',
  `obm_id` int(10) NOT NULL,
  `viatura_obs` varchar(255) DEFAULT NULL,
  `viatura_status` int(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `fk_tipoviatura` (`tipoviatura_id`),
  CONSTRAINT `viaturas_ibfk_1` FOREIGN KEY (`tipoviatura_id`) REFERENCES `tiposviaturas` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Lista de viaturas operacionais usadas no CBMERJ';

--
-- Dumping data for table `viaturas`
--

/*!40000 ALTER TABLE `viaturas` DISABLE KEYS */;
/*!40000 ALTER TABLE `viaturas` ENABLE KEYS */;


--
-- Definition of table `vitimas`
--

DROP TABLE IF EXISTS `vitimas`;
CREATE TABLE `vitimas` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `atendimento_id` int(10) NOT NULL DEFAULT '0',
  `nome` varchar(60) DEFAULT NULL,
  `idade` int(3) DEFAULT NULL,
  `sexo` varchar(1) DEFAULT NULL,
  `cor` int(1) DEFAULT NULL,
  `vitima_situacao` int(1) NOT NULL DEFAULT '0',
  `hospitaldestino` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_atendimento` (`atendimento_id`),
  CONSTRAINT `vitimas_ibfk_1` FOREIGN KEY (`atendimento_id`) REFERENCES `atendimentos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Dados de vítimas atendidas';

--
-- Dumping data for table `vitimas`
--

/*!40000 ALTER TABLE `vitimas` DISABLE KEYS */;
/*!40000 ALTER TABLE `vitimas` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;