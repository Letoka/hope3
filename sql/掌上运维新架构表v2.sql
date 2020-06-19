/*
Navicat MySQL Data Transfer

Source Server         : localbackstage
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : mldb

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2020-05-31 11:39:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `hopeapitoken`
-- ----------------------------
DROP TABLE IF EXISTS `hopeapitoken`;
CREATE TABLE `hopeapitoken` (
  `apicode` varchar(10) NOT NULL,
  `apitoken` varchar(100) NOT NULL,
  `apiname` varchar(100) DEFAULT NULL,
  `createtime` datetime NOT NULL,
  PRIMARY KEY (`apicode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of hopeapitoken
-- ----------------------------

-- ----------------------------
-- Table structure for `hopebroadcast`
-- ----------------------------
DROP TABLE IF EXISTS `hopebroadcast`;
CREATE TABLE `hopebroadcast` (
  `broadcastid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID作主键，掌上播报',
  `broadcasttype` varchar(10) CHARACTER SET utf8 NOT NULL COMMENT '播报类型：公告/上新/优化/推广',
  `broadcastname` varchar(80) CHARACTER SET utf8 NOT NULL COMMENT '播报内容',
  `starttime` datetime NOT NULL COMMENT '播报开始时间',
  `endtime` datetime NOT NULL COMMENT '播报下线时间',
  `enabled` int(11) NOT NULL COMMENT '是否展示（0是1否）',
  `published` int(11) DEFAULT NULL COMMENT '1:发布，0：不发',
  PRIMARY KEY (`broadcastid`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of hopebroadcast
-- ----------------------------
INSERT INTO `hopebroadcast` VALUES ('1', '公告', '我是', '2020-05-19 16:38:38', '2020-05-22 16:38:44', '1', '1');
INSERT INTO `hopebroadcast` VALUES ('2', '优化', '阿嘎', '2020-05-17 16:39:54', '2020-05-23 16:39:50', '1', '1');
INSERT INTO `hopebroadcast` VALUES ('3', '推广', '想办法的合法的', '2020-05-06 11:17:58', '2020-06-02 11:20:25', '1', '1');
INSERT INTO `hopebroadcast` VALUES ('4', '上新', '只能收到回复的', '2020-05-12 11:18:03', '2020-06-03 11:20:21', '1', '1');
INSERT INTO `hopebroadcast` VALUES ('5', '公告', '吃奶粉单反', '2020-05-16 11:18:08', '2020-06-07 11:20:16', '1', '1');
INSERT INTO `hopebroadcast` VALUES ('6', '公告', '祝福你读书方法', '2020-05-12 11:18:13', '2020-06-05 11:20:11', '1', '1');
INSERT INTO `hopebroadcast` VALUES ('7', '优化', '这地方决定是否', '2020-05-07 11:18:17', '2020-05-25 11:20:03', '1', '1');
INSERT INTO `hopebroadcast` VALUES ('8', '优化', '爱德华的任何', '2020-05-05 11:18:22', '2020-05-30 11:19:49', '1', '1');
INSERT INTO `hopebroadcast` VALUES ('9', '上新', '这地方那地方', '2020-04-30 11:18:27', '2020-05-22 11:19:45', '1', '1');
INSERT INTO `hopebroadcast` VALUES ('10', '上新', '攻击力', '2020-05-18 11:18:34', '2020-05-23 11:19:40', '1', '1');
INSERT INTO `hopebroadcast` VALUES ('11', '推广', '刚看过', '2020-05-18 11:18:41', '2020-05-24 11:19:34', '1', '1');
INSERT INTO `hopebroadcast` VALUES ('12', '推广', '非公开客观方面', '2020-05-07 11:18:46', '2020-05-25 11:19:29', '1', '1');
INSERT INTO `hopebroadcast` VALUES ('13', '推广', '向法国美国法国', '2020-05-15 11:19:04', '2020-05-27 11:19:25', '1', '1');
INSERT INTO `hopebroadcast` VALUES ('14', '上新', 'x项目相关方面下功夫麻烦', '2020-05-16 11:18:58', '2020-05-28 11:19:20', '1', '1');
INSERT INTO `hopebroadcast` VALUES ('15', '优化', '下的反击得分较高', '2020-05-19 11:18:53', '2020-05-29 11:19:13', '1', '1');

-- ----------------------------
-- Table structure for `hopebroadcastpriv`
-- ----------------------------
DROP TABLE IF EXISTS `hopebroadcastpriv`;
CREATE TABLE `hopebroadcastpriv` (
  `broadcastprivid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID作主键，掌上播报权限',
  `broadcastid` int(11) NOT NULL COMMENT 'broadcast表中的id',
  `privtype` int(11) NOT NULL COMMENT '0为aamid有值，1为deptid有值，2为privgroupid有值，非空',
  `aamid` varchar(10) CHARACTER SET utf8 DEFAULT NULL COMMENT '统一认证号，priv为0时有值',
  `deptid` varchar(12) CHARACTER SET utf8 DEFAULT NULL COMMENT '部门编号，priv为1时有值',
  `privgroupid` varchar(30) CHARACTER SET utf8 DEFAULT NULL COMMENT '对应hopeprivgroup表id，priv为2时有值',
  `odeptid` varchar(12) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`broadcastprivid`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of hopebroadcastpriv
-- ----------------------------
INSERT INTO `hopebroadcastpriv` VALUES ('1', '1', '0', null, null, '数据中心1组', null);
INSERT INTO `hopebroadcastpriv` VALUES ('2', '2', '0', '001151515', null, null, null);
INSERT INTO `hopebroadcastpriv` VALUES ('3', '3', '0', null, '0010101032', null, null);
INSERT INTO `hopebroadcastpriv` VALUES ('4', '4', '0', null, null, '数据中心1组', null);
INSERT INTO `hopebroadcastpriv` VALUES ('5', '5', '0', null, null, '数据中心1组', null);
INSERT INTO `hopebroadcastpriv` VALUES ('6', '6', '0', null, null, '数据中心1组', null);
INSERT INTO `hopebroadcastpriv` VALUES ('7', '7', '0', null, null, '数据中心1组', null);
INSERT INTO `hopebroadcastpriv` VALUES ('8', '8', '0', null, null, '数据中心1组', null);
INSERT INTO `hopebroadcastpriv` VALUES ('9', '9', '0', null, null, '数据中心1组', null);
INSERT INTO `hopebroadcastpriv` VALUES ('10', '10', '0', null, null, '数据中心1组', null);
INSERT INTO `hopebroadcastpriv` VALUES ('11', '11', '0', null, null, '数据中心1组', null);
INSERT INTO `hopebroadcastpriv` VALUES ('12', '12', '0', null, null, '数据中心1组', null);
INSERT INTO `hopebroadcastpriv` VALUES ('13', '13', '0', null, null, '数据中心1组', null);
INSERT INTO `hopebroadcastpriv` VALUES ('14', '14', '0', null, null, '数据中心1组', null);
INSERT INTO `hopebroadcastpriv` VALUES ('15', '15', '0', null, null, '数据中心1组', null);
INSERT INTO `hopebroadcastpriv` VALUES ('16', '16', '0', null, null, '数据中心1组', null);
INSERT INTO `hopebroadcastpriv` VALUES ('17', '17', '0', null, null, '数据中心1组', null);

-- ----------------------------
-- Table structure for `hopecomments`
-- ----------------------------
DROP TABLE IF EXISTS `hopecomments`;
CREATE TABLE `hopecomments` (
  `aamid` varchar(10) NOT NULL,
  `moduleid` int(11) NOT NULL,
  `logtime` datetime NOT NULL,
  `comments` int(11) NOT NULL,
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`aamid`,`moduleid`,`logtime`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of hopecomments
-- ----------------------------
INSERT INTO `hopecomments` VALUES ('010203', '1', '2020-05-22 14:11:47', '1', null);
INSERT INTO `hopecomments` VALUES ('010203', '1', '2020-05-25 14:33:24', '0', '台风天赋有反应亿帆医药一个');
INSERT INTO `hopecomments` VALUES ('010203', '1', '2020-05-25 14:54:52', '1', 'xxxhhhxxx');
INSERT INTO `hopecomments` VALUES ('010203', '1', '2020-05-25 15:45:37', '1', 'xxxhhhxxx');
INSERT INTO `hopecomments` VALUES ('010203', '1', '2020-05-25 15:45:39', '1', 'xxxhhhxxx');
INSERT INTO `hopecomments` VALUES ('010203', '1', '2020-05-26 10:41:19', '1', '');
INSERT INTO `hopecomments` VALUES ('010203', '1', '2020-05-26 17:07:40', '0', '123123123123');
INSERT INTO `hopecomments` VALUES ('010203', '1', '2020-05-28 16:50:54', '1', '');
INSERT INTO `hopecomments` VALUES ('010203', '1', '2020-05-28 16:51:23', '0', '123');
INSERT INTO `hopecomments` VALUES ('010203', '2', '2020-05-25 16:00:02', '0', '不感兴趣');
INSERT INTO `hopecomments` VALUES ('010203', '2', '2020-05-26 14:46:52', '1', '');
INSERT INTO `hopecomments` VALUES ('010203', '2', '2020-05-26 17:08:03', '0', '123123123213');
INSERT INTO `hopecomments` VALUES ('010203', '3', '2020-05-25 15:48:31', '1', '');
INSERT INTO `hopecomments` VALUES ('010203', '4', '2020-05-25 15:59:53', '1', '');
INSERT INTO `hopecomments` VALUES ('010203', '5', '2020-05-25 16:13:59', '1', '');
INSERT INTO `hopecomments` VALUES ('010203', '6', '2020-05-25 16:18:26', '1', '');
INSERT INTO `hopecomments` VALUES ('010203', '6', '2020-05-25 16:19:48', '1', '');
INSERT INTO `hopecomments` VALUES ('010203', '6', '2020-05-25 16:19:54', '1', '');
INSERT INTO `hopecomments` VALUES ('010203', '6', '2020-05-25 16:19:55', '1', '');
INSERT INTO `hopecomments` VALUES ('010203', '6', '2020-05-25 16:19:56', '1', '');
INSERT INTO `hopecomments` VALUES ('010203', '7', '2020-05-25 16:20:50', '1', '');
INSERT INTO `hopecomments` VALUES ('010203', '9', '2020-05-25 16:20:14', '1', '');
INSERT INTO `hopecomments` VALUES ('010203', '9', '2020-05-26 10:22:04', '1', '');
INSERT INTO `hopecomments` VALUES ('010203', '11', '2020-05-25 16:27:16', '1', '');
INSERT INTO `hopecomments` VALUES ('010203', '11', '2020-05-25 16:27:19', '1', '');
INSERT INTO `hopecomments` VALUES ('010203', '11', '2020-05-25 16:27:20', '1', '');
INSERT INTO `hopecomments` VALUES ('010203', '11', '2020-05-25 16:27:21', '1', '');
INSERT INTO `hopecomments` VALUES ('010203', '11', '2020-05-25 16:27:22', '1', '');
INSERT INTO `hopecomments` VALUES ('010203', '11', '2020-05-25 16:27:23', '1', '');
INSERT INTO `hopecomments` VALUES ('010203', '12', '2020-05-25 16:24:39', '1', '');
INSERT INTO `hopecomments` VALUES ('010203', '13', '2020-05-25 16:25:18', '1', '');
INSERT INTO `hopecomments` VALUES ('010203', '14', '2020-05-25 16:30:15', '1', '');

-- ----------------------------
-- Table structure for `hopeimagebar`
-- ----------------------------
DROP TABLE IF EXISTS `hopeimagebar`;
CREATE TABLE `hopeimagebar` (
  `imagebarid` int(11) NOT NULL COMMENT '手动加ID作主键，头图区数据',
  `icon` varchar(30) CHARACTER SET utf8 NOT NULL COMMENT '放头图名称（不要路径/扩展名）',
  `starttime` datetime DEFAULT NULL COMMENT '播放开始时间，为空时从现在开始播放',
  `endtime` datetime NOT NULL COMMENT '播放结束时间',
  `weight` int(11) NOT NULL COMMENT '头图顺序权重（1-100，在权限范围内从小到大）',
  `enabled` int(11) NOT NULL COMMENT '是否展示',
  `published` int(11) DEFAULT NULL COMMENT '1:发布，空：不发',
  `moduleurl` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`imagebarid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of hopeimagebar
-- ----------------------------
INSERT INTO `hopeimagebar` VALUES ('0', 'qqq', null, '2020-05-30 10:19:53', '0', '1', '1', null);
INSERT INTO `hopeimagebar` VALUES ('1', 'juhezhifu', null, '2020-06-05 23:59:00', '1', '1', '1', 'woshizsywhhh');
INSERT INTO `hopeimagebar` VALUES ('2', 'zhj_chuke', '2020-05-01 00:00:00', '2020-06-01 23:59:00', '2', '1', '1', 'wo111');
INSERT INTO `hopeimagebar` VALUES ('3', 'wangluo', null, '2020-06-01 00:00:00', '3', '1', '1', 'wo333');
INSERT INTO `hopeimagebar` VALUES ('5', 'www', null, '2020-05-29 10:19:47', '0', '1', '1', 'eee');

-- ----------------------------
-- Table structure for `hopeimagebarpriv`
-- ----------------------------
DROP TABLE IF EXISTS `hopeimagebarpriv`;
CREATE TABLE `hopeimagebarpriv` (
  `imagebarprivid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID，头图权限',
  `imagebarid` int(11) NOT NULL COMMENT '头图表的ID',
  `privtype` int(11) NOT NULL COMMENT '权限类型0:aamid/1:deptid/2:groupid',
  `aamid` varchar(10) CHARACTER SET utf8 DEFAULT NULL COMMENT 'privtype为0时有值',
  `deptid` varchar(12) CHARACTER SET utf8 DEFAULT NULL COMMENT 'privtype为1时有值',
  `privgroupid` varchar(120) CHARACTER SET utf8 DEFAULT NULL COMMENT 'privtype为2时有值',
  `odeptid` varchar(12) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`imagebarprivid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of hopeimagebarpriv
-- ----------------------------
INSERT INTO `hopeimagebarpriv` VALUES ('1', '1', '2', null, null, '数据中心1组', null);
INSERT INTO `hopeimagebarpriv` VALUES ('2', '2', '0', '001151515', null, null, null);
INSERT INTO `hopeimagebarpriv` VALUES ('3', '3', '1', null, '0010101136', null, null);

-- ----------------------------
-- Table structure for `hopemedal`
-- ----------------------------
DROP TABLE IF EXISTS `hopemedal`;
CREATE TABLE `hopemedal` (
  `aamid` int(11) NOT NULL COMMENT '登录用户统一认证号',
  `medalid` int(11) NOT NULL COMMENT '不分级勋章id',
  PRIMARY KEY (`aamid`,`medalid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of hopemedal
-- ----------------------------

-- ----------------------------
-- Table structure for `hopemedalinfo`
-- ----------------------------
DROP TABLE IF EXISTS `hopemedalinfo`;
CREATE TABLE `hopemedalinfo` (
  `medalid` int(11) NOT NULL COMMENT '手工添加ID增序列，勋章数据',
  `medalname` varchar(12) CHARACTER SET utf8 NOT NULL COMMENT '勋章名称',
  `medallevel` int(11) DEFAULT NULL COMMENT '勋章等级（可以不要）',
  `medaldescript` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '勋章描述',
  PRIMARY KEY (`medalid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of hopemedalinfo
-- ----------------------------

-- ----------------------------
-- Table structure for `hopemodule`
-- ----------------------------
DROP TABLE IF EXISTS `hopemodule`;
CREATE TABLE `hopemodule` (
  `moduleid` int(11) NOT NULL COMMENT '视图ID手动加，视图区域',
  `modulename` varchar(24) CHARACTER SET utf8 NOT NULL COMMENT '视图名称（12字以内）',
  `enabled` int(11) NOT NULL COMMENT '视图启用（0、1）',
  `shortname` varchar(12) CHARACTER SET utf8 NOT NULL COMMENT '视图简称',
  `description` varchar(400) CHARACTER SET utf8 DEFAULT NULL COMMENT '描述',
  `icon` varchar(30) CHARACTER SET utf8 DEFAULT NULL COMMENT '图标名称（不要路径，要扩展名）',
  `image` varchar(30) CHARACTER SET utf8 NOT NULL COMMENT '图片名称（不要路径，要扩展名）',
  `modulegroupname` varchar(24) CHARACTER SET utf8 NOT NULL COMMENT '分类属组',
  `url` varchar(200) COLLATE utf8_bin NOT NULL COMMENT '视图url',
  PRIMARY KEY (`moduleid`),
  UNIQUE KEY `modulename_UNIQUE` (`modulename`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of hopemodule
-- ----------------------------
INSERT INTO `hopemodule` VALUES ('0', '阿桑的歌的', '1', '缩写dfd', '描述ege', null, '', '应用', '');
INSERT INTO `hopemodule` VALUES ('1', '仕途', '1', '缩写157', '描述g8hninin', null, '', '应用', '');
INSERT INTO `hopemodule` VALUES ('2', '试图45', '1', '缩写1679', '描述8tg87ghbin', null, '', '系统', '');
INSERT INTO `hopemodule` VALUES ('3', '识图', '1', '缩写16795', '描述kpok,pom,po', null, '', '分行', '');
INSERT INTO `hopemodule` VALUES ('4', '试图', '1', '缩写1685', '描述g8g7', null, '', '分行', '');
INSERT INTO `hopemodule` VALUES ('5', 'a图2', '1', '缩写1326', '描述pomplmpm', null, '', '系统', '');
INSERT INTO `hopemodule` VALUES ('6', '司徒', '1', '缩写121', '描述8g8iu', null, '', '分行', '');
INSERT INTO `hopemodule` VALUES ('7', 's图1', '1', '缩写169', '描述7gt8bh8', null, '', '应用', '');
INSERT INTO `hopemodule` VALUES ('8', '哦啊弄个999', '1', '缩写999', '描述999', null, '', '分行', '');
INSERT INTO `hopemodule` VALUES ('9', '是大概多少', '1', '缩写135', '描述hg87noijn', null, '', '系统', '');
INSERT INTO `hopemodule` VALUES ('10', '是大概多少烦得很', '1', '缩写146', '描述hihihnjmo', null, '', '分行', '');
INSERT INTO `hopemodule` VALUES ('11', '视图23', '1', '缩写134', '描述jkopmpj', null, '', '应用', '');
INSERT INTO `hopemodule` VALUES ('12', '该飞机发动机', '1', '缩写1346', '描述pjpmlm;lml', null, '', '应用', '');
INSERT INTO `hopemodule` VALUES ('13', '的身份获得释放和', '1', '缩写12', '描述jojonmo', null, '', '系统', '');
INSERT INTO `hopemodule` VALUES ('14', 's视图q', '1', '缩写185', '描述9h9joijo', null, '', '系统', '');
INSERT INTO `hopemodule` VALUES ('15', '对方的', '1', '缩写11', '描述ssssssssssssg', null, '', '分行', '');

-- ----------------------------
-- Table structure for `hopemodulegroup`
-- ----------------------------
DROP TABLE IF EXISTS `hopemodulegroup`;
CREATE TABLE `hopemodulegroup` (
  `modulegroupname` varchar(24) CHARACTER SET utf8 NOT NULL COMMENT '视图分类属组ID',
  `moduleid` int(11) NOT NULL COMMENT '视图ID',
  `sequence` int(11) NOT NULL COMMENT '视图位置顺序',
  PRIMARY KEY (`modulegroupname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of hopemodulegroup
-- ----------------------------

-- ----------------------------
-- Table structure for `hopemodulepriv`
-- ----------------------------
DROP TABLE IF EXISTS `hopemodulepriv`;
CREATE TABLE `hopemodulepriv` (
  `moduleprivid` int(11) NOT NULL COMMENT '视图权限ID',
  `moduleid` int(11) NOT NULL COMMENT '视图ID',
  `privtype` int(11) NOT NULL COMMENT '权限类型（0:aamid/1:deptid/2:privgroupid）',
  `aamid` varchar(10) CHARACTER SET utf8 DEFAULT NULL COMMENT 'privtype为0时有值',
  `deptid` varchar(12) CHARACTER SET utf8 DEFAULT NULL COMMENT 'privtype为1时有值',
  `privgroupid` varchar(30) CHARACTER SET utf8 DEFAULT NULL COMMENT 'privtype为2时有值',
  `odeptid` varchar(12) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`moduleprivid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of hopemodulepriv
-- ----------------------------
INSERT INTO `hopemodulepriv` VALUES ('0', '1', '0', null, null, '数据中心1组', null);
INSERT INTO `hopemodulepriv` VALUES ('1', '2', '0', '010203', null, null, null);
INSERT INTO `hopemodulepriv` VALUES ('2', '3', '0', '040506', null, '数据中心1组', null);
INSERT INTO `hopemodulepriv` VALUES ('3', '4', '0', '070809', null, '数据中心1组', null);
INSERT INTO `hopemodulepriv` VALUES ('4', '5', '0', null, '111213', null, null);
INSERT INTO `hopemodulepriv` VALUES ('5', '6', '0', null, '141516', '数据中心1组', null);
INSERT INTO `hopemodulepriv` VALUES ('6', '7', '0', null, '171819', '数据中心1组', null);
INSERT INTO `hopemodulepriv` VALUES ('8', '8', '0', null, null, '数据中心1组', null);
INSERT INTO `hopemodulepriv` VALUES ('9', '9', '0', null, null, '数据中心1组', null);
INSERT INTO `hopemodulepriv` VALUES ('10', '10', '0', null, null, '数据中心1组', null);
INSERT INTO `hopemodulepriv` VALUES ('11', '11', '0', null, null, '数据中心1组', null);
INSERT INTO `hopemodulepriv` VALUES ('12', '12', '0', null, null, '数据中心1组', null);
INSERT INTO `hopemodulepriv` VALUES ('13', '13', '0', null, null, '数据中心1组', null);
INSERT INTO `hopemodulepriv` VALUES ('14', '14', '0', null, null, '数据中心1组', null);
INSERT INTO `hopemodulepriv` VALUES ('15', '15', '0', null, null, '数据中心1组', null);

-- ----------------------------
-- Table structure for `hopeprivgroup`
-- ----------------------------
DROP TABLE IF EXISTS `hopeprivgroup`;
CREATE TABLE `hopeprivgroup` (
  `privgroupid` varchar(30) CHARACTER SET utf8 NOT NULL COMMENT '权限组ID',
  `privtype` int(11) NOT NULL COMMENT '权限组类型（0:aamid/1:deptid）',
  `aamid` varchar(10) CHARACTER SET utf8 DEFAULT NULL COMMENT 'privtype为0',
  `deptid` varchar(12) CHARACTER SET utf8 DEFAULT NULL COMMENT 'privtype为1',
  `odeptid` varchar(12) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of hopeprivgroup
-- ----------------------------
INSERT INTO `hopeprivgroup` VALUES ('数据中心1组', '0', '001151515', null, null);
INSERT INTO `hopeprivgroup` VALUES ('数据中心1组', '0', '888803996', null, null);
INSERT INTO `hopeprivgroup` VALUES ('数据中心1组', '3', null, '', '0010101032');
INSERT INTO `hopeprivgroup` VALUES ('数据中心1组', '0', '000111', null, null);
INSERT INTO `hopeprivgroup` VALUES ('数据中心1组', '0', '010203', null, null);
INSERT INTO `hopeprivgroup` VALUES ('数据中心1组', '1', null, '111213', null);

-- ----------------------------
-- Table structure for `hopesearchhistory`
-- ----------------------------
DROP TABLE IF EXISTS `hopesearchhistory`;
CREATE TABLE `hopesearchhistory` (
  `searchhistoryid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增搜索历史ID，清理策略：logtime3月之前',
  `aamid` varchar(10) CHARACTER SET utf8 NOT NULL COMMENT '登录用户统一认证号',
  `searchtext` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '搜索内容',
  `logtime` datetime NOT NULL COMMENT '搜索时间',
  PRIMARY KEY (`searchhistoryid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of hopesearchhistory
-- ----------------------------
INSERT INTO `hopesearchhistory` VALUES ('1', '1', '搜索5', '2020-05-14 17:18:13');
INSERT INTO `hopesearchhistory` VALUES ('2', '1', '搜索1', '2020-05-18 17:17:55');

-- ----------------------------
-- Table structure for `hopesearchhistory_h`
-- ----------------------------
DROP TABLE IF EXISTS `hopesearchhistory_h`;
CREATE TABLE `hopesearchhistory_h` (
  `searchhistoryid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增搜索历史ID，清理策略：logtime3月之前',
  `aamid` varchar(10) CHARACTER SET utf8 NOT NULL COMMENT '登录用户统一认证号',
  `searchtext` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '搜索内容',
  `logtime` datetime NOT NULL COMMENT '搜索时间',
  PRIMARY KEY (`searchhistoryid`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of hopesearchhistory_h
-- ----------------------------
INSERT INTO `hopesearchhistory_h` VALUES ('1', '', '快捷', '0000-00-00 00:00:00');
INSERT INTO `hopesearchhistory_h` VALUES ('2', '', '快捷支付', '0000-00-00 00:00:00');
INSERT INTO `hopesearchhistory_h` VALUES ('3', '', '聚合', '0000-00-00 00:00:00');
INSERT INTO `hopesearchhistory_h` VALUES ('4', '', '聚合支付', '0000-00-00 00:00:00');
INSERT INTO `hopesearchhistory_h` VALUES ('5', '', '工商支付', '0000-00-00 00:00:00');
INSERT INTO `hopesearchhistory_h` VALUES ('6', '', '工商支付', '0000-00-00 00:00:00');
INSERT INTO `hopesearchhistory_h` VALUES ('7', '', '支付宝支付', '0000-00-00 00:00:00');
INSERT INTO `hopesearchhistory_h` VALUES ('8', '', '支付宝支付', '0000-00-00 00:00:00');
INSERT INTO `hopesearchhistory_h` VALUES ('9', '', '工商支付', '0000-00-00 00:00:00');
INSERT INTO `hopesearchhistory_h` VALUES ('10', '', '工商支付', '0000-00-00 00:00:00');
INSERT INTO `hopesearchhistory_h` VALUES ('11', '', '工商支付', '0000-00-00 00:00:00');
INSERT INTO `hopesearchhistory_h` VALUES ('12', '', '工商支付', '0000-00-00 00:00:00');
INSERT INTO `hopesearchhistory_h` VALUES ('13', '', '微信', '0000-00-00 00:00:00');
INSERT INTO `hopesearchhistory_h` VALUES ('14', '', '微信支付', '0000-00-00 00:00:00');
INSERT INTO `hopesearchhistory_h` VALUES ('15', '', '微信支付', '0000-00-00 00:00:00');
INSERT INTO `hopesearchhistory_h` VALUES ('16', '', '支付宝', '0000-00-00 00:00:00');
INSERT INTO `hopesearchhistory_h` VALUES ('17', '', '工商', '0000-00-00 00:00:00');
INSERT INTO `hopesearchhistory_h` VALUES ('18', '', '京东支付', '0000-00-00 00:00:00');
INSERT INTO `hopesearchhistory_h` VALUES ('19', '', '京东', '0000-00-00 00:00:00');

-- ----------------------------
-- Table structure for `hopeshortcutbar`
-- ----------------------------
DROP TABLE IF EXISTS `hopeshortcutbar`;
CREATE TABLE `hopeshortcutbar` (
  `shortcutbarid` int(11) NOT NULL COMMENT '快捷方式ID，手动添加',
  `shortcutbarname` varchar(16) CHARACTER SET utf8 NOT NULL COMMENT '快捷方式名称',
  `shortcutbardescript` varchar(60) CHARACTER SET utf8 DEFAULT NULL COMMENT '快捷方式描述',
  `shortcut_image` varchar(30) CHARACTER SET utf8 DEFAULT NULL COMMENT '快捷图片名称，要扩展名',
  `moduleid` int(11) NOT NULL,
  PRIMARY KEY (`shortcutbarid`,`shortcutbarname`,`moduleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of hopeshortcutbar
-- ----------------------------
INSERT INTO `hopeshortcutbar` VALUES ('0', '分行', null, null, '0');
INSERT INTO `hopeshortcutbar` VALUES ('0', '分行', null, null, '8');
INSERT INTO `hopeshortcutbar` VALUES ('0', '分行', null, null, '9');
INSERT INTO `hopeshortcutbar` VALUES ('0', '生产运行', null, null, '0');
INSERT INTO `hopeshortcutbar` VALUES ('0', '生产运行', null, null, '1');
INSERT INTO `hopeshortcutbar` VALUES ('0', '联系我们', null, null, '0');
INSERT INTO `hopeshortcutbar` VALUES ('0', '联系我们', null, null, '6');
INSERT INTO `hopeshortcutbar` VALUES ('0', '重点业务', null, null, '0');
INSERT INTO `hopeshortcutbar` VALUES ('0', '重点业务', null, null, '7');

-- ----------------------------
-- Table structure for `hopeshortcutbarpriv`
-- ----------------------------
DROP TABLE IF EXISTS `hopeshortcutbarpriv`;
CREATE TABLE `hopeshortcutbarpriv` (
  `shortcutbarprivid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增快捷方式权限ID',
  `shortcutbarname` varchar(16) COLLATE utf8_bin NOT NULL COMMENT '快捷方式名称，查hopeshortcutbarbiao',
  `privtype` int(11) NOT NULL COMMENT '权限类型（0:aamid/1:deptid/2:privid）',
  `aamid` varchar(10) CHARACTER SET utf8 DEFAULT NULL,
  `deptid` varchar(12) CHARACTER SET utf8 DEFAULT NULL,
  `privgroupid` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `odeptid` varchar(12) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`shortcutbarprivid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of hopeshortcutbarpriv
-- ----------------------------
INSERT INTO `hopeshortcutbarpriv` VALUES ('1', '生产运行', '2', null, null, '数据中心1组', '');
INSERT INTO `hopeshortcutbarpriv` VALUES ('2', '重点业务', '3', null, null, null, '123456');
INSERT INTO `hopeshortcutbarpriv` VALUES ('3', '分行', '1', null, '111213', null, null);
INSERT INTO `hopeshortcutbarpriv` VALUES ('4', '联系我们', '0', '010203', null, null, null);

-- ----------------------------
-- Table structure for `hopeuser`
-- ----------------------------
DROP TABLE IF EXISTS `hopeuser`;
CREATE TABLE `hopeuser` (
  `aamid` varchar(10) CHARACTER SET utf8 NOT NULL COMMENT '登录用户统一认证号',
  `username` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户名',
  `registtime` datetime NOT NULL COMMENT '第一次登录掌上运维时间',
  `redirectgroup` int(11) DEFAULT NULL COMMENT '用于灰度发布，1:开发组，空:正常组',
  `userlevel` int(11) NOT NULL COMMENT '用户级别（1-6）',
  `userwatchlevel` int(11) DEFAULT NULL COMMENT '用户重要程度（1-99）',
  PRIMARY KEY (`aamid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of hopeuser
-- ----------------------------

-- ----------------------------
-- Table structure for `hopeuserconf`
-- ----------------------------
DROP TABLE IF EXISTS `hopeuserconf`;
CREATE TABLE `hopeuserconf` (
  `aamid` varchar(10) CHARACTER SET utf8 NOT NULL COMMENT '登录用户ID',
  `confid` varchar(15) CHARACTER SET utf8 NOT NULL COMMENT '用户设置ID（收藏展开，视图小图标，隐藏快捷方式）',
  `confvalue` int(11) NOT NULL COMMENT '用户设置内容（0/1）',
  PRIMARY KEY (`aamid`,`confid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of hopeuserconf
-- ----------------------------

-- ----------------------------
-- Table structure for `hopeuserfavor`
-- ----------------------------
DROP TABLE IF EXISTS `hopeuserfavor`;
CREATE TABLE `hopeuserfavor` (
  `aamid` varchar(10) CHARACTER SET utf8 NOT NULL COMMENT '登录用户统一认证号',
  `moduleid` int(11) NOT NULL COMMENT '视图ID',
  `sequence` int(11) NOT NULL COMMENT '关注视图的顺序'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of hopeuserfavor
-- ----------------------------
INSERT INTO `hopeuserfavor` VALUES ('010203', '5', '4');
INSERT INTO `hopeuserfavor` VALUES ('010203', '4', '1');
INSERT INTO `hopeuserfavor` VALUES ('010203', '3', '2');
INSERT INTO `hopeuserfavor` VALUES ('010203', '1', '5');

-- ----------------------------
-- Table structure for `hopeuserhistory`
-- ----------------------------
DROP TABLE IF EXISTS `hopeuserhistory`;
CREATE TABLE `hopeuserhistory` (
  `userhistoryid` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户浏览记录ID',
  `aamid` varchar(10) CHARACTER SET utf8 NOT NULL COMMENT '用户统一认证号',
  `moduleid` int(11) NOT NULL COMMENT '视图ID',
  `logtime` datetime NOT NULL COMMENT '浏览视图时间',
  PRIMARY KEY (`userhistoryid`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of hopeuserhistory
-- ----------------------------
INSERT INTO `hopeuserhistory` VALUES ('1', '1', '1', '2020-05-20 14:08:47');
INSERT INTO `hopeuserhistory` VALUES ('2', '1', '1', '2020-05-19 14:08:52');
INSERT INTO `hopeuserhistory` VALUES ('3', '1', '1', '2020-05-18 14:08:59');
INSERT INTO `hopeuserhistory` VALUES ('4', '1', '1', '2020-05-17 14:09:04');
INSERT INTO `hopeuserhistory` VALUES ('5', '1', '1', '2020-05-16 14:09:10');
INSERT INTO `hopeuserhistory` VALUES ('6', '123', '1', '2020-05-27 17:01:46');
INSERT INTO `hopeuserhistory` VALUES ('7', '123', '2', '2020-05-27 17:01:57');
INSERT INTO `hopeuserhistory` VALUES ('8', '123', '2', '2020-05-27 17:03:54');
INSERT INTO `hopeuserhistory` VALUES ('9', '1', '2', '2020-05-27 17:14:20');
INSERT INTO `hopeuserhistory` VALUES ('10', '1', '3', '2020-05-27 17:30:46');
INSERT INTO `hopeuserhistory` VALUES ('11', '1', '1', '2020-05-28 16:50:45');
INSERT INTO `hopeuserhistory` VALUES ('12', '1', '1', '2020-05-28 16:50:50');
INSERT INTO `hopeuserhistory` VALUES ('13', '1', '15', '2020-05-28 16:53:05');
INSERT INTO `hopeuserhistory` VALUES ('14', '1', '7', '2020-05-28 16:53:31');
INSERT INTO `hopeuserhistory` VALUES ('15', '1', '13', '2020-05-28 16:54:05');
INSERT INTO `hopeuserhistory` VALUES ('16', '1', '1', '2020-05-29 16:24:08');

-- ----------------------------
-- Table structure for `hopeuserinfo`
-- ----------------------------
DROP TABLE IF EXISTS `hopeuserinfo`;
CREATE TABLE `hopeuserinfo` (
  `aamid` varchar(10) CHARACTER SET utf8 NOT NULL COMMENT '统一认证号，用户详情',
  `username` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '用户名',
  `deptid` varchar(12) CHARACTER SET utf8 NOT NULL,
  `deptname` varchar(45) CHARACTER SET utf8 NOT NULL,
  `odeptid` varchar(12) CHARACTER SET utf8 DEFAULT NULL,
  `odeptname` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`aamid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of hopeuserinfo
-- ----------------------------
INSERT INTO `hopeuserinfo` VALUES ('010203', 'qwk', '0010101032', '', null, null);
INSERT INTO `hopeuserinfo` VALUES ('040506', 'aaa', '0010101032', '', null, null);
INSERT INTO `hopeuserinfo` VALUES ('070809', 'ccc', '0010101032', '', null, null);
INSERT INTO `hopeuserinfo` VALUES ('111213', 'ddd', '0010101136', '', null, null);
INSERT INTO `hopeuserinfo` VALUES ('141516', 'fff', '0010101136', '', null, null);
INSERT INTO `hopeuserinfo` VALUES ('171819', 'eee', '0010101136', '', null, null);

-- ----------------------------
-- Table structure for `hopeuserlog`
-- ----------------------------
DROP TABLE IF EXISTS `hopeuserlog`;
CREATE TABLE `hopeuserlog` (
  `userlogid` int(11) NOT NULL AUTO_INCREMENT COMMENT '访问日志记录ID，清理策略：logtime待定',
  `aamid` varchar(10) CHARACTER SET utf8 DEFAULT NULL,
  `moduleid` int(11) DEFAULT NULL,
  `logtime` datetime DEFAULT NULL,
  PRIMARY KEY (`userlogid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of hopeuserlog
-- ----------------------------

-- ----------------------------
-- Table structure for `hopeuserlogdetail`
-- ----------------------------
DROP TABLE IF EXISTS `hopeuserlogdetail`;
CREATE TABLE `hopeuserlogdetail` (
  `userlogdetailid` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户行为记录ID，清理策略：待定',
  `aamid` varchar(10) CHARACTER SET utf8 NOT NULL,
  `targetid` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '操作对象ID',
  `targetname` varchar(20) CHARACTER SET utf8 NOT NULL,
  `operation` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '什么操作',
  `logtime` datetime NOT NULL,
  PRIMARY KEY (`userlogdetailid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of hopeuserlogdetail
-- ----------------------------

-- ----------------------------
-- Table structure for `hopeuserlogdetail_h`
-- ----------------------------
DROP TABLE IF EXISTS `hopeuserlogdetail_h`;
CREATE TABLE `hopeuserlogdetail_h` (
  `userlogdetailid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增用户行为记录ID',
  `aamid` varchar(10) CHARACTER SET utf8 NOT NULL,
  `targetid` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '操作对象ID',
  `targetname` varchar(20) CHARACTER SET utf8 NOT NULL,
  `operation` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '什么操作',
  `logtime` datetime NOT NULL,
  PRIMARY KEY (`userlogdetailid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of hopeuserlogdetail_h
-- ----------------------------

-- ----------------------------
-- Table structure for `hopeuserlog_h`
-- ----------------------------
DROP TABLE IF EXISTS `hopeuserlog_h`;
CREATE TABLE `hopeuserlog_h` (
  `userlogid` int(11) NOT NULL AUTO_INCREMENT,
  `aamid` varchar(10) CHARACTER SET utf8 NOT NULL,
  `moduleid` int(11) NOT NULL,
  `logtime` datetime DEFAULT NULL,
  PRIMARY KEY (`userlogid`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of hopeuserlog_h
-- ----------------------------
INSERT INTO `hopeuserlog_h` VALUES ('1', '', '1', null);
INSERT INTO `hopeuserlog_h` VALUES ('2', '', '2', null);
INSERT INTO `hopeuserlog_h` VALUES ('3', '', '3', null);
INSERT INTO `hopeuserlog_h` VALUES ('4', '', '4', null);
INSERT INTO `hopeuserlog_h` VALUES ('5', '', '1', null);
INSERT INTO `hopeuserlog_h` VALUES ('6', '', '1', null);
INSERT INTO `hopeuserlog_h` VALUES ('7', '', '2', null);
INSERT INTO `hopeuserlog_h` VALUES ('8', '', '2', null);
INSERT INTO `hopeuserlog_h` VALUES ('9', '', '3', null);
INSERT INTO `hopeuserlog_h` VALUES ('10', '', '0', null);
INSERT INTO `hopeuserlog_h` VALUES ('11', '123', '1', '2020-05-20 05:20:05');
INSERT INTO `hopeuserlog_h` VALUES ('15', '20200521', '1', '2020-05-20 05:20:05');
INSERT INTO `hopeuserlog_h` VALUES ('17', '10100521', '1', '2020-05-20 05:20:05');
INSERT INTO `hopeuserlog_h` VALUES ('18', '03040521', '1', '2020-05-25 15:00:02');
INSERT INTO `hopeuserlog_h` VALUES ('19', '123', '1', '2020-05-25 15:00:23');
INSERT INTO `hopeuserlog_h` VALUES ('20', '123', '1', '2020-05-25 15:03:00');
INSERT INTO `hopeuserlog_h` VALUES ('21', '123', '1', '2020-05-25 15:05:46');
INSERT INTO `hopeuserlog_h` VALUES ('22', '123', '3', '2020-05-25 16:08:20');
INSERT INTO `hopeuserlog_h` VALUES ('23', '123', '5', '2020-05-25 16:10:54');
INSERT INTO `hopeuserlog_h` VALUES ('24', '123', '5', '2020-05-25 16:13:56');
INSERT INTO `hopeuserlog_h` VALUES ('25', '123', '6', '2020-05-25 16:17:07');
INSERT INTO `hopeuserlog_h` VALUES ('26', '123', '6', '2020-05-25 16:17:18');
INSERT INTO `hopeuserlog_h` VALUES ('27', '123', '6', '2020-05-25 16:19:37');
INSERT INTO `hopeuserlog_h` VALUES ('28', '123', '12', '2020-05-25 16:24:29');
INSERT INTO `hopeuserlog_h` VALUES ('29', '123', '11', '2020-05-25 16:26:25');
INSERT INTO `hopeuserlog_h` VALUES ('30', '123', '11', '2020-05-25 16:26:41');
INSERT INTO `hopeuserlog_h` VALUES ('31', '123', '14', '2020-05-25 16:30:11');
INSERT INTO `hopeuserlog_h` VALUES ('32', '123', '1', '2020-05-25 17:07:43');
INSERT INTO `hopeuserlog_h` VALUES ('33', '123', '1', '2020-05-25 17:07:57');
INSERT INTO `hopeuserlog_h` VALUES ('34', '123', '9', '2020-05-26 10:21:27');
INSERT INTO `hopeuserlog_h` VALUES ('35', '123', '2', '2020-05-26 14:46:57');
INSERT INTO `hopeuserlog_h` VALUES ('36', '123', '2', '2020-05-27 09:03:32');
INSERT INTO `hopeuserlog_h` VALUES ('37', '123', '2', '2020-05-27 09:03:53');
INSERT INTO `hopeuserlog_h` VALUES ('38', '123', '5', '2020-05-27 09:05:02');
INSERT INTO `hopeuserlog_h` VALUES ('39', '123', '9', '2020-05-27 09:06:03');
INSERT INTO `hopeuserlog_h` VALUES ('40', '123', '10', '2020-05-27 09:08:05');
INSERT INTO `hopeuserlog_h` VALUES ('41', '123', '2', '2020-05-27 10:30:38');
INSERT INTO `hopeuserlog_h` VALUES ('42', '123', '2', '2020-05-27 10:30:47');
INSERT INTO `hopeuserlog_h` VALUES ('43', '123', '2', '2020-05-27 10:30:55');
INSERT INTO `hopeuserlog_h` VALUES ('44', '123', '2', '2020-05-27 10:32:33');
INSERT INTO `hopeuserlog_h` VALUES ('45', '123', '2', '2020-05-27 14:38:17');
INSERT INTO `hopeuserlog_h` VALUES ('46', '123', '1', '2020-05-27 17:01:46');
INSERT INTO `hopeuserlog_h` VALUES ('47', '123', '2', '2020-05-27 17:01:57');
INSERT INTO `hopeuserlog_h` VALUES ('48', '123', '2', '2020-05-27 17:03:54');
INSERT INTO `hopeuserlog_h` VALUES ('49', '1', '2', '2020-05-27 17:14:20');
INSERT INTO `hopeuserlog_h` VALUES ('50', '1', '3', '2020-05-27 17:30:46');
INSERT INTO `hopeuserlog_h` VALUES ('51', '1', '1', '2020-05-28 16:50:45');
INSERT INTO `hopeuserlog_h` VALUES ('52', '1', '1', '2020-05-28 16:50:50');
INSERT INTO `hopeuserlog_h` VALUES ('53', '1', '15', '2020-05-28 16:53:05');
INSERT INTO `hopeuserlog_h` VALUES ('54', '1', '7', '2020-05-28 16:53:31');
INSERT INTO `hopeuserlog_h` VALUES ('55', '1', '13', '2020-05-28 16:54:05');
INSERT INTO `hopeuserlog_h` VALUES ('56', '1', '1', '2020-05-29 16:24:08');

-- ----------------------------
-- Table structure for `hopewatchgroup`
-- ----------------------------
DROP TABLE IF EXISTS `hopewatchgroup`;
CREATE TABLE `hopewatchgroup` (
  `watchgroupid` varchar(30) CHARACTER SET utf8 NOT NULL COMMENT '被监控组',
  `privtype` int(11) NOT NULL,
  `aamid` varchar(10) CHARACTER SET utf8 DEFAULT NULL,
  `deptid` varchar(12) CHARACTER SET utf8 DEFAULT NULL,
  `odeptid` varchar(12) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of hopewatchgroup
-- ----------------------------

-- ----------------------------
-- Table structure for `hopewatchgrouppriv`
-- ----------------------------
DROP TABLE IF EXISTS `hopewatchgrouppriv`;
CREATE TABLE `hopewatchgrouppriv` (
  `watchgroupprivid` varchar(12) CHARACTER SET utf8 NOT NULL COMMENT '监控主语',
  `aamid` varchar(10) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`watchgroupprivid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of hopewatchgrouppriv
-- ----------------------------

-- ----------------------------
-- View structure for `hopeviewbroadcastpriv`
-- ----------------------------
DROP VIEW IF EXISTS `hopeviewbroadcastpriv`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `hopeviewbroadcastpriv` AS (select `a`.`broadcastid` AS `broadcastid`,`b`.`privtype` AS `privtype`,`b`.`aamid` AS `aamid`,`b`.`deptid` AS `deptid`,`b`.`odeptid` AS `odeptid` from (`hopebroadcastpriv` `a` join `hopeprivgroup` `b`) where (`a`.`privgroupid` = `b`.`privgroupid`)) union (select `hopebroadcastpriv`.`broadcastid` AS `broadcastid`,`hopebroadcastpriv`.`privtype` AS `privtype`,`hopebroadcastpriv`.`aamid` AS `aamid`,`hopebroadcastpriv`.`deptid` AS `deptid`,`hopebroadcastpriv`.`odeptid` AS `odeptid` from `hopebroadcastpriv` where (`hopebroadcastpriv`.`privtype` <> 2)) ;

-- ----------------------------
-- View structure for `hopeviewimagebarpriv`
-- ----------------------------
DROP VIEW IF EXISTS `hopeviewimagebarpriv`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `hopeviewimagebarpriv` AS (select `a`.`imagebarid` AS `imagebarid`,`b`.`privtype` AS `privtype`,`b`.`aamid` AS `aamid`,`b`.`deptid` AS `deptid`,`b`.`odeptid` AS `odeptid` from (`hopeimagebarpriv` `a` join `hopeprivgroup` `b`) where (`a`.`privgroupid` = `b`.`privgroupid`)) union (select `hopeimagebarpriv`.`imagebarid` AS `imagebarid`,`hopeimagebarpriv`.`privtype` AS `privtype`,`hopeimagebarpriv`.`aamid` AS `aamid`,`hopeimagebarpriv`.`deptid` AS `deptid`,`hopeimagebarpriv`.`odeptid` AS `odeptid` from `hopeimagebarpriv` where (`hopeimagebarpriv`.`privtype` <> 2)) ;

-- ----------------------------
-- View structure for `hopeviewmodulepriv`
-- ----------------------------
DROP VIEW IF EXISTS `hopeviewmodulepriv`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `hopeviewmodulepriv` AS (select `a`.`moduleid` AS `moduleid`,`b`.`privtype` AS `privtype`,`b`.`aamid` AS `aamid`,`b`.`deptid` AS `deptid`,`b`.`odeptid` AS `odeptid` from (`hopemodulepriv` `a` join `hopeprivgroup` `b`) where (`a`.`privgroupid` = `b`.`privgroupid`)) union (select `hopemodulepriv`.`moduleid` AS `moduleid`,`hopemodulepriv`.`privtype` AS `privtype`,`hopemodulepriv`.`aamid` AS `aamid`,`hopemodulepriv`.`deptid` AS `deptid`,`hopemodulepriv`.`odeptid` AS `odeptid` from `hopemodulepriv` where (`hopemodulepriv`.`privtype` <> 2)) ;

-- ----------------------------
-- View structure for `hopeviewshortcutbarpriv`
-- ----------------------------
DROP VIEW IF EXISTS `hopeviewshortcutbarpriv`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `hopeviewshortcutbarpriv` AS (select `a`.`shortcutbarname` AS `shortcutbarname`,`b`.`privtype` AS `privtype`,`b`.`aamid` AS `aamid`,`b`.`deptid` AS `deptid`,`b`.`odeptid` AS `odeptid` from (`hopeshortcutbarpriv` `a` join `hopeprivgroup` `b`) where (`a`.`privgroupid` = `b`.`privgroupid`)) union (select `hopeshortcutbarpriv`.`shortcutbarname` AS `shortcutbarname`,`hopeshortcutbarpriv`.`privtype` AS `privtype`,`hopeshortcutbarpriv`.`aamid` AS `aamid`,`hopeshortcutbarpriv`.`deptid` AS `deptid`,`hopeshortcutbarpriv`.`odeptid` AS `odeptid` from `hopeshortcutbarpriv` where (`hopeshortcutbarpriv`.`privtype` <> 2)) ;

-- ----------------------------
-- View structure for `view_back_summary`
-- ----------------------------
DROP VIEW IF EXISTS `view_back_summary`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_back_summary` AS select `a`.`privgroupid` AS `privgroupid`,`a`.`hgaamid` AS `hgaamid`,`a`.`huaamid` AS `huaamid`,`a`.`hudeptid` AS `hudeptid` from `v_pri_dept_v1` `a` union select `b`.`privgroupid` AS `privgroupid`,`b`.`hgaamid` AS `hgaamid`,`b`.`huaamid` AS `huaamid`,`b`.`hudeptid` AS `hudeptid` from `v_pri_dept_v2` `b` ;

-- ----------------------------
-- View structure for `v_pri_dept`
-- ----------------------------
DROP VIEW IF EXISTS `v_pri_dept`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_pri_dept` AS select `view_back_summary`.`privgroupid` AS `privgroupid`,(case when (`view_back_summary`.`hgaamid` is not null) then `view_back_summary`.`hgaamid` else `view_back_summary`.`huaamid` end) AS `aamid`,`view_back_summary`.`hudeptid` AS `hudeptid` from `view_back_summary` ;

-- ----------------------------
-- View structure for `v_pri_dept_v1`
-- ----------------------------
DROP VIEW IF EXISTS `v_pri_dept_v1`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_pri_dept_v1` AS select `hg`.`privgroupid` AS `privgroupid`,`hg`.`aamid` AS `hgaamid`,`hu`.`aamid` AS `huaamid`,`hu`.`deptid` AS `hudeptid` from (`hopeprivgroup` `hg` left join `hopeuserinfo` `hu` on((`hg`.`deptid` = `hu`.`deptid`))) ;

-- ----------------------------
-- View structure for `v_pri_dept_v2`
-- ----------------------------
DROP VIEW IF EXISTS `v_pri_dept_v2`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_pri_dept_v2` AS select `hg`.`privgroupid` AS `privgroupid`,`hg`.`aamid` AS `hgaamid`,`hu`.`aamid` AS `huaamid`,`hu`.`deptid` AS `hudeptid` from (`hopeuserinfo` `hu` left join `hopeprivgroup` `hg` on((`hg`.`deptid` = `hu`.`deptid`))) ;
