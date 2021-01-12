CREATE DATABASE IF NOT EXISTS `language_memo`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `sentence`;

CREATE TABLE `sentence`(
                `id` BIGINT NOT NULL AUTO_INCREMENT,
                `language_eng` VARCHAR (32) NOT NULL,
                `language_pol` VARCHAR (32) NOT NULL,
                `hint_from_native` VARCHAR (128) DEFAULT NULL,
                `hint_to_native` VARCHAR (128) DEFAULT NULL,
                `replay_level_from_native` INTEGER DEFAULT 0,
                `replay_level_to_native` INTEGER DEFAULT 0,
                `replay_date_from_native` DATE NOT NULL,
                `replay_date_to_native` DATE NOT NULL,
                `date_created` DATE NOT NULL,
                PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO `sentence` VALUES
(1, 'home', 'dom', 'miejsce, nie budynek', 'znasz', 1, 1, '2019-12-15', '2019-12-15', '2019-12-15'),
(2, 'field', 'pole', 'miejsce do okreslania zmiennych', 'na roli', 1, 1, '2019-12-14', '2019-12-15', '2019-12-15'),
(3, 'granted', 'zgoda', 'inaczej pozwolenie(pol)', 'po kłótni', 1, 1, '2019-12-14', '2019-12-15', '2019-12-15'),
(4, 'suite', 'zestaw', 'pisze się podobnie jak stroj', '... sztućców', 1, 1, '2019-12-15', '2019-12-15', '2019-12-15');

SET FOREIGN_KEY_CHECKS = 1;