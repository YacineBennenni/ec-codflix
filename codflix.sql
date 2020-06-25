-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3308
-- Généré le :  jeu. 25 juin 2020 à 16:54
-- Version du serveur :  8.0.18
-- Version de PHP :  7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `codflix`
--

-- --------------------------------------------------------

--
-- Structure de la table `episode`
--

DROP TABLE IF EXISTS `episode`;
CREATE TABLE IF NOT EXISTS `episode` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `media_id` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `saison` int(11) NOT NULL,
  `num_episode` int(11) NOT NULL,
  `time` int(11) NOT NULL,
  `summary` longtext NOT NULL,
  `trailer_url` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `episode_genre_id_fk_media_id` (`media_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `episode`
--

INSERT INTO `episode` (`id`, `media_id`, `title`, `saison`, `num_episode`, `time`, `summary`, `trailer_url`) VALUES
(1, 6, 'Au commencement…', 1, 1, 44, 'Quand une de ses vieilles connaissances connaît une fin violente, le charmant Lucifer s\'engage à la venger et noue une alliance inattendue.', 'https://www.youtube.com/embed/ba-hQluHyYk'),
(2, 6, 'Maman où t\'es ?', 2, 1, 43, 'Alors qu\'elle enquête sur le meurtre d\'une actrice doublure, Chloe tente de résoudre une fois pour toutes le mystère de l\'origine de Lucifer.', 'https://www.youtube.com/embed/ba-hQluHyYk'),
(3, 6, 'Nouveau corps pour une nouvelle vie', 2, 2, 44, 'Lucifer soupçonne sa mère arrivée récemment d\'être derrière un crime odieux. Puis il en apprend un peu plus sur son histoire infernale.', 'https://www.youtube.com/embed/ba-hQluHyYk'),
(4, 6, 'Lucifer, à la niche', 1, 2, 43, 'Son retour en enfer approche, mais Lucifer prend le temps d\'enquêter sur la mort d\'un jeune homme causée par un paparazzi trop zélé.', 'https://www.youtube.com/embed/ba-hQluHyYk');

-- --------------------------------------------------------

--
-- Structure de la table `genre`
--

DROP TABLE IF EXISTS `genre`;
CREATE TABLE IF NOT EXISTS `genre` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `genre`
--

INSERT INTO `genre` (`id`, `name`) VALUES
(1, 'Action'),
(2, 'Horreur'),
(3, 'Science-Fiction'),
(4, 'Animation');

-- --------------------------------------------------------

--
-- Structure de la table `history`
--

DROP TABLE IF EXISTS `history`;
CREATE TABLE IF NOT EXISTS `history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `media_id` int(11) NOT NULL,
  `start_date` datetime NOT NULL,
  `finish_date` datetime DEFAULT NULL,
  `watch_duration` int(11) NOT NULL DEFAULT '0' COMMENT 'in seconds',
  PRIMARY KEY (`id`),
  KEY `history_user_id_fk_media_id` (`user_id`),
  KEY `history_media_id_fk_media_id` (`media_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `media`
--

DROP TABLE IF EXISTS `media`;
CREATE TABLE IF NOT EXISTS `media` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `genre_id` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `type` varchar(20) NOT NULL,
  `status` varchar(20) NOT NULL,
  `release_date` date NOT NULL,
  `summary` longtext NOT NULL,
  `trailer_url` varchar(100) NOT NULL,
  `time` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `media_genre_id_fk_genre_id` (`genre_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `media`
--

INSERT INTO `media` (`id`, `genre_id`, `title`, `type`, `status`, `release_date`, `summary`, `trailer_url`, `time`) VALUES
(1, 3, 'Projet X ', 'film', 'Media publie', '2012-06-20', 'Alors qu\'ils semblaient jusque-là se fondre dans la masse, trois lycéens décident de sortir de l\'anonymat. En apparence, leur projet est plutôt inoffensif puisqu\'ils ont l\'intention d\'organiser une fête des plus mémorables. Mais rien n\'aurait pu les préparer à la soirée qu\'ils s\'apprêtent à vivre... La rumeur se propage alors rapidement, tandis que les rêves des uns s\'effondrent, les résultats scolaires des autres dégringolent, et des légendes se forgent… ', 'https://www.youtube.com/embed/Kfm4Z4C2NRo', 87),
(2, 4, 'One piece : Stampede', 'film', 'Media publie', '2019-09-12', 'Luffy et son équipage s’apprêtent à participer au plus grand rassemblement des pirates du monde entier : Le PIRATE FEST, organisé par le machiavélique Buena Festa. Les Pirates, les grands corsaires, la Marine et même l’Armée Révolutionnaire s’y retrouvent pour tenter de découvrir le trésor si convoité de Gol.D.Roger. Mais pour cela, ils vont devoir combattre un ancien membre de l’équipage de Roger, Douglas Bullet, aussi appelé « l’héritier du démon ». Au cours de cet affrontement qui s’annonce hors du commun, les pires dangers attendent les Chapeaux de paille et d’étonnantes alliances pourraient voir le jour…', 'https://www.youtube.com/embed/S8_YwFLCh4U', 101),
(3, 4, 'Naruto : The last', 'film', 'Media publie', '2013-05-13', 'Dans le village de Konoha, deux années après la 4ème grande guerre des ninjas, Naruto s’apprête à célébrer la fête de l’hiver entouré d’Hinata et de ses amis. Soudain, une météorite déchire la nuit de son intense lumière et voilà que la lune se rapproche anormalement de la Terre. Le Conseil des Kage se réunit en urgence  face à cette menace de destruction de la planète ! Pour Naruto, Sakura, Shikamaru, Saï et Hinata le compte à rebours pour sauver la planète a commencé. ', 'https://www.youtube.com/embed/tA3yE4_t6SY', 117),
(4, 3, 'Matrix', 'film', 'Media publie', '1999-05-24', 'Programmeur anonyme dans un service administratif le jour, Thomas Anderson devient Neo la nuit venue. Sous ce pseudonyme, il est l\'un des pirates les plus recherchés du cyber-espace. A cheval entre deux mondes, Neo est assailli par d\'étranges songes et des messages cryptés provenant d\'un certain Morpheus. Celui-ci l\'exhorte à aller au-delà des apparences et à trouver la réponse à la question qui hante constamment ses pensées : qu\'est-ce que la Matrice ? Nul ne le sait, et aucun homme n\'est encore parvenu à en percer les defenses. Mais Morpheus est persuadé que Neo est l\'Elu, le libérateur mythique de l\'humanité annoncé selon la prophétie. Ensemble, ils se lancent dans une lutte sans retour contre la Matrice et ses terribles agents... ', 'https://www.youtube.com/embed/XL2fCRd3c_s', 155),
(5, 1, 'John Wick ', 'film', 'Media publie', '2014-10-29', 'Depuis la mort de sa femme bien-aimée, John Wick passe ses journées à retaper sa Ford Mustang de 1969, avec pour seule compagnie sa chienne Daisy. Il mène une vie sans histoire, jusqu’à ce qu’un malfrat sadique nommé Iosef Tarasof remarque sa voiture. John refuse de la lui vendre. Iosef n’acceptant pas qu’on lui résiste, s’introduit chez John avec deux complices pour voler la Mustang, et tuer sauvagement Daisy…\r\nJohn remonte la piste de Iosef jusqu’à New York. Un ancien contact, Aurelio, lui apprend que le malfrat est le fils unique d’un grand patron de la pègre, Viggo Tarasof. La rumeur se répand rapidement dans le milieu : le légendaire tueur cherche Iosef. Viggo met à prix la tête de John : quiconque l’abattra touchera une énorme récompense. John a désormais tous les assassins de New York aux trousses.', 'https://www.youtube.com/embed/pWK5crMuhHY', 101),
(6, 3, 'Lucifer', 'serie', 'Media publie', '2016-01-25', 'Lassé et mécontent de sa position de \"Seigneur des Enfers\", Lucifer Morningstar démissionne et abandonne le trône de son royaume pour la bouillonnante et non moins impure Los Angeles. Dans la Cité des anges, l\'ex maître diabolique est le patron d\'un nightclub baptisé \"Lux\". Quand une star de la Pop est sauvagement assassinée sous ses yeux, il décide de partir à la recherche du coupable et croise sur sa route Chloe Decker, une femme flic qui résiste à ses charmes et lui met constamment des bâtons dans les roues.\r\n\r\nAlors que l\'improbable duo s\'entraide pour venir à bout de l\'enquête, l\'ange Amenadiel est envoyé à Los Angeles par Dieu pour tenter de convaincre Lucifer de regagner son royaume. L\'ancien Seigneur des Enfers cèdera-t-il aux sirènes du Mal qui l\'appellent ou se laissera-t-il tenter par le Bien, vers lequel l\'inspecteur Chloe Decker semble peu à peu l\'amener ?', 'https://www.youtube.com/embed/ba-hQluHyYk', 0);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(254) NOT NULL,
  `password` varchar(80) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `email`, `password`) VALUES
(1, 'coding@gmail.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `episode`
--
ALTER TABLE `episode`
  ADD CONSTRAINT `media_id_fk_media_id` FOREIGN KEY (`media_id`) REFERENCES `media` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Contraintes pour la table `history`
--
ALTER TABLE `history`
  ADD CONSTRAINT `history_media_id_fk_media_id` FOREIGN KEY (`media_id`) REFERENCES `media` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `history_user_id_fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `media`
--
ALTER TABLE `media`
  ADD CONSTRAINT `media_genre_id_b1257088_fk_genre_id` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
