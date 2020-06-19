/*
SQLyog Community v12.01 (32 bit)
MySQL - 5.7.23 : Database - mldb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mldb` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `mldb`;

/*Table structure for table `hopebroadcast` */

DROP TABLE IF EXISTS `hopebroadcast`;

CREATE TABLE `hopebroadcast` (
  `broadcastid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID作主键，掌上播报',
  `broadcasttype` varchar(10) CHARACTER SET utf8 NOT NULL COMMENT '播报类型：公告/上新/优化/推广',
  `broadcastname` varchar(80) CHARACTER SET utf8 NOT NULL COMMENT '播报内容',
  `starttime` datetime NOT NULL COMMENT '播报开始时间',
  `endtime` datetime NOT NULL COMMENT '播报下线时间',
  `enabled` int(11) NOT NULL COMMENT '是否展示（0是1否）',
  `published` int(11) DEFAULT NULL COMMENT '1:发布，空：不发',
  PRIMARY KEY (`broadcastid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `hopebroadcast` */

/*Table structure for table `hopebroadcastpriv` */

DROP TABLE IF EXISTS `hopebroadcastpriv`;

CREATE TABLE `hopebroadcastpriv` (
  `broadcastprivid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID作主键，掌上播报权限',
  `broadcastid` int(11) NOT NULL COMMENT 'broadcast表中的id',
  `privtype` int(11) NOT NULL COMMENT '0为aamid有值，1为deptid有值，2为privgroupid有值，非空',
  `aamid` varchar(10) CHARACTER SET utf8 DEFAULT NULL COMMENT '统一认证号，priv为0时有值',
  `deptid` varchar(12) CHARACTER SET utf8 DEFAULT NULL COMMENT '部门编号，priv为1时有值',
  `privgroupid` varchar(30) CHARACTER SET utf8 DEFAULT NULL COMMENT '对应hopeprivgroup表id，priv为2时有值',
  PRIMARY KEY (`broadcastprivid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `hopebroadcastpriv` */

/*Table structure for table `hopeimagebar` */

DROP TABLE IF EXISTS `hopeimagebar`;

CREATE TABLE `hopeimagebar` (
  `imagebarid` int(11) NOT NULL COMMENT '自增ID作主键，头图区数据',
  `icon` varchar(30) CHARACTER SET utf8 NOT NULL COMMENT '放头图名称（不要路径/扩展名）',
  `starttime` datetime DEFAULT NULL COMMENT '播放开始时间，为空时从现在开始播放',
  `endtime` datetime NOT NULL COMMENT '播放结束时间',
  `weight` int(11) NOT NULL COMMENT '头图顺序权重（1-100，在权限范围内从小到大）',
  `enabled` int(11) NOT NULL COMMENT '是否展示',
  `published` int(11) DEFAULT NULL COMMENT '1:发布，空：不发',
  PRIMARY KEY (`imagebarid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `hopeimagebar` */

/*Table structure for table `hopeimagebarpriv` */

DROP TABLE IF EXISTS `hopeimagebarpriv`;

CREATE TABLE `hopeimagebarpriv` (
  `imagebarprivid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID，头图权限',
  `privtype` int(11) NOT NULL COMMENT '权限类型0:aamid/1:deptid/2:groupid',
  `aamid` varchar(10) CHARACTER SET latin1 DEFAULT NULL COMMENT 'privtype为0时有值',
  `deptid` varchar(12) CHARACTER SET latin1 DEFAULT NULL COMMENT 'privtype为1时有值',
  `privgroupid` varchar(30) CHARACTER SET latin1 DEFAULT NULL COMMENT 'privtype为2时有值',
  PRIMARY KEY (`imagebarprivid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `hopeimagebarpriv` */

/*Table structure for table `hopemedal` */

DROP TABLE IF EXISTS `hopemedal`;

CREATE TABLE `hopemedal` (
  `aamid` int(11) NOT NULL COMMENT '登录用户统一认证号',
  `medalid` int(11) NOT NULL COMMENT '不分级勋章id',
  PRIMARY KEY (`aamid`,`medalid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `hopemedal` */

/*Table structure for table `hopemedalinfo` */

DROP TABLE IF EXISTS `hopemedalinfo`;

CREATE TABLE `hopemedalinfo` (
  `medalid` int(11) NOT NULL COMMENT '手工添加ID增序列，勋章数据',
  `medalname` varchar(12) CHARACTER SET utf8 NOT NULL COMMENT '勋章名称',
  `medallevel` int(11) DEFAULT NULL COMMENT '勋章等级（可以不要）',
  `medaldescript` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '勋章描述',
  PRIMARY KEY (`medalid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `hopemedalinfo` */

/*Table structure for table `hopemodule` */

DROP TABLE IF EXISTS `hopemodule`;

CREATE TABLE `hopemodule` (
  `moduleid` int(11) NOT NULL COMMENT '视图ID手动加，视图区域',
  `modulename` varchar(24) CHARACTER SET utf8 NOT NULL COMMENT '视图名称（12字以内）',
  `enabled` int(11) NOT NULL COMMENT '视图启用（0、1）',
  `shortname` varchar(12) CHARACTER SET utf8 NOT NULL COMMENT '视图简称',
  `description` varchar(400) CHARACTER SET utf8 DEFAULT NULL COMMENT '描述',
  `icon` varchar(30) CHARACTER SET utf8 DEFAULT NULL COMMENT '图标名称（不要路径，要扩展名）',
  `image` varchar(30) COLLATE utf8_bin NOT NULL COMMENT '图片名称（不要路径，要扩展名）',
  `modulegroupname` varchar(24) CHARACTER SET utf8 NOT NULL COMMENT '分类属组',
  `url` varchar(200) COLLATE utf8_bin NOT NULL COMMENT '视图url',
  PRIMARY KEY (`moduleid`),
  UNIQUE KEY `modulename_UNIQUE` (`modulename`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `hopemodule` */

/*Table structure for table `hopemodulegroup` */

DROP TABLE IF EXISTS `hopemodulegroup`;

CREATE TABLE `hopemodulegroup` (
  `modulegroupname` varchar(24) CHARACTER SET utf8 NOT NULL COMMENT '视图分类属组ID',
  `moduleid` int(11) NOT NULL COMMENT '视图ID',
  `sequence` int(11) NOT NULL COMMENT '视图位置顺序',
  PRIMARY KEY (`modulegroupname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `hopemodulegroup` */

/*Table structure for table `hopemodulepriv` */

DROP TABLE IF EXISTS `hopemodulepriv`;

CREATE TABLE `hopemodulepriv` (
  `moduleprivid` int(11) NOT NULL COMMENT '视图权限ID',
  `moduleid` int(11) NOT NULL COMMENT '视图ID',
  `privtype` int(11) NOT NULL COMMENT '权限类型（0:aamid/1:deptid/2:privgroupid）',
  `aamid` varchar(10) DEFAULT NULL COMMENT 'privtype为0时有值',
  `deptid` varchar(12) DEFAULT NULL COMMENT 'privtype为1时有值',
  `privgroupid` varchar(30) DEFAULT NULL COMMENT 'privtype为2时有值',
  PRIMARY KEY (`moduleprivid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `hopemodulepriv` */

/*Table structure for table `hopeprivgroup` */

DROP TABLE IF EXISTS `hopeprivgroup`;

CREATE TABLE `hopeprivgroup` (
  `privgroupid` varchar(30) CHARACTER SET utf8 NOT NULL COMMENT '权限组ID',
  `privtype` int(11) NOT NULL COMMENT '权限组类型（0:aamid/1:deptid）',
  `aamid` varchar(10) CHARACTER SET utf8 DEFAULT NULL COMMENT 'privtype为0',
  `deptid` varchar(12) CHARACTER SET utf8 DEFAULT NULL COMMENT 'privtype为1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `hopeprivgroup` */

/*Table structure for table `hopesearchhistory` */

DROP TABLE IF EXISTS `hopesearchhistory`;

CREATE TABLE `hopesearchhistory` (
  `searchhistoryid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增搜索历史ID，清理策略：logtime3月之前',
  `aamid` varchar(10) CHARACTER SET utf8 NOT NULL COMMENT '登录用户统一认证号',
  `searchtext` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '搜索内容',
  `logtime` datetime NOT NULL COMMENT '搜索时间',
  PRIMARY KEY (`searchhistoryid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `hopesearchhistory` */

/*Table structure for table `hopesearchhistory_h` */

DROP TABLE IF EXISTS `hopesearchhistory_h`;

CREATE TABLE `hopesearchhistory_h` (
  `searchhistoryid` int(11) NOT NULL DEFAULT '0' COMMENT '自增搜索历史ID，清理策略：logtime3月之前',
  `aamid` varchar(10) CHARACTER SET utf8 NOT NULL COMMENT '登录用户统一认证号',
  `searchtext` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '搜索内容',
  `logtime` datetime NOT NULL COMMENT '搜索时间'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `hopesearchhistory_h` */

/*Table structure for table `hopeshortcutbar` */

DROP TABLE IF EXISTS `hopeshortcutbar`;

CREATE TABLE `hopeshortcutbar` (
  `shortcutbarid` int(11) NOT NULL COMMENT '快捷方式ID，手动添加',
  `shortcutbarname` varchar(16) CHARACTER SET utf8 NOT NULL COMMENT '快捷方式名称',
  `shortcutbardescript` varchar(60) CHARACTER SET utf8 DEFAULT NULL COMMENT '快捷方式描述',
  `shortcut_image` varchar(30) CHARACTER SET utf8 DEFAULT NULL COMMENT '快捷图片名称，要扩展名',
  PRIMARY KEY (`shortcutbarid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `hopeshortcutbar` */

/*Table structure for table `hopeshortcutbarpriv` */

DROP TABLE IF EXISTS `hopeshortcutbarpriv`;

CREATE TABLE `hopeshortcutbarpriv` (
  `shortcutbarprivid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增快捷方式权限ID',
  `shortcutbarid` int(11) NOT NULL COMMENT '快捷方式ID，查hopeshortcutbarbiao',
  `privtype` int(11) NOT NULL COMMENT '权限类型（0:aamid/1:deptid/2:privid）',
  `aamid` varchar(10) CHARACTER SET latin1 DEFAULT NULL,
  `deptid` varchar(12) CHARACTER SET latin1 DEFAULT NULL,
  `privgroupid` varchar(30) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`shortcutbarprivid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `hopeshortcutbarpriv` */

/*Table structure for table `hopeuser` */

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

/*Data for the table `hopeuser` */

/*Table structure for table `hopeuserconf` */

DROP TABLE IF EXISTS `hopeuserconf`;

CREATE TABLE `hopeuserconf` (
  `aamid` varchar(10) CHARACTER SET utf8 NOT NULL COMMENT '登录用户ID',
  `confid` varchar(15) CHARACTER SET utf8 NOT NULL COMMENT '用户设置ID（收藏展开，视图小图标，隐藏快捷方式）',
  `confvalue` int(11) NOT NULL COMMENT '用户设置内容（0/1）',
  PRIMARY KEY (`aamid`,`confid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `hopeuserconf` */

/*Table structure for table `hopeuserfavor` */

DROP TABLE IF EXISTS `hopeuserfavor`;

CREATE TABLE `hopeuserfavor` (
  `aamid` varchar(10) CHARACTER SET utf8 NOT NULL COMMENT '登录用户统一认证号',
  `moduleid` int(11) NOT NULL COMMENT '视图ID',
  `sequence` int(11) NOT NULL COMMENT '关注视图的顺序',
  PRIMARY KEY (`aamid`,`moduleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `hopeuserfavor` */

/*Table structure for table `hopeuserhistory` */

DROP TABLE IF EXISTS `hopeuserhistory`;

CREATE TABLE `hopeuserhistory` (
  `userhistoryid` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户浏览记录ID',
  `aamid` varchar(10) CHARACTER SET utf8 NOT NULL COMMENT '用户统一认证号',
  `moduleid` int(11) NOT NULL COMMENT '视图ID',
  `logtime` datetime NOT NULL COMMENT '浏览视图时间',
  PRIMARY KEY (`userhistoryid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `hopeuserhistory` */

/*Table structure for table `hopeuserinfo` */

DROP TABLE IF EXISTS `hopeuserinfo`;

CREATE TABLE `hopeuserinfo` (
  `aamid` varchar(10) CHARACTER SET latin1 NOT NULL COMMENT '统一认证号，用户详情',
  `username` varchar(20) CHARACTER SET latin1 NOT NULL COMMENT '用户名',
  `deptid` varchar(12) CHARACTER SET latin1 NOT NULL,
  `deptname` varchar(45) CHARACTER SET latin1 NOT NULL,
  `odeptid` varchar(12) CHARACTER SET latin1 DEFAULT NULL,
  `odeptname` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`aamid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `hopeuserinfo` */

/*Table structure for table `hopeuserlog` */

DROP TABLE IF EXISTS `hopeuserlog`;

CREATE TABLE `hopeuserlog` (
  `userlogid` int(11) NOT NULL AUTO_INCREMENT COMMENT '访问日志记录ID，清理策略：logtime待定',
  `aamid` varchar(10) CHARACTER SET utf8 DEFAULT NULL,
  `moduleid` int(11) DEFAULT NULL,
  `logtime` datetime DEFAULT NULL,
  PRIMARY KEY (`userlogid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `hopeuserlog` */

/*Table structure for table `hopeuserlog_h` */

DROP TABLE IF EXISTS `hopeuserlog_h`;

CREATE TABLE `hopeuserlog_h` (
  `userlogid` int(11) NOT NULL AUTO_INCREMENT,
  `aamid` varchar(10) CHARACTER SET utf8 NOT NULL,
  `moduleid` int(11) NOT NULL,
  `logtime` datetime DEFAULT NULL,
  PRIMARY KEY (`userlogid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `hopeuserlog_h` */

/*Table structure for table `hopeuserlogdetail` */

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

/*Data for the table `hopeuserlogdetail` */

/*Table structure for table `hopeuserlogdetail_h` */

DROP TABLE IF EXISTS `hopeuserlogdetail_h`;

CREATE TABLE `hopeuserlogdetail_h` (
  `userlogdetailid` int(11) NOT NULL DEFAULT '0',
  `aamid` varchar(10) NOT NULL,
  `targetid` varchar(20) NOT NULL,
  `targetname` varchar(20) NOT NULL,
  `operation` varchar(20) NOT NULL,
  `logtime` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `hopeuserlogdetail_h` */

/*Table structure for table `hopewatchgroup` */

DROP TABLE IF EXISTS `hopewatchgroup`;

CREATE TABLE `hopewatchgroup` (
  `watchgroupid` varchar(30) CHARACTER SET utf8 NOT NULL COMMENT '被监控组',
  `privtype` int(11) NOT NULL,
  `aamid` varchar(10) CHARACTER SET utf8 DEFAULT NULL,
  `deptid` varchar(12) CHARACTER SET utf8 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `hopewatchgroup` */

/*Table structure for table `hopewatchgrouppriv` */

DROP TABLE IF EXISTS `hopewatchgrouppriv`;

CREATE TABLE `hopewatchgrouppriv` (
  `watchgroupprivid` varchar(12) CHARACTER SET utf8 NOT NULL COMMENT '监控主语',
  `aamid` varchar(10) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`watchgroupprivid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `hopewatchgrouppriv` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
