/*
Navicat MySQL Data Transfer

Source Server         : 本地Mysql
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : kj_fy

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2018-06-08 10:30:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `menu_name` varchar(50) DEFAULT NULL,
  `menu_url` varchar(50) DEFAULT '菜单链接',
  `menu_icon` varchar(50) DEFAULT '菜单图片',
  `sort_num` int(11) DEFAULT '1',
  `user_id` int(11) DEFAULT '1' COMMENT '创建这个菜单的用户id',
  `is_del` int(11) DEFAULT '0' COMMENT '1-- 删除状态，0 -- 正常',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '总菜单', '菜单链接', '菜单图片', '1', '1', '0', '2018-06-07 15:43:47', null);
INSERT INTO `sys_menu` VALUES ('2', '1', '001', '菜单链接', '菜单图片', '1', '1', '0', '2018-06-07 15:44:10', null);
INSERT INTO `sys_menu` VALUES ('3', '2', '001001', '菜单链接', '菜单图片', '1', '1', '0', '2018-06-07 15:44:18', null);
INSERT INTO `sys_menu` VALUES ('4', '3', '001001001', '菜单链接', '菜单图片', '1', '1', '0', '2018-06-07 15:44:24', null);
INSERT INTO `sys_menu` VALUES ('5', '4', '001001001001', '菜单链接', '菜单图片', '1', '1', '0', '2018-06-07 15:44:30', null);
INSERT INTO `sys_menu` VALUES ('6', '1', '002', '菜单链接', '菜单图片', '1', '1', '0', '2018-06-07 15:45:07', null);
INSERT INTO `sys_menu` VALUES ('7', '6', '002001', '菜单链接', '菜单图片', '1', '1', '0', '2018-06-07 15:45:25', null);
INSERT INTO `sys_menu` VALUES ('8', '6', '002002', '菜单链接', '菜单图片', '1', '1', '0', '2018-06-07 15:45:37', null);

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `test_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `test_name` varchar(200) DEFAULT NULL COMMENT '名称',
  `test_password` mediumtext COMMENT '密码',
  PRIMARY KEY (`test_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='测试';

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES ('1', 'aaa', 'aaa');
INSERT INTO `test` VALUES ('2', 'bbb', 'bbb');
INSERT INTO `test` VALUES ('3', 'ccc', 'ccc');
INSERT INTO `test` VALUES ('4', 'aaa', 'ddd');
INSERT INTO `test` VALUES ('5', 'aaa', 'aaaa');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `USER_NAME` char(30) NOT NULL COMMENT '用户名',
  `USER_PWD` char(100) DEFAULT NULL COMMENT '密码',
  `STATUS` int(1) NOT NULL DEFAULT '1' COMMENT '状态:0-失效; 1-正常; 2-锁定;3-待审核',
  `Email` char(50) DEFAULT NULL COMMENT '邮箱',
  `MOBILE` char(11) DEFAULT NULL COMMENT '手机',
  PRIMARY KEY (`ID`,`USER_NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', 'admin', '1', '15008@qq.com', '13265174688');
INSERT INTO `t_user` VALUES ('2', 'user', 'user', '1', 'user', 'user');
INSERT INTO `t_user` VALUES ('3', 'yyy', 'yyy', '1', 'yyy', 'yyy');
INSERT INTO `t_user` VALUES ('4', 'aaa', 'aaa', '1', 'aaa', 'aaa');
INSERT INTO `t_user` VALUES ('5', 'admin', 'ccc', '1', 'ccc', 'ccc');
