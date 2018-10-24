/*
 Navicat MySQL Data Transfer

 Source Server         : 47.104.172.218
 Source Server Type    : MySQL
 Source Server Version : 100204
 Source Host           : 47.104.172.218:3306
 Source Schema         : material

 Target Server Type    : MySQL
 Target Server Version : 100204
 File Encoding         : 65001

 Date: 24/10/2018 09:23:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for material
-- ----------------------------
DROP TABLE IF EXISTS `material`;
CREATE TABLE `material` (
  `material_id` varchar(32) NOT NULL COMMENT '主键',
  `material_name` varchar(30) DEFAULT NULL COMMENT '原料名称',
  `material_num` int(6) DEFAULT NULL COMMENT '原料数量',
  `material_description` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(32) DEFAULT NULL COMMENT '修改人',
  `delete_flag` char(1) DEFAULT NULL COMMENT '删除标志	0：未删除，1：已删除',
  PRIMARY KEY (`material_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of material
-- ----------------------------
BEGIN;
INSERT INTO `material` VALUES ('829355aa28b749d88fb7d826b05ed736', '原料D', 20, '这是原料D', NULL, '706a8435ccf04a05a22a975b4ce40581', NULL, NULL, NULL);
INSERT INTO `material` VALUES ('8f7b645689844f1eb68758827551cfe3', '原料D', 20, '这是原料D', NULL, '706a8435ccf04a05a22a975b4ce40581', NULL, NULL, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
