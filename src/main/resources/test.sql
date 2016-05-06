USE `my_test`;

CREATE TABLE `my_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'account_id自增id',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(20) NOT NULL COMMENT '密码',
  `register_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='测试账号表';

-- 账号信息
INSERT INTO `my_account` VALUES ('1', 'root', 'root', CURRENT_TIMESTAMP, '管理员账号');
INSERT INTO `my_account` VALUES ('2', 'test', 'test', CURRENT_TIMESTAMP, '测试账号');
INSERT INTO `my_account` VALUES ('3', 'person', 'yes', CURRENT_TIMESTAMP, '测试账号');