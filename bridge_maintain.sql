/*
 Navicat Premium Data Transfer

 Source Server         : db1
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : bridge_maintain

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 12/07/2021 23:25:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bridge
-- ----------------------------
DROP TABLE IF EXISTS `bridge`;
CREATE TABLE `bridge`  (
  `bridge_id` bigint NOT NULL AUTO_INCREMENT,
  `abutment_num` int NOT NULL,
  `bridge_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `build_unit` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `build_year` date NOT NULL,
  `total_length` double(10, 3) NOT NULL,
  `design_load` double(10, 3) NOT NULL,
  `maintain_class` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `maintain_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `maintain_unit` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `manage_unit` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `max_waterline` double(10, 3) NOT NULL,
  `pier_num` int NOT NULL,
  `road_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `road_width` double(10, 3) NOT NULL,
  `sidewalk_width` double(10, 3) NOT NULL,
  `span_num` int NOT NULL,
  `watch_unit` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `total_width` double(10, 3) NOT NULL,
  `type_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`bridge_id`) USING BTREE,
  INDEX `FKr5u4y0k4td2yufv4jajx3dp6g`(`type_id`) USING BTREE,
  CONSTRAINT `FKr5u4y0k4td2yufv4jajx3dp6g` FOREIGN KEY (`type_id`) REFERENCES `bridge_type` (`type_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1000009 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '桥梁实体' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bridge
-- ----------------------------
INSERT INTO `bridge` VALUES (1000001, 10, '东水门长江大桥', '中建一局', '2021-07-02', 789.200, 542.000, '5', '1', '养护一号单位', '管理一号单位', 45.600, 15, '江都路', 35.700, 15.600, 3, '监管一号单位', 45.800, 1);
INSERT INTO `bridge` VALUES (1000002, 3, '卢沟桥', '川建一局', '2021-07-08', 151.000, 61651.000, '2', '1', '养护三号单位', '管理二号单位', 1651654.000, 65465, '玉龙路', 4151.000, 156.000, 2, '监管一号单位', 45684.000, 3);
INSERT INTO `bridge` VALUES (1000003, 2, '赵州桥', '中建一局', '2021-07-09', 1561.000, 1651561.000, '', '1', '养护一号单位', '管理二号单位', 4654.000, 15, '学府路', 48456.000, 4654.000, 5, '监管一号单位', 321316.000, 4);
INSERT INTO `bridge` VALUES (1000004, 1, '鄂丰桥', '中建二局', '2021-07-07', 1.000, 1.000, '1', '1', '养护二号单位', '管理一号单位', 1.000, 1, '干将路', 1.000, 15.000, 1, '监管一号单位', 1.000, 5);
INSERT INTO `bridge` VALUES (1000005, 6, '奈何桥', '川建二局', '2021-07-06', 12345.000, 132.000, '3', '1', '养护一号单位', '管理一号单位', 1235.000, 3, '黄泉路', 6526.000, 12331.000, 2, '监管一号单位', 516165.000, 4);
INSERT INTO `bridge` VALUES (1000006, 15, '港珠澳大桥', '中建一局', '2019-11-29', 1234567.000, 12345.000, '5', '1', '养护一号单位', '管理一号单位', 1234567.000, 132, '东海路', 1234567.000, 123456.000, 1, '监管一号单位', 1234567.000, 1);

-- ----------------------------
-- Table structure for bridge_type
-- ----------------------------
DROP TABLE IF EXISTS `bridge_type`;
CREATE TABLE `bridge_type`  (
  `type_id` bigint NOT NULL AUTO_INCREMENT,
  `type_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`type_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '桥梁类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bridge_type
-- ----------------------------
INSERT INTO `bridge_type` VALUES (1, '梁式桥');
INSERT INTO `bridge_type` VALUES (2, '拱桥');
INSERT INTO `bridge_type` VALUES (3, '悬臂+挂梁桥');
INSERT INTO `bridge_type` VALUES (4, '刚构桥');
INSERT INTO `bridge_type` VALUES (5, '桁架桥');
INSERT INTO `bridge_type` VALUES (6, '人行天桥');

-- ----------------------------
-- Table structure for bridgetype_part
-- ----------------------------
DROP TABLE IF EXISTS `bridgetype_part`;
CREATE TABLE `bridgetype_part`  (
  `type_part_id` bigint NOT NULL AUTO_INCREMENT,
  `type_id` bigint NULL DEFAULT NULL,
  `part_id` bigint NULL DEFAULT NULL,
  `weight` double NULL DEFAULT NULL,
  PRIMARY KEY (`type_part_id`) USING BTREE,
  INDEX `FK2awe1fxebmf310f09fxany9ch`(`type_id`) USING BTREE,
  INDEX `FKshmv0shrvhhiqsb16g3vkp15w`(`part_id`) USING BTREE,
  CONSTRAINT `FK2awe1fxebmf310f09fxany9ch` FOREIGN KEY (`type_id`) REFERENCES `bridge_type` (`type_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKshmv0shrvhhiqsb16g3vkp15w` FOREIGN KEY (`part_id`) REFERENCES `part` (`part_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '类型和部位的关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bridgetype_part
-- ----------------------------
INSERT INTO `bridgetype_part` VALUES (1, 1, 1, 0.1);
INSERT INTO `bridgetype_part` VALUES (2, 1, 2, 0.45);
INSERT INTO `bridgetype_part` VALUES (3, 1, 3, 0.45);
INSERT INTO `bridgetype_part` VALUES (4, 2, 1, 0.15);
INSERT INTO `bridgetype_part` VALUES (5, 2, 2, 0.4);
INSERT INTO `bridgetype_part` VALUES (6, 2, 3, 0.45);

-- ----------------------------
-- Table structure for check_item
-- ----------------------------
DROP TABLE IF EXISTS `check_item`;
CREATE TABLE `check_item`  (
  `item_id` bigint NOT NULL AUTO_INCREMENT,
  `item_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`item_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '日常检测项表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of check_item
-- ----------------------------
INSERT INTO `check_item` VALUES (1, '桥名牌');
INSERT INTO `check_item` VALUES (2, '限高牌、限载牌');
INSERT INTO `check_item` VALUES (3, '车行道');
INSERT INTO `check_item` VALUES (4, '人行道');
INSERT INTO `check_item` VALUES (5, '伸缩缝');
INSERT INTO `check_item` VALUES (6, '栏杆');
INSERT INTO `check_item` VALUES (7, '排水设施');
INSERT INTO `check_item` VALUES (8, '桥路连接位置');
INSERT INTO `check_item` VALUES (9, '上部结构');
INSERT INTO `check_item` VALUES (10, '支座');
INSERT INTO `check_item` VALUES (11, '下部结构');

-- ----------------------------
-- Table structure for component
-- ----------------------------
DROP TABLE IF EXISTS `component`;
CREATE TABLE `component`  (
  `component_id` bigint NOT NULL AUTO_INCREMENT,
  `component_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `part_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`component_id`) USING BTREE,
  INDEX `FK4sd4nh7m3dce0rkj0jvbh2xel`(`part_id`) USING BTREE,
  CONSTRAINT `FK4sd4nh7m3dce0rkj0jvbh2xel` FOREIGN KEY (`part_id`) REFERENCES `part` (`part_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '构件类型' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of component
-- ----------------------------
INSERT INTO `component` VALUES (1, '桥面铺装', 1);
INSERT INTO `component` VALUES (2, '排水系统', 1);
INSERT INTO `component` VALUES (3, '桥头平顺', 1);
INSERT INTO `component` VALUES (4, '人行道', 1);
INSERT INTO `component` VALUES (5, '伸缩装置', 1);
INSERT INTO `component` VALUES (6, '栏杆或护栏', 1);
INSERT INTO `component` VALUES (7, '主梁', 2);
INSERT INTO `component` VALUES (8, '横向联系', 2);
INSERT INTO `component` VALUES (9, '盖梁', 3);
INSERT INTO `component` VALUES (10, '墩身', 3);
INSERT INTO `component` VALUES (11, '基础', 3);
INSERT INTO `component` VALUES (12, '支座', 3);

-- ----------------------------
-- Table structure for daily_check
-- ----------------------------
DROP TABLE IF EXISTS `daily_check`;
CREATE TABLE `daily_check`  (
  `check_id` bigint NOT NULL AUTO_INCREMENT,
  `comments` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `daily_date` date NULL DEFAULT NULL,
  `bridge_id` bigint NULL DEFAULT NULL,
  `user_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`check_id`) USING BTREE,
  INDEX `FKrfow5adpl6d0agu0idv0yjjvk`(`bridge_id`) USING BTREE,
  INDEX `FKed43xly4m36px12a8ecj7nqos`(`user_id`) USING BTREE,
  CONSTRAINT `FKed43xly4m36px12a8ecj7nqos` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKrfow5adpl6d0agu0idv0yjjvk` FOREIGN KEY (`bridge_id`) REFERENCES `bridge` (`bridge_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '日常检测表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of daily_check
-- ----------------------------
INSERT INTO `daily_check` VALUES (1, '很好', '2021-07-05', 1000001, 1);
INSERT INTO `daily_check` VALUES (2, '不错', '2021-07-08', 1000004, 4);
INSERT INTO `daily_check` VALUES (3, '一般', '2021-07-08', 1000001, 4);

-- ----------------------------
-- Table structure for daily_item
-- ----------------------------
DROP TABLE IF EXISTS `daily_item`;
CREATE TABLE `daily_item`  (
  `daily_item_id` bigint NOT NULL AUTO_INCREMENT,
  `damage_num` int NULL DEFAULT NULL,
  `damage_position` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_well` bit(1) NULL DEFAULT NULL,
  `item_id` bigint NULL DEFAULT NULL,
  `check_id` bigint NULL DEFAULT NULL,
  `comments` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`daily_item_id`) USING BTREE,
  INDEX `FKof5b7tjwmi1dnphqhf0ly40q1`(`item_id`) USING BTREE,
  INDEX `FK6s9olc7tu7rp34rg6emaekk5n`(`check_id`) USING BTREE,
  CONSTRAINT `FK6s9olc7tu7rp34rg6emaekk5n` FOREIGN KEY (`check_id`) REFERENCES `daily_check` (`check_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKof5b7tjwmi1dnphqhf0ly40q1` FOREIGN KEY (`item_id`) REFERENCES `check_item` (`item_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '日常检测项和检测表的中间关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of daily_item
-- ----------------------------

-- ----------------------------
-- Table structure for mainmenu
-- ----------------------------
DROP TABLE IF EXISTS `mainmenu`;
CREATE TABLE `mainmenu`  (
  `mid` int NOT NULL AUTO_INCREMENT,
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`mid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 300 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mainmenu
-- ----------------------------
INSERT INTO `mainmenu` VALUES (100, 'iconfont icon-permissions', '/admin', '用户管理');
INSERT INTO `mainmenu` VALUES (200, 'iconfont icon-bridge', '/bridge', '桥梁资料');
INSERT INTO `mainmenu` VALUES (300, 'iconfont icon-edit', '/check', '检测记录');

-- ----------------------------
-- Table structure for part
-- ----------------------------
DROP TABLE IF EXISTS `part`;
CREATE TABLE `part`  (
  `part_id` bigint NOT NULL AUTO_INCREMENT,
  `part_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`part_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '桥梁部位' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of part
-- ----------------------------
INSERT INTO `part` VALUES (1, '桥面系');
INSERT INTO `part` VALUES (2, '上部结构');
INSERT INTO `part` VALUES (3, '下部结构');

-- ----------------------------
-- Table structure for regular_check
-- ----------------------------
DROP TABLE IF EXISTS `regular_check`;
CREATE TABLE `regular_check`  (
  `regular_id` bigint NOT NULL AUTO_INCREMENT,
  `bci_value` double NULL DEFAULT NULL,
  `bsi_value` double NULL DEFAULT NULL,
  `regular_date` date NULL DEFAULT NULL,
  `bridge_id` bigint NULL DEFAULT NULL,
  `user_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`regular_id`) USING BTREE,
  INDEX `FK4vkhxm45tk48ugk7yvkemjv9s`(`bridge_id`) USING BTREE,
  INDEX `FKrr4pwwd4kbff3xyy2n8nn95p2`(`user_id`) USING BTREE,
  CONSTRAINT `FK4vkhxm45tk48ugk7yvkemjv9s` FOREIGN KEY (`bridge_id`) REFERENCES `bridge` (`bridge_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKrr4pwwd4kbff3xyy2n8nn95p2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 145 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定期检测表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of regular_check
-- ----------------------------
INSERT INTO `regular_check` VALUES (137, 98.19583333333334, 95, '2021-07-10', 1000001, 4);
INSERT INTO `regular_check` VALUES (138, 87.21875, 60, '2021-07-10', 1000001, 4);
INSERT INTO `regular_check` VALUES (143, 98.20833333333333, 90, '2021-07-10', 1000001, 4);
INSERT INTO `regular_check` VALUES (144, 97.69583333333334, 85, '2021-07-10', 1000001, 4);
INSERT INTO `regular_check` VALUES (145, 98.22916666666667, 95, '2021-07-10', 1000001, 4);

-- ----------------------------
-- Table structure for regular_component
-- ----------------------------
DROP TABLE IF EXISTS `regular_component`;
CREATE TABLE `regular_component`  (
  `regular_component_id` bigint NOT NULL AUTO_INCREMENT,
  `value` double NULL DEFAULT NULL,
  `regular_id` bigint NULL DEFAULT NULL,
  `type_component_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`regular_component_id`) USING BTREE,
  INDEX `FK5f9r1r91ey1si4ajtwh3sr1jo`(`regular_id`) USING BTREE,
  INDEX `FKvketojee2hif0n8ascxtru3s`(`type_component_id`) USING BTREE,
  CONSTRAINT `FK5f9r1r91ey1si4ajtwh3sr1jo` FOREIGN KEY (`regular_id`) REFERENCES `regular_check` (`regular_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `FKvketojee2hif0n8ascxtru3s` FOREIGN KEY (`type_component_id`) REFERENCES `type_component` (`type_component_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1228 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定期检测表和构件值关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of regular_component
-- ----------------------------
INSERT INTO `regular_component` VALUES (1097, 5, 137, 5);
INSERT INTO `regular_component` VALUES (1098, 10, 137, 2);
INSERT INTO `regular_component` VALUES (1099, 10, 137, 1);
INSERT INTO `regular_component` VALUES (1100, 5, 137, 8);
INSERT INTO `regular_component` VALUES (1101, 5, 137, 11);
INSERT INTO `regular_component` VALUES (1102, 5, 137, 7);
INSERT INTO `regular_component` VALUES (1103, 5, 137, 10);
INSERT INTO `regular_component` VALUES (1104, 5, 137, 4);
INSERT INTO `regular_component` VALUES (1105, 5, 137, 3);
INSERT INTO `regular_component` VALUES (1106, 5, 137, 9);
INSERT INTO `regular_component` VALUES (1107, 5, 137, 12);
INSERT INTO `regular_component` VALUES (1108, 5, 137, 6);
INSERT INTO `regular_component` VALUES (1109, 5, 138, 5);
INSERT INTO `regular_component` VALUES (1110, 40, 138, 2);
INSERT INTO `regular_component` VALUES (1111, 40, 138, 1);
INSERT INTO `regular_component` VALUES (1112, 40, 138, 8);
INSERT INTO `regular_component` VALUES (1113, 40, 138, 11);
INSERT INTO `regular_component` VALUES (1114, 40, 138, 7);
INSERT INTO `regular_component` VALUES (1115, 40, 138, 10);
INSERT INTO `regular_component` VALUES (1116, 40, 138, 4);
INSERT INTO `regular_component` VALUES (1117, 40, 138, 3);
INSERT INTO `regular_component` VALUES (1118, 5, 138, 9);
INSERT INTO `regular_component` VALUES (1119, 5, 138, 12);
INSERT INTO `regular_component` VALUES (1120, 5, 138, 6);
INSERT INTO `regular_component` VALUES (1169, 10, 143, 5);
INSERT INTO `regular_component` VALUES (1170, 5, 143, 2);
INSERT INTO `regular_component` VALUES (1171, 5, 143, 1);
INSERT INTO `regular_component` VALUES (1172, 5, 143, 8);
INSERT INTO `regular_component` VALUES (1173, 5, 143, 11);
INSERT INTO `regular_component` VALUES (1174, 5, 143, 7);
INSERT INTO `regular_component` VALUES (1175, 5, 143, 10);
INSERT INTO `regular_component` VALUES (1176, 5, 143, 4);
INSERT INTO `regular_component` VALUES (1177, 5, 143, 3);
INSERT INTO `regular_component` VALUES (1178, 5, 143, 9);
INSERT INTO `regular_component` VALUES (1179, 5, 143, 12);
INSERT INTO `regular_component` VALUES (1180, 5, 143, 6);
INSERT INTO `regular_component` VALUES (1181, 15, NULL, 5);
INSERT INTO `regular_component` VALUES (1182, 15, NULL, 2);
INSERT INTO `regular_component` VALUES (1183, 10, NULL, 1);
INSERT INTO `regular_component` VALUES (1184, 10, NULL, 8);
INSERT INTO `regular_component` VALUES (1185, 5, NULL, 11);
INSERT INTO `regular_component` VALUES (1186, 5, NULL, 7);
INSERT INTO `regular_component` VALUES (1187, 5, NULL, 10);
INSERT INTO `regular_component` VALUES (1188, 5, NULL, 4);
INSERT INTO `regular_component` VALUES (1189, 5, NULL, 3);
INSERT INTO `regular_component` VALUES (1190, 5, NULL, 9);
INSERT INTO `regular_component` VALUES (1191, 5, NULL, 12);
INSERT INTO `regular_component` VALUES (1192, 5, NULL, 6);
INSERT INTO `regular_component` VALUES (1193, 15, 144, 5);
INSERT INTO `regular_component` VALUES (1194, 15, 144, 2);
INSERT INTO `regular_component` VALUES (1195, 10, 144, 1);
INSERT INTO `regular_component` VALUES (1196, 10, 144, 8);
INSERT INTO `regular_component` VALUES (1197, 5, 144, 11);
INSERT INTO `regular_component` VALUES (1198, 5, 144, 7);
INSERT INTO `regular_component` VALUES (1199, 5, 144, 10);
INSERT INTO `regular_component` VALUES (1200, 5, 144, 4);
INSERT INTO `regular_component` VALUES (1201, 5, 144, 3);
INSERT INTO `regular_component` VALUES (1202, 5, 144, 9);
INSERT INTO `regular_component` VALUES (1203, 5, 144, 12);
INSERT INTO `regular_component` VALUES (1204, 5, 144, 6);
INSERT INTO `regular_component` VALUES (1205, 5, NULL, 5);
INSERT INTO `regular_component` VALUES (1206, 5, NULL, 2);
INSERT INTO `regular_component` VALUES (1207, 5, NULL, 1);
INSERT INTO `regular_component` VALUES (1208, 5, NULL, 8);
INSERT INTO `regular_component` VALUES (1209, 5, NULL, 11);
INSERT INTO `regular_component` VALUES (1210, 5, NULL, 7);
INSERT INTO `regular_component` VALUES (1211, 5, NULL, 10);
INSERT INTO `regular_component` VALUES (1212, 5, NULL, 4);
INSERT INTO `regular_component` VALUES (1213, 5, NULL, 3);
INSERT INTO `regular_component` VALUES (1214, 5, NULL, 9);
INSERT INTO `regular_component` VALUES (1215, 5, NULL, 12);
INSERT INTO `regular_component` VALUES (1216, 5, NULL, 6);
INSERT INTO `regular_component` VALUES (1217, 5, 145, 5);
INSERT INTO `regular_component` VALUES (1218, 5, 145, 2);
INSERT INTO `regular_component` VALUES (1219, 5, 145, 1);
INSERT INTO `regular_component` VALUES (1220, 5, 145, 8);
INSERT INTO `regular_component` VALUES (1221, 5, 145, 11);
INSERT INTO `regular_component` VALUES (1222, 5, 145, 7);
INSERT INTO `regular_component` VALUES (1223, 5, 145, 10);
INSERT INTO `regular_component` VALUES (1224, 5, 145, 4);
INSERT INTO `regular_component` VALUES (1225, 5, 145, 3);
INSERT INTO `regular_component` VALUES (1226, 5, 145, 9);
INSERT INTO `regular_component` VALUES (1227, 5, 145, 12);
INSERT INTO `regular_component` VALUES (1228, 5, 145, 6);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` bigint NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '管理员');
INSERT INTO `role` VALUES (2, '日常巡检员');
INSERT INTO `role` VALUES (3, '定期巡检员');

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `role_id` bigint NOT NULL,
  `mid` int NOT NULL,
  PRIMARY KEY (`role_id`, `mid`) USING BTREE,
  INDEX `FK9gv8avsaapkmnyeacq5u1bak`(`mid`) USING BTREE,
  CONSTRAINT `FK9gv8avsaapkmnyeacq5u1bak` FOREIGN KEY (`mid`) REFERENCES `mainmenu` (`mid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKqyvxw2gg2qk0wld3bqfcb58vq` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES (1, 100);
INSERT INTO `role_menu` VALUES (1, 200);
INSERT INTO `role_menu` VALUES (2, 200);
INSERT INTO `role_menu` VALUES (3, 200);
INSERT INTO `role_menu` VALUES (1, 300);
INSERT INTO `role_menu` VALUES (2, 300);
INSERT INTO `role_menu` VALUES (3, 300);

-- ----------------------------
-- Table structure for submenu
-- ----------------------------
DROP TABLE IF EXISTS `submenu`;
CREATE TABLE `submenu`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mid` int NULL DEFAULT NULL,
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '子菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of submenu
-- ----------------------------
INSERT INTO `submenu` VALUES (1, 'iconfont icon-user', 100, '/user', '用户列表');
INSERT INTO `submenu` VALUES (2, 'iconfont icon-bridge1', 200, '/bridge/info', '桥梁资料卡');
INSERT INTO `submenu` VALUES (3, 'iconfont icon-monitor', 200, '/bridge/monitor', '监测内容');
INSERT INTO `submenu` VALUES (4, 'iconfont icon-form1', 300, '/check/dailyCheck', '日常检测');
INSERT INTO `submenu` VALUES (5, 'iconfont icon-Monitor', 300, '/check/regularCheck', '定期检测');

-- ----------------------------
-- Table structure for type_component
-- ----------------------------
DROP TABLE IF EXISTS `type_component`;
CREATE TABLE `type_component`  (
  `type_component_id` bigint NOT NULL AUTO_INCREMENT,
  `type_id` bigint NULL DEFAULT NULL,
  `component_id` bigint NULL DEFAULT NULL,
  `weight` double NULL DEFAULT NULL,
  PRIMARY KEY (`type_component_id`) USING BTREE,
  INDEX `FKrfnqvdoxkf3g9qo5bbv7dmnkc`(`type_id`) USING BTREE,
  INDEX `FKlhqk2qdvpg500o2eukr76m69k`(`component_id`) USING BTREE,
  CONSTRAINT `FKlhqk2qdvpg500o2eukr76m69k` FOREIGN KEY (`component_id`) REFERENCES `component` (`component_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKrfnqvdoxkf3g9qo5bbv7dmnkc` FOREIGN KEY (`type_id`) REFERENCES `bridge_type` (`type_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '桥梁类型和构件中间表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of type_component
-- ----------------------------
INSERT INTO `type_component` VALUES (1, 1, 1, 0.3);
INSERT INTO `type_component` VALUES (2, 1, 2, 0.1);
INSERT INTO `type_component` VALUES (3, 1, 3, 0.15);
INSERT INTO `type_component` VALUES (4, 1, 4, 0.1);
INSERT INTO `type_component` VALUES (5, 1, 5, 0.25);
INSERT INTO `type_component` VALUES (6, 1, 6, 0.1);
INSERT INTO `type_component` VALUES (7, 1, 7, 0.6);
INSERT INTO `type_component` VALUES (8, 1, 8, 0.4);
INSERT INTO `type_component` VALUES (9, 1, 9, 0.15);
INSERT INTO `type_component` VALUES (10, 1, 10, 0.3);
INSERT INTO `type_component` VALUES (11, 1, 11, 0.4);
INSERT INTO `type_component` VALUES (12, 1, 12, 0.15);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` bit(1) NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `FKn82ha3ccdebhokx3a8fgdqeyy`(`role_id`) USING BTREE,
  CONSTRAINT `FKn82ha3ccdebhokx3a8fgdqeyy` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '123', b'0', 'mk', 3);
INSERT INTO `user` VALUES (4, '1234', b'0', 'shan', 1);
INSERT INTO `user` VALUES (9, '1234', b'0', 'lily', 1);
INSERT INTO `user` VALUES (10, '123', b'0', 'lily', 2);

SET FOREIGN_KEY_CHECKS = 1;
