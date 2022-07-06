CREATE DATABASE IF NOT EXISTS `language_memo`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `sentence`;

CREATE TABLE `sentence`(
                `id` BIGINT NOT NULL AUTO_INCREMENT,
                `clues` VARCHAR (32) NOT NULL,
                `correct_answer` VARCHAR (32) NOT NULL,
                `hint` VARCHAR (128) DEFAULT NULL,
                `example_of_use` VARCHAR (128) DEFAULT NULL,
                `part_of_speech_clues` VARCHAR (32) DEFAULT NULL,
                `similar_word` VARCHAR (128) DEFAULT NULL,
                `replay_level` INTEGER DEFAULT 0,
                `replay_date` DATE NOT NULL,
                `date_created` DATE NOT NULL,
                PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO `sentence` VALUES
(1, 'dom', 'house', 'miejsce, nie budynek', 'That\'s the house where Tom was born', 'noun', null, 0, '2021-12-15', '2019-12-15'),
(2, 'pole', 'field', 'miejsce do okreslania zmiennych lub na roli', 'The football field is enclosed by a wall', 'noun', null, 1, '2021-12-18', '2019-12-15'),
(3, 'zapewniać', 'provide', 'podobnie jak udowodnić (ang)', 'The store provides its customers with excellent service', 'verb', 'provided;',1, '2021-12-18', '2019-12-15'),
(4, 'dostarczać', 'provide', 'podobnie nieco do prywatnego', 'Employees provide products to our store', 'verb', 'provided;',1, '2021-12-18', '2019-12-15'),
(5, 'zaopatrzony', 'provided', 'podobnie jak zapewniony (ang), tylko w czasie przeszłym', 'The school provided the band with new uniforms', 'verb', 'provide;',1, '2021-12-18', '2019-12-15');

SET FOREIGN_KEY_CHECKS = 1;