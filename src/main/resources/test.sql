USE `my_test`;

CREATE TABLE `my_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'account_id自增id',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(20) NOT NULL COMMENT '密码',
  `register_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='测试账号表';

CREATE TABLE `my_account_addition` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'account自定键值',
  `account_id` int(11) NOT NULL COMMENT 'account表的id',
  `addition_key` varchar(50) NOT NULL COMMENT '增加的键名称',
  `addition_value` varchar(50) NOT NULL COMMENT '增加的值',
  `description` varchar(100) DEFAULT NULL COMMENT 'account自定键值描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- 账号信息
INSERT INTO `my_account` VALUES ('1', 'root', 'root', CURRENT_TIMESTAMP, '管理员账号');
INSERT INTO `my_account` VALUES ('2', 'test', 'test', CURRENT_TIMESTAMP, '测试账号');
INSERT INTO `my_account` VALUES ('3', 'person', 'yes', CURRENT_TIMESTAMP, '测试账号');

-- 账号自定义字段
INSERT INTO `my_account_addition` VALUES ('1', '1', 'nickname', '管理员', '增加昵称字段');
INSERT INTO `my_account_addition` VALUES ('2',	'1', 'sex', '男', '增加性别字段');
INSERT INTO `my_account_addition` VALUES ('3', '2', 'nickname', '测试账号', '增加昵称字段');
INSERT INTO `my_account_addition` VALUES ('4',	'2', 'sex', '女', '增加性别字段');
INSERT INTO `my_account_addition` VALUES ('5', '3', 'nickname', '测试账号2', '增加昵称字段');
INSERT INTO `my_account_addition` VALUES ('6',	'3', 'test', '测试', '增加测试字段');