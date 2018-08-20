CREATE database if NOT EXISTS `xxl-job_2` default character set utf8 collate utf8_general_ci;
use `xxl-job_2`;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `xxl_job_qrtz_trigger_group`
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_trigger_group`;
CREATE TABLE `xxl_job_qrtz_trigger_group` (
  `job_group` bigint(20) NOT NULL AUTO_INCREMENT,
  `app_name` varchar(64) NOT NULL COMMENT ''执行器AppName'',
  `title` varchar(12) NOT NULL COMMENT ''执行器名称'',
  `order` tinyint(4) NOT NULL DEFAULT ''0'' COMMENT ''排序'',
  `address_type` tinyint(4) NOT NULL DEFAULT ''0'' COMMENT ''执行器地址类型：0=自动注册、1=手动录入'',
  `address_list` varchar(512) DEFAULT NULL COMMENT ''执行器地址列表，多地址逗号分隔'',
  PRIMARY KEY (`job_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xxl_job_qrtz_trigger_group
-- ----------------------------

-- ----------------------------
-- Table structure for `xxl_job_qrtz_trigger_info`
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_trigger_info`;
CREATE TABLE `xxl_job_qrtz_trigger_info` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `job_group` bigint(20) NOT NULL COMMENT ''执行器主键ID'',
  `job_cron` varchar(128) NOT NULL COMMENT ''任务执行CRON'',
  `job_desc` varchar(255) NOT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `author` varchar(64) DEFAULT NULL COMMENT ''作者'',
  `alarm_email` varchar(255) DEFAULT NULL COMMENT ''报警邮件'',
  `executor_route_strategy` varchar(50) DEFAULT NULL COMMENT ''执行器路由策略'',
  `executor_handler` varchar(255) DEFAULT NULL COMMENT ''执行器任务handler'',
  `executor_param` varchar(512) DEFAULT NULL COMMENT ''执行器任务参数'',
  `executor_block_strategy` varchar(50) DEFAULT NULL COMMENT ''阻塞处理策略'',
  `executor_fail_strategy` varchar(50) DEFAULT NULL COMMENT ''失败处理策略'',
  `executor_timeout` int(11) NOT NULL DEFAULT ''0'' COMMENT ''任务执行超时时间，单位秒'',
  `glue_type` varchar(50) NOT NULL COMMENT ''GLUE类型'',
  `glue_source` text COMMENT ''GLUE源代码'',
  `glue_remark` varchar(128) DEFAULT NULL COMMENT ''GLUE备注'',
  `glue_updatetime` datetime DEFAULT NULL COMMENT ''GLUE更新时间'',
  `child_jobid` varchar(255) DEFAULT NULL COMMENT ''子任务ID，多个逗号分隔'',
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xxl_job_qrtz_trigger_info
-- ----------------------------

-- ----------------------------
-- Table structure for `xxl_job_qrtz_trigger_logglue`
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_trigger_logglue`;
CREATE TABLE `xxl_job_qrtz_trigger_logglue` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `job_group` bigint(20) NOT NULL,
  `job_id` bigint(20) NOT NULL COMMENT ''任务，主键ID'',
  `glue_type` varchar(50) DEFAULT NULL COMMENT ''GLUE类型'',
  `glue_source` text COMMENT ''GLUE源代码'',
  `glue_remark` varchar(128) NOT NULL COMMENT ''GLUE备注'',
  `add_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xxl_job_qrtz_trigger_logglue
-- ----------------------------

-- ----------------------------
-- Table structure for `xxl_job_qrtz_trigger_log_0`
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_trigger_log_0`;
CREATE TABLE `xxl_job_qrtz_trigger_log_0` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `job_group` bigint(20) NOT NULL COMMENT ''执行器主键ID'',
  `job_id` bigint(20) NOT NULL COMMENT ''任务，主键ID'',
  `glue_type` varchar(50) DEFAULT NULL COMMENT ''GLUE类型'',
  `executor_address` varchar(255) DEFAULT NULL COMMENT ''执行器地址，本次执行的地址'',
  `executor_handler` varchar(255) DEFAULT NULL COMMENT ''执行器任务handler'',
  `executor_param` varchar(512) DEFAULT NULL COMMENT ''执行器任务参数'',
  `trigger_time` datetime DEFAULT NULL COMMENT ''调度-时间'',
  `trigger_code` int(11) NOT NULL COMMENT ''调度-结果'',
  `trigger_msg` varchar(2048) DEFAULT NULL COMMENT ''调度-日志'',
  `handle_time` datetime DEFAULT NULL COMMENT ''执行-时间'',
  `handle_code` int(11) NOT NULL COMMENT ''执行-状态'',
  `handle_msg` varchar(2048) DEFAULT NULL COMMENT ''执行-日志'',
  PRIMARY KEY (`id`),
  KEY `I_trigger_time` (`trigger_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xxl_job_qrtz_trigger_log_0
-- ----------------------------

-- ----------------------------
-- Table structure for `xxl_job_qrtz_trigger_log_1`
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_trigger_log_1`;
CREATE TABLE `xxl_job_qrtz_trigger_log_1` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `job_group` bigint(20) NOT NULL COMMENT ''执行器主键ID'',
  `job_id` bigint(20) NOT NULL COMMENT ''任务，主键ID'',
  `glue_type` varchar(50) DEFAULT NULL COMMENT ''GLUE类型'',
  `executor_address` varchar(255) DEFAULT NULL COMMENT ''执行器地址，本次执行的地址'',
  `executor_handler` varchar(255) DEFAULT NULL COMMENT ''执行器任务handler'',
  `executor_param` varchar(512) DEFAULT NULL COMMENT ''执行器任务参数'',
  `trigger_time` datetime DEFAULT NULL COMMENT ''调度-时间'',
  `trigger_code` int(11) NOT NULL COMMENT ''调度-结果'',
  `trigger_msg` varchar(2048) DEFAULT NULL COMMENT ''调度-日志'',
  `handle_time` datetime DEFAULT NULL COMMENT ''执行-时间'',
  `handle_code` int(11) NOT NULL COMMENT ''执行-状态'',
  `handle_msg` varchar(2048) DEFAULT NULL COMMENT ''执行-日志'',
  PRIMARY KEY (`id`),
  KEY `I_trigger_time` (`trigger_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xxl_job_qrtz_trigger_log_1
-- ----------------------------

-- ----------------------------
-- Table structure for `xxl_job_qrtz_trigger_registry`
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_trigger_registry`;
CREATE TABLE `xxl_job_qrtz_trigger_registry` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `registry_group` varchar(255) NOT NULL,
  `job_group` bigint(20) NOT NULL,
  `registry_key` varchar(255) NOT NULL,
  `registry_value` varchar(255) NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xxl_job_qrtz_trigger_registry
-- ----------------------------