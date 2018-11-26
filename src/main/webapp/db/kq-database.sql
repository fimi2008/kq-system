/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.6.24 : Database - kq-database
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`kq-database` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `kq-database`;

/*Table structure for table `kq_menu` */

DROP TABLE IF EXISTS `kq_menu`;

CREATE TABLE `kq_menu` (
  `id` bigint(20) NOT NULL,
  `text` varchar(20) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单跳转路径',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级菜单',
  `note` varchar(200) DEFAULT NULL COMMENT '备注',
  `icon` varchar(20) DEFAULT NULL COMMENT '图标',
  PRIMARY KEY (`id`),
  UNIQUE KEY `menu_text_unique` (`text`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='个人考勤系统菜单表';

/*Data for the table `kq_menu` */

/*Table structure for table `kq_order_dinner` */

DROP TABLE IF EXISTS `kq_order_dinner`;

CREATE TABLE `kq_order_dinner` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `order_date` date DEFAULT NULL COMMENT '点餐日期',
  `note` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '点餐备注',
  `publish_user_id` bigint(20) DEFAULT NULL COMMENT '点餐发起人id',
  `publish_time` datetime DEFAULT NULL COMMENT '点餐发起时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '点餐创建时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '点餐创建人id',
  `state` int(1) DEFAULT NULL COMMENT '0-初始化,1-已发布,-1-已作废,2-已完成',
  `last_update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `end_time` datetime DEFAULT NULL COMMENT '点餐截止时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='加班晚餐发起表';

/*Data for the table `kq_order_dinner` */

/*Table structure for table `kq_order_dinner_option_template` */

DROP TABLE IF EXISTS `kq_order_dinner_option_template`;

CREATE TABLE `kq_order_dinner_option_template` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL COMMENT '选项标题',
  `note` text COMMENT '详细说明(富文本信息)',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建用户id',
  `last_update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `last_update_user_id` bigint(20) DEFAULT NULL COMMENT '最后更新用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='点餐选项模板';

/*Data for the table `kq_order_dinner_option_template` */

/*Table structure for table `kq_order_dinner_options` */

DROP TABLE IF EXISTS `kq_order_dinner_options`;

CREATE TABLE `kq_order_dinner_options` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) DEFAULT NULL COMMENT '点餐id',
  `temp_id` varchar(20) DEFAULT NULL COMMENT '点餐选项模板id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='点餐选项表';

/*Data for the table `kq_order_dinner_options` */

/*Table structure for table `kq_overtime` */

DROP TABLE IF EXISTS `kq_overtime`;

CREATE TABLE `kq_overtime` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `work_date` date DEFAULT NULL COMMENT '加班日期',
  `start_time` varchar(5) DEFAULT NULL COMMENT '开始时间',
  `end_time` varchar(5) DEFAULT NULL COMMENT '结束时间',
  `is_del` tinyint(1) DEFAULT '0' COMMENT '是否删除(0-否,1-是)',
  `note` varchar(200) DEFAULT NULL COMMENT '备注',
  `total_house` double DEFAULT NULL COMMENT '总计(单位:小时)',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='考勤加班表';

/*Data for the table `kq_overtime` */

insert  into `kq_overtime`(`id`,`user_id`,`work_date`,`start_time`,`end_time`,`is_del`,`note`,`total_house`,`create_time`,`last_update_time`) values (1,1,'2016-07-07','19:00','21:00',0,'周四加班',2,'2016-07-11 13:23:10','2016-07-11 19:57:56'),(2,1,'2016-07-06','19:00','21:00',0,'周三加班',2,'2016-07-11 13:33:45','2016-07-11 19:57:44'),(3,1,'2016-07-11','19:00','21:00',0,'周一加班',2,'2016-07-12 10:29:39',NULL),(5,1,'2016-07-12','19:00','21:00',0,'周二加班',2,'2016-07-12 18:07:55',NULL),(6,1,'2016-07-13','19:00','21:00',0,'周三加班',2,'2016-07-15 09:11:07',NULL),(7,1,'2016-07-14','19:00','20:00',0,'周四加班(可忽略)',1,'2016-07-15 09:11:42',NULL),(8,1,'2016-07-18','19:00','21:30',0,'周一加班.走迟了',2.5,'2016-07-19 09:42:46',NULL),(9,1,'2016-07-25','19:00','21:00',0,'',2,'2016-07-27 10:57:27',NULL),(10,1,'2016-07-26','19:00','21:00',0,'',2,'2016-07-27 10:57:40',NULL),(11,1,'2016-07-19','19:00','21:00',0,'',2,'2016-07-27 10:59:29',NULL),(12,1,'2016-07-21','19:00','21:00',0,'',2,'2016-07-27 10:59:48',NULL);

/*Table structure for table `kq_user` */

DROP TABLE IF EXISTS `kq_user`;

CREATE TABLE `kq_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account` varchar(20) DEFAULT NULL COMMENT '账号',
  `cname` varchar(20) DEFAULT NULL COMMENT '中文名',
  `ename` varchar(20) DEFAULT NULL COMMENT '花名',
  `password` varchar(36) DEFAULT NULL COMMENT '密码',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `last_login_ip` varchar(15) DEFAULT NULL COMMENT '最后登陆ip',
  `sex` int(1) DEFAULT '0' COMMENT '性别(0-保密,1男,2-女)',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`),
  UNIQUE KEY `kq_user_account_unique` (`account`),
  UNIQUE KEY `kq_user_mobile_unique` (`mobile`),
  UNIQUE KEY `kq_user_email_unique` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='考勤用户表';

/*Data for the table `kq_user` */

insert  into `kq_user`(`id`,`account`,`cname`,`ename`,`password`,`create_time`,`last_login_time`,`last_login_ip`,`sex`,`mobile`,`email`) values (1,'lionxxw','王翔','王翦','b2098805d78de8cc015a7ebd34046142','2016-07-08 11:31:20','2016-07-27 10:56:59','0:0:0:0:0:0:0:1',1,'18721472363','lionxxw');

/*Table structure for table `kq_user_order_dinner_result` */

DROP TABLE IF EXISTS `kq_user_order_dinner_result`;

CREATE TABLE `kq_user_order_dinner_result` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `order_id` bigint(20) DEFAULT NULL COMMENT '点餐id',
  `option_id` bigint(20) DEFAULT NULL COMMENT '点餐选项id',
  `user_cname` varchar(20) DEFAULT NULL COMMENT '中文名',
  `user_ename` varchar(20) DEFAULT NULL COMMENT '花名',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `state` int(1) DEFAULT '1' COMMENT '1-已点,-1-已取消',
  `is_append` tinyint(1) DEFAULT '0' COMMENT '是否补录(默认否)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户点餐结果表';

/*Data for the table `kq_user_order_dinner_result` */

/*Table structure for table `kq_working_log` */

DROP TABLE IF EXISTS `kq_working_log`;

CREATE TABLE `kq_working_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `work_date` date DEFAULT NULL COMMENT '工作日期',
  `note` text COMMENT '工作日志内容(富文本信息)',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `is_del` tinyint(1) DEFAULT '0' COMMENT '是否删除(0-否,1-是)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='考勤工作日志表';

/*Data for the table `kq_working_log` */

insert  into `kq_working_log`(`id`,`user_id`,`work_date`,`note`,`create_time`,`last_update_time`,`is_del`) values (1,1,'2016-07-12','<ol>\r\n    <li>&nbsp;我可以假装没有</li>\r\n    <li>我可以习惯一个人生活</li>\r\n    <li>你的承诺</li>\r\n    <li>我就我</li>\r\n</ol>','2016-07-12 13:47:18','2016-07-12 13:50:08',0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
