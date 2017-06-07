--用户登录表
CREATE TABLE `logininfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `state` tinyint(4) DEFAULT NULL,--登陆状态
  `username` varchar(20) DEFAULT NULL,--用户名
  `password` varchar(100) DEFAULT NULL,--密码
  `usertype` tinyint(20) DEFAULT NULL,--用户类型
  `mobile` varchar(11) DEFAULT NULL,--手机号
  `email` varchar(50) DEFAULT NULL,--邮箱
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

