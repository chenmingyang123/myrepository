CREATE TABLE `banner` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '广告id',
  `image_path` varchar(255) DEFAULT NULL COMMENT '图片路径',
  `type` varchar(10) DEFAULT '1' COMMENT '跳转类型： 1跳转到网页  2跳转到商品详情 3跳转到列表',
  `ad_url` varchar(255) DEFAULT NULL COMMENT '跳转路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

