--登陆日志表
CREATE TABLE `iplog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ip` varchar(50) NOT NULL,--登陆ip
  `loginstate` tinyint(4) NOT NULL,--登录状态
  `username` varchar(50) DEFAULT NULL,--用户名
  `logintime` datetime NOT NULL,--登录时间
  `usertype` tinyint(4) DEFAULT NULL,--用户类型：0前台用户    1后台用户
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=614 DEFAULT CHARSET=utf8;

