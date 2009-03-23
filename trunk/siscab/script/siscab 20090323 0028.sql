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
  `logradouro` varchar(70) DEFAULT NULL,
  `numcompl` varchar(30) DEFAULT NULL,
  `coordx` double DEFAULT NULL,
  `coordy` double DEFAULT NULL,
  `obm_id` int(10) NOT NULL DEFAULT '0',
  `status_atendimento` varchar(30) NOT NULL DEFAULT '',
  `tipoocorrencia_id` int(10) DEFAULT NULL,
  `modofechamento_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_atendimentos_chamados` (`chamado_id`),
  KEY `FK_atendimentos_municipios` (`municipio_id`),
  KEY `FK_atendimentos_obm` (`obm_id`),
  KEY `FK_atendimentos_tiposocorrencias` (`tipoocorrencia_id`),
  KEY `FK7E7914A34D8464F2` (`municipio_id`),
  KEY `FK7E7914A31235C51D` (`tipoocorrencia_id`),
  KEY `FK7E7914A32A076032` (`chamado_id`),
  KEY `FK7E7914A3413BCBD2` (`obm_id`),
  CONSTRAINT `FK7E7914A3413BCBD2` FOREIGN KEY (`obm_id`) REFERENCES `obm` (`id`),
  CONSTRAINT `FK7E7914A31235C51D` FOREIGN KEY (`tipoocorrencia_id`) REFERENCES `tiposocorrencias` (`id`),
  CONSTRAINT `FK7E7914A32A076032` FOREIGN KEY (`chamado_id`) REFERENCES `chamados` (`id`),
  CONSTRAINT `FK7E7914A34D8464F2` FOREIGN KEY (`municipio_id`) REFERENCES `municipios` (`id`),
  CONSTRAINT `FK_atendimentos_chamados` FOREIGN KEY (`chamado_id`) REFERENCES `chamados` (`id`),
  CONSTRAINT `FK_atendimentos_municipios` FOREIGN KEY (`municipio_id`) REFERENCES `municipios` (`id`),
  CONSTRAINT `FK_atendimentos_obm` FOREIGN KEY (`obm_id`) REFERENCES `obm` (`id`),
  CONSTRAINT `FK_atendimentos_tiposocorrencias` FOREIGN KEY (`tipoocorrencia_id`) REFERENCES `tiposocorrencias` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Dados de atendimentos realizados';

--
-- Dumping data for table `atendimentos`
--

/*!40000 ALTER TABLE `atendimentos` DISABLE KEYS */;
/*!40000 ALTER TABLE `atendimentos` ENABLE KEYS */;


--
-- Definition of table `avisossistema`
--

DROP TABLE IF EXISTS `avisossistema`;
CREATE TABLE `avisossistema` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(120) NOT NULL DEFAULT '',
  `conteudo` text,
  PRIMARY KEY (`id`)
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
  KEY `FK55502018413BCBD2` (`obm_id`),
  KEY `FK5550201849BB2B9B` (`naturezachamado_id`),
  CONSTRAINT `FK5550201849BB2B9B` FOREIGN KEY (`naturezachamado_id`) REFERENCES `naturezaschamados` (`id`),
  CONSTRAINT `FK55502018413BCBD2` FOREIGN KEY (`obm_id`) REFERENCES `obm` (`id`),
  CONSTRAINT `FK_chamados_naturezaschamados` FOREIGN KEY (`naturezachamado_id`) REFERENCES `naturezaschamados` (`id`),
  CONSTRAINT `FK_chamados_obm` FOREIGN KEY (`obm_id`) REFERENCES `obm` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COMMENT='Registro de chamados de socorro';

--
-- Dumping data for table `chamados`
--

/*!40000 ALTER TABLE `chamados` DISABLE KEYS */;
INSERT INTO `chamados` (`id`,`naturezachamado_id`,`origem`,`nomesolicitante`,`telefonesolicitante`,`numaproxvitimas`,`infocomplementares`,`obm_id`,`horainicio`,`horatermino`) VALUES 
 (1,1,'Telefone','Amanda','89098765',0,'urgente...',1,'2009-03-22 23:41:00','2009-03-22 23:43:00');
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
  KEY `FKA197AF8F4DC4F935` (`atendimento_id`),
  CONSTRAINT `FKA197AF8F4DC4F935` FOREIGN KEY (`atendimento_id`) REFERENCES `atendimentos` (`id`),
  CONSTRAINT `FK_cronoatendimento_atendimentos` FOREIGN KEY (`atendimento_id`) REFERENCES `atendimentos` (`id`)
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
  `datahora` datetime DEFAULT NULL,
  `usuario` varchar(10) DEFAULT NULL,
  `chave` int(10) DEFAULT NULL,
  `operacao` varchar(50) DEFAULT NULL,
  `detalhes` varchar(255) DEFAULT NULL,
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
  `atendimento_id` int(10) NOT NULL DEFAULT '0',
  `viatura_id` int(10) NOT NULL DEFAULT '0',
  `tipoevento` varchar(40) NOT NULL DEFAULT '',
  `horaevento` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  KEY `FK_movimentaviatura_atendimentos` (`atendimento_id`),
  KEY `FK_movimentaviatura_viaturas` (`viatura_id`),
  KEY `FK93D18DB04DC4F935` (`atendimento_id`),
  KEY `FK93D18DB063B96552` (`viatura_id`),
  CONSTRAINT `FK93D18DB063B96552` FOREIGN KEY (`viatura_id`) REFERENCES `viaturas` (`id`),
  CONSTRAINT `FK93D18DB04DC4F935` FOREIGN KEY (`atendimento_id`) REFERENCES `atendimentos` (`id`),
  CONSTRAINT `FK_movimentaviatura_atendimentos` FOREIGN KEY (`atendimento_id`) REFERENCES `atendimentos` (`id`),
  CONSTRAINT `FK_movimentaviatura_viaturas` FOREIGN KEY (`viatura_id`) REFERENCES `viaturas` (`id`)
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
  `municipio_nome` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
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
 (27,'ITABORAÍ'),
 (28,'ITALVA'),
 (29,'ITAOCARA'),
 (30,'ITAPERUNA'),
 (31,'ITATIAIA'),
 (32,'JAPERI'),
 (33,'LAJE DO MURIAÉ'),
 (34,'MACAÉ'),
 (35,'MAGÉ'),
 (36,'MANGARATIBA'),
 (37,'MARICÁ'),
 (38,'MENDES'),
 (39,'MIGUEL PEREIRA'),
 (40,'MIRACEMA'),
 (41,'MESQUITA'),
 (42,'NATIVIDADE'),
 (43,'NILÓPOLIS'),
 (44,'NITERÓI'),
 (45,'NOVA IGUAÇU'),
 (46,'PARACAMBI'),
 (47,'PARAÍBA DO SUL'),
 (48,'PARATI'),
 (49,'PATY DO ALFERES'),
 (50,'PETRÓPOLIS'),
 (51,'PIRAÍ'),
 (52,'PORCIÚNCULA'),
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
 (65,'SÃO GONÇALO'),
 (66,'SÃO JOÃO DA BARRA'),
 (67,'SÃO JOÃO DE MERITI'),
 (68,'SÃO JOSÉ DO VALE DO RIO PRETO'),
 (69,'SÃO PEDRO DA ALDEIA'),
 (70,'SÃO SEBASTIÃO DO ALTO'),
 (71,'SAPUCAIA'),
 (72,'SAQUAREMA'),
 (73,'SEROPÉDICA'),
 (74,'SILVA JARDIM'),
 (75,'TERESÓPOLIS'),
 (76,'TRAJANO DE MORAIS'),
 (77,'TRÊS RIOS'),
 (78,'VALENÇA'),
 (79,'VARRE-SAI'),
 (80,'VASSOURAS'),
 (81,'VOLTA REDONDA'),
 (82,'NOVA FRIBURGO'),
 (83,'MACUCO'),
 (84,'ITAGUAÍ'),
 (85,'SÃO FRANCISCO DE ITABAPOANA'),
 (86,'SUMIDOURO'),
 (87,'ARMAÇÃO DE BÚZIOS'),
 (88,'IGUABA GRANDE'),
 (89,'PINHEIRAL'),
 (90,'PORTO REAL'),
 (91,'SÃO JOSÉ DE UBÁ'),
 (92,'TANGUÁ');
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
  `logradouro` varchar(70) DEFAULT NULL,
  `numcompl` varchar(30) DEFAULT NULL,
  `coordx` double DEFAULT NULL,
  `coordy` double DEFAULT NULL,
  `status_obm` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_obm_municipios` (`municipio_id`),
  KEY `FK1ACFA4D8464F2` (`municipio_id`),
  CONSTRAINT `FK1ACFA4D8464F2` FOREIGN KEY (`municipio_id`) REFERENCES `municipios` (`id`),
  CONSTRAINT `FK_obm_municipios` FOREIGN KEY (`municipio_id`) REFERENCES `municipios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `obm`
--

/*!40000 ALTER TABLE `obm` DISABLE KEYS */;
INSERT INTO `obm` (`id`,`nome`,`municipio_id`,`bairro`,`logradouro`,`numcompl`,`coordx`,`coordy`,`status_obm`) VALUES 
 (1,'COCB',61,'Centro','Praça da Republica','45',-22.95838343909873,-43.200130462646484,1),
 (2,'1º GBM - Humaitá',61,'Humaitá','Rua Humaitá','S/N',-22.95838343909873,-43.200130462646484,1),
 (3,'2º GBM - Méier',61,'Méier','Rua Aristide Caire','56',-22.899699412528445,-43.27735662460327,1),
 (4,'8º GBM - Campinho',61,'Campinho','Rua Domingos Lopes','336',-22.8799215496761,-43.34126830101013,1),
 (5,'11º GBM - Vila Isabel',61,'Vila Isabel','Rua Oito de Dezembro','456',-22.91019503816399,-43.241339921951294,1),
 (6,'12º GBM - Jacarepaguá',61,'Jacarepaguá','Rua Henriqueta','99 - Tanque',-22.919583092024975,-43.3580482006073,1),
 (7,'13º GBM - Campo Grande',61,'Campo Grande','AV. Cesário de Melo','3226',-22.907279668085742,-43.56295824050903,1),
 (8,'17º GBM - Copacabana',61,'Copacabana','Rua Xavier da Silveira','120',-22.976026160554113,-43.19354295730591,1),
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
  KEY `FK523511944DC4F935` (`atendimento_id`),
  KEY `FK523511943C34A59A` (`tiposervico_id`),
  CONSTRAINT `FK523511943C34A59A` FOREIGN KEY (`tiposervico_id`) REFERENCES `tiposservicos` (`id`),
  CONSTRAINT `FK523511944DC4F935` FOREIGN KEY (`atendimento_id`) REFERENCES `atendimentos` (`id`),
  CONSTRAINT `FK_servicos_atendimentos` FOREIGN KEY (`atendimento_id`) REFERENCES `atendimentos` (`id`),
  CONSTRAINT `FK_servicos_tiposservicos` FOREIGN KEY (`tiposervico_id`) REFERENCES `tiposservicos` (`id`)
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1 COMMENT='Tipos de ocorrencias atendidas';

--
-- Dumping data for table `tiposocorrencias`
--

/*!40000 ALTER TABLE `tiposocorrencias` DISABLE KEYS */;
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
  `nomeguerra` varchar(40) DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL,
  `obm_id` int(11) NOT NULL DEFAULT '0',
  `perfil_id` int(11) NOT NULL DEFAULT '0',
  `senha` varchar(50) NOT NULL DEFAULT '',
  `status_usuario` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_usuario_obm` (`obm_id`),
  KEY `FK_usuario_perfil` (`perfil_id`),
  KEY `FKF814F32E6E279288` (`perfil_id`),
  KEY `FKF814F32E413BCBD2` (`obm_id`),
  CONSTRAINT `FKF814F32E413BCBD2` FOREIGN KEY (`obm_id`) REFERENCES `obm` (`id`),
  CONSTRAINT `FKF814F32E6E279288` FOREIGN KEY (`perfil_id`) REFERENCES `perfis` (`id`),
  CONSTRAINT `FK_usuario_obm` FOREIGN KEY (`obm_id`) REFERENCES `obm` (`id`),
  CONSTRAINT `FK_usuario_perfil` FOREIGN KEY (`perfil_id`) REFERENCES `perfis` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usuario`
--

/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`id`,`numregistro`,`nomeguerra`,`email`,`obm_id`,`perfil_id`,`senha`,`status_usuario`) VALUES 
 (1,123,'araujo','tecladista.vando@gmail',1,1,'000','ATIVO'),
 (5,456,'bruno','bruno@gmail.com',1,3,'123','ATIVO'),
 (11,777,'FLAVITCHA','FLAMA',3,1,'987','ATIVO'),
 (15,890,'Carol','carol@gmail.com',1,2,'000','ATIVO'),
 (16,891,'abobrinha','kjsjhskjd5',1,4,'8787','ATIVO'),
 (17,892,'Luciana','lu',1,5,'123','ATIVO'),
 (18,876,'Ronaldo','ronaldo@gmail.com',2,3,'123','ATIVO'),
 (19,990,'Flavia','flavia@gmail.com',1,2,'123','ATIVO'),
 (20,0,'abobora','1',1,1,'0','ATIVO');
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
  KEY `FK40589EB56C365DB2` (`tipoviatura_id`),
  KEY `FK40589EB5413BCBD2` (`obm_id`),
  CONSTRAINT `FK40589EB5413BCBD2` FOREIGN KEY (`obm_id`) REFERENCES `obm` (`id`),
  CONSTRAINT `FK40589EB56C365DB2` FOREIGN KEY (`tipoviatura_id`) REFERENCES `tiposviaturas` (`id`),
  CONSTRAINT `FK_viaturas_obm` FOREIGN KEY (`obm_id`) REFERENCES `obm` (`id`),
  CONSTRAINT `FK_viaturas_tiposviaturas` FOREIGN KEY (`tipoviatura_id`) REFERENCES `tiposviaturas` (`id`)
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
  `situacaofinal` int(1) NOT NULL DEFAULT '0',
  `hospitaldestino` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_vitimas_atendimentos` (`atendimento_id`),
  KEY `FK1BE02FF74DC4F935` (`atendimento_id`),
  CONSTRAINT `FK1BE02FF74DC4F935` FOREIGN KEY (`atendimento_id`) REFERENCES `atendimentos` (`id`),
  CONSTRAINT `FK_vitimas_atendimentos` FOREIGN KEY (`atendimento_id`) REFERENCES `atendimentos` (`id`)
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
