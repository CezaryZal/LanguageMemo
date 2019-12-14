CREATE DATABASE IF NOT EXISTS `language_memo`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `sentence`;

CREATE TABLE `sentence`(
                `id` BIGINT NOT NULL AUTO_INCREMENT,
                `language_eng` VARCHAR (45) NOT NULL,
                `language_pol` VARCHAR (45) NOT NULL,
                `hint` VARCHAR (128) DEFAULT NULL,
                `replay_level` INTEGER DEFAULT 0,
                `replay_date` DATE NOT NULL,
                PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO `sentence` VALUES
(1, 'home', 'dom', 'miejsce, nie budynek', 1, '2019-12-15'),
(2, 'field', 'pole', 'miejsce do okreslania zmiennych', 1, '2019-12-14'),
(3, 'granted', 'zgoda', 'inaczej pozwolenie(pol)', 1, '2019-12-14'),
(4, 'suite', 'zestaw', 'pisze się podobnie jak stroj', 1, '2019-12-15');

SET FOREIGN_KEY_CHECKS = 1;