/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 5.7.18 : Database - lost_and_found
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`lost_and_found` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;

USE `lost_and_found`;

/*Table structure for table `QRTZ_BLOB_TRIGGERS` */

DROP TABLE IF EXISTS `QRTZ_BLOB_TRIGGERS`;

CREATE TABLE `QRTZ_BLOB_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_BLOB_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `QRTZ_BLOB_TRIGGERS` */

/*Table structure for table `QRTZ_CALENDARS` */

DROP TABLE IF EXISTS `QRTZ_CALENDARS`;

CREATE TABLE `QRTZ_CALENDARS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `QRTZ_CALENDARS` */

/*Table structure for table `QRTZ_CRON_TRIGGERS` */

DROP TABLE IF EXISTS `QRTZ_CRON_TRIGGERS`;

CREATE TABLE `QRTZ_CRON_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_CRON_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `QRTZ_CRON_TRIGGERS` */

/*Table structure for table `QRTZ_FIRED_TRIGGERS` */

DROP TABLE IF EXISTS `QRTZ_FIRED_TRIGGERS`;

CREATE TABLE `QRTZ_FIRED_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`),
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `QRTZ_FIRED_TRIGGERS` */

/*Table structure for table `QRTZ_JOB_DETAILS` */

DROP TABLE IF EXISTS `QRTZ_JOB_DETAILS`;

CREATE TABLE `QRTZ_JOB_DETAILS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `QRTZ_JOB_DETAILS` */

/*Table structure for table `QRTZ_LOCKS` */

DROP TABLE IF EXISTS `QRTZ_LOCKS`;

CREATE TABLE `QRTZ_LOCKS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `QRTZ_LOCKS` */

/*Table structure for table `QRTZ_PAUSED_TRIGGER_GRPS` */

DROP TABLE IF EXISTS `QRTZ_PAUSED_TRIGGER_GRPS`;

CREATE TABLE `QRTZ_PAUSED_TRIGGER_GRPS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `QRTZ_PAUSED_TRIGGER_GRPS` */

/*Table structure for table `QRTZ_SCHEDULER_STATE` */

DROP TABLE IF EXISTS `QRTZ_SCHEDULER_STATE`;

CREATE TABLE `QRTZ_SCHEDULER_STATE` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `QRTZ_SCHEDULER_STATE` */

/*Table structure for table `QRTZ_SIMPLE_TRIGGERS` */

DROP TABLE IF EXISTS `QRTZ_SIMPLE_TRIGGERS`;

CREATE TABLE `QRTZ_SIMPLE_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_SIMPLE_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `QRTZ_SIMPLE_TRIGGERS` */

/*Table structure for table `QRTZ_SIMPROP_TRIGGERS` */

DROP TABLE IF EXISTS `QRTZ_SIMPROP_TRIGGERS`;

CREATE TABLE `QRTZ_SIMPROP_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_SIMPROP_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `QRTZ_SIMPROP_TRIGGERS` */

/*Table structure for table `QRTZ_TRIGGERS` */

DROP TABLE IF EXISTS `QRTZ_TRIGGERS`;

CREATE TABLE `QRTZ_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`),
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  CONSTRAINT `QRTZ_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `QRTZ_JOB_DETAILS` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `QRTZ_TRIGGERS` */

/*Table structure for table `lost_things` */

DROP TABLE IF EXISTS `lost_things`;

CREATE TABLE `lost_things` (
  `ID` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'LostThings ID ??????',
  `Things_Name` varchar(30) NOT NULL COMMENT '????????????',
  `Lost_Place` varchar(30) NOT NULL COMMENT '????????????',
  `Lost_Time` date NOT NULL COMMENT '????????????',
  `Things_Type` varchar(15) NOT NULL COMMENT '????????????',
  `Things_Img` varchar(2000) DEFAULT NULL COMMENT '???????????? ??? null',
  `Things_Des` text COMMENT '????????????',
  `Phone` varchar(30) DEFAULT NULL COMMENT '????????????',
  `QQ` varchar(15) DEFAULT NULL COMMENT 'qq',
  `Email` varchar(30) DEFAULT NULL COMMENT '??????',
  `Publish_Time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '????????????',
  `Status` int(11) NOT NULL DEFAULT '1' COMMENT '?????? 1:????????????0:?????????',
  `Student_Name` varchar(20) NOT NULL COMMENT '?????????',
  `Student_No` bigint(15) NOT NULL COMMENT '??????id',
  `del_status` int(11) NOT NULL DEFAULT '0' COMMENT '???????????? 0:?????????1:??????',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='????????????';


/*Table structure for table `lostthings_relation` */

DROP TABLE IF EXISTS `lostthings_relation`;

CREATE TABLE `lostthings_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `lostthings_id` bigint(20) NOT NULL COMMENT '??????id',
  `lostthings_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `student_id` bigint(20) NOT NULL COMMENT '??????id',
  `student_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='??????????????????';

/*Data for the table `lostthings_relation` */

/*Table structure for table `pick_things` */

DROP TABLE IF EXISTS `pick_things`;

CREATE TABLE `pick_things` (
  `ID` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'LostThings ID ??????',
  `Things_Name` varchar(30) NOT NULL COMMENT '????????????',
  `Pick_Place` varchar(30) NOT NULL COMMENT '????????????',
  `Pick_Time` date NOT NULL COMMENT '????????????',
  `Things_Type` varchar(15) NOT NULL COMMENT '????????????',
  `Things_Img` varchar(2000) DEFAULT NULL COMMENT '????????????',
  `Things_Des` text COMMENT '????????????',
  `Storage_Place` text COMMENT '????????????',
  `Phone` varchar(30) DEFAULT NULL COMMENT '????????????',
  `QQ` varchar(15) DEFAULT NULL COMMENT 'qq',
  `Email` varchar(30) DEFAULT NULL COMMENT '??????',
  `Publish_Time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '????????????',
  `Status` int(11) NOT NULL DEFAULT '1' COMMENT '?????? 1:????????????0:?????????',
  `Student_Name` varchar(20) NOT NULL COMMENT '?????????',
  `Student_No` bigint(15) NOT NULL COMMENT '??????id',
  `del_status` int(1) NOT NULL DEFAULT '0' COMMENT '???????????? 0:?????????1:??????',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='????????????';


/*Table structure for table `pickthings_relation` */

DROP TABLE IF EXISTS `pickthings_relation`;

CREATE TABLE `pickthings_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pickthings_id` bigint(20) NOT NULL COMMENT '??????id',
  `pickthings_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `student_id` bigint(20) NOT NULL COMMENT '??????id',
  `student_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='??????????????????';

/*Data for the table `pickthings_relation` */

insert  into `pickthings_relation`(`id`,`pickthings_id`,`pickthings_name`,`student_id`,`student_name`) values 
(1,16,'??????',21,'?????????');

/*Table structure for table `schedule_job` */

DROP TABLE IF EXISTS `schedule_job`;

CREATE TABLE `schedule_job` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '??????id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean??????',
  `params` varchar(2000) DEFAULT NULL COMMENT '??????',
  `cron_expression` varchar(100) DEFAULT NULL COMMENT 'cron?????????',
  `status` tinyint(4) DEFAULT NULL COMMENT '????????????  0?????????  1?????????',
  `remark` varchar(255) DEFAULT NULL COMMENT '??????',
  `create_time` datetime DEFAULT NULL COMMENT '????????????',
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='????????????';

/*Data for the table `schedule_job` */

/*Table structure for table `schedule_job_log` */

DROP TABLE IF EXISTS `schedule_job_log`;

CREATE TABLE `schedule_job_log` (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '????????????id',
  `job_id` bigint(20) NOT NULL COMMENT '??????id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean??????',
  `params` varchar(2000) DEFAULT NULL COMMENT '??????',
  `status` tinyint(4) NOT NULL COMMENT '????????????    0?????????    1?????????',
  `error` varchar(2000) DEFAULT NULL COMMENT '????????????',
  `times` int(11) NOT NULL COMMENT '??????(???????????????)',
  `create_time` datetime DEFAULT NULL COMMENT '????????????',
  PRIMARY KEY (`log_id`),
  KEY `job_id` (`job_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='??????????????????';

/*Data for the table `schedule_job_log` */

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` bigint(15) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `Student_No` bigint(15) NOT NULL COMMENT '??????',
  `Student_Name` varchar(16) NOT NULL COMMENT '??????',
  `Password` varchar(100) NOT NULL COMMENT '??????',
  `Class_Name` varchar(10) NOT NULL COMMENT '??????',
  `Specialty_Name` varchar(20) NOT NULL COMMENT '??????',
  `Phone` char(12) NOT NULL COMMENT '??????',
  `QQ` varchar(15) DEFAULT NULL COMMENT 'qq',
  `Email` varchar(30) DEFAULT NULL COMMENT '??????',
  `Is_New` int(11) NOT NULL DEFAULT '0' COMMENT '???????????????0??? ????????????1???',
  `salt` varchar(20) DEFAULT NULL COMMENT '???',
  `del_status` int(11) NOT NULL DEFAULT '0' COMMENT '???????????? 0:?????????1:??????',
  PRIMARY KEY (`id`),
  UNIQUE KEY `Student_No` (`Student_No`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='??????';


/*Table structure for table `student_token` */

DROP TABLE IF EXISTS `student_token`;

CREATE TABLE `student_token` (
  `user_id` bigint(20) NOT NULL,
  `token` varchar(100) NOT NULL COMMENT 'token',
  `expire_time` datetime DEFAULT NULL COMMENT '????????????',
  `update_time` datetime DEFAULT NULL COMMENT '????????????',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='????????????Token';

/*Data for the table `student_token` */

/*Table structure for table `sys_captcha` */

DROP TABLE IF EXISTS `sys_captcha`;

CREATE TABLE `sys_captcha` (
  `uuid` char(36) NOT NULL COMMENT 'uuid',
  `code` varchar(6) NOT NULL COMMENT '?????????',
  `expire_time` datetime DEFAULT NULL COMMENT '????????????',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='???????????????';


/*Table structure for table `sys_config` */

DROP TABLE IF EXISTS `sys_config`;

CREATE TABLE `sys_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `param_key` varchar(50) DEFAULT NULL COMMENT 'key',
  `param_value` varchar(2000) DEFAULT NULL COMMENT 'value',
  `status` tinyint(4) DEFAULT '1' COMMENT '??????   0?????????   1?????????',
  `remark` varchar(500) DEFAULT NULL COMMENT '??????',
  PRIMARY KEY (`id`),
  UNIQUE KEY `param_key` (`param_key`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='?????????????????????';


/*Table structure for table `sys_log` */

DROP TABLE IF EXISTS `sys_log`;

CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '?????????',
  `operation` varchar(50) DEFAULT NULL COMMENT '????????????',
  `method` varchar(200) DEFAULT NULL COMMENT '????????????',
  `params` varchar(5000) DEFAULT NULL COMMENT '????????????',
  `time` bigint(20) NOT NULL COMMENT '????????????(??????)',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP??????',
  `create_date` datetime DEFAULT NULL COMMENT '????????????',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8mb4 COMMENT='????????????';


/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '?????????ID??????????????????0',
  `name` varchar(50) DEFAULT NULL COMMENT '????????????',
  `url` varchar(200) DEFAULT NULL COMMENT '??????URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '??????(??????????????????????????????user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '??????   0?????????   1?????????   2?????????',
  `icon` varchar(50) DEFAULT NULL COMMENT '????????????',
  `order_num` int(11) DEFAULT NULL COMMENT '??????',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8mb4 COMMENT='????????????';

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`menu_id`,`parent_id`,`name`,`url`,`perms`,`type`,`icon`,`order_num`) values 
(1,0,'????????????',NULL,NULL,0,'system',0),
(2,31,'???????????????','sys/user',NULL,1,'admin',1),
(3,1,'????????????','sys/role',NULL,1,'role',2),
(4,1,'????????????','sys/menu',NULL,1,'menu',3),
(5,1,'SQL??????','http://localhost:8080/renren-fast/druid/sql.html',NULL,1,'sql',4),
(6,1,'????????????','job/schedule',NULL,1,'job',5),
(7,6,'??????',NULL,'sys:schedule:list,sys:schedule:info',2,NULL,0),
(8,6,'??????',NULL,'sys:schedule:save',2,NULL,0),
(9,6,'??????',NULL,'sys:schedule:update',2,NULL,0),
(10,6,'??????',NULL,'sys:schedule:delete',2,NULL,0),
(11,6,'??????',NULL,'sys:schedule:pause',2,NULL,0),
(12,6,'??????',NULL,'sys:schedule:resume',2,NULL,0),
(13,6,'????????????',NULL,'sys:schedule:run',2,NULL,0),
(14,6,'????????????',NULL,'sys:schedule:log',2,NULL,0),
(15,2,'??????',NULL,'sys:user:list,sys:user:info',2,NULL,0),
(16,2,'??????',NULL,'sys:user:save,sys:role:select',2,NULL,0),
(17,2,'??????',NULL,'sys:user:update,sys:role:select',2,NULL,0),
(18,2,'??????',NULL,'sys:user:delete',2,NULL,0),
(19,3,'??????',NULL,'sys:role:list,sys:role:info',2,NULL,0),
(20,3,'??????',NULL,'sys:role:save,sys:menu:list',2,NULL,0),
(21,3,'??????',NULL,'sys:role:update,sys:menu:list',2,NULL,0),
(22,3,'??????',NULL,'sys:role:delete',2,NULL,0),
(23,4,'??????',NULL,'sys:menu:list,sys:menu:info',2,NULL,0),
(24,4,'??????',NULL,'sys:menu:save,sys:menu:select',2,NULL,0),
(25,4,'??????',NULL,'sys:menu:update,sys:menu:select',2,NULL,0),
(26,4,'??????',NULL,'sys:menu:delete',2,NULL,0),
(27,1,'????????????','sys/config','sys:config:list,sys:config:info,sys:config:save,sys:config:update,sys:config:delete',1,'config',6),
(29,1,'????????????','sys/log','sys:log:list',1,'log',7),
(30,1,'????????????','oss/oss','sys:oss:all',1,'oss',6),
(31,0,'????????????','','',0,'role',1),
(76,31,'????????????','admin/student','',1,'admin',2),
(77,0,'????????????','','',0,'zonghe',0),
(78,77,'????????????','admin/pickthings','',1,'log',1),
(79,77,'????????????','admin/lostthings','',1,'log',2);

/*Table structure for table `sys_oss` */

DROP TABLE IF EXISTS `sys_oss`;

CREATE TABLE `sys_oss` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(200) DEFAULT NULL COMMENT 'URL??????',
  `create_date` datetime DEFAULT NULL COMMENT '????????????',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='????????????';


/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '????????????',
  `remark` varchar(100) DEFAULT NULL COMMENT '??????',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '?????????ID',
  `create_time` datetime DEFAULT NULL COMMENT '????????????',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='??????';

/*Data for the table `sys_role` */

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '??????ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '??????ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='???????????????????????????';

/*Data for the table `sys_role_menu` */

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '?????????',
  `password` varchar(100) DEFAULT NULL COMMENT '??????',
  `salt` varchar(20) DEFAULT NULL COMMENT '???',
  `email` varchar(100) DEFAULT NULL COMMENT '??????',
  `mobile` varchar(100) DEFAULT NULL COMMENT '?????????',
  `status` tinyint(4) DEFAULT NULL COMMENT '??????  0?????????   1?????????',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '?????????ID',
  `create_time` datetime DEFAULT NULL COMMENT '????????????',
  `del_status` int(11) NOT NULL DEFAULT '0' COMMENT '???????????? 0:?????????1:??????',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='????????????';


/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '??????ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '??????ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='???????????????????????????';

/*Data for the table `sys_user_role` */

/*Table structure for table `sys_user_token` */

DROP TABLE IF EXISTS `sys_user_token`;

CREATE TABLE `sys_user_token` (
  `user_id` bigint(20) NOT NULL,
  `token` varchar(100) NOT NULL COMMENT 'token',
  `expire_time` datetime DEFAULT NULL COMMENT '????????????',
  `update_time` datetime DEFAULT NULL COMMENT '????????????',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='????????????Token';

/*Data for the table `sys_user_token` */

insert  into `sys_user_token`(`user_id`,`token`,`expire_time`,`update_time`) values 
(1,'9fbec62b253be93796a84c05bbddf706','2021-05-20 08:52:22','2021-05-19 20:52:22');

/*Table structure for table `tb_user` */

DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '?????????',
  `mobile` varchar(20) NOT NULL COMMENT '?????????',
  `password` varchar(64) DEFAULT NULL COMMENT '??????',
  `create_time` datetime DEFAULT NULL COMMENT '????????????',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='??????';

/*Data for the table `tb_user` */

insert  into `tb_user`(`user_id`,`username`,`mobile`,`password`,`create_time`) values 
(1,'mark','13612345678','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918','2017-03-23 22:37:41');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID??????',
  `User_Name` varchar(20) NOT NULL COMMENT '?????????',
  `Password` varchar(50) NOT NULL COMMENT '??????',
  `Nick_Name` varchar(20) DEFAULT NULL COMMENT '??????',
  `Status` int(11) NOT NULL DEFAULT '1' COMMENT '??????',
  `Create_Time` datetime NOT NULL COMMENT '????????????',
  `Is_New` int(11) NOT NULL DEFAULT '0' COMMENT '???????????????0??? ????????????1???',
  `LastLogin_Time` datetime DEFAULT NULL COMMENT '????????????????????????',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE KEY `User_Name` (`User_Name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='?????????';

/*Data for the table `user` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
