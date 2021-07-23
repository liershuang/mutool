
-- 配置分组表
CREATE TABLE IF NOT EXISTS `sys_config_group` (
  `id` INTEGER primary key AUTOINCREMENT,
  `key` varchar(50)  NOT NULL ,
  `name` varchar(100)  NOT NULL ,
  `remark` varchar(500)  DEFAULT null ,

  `create_by` varchar(64)  DEFAULT null ,
  `create_datetime` datetime DEFAULT (datetime('now', 'localtime')),
  `update_by` varchar(64)  DEFAULT null ,
  `update_datetime` datetime DEFAULT (datetime('now', 'localtime'))
) ;

-- 配置表
CREATE TABLE IF NOT EXISTS `sys_config` (
  `id` INTEGER primary key AUTOINCREMENT,
  `key` varchar(50)  NOT NULL ,
  `name` varchar(100)  NOT NULL ,
  `value` varchar(500)  NOT NULL ,
  `remark` varchar(500)  DEFAULT null ,
  `group_id` smallint(20) DEFAULT 0,

  `create_by` varchar(64)  DEFAULT null ,
  `create_datetime` datetime DEFAULT (datetime('now', 'localtime')),
  `update_by` varchar(64)  DEFAULT null ,
  `update_datetime` datetime DEFAULT (datetime('now', 'localtime'))
) ;