-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : lun. 26 juin 2023 à 12:57
-- Version du serveur : 8.0.31
-- Version de PHP : 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `spring_db`
--

CREATE DATABASE IF NOT EXISTS spring_db;
USE spring_db;

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

DROP TABLE IF EXISTS `categorie`;
CREATE TABLE IF NOT EXISTS `categorie` (
  `id_cat` bigint NOT NULL AUTO_INCREMENT,
  `description_cat` varchar(255) DEFAULT NULL,
  `nom_cat` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_cat`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `categorie`
--

INSERT INTO `categorie` (`id_cat`, `description_cat`, `nom_cat`) VALUES
(1, 'Tout les PC', 'PC'),
(2, 'Toutes les imprimantes', 'Imprimantes'),
(3, 'Tout les ', 'pc');

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

DROP TABLE IF EXISTS `produit`;
CREATE TABLE IF NOT EXISTS `produit` (
  `id_produit` bigint NOT NULL AUTO_INCREMENT,
  `date_creation` datetime(6) DEFAULT NULL,
  `nom_produit` varchar(255) DEFAULT NULL,
  `prix_produit` double DEFAULT NULL,
  `categorie_id_cat` bigint DEFAULT NULL,
  PRIMARY KEY (`id_produit`),
  KEY `FKsu6ikhfh3e1shoow8pb5v2yie` (`categorie_id_cat`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`id_produit`, `date_creation`, `nom_produit`, `prix_produit`, `categorie_id_cat`) VALUES
(1, '2023-06-23 09:27:27.000000', 'PC ASUS', 1200, 1),
(2, '2023-06-23 09:27:27.000000', 'Imprimante HP', 500, 2),
(4, '2023-06-26 00:00:00.000000', 'iphone 15', 1, 2);

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`id`, `nom`) VALUES
(1, 'ROLE_UTILISATEUR'),
(2, 'ROLE_ADMINISTRATEUR'),
(3, 'ROLE_SUPER_ADMINISTRATEUR');

-- --------------------------------------------------------

--
-- Structure de la table `role_utilisateur`
--

DROP TABLE IF EXISTS `role_utilisateur`;
CREATE TABLE IF NOT EXISTS `role_utilisateur` (
  `utilisateur_id` int NOT NULL,
  `role_id` int NOT NULL,
  KEY `FKtqsv7mkho8vm7i3x5n65a0fr2` (`role_id`),
  KEY `FKey0mbtbkylluasjnxhfrt042u` (`utilisateur_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `role_utilisateur`
--

INSERT INTO `role_utilisateur` (`utilisateur_id`, `role_id`) VALUES
(1, 1),
(2, 1),
(2, 2),
(3, 3);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `nom_image_profil` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `created_at`, `email`, `nom`, `nom_image_profil`, `password`, `prenom`, `updated_at`) VALUES
(1, '2023-01-01', 'jd@a.com', 'doe', NULL, '$2a$10$wXW2wHA2bu1TdQ26p.2UoehWv8m92w88kabSeL.348VqkpWvSt51q', 'John', '2023-01-03 00:00:00.000000'),
(2, '2023-06-26', 'fb@a.com', 'bansept', NULL, '$2a$10$wXW2wHA2bu1TdQ26p.2UoehWv8m92w88kabSeL.348VqkpWvSt51q', 'Franck', '2023-06-26 11:40:49.000000'),
(3, '2023-06-26', 'as@a.com', 'Super', NULL, '$2a$10$wXW2wHA2bu1TdQ26p.2UoehWv8m92w88kabSeL.348VqkpWvSt51q', 'Admin', '2023-06-26 11:40:49.000000');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `produit`
--
ALTER TABLE `produit`
  ADD CONSTRAINT `FKsu6ikhfh3e1shoow8pb5v2yie` FOREIGN KEY (`categorie_id_cat`) REFERENCES `categorie` (`id_cat`);

--
-- Contraintes pour la table `role_utilisateur`
--
ALTER TABLE `role_utilisateur`
  ADD CONSTRAINT `FKey0mbtbkylluasjnxhfrt042u` FOREIGN KEY (`utilisateur_id`) REFERENCES `utilisateur` (`id`),
  ADD CONSTRAINT `FKtqsv7mkho8vm7i3x5n65a0fr2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
