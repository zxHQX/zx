   

/*
Date: 2019-12-01 12:00:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_dict`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type_id` bigint(20) unsigned NOT NULL COMMENT '类型ID',
  `sort_num` int(10) unsigned NOT NULL DEFAULT '1' COMMENT '排序',
  `name` varchar(80) DEFAULT NULL COMMENT '名称',
  `value` varchar(80) DEFAULT NULL COMMENT '值',
  `des` varchar(512) DEFAULT NULL COMMENT '描述',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态，1 未删除，0 禁用，-1 删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `value_index` (`value`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------

-- 性别
INSERT INTO `sys_dict` VALUES (null, '1', '1','男','1','',1,now(), now());
INSERT INTO `sys_dict` VALUES (null, '1', '2','女','2','',1,now(), now());

-- 记录状态
INSERT INTO `sys_dict` VALUES (null, '2', '1','可用','1','',1,now(), now());
INSERT INTO `sys_dict` VALUES (null, '2', '2','禁用','0','',1,now(), now());
INSERT INTO `sys_dict` VALUES (null, '2', '3','删除','-1','',1,now(), now());

-- 图元分类
INSERT INTO `sys_dict` VALUES (null, '3', '0','全部','0','',1,now(), now());
INSERT INTO `sys_dict` VALUES (null, '3', '1','设备','1','',1,now(), now());
INSERT INTO `sys_dict` VALUES (null, '3', '2','塔杆','2','',1,now(), now());
INSERT INTO `sys_dict` VALUES (null, '3', '3','导线','3','',1,now(), now());
INSERT INTO `sys_dict` VALUES (null, '3', '4','变压器','4','',1,now(), now());
INSERT INTO `sys_dict` VALUES (null, '3', '5','开关','5','',1,now(), now());

-- 启用状态
INSERT INTO `sys_dict` VALUES (null, '4', '1','使用','1','',1,now(), now());
INSERT INTO `sys_dict` VALUES (null, '4', '2','停用','2','',1,now(), now());

-- 电压级别
INSERT INTO `sys_dict` VALUES (null, '5', '220','使用','220V','',1,now(), now());
INSERT INTO `sys_dict` VALUES (null, '5', '380','使用','380V','',1,now(), now());

-- 模型类型
INSERT INTO `sys_dict` VALUES (null, '6', '1','模型1','模型描述1','1',1,now(), now());
INSERT INTO `sys_dict` VALUES (null, '6', '2','模型2','模型描述2','2',1,now(), now());

-- 模型状态
INSERT INTO `sys_dict` VALUES (null, '7', '0','创建中','0','新建模型',1,now(), now());
INSERT INTO `sys_dict` VALUES (null, '7', '1','待审批','1','发布模型，等待审批',1,now(), now());
INSERT INTO `sys_dict` VALUES (null, '7', '2','拒绝','2','拒绝使用',1,now(), now());
INSERT INTO `sys_dict` VALUES (null, '7', '3','停用','3','停止使用',1,now(), now());
INSERT INTO `sys_dict` VALUES (null, '7', '4','使用中','4','允许使用',1,now(), now());
INSERT INTO `sys_dict` VALUES (null, '7', '5','删除','-1','删除标记',1,now(), now());

-- 图数据类型
INSERT INTO `sys_dict` VALUES (null, '8', '1','int8','1','8位整数',1,now(), now());
INSERT INTO `sys_dict` VALUES (null, '8', '2','int16','2','16位整数',1,now(), now());
INSERT INTO `sys_dict` VALUES (null, '8', '3','int32','3','32位整数',1,now(), now());
INSERT INTO `sys_dict` VALUES (null, '8', '4','int64','4','64位整数',1,now(), now());
INSERT INTO `sys_dict` VALUES (null, '8', '5','float','5','浮点数',1,now(), now());
INSERT INTO `sys_dict` VALUES (null, '8', '6','double','6','双进度浮点数',1,now(), now());
INSERT INTO `sys_dict` VALUES (null, '8', '0','string','7','字符串',1,now(), now());

-- 图对象类型
INSERT INTO `sys_dict` VALUES (null, '9', '0','名称','0','图节点名称',1,now(), now());
INSERT INTO `sys_dict` VALUES (null, '9', '1','主键','1','主键属性',1,now(), now());
INSERT INTO `sys_dict` VALUES (null, '9', '2','点','2','节点',1,now(), now());
INSERT INTO `sys_dict` VALUES (null, '9', '3','边','3','边',1,now(), now());
INSERT INTO `sys_dict` VALUES (null, '9', '4','有向边','4','有向边',1,now(), now());


-- ----------------------------
-- Table structure for `sys_dict_type`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type` (
  `id` bigint(20) unsigned NOT NULL,
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '字典类型名称',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态，1 未删除，0 禁用，-1 删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典类型表';



-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (1, '性别', '1',now(), now());
INSERT INTO `sys_dict_type` VALUES (2, '记录状态', '1',now(), now());
INSERT INTO `sys_dict_type` VALUES (3, '图元分类', '1',now(), now());
INSERT INTO `sys_dict_type` VALUES (4, '图元状态', '1',now(), now());
INSERT INTO `sys_dict_type` VALUES (5, '电压级别', '1',now(), now());
INSERT INTO `sys_dict_type` VALUES (6, '模型类型', '1',now(), now());
INSERT INTO `sys_dict_type` VALUES (7, '模型状态', '1',now(), now());
INSERT INTO `sys_dict_type` VALUES (8, '图数据类型', '1',now(), now());
INSERT INTO `sys_dict_type` VALUES (9, '图对象类型', '1',now(), now());

-- ----------------------------
-- Table structure for `sys_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) unsigned NOT NULL COMMENT '日志信息表主键',
  `type` int(4) DEFAULT NULL COMMENT '日志类型（1:增，2:删，3:改，4:查）',
  `desc_info` varchar(512) DEFAULT NULL COMMENT '日志说明（日志数据）',
  `remark` varchar(512) DEFAULT NULL COMMENT '操作详情',
  `adr` varchar(128) DEFAULT NULL COMMENT '操作人IP地址',
  `backups` tinyint(4) NOT NULL DEFAULT '1' COMMENT '日志备份状态，1：未备份，2：已备份',
  `is_success` tinyint(4) DEFAULT '1' COMMENT '执行结果，1：成功，2：失败',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日志信息表';

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_res`
-- ----------------------------
DROP TABLE IF EXISTS `sys_res`;
CREATE TABLE `sys_res` (
  `id` bigint(20) unsigned NOT NULL,
  `pid` bigint(20) unsigned NOT NULL DEFAULT '0',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '在vue中name表示路由名称，非vue是资源名称',
  `permission` varchar(100) NOT NULL DEFAULT '',
  `url` varchar(200) NOT NULL DEFAULT '' COMMENT 'vue中表示路由的path',
  `sort_num` int(10) DEFAULT NULL COMMENT '排序',
  `attach` tinyint(1) NOT NULL DEFAULT '1' COMMENT 'attach(隶属字段)1:管理端，2:应用端，3：移动端 ',
  `icon1` varchar(200) DEFAULT NULL COMMENT 'PC菜单图片',
  `icon2` varchar(200) DEFAULT NULL COMMENT 'APP菜单图片',
  `pids` varchar(500) NOT NULL DEFAULT '' COMMENT 'TreeTable排序',
  `type` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '1 菜单 2 按钮',
  `des` varchar(255) DEFAULT NULL COMMENT '描述',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态，1 未删除，0 禁用，-1 删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源表';

-- ----------------------------
-- Records of sys_res
-- ----------------------------
INSERT INTO `sys_res` VALUES ('1', '0', '首页', 'home', 'home', 1, '1', 'el-icon-menu', '', '', '2', '首页', '1', now(), now());



-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) unsigned NOT NULL,
  `name` varchar(55) NOT NULL DEFAULT '角色名称',
  `code` varchar(100) NOT NULL DEFAULT '角色编码:词典',
  `sort_num` int(10) unsigned NOT NULL DEFAULT '1',
  `des` varchar(100) DEFAULT NULL COMMENT '描述',
  `del` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否可删除，1：可删除，2：禁止删除',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态，1 未删除，0 禁用，-1 删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级管理员', 'super_system', '1', '超级管理员', '2', '1', now(), now());
INSERT INTO `sys_role` VALUES ('2', '日志管理员', 'auditor', '2', '日志管理员', '2', '1', now(), now());
INSERT INTO `sys_role` VALUES ('3', '用户', 'visitor', '3', '用户', '2', '1',now(), now());


-- ----------------------------
-- Table structure for `sys_role_res`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_res`;
CREATE TABLE `sys_role_res` (
  `id` bigint(20) unsigned NOT NULL,
  `res_id` bigint(20) unsigned NOT NULL,
  `role_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色资源表';

-- ----------------------------
-- Records of sys_role_res
-- ----------------------------
INSERT INTO `sys_role_res` VALUES ('1139444862914576386', '1', '1');


-- ----------------------------
-- Table structure for `sys_groupsys_groupsys_group`
-- ----------------------------
DROP TABLE IF EXISTS `sys_group`;
CREATE TABLE `sys_group` (
  `id` bigint(20) unsigned NOT NULL,
  `name` varchar(64) COMMENT '组名',
  `desc` varchar(128) COMMENT '职责',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色资源表';




-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) unsigned NOT NULL,
  `type` tinyint(3) NOT NULL DEFAULT '1' COMMENT '用户类型：1 管理端用户 2 应用端用户',
  `role`  tinyint(3) NOT NULL DEFAULT '1' COMMENT '角色',
  `username` varchar(100) DEFAULT NULL COMMENT '姓名',
  `account` varchar(100) DEFAULT NULL COMMENT '帐号',
  `nickname` varchar(100) DEFAULT NULL COMMENT '昵称',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机',
  `sex` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '1:男，0:女',
  `avatar` varchar(20) DEFAULT NULL COMMENT '头像',
  `introduction` varchar(500) DEFAULT NULL COMMENT '介绍',
  `password` varchar(150) DEFAULT NULL COMMENT '密码',
  `pwd_salt` varchar(150) DEFAULT NULL COMMENT '密码盐',
  `mod_passwd_date` datetime DEFAULT NULL COMMENT '密码修改日期',
  `group_id` bigint(20) COMMENT '所属组id',
  `multipoint` int(5) DEFAULT NULL COMMENT '多点登录',
  `del` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否可删除，1：可删除，2：禁止删除',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态，1 未删除，0 禁用，-1 删除，2 锁定，3 用户密码被重置 4 用户密码已过期',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `index_account` (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('200058115285323771', '1', '1','admin', 'admin', '管理员', 'powertime@powertime.cn', '', '1', null, '超级管理员', 'da1357d5fc295cd6a53864648086bd90', '6872aeaa5448eef15b605e1302513bc1', now(),1,1,2,1,now(),now());
INSERT INTO `sys_user` VALUES ('200058115285323772', '1', '2','auditor', 'auditor', '日志管理员', 'powertime@powertime.cn', '','1', null, '审计员', 'da1357d5fc295cd6a53864648086bd90', '6872aeaa5448eef15b605e1302513bc1',now(),2,1,2,1, now(), now());
INSERT INTO `sys_user` VALUES ('200058115285323773', '2','3', 'user', 'user', '用户', 'powertime@powertime.cn', '','1', null, '普通用户', 'da1357d5fc295cd6a53864648086bd90', '6872aeaa5448eef15b605e1302513bc1',now(),3,1,2,1, now(), now());

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) unsigned NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `role_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '200058115285323771', '1');
INSERT INTO `sys_user_role` VALUES ('2', '200058115285323772', '2');
INSERT INTO `sys_user_role` VALUES ('3', '200058115285323773', '3');


-- ----------------------------
-- Table structure for `sys_dbtype`
-- ----------------------------

DROP TABLE IF EXISTS sys_dbtype;
CREATE TABLE 
	sys_dbtype
	(
		id INT NOT NULL AUTO_INCREMENT,
		name VARCHAR(32) NOT NULL COMMENT '数据库名称',
		type VARCHAR(32) NOT NULL COMMENT '数据库类型',
		driverClass VARCHAR(64) NOT NULL COMMENT  '驱动',
		urlFormat VARCHAR(256) NOT NULL COMMENT '数据库连接URL格式',
		PRIMARY KEY (id)
	)
	ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
-- ----------------------------
-- Records of sys_dbtype
-- ----------------------------

	INSERT INTO `sys_dbtype` VALUES (1,'mysql','mysql','com.mysql.jdbc.Driver','jdbc:mysql://%s:%d/%s');
	INSERT INTO `sys_dbtype` VALUES (2,'oracle','oracle','oracle.jdbc.OracleDriver','jdbc:oracle:thin:@%s:%d:%s');
--	INSERT INTO `sys_dbtype` VALUES (3,'msserver','msserver','net.sourceforage.jtds.jdbc.Driver','jdbc:sqlserver://%s:%d;DatabaseName=%s');


-- 数据源表 --
-- ----------------------------
-- Table structure for `sys_db`
-- ----------------------------

DROP TABLE IF EXISTS sys_db;
CREATE TABLE 
	sys_db
	(
		id INT NOT NULL AUTO_INCREMENT,
		name VARCHAR(32) COMMENT '数据源名称',
-- 		account VARCHAR(32) NOT NULL COMMENT '数据库标识,唯一',
		type int NOT NULL COMMENT '数据库类型',
		ip VARCHAR(31) COMMENT 'IP地址',
		port INT COMMENT '端口号',
		username VARCHAR(32) COMMENT '数据库用户名',
		password VARCHAR(128) COMMENT '登录密码',
		title VARCHAR(128) COMMENT '数据库中文名称或描述',
		description VARCHAR(128) COMMENT '业务描述',
		status int COMMENT '状态: 1启用，2停用',
		level int COMMENT '级别',
		create_time datetime DEFAULT NULL COMMENT '创建时间',
		update_time datetime DEFAULT NULL COMMENT '修改时间',
		PRIMARY KEY (id)
	)
	ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 表源表 --
-- ----------------------------
-- Table structure for `sys_tables`
-- ----------------------------
DROP TABLE IF EXISTS sys_tables;
CREATE TABLE 
	sys_tables
	(
		id INT NOT NULL AUTO_INCREMENT,
		name VARCHAR(32) COMMENT '表名',
		datasourceid INT COMMENT '数据源id',
		title VARCHAR(128) COMMENT '表的中文名称或描述',
		level int COMMENT '级别',
		PRIMARY KEY (id)
	)
	ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
-- ----------------------------
-- Table structure for `sys_fields`
-- ----------------------------	
-- 字段资源表 --
DROP TABLE IF EXISTS sys_fields;
CREATE TABLE 
	sys_fields
	(
		id INT NOT NULL AUTO_INCREMENT,
		name VARCHAR(32) COMMENT '字段名',
		tableid INT COMMENT '表id',
		type VARCHAR(16) COMMENT '字段类型',
		len INT(5) COMMENT '长度',
		dec_len INT(5) COMMENT '小数位',
		pk INT(5) COMMENT '主键',
		title VARCHAR(128) COMMENT '表的中文名称或描述',
		level int COMMENT '级别',
		PRIMARY KEY (id),
		INDEX (tableid)
	)
	ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
	
-- ----------------------------
-- Table structure for `application`
-- ----------------------------	
-- 应用登记表 --
DROP TABLE IF EXISTS application;
CREATE TABLE 
	application
	(
		id INT NOT NULL AUTO_INCREMENT,
		name VARCHAR(32) COMMENT '系统名称',
		ip VARCHAR(16) COMMENT 'IP地址',
		port INT COMMENT '端口',
		username VARCHAR(20) COMMENT '用户名',
		password VARCHAR(64) COMMENT '密码',
		description VARCHAR(128) COMMENT '备注',
		image varchar(128) COMMENT '图片文件名',
		status int(4) COMMENT '状态',
		PRIMARY KEY (id)
	)
	ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;	

-- ----------------------------
-- Table structure for `model`
-- ----------------------------	
-- 模型表 --
DROP TABLE IF EXISTS model;
CREATE TABLE 
	model
	(
		id bigint(20) unsigned NOT NULL,
		name VARCHAR(32) COMMENT '系统名称',
		type int COMMENT '模型类型',
		application_id bigint(20)  COMMENT '所属应用id',
		status int(4) COMMENT '状态:0 创建、1发布、2拒绝、3停用、4使用',
		create_id bigint(20) unsigned COMMENT '创建人id',
		create_time datetime DEFAULT NULL COMMENT '创建时间',
		update_id bigint(20) unsigned COMMENT '修改人id',
		update_time datetime DEFAULT NULL COMMENT '修改时间',		
		PRIMARY KEY (id)
	)
	ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;	
	
-- ----------------------------
-- Table structure for `model_information`
-- ----------------------------	
-- 模型定义表 --
DROP TABLE IF EXISTS model_information;
CREATE TABLE 
	model_information
	(
		id bigint(20) unsigned NOT NULL,
		model_id bigint(20) unsigned NOT NULL COMMENT '模型id',
		property VARCHAR(32) COMMENT '属性名称',
		type int COMMENT '类型:1 int8,2 int16,3 int32,4 int64,5 float,6 double,7 sting',
		flag int(2) COMMENT '标记:0节点,1边,2有向边,3节点属性,4边属性',
		isEmpty int(1) COMMENT '是否可以空：0可以、1主键、2不能为空',
		x_source bigint(20) COMMENT '节点x坐标或源',
		y_target bigint(20) COMMENT '节点y坐标或目的',
		style VARCHAR(64) COMMENT '节点或边的显示风格',
		owner bigint(20) COMMENT '所属节点或边的ID',
		PRIMARY KEY (id)
	)
	ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for `field_map`


-- ----------------------------	
-- 模型属性映射表 --
-- DROP TABLE IF EXISTS field_map;
-- CREATE TABLE 
-- 	field_map
-- 	(
-- 		id bigint(20) unsigned NOT NULL COMMENT '图数据库属性ID',
-- 		table_name VARCHAR(32) COMMENT '表名',
-- 		field_name VARCHAR(32) COMMENT '字段名',
-- 		PRIMARY KEY (id)
-- 	)
-- 	ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
	
-- ----------------------------
-- Table structure for `data_import`
-- ----------------------------	
-- 数据导入表 --
DROP TABLE IF EXISTS data_import;
CREATE TABLE 
	data_import
	(
		id bigint(20) unsigned NOT NULL,
		name VARCHAR(32) COMMENT '名称',
		datasource_id bigint(20) unsigned NOT NULL COMMENT '数据源ID',
		model_id bigint(20) unsigned NOT NULL COMMENT '模型ID',
		description VARCHAR(128) COMMENT '说明',
		type int COMMENT '类型:1 在线数据源,2 离线文件',
		process int COMMENT '进度0-100%',
		time datetime COMMENT '时间',
		user_id bigint(20) unsigned NOT NULL COMMENT '创建人id',
		PRIMARY KEY (id)
	)
	ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
	
-- ----------------------------
-- Table structure for `graph_meta`
-- ----------------------------	
-- 图元表 --
DROP TABLE IF EXISTS graph_meta;
CREATE TABLE 
	graph_meta
	(
		id bigint(20) unsigned NOT NULL,
		name VARCHAR(32) COMMENT '名称',
		type int(5) COMMENT '图元类型',
		grade varchar(32) COMMENT '电压等级',
		description VARCHAR(128) COMMENT '说明',
		filename varchar(128) COMMENT '图元图片文件名',
		status int COMMENT '状态：1 未删除，0 禁用，-1 删除',
		PRIMARY KEY (id)
	)
	ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for `user_dict_type`
-- ----------------------------
DROP TABLE IF EXISTS `user_dict_type`;
CREATE TABLE `user_dict_type` (
  `id` bigint(20) unsigned NOT NULL,
  `name` varchar(20) NOT NULL COMMENT '字典类型名称',
  `varname` varchar(32) NOT NULL COMMENT '变量名',
  `description` VARCHAR(128) COMMENT '说明',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态，1 未删除，0 禁用，-1 删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典类型表';


-- ----------------------------
-- Records of user_dict_type
-- ----------------------------
INSERT INTO `user_dict_type` VALUES (1, '文件类型', 'FILE_TYPE',null,1,now(), now());
INSERT INTO `user_dict_type` VALUES (2, '关系数据库类型', 'DB_TYPE',null,1,now(), now());
INSERT INTO `user_dict_type` VALUES (3, '数据导入类型', 'DATA_IMPORT_TYPE',null,1,now(), now());

-- ----------------------------
-- Table structure for `user_dict`
-- ----------------------------
DROP TABLE IF EXISTS `user_dict`;
CREATE TABLE `user_dict` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type_id` bigint(20) unsigned NOT NULL COMMENT '类型ID',
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `value` varchar(64) DEFAULT NULL COMMENT '值',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态，1 未删除，0 禁用，-1 删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `value_index` (`value`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户字典表';

-- ----------------------------
-- Records of user_dict
-- ----------------------------

-- 文件类型
INSERT INTO `user_dict` VALUES (null, 1, 'csv','1',1,now(), now());
INSERT INTO `user_dict` VALUES (null, 1, 'xlsx','2',1,now(), now());

-- 关系数据库类型
INSERT INTO `user_dict` VALUES (null, 2, 'oracle','1',1,now(), now());
INSERT INTO `user_dict` VALUES (null, 2, 'db2','2',1,now(), now());
INSERT INTO `user_dict` VALUES (null, 2, 'mysql','2',1,now(), now());

-- 数据导入类型
INSERT INTO `user_dict` VALUES (null, 3, '在线数据源','1',1,now(), now());
INSERT INTO `user_dict` VALUES (null, 3, '离线文件','2',1,now(), now());
