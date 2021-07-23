
-- 菜单表
CREATE TABLE IF NOT EXISTS `menu` (
  `id` INTEGER primary key AUTOINCREMENT,
  `pid` smallint(20) DEFAULT 0,
  `name` varchar(60)  NOT NULL ,
  `type` smallint(6) DEFAULT 1 , -- 菜单类型 0：目录 1、菜单 2、按钮
  `icon` varchar(20)  DEFAULT '#' ,
  `perms` varchar(100)  DEFAULT NULL ,
  `url` varchar(200)  DEFAULT '#' ,
  `sort` smallint(4) DEFAULT 0,
  `status` smallint(6) DEFAULT 0, -- 状态 0：禁用， 1：正常
  `remark` varchar(500)  DEFAULT null ,

  `create_by` varchar(64)  DEFAULT null ,
  `create_datetime` datetime DEFAULT (datetime('now', 'localtime')),
  `update_by` varchar(64)  DEFAULT null ,
  `update_datetime` datetime DEFAULT (datetime('now', 'localtime'))
) ;

-- 配置分组表
-- CREATE TABLE IF NOT EXISTS `sys_config_group` (
--   `id` INTEGER primary key AUTOINCREMENT,
--   `key` varchar(50)  NOT NULL ,
--   `name` varchar(100)  NOT NULL ,
--   `remark` varchar(500)  DEFAULT null ,
--
--   `create_by` varchar(64)  DEFAULT null ,
--   `create_datetime` datetime DEFAULT (datetime('now', 'localtime')),
--   `update_by` varchar(64)  DEFAULT null ,
--   `update_datetime` datetime DEFAULT (datetime('now', 'localtime'))
-- ) ;

-- 配置表
-- CREATE TABLE IF NOT EXISTS `sys_config` (
--   `id` INTEGER primary key AUTOINCREMENT,
--   `key` varchar(50)  NOT NULL ,
--   `name` varchar(100)  NOT NULL ,
--   `value` varchar(500)  NOT NULL ,
--   `remark` varchar(500)  DEFAULT null ,
--   `group_id` smallint(20) DEFAULT 0,
--
--   `create_by` varchar(64)  DEFAULT null ,
--   `create_datetime` datetime DEFAULT (datetime('now', 'localtime')),
--   `update_by` varchar(64)  DEFAULT null ,
--   `update_datetime` datetime DEFAULT (datetime('now', 'localtime'))
-- ) ;