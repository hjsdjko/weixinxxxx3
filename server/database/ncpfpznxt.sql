/*
Navicat MySQL Data Transfer

Source Server         : localhost_3307
Source Server Version : 50736
Source Host           : localhost:3307
Source Database       : ncpfpznxt

Target Server Type    : MYSQL
Target Server Version : 50736
File Encoding         : 65001

Date: 2024-03-06 15:42:39
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `admin_info`
-- ----------------------------
DROP TABLE IF EXISTS `admin_info`;
CREATE TABLE `admin_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(175) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '姓名',
  `mima` varchar(175) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `nickName` varchar(175) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '昵称',
  `sex` varchar(175) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '性别',
  `age` int(10) DEFAULT NULL COMMENT '年龄',
  `birthday` varchar(175) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '生日',
  `phone` varchar(175) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '手机号',
  `address` varchar(175) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '地址',
  `code` varchar(175) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '编号',
  `email` varchar(175) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮箱',
  `cardId` varchar(175) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '身份证',
  `account` double(10,2) DEFAULT NULL COMMENT '余额',
  `level` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '管理员' COMMENT '权限等级',
  `fileIds` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '商品图片id，用英文逗号隔开',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_name` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='管理员信息表';

-- ----------------------------
-- Records of admin_info
-- ----------------------------
INSERT INTO `admin_info` VALUES ('1', 'hsg', '94b40c6db280230b4211b06fa04c7be1', '何升高', '男', '222', '2020-11-02 00:04:25', '18843232356', '上海市ddff33', '111fsfsggsss11', 'aa@163.com', '342425199001116372', '2100.00', '管理员', '[56]');

-- ----------------------------
-- Table structure for `cart_info`
-- ----------------------------
DROP TABLE IF EXISTS `cart_info`;
CREATE TABLE `cart_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `count` int(11) NOT NULL DEFAULT '0' COMMENT '数量',
  `shangpinxinxiId` bigint(20) NOT NULL DEFAULT '0' COMMENT '所属商品',
  `userId` bigint(20) NOT NULL DEFAULT '0' COMMENT '所属用户',
  `createTime` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='购物车信息表';

-- ----------------------------
-- Records of cart_info
-- ----------------------------
INSERT INTO `cart_info` VALUES ('4', '2', '2', '9', '2022-10-29 18:35:41');

-- ----------------------------
-- Table structure for `collect_info`
-- ----------------------------
DROP TABLE IF EXISTS `collect_info`;
CREATE TABLE `collect_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `shoucangmingcheng` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `shangpinxinxiid` bigint(20) DEFAULT NULL,
  `level` varchar(10) CHARACTER SET utf8mb4 DEFAULT NULL,
  `biao` varchar(20) DEFAULT NULL,
  `addtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of collect_info
-- ----------------------------

-- ----------------------------
-- Table structure for `comment_info`
-- ----------------------------
DROP TABLE IF EXISTS `comment_info`;
CREATE TABLE `comment_info` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `content` varchar(175) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '评价内容',
  `shujuid` bigint(10) NOT NULL DEFAULT '0' COMMENT '所属商品',
  `userId` bigint(10) NOT NULL DEFAULT '0' COMMENT '评价人id',
  `level` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户等级',
  `createTime` varchar(175) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '创建时间',
  `biao` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='商品评价表';

-- ----------------------------
-- Records of comment_info
-- ----------------------------

-- ----------------------------
-- Table structure for `gonggaoxinxi_info`
-- ----------------------------
DROP TABLE IF EXISTS `gonggaoxinxi_info`;
CREATE TABLE `gonggaoxinxi_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gonggaobiaoti` varchar(50) DEFAULT NULL,
  `gonggaoneirong` varchar(500) DEFAULT NULL,
  `status` varchar(10) DEFAULT '是',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=gb2312 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of gonggaoxinxi_info
-- ----------------------------
INSERT INTO `gonggaoxinxi_info` VALUES ('2', '衢州大雾殃及温州春运', '测试内容', '是', '2022-10-25 18:53:58');
INSERT INTO `gonggaoxinxi_info` VALUES ('3', '库尔德工人党武装绑架一名土耳其议员', '', '是', '2022-10-25 18:53:58');
INSERT INTO `gonggaoxinxi_info` VALUES ('4', '习近平生态文明思想金句', '', '是', '2022-10-25 18:53:58');
INSERT INTO `gonggaoxinxi_info` VALUES ('5', '衢州大雾殃及温州春运', '', '是', '2022-10-25 18:53:58');
INSERT INTO `gonggaoxinxi_info` VALUES ('6', '苍南县委常委、常务副县长黄锦耀率队开展“零点”行动', '', '是', '2022-10-25 18:53:58');
INSERT INTO `gonggaoxinxi_info` VALUES ('7', '库尔德工人党武装绑架一名土耳其议员', '', '是', '2022-10-25 18:53:58');
INSERT INTO `gonggaoxinxi_info` VALUES ('8', '库尔德工人党武装绑架一名土耳其议员', '', '是', '2022-10-25 18:53:58');
INSERT INTO `gonggaoxinxi_info` VALUES ('9', '苍南县委常委、常务副县长黄锦耀率队开展“零点”行动', '', '是', '2022-10-25 18:53:58');

-- ----------------------------
-- Table structure for `goumai_info`
-- ----------------------------
DROP TABLE IF EXISTS `goumai_info`;
CREATE TABLE `goumai_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shangpinbianhao` varchar(50) DEFAULT NULL,
  `shangpinmingcheng` varchar(50) DEFAULT NULL,
  `shangpinleibie` varchar(50) DEFAULT NULL,
  `jiage` varchar(50) DEFAULT NULL,
  `xiaoliang` varchar(50) DEFAULT NULL,
  `kucun` varchar(50) DEFAULT NULL,
  `goumaishuliang` varchar(50) DEFAULT NULL,
  `goumaijine` varchar(50) DEFAULT NULL,
  `goumairen` varchar(50) DEFAULT NULL,
  `status` varchar(10) DEFAULT '待发货',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dianhua` varchar(50) DEFAULT NULL,
  `dizhi` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=gb2312 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of goumai_info
-- ----------------------------
INSERT INTO `goumai_info` VALUES ('31', '1231421585767', 'A电脑', 'E类', '48', '122', '53', '2', '96', '13', '完成', '2023-12-04 03:13:32', '18767386260', 'XXX');
INSERT INTO `goumai_info` VALUES ('32', '1231460575336', 'H毛笔', '测试', '85', '411', '69', '1', '85', '13', '完成', '2023-12-04 03:13:32', '18767386260', 'XXX');

-- ----------------------------
-- Table structure for `liuyanban_info`
-- ----------------------------
DROP TABLE IF EXISTS `liuyanban_info`;
CREATE TABLE `liuyanban_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `yonghuming` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL,
  `cheng` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL,
  `touxiang` varchar(255) DEFAULT NULL,
  `neirong` varchar(500) DEFAULT NULL,
  `huifu` varchar(500) DEFAULT NULL,
  `status` varchar(10) DEFAULT '是',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of liuyanban_info
-- ----------------------------

-- ----------------------------
-- Table structure for `nongchanpinpinglun_info`
-- ----------------------------
DROP TABLE IF EXISTS `nongchanpinpinglun_info`;
CREATE TABLE `nongchanpinpinglun_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shangpinbianhao` varchar(50) DEFAULT NULL,
  `shangpinmingcheng` varchar(50) DEFAULT NULL,
  `pingfen` varchar(50) DEFAULT NULL,
  `pingjia` varchar(50) DEFAULT NULL,
  `zhanghao` varchar(50) DEFAULT NULL,
  `xingming` varchar(50) DEFAULT NULL,
  `status` varchar(10) DEFAULT '是',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of nongchanpinpinglun_info
-- ----------------------------
INSERT INTO `nongchanpinpinglun_info` VALUES ('2', '1231480127145', 'C茶杯', '3', '', '021', '科比', '是', '2023-12-04 03:00:37');
INSERT INTO `nongchanpinpinglun_info` VALUES ('3', '1231410655915', 'D手表', '4', '', '030', '鹿晗', '是', '2023-12-04 03:00:37');
INSERT INTO `nongchanpinpinglun_info` VALUES ('4', '1231479298393', 'G鼠标', '2', '', '006', '胡歌', '是', '2023-12-04 03:00:37');
INSERT INTO `nongchanpinpinglun_info` VALUES ('5', '1231418288972', 'F牙膏', '1', '', '033', '王柏弘', '是', '2023-12-04 03:00:37');
INSERT INTO `nongchanpinpinglun_info` VALUES ('6', '1231420218724', 'E牙刷', '1', '', '024', '乔维', '是', '2023-12-04 03:00:37');
INSERT INTO `nongchanpinpinglun_info` VALUES ('7', '1231421585767', 'A电脑', '1', '', '015', '霍建华', '是', '2023-12-04 03:00:37');
INSERT INTO `nongchanpinpinglun_info` VALUES ('8', '1231410655915', 'D手表', '1', '', '012', '吴官本', '是', '2023-12-04 03:00:37');
INSERT INTO `nongchanpinpinglun_info` VALUES ('9', '1231420218724', 'E牙刷', '5', '', '009', '陈贵柏', '是', '2023-12-04 03:00:37');
INSERT INTO `nongchanpinpinglun_info` VALUES ('10', '1231460575336', 'H毛笔', '3', 'xxxxx', '009', '张燕', '是', '2023-12-04 03:05:43');
INSERT INTO `nongchanpinpinglun_info` VALUES ('11', '1231460575336', 'H毛笔', '3', 'XXXXX', '029', '陈明希', '是', '2023-12-04 03:15:21');

-- ----------------------------
-- Table structure for `nx_system_file_info`
-- ----------------------------
DROP TABLE IF EXISTS `nx_system_file_info`;
CREATE TABLE `nx_system_file_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `originName` varchar(175) CHARACTER SET gb2312 DEFAULT NULL COMMENT '原始文件名',
  `fileName` varchar(175) CHARACTER SET gb2312 DEFAULT NULL COMMENT '存储文件名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=230 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='文件信息表';

-- ----------------------------
-- Records of nx_system_file_info
-- ----------------------------
INSERT INTO `nx_system_file_info` VALUES ('188', 'yhtx1.jpg', 'yhtx1.jpg');
INSERT INTO `nx_system_file_info` VALUES ('189', 'yhtx2.jpg', 'yhtx2.jpg');
INSERT INTO `nx_system_file_info` VALUES ('190', 'yhtx3.jpg', 'yhtx3.jpg');
INSERT INTO `nx_system_file_info` VALUES ('191', 'yhtx4.jpg', 'yhtx4.jpg');
INSERT INTO `nx_system_file_info` VALUES ('192', 'yhtx5.jpg', 'yhtx5.jpg');
INSERT INTO `nx_system_file_info` VALUES ('193', 'yhtx6.jpg', 'yhtx6.jpg');
INSERT INTO `nx_system_file_info` VALUES ('194', 'yhtx7.jpg', 'yhtx7.jpg');
INSERT INTO `nx_system_file_info` VALUES ('195', 'yhtx8.jpg', 'yhtx8.jpg');
INSERT INTO `nx_system_file_info` VALUES ('196', 'shangpinxinxi1.jpg', 'shangpinxinxi1.jpg');
INSERT INTO `nx_system_file_info` VALUES ('197', 'shangpinxinxi2.jpg', 'shangpinxinxi2.jpg');
INSERT INTO `nx_system_file_info` VALUES ('198', 'shangpinxinxi3.jpg', 'shangpinxinxi3.jpg');
INSERT INTO `nx_system_file_info` VALUES ('199', 'shangpinxinxi4.jpg', 'shangpinxinxi4.jpg');
INSERT INTO `nx_system_file_info` VALUES ('200', 'shangpinxinxi5.jpg', 'shangpinxinxi5.jpg');
INSERT INTO `nx_system_file_info` VALUES ('201', 'shangpinxinxi6.jpg', 'shangpinxinxi6.jpg');
INSERT INTO `nx_system_file_info` VALUES ('202', 'shangpinxinxi7.jpg', 'shangpinxinxi7.jpg');
INSERT INTO `nx_system_file_info` VALUES ('203', 'shangpinxinxi8.jpg', 'shangpinxinxi8.jpg');
INSERT INTO `nx_system_file_info` VALUES ('204', 'zhuceyonghu1.jpg', 'zhuceyonghu1.jpg');
INSERT INTO `nx_system_file_info` VALUES ('205', 'zhuceyonghu2.jpg', 'zhuceyonghu2.jpg');
INSERT INTO `nx_system_file_info` VALUES ('206', 'zhuceyonghu3.jpg', 'zhuceyonghu3.jpg');
INSERT INTO `nx_system_file_info` VALUES ('207', 'zhuceyonghu4.jpg', 'zhuceyonghu4.jpg');
INSERT INTO `nx_system_file_info` VALUES ('208', 'zhuceyonghu5.jpg', 'zhuceyonghu5.jpg');
INSERT INTO `nx_system_file_info` VALUES ('209', 'zhuceyonghu6.jpg', 'zhuceyonghu6.jpg');
INSERT INTO `nx_system_file_info` VALUES ('210', 'zhuceyonghu7.jpg', 'zhuceyonghu7.jpg');
INSERT INTO `nx_system_file_info` VALUES ('211', 'zhuceyonghu8.jpg', 'zhuceyonghu8.jpg');
INSERT INTO `nx_system_file_info` VALUES ('212', 'nopic1.jpg', 'nopic11670072986269.jpg');
INSERT INTO `nx_system_file_info` VALUES ('213', 'QQ图片20200628133103.jpg', 'QQ图片202006281331031670078732116.jpg');
INSERT INTO `nx_system_file_info` VALUES ('214', 'QQ图片20200616222243.jpg', 'QQ图片202006162222431670078738300.jpg');
INSERT INTO `nx_system_file_info` VALUES ('215', 'QQ图片20200606235818.jpg', 'QQ图片202006062358181670078743068.jpg');
INSERT INTO `nx_system_file_info` VALUES ('216', 'nopic1.jpg', 'nopic11701628248292.jpg');
INSERT INTO `nx_system_file_info` VALUES ('217', 'pinkunrenzhengshenqing1.jpg', 'pinkunrenzhengshenqing1.jpg');
INSERT INTO `nx_system_file_info` VALUES ('218', 'pinkunrenzhengshenqing2.jpg', 'pinkunrenzhengshenqing2.jpg');
INSERT INTO `nx_system_file_info` VALUES ('219', 'pinkunrenzhengshenqing3.jpg', 'pinkunrenzhengshenqing3.jpg');
INSERT INTO `nx_system_file_info` VALUES ('220', 'pinkunrenzhengshenqing4.jpg', 'pinkunrenzhengshenqing4.jpg');
INSERT INTO `nx_system_file_info` VALUES ('221', 'pinkunrenzhengshenqing5.jpg', 'pinkunrenzhengshenqing5.jpg');
INSERT INTO `nx_system_file_info` VALUES ('222', 'pinkunrenzhengshenqing6.jpg', 'pinkunrenzhengshenqing6.jpg');
INSERT INTO `nx_system_file_info` VALUES ('223', 'pinkunrenzhengshenqing7.jpg', 'pinkunrenzhengshenqing7.jpg');
INSERT INTO `nx_system_file_info` VALUES ('224', 'pinkunrenzhengshenqing8.jpg', 'pinkunrenzhengshenqing8.jpg');
INSERT INTO `nx_system_file_info` VALUES ('225', 'RCe3eZEdevkT2536e70544dd3cf02b86983a9e9f9a46.jpg', 'RCe3eZEdevkT2536e70544dd3cf02b86983a9e9f9a461701630106416.jpg');
INSERT INTO `nx_system_file_info` VALUES ('226', 'nopic1.jpg', 'nopic11701630628191.jpg');
INSERT INTO `nx_system_file_info` VALUES ('227', 'nopic1.jpg', 'nopic11701630680207.jpg');
INSERT INTO `nx_system_file_info` VALUES ('228', '71gmmJzd6Iak2536e70544dd3cf02b86983a9e9f9a46.jpg', '71gmmJzd6Iak2536e70544dd3cf02b86983a9e9f9a461701630822222.jpg');
INSERT INTO `nx_system_file_info` VALUES ('229', 'mwb5h2XLIcnA2536e70544dd3cf02b86983a9e9f9a46.jpg', 'mwb5h2XLIcnA2536e70544dd3cf02b86983a9e9f9a461701630890744.jpg');

-- ----------------------------
-- Table structure for `paper_comment_info`
-- ----------------------------
DROP TABLE IF EXISTS `paper_comment_info`;
CREATE TABLE `paper_comment_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `content` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '评价内容',
  `paperId` bigint(20) NOT NULL DEFAULT '0' COMMENT '所属商品',
  `userId` bigint(20) NOT NULL DEFAULT '0' COMMENT '评价人id',
  `level` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户等级',
  `createTime` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='商品评价表';

-- ----------------------------
-- Records of paper_comment_info
-- ----------------------------
INSERT INTO `paper_comment_info` VALUES ('24', '123', '31', '3', '注册用户', '2023-11-22 01:47:19');
INSERT INTO `paper_comment_info` VALUES ('25', '1111', '36', '3', '注册用户', '2023-11-22 01:47:19');
INSERT INTO `paper_comment_info` VALUES ('26', '1111', '36', '3', '注册用户', '2023-11-22 01:47:19');
INSERT INTO `paper_comment_info` VALUES ('27', '发发发', '36', '2', '注册用户', '2023-11-22 01:47:19');
INSERT INTO `paper_comment_info` VALUES ('28', '发傻瓜', '37', '2', '注册用户', '2023-11-22 01:47:19');
INSERT INTO `paper_comment_info` VALUES ('29', 'xxxxx', '38', '2', '注册用户', '2023-11-22 01:47:19');
INSERT INTO `paper_comment_info` VALUES ('34', '发生发撒法', '38', '2', '注册用户', '2023-11-22 01:47:19');
INSERT INTO `paper_comment_info` VALUES ('35', '回复别人测试', '38', '3', '注册用户', '2023-11-22 02:04:20');
INSERT INTO `paper_comment_info` VALUES ('36', '自己帖子下面留言', '42', '3', '注册用户', '2023-11-22 02:04:55');
INSERT INTO `paper_comment_info` VALUES ('37', 'xxxxx', '38', '2', '注册用户', '2023-12-04 03:06:41');
INSERT INTO `paper_comment_info` VALUES ('38', '回复自己', '43', '4', '注册用户', '2023-12-04 03:14:18');

-- ----------------------------
-- Table structure for `pinkunrenzhengshenqing_info`
-- ----------------------------
DROP TABLE IF EXISTS `pinkunrenzhengshenqing_info`;
CREATE TABLE `pinkunrenzhengshenqing_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shenqingbiaoti` varchar(50) DEFAULT NULL,
  `pinkunzhengshu` varchar(50) DEFAULT NULL,
  `shenqingshuoming` varchar(500) DEFAULT NULL,
  `zhanghao` varchar(50) DEFAULT NULL,
  `xingming` varchar(50) DEFAULT NULL,
  `status` varchar(10) DEFAULT '否',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of pinkunrenzhengshenqing_info
-- ----------------------------
INSERT INTO `pinkunrenzhengshenqing_info` VALUES ('9', '我市启动对日本入境集装箱检测', '[227]', '暂无', '016', '梅邹雁', '是', '2023-12-04 03:00:41');
INSERT INTO `pinkunrenzhengshenqing_info` VALUES ('11', 'XXXXX', '[229]', 'XXXXXXXXXXXXXXX', '029', '陈明希', '是', '2023-12-04 03:14:52');

-- ----------------------------
-- Table structure for `richtext_info`
-- ----------------------------
DROP TABLE IF EXISTS `richtext_info`;
CREATE TABLE `richtext_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(175) CHARACTER SET gb2312 DEFAULT NULL COMMENT '名称',
  `content` longtext CHARACTER SET utf8 COMMENT '公告内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='用户上传信息表';

-- ----------------------------
-- Records of richtext_info
-- ----------------------------

-- ----------------------------
-- Table structure for `shangpinleibie_info`
-- ----------------------------
DROP TABLE IF EXISTS `shangpinleibie_info`;
CREATE TABLE `shangpinleibie_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `leibie` varchar(50) DEFAULT NULL,
  `status` varchar(10) DEFAULT '是',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of shangpinleibie_info
-- ----------------------------
INSERT INTO `shangpinleibie_info` VALUES ('2', 'A类', '是', '2022-12-03 20:52:03');
INSERT INTO `shangpinleibie_info` VALUES ('3', 'X类', '是', '2022-12-03 20:52:03');
INSERT INTO `shangpinleibie_info` VALUES ('4', 'H类', '是', '2022-12-03 20:52:03');
INSERT INTO `shangpinleibie_info` VALUES ('5', 'D类', '是', '2022-12-03 20:52:03');
INSERT INTO `shangpinleibie_info` VALUES ('6', 'J类', '是', '2022-12-03 20:52:03');
INSERT INTO `shangpinleibie_info` VALUES ('7', 'E类', '是', '2022-12-03 20:52:03');
INSERT INTO `shangpinleibie_info` VALUES ('8', 'Z类', '是', '2022-12-03 20:52:03');
INSERT INTO `shangpinleibie_info` VALUES ('9', 'S类', '是', '2022-12-03 20:52:03');
INSERT INTO `shangpinleibie_info` VALUES ('12', '测试', '是', '2023-12-04 03:12:02');

-- ----------------------------
-- Table structure for `shangpinxinxi_info`
-- ----------------------------
DROP TABLE IF EXISTS `shangpinxinxi_info`;
CREATE TABLE `shangpinxinxi_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shangpinbianhao` varchar(50) DEFAULT NULL,
  `shangpinmingcheng` varchar(50) DEFAULT NULL,
  `shangpinleibie` varchar(50) DEFAULT NULL,
  `jiage` varchar(50) DEFAULT NULL,
  `kucun` varchar(50) DEFAULT NULL,
  `xiaoliang` varchar(50) DEFAULT NULL,
  `tupian` varchar(50) DEFAULT NULL,
  `beizhu` varchar(500) DEFAULT NULL,
  `status` varchar(10) DEFAULT '是',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of shangpinxinxi_info
-- ----------------------------
INSERT INTO `shangpinxinxi_info` VALUES ('2', '1231460575336', 'H毛笔', '测试', '85', '69', '42', '[212]', '暂无', '是', '2022-12-03 20:52:03');
INSERT INTO `shangpinxinxi_info` VALUES ('3', '1231410655915', 'D手表', 'S类', '19', '66', '53', '[212]', '暂无', '是', '2022-12-03 20:52:03');
INSERT INTO `shangpinxinxi_info` VALUES ('4', '1231482404298', 'B手机', 'X类', '89', '72', '39', '[212]', 'ok', '是', '2022-12-03 20:52:03');
INSERT INTO `shangpinxinxi_info` VALUES ('5', '1231421585767', 'A电脑', 'E类', '48', '53', '14', '[212]', 'ok', '是', '2022-12-03 20:52:03');
INSERT INTO `shangpinxinxi_info` VALUES ('6', '1231418288972', 'F牙膏', 'S类', '19', '11', '67', '[212]', '暂无', '是', '2022-12-03 20:52:03');
INSERT INTO `shangpinxinxi_info` VALUES ('7', '1231420218724', 'E牙刷', 'Z类', '74', '23', '43', '[212]', '无', '是', '2022-12-03 20:52:03');
INSERT INTO `shangpinxinxi_info` VALUES ('8', '1231479298393', 'G鼠标', 'D类', '19', '25', '10', '[212]', '无', '是', '2022-12-03 20:52:03');
INSERT INTO `shangpinxinxi_info` VALUES ('9', '1231480127145', 'C茶杯', 'E类', '68', '24', '67', '[212]', 'ok', '是', '2022-12-03 20:52:03');

-- ----------------------------
-- Table structure for `user_text_info`
-- ----------------------------
DROP TABLE IF EXISTS `user_text_info`;
CREATE TABLE `user_text_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '公告名称',
  `content` longtext COLLATE utf8mb4_unicode_ci COMMENT '公告内容',
  `time` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '公告时间',
  `fileIds` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '商品图片id，用英文逗号隔开',
  `description` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `level` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `click` int(11) DEFAULT NULL,
  `status` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '待审核',
  `reason` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `fenxiang` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '否',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='公告信息表';

-- ----------------------------
-- Records of user_text_info
-- ----------------------------
INSERT INTO `user_text_info` VALUES ('43', '测试', '<p>测试测试测试</p>', null, '[228]', '测试', '4', '注册用户', '1', '审核通过', 'xxx', '是');

-- ----------------------------
-- Table structure for `xiaoyuantongzhi_info`
-- ----------------------------
DROP TABLE IF EXISTS `xiaoyuantongzhi_info`;
CREATE TABLE `xiaoyuantongzhi_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tongzhibianhao` varchar(50) DEFAULT NULL,
  `tongzhibiaoti` varchar(50) DEFAULT NULL,
  `tongzhileixing` varchar(50) DEFAULT NULL,
  `tongzhineirong` varchar(500) DEFAULT NULL,
  `status` varchar(10) DEFAULT '是',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=gb2312 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of xiaoyuantongzhi_info
-- ----------------------------
INSERT INTO `xiaoyuantongzhi_info` VALUES ('2', '10251803896021', '钱库片区152名党政干部集中应考', '测试类型', '', '是', '2022-10-25 18:53:58');
INSERT INTO `xiaoyuantongzhi_info` VALUES ('3', '10251815192916', '龙港摘除市级治安信访重点地区帽子 在建设“平安浙江”大会作经验介绍', '测试类型2', '', '是', '2022-10-25 18:53:58');
INSERT INTO `xiaoyuantongzhi_info` VALUES ('4', '10251865173585', '民革温州市委会枫岭建基地', '', '', '是', '2022-10-25 18:53:58');
INSERT INTO `xiaoyuantongzhi_info` VALUES ('5', '10251842976690', '早晨吃姜好处多 养生防病还治病', '', '', '是', '2022-10-25 18:53:58');
INSERT INTO `xiaoyuantongzhi_info` VALUES ('6', '10251844253632', '建筑废渣倒入农田 谁能尽快来管管', '', '', '是', '2022-10-25 18:53:58');
INSERT INTO `xiaoyuantongzhi_info` VALUES ('7', '10251804163164', '县政协第十三组（少数民族、宗教）讨论现场（图）', '', '', '是', '2022-10-25 18:53:58');
INSERT INTO `xiaoyuantongzhi_info` VALUES ('8', '10251863806542', '民革温州市委会枫岭建基地', '', '', '是', '2022-10-25 18:53:58');
INSERT INTO `xiaoyuantongzhi_info` VALUES ('9', '10251824815973', '钱库片区152名党政干部集中应考', '', '', '是', '2022-10-25 18:53:58');
INSERT INTO `xiaoyuantongzhi_info` VALUES ('10', '67775367232', 'xxxx', 'xxxx', 'xx现在这些数据均为测试数据到时候自行修改即可！！', '是', '2023-12-04 03:12:31');

-- ----------------------------
-- Table structure for `xinwentongzhi_info`
-- ----------------------------
DROP TABLE IF EXISTS `xinwentongzhi_info`;
CREATE TABLE `xinwentongzhi_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `biaoti` varchar(50) DEFAULT NULL,
  `leibie` varchar(50) DEFAULT NULL,
  `neirong` longtext CHARACTER SET utf8,
  `shouyetupian` varchar(50) DEFAULT NULL,
  `zhaiyao` varchar(500) DEFAULT NULL,
  `dianjilv` varchar(50) DEFAULT NULL,
  `faburen` varchar(50) DEFAULT NULL,
  `status` varchar(10) DEFAULT '是',
  `dianzan_d` int(11) DEFAULT '0',
  `dianzan_c` int(11) DEFAULT '0',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of xinwentongzhi_info
-- ----------------------------

-- ----------------------------
-- Table structure for `xitongjianjie_info`
-- ----------------------------
DROP TABLE IF EXISTS `xitongjianjie_info`;
CREATE TABLE `xitongjianjie_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `leibie` varchar(50) DEFAULT NULL,
  `neirong` longtext CHARACTER SET utf8,
  `status` varchar(10) DEFAULT '是',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of xitongjianjie_info
-- ----------------------------
INSERT INTO `xitongjianjie_info` VALUES ('2', '系统简介', '<p>像一个金色大吊灯，又像一个多层的旋转木马……在中国科学技术大学物理学院的实验室里，特任教授曹刚正在为提升量子计算机的算力忙碌。</p><p>他所在的科研团队刚刚在半导体量子芯片研究上获得重要进展。</p><p>“全会要求推进科技自立自强，作为科技工作者倍感使命光荣、责任重大。”曹刚说，量子科技是关键核心技术领域。目前团队正在积极筹划新的科研计划，力fffff争在新阶段实现从追赶到并跑、赶超的跨越。</p><p>冰天雪地的东北，沈阳鼓风机集团透平公司的智能化车间里，一台台数字化工位机有条不紊地工作，各种生产信息在显示屏上一目了然。</p><p>“全会提出坚持开拓创新，对我们这样的老字号国企来说，就是要赶上时代发展的浪潮，抓住新一代信息技术和产业变革的机遇，坚持在改革中实现转型发展。”沈鼓集团董事长戴继双说。</p><p>采访越深入，记者越真切地感受到，全会精神正激发大家拿出更大魄力、更加主动改革创新，推动各行各业加速转入高质量发展轨道。</p><p>揉面、拌馅、印花、烤制……在新疆伊犁的霍尔果斯馕产业园里，刚出炉的馕散发着诱人的香味。“最近又有来自俄罗斯和白俄罗斯的线上订单，得抓紧赶制240万个馕。”霍尔果斯金亿国际贸易（集团）有限公司董事长于成忠仔细查看生产进度。</p><p>霍尔果斯创新发展模式，通过引入金亿这样的外贸企业参与产品研发、改良种类，加快了馕产业走出国门的步伐。</p><p>“‘敢为天下先，走出了前人没有走出的路’。全会总结历史经验里的这句话，将激励我们全方位拓展产业链，用小馕饼撬动大产业。”于成忠信心十足。</p>', '是', '2021-12-24 15:09:13');
INSERT INTO `xitongjianjie_info` VALUES ('3', '关于我们', '<p>gegreeee</p>', '是', '2021-12-24 15:09:13');
INSERT INTO `xitongjianjie_info` VALUES ('12', '系统公告', '系统公告', '是', '2021-12-29 11:07:38');
INSERT INTO `xitongjianjie_info` VALUES ('13', '联系方式', '联系方式', '是', '2021-12-29 11:07:45');

-- ----------------------------
-- Table structure for `yonghu`
-- ----------------------------
DROP TABLE IF EXISTS `yonghu`;
CREATE TABLE `yonghu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `avater` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `level` varchar(255) DEFAULT NULL,
  `mima` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of yonghu
-- ----------------------------
INSERT INTO `yonghu` VALUES ('13', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTK95W21D4OjF1reltAL8onsTDMFShGzPC5ne2mLEQwVzwGL9JDdpwaagvvsCXO23FuUSVdXJT6YQg/132', '?', 'e10adc3949ba59abbe56e057f20f883e', '4', '注册用户', null);

-- ----------------------------
-- Table structure for `yonghuxinxi_info`
-- ----------------------------
DROP TABLE IF EXISTS `yonghuxinxi_info`;
CREATE TABLE `yonghuxinxi_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `yonghuming` varchar(50) DEFAULT NULL,
  `mima` varchar(50) DEFAULT NULL,
  `xingming` varchar(50) DEFAULT NULL,
  `xingbie` varchar(50) DEFAULT NULL,
  `shouji` varchar(50) DEFAULT NULL,
  `shenfenzheng` varchar(50) DEFAULT NULL,
  `youxiang` varchar(50) DEFAULT NULL,
  `zhaopian` varchar(50) DEFAULT NULL,
  `beizhu` varchar(500) DEFAULT NULL,
  `level` varchar(10) DEFAULT '注册用户',
  `status` varchar(10) DEFAULT '是',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of yonghuxinxi_info
-- ----------------------------
INSERT INTO `yonghuxinxi_info` VALUES ('2', '002', 'dc5c7986daef50c1e02ab09b442ee34f', '吴亦凡', '男', '13186835555', '330327199010142546', '2352352@qq.com', '[188]', '无', '注册用户', '是', '2021-12-13 13:16:12');
INSERT INTO `yonghuxinxi_info` VALUES ('3', '020', 'dc5c7986daef50c1e02ab09b442ee34f', '李景阳', '女', '6460848', '330327199504059511', '56547474@qq.com', '[189]', 'abc', '注册用户', '是', '2021-12-13 13:16:12');
INSERT INTO `yonghuxinxi_info` VALUES ('4', '016', 'dc5c7986daef50c1e02ab09b442ee34f', '鹿晗', '女', '4231999', '330327198501020300', 'grorti@qq.com', '[190]', 'abc', '注册用户', '是', '2021-12-13 13:16:12');
INSERT INTO `yonghuxinxi_info` VALUES ('5', '021', 'dc5c7986daef50c1e02ab09b442ee34f', '陈小巧', '女', '7856954', '330327198708070789', 'grhtrhk@163.com', '[191]', '没问题', '注册用户', '是', '2021-12-13 13:16:12');
INSERT INTO `yonghuxinxi_info` VALUES ('6', '022', 'dc5c7986daef50c1e02ab09b442ee34f', '梅邹雁', '男', '6498567', '330327198615482633', '43643933@qq.com', '[192]', 'ok', '注册用户', '是', '2021-12-13 13:16:12');
INSERT INTO `yonghuxinxi_info` VALUES ('7', '019', 'dc5c7986daef50c1e02ab09b442ee34f', '陈智一', '女', '8945622', '330327185403060589', 'trodd@yahoo.com', '[193]', 'ok', '注册用户', '是', '2021-12-13 13:16:12');
INSERT INTO `yonghuxinxi_info` VALUES ('8', '009', 'dc5c7986daef50c1e02ab09b442ee34f', '哈登', '男', '6460233', '330327198406150488', '	fegge@163.com', '[194]', '暂无', '注册用户', '是', '2021-12-13 13:16:12');
INSERT INTO `yonghuxinxi_info` VALUES ('9', '001', 'dc5c7986daef50c1e02ab09b442ee34f', '胡歌', '男', '4265325', '330327198811020456', 'regrodd@qq.com', '[195]', 'ok', '注册用户', '是', '2021-12-13 13:16:12');

-- ----------------------------
-- Table structure for `zhuceyonghu_info`
-- ----------------------------
DROP TABLE IF EXISTS `zhuceyonghu_info`;
CREATE TABLE `zhuceyonghu_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `zhanghao` varchar(50) DEFAULT NULL,
  `mima` varchar(50) DEFAULT NULL,
  `xingming` varchar(50) DEFAULT NULL,
  `xingbie` varchar(50) DEFAULT NULL,
  `shouji` varchar(50) DEFAULT NULL,
  `shenfenzheng` varchar(50) DEFAULT NULL,
  `zhaopian` varchar(50) DEFAULT NULL,
  `beizhu` varchar(500) DEFAULT NULL,
  `status` varchar(10) DEFAULT '是',
  `level` varchar(10) DEFAULT '注册用户',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of zhuceyonghu_info
-- ----------------------------
INSERT INTO `zhuceyonghu_info` VALUES ('2', '009', 'dc5c7986daef50c1e02ab09b442ee34f', '张燕', '男', '13052154877', '330327199010142546', '[213]', '没问题', '是', '注册用户', '2022-12-03 20:52:03');
INSERT INTO `zhuceyonghu_info` VALUES ('3', '021', 'dc5c7986daef50c1e02ab09b442ee34f', '林嘉俐', '女', '13489665487', '330327198811020456', '[214]', 'ok', '是', '注册用户', '2022-12-03 20:52:03');
INSERT INTO `zhuceyonghu_info` VALUES ('4', '029', 'dc5c7986daef50c1e02ab09b442ee34f', '陈明希', '女', '13186835580', '330327199005060003', '[215]', 'abc', '是', '注册用户', '2022-12-03 20:52:03');
INSERT INTO `zhuceyonghu_info` VALUES ('5', '023', 'dc5c7986daef50c1e02ab09b442ee34f', '沈古璐', '男', '13769548711', '330327185403060589', '[213]', 'abc', '是', '注册用户', '2022-12-03 20:52:03');
INSERT INTO `zhuceyonghu_info` VALUES ('6', '004', 'dc5c7986daef50c1e02ab09b442ee34f', '易建联', '男', '18956482221', '330327198805060222', '[213]', '无', '是', '注册用户', '2022-12-03 20:52:03');
INSERT INTO `zhuceyonghu_info` VALUES ('7', '018', 'dc5c7986daef50c1e02ab09b442ee34f', '马庆炳', '男', '13587835380', '330327198406150488', '[213]', '暂无', '是', '注册用户', '2022-12-03 20:52:03');
INSERT INTO `zhuceyonghu_info` VALUES ('8', '006', 'dc5c7986daef50c1e02ab09b442ee34f', '伍兆斌', '女', '13623256544', '330327199504059511', '[213]', 'ok', '是', '注册用户', '2022-12-03 20:52:03');
INSERT INTO `zhuceyonghu_info` VALUES ('9', '001', 'dc5c7986daef50c1e02ab09b442ee34f', '林书豪', '男', '13544655202', '33032719900723568X', '[213]', 'ok', '是', '注册用户', '2022-12-03 20:52:03');
