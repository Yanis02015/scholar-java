-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  ven. 26 fév. 2021 à 00:28
-- Version du serveur :  10.4.10-MariaDB
-- Version de PHP :  7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+01:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `scholar`
--

-- --------------------------------------------------------

--
-- Structure de la table `article`
--

DROP TABLE IF EXISTS `article`;
CREATE TABLE IF NOT EXISTS `article` (
  `idArticle` int(11) NOT NULL AUTO_INCREMENT,
  `titre` varchar(100) NOT NULL,
  `nbPages` int(2) NOT NULL CHECK (`nbPages` > 0 and `nbPages` < 32),
  `contenu` text NOT NULL,
  `type` varchar(10) NOT NULL CHECK (`type` in ('Revue','Conférence')),
  `nomR` varchar(30) DEFAULT NULL,
  `facteurImpact` float DEFAULT NULL CHECK (`facteurImpact` > 0 and `facteurImpact` < 5),
  `nomC` varchar(30) DEFAULT NULL,
  `lieu` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idArticle`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `auteur`
--

DROP TABLE IF EXISTS `auteur`;
CREATE TABLE IF NOT EXISTS `auteur` (
  `idAuteur` int(11) NOT NULL AUTO_INCREMENT,
  `nomA` varchar(50) NOT NULL,
  `prenomA` varchar(50) NOT NULL,
  `email` varchar(320) NOT NULL,
  `idUniversite` int(11) NOT NULL,
  PRIMARY KEY (`idAuteur`),
  KEY `idUniversite` (`idUniversite`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `rediger`
--

DROP TABLE IF EXISTS `rediger`;
CREATE TABLE IF NOT EXISTS `rediger` (
  `idAuteur` int(11) NOT NULL,
  `idArticle` int(11) NOT NULL,
  PRIMARY KEY (`idAuteur`,`idArticle`),
  KEY `idArticle` (`idArticle`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `universite`
--

DROP TABLE IF EXISTS `universite`;
CREATE TABLE IF NOT EXISTS `universite` (
  `idUniversite` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `siteweb` varchar(50) DEFAULT NULL,
  `adresse` varchar(140) NOT NULL,
  PRIMARY KEY (`idUniversite`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `auteur`
--
ALTER TABLE `auteur`
  ADD CONSTRAINT `auteur_ibfk_1` FOREIGN KEY (`idUniversite`) REFERENCES `universite` (`idUniversite`);

--
-- Contraintes pour la table `rediger`
--
ALTER TABLE `rediger`
  ADD CONSTRAINT `FK__ConstraintDe__AUTEUR` FOREIGN KEY (`idAuteur`) REFERENCES `auteur` (`idAuteur`),
  ADD CONSTRAINT `FK__ConstraintDe__ARTICLE` FOREIGN KEY (`idArticle`) REFERENCES `article` (`idArticle`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
