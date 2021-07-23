
INSERT INTO menu (update_datetime, update_by, create_datetime, create_by, remark, status, sort, url, perms, icon, type, name, pid)
select datetime('now', 'localtime'), 'root', datetime('now', 'localtime'), 'root', NULL, 1, 20, NULL, '#', 'layui-icon-set-fill', 0, '系统管理', 0
where not exists (select 1 from menu where name = '系统管理');

INSERT INTO menu (update_datetime, update_by, create_datetime, create_by, remark, status, sort, url, perms, icon, type, name, pid)
select datetime('now', 'localtime'), 'root', datetime('now', 'localtime'), 'root', NULL, 1, 3, 'views/menu_list.html', '#', 'layui-icon-form', 1, '菜单管理', (select id from menu where name = '系统管理')
where not exists (select 1 from menu where name = '菜单管理');

INSERT INTO menu (update_datetime, update_by, create_datetime, create_by, remark, status, sort, url, perms, icon, type, name, pid)
select datetime('now', 'localtime'), 'root', datetime('now', 'localtime'), 'root', NULL, 1, 4, 'views/config_group_list.html', '#', 'layui-icon-set', 1, '配置管理', (select id from menu where name = '系统管理')
where not exists (select 1 from menu where name = '配置管理');



