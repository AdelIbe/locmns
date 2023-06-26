INSERT INTO `role` (`id`, `nom`) VALUES
(1, 'ROLE_UTILISATEUR'),
(2, 'ROLE_ADMINISTRATEUR'),
(3, 'ROLE_SUPER_ADMINISTRATEUR');

INSERT INTO utilisateur(prenom, nom, email, password, created_at, updated_at)
VALUES
("John","doe","jd@a.com","$2a$10$wXW2wHA2bu1TdQ26p.2UoehWv8m92w88kabSeL.348VqkpWvSt51q","2023-01-01","2023-01-03"),
("Franck","bansept","fb@a.com","$2a$10$wXW2wHA2bu1TdQ26p.2UoehWv8m92w88kabSeL.348VqkpWvSt51q",UTC_TIMESTAMP(),UTC_TIMESTAMP()),
("Admin","Super","as@a.com","$2a$10$wXW2wHA2bu1TdQ26p.2UoehWv8m92w88kabSeL.348VqkpWvSt51q",UTC_TIMESTAMP(),UTC_TIMESTAMP());

INSERT INTO role_utilisateur (utilisateur_id, role_id) VALUES
(1, 1),
(2, 1),
(2, 2),
(3, 3);

INSERT INTO `categorie` (`id_cat`, `description_cat`, `nom_cat`) VALUES (NULL, 'Tout les PC', 'PC'), (NULL, 'Toutes les imprimantes', 'Imprimantes');

INSERT INTO `produit` (`id_produit`, `date_creation`, `nom_produit`, `prix_produit`, `categorie_id_cat`) VALUES
(1, '2023-06-23 09:27:27.000000', 'PC ASUS', 1200, 1),
(2, '2023-06-23 09:27:27.000000', 'Imprimante HP', 500, 2);
