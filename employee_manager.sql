/*
Navicat MySQL Data Transfer

Source Server         : drake
Source Server Version : 50022
Source Host           : localhost:3306
Source Database       : employee_manager

Target Server Type    : MYSQL
Target Server Version : 50022
File Encoding         : 65001

Date: 2018-12-09 23:17:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admins
-- ----------------------------
DROP TABLE IF EXISTS `admins`;
CREATE TABLE `admins` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `permission` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of admins
-- ----------------------------
INSERT INTO `admins` VALUES ('1', 'admin', '123', '9');
INSERT INTO `admins` VALUES ('2', 'drake', 'zxc', '1');
INSERT INTO `admins` VALUES ('3', 'yang', '123', '1');

-- ----------------------------
-- Table structure for award
-- ----------------------------
DROP TABLE IF EXISTS `award`;
CREATE TABLE `award` (
  `id` int(11) NOT NULL,
  `number` int(11) NOT NULL,
  `employee_id` int(11) NOT NULL,
  `time` date default NULL,
  `place` varchar(255) default NULL,
  `reason` varchar(255) default NULL,
  `etc` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of award
-- ----------------------------
INSERT INTO `award` VALUES ('1', '1', '1', '2018-11-15', 'æ¹–å—', 'å¸…', 'æ— ');

-- ----------------------------
-- Table structure for departments
-- ----------------------------
DROP TABLE IF EXISTS `departments`;
CREATE TABLE `departments` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `people` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of departments
-- ----------------------------
INSERT INTO `departments` VALUES ('1', '大数据', '15');
INSERT INTO `departments` VALUES ('2', '人工智能', '5');

-- ----------------------------
-- Table structure for employees
-- ----------------------------
DROP TABLE IF EXISTS `employees`;
CREATE TABLE `employees` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(20) NOT NULL,
  `sex` varchar(10) NOT NULL,
  `education` varchar(255) default NULL,
  `politics` varchar(255) default NULL,
  `married` varchar(255) default NULL,
  `birthday` date default NULL,
  `in_work` varchar(255) default NULL,
  `first_day` date default NULL,
  `regular_day` date default NULL,
  `department` varchar(255) default NULL,
  `job` varchar(255) default NULL,
  `etc` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employees
-- ----------------------------
INSERT INTO `employees` VALUES ('1', 'Drake', '男', '大学本科', '团员', '未婚', '1998-01-20', '否', '2018-11-01', '2018-11-10', '大数据', '程序员', '无');

-- ----------------------------
-- Table structure for salary
-- ----------------------------
DROP TABLE IF EXISTS `salary`;
CREATE TABLE `salary` (
  `id` int(11) NOT NULL,
  `number` int(11) NOT NULL,
  `employee_id` int(11) NOT NULL,
  `basic_salary` int(11) default NULL,
  `bouns` int(11) default NULL,
  `welfare` int(11) default NULL,
  `total` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of salary
-- ----------------------------
INSERT INTO `salary` VALUES ('1', '1', '1', '1000', '500', '200', '1700');

-- ----------------------------
-- Table structure for training
-- ----------------------------
DROP TABLE IF EXISTS `training`;
CREATE TABLE `training` (
  `id` int(11) NOT NULL,
  `number` int(11) NOT NULL,
  `employee_id` int(11) NOT NULL,
  `days` int(11) default NULL,
  `fee` int(11) default NULL,
  `content` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of training
-- ----------------------------
INSERT INTO `training` VALUES ('1', '1', '1', '5', '300', '无');
