SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for menu_category
-- ----------------------------
DROP TABLE IF EXISTS `demo`;
CREATE TABLE `demo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `price` decimal(7, 2) DEFAULT NULL,
  `time` date NOT NULL,
  `name` varchar(255) NOT NULL,
  `active` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci;