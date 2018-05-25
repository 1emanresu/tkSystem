/*
Navicat MySQL Data Transfer

Source Server         : l1
Source Server Version : 50505
Source Host           : 127.0.0.1:3306
Source Database       : tk_manage

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2018-05-25 17:53:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tk_apply`
-- ----------------------------
DROP TABLE IF EXISTS `tk_apply`;
CREATE TABLE `tk_apply` (
  `tk_apply_id` varchar(255) NOT NULL COMMENT '唯一标示',
  `tk_apply_user_id` varchar(255) DEFAULT NULL COMMENT '申请人id',
  `tk_apply_good_id` varchar(255) DEFAULT NULL COMMENT '申请物料id',
  `tk_apply_good_amount` varchar(255) DEFAULT NULL COMMENT '申请物料数量',
  `tk_apply_good_time` varchar(255) DEFAULT NULL COMMENT '申请时间',
  `tk_apply_good_state` varchar(255) DEFAULT NULL COMMENT '申请状态',
  PRIMARY KEY (`tk_apply_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tk_apply
-- ----------------------------
INSERT INTO `tk_apply` VALUES ('１', '１', '１', '１', '１', '１');

-- ----------------------------
-- Table structure for `tk_channel`
-- ----------------------------
DROP TABLE IF EXISTS `tk_channel`;
CREATE TABLE `tk_channel` (
  `tk_channel_id` varchar(255) NOT NULL COMMENT '渠道id唯一标识',
  `tk_channel_name` varchar(255) DEFAULT NULL COMMENT '渠道名称',
  `tk_user_id` varchar(255) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`tk_channel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tk_channel
-- ----------------------------
INSERT INTO `tk_channel` VALUES ('1', '企业拓展', '1');
INSERT INTO `tk_channel` VALUES ('2', '竞品拦截', '1');
INSERT INTO `tk_channel` VALUES ('3', '派单举牌', '1');
INSERT INTO `tk_channel` VALUES ('4', '社区巡展', '1');
INSERT INTO `tk_channel` VALUES ('5', '商圈巡展', '1');
INSERT INTO `tk_channel` VALUES ('6', '商圈拓客', '1');

-- ----------------------------
-- Table structure for `tk_client_goods`
-- ----------------------------
DROP TABLE IF EXISTS `tk_client_goods`;
CREATE TABLE `tk_client_goods` (
  `tk_client_goods_id` varchar(255) NOT NULL COMMENT '客户礼品信息编号',
  `tk_client_id` varchar(255) DEFAULT NULL COMMENT '客户编号',
  `tk_client_goods_name` varchar(255) DEFAULT NULL COMMENT '礼品名',
  `tk_client_goods_amount` varchar(255) DEFAULT NULL COMMENT '礼品数量',
  PRIMARY KEY (`tk_client_goods_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tk_client_goods
-- ----------------------------
INSERT INTO `tk_client_goods` VALUES ('152600518201138', '152600518076895', '西瓜', '1');
INSERT INTO `tk_client_goods` VALUES ('152600519391486', '152600519340110', '西瓜', '1');
INSERT INTO `tk_client_goods` VALUES ('152600764792668', '152600764791592', 'undefined', '2');
INSERT INTO `tk_client_goods` VALUES ('152600791638263', '152600791620851', '324', '2');
INSERT INTO `tk_client_goods` VALUES ('152600810409992', '152600810399969', '4', '1');
INSERT INTO `tk_client_goods` VALUES ('152603162155935', '152603162155660', '大道', '1');
INSERT INTO `tk_client_goods` VALUES ('152603291208592', '152603291205281', '西瓜', '1');
INSERT INTO `tk_client_goods` VALUES ('152603395632927', '152603395629340', '西瓜', '1');
INSERT INTO `tk_client_goods` VALUES ('152635319723729', '152635319713469', '西瓜', '1');
INSERT INTO `tk_client_goods` VALUES ('152635339719119', '152635339718230', '西瓜', '1');

-- ----------------------------
-- Table structure for `tk_client_info`
-- ----------------------------
DROP TABLE IF EXISTS `tk_client_info`;
CREATE TABLE `tk_client_info` (
  `tk_client_id` varchar(255) NOT NULL COMMENT '客户编号',
  `tk_client_name` varchar(255) DEFAULT NULL COMMENT '客户名字',
  `tk_client_joindate` varchar(255) DEFAULT NULL COMMENT '客户加入时间',
  `tk_client_phone` varchar(255) DEFAULT NULL COMMENT '客户手机号码',
  `tk_client_location` varchar(255) DEFAULT NULL COMMENT '客户地址',
  `tk_client_user_id` varchar(255) DEFAULT NULL COMMENT '客户信息录入人',
  `tk_client_type_id` varchar(255) DEFAULT NULL COMMENT '客户类型id',
  `tk_channel_id` varchar(255) DEFAULT NULL COMMENT '渠道id',
  `tk_plan_id` varchar(255) DEFAULT NULL COMMENT '任务id',
  `tk_client_photo` varchar(255) DEFAULT NULL COMMENT '照片',
  `tk_client_sex` varchar(255) DEFAULT NULL COMMENT '性别',
  `tk_client_location_detail` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `tk_client_fresh_date` varchar(255) DEFAULT NULL COMMENT '客户跟进时间',
  PRIMARY KEY (`tk_client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tk_client_info
-- ----------------------------
INSERT INTO `tk_client_info` VALUES ('152568633885233', '刘禅', '2018-05-11 10:19:42', '13377786910', '三国阆中', '152205226151757', '2018-05-22 11:33:04', '1', '152568016175057', '%%', '1', null, '2018-05-22 11:33:04');
INSERT INTO `tk_client_info` VALUES ('152568691609266', '保罗乔治', '2018-05-11 10:19:42', '13377786910', '美国芝加哥', '152205226151757', '2018-05-22 11:24:24', '1', '152568016175057', 'http://192.168.0.152:8080/tkSystem//static/img/tkClientPhoto/152568691609266/0.PNG', '1', null, '2018-05-22 11:24:24');
INSERT INTO `tk_client_info` VALUES ('152600344025078', 'Ajim', '2018-05-11 10:19:42', '13377786910', null, '152205226151757', '2018-05-22 11:24:24', '1', '152568016175057', '%%,', '1', null, '2018-05-22 11:24:24');
INSERT INTO `tk_client_info` VALUES ('152600518076895', '罗乔治', '2018-05-11 10:19:42', '13377786910', '广东', '152205226151757', '2018-05-22 11:24:24', '1', '152568016175057', 'tkClientPhoto', '1&tk_channel_id=1&tk_client_type_id=1', 'hongkong', '2018-05-22 11:24:24');
INSERT INTO `tk_client_info` VALUES ('152600519340110', '乔治', '2018-05-11 10:19:53', '13377786910', '广东', '152205226151757', '2018-05-22 11:24:24', '1', '152568016175057', 'tkClientPhoto', '1&tk_channel_id=1&tk_client_type_id=1', 'hongkong', '2018-05-22 11:24:24');
INSERT INTO `tk_client_info` VALUES ('152600764791592', '5435', '2018-05-11 11:00:47', '13377786910', 'undefined', '152205226151757', '2018-05-22 11:24:24', '1', '152523051620962', 'tkClientPhoto', '女', 'undefined', '2018-05-22 11:24:24');
INSERT INTO `tk_client_info` VALUES ('152600791620851', '4444', '2018-05-11 11:05:16', '13377786910', '4324', '152205226151757', '2018-05-22 11:24:24', '1', '152523051620962', 'tkClientPhoto', '女', 'undefined', '2018-05-22 11:24:24');
INSERT INTO `tk_client_info` VALUES ('152600810399969', 'e', '2018-05-11 11:08:24', '13377786910', '4214', '152205226151757', '2018-05-22 11:24:24', '1', '152523051620962', 'tkClientPhoto', '女', '4124', '2018-05-22 11:24:24');
INSERT INTO `tk_client_info` VALUES ('152603162155660', '大道', '2018-05-11 17:40:21', '13377786910', '多大的顶顶顶顶顶', '152205226151757', '2018-05-22 11:24:24', '1', '152603118855240', 'tkClientPhoto', '女', '大道', '2018-05-22 11:24:24');
INSERT INTO `tk_client_info` VALUES ('152603291205281', '保罗乔治道', '2018-05-11 18:01:52', '13377786910', '广东', '152205226151757', '2018-05-22 11:24:24', '1', '152603118855240', 'tkClientPhoto', '1&tk_channel_id=1&tk_client_type_id=1', 'hongkong', '2018-05-22 11:24:24');
INSERT INTO `tk_client_info` VALUES ('152603395629340', '治道', '2018-05-11 18:19:16', '13377786910', '广东', '152205226151757', '2018-05-22 11:24:24', '1', '152603118855240', 'tkClientPhoto', '1&tk_channel_id=1&tk_client_type_id=1', 'hongkong', '2018-05-22 11:24:24');
INSERT INTO `tk_client_info` VALUES ('152635319713469', '罗乔', '2018-05-15 10:59:57', '13377786910', '广东', '152205226151757', '2018-05-22 11:24:24', '1', '1', '152635319713452', '1&tk_channel_id=1&tk_client_type_id=1', 'hongkong', '2018-05-22 11:24:24');
INSERT INTO `tk_client_info` VALUES ('152635339718230', '保罗乔治123', '2018-05-15 11:03:17', '13377786910', '广东', '152205226151757', '2018-05-22 11:24:24', '1', '152603329209438', '152635339718291', '1&tk_channel_id=1&tk_client_type_id=1', 'hongkong', '2018-05-22 11:24:24');
INSERT INTO `tk_client_info` VALUES ('a74899f2a5214d7cb6327ca195d426fe', '羽生结弦', '2018-04-27 14:56:29', '13377786910', '中国香港', '152205226151757', '2018-05-22 11:24:24', '1', '152523051620962', null, '0', null, '2018-05-22 11:24:24');
INSERT INTO `tk_client_info` VALUES ('caf623af67914c649905b65321c468aa', '张雨生', '2018-04-27 14:54:31', '13377786910', '广东梅州', '152205226151757', '2018-05-22 11:24:24', '1', '152523051620962', null, '1', null, '2018-05-22 11:24:24');
INSERT INTO `tk_client_info` VALUES ('ded3e83117ce4cbfa041cce38b4c3b64', '王德辉', '2018-04-27 14:48:18', '13377786910', '广东茂名', '152205226151757', '2018-05-22 11:24:24', '1', '152523051620962', null, '1', null, '2018-05-22 11:24:24');

-- ----------------------------
-- Table structure for `tk_client_photo`
-- ----------------------------
DROP TABLE IF EXISTS `tk_client_photo`;
CREATE TABLE `tk_client_photo` (
  `tk_client_photo_id` varchar(255) NOT NULL COMMENT '客户图片信息编号',
  `tk_client_photo_url` varchar(255) NOT NULL COMMENT '客户图片信息url',
  `tk_client_id` varchar(255) NOT NULL COMMENT '客户编号',
  PRIMARY KEY (`tk_client_photo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户图片信息表';

-- ----------------------------
-- Records of tk_client_photo
-- ----------------------------
INSERT INTO `tk_client_photo` VALUES ('152600344025088', 'http://n.sinaimg.cn/news/transform/175/w105h70/20180523/tqbM-fzrwiaz5778371.jpg', '152600344025078');
INSERT INTO `tk_client_photo` VALUES ('152600518076882', 'http://n.sinaimg.cn/translate/39/w1048h591/20180523/7p83-hawmauc2407512.jpg', '152600518076895');
INSERT INTO `tk_client_photo` VALUES ('152600519340179', 'http://n.sinaimg.cn/mil/crawl/100/w550h350/20180523/3rK1-hawmauc3278635.jpg', '152600519340110');
INSERT INTO `tk_client_photo` VALUES ('152600764791586', 'http://n.sinaimg.cn/index/mid_article/images/sinaEnt.png', '152600764791592');
INSERT INTO `tk_client_photo` VALUES ('152600791620837', 'http://n.sinaimg.cn/games/175/w105h70/20180518/FOrc-harvfhv2289300.jpg', '152600791620851');
INSERT INTO `tk_client_photo` VALUES ('152600810399920', 'http://n.sinaimg.cn/homepage/paizhao/b5.jpg', '152600810399969');
INSERT INTO `tk_client_photo` VALUES ('152603162155794', 'http://n.sinaimg.cn/news/transform/579/w340h239/20180523/cQS4-hawmauc2819766.jpg', '152603162155660');
INSERT INTO `tk_client_photo` VALUES ('152603291205364', 'http://n.sinaimg.cn/blog/175/w105h70/20180521/4a5P-hawmaua0122222.jpg', '152603291205281');
INSERT INTO `tk_client_photo` VALUES ('152603395629329', 'http://n.sinaimg.cn/news/transform/579/w340h239/20180523/TlTQ-fzrwiaz5772658.jpg', '152603395629340');
INSERT INTO `tk_client_photo` VALUES ('152635319713452', 'http://image2.sina.com.cn/c.gif', '152635319713469');
INSERT INTO `tk_client_photo` VALUES ('152635339718291', 'http://n.sinaimg.cn/news/transform/63/w550h313/20180523/_fUd-hawmauc3294099.jpg', '152635339718230');

-- ----------------------------
-- Table structure for `tk_client_type`
-- ----------------------------
DROP TABLE IF EXISTS `tk_client_type`;
CREATE TABLE `tk_client_type` (
  `tk_type_id` varchar(255) NOT NULL COMMENT '客户类型id［唯一标示］',
  `tk_type_name` varchar(255) DEFAULT NULL COMMENT '客户类型名称',
  `tk_type_time` varchar(255) DEFAULT NULL COMMENT '入列时间',
  PRIMARY KEY (`tk_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tk_client_type
-- ----------------------------
INSERT INTO `tk_client_type` VALUES ('1', '公共客户', '2018-04-28 10:16:13');
INSERT INTO `tk_client_type` VALUES ('2', '可成交客户', '2018-04-28 10:16:13');
INSERT INTO `tk_client_type` VALUES ('3', '待跟进客户', '2018-04-28 10:16:13');

-- ----------------------------
-- Table structure for `tk_function`
-- ----------------------------
DROP TABLE IF EXISTS `tk_function`;
CREATE TABLE `tk_function` (
  `tk_function_id` varchar(255) NOT NULL COMMENT '功能id唯一标示',
  `tk_function_name` varchar(255) DEFAULT NULL COMMENT '功能名称',
  `tk_function_pid` varchar(255) DEFAULT NULL COMMENT '功能父id',
  PRIMARY KEY (`tk_function_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tk_function
-- ----------------------------
INSERT INTO `tk_function` VALUES ('１', '１', '１');

-- ----------------------------
-- Table structure for `tk_good`
-- ----------------------------
DROP TABLE IF EXISTS `tk_good`;
CREATE TABLE `tk_good` (
  `tk_good_id` varchar(255) NOT NULL COMMENT '物料id唯一标示',
  `tk_good_name` varchar(255) DEFAULT NULL COMMENT '物料名称',
  `tk_good_type` varchar(255) DEFAULT NULL COMMENT '物料类型',
  `tk_good_price` varchar(255) DEFAULT NULL COMMENT '物料价格',
  `tk_good_insert_time` varchar(255) DEFAULT NULL COMMENT '物料入列时间',
  `tk_good_amount` varchar(255) DEFAULT NULL COMMENT '物料数量',
  PRIMARY KEY (`tk_good_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tk_good
-- ----------------------------
INSERT INTO `tk_good` VALUES ('152602522029356', '牧马人鼠标垫', '1', '1.00', '2018-05-11 15:53:40', '12');
INSERT INTO `tk_good` VALUES ('152602541477182', '牧马人卡车套件', '3', '1200.00', '2018-05-11 15:56:54', '3');

-- ----------------------------
-- Table structure for `tk_good_apply_good`
-- ----------------------------
DROP TABLE IF EXISTS `tk_good_apply_good`;
CREATE TABLE `tk_good_apply_good` (
  `tk_good_apply_id` varchar(255) NOT NULL COMMENT '申请物料id唯一标示',
  `tk_good_apply_name` varchar(255) DEFAULT NULL COMMENT '申请物料名称',
  `tk_good_apply_user_id` varchar(255) DEFAULT NULL COMMENT '申请物料者id',
  `tk_good_apply_good_id` varchar(255) DEFAULT NULL COMMENT '物料id',
  `tk_good_apply_good_amount` varchar(255) DEFAULT NULL COMMENT '物料数量',
  `tk_good_apply_good_time` varchar(255) DEFAULT NULL COMMENT '时间',
  `tk_good_apply_state` varchar(255) DEFAULT NULL COMMENT '状态1审批通过,非1不通过',
  `tk_good_apply_feedback_time` varchar(255) DEFAULT NULL COMMENT '反馈',
  `tk_good_apply_remark` varchar(255) NOT NULL,
  `tk_good_apply_plan_id` varchar(255) NOT NULL COMMENT '任务id',
  PRIMARY KEY (`tk_good_apply_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tk_good_apply_good
-- ----------------------------
INSERT INTO `tk_good_apply_good` VALUES ('1', '电风扇', '152205226151755', '152229387241073', '5', '2018-03-29 11:24:32', '0', null, '', '');
INSERT INTO `tk_good_apply_good` VALUES ('152490657771768', '电风扇', '152205226151754', null, '1', null, null, null, '1', '1');
INSERT INTO `tk_good_apply_good` VALUES ('152490696362687', '电风扇', '152205226151755', null, '1', '2018-04-28 17:16:03', '-1', null, '1', '1');
INSERT INTO `tk_good_apply_good` VALUES ('152490754940019', '电风扇', '152205226151757', null, '1', '2018-04-28 17:25:49', '-1', null, '1', '1');
INSERT INTO `tk_good_apply_good` VALUES ('152490756328311', '电风扇', '152205226151755', null, '1', '2018-04-28 17:26:03', '-1', null, '1', '1');
INSERT INTO `tk_good_apply_good` VALUES ('152663584913226', '电风扇', '152205226151755', null, '1', '2018-05-18 17:30:49', '-1', null, '1', '1');
INSERT INTO `tk_good_apply_good` VALUES ('152663599410446', '电风扇', '152205226151755', null, '1', '2018-05-18 17:33:14', '-1', null, '1', '152603118855240');
INSERT INTO `tk_good_apply_good` VALUES ('152663733163977', '电风扇', '152205226151755', null, '2', '2018-05-18 17:55:31', '-1', null, '天气热', '152603118855240');
INSERT INTO `tk_good_apply_good` VALUES ('152663739143470', '电风扇', '152205226151757', null, '1', '2018-05-18 17:56:31', '0', null, '卡顿', '152603118855240');
INSERT INTO `tk_good_apply_good` VALUES ('152663748026442', '电风扇', '152205226151757', null, '1', '2018-05-18 17:58:00', '-1', null, '324', '152603118855240');
INSERT INTO `tk_good_apply_good` VALUES ('152663751051796', '电风扇', '152205226151755', null, '1', '2018-05-18 17:58:30', '1', null, '3213', '152603118855240');
INSERT INTO `tk_good_apply_good` VALUES ('152663753432281', '电风扇', '152205226151757', null, '1', '2018-05-18 17:58:54', '0', null, '3424', '152603118855240');
INSERT INTO `tk_good_apply_good` VALUES ('152689127649390', 'FFFF', '152205226151757', null, '2', '2018-05-21 16:27:56', '1', null, 'VCVCV', '152603118855240');
INSERT INTO `tk_good_apply_good` VALUES ('152689171616359', 'GGGG', '152205226151757', null, '1', '2018-05-21 16:35:16', '-1', null, 'GGG', '152603118855240');
INSERT INTO `tk_good_apply_good` VALUES ('152689185302858', 'UUUU', '152205226151757', null, '1', '2018-05-21 16:37:33', '1', null, 'IIII', '152603118855240');
INSERT INTO `tk_good_apply_good` VALUES ('152715456605331', 'fsaf', '152205226151757', null, '1', '2018-05-24 17:36:06', '-1', null, 'FFFF', '152603118855240');
INSERT INTO `tk_good_apply_good` VALUES ('85b87e3e48764df2bddfbb00f58d4e16', '电风扇', '152205226151757', '152412192809652', '3', '2018-04-19 15:12:08', null, null, '', '');
INSERT INTO `tk_good_apply_good` VALUES ('897abb34d9f446e5bf70585ba0e052c3', '电风扇', '123', '152410526994786', '123', '2018-04-19 10:34:29', null, null, '', '');
INSERT INTO `tk_good_apply_good` VALUES ('１', '电风扇', '１', '１', '１', '１', '１', '１', '', '');

-- ----------------------------
-- Table structure for `tk_good_type`
-- ----------------------------
DROP TABLE IF EXISTS `tk_good_type`;
CREATE TABLE `tk_good_type` (
  `tk_good_type_id` varchar(255) NOT NULL COMMENT '物料类型id',
  `tk_good_type_name` varchar(255) DEFAULT NULL COMMENT '物料类型名称',
  `tk_good_type_time` varchar(255) DEFAULT NULL COMMENT '物料类型rul时间',
  PRIMARY KEY (`tk_good_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tk_good_type
-- ----------------------------
INSERT INTO `tk_good_type` VALUES ('1', '初级小礼品', '１');

-- ----------------------------
-- Table structure for `tk_loc`
-- ----------------------------
DROP TABLE IF EXISTS `tk_loc`;
CREATE TABLE `tk_loc` (
  `tk_loc_id` varchar(255) NOT NULL COMMENT '位置id',
  `tk_loc_pid` varchar(255) DEFAULT NULL COMMENT '父id',
  `tk_loc_latitude` varchar(255) DEFAULT NULL COMMENT '纬度',
  `tk_loc_longitude` varchar(255) DEFAULT NULL COMMENT '经度',
  `tk_loc_name` varchar(255) DEFAULT NULL COMMENT '位置名称',
  `tk_loc_time` varchar(255) DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`tk_loc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tk_loc
-- ----------------------------
INSERT INTO `tk_loc` VALUES ('１', '１', '１', '１', '１', '１');

-- ----------------------------
-- Table structure for `tk_location`
-- ----------------------------
DROP TABLE IF EXISTS `tk_location`;
CREATE TABLE `tk_location` (
  `tk_location_id` varchar(255) NOT NULL,
  `latitude` varchar(255) NOT NULL COMMENT '经度',
  `longitude` varchar(255) NOT NULL COMMENT '维度',
  `tk_location_detail` varchar(255) NOT NULL COMMENT '位置信息',
  PRIMARY KEY (`tk_location_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='位置信息表';

-- ----------------------------
-- Records of tk_location
-- ----------------------------
INSERT INTO `tk_location` VALUES ('152714318275543', '23.02067', '113.75179', '广东省东莞市鸿福路99号');
INSERT INTO `tk_location` VALUES ('152714346540017', '23.02067', '113.75179', '广东省东莞市鸿福路99号');
INSERT INTO `tk_location` VALUES ('152714381616749', '23.02067', '113.75179', '广东省东莞市鸿福路99号');
INSERT INTO `tk_location` VALUES ('152714385570170', '23.02067', '113.75179', '广东省东莞市鸿福路99号');
INSERT INTO `tk_location` VALUES ('152714386192938', '21.02067', '114.75179', '广东省东莞市鸿福路99号');
INSERT INTO `tk_location` VALUES ('152714398449069', '23.02067', '113.75179', '广东省东莞市鸿福路99号');
INSERT INTO `tk_location` VALUES ('152714399706294', '23.02067', '113.75179', '广东省东莞市鸿福路99号');
INSERT INTO `tk_location` VALUES ('152714438133572', '23.02067', '113.75179', '广东省东莞市鸿福路99号');
INSERT INTO `tk_location` VALUES ('152714445766970', '23.02067', '113.75179', '广东省东莞市鸿福路99号');
INSERT INTO `tk_location` VALUES ('152714463224278', '23.02067', '113.75179', '广东省东莞市鸿福路99号');
INSERT INTO `tk_location` VALUES ('152714492740930', '23.02067', '113.75179', '广东省东莞市鸿福路99号');
INSERT INTO `tk_location` VALUES ('152714514246934', '23.02067', '113.75179', '广东省东莞市鸿福路99号');
INSERT INTO `tk_location` VALUES ('152714515958189', '23.02067', '113.75179', '广东省东莞市鸿福路99号');
INSERT INTO `tk_location` VALUES ('152714831001421', '23.02067', '113.75179', '广东省东莞市鸿福路99号');
INSERT INTO `tk_location` VALUES ('152714843967634', '23.02067', '113.75179', '广东省东莞市鸿福路99号');
INSERT INTO `tk_location` VALUES ('152714883446429', '23.02067', '113.75179', '广东省东莞市鸿福路99号');
INSERT INTO `tk_location` VALUES ('152714890707739', '23.02067', '113.75179', '广东省东莞市鸿福路99号');
INSERT INTO `tk_location` VALUES ('152714891062817', '23.02067', '113.75179', '广东省东莞市鸿福路99号');
INSERT INTO `tk_location` VALUES ('152714891585249', '23.02067', '113.75179', '广东省东莞市鸿福路99号');
INSERT INTO `tk_location` VALUES ('152714922724311', '23.02067', '113.75179', '广东省东莞市鸿福路99号');
INSERT INTO `tk_location` VALUES ('152714969332985', '23.02146', '113.749094', '广东省东莞市胜和路');
INSERT INTO `tk_location` VALUES ('152715294822536', '23.02067', '113.75179', '广东省东莞市鸿福路99号');

-- ----------------------------
-- Table structure for `tk_login_history`
-- ----------------------------
DROP TABLE IF EXISTS `tk_login_history`;
CREATE TABLE `tk_login_history` (
  `tk_login_history_id` varchar(255) CHARACTER SET armscii8 NOT NULL,
  `tk_login_history_name` varchar(255) CHARACTER SET armscii8 NOT NULL,
  `tk_login_history_time` varchar(255) CHARACTER SET armscii8 NOT NULL,
  `tk_login_history_date` varchar(255) CHARACTER SET armscii8 NOT NULL,
  `tk_user_id` varchar(255) CHARACTER SET armscii8 NOT NULL,
  PRIMARY KEY (`tk_login_history_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tk_login_history
-- ----------------------------
INSERT INTO `tk_login_history` VALUES ('?', '?', '?', '?', '?');

-- ----------------------------
-- Table structure for `tk_plan`
-- ----------------------------
DROP TABLE IF EXISTS `tk_plan`;
CREATE TABLE `tk_plan` (
  `tk_plan_id` varchar(255) NOT NULL COMMENT '任务id',
  `tk_plan_user_id` varchar(255) DEFAULT NULL COMMENT '任务发布者id',
  `tk_plan_user_name` varchar(255) DEFAULT NULL COMMENT '发布者名称',
  `tk_client_type_id` varchar(255) DEFAULT NULL COMMENT '客户类型id',
  `tk_plan_loc_id` varchar(255) DEFAULT NULL COMMENT '任务位置id',
  `tk_plan_time` varchar(255) DEFAULT NULL COMMENT '任务发布时间',
  `tk_plan_target` varchar(255) DEFAULT NULL COMMENT '任务目标',
  `tk_plan_state` varchar(255) DEFAULT NULL COMMENT '该任务状态 1默认 2已经签到3已分配',
  `tk_plan_name` varchar(255) NOT NULL COMMENT '任务名称',
  PRIMARY KEY (`tk_plan_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tk_plan
-- ----------------------------
INSERT INTO `tk_plan` VALUES ('152603118855240', '152205226151757', null, null, null, '2018-05-23 11:25:06', '123', '2', '嗷嗷嗷');
INSERT INTO `tk_plan` VALUES ('152603329209438', '152205226151757', null, null, null, '2018-05-22 18:08:12', '1', '3', '2018年5月11日18:05:56 测试');
INSERT INTO `tk_plan` VALUES ('152705717438288', '152205226151757', null, null, null, '2018-05-23 14:32:54', '2', '1', '走西口2圈');
INSERT INTO `tk_plan` VALUES ('152714385569940', '152205226151757', null, null, null, '2018-05-24 14:37:35', '1', '1', 'CCC');
INSERT INTO `tk_plan` VALUES ('152714386192842', '152205226151757', null, null, null, '2018-05-24 14:37:41', '1', '1', 'CCC');
INSERT INTO `tk_plan` VALUES ('152714398445327', '152205226151757tkUserToken=36c77990c8584246856dd7b56e0bb8ea', null, null, null, '2018-05-24 14:39:44', '2', '1', 'èµ°è¥¿å£2å');
INSERT INTO `tk_plan` VALUES ('152714399706190', '152205226151757', null, null, null, '2018-05-24 14:39:57', '1', '1', 'CCC');
INSERT INTO `tk_plan` VALUES ('152714514246697', '152205226151757', null, null, null, '2018-05-24 14:59:02', '1', '1', 'CCC');
INSERT INTO `tk_plan` VALUES ('152714515958052', '152205226151757', null, null, null, '2018-05-24 14:59:19', '1', '1', 'CCC');
INSERT INTO `tk_plan` VALUES ('152714831001210', '152205226151757', null, null, null, '2018-05-24 15:51:50', '11', '1', 'GGG');
INSERT INTO `tk_plan` VALUES ('152714843967579', '152205226151757', null, null, null, '2018-05-24 15:53:59', '12', '1', 'XXX');
INSERT INTO `tk_plan` VALUES ('152714883442646', '152205226151757', null, null, null, '2018-05-24 16:00:34', '1', '1', 'CCC');
INSERT INTO `tk_plan` VALUES ('152714890704165', '152205226151757', null, null, null, '2018-05-24 16:01:47', '1', '1', 'CCC');
INSERT INTO `tk_plan` VALUES ('152714891062783', '152205226151757', null, null, null, '2018-05-24 16:01:50', '1', '1', 'CCC');
INSERT INTO `tk_plan` VALUES ('152714891585158', '152205226151757', null, null, null, '2018-05-24 16:01:55', '1', '1', 'CCC');
INSERT INTO `tk_plan` VALUES ('152714922724216', '152205226151757', null, null, null, '2018-05-24 16:07:07', '2', '1', 'TTT');
INSERT INTO `tk_plan` VALUES ('152714969332813', '152205226151757', null, null, null, '2018-05-24 16:14:53', '1', '1', 'QQQ');
INSERT INTO `tk_plan` VALUES ('152715294822466', '152205226151757', null, null, null, '2018-05-24 17:09:08', '5', '1', 'YYYY');

-- ----------------------------
-- Table structure for `tk_plan_detail`
-- ----------------------------
DROP TABLE IF EXISTS `tk_plan_detail`;
CREATE TABLE `tk_plan_detail` (
  `tk_plan_detail_id` varchar(255) NOT NULL COMMENT '任务id',
  `tk_plan_detail_title` varchar(255) DEFAULT NULL COMMENT '任务标题',
  `tk_plan_detail_remark` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `tk_plan_detail_start` varchar(255) DEFAULT NULL COMMENT '开始时间',
  `tk_plan_detail_end` varchar(255) DEFAULT NULL COMMENT '结束时间',
  `tk_plan_detail_target` varchar(255) DEFAULT NULL COMMENT '任务目标',
  `tk_plan_detail_contacts_name` varchar(255) DEFAULT NULL COMMENT '联系人姓名',
  `tk_plan_detail_contacts_sex` varchar(255) DEFAULT NULL COMMENT '联系人性别',
  `tk_plan_detail_location` varchar(255) DEFAULT NULL COMMENT '所在地址',
  `tk_plan_detail_location_detail` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `tk_plan_detail_good_name` varchar(255) DEFAULT NULL COMMENT '礼品名称',
  `tk_plan_detail_good_amount` varchar(255) DEFAULT NULL COMMENT '礼品数量',
  `tk_plan_detail_photo` varchar(255) DEFAULT NULL COMMENT '照片',
  `tk_plan_detail_user_id` varchar(255) DEFAULT NULL COMMENT '发布者id',
  `tk_plan_detail_tkuser_id` varchar(255) DEFAULT NULL COMMENT '任务tk人员id',
  `tk_plan_detail_target_achieve` varchar(255) NOT NULL DEFAULT '0' COMMENT '完成目标数',
  `tk_plan_contacts_phone` varchar(255) DEFAULT NULL COMMENT '联系人电话',
  `tk_channel_id` varchar(255) DEFAULT NULL COMMENT '渠道id',
  `tk_plan_state` varchar(255) DEFAULT NULL COMMENT '该任务状态 1默认 2已经签到3已分配',
  `tk_good_id` varchar(255) DEFAULT NULL COMMENT '礼品id',
  `tk_location_id` varchar(255) NOT NULL COMMENT '位置编号',
  PRIMARY KEY (`tk_plan_detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务详细信息表';

-- ----------------------------
-- Records of tk_plan_detail
-- ----------------------------
INSERT INTO `tk_plan_detail` VALUES ('152603118855240', '嗷嗷嗷', '嗷嗷嗷', '2018-05-22 11:25:06', '2018-05-26 17:31:19', '123', '3213', '女', '213', '3213', '312312', '1', 'http://n.sinaimg.cn/www/transform/99/w550h349/20180424/b8MS-fzqvvsa3342883.jpg', '152205226151757', '152205226151757', '2', '3213', '4', '2', null, '152714386192938');
INSERT INTO `tk_plan_detail` VALUES ('152603329209438', '2018年5月11日18:05:56 测试', '流程测试', '2018-05-22 18:05:47', '2018-05-27 18:05:47', '1', '王志伟', '女', '卡布斯', 'B902', '铅笔', '1', 'http://n.sinaimg.cn/news/transform/109/w550h359/20180523/KijU-hawmauc2468323.jpg', '152205226151757', '152205226151755', '1', '19965156145', '4', null, null, '152714386192938');
INSERT INTO `tk_plan_detail` VALUES ('152714386192842', 'CCC', 'CCCCC', '2018-05-24 14:10:04', '2018-05-28 14:10:04', '1', 'd', '女', '广东省东莞市鸿福路99号', 'dsads', 'dsad', '1', 'http://n.sinaimg.cn/sports/transform/195/w105h90/20180520/FiOj-haturfs9929052.jpg', '152205226151757', '152205226151755', '0', 'dsad', '1', null, null, '152714386192938');
INSERT INTO `tk_plan_detail` VALUES ('152714514246697', 'CCC', 'CCCCC', '2018-05-24 14:10:04', '2018-05-29 14:10:04', '1', 'd', '女', '广东省东莞市鸿福路99号', 'dsads', 'dsad', '1', 'http://n.sinaimg.cn/auto/transform/350/w210h140/20180522/s_m9-fzrwiaz5729655.jpg', '152205226151757', '152205226151755', '0', 'dsad', '1', null, null, '152714514246934');
INSERT INTO `tk_plan_detail` VALUES ('152714515958052', 'CCC', 'CCCCC', '2018-05-24 14:10:04', '2018-05-30 14:10:04', '1', 'd', '女', '广东省东莞市鸿福路99号', 'dsads', 'dsad', '1', 'http://i1.sinaimg.cn/home/images/lx02.jpg', '152205226151757', '152205226151757', '0', 'dsad', '1', null, null, '152714515958189');
INSERT INTO `tk_plan_detail` VALUES ('152714831001210', 'GGG', 'GGG', '2018-05-24 15:51:20', '2018-05-28 15:51:20', '11', 'GG', '女', '广东省东莞市鸿福路99号', '2e3', 'e3e', '2', 'http://n.sinaimg.cn/games/82f31f45/20180516/3.jpg', '152205226151757', '152205226151757', '0', '3214214214', '1', null, null, '152714831001421');
INSERT INTO `tk_plan_detail` VALUES ('152714843967579', 'XXX', 'XXX', '2018-05-24 15:53:36', '2018-05-29 15:53:36', '12', 'XX', '女', '广东省东莞市鸿福路99号', '23123', '3213', '1', 'http://n.sinaimg.cn/www/transform/108/w475h433/20180424/_1gi-fzqvvsa3368284.jpg', '152205226151757', '152205226151757', '0', '3213', '1', null, null, '152714843967634');
INSERT INTO `tk_plan_detail` VALUES ('152714883442646', 'CCC', 'CCCCC', '2018-05-24 14:10:04', '2018-05-24 14:10:04', '1', 'd', '女', '广东省东莞市鸿福路99号', 'dsads', 'dsad', '1', 'http://n.sinaimg.cn/news/175/w105h70/20180523/eNwG-hawmauc2691378.jpg', '152205226151757', '152205226151757', '0', 'dsad', '1', null, null, '152714883446429');
INSERT INTO `tk_plan_detail` VALUES ('152714890704165', 'CCC', 'CCCCC', '2018-05-24 14:10:04', '2018-05-24 14:10:04', '1', 'd', '女', '广东省东莞市鸿福路99号', 'dsads', 'dsad', '1', 'http://n.sinaimg.cn/kandian/transform/20170620/_PTI-fyhfxph4388828.jpg', '152205226151757', '152205226151755', '0', 'dsad', '1', null, null, '152714890707739');
INSERT INTO `tk_plan_detail` VALUES ('152714891062783', 'CCC', 'CCCCC', '2018-05-24 14:10:04', '2018-05-24 14:10:04', '1', 'd', '女', '广东省东莞市鸿福路99号', 'dsads', 'dsad', '1', 'http://n.sinaimg.cn/news/crawl/647/w550h897/20180523/fg9P-fzrwiaz5764886.jpg', '152205226151757', '152205226151755', '0', 'dsad', '1', null, null, '152714891062817');
INSERT INTO `tk_plan_detail` VALUES ('152714891585158', 'CCC', 'CCCCC', '2018-05-24 14:10:04', '2018-05-24 14:10:04', '1', 'd', '女', '广东省东莞市鸿福路99号', 'dsads', 'dsad', '1', 'http://n.sinaimg.cn/www/transform/108/w475h433/20180424/_1gi-fzqvvsa3368284.jpg', '152205226151757', '152205226151755', '0', 'dsad', '1', null, null, '152714891585249');
INSERT INTO `tk_plan_detail` VALUES ('152714922724216', 'TTT', 'TTT', '2018-05-24 16:06:30', '2018-05-24 16:06:30', '2', 'TT', '男', '广东省东莞市鸿福路99号', '4324', '4324', '1', 'http://n.sinaimg.cn/news/175/w105h70/20180523/eNwG-hawmauc2691378.jpg', '152205226151757', '152205226151755', '0', '214314124', '1', null, null, '152714922724311');
INSERT INTO `tk_plan_detail` VALUES ('152714969332813', 'QQQ', 'QQQ', '2018-05-24 16:14:35', '2018-05-24 16:14:35', '1', '1', '女', '广东省东莞市胜和路', '111', '11', '1', '152714969332877', '152205226151757', '152205226151755', '0', '1111111', '1', null, null, '152714969332985');
INSERT INTO `tk_plan_detail` VALUES ('152715294822466', 'YYYY', 'YYYY', '2018-05-24 17:08:42', '2018-05-24 17:08:42', '5', 'YYY', '女', '广东省东莞市鸿福路99号', '&&&*', '235345', '2', '152715294822458', '152205226151757', '152205226151755', '0', '34234324', '1', null, null, '152715294822536');

-- ----------------------------
-- Table structure for `tk_plan_execute`
-- ----------------------------
DROP TABLE IF EXISTS `tk_plan_execute`;
CREATE TABLE `tk_plan_execute` (
  `tk_plan_execute_id` varchar(255) NOT NULL COMMENT '执行任务id',
  `tk_plan_execute_name` varchar(255) DEFAULT NULL COMMENT '执行计划名称',
  `tk_plan_execute_content` varchar(255) DEFAULT NULL COMMENT '执行计划内容以及情况',
  `tk_plan_id` varchar(255) DEFAULT NULL COMMENT '关联计划的id',
  `tk_plan_execute_start_time` varchar(255) DEFAULT NULL COMMENT '执行计划的start时间',
  `tk_plan_execute_end_time` varchar(255) DEFAULT NULL COMMENT '执行计划的end时间',
  `tk_plan_execute_cutorm_id` varchar(255) DEFAULT NULL COMMENT '执行计划客户id',
  `tk_user_id` varchar(255) DEFAULT NULL COMMENT '关联计划人id',
  `tk_plan_execute_state` varchar(255) DEFAULT NULL COMMENT '执行完成状态',
  `tk_plan_execute_remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `tk_plan_user_id` varchar(255) NOT NULL COMMENT '计划发布者id',
  PRIMARY KEY (`tk_plan_execute_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tk_plan_execute
-- ----------------------------
INSERT INTO `tk_plan_execute` VALUES ('152603119634766', null, null, '152603118855240', null, null, null, '152205226151757', null, null, '152205226151757');
INSERT INTO `tk_plan_execute` VALUES ('152603119634798', null, null, '152603118855240', null, null, null, '152205226151755', null, null, '152205226151757');
INSERT INTO `tk_plan_execute` VALUES ('152603119635738', null, null, '152603118855240', null, null, null, '152205226151758', null, null, '152205226151757');
INSERT INTO `tk_plan_execute` VALUES ('152603145434613', null, null, '152603118855240', null, null, null, '152205226151757', null, null, '152205226151757');
INSERT INTO `tk_plan_execute` VALUES ('152603332705178', null, null, '152603329209438', null, null, null, '152205226151755', null, null, '152205226151757');
INSERT INTO `tk_plan_execute` VALUES ('152603332705185', null, null, '152603329209438', null, null, null, '152205226151758', null, null, '152205226151757');
INSERT INTO `tk_plan_execute` VALUES ('152603332706194', null, null, '152603329209438', null, null, null, '152205226151757', null, null, '152205226151757');
INSERT INTO `tk_plan_execute` VALUES ('152637886904594', null, null, '152603118855240', null, null, null, '152205226151757', null, null, '152205226151757');
INSERT INTO `tk_plan_execute` VALUES ('152637890712817', null, null, '152603118855240', null, null, null, '152205226151757', null, null, '152205226151757');
INSERT INTO `tk_plan_execute` VALUES ('152637902970856', null, null, '152603118855240', null, null, null, '152205226151757', null, null, '152205226151757');
INSERT INTO `tk_plan_execute` VALUES ('152637917256251', null, null, '152603118855240', null, null, null, '152205226151757', null, null, '152205226151757');

-- ----------------------------
-- Table structure for `tk_plan_execute_client`
-- ----------------------------
DROP TABLE IF EXISTS `tk_plan_execute_client`;
CREATE TABLE `tk_plan_execute_client` (
  `tk_plan_execute_client_id` varchar(255) NOT NULL COMMENT '任务客户id',
  `tk_plan_execute_client_name` varchar(255) DEFAULT NULL COMMENT '渠道：社区巡展，客户名',
  `tk_plan_execute_client_photo` varchar(255) DEFAULT NULL COMMENT '渠道：社区巡展、商户合作，联系电话',
  `tk_plan_execute_client_phone` varchar(255) DEFAULT NULL COMMENT '渠道：社区巡展、商户合作，实时照片',
  `tk_plan_execute_client_loc` varchar(255) DEFAULT NULL COMMENT '渠道：社区巡展、商户合作，实时定位',
  `tk_plan_execute_client_good` varchar(255) DEFAULT NULL COMMENT '渠道：社区巡展、商户合作，拓客礼品',
  `tk_plan_execute_client_goodNum` varchar(255) DEFAULT NULL COMMENT '渠道：社区巡展、商户合作，拓客礼品数量',
  PRIMARY KEY (`tk_plan_execute_client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tk_plan_execute_client
-- ----------------------------
INSERT INTO `tk_plan_execute_client` VALUES ('06c879e338aa41f2b80ff85436471acc', 'tkPlanExecuteClientName', null, null, null, null, null);
INSERT INTO `tk_plan_execute_client` VALUES ('1', '1', null, null, null, null, null);

-- ----------------------------
-- Table structure for `tk_plan_photo`
-- ----------------------------
DROP TABLE IF EXISTS `tk_plan_photo`;
CREATE TABLE `tk_plan_photo` (
  `tk_plan_photo_id` varchar(255) NOT NULL COMMENT 'id',
  `tk_plan_photo_url` varchar(255) DEFAULT NULL COMMENT 'url',
  `tk_plan_id` varchar(255) DEFAULT NULL COMMENT '任务id',
  PRIMARY KEY (`tk_plan_photo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务关联的照片信息表';

-- ----------------------------
-- Records of tk_plan_photo
-- ----------------------------
INSERT INTO `tk_plan_photo` VALUES ('152583708845033', 'http://i11.esfimg.com/imp/58e2b693c2c0032baabd8303a0e2b14f_os992318.jpg', '152583708845084');
INSERT INTO `tk_plan_photo` VALUES ('152583709601967', 'http://n.sinaimg.cn/www/transform/20161108/QGkw-fxxneue1058740.png', '152583709601983');
INSERT INTO `tk_plan_photo` VALUES ('152585379196582', 'http://n.sinaimg.cn/games/175/w105h70/20180523/Y9dQ-hawmauc3882282.jpg', '152585379196512');
INSERT INTO `tk_plan_photo` VALUES ('152585391058570', 'http://n.sinaimg.cn/sports/transform/274/w164h110/20180523/KA0k-hawmauc1250079.jpg', '152585391058545');
INSERT INTO `tk_plan_photo` VALUES ('152585401104432', 'http://n.sinaimg.cn/translate/66/w500h366/20180523/DbfC-hawmauc2757055.jpg', '152585401104380');
INSERT INTO `tk_plan_photo` VALUES ('152585456874148', 'http://n.sinaimg.cn/translate/59/w550h309/20180523/M21l-hawmauc2189047.jpg', '152585456874152');
INSERT INTO `tk_plan_photo` VALUES ('152585569164951', 'http://n.sinaimg.cn/auto/transform/265/w171h94/20180517/UZcr-harvfhu4633327.jpg', '152585569164883');
INSERT INTO `tk_plan_photo` VALUES ('152585631563045', 'http://n.sinaimg.cn/sports/transform/274/w164h110/20180522/4A_Q-fzrwiaz5739132.jpg', '152585631563061');
INSERT INTO `tk_plan_photo` VALUES ('152585694800696', 'http://n.sinaimg.cn/auto/transform/265/w171h94/20180517/2X8m-harvfhu4638476.jpg', '152585694800677');
INSERT INTO `tk_plan_photo` VALUES ('152585698446383', 'http://n.sinaimg.cn/translate/66/w500h366/20180523/DbfC-hawmauc2757055.jpg', '152585698446339');
INSERT INTO `tk_plan_photo` VALUES ('152602326426835', 'http://n.sinaimg.cn/news/transform/175/w105h70/20180523/tqbM-fzrwiaz5778371.jpg', '152602326426764');
INSERT INTO `tk_plan_photo` VALUES ('152602620657140', 'http://n.sinaimg.cn/news/crawl/58/w550h308/20180523/i-9O-hawmauc1515326.jpg', '152602620657190');
INSERT INTO `tk_plan_photo` VALUES ('152602666778353', 'http://n.sinaimg.cn/news/195/w105h90/20180519/ogHj-haturfs6041900.jpg', '152602666778353');
INSERT INTO `tk_plan_photo` VALUES ('152602911917163', 'http://n.sinaimg.cn/finance/stt/pc/0427_logo.png', '152602911917158');
INSERT INTO `tk_plan_photo` VALUES ('152602924688135', 'http://n.sinaimg.cn/news/transform/797/w550h247/20180523/vi3E-hawmauc2201702.jpg', '152602924688142');
INSERT INTO `tk_plan_photo` VALUES ('152602933151256', 'http://n.sinaimg.cn/news/transform/42/w550h292/20180523/0YTT-hawmauc2201924.jpg', '152602933151220');
INSERT INTO `tk_plan_photo` VALUES ('152602968186278', 'http://n.sinaimg.cn/news/crawl/116/w550h366/20180523/m8f_-hawmauc1515271.jpg', '152602968186264');
INSERT INTO `tk_plan_photo` VALUES ('152603101522046', 'http://n.sinaimg.cn/ent/transform/175/w105h70/20180523/nIea-hawmauc1640271.jpg', '152603101521959');
INSERT INTO `tk_plan_photo` VALUES ('152603118855281', 'http://n.sinaimg.cn/mil/transform/116/w550h366/20180523/H2xB-hawmauc3277599.jpg', '152603118855240');
INSERT INTO `tk_plan_photo` VALUES ('152603329209565', 'http://n.sinaimg.cn/news/transform/368/w550h618/20180523/6OqV-hawmauc2200517.jpg', '152603329209438');
INSERT INTO `tk_plan_photo` VALUES ('152635213869413', 'http://n.sinaimg.cn/video/704/w440h264/20180523/u_zi-hawmauc2434368.jpg', '152635213869495');
INSERT INTO `tk_plan_photo` VALUES ('152714318272024', 'http://www.sina.com.cn/licence/3.jpg', '152714318272083');
INSERT INTO `tk_plan_photo` VALUES ('152714318275411', 'http://n.sinaimg.cn/news/195/w105h90/20180514/Kh_K-hapkuvk5083650.jpg', '152714318272083');
INSERT INTO `tk_plan_photo` VALUES ('152714346539749', 'http://n.sinaimg.cn/games/175/w105h70/20180523/Y9dQ-hawmauc3882282.jpg', '152714346539713');
INSERT INTO `tk_plan_photo` VALUES ('152714346539945', 'http://n.sinaimg.cn/translate/600/w800h600/20180523/TbsB-hawmauc2407450.jpg', '152714346539713');
INSERT INTO `tk_plan_photo` VALUES ('152714381616536', 'http://n.sinaimg.cn/sports/transform/195/w105h90/20180518/5WeZ-haturfs1819341.jpg', '152714381616414');
INSERT INTO `tk_plan_photo` VALUES ('152714381616633', 'http://n.sinaimg.cn/auto/transform/350/w210h140/20180522/s_m9-fzrwiaz5729655.jpg', '152714381616414');
INSERT INTO `tk_plan_photo` VALUES ('152714385569932', 'http://n.sinaimg.cn/games/175/w105h70/20180523/Y9dQ-hawmauc3882282.jpg', '152714385569940');
INSERT INTO `tk_plan_photo` VALUES ('152714386192864', 'http://n.sinaimg.cn/games/175/w105h70/20180523/Ey-M-hawmauc3875526.jpg', '152714386192842');
INSERT INTO `tk_plan_photo` VALUES ('152714398445382', 'http://i3.sinaimg.cn/home/2013/0331/U586P30DT20130331093840.png', '152714398445327');
INSERT INTO `tk_plan_photo` VALUES ('152714398448936', 'http://n.sinaimg.cn/games/175/w105h70/20180518/5X4Z-harvfhv2301524.jpg', '152714398445327');
INSERT INTO `tk_plan_photo` VALUES ('152714399706151', 'http://n.sinaimg.cn/www/transform/128/w473h455/20180424/yH8g-fzqvvsa3369708.jpg', '152714399706190');
INSERT INTO `tk_plan_photo` VALUES ('152714438133310', 'http://n.sinaimg.cn/news/transform/198/w550h448/20180523/Jh9o-hawmauc3179209.jpg', '152714438133338');
INSERT INTO `tk_plan_photo` VALUES ('152714438133470', 'http://n.sinaimg.cn/translate/116/w550h366/20180523/7FZy-hawmauc2508437.jpg', '152714438133338');
INSERT INTO `tk_plan_photo` VALUES ('152714445766685', 'http://n.sinaimg.cn/news/crawl/699/w400h299/20180523/IW4Y-fzrwiaz5764883.jpg', '152714445766670');
INSERT INTO `tk_plan_photo` VALUES ('152714445766870', 'http://n.sinaimg.cn/news/transform/20170309/dB0p-fychhuq3368141.jpg', '152714445766670');
INSERT INTO `tk_plan_photo` VALUES ('152714463224181', 'http://src.leju.com/imp/imp/deal/c6/3b/f/b9ffb85edeaf409c148298c81ee_p24_mk24.jpg', '152714463223977');
INSERT INTO `tk_plan_photo` VALUES ('152714492737350', 'http://n.sinaimg.cn/www/transform/128/w473h455/20180424/yH8g-fzqvvsa3369708.jpg', '152714492737379');
INSERT INTO `tk_plan_photo` VALUES ('152714492740869', 'http://n.sinaimg.cn/finance/stt/pc/0427_logo.png', '152714492737379');
INSERT INTO `tk_plan_photo` VALUES ('152714514246722', 'http://n.sinaimg.cn/www/transform/20161108/QGkw-fxxneue1058740.png', '152714514246697');
INSERT INTO `tk_plan_photo` VALUES ('152714515958021', 'http://n.sinaimg.cn/translate/404/w500h704/20180523/qP82-hawmauc2189108.jpg', '152714515958052');
INSERT INTO `tk_plan_photo` VALUES ('152714831001243', 'http://n.sinaimg.cn/www/20161001/x6Co-fxwkzyh4046096.gif', '152714831001210');
INSERT INTO `tk_plan_photo` VALUES ('152714843967548', 'http://n.sinaimg.cn/auto/transform/265/w171h94/20180517/qQ9v-harvfhu4643180.jpg', '152714843967579');
INSERT INTO `tk_plan_photo` VALUES ('152714883442674', 'http://www.sinaimg.cn/home/main/blk/d.gif', '152714883442646');
INSERT INTO `tk_plan_photo` VALUES ('152714890704146', 'http://n.sinaimg.cn/homepage/paizhao/b4.jpg', '152714890704165');
INSERT INTO `tk_plan_photo` VALUES ('152714891062777', 'http://n.sinaimg.cn/auto/transform/350/w210h140/20180523/B4PT-hawmauc2487610.jpg', '152714891062783');
INSERT INTO `tk_plan_photo` VALUES ('152714891585120', 'http://n.sinaimg.cn/games/82f31f45/20180516/105.jpg', '152714891585158');
INSERT INTO `tk_plan_photo` VALUES ('152714922724216', 'http://192.168.0.152:8080/tkSystem/static/img/photo/152714922717038', '152714922724216');
INSERT INTO `tk_plan_photo` VALUES ('152714969332877', 'http://192.168.0.152:8080/tkSystem/static/img/photo/152714969323913', '152714969332813');
INSERT INTO `tk_plan_photo` VALUES ('152715294822458', 'http://192.168.0.152:8080/tkSystem/static/img/photo/152715294817173', '152715294822466');

-- ----------------------------
-- Table structure for `tk_power`
-- ----------------------------
DROP TABLE IF EXISTS `tk_power`;
CREATE TABLE `tk_power` (
  ` tk_power_id` varchar(255) NOT NULL COMMENT '权限ｉｄ',
  `tk_user_id` varchar(255) DEFAULT NULL COMMENT '用户id',
  `tk_function_id` varchar(255) DEFAULT NULL COMMENT '功能id',
  PRIMARY KEY (` tk_power_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tk_power
-- ----------------------------
INSERT INTO `tk_power` VALUES ('1', '1', '1');

-- ----------------------------
-- Table structure for `tk_punchcard`
-- ----------------------------
DROP TABLE IF EXISTS `tk_punchcard`;
CREATE TABLE `tk_punchcard` (
  `tk_punchCard_id` varchar(255) NOT NULL COMMENT '唯一标示',
  `tk_punchCard_time` varchar(255) DEFAULT NULL COMMENT '打卡时间',
  `tk_punchCard_person` varchar(255) DEFAULT NULL COMMENT '打卡人',
  `tk_punchCard_loc` varchar(255) DEFAULT NULL COMMENT '位置坐标信息',
  `tk_punchCard_photo` varchar(255) DEFAULT NULL COMMENT '工作照片',
  `tk_plan_id` varchar(255) NOT NULL COMMENT '任务ID',
  PRIMARY KEY (`tk_punchCard_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tk_punchcard
-- ----------------------------
INSERT INTO `tk_punchcard` VALUES ('098c2de3dfe242088b836427e8d19c95', '2018-04-28 10:16:13', '152205226151757', '商业大街', '0.PNG', '152523051620962');
INSERT INTO `tk_punchcard` VALUES ('13d5112ea15440bda64f6e1cbb299241', '2018-04-28 10:01:52', '152205226151757', '商业大街', null, '152523051620962');
INSERT INTO `tk_punchcard` VALUES ('152488652706148', '2018-04-28 11:35:27', '152205226151757', '123', null, '152523051620962');
INSERT INTO `tk_punchcard` VALUES ('152488666038622', '2018-04-28 11:37:40', '152205226151757', '123', null, '152523051620962');
INSERT INTO `tk_punchcard` VALUES ('152488763629562', '2018-04-28 11:53:56', '152205226151757', '123', null, '152523051620962');
INSERT INTO `tk_punchcard` VALUES ('152488775143382', '2018-04-28 11:55:51', '152205226151757', '123', 'http://192.168.0.152:8080/tkSystem//static/img/tkPunchCardPhoto/152488775143382/152488775143382.PNG', '152523051620962');
INSERT INTO `tk_punchcard` VALUES ('152489910884723', '2018-04-28 15:05:08', '152205226151757', '123', 'http://192.168.0.152:8080/tkSystem//static/img/tkPunchCardPhoto/152489910884723/152489910884723.PNG', '152523051620962');
INSERT INTO `tk_punchcard` VALUES ('152533669823699', '2018-05-03 16:38:18', '152205226151757', '123', 'http://192.168.0.152:8080/tkSystem//static/img/tkPunchCardPhoto/152533669823699/152533669823699.PNG', '152523051620962');
INSERT INTO `tk_punchcard` VALUES ('152533689342055', '2018-05-03 16:41:33', '152205226151757', '123', 'http://192.168.0.152:8080/tkSystem//static/img/tkPunchCardPhoto/152533689342055/152533689342055.PNG', '152523051620962');
INSERT INTO `tk_punchcard` VALUES ('152566063806191', '2017-03-07 10:37:18', '152205226151757', '123', 'http://192.168.0.152:8080/tkSystem//static/img/tkPunchCardPhoto/152566063806191/152566063806191.PNG', '152523051620962');
INSERT INTO `tk_punchcard` VALUES ('152594307321277', '2018-05-10 17:04:33', null, '123', 'http://192.168.0.152:8080/tkSystem//static/img/tkPunchCardPhoto/152594307321277/C:\\tomcat\\wtpwebapps\\tkSystem\\static\\img\\tkPunchCardPhoto\\152594307321277\\152594307321277\\152594307321277.PNG', '123');
INSERT INTO `tk_punchcard` VALUES ('152594394164792', '2018-05-10 17:19:01', null, '123', '123,', '123');
INSERT INTO `tk_punchcard` VALUES ('152594408209061', '2018-05-10 17:21:22', null, '123', '123,', '123');
INSERT INTO `tk_punchcard` VALUES ('152594702788867', '2018-05-10 18:10:27', null, '广东省东莞市鸿福路99号', 'undefined,', '152523052743992');
INSERT INTO `tk_punchcard` VALUES ('152600046969190', '2018-05-11 09:01:09', null, '123', '123,', '123');
INSERT INTO `tk_punchcard` VALUES ('152600088066086', '2018-05-11 09:08:00', null, '广东省东莞市鸿福路99号', 'http://192.168.0.152:8080/tkSystem/static/img/photo/152600088049638.PNG,http://192.168.0.152:8080/tkSystem/static/img/photo/152600088059982.PNG,', '152523051620962');
INSERT INTO `tk_punchcard` VALUES ('152600109699933', '2018-05-11 09:11:37', null, '广东省东莞市鸿福路99号', 'http://192.168.0.152:8080/tkSystem/static/img/photo/152600109635554.PNG,http://192.168.0.152:8080/tkSystem/static/img/photo/152600109677140.PNG,', '152523074195157');
INSERT INTO `tk_punchcard` VALUES ('152600119476122', '2018-05-11 09:13:14', null, '广东省东莞市胜和路', ',', '152583620991851');
INSERT INTO `tk_punchcard` VALUES ('152600119828120', '2018-05-11 09:13:18', null, '广东省东莞市胜和路', ',', '152583620991851');
INSERT INTO `tk_punchcard` VALUES ('152600120307764', '2018-05-11 09:13:23', null, '广东省东莞市胜和路', 'http://192.168.0.152:8080/tkSystem/static/img/photo/152600120300653.PNG,', '152583620991851');
INSERT INTO `tk_punchcard` VALUES ('152600121013887', '2018-05-11 09:13:30', null, '广东省东莞市胜和路', 'http://192.168.0.152:8080/tkSystem/static/img/photo/152600120300653.PNG,http://192.168.0.152:8080/tkSystem/static/img/photo/152600121007031.PNG,', '152583620991851');
INSERT INTO `tk_punchcard` VALUES ('152602862358512', '2018-05-11 16:50:23', null, '广东省东莞市富民商业步行街B73号4层', 'TkPunchCardPhoto', '152602666778353');
INSERT INTO `tk_punchcard` VALUES ('152602976465656', '2018-05-11 17:09:24', null, '广东省东莞市鸿福路99号', 'TkPunchCardPhoto', '152523051620962');
INSERT INTO `tk_punchcard` VALUES ('152603033709836', '2018-05-11 17:18:57', null, '广东省东莞市鸿福路99号', 'TkPunchCardPhoto', '152602968186264');
INSERT INTO `tk_punchcard` VALUES ('152603148976874', '2018-05-11 17:38:09', null, '广东省东莞市鸿福路99号', 'TkPunchCardPhoto', '152603118855240');
INSERT INTO `tk_punchcard` VALUES ('152689135111388', '2018-05-21 16:29:11', null, '广东省东莞市鸿福路99号', 'TkPunchCardPhoto', '152603118855240');
INSERT INTO `tk_punchcard` VALUES ('152714916831534', '2018-05-24 16:06:08', null, '东莞市市辖区鸿福路9', 'TkPunchCardPhoto', '152603118855240');
INSERT INTO `tk_punchcard` VALUES ('152715276952140', '2018-05-24 17:06:09', null, '东莞市政府内,东莞市发展和改革局附近13米', 'TkPunchCardPhoto', '152603118855240');
INSERT INTO `tk_punchcard` VALUES ('23617aed77e84ea681723466f099fa59', '2018-04-28 10:08:19', '152205226151757', '商业大街', null, '152523051620962');
INSERT INTO `tk_punchcard` VALUES ('a17756a7f0274bb1aeb01aa21e783108', '2018-04-28 10:18:11', '152205226151757', '商业大街', '0.PNG', '152523051620962');
INSERT INTO `tk_punchcard` VALUES ('a734ccefb6d34f0fa78633ad8379b74e', '2018-04-28 10:14:38', '152205226151757', '商业大街', '0.PNG', '152523051620962');
INSERT INTO `tk_punchcard` VALUES ('b228fd6660e3459eb86d3c9f74688167', '2018-04-28 10:07:31', '152205226151757', '商业大街', null, '152523051620962');
INSERT INTO `tk_punchcard` VALUES ('eac326efdc0d41e086049c8d698fb991', '2018-04-28 10:11:34', '152205226151757', '商业大街', null, '152523051620962');

-- ----------------------------
-- Table structure for `tk_punchcard_feedback`
-- ----------------------------
DROP TABLE IF EXISTS `tk_punchcard_feedback`;
CREATE TABLE `tk_punchcard_feedback` (
  `tk_punchcard_feedback_id` varchar(255) NOT NULL COMMENT '打卡迟到反馈ID，唯一标识',
  `tk_punchcard_feedback_user_id` varchar(255) DEFAULT NULL COMMENT '打卡人ID',
  `tk_punchcard_feedback_time` varchar(255) DEFAULT NULL COMMENT '打卡时间',
  `tk_punchcard_feedback_lateTime` varchar(255) DEFAULT NULL COMMENT '迟到时间',
  `tk_punchcard_feedback_managerId` varchar(255) DEFAULT NULL COMMENT '上级ID',
  `tk_punchcard_feedback_plan_id` varchar(255) DEFAULT NULL COMMENT '打卡任务的ID',
  PRIMARY KEY (`tk_punchcard_feedback_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tk_punchcard_feedback
-- ----------------------------
INSERT INTO `tk_punchcard_feedback` VALUES ('152488652706148', '152205226151757', null, null, null, '152523051620962');
INSERT INTO `tk_punchcard_feedback` VALUES ('152488666038622', '152205226151757', null, null, null, '152523051620962');
INSERT INTO `tk_punchcard_feedback` VALUES ('152488763629562', '152205226151757', null, null, null, '152523051620962');
INSERT INTO `tk_punchcard_feedback` VALUES ('152488775143382', '152205226151757', null, null, null, '152523051620962');
INSERT INTO `tk_punchcard_feedback` VALUES ('152489910884723', '152205226151757', null, null, null, '152523051620962');
INSERT INTO `tk_punchcard_feedback` VALUES ('152533669823699', '152205226151757', null, null, null, '152523051620962');
INSERT INTO `tk_punchcard_feedback` VALUES ('152533689342055', '152205226151757', null, null, null, '152523051620962');
INSERT INTO `tk_punchcard_feedback` VALUES ('152566063806191', '152205226151757', null, null, null, '123');
INSERT INTO `tk_punchcard_feedback` VALUES ('152594307321277', '152205226151757', null, null, null, '123');
INSERT INTO `tk_punchcard_feedback` VALUES ('152594394164792', '152205226151757', null, null, null, '123');
INSERT INTO `tk_punchcard_feedback` VALUES ('152594408209061', '152205226151757', null, null, null, '123');
INSERT INTO `tk_punchcard_feedback` VALUES ('152594702788867', '152205226151757', null, null, null, '152523052743992');
INSERT INTO `tk_punchcard_feedback` VALUES ('152600046969190', '152205226151757', null, null, null, '123');
INSERT INTO `tk_punchcard_feedback` VALUES ('152600088066086', '152205226151757', null, null, null, '152523051620962');
INSERT INTO `tk_punchcard_feedback` VALUES ('152600109699933', '152205226151757', null, null, null, '152523074195157');
INSERT INTO `tk_punchcard_feedback` VALUES ('152600119476122', '152205226151757', null, null, null, '152583620991851');
INSERT INTO `tk_punchcard_feedback` VALUES ('152600119828120', '152205226151757', null, null, null, '152583620991851');
INSERT INTO `tk_punchcard_feedback` VALUES ('152600120307764', '152205226151757', null, null, null, '152583620991851');
INSERT INTO `tk_punchcard_feedback` VALUES ('152600121013887', '152205226151757', null, null, null, '152583620991851');
INSERT INTO `tk_punchcard_feedback` VALUES ('152602862358512', '152205226151757', null, null, null, '152602666778353');
INSERT INTO `tk_punchcard_feedback` VALUES ('152602976465656', '152205226151757', null, null, null, '152523051620962');
INSERT INTO `tk_punchcard_feedback` VALUES ('152603033709836', '152205226151757', null, null, null, '152602968186264');
INSERT INTO `tk_punchcard_feedback` VALUES ('152603148976874', '152205226151757', null, null, null, '152603118855240');
INSERT INTO `tk_punchcard_feedback` VALUES ('152689135111388', '152205226151757', null, null, null, '152603118855240');
INSERT INTO `tk_punchcard_feedback` VALUES ('152714916831534', '152205226151757', null, null, null, '152603118855240');
INSERT INTO `tk_punchcard_feedback` VALUES ('152715276952140', '152205226151757', null, null, null, '152603118855240');

-- ----------------------------
-- Table structure for `tk_punchcard_photo`
-- ----------------------------
DROP TABLE IF EXISTS `tk_punchcard_photo`;
CREATE TABLE `tk_punchcard_photo` (
  `tk_punchcard_photo_id` varchar(255) NOT NULL COMMENT '打卡信息图片编号',
  `tk_punchcard_photo_url` varchar(255) NOT NULL COMMENT 'url',
  `tk_punchcard_id` varchar(255) NOT NULL COMMENT '打卡信息编号',
  PRIMARY KEY (`tk_punchcard_photo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tk_punchcard_photo
-- ----------------------------
INSERT INTO `tk_punchcard_photo` VALUES ('152594394164732', 'http://src.leju.com/imp/imp/deal/c6/3b/f/b9ffb85edeaf409c148298c81ee_p24_mk24.jpg', '152594394164792');
INSERT INTO `tk_punchcard_photo` VALUES ('152594408209176', 'http://n.sinaimg.cn/news/crawl/699/w400h299/20180523/IW4Y-fzrwiaz5764883.jpg', '152594408209061');
INSERT INTO `tk_punchcard_photo` VALUES ('152594702788867', 'http://n.sinaimg.cn/news/transform/579/w340h239/20180523/cQS4-hawmauc2819766.jpg', '152594702788867');
INSERT INTO `tk_punchcard_photo` VALUES ('152600046969157', 'http://n.sinaimg.cn/auto/transform/350/w210h140/20180522/s_m9-fzrwiaz5729655.jpg', '152600046969190');
INSERT INTO `tk_punchcard_photo` VALUES ('152600046973631', 'http://src.house.sina.com.cn/imp/imp/deal/19/c4/f/b492725c5c376b1597df63bd726_p24_mk24.jpg', '152600046969190');
INSERT INTO `tk_punchcard_photo` VALUES ('152600088066050', 'http://i3.sinaimg.cn/home/deco/2009/0330/logo_home.gif', '152600088066086');
INSERT INTO `tk_punchcard_photo` VALUES ('152600109700055', 'http://n.sinaimg.cn/news/175/w105h70/20180523/eNwG-hawmauc2691378.jpg', '152600109699933');
INSERT INTO `tk_punchcard_photo` VALUES ('152600119476123', 'http://n.sinaimg.cn/games/82f31f45/20180516/105.jpg', '152600119476122');
INSERT INTO `tk_punchcard_photo` VALUES ('152600119828115', 'http://i11.esfimg.com/imp/58e2b693c2c0032baabd8303a0e2b14f_os992318.jpg', '152600119828120');
INSERT INTO `tk_punchcard_photo` VALUES ('152600120307716', 'http://www.sina.com.cn/licence/4-1.jpg', '152600120307764');
INSERT INTO `tk_punchcard_photo` VALUES ('152600121013891', 'http://n.sinaimg.cn/sports/transform/274/w164h110/20180522/Z9Cm-hawmaua6269312.jpg', '152600121013887');
INSERT INTO `tk_punchcard_photo` VALUES ('152602862358542', 'http://n.sinaimg.cn/www/transform/20161108/QGkw-fxxneue1058740.png', '152602862358512');
INSERT INTO `tk_punchcard_photo` VALUES ('152602976465669', 'http://n.sinaimg.cn/sports/transform/274/w164h110/20180522/Z9Cm-hawmaua6269312.jpg', '152602976465656');
INSERT INTO `tk_punchcard_photo` VALUES ('152603033709847', 'http://n.sinaimg.cn/kandian/transform/20170620/_PTI-fyhfxph4388828.jpg', '152603033709836');
INSERT INTO `tk_punchcard_photo` VALUES ('152603148976874', 'http://n.sinaimg.cn/auto/transform/265/w171h94/20180522/HGTd-fzrwiaz5729883.jpg', '152603148976874');
INSERT INTO `tk_punchcard_photo` VALUES ('152689135111379', 'http://www.sina.com.cn/licence/4-1.jpg', '152689135111388');
INSERT INTO `tk_punchcard_photo` VALUES ('152714916831665', 'http://192.168.0.152:8080/tkSystem/static/img/photo/152714916806425.PNG', '152714916831534');
INSERT INTO `tk_punchcard_photo` VALUES ('152715276952197', 'http://192.168.0.152:8080/tkSystem/static/img/photo/152715276946820.png', '152715276952140');

-- ----------------------------
-- Table structure for `tk_target_report`
-- ----------------------------
DROP TABLE IF EXISTS `tk_target_report`;
CREATE TABLE `tk_target_report` (
  `tk_target_report_id` varchar(255) NOT NULL COMMENT '任务报备id',
  `tk_target_report_name` varchar(255) DEFAULT NULL COMMENT '报备名称',
  `tk_target_report_time` varchar(255) DEFAULT NULL COMMENT '报备时间',
  `tk_user_id` varchar(255) DEFAULT NULL COMMENT '用户id',
  `tk_target_reuser_id` varchar(255) DEFAULT NULL,
  `tk_target_report_remark` varchar(255) DEFAULT NULL,
  `tk_plan_id` varchar(255) DEFAULT NULL COMMENT '任务id',
  `tk_report_state` varchar(255) NOT NULL DEFAULT '-1' COMMENT '报备状态0拒绝1通过',
  PRIMARY KEY (`tk_target_report_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tk_target_report
-- ----------------------------
INSERT INTO `tk_target_report` VALUES ('152663320730964', '嗷嗷嗷的报备', '2018-05-18 16:46:47', '152205226151757', '152205226151757', '天气炎热', '152603118855240', '-1');
INSERT INTO `tk_target_report` VALUES ('152695413730985', '嗷嗷嗷的报备', '2018-05-22 09:55:37', '152205226151757', '152205226151757', '天气炎热', '152603118855240', '1');
INSERT INTO `tk_target_report` VALUES ('152695425651063', '嗷嗷嗷的报备', '2018-05-18 16:46:47', '152205226151757', '152205226151757', 'TTTTT', '152603118855240', '-1');
INSERT INTO `tk_target_report` VALUES ('152715449997498', '嗷嗷嗷的报备', '2018-05-24 17:34:59', '152205226151757', '152205226151757', 'FFFF', '152603118855240', '-1');

-- ----------------------------
-- Table structure for `tk_user`
-- ----------------------------
DROP TABLE IF EXISTS `tk_user`;
CREATE TABLE `tk_user` (
  `tk_user_id` varchar(255) NOT NULL COMMENT '用户表唯一标识',
  `tk_user_name` varchar(255) DEFAULT NULL COMMENT '用户名(登录名)',
  `tk_user_type` varchar(255) DEFAULT NULL COMMENT '用户类型(如管理员，拓客人员)的唯一标识',
  `tk_user_password` varchar(255) DEFAULT NULL COMMENT '用户密码',
  `tk_user_time` varchar(255) DEFAULT NULL COMMENT '入列时间',
  `tk_user_token` varchar(255) DEFAULT NULL COMMENT 'token',
  `tk_user_login_ip` varchar(255) DEFAULT NULL COMMENT '登录ip',
  `tk_user_head` varchar(255) DEFAULT NULL,
  `tk_user_phone` varchar(255) DEFAULT NULL,
  `tk_client_amount` varchar(255) DEFAULT NULL COMMENT '客户人数',
  `openid` varchar(255) DEFAULT NULL COMMENT '微信用户登陆id',
  `tk_user_type_id` varchar(255) NOT NULL DEFAULT '0' COMMENT '用户类型编号默认0拓客人员或普通用户，1超级管理员2一级经理',
  PRIMARY KEY (`tk_user_id`),
  UNIQUE KEY `tk_user_name` (`tk_user_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tk_user
-- ----------------------------
INSERT INTO `tk_user` VALUES ('152205226151755', '阿中', '3', '250cf8b51c773f3f8dc8b4be867a9a02', '2018-04-28 16:21:53', 'ef5a66c9507b47dc9c20b15e9fcc2dcd', '192.168.0.152', 'http://n.sinaimg.cn/www/transform/797/w455h342/20180416/JaPH-fzcyxmv4148055.jpg', '13377786910', '11', '', '0');
INSERT INTO `tk_user` VALUES ('152205226151756', '阿方', '1', '250cf8b51c773f3f8dc8b4be867a9a02', '2018-04-28 16:21:53', 'ef5a66c9507b47dc9c20b15e9fcc2dcd', '192.168.0.152', 'http://n.sinaimg.cn/sports/transform/274/w164h110/20180522/Z9Cm-hawmaua6269312.jpg', '13377786910', '12', '', '1');
INSERT INTO `tk_user` VALUES ('152205226151757', '任俊忠', '2', '250cf8b51c773f3f8dc8b4be867a9a02', '2018-04-28 16:21:53', '36c77990c8584246856dd7b56e0bb8ea', '192.168.0.151', 'http://n.sinaimg.cn/mil/transform/300/w550h550/20180523/tRNV-hawmauc3117190.jpg', '13377786910', '1', '', '2');
INSERT INTO `tk_user` VALUES ('152205226151758', '阿林', '3', '250cf8b51c773f3f8dc8b4be867a9a02', '2018-04-28 16:21:53', 'b0418a9e6ed34ef2a2348f6b6e7d6de5', '192.168.0.152', 'http://n.sinaimg.cn/kandian/transform/20170620/_PTI-fyhfxph4388828.jpg', '13377786910', '11', '', '3');
INSERT INTO `tk_user` VALUES ('152551075197418', '阿林啊', null, 'd41d8cd98f00b204e9800998ecf8427e', '2018-05-18 10:37:32', 'd8c029d8cb0c4ee2bf4a347d034e8cc3', '192.168.0.168', 'http://i2.sinaimg.cn/dy/deco/2009/0330/lx01.jpg', '13377786910', '10', '152205226151757', '2');
INSERT INTO `tk_user` VALUES ('152592084321822', null, null, 'd41d8cd98f00b204e9800998ecf8427e', '2018-05-24 17:36:22', '6df690d5aaa54ad18e5df16c92307411', '192.168.0.111', 'http://n.sinaimg.cn/news/transform/175/w105h70/20180523/jQND-hawmauc3723710.jpg', '13113687500', null, 'omP-J5c-UfgtR_DkDGPWoKoo3ZfI', '2');
INSERT INTO `tk_user` VALUES ('152626428794591', null, null, 'd41d8cd98f00b204e9800998ecf8427e', '2018-05-15 16:48:04', '0b23aebbe9d241d9b871928a34c1cdb9', '192.168.0.152', 'http://n.sinaimg.cn/blog/175/w105h70/20180521/zOxH-hawmaua0127397.jpg', null, null, 'undefined', '2');
INSERT INTO `tk_user` VALUES ('152723223883199', '刘勇', null, 'd41d8cd98f00b204e9800998ecf8427e', '2018-05-25 16:43:02', '5d3ea74432cb46f7a99f0336d3811094', '192.168.0.151', 'https://wx.qlogo.cn/mmopen/vi_32/W7z4uB2e2Z4nhu21f45CLJWdDQibZqkd2mL93dgx3NkzKtsCjHjU7Xib6iaTRlNycHa9pgeC3GxWku4k7RVficTPZg/132', '17620588879', null, 'omP-J5Q-dS9eGHvKo9Q9-gbTv0Xg', '0');

-- ----------------------------
-- Table structure for `tk_user_grade`
-- ----------------------------
DROP TABLE IF EXISTS `tk_user_grade`;
CREATE TABLE `tk_user_grade` (
  `tk_user_grade_id` varchar(255) NOT NULL COMMENT '用户组id',
  `tk_user_grade_name` varchar(255) DEFAULT NULL COMMENT '用户类型名称',
  `tk_user_grade_time` varchar(255) DEFAULT NULL COMMENT '用户类型名称',
  `tk_user_grade_pid` varchar(255) DEFAULT NULL COMMENT '父ｉｄ',
  `tk_user_grade_gnid` varchar(255) DEFAULT NULL COMMENT '功能id',
  `tk_user_state` varchar(255) DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`tk_user_grade_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tk_user_grade
-- ----------------------------
INSERT INTO `tk_user_grade` VALUES ('152205226151755', null, null, '152205226151757', null, '0');
INSERT INTO `tk_user_grade` VALUES ('152205226151756', '9', '9', '152205226151757', '9', '2');
INSERT INTO `tk_user_grade` VALUES ('152205226151757', '2', '2', '152205226151757', '2', '-1');
INSERT INTO `tk_user_grade` VALUES ('152205226151758', '3', '3', '152205226151757', '3', '2');
INSERT INTO `tk_user_grade` VALUES ('4', '4', '4', '4', '4', '2');
INSERT INTO `tk_user_grade` VALUES ('5', '5', '5', '5', '5', '2');
INSERT INTO `tk_user_grade` VALUES ('6', '6', '6', '6', '6', '2');
INSERT INTO `tk_user_grade` VALUES ('7', '7', '7', '7', '7', '2');
INSERT INTO `tk_user_grade` VALUES ('8', '8', '8', '8', '8', '2');

-- ----------------------------
-- Table structure for `tk_user_type`
-- ----------------------------
DROP TABLE IF EXISTS `tk_user_type`;
CREATE TABLE `tk_user_type` (
  `tk_user_type_id` varchar(255) NOT NULL COMMENT '类型id',
  `tk_user_type_name` varchar(255) NOT NULL COMMENT '类型名称'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户类型表';

-- ----------------------------
-- Records of tk_user_type
-- ----------------------------
INSERT INTO `tk_user_type` VALUES ('1', '超级管理员');
INSERT INTO `tk_user_type` VALUES ('2', '一级经理');
INSERT INTO `tk_user_type` VALUES ('3', '二级经理');
INSERT INTO `tk_user_type` VALUES ('4', '三级经理');

-- ----------------------------
-- Table structure for `wechat_user`
-- ----------------------------
DROP TABLE IF EXISTS `wechat_user`;
CREATE TABLE `wechat_user` (
  `openid` varchar(255) NOT NULL COMMENT 'id',
  `nick_name` varchar(255) NOT NULL COMMENT '用户名',
  `avatar_url` varchar(255) NOT NULL COMMENT '微信头像',
  PRIMARY KEY (`openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wechat_user
-- ----------------------------
INSERT INTO `wechat_user` VALUES ('152205226151757', '152205226151758', '36c77990c8584246856dd7b56e0bb8ea');
INSERT INTO `wechat_user` VALUES ('omP-J5c-UfgtR_DkDGPWoKoo3ZfI', '布衣~央.', 'https://wx.qlogo.cn/mmopen/vi_32/ZrPfqw4AbHdJEzFe9R4ibicIBFuzRv56s7LWnSM3Byj3uRoe2blxqURkoibSAhp60PhzTljgMMFZHapOe4NnAKIcw/132');
INSERT INTO `wechat_user` VALUES ('omP-J5Q-dS9eGHvKo9Q9-gbTv0Xg', '前方', 'https://wx.qlogo.cn/mmopen/vi_32/W7z4uB2e2Z4nhu21f45CLJWdDQibZqkd2mL93dgx3NkzKtsCjHjU7Xib6iaTRlNycHa9pgeC3GxWku4k7RVficTPZg/132');
INSERT INTO `wechat_user` VALUES ('undefined', '纯洁的微笑', 'https://wx.qlogo.cn/mmopen/vi_32/8ricsRPe7lSwcNMOmc2mls2g9ld7ic2BuO5F8MewcBxtBbCiclrdSNf1svcm79mAInU5dibBicZWLia0qeNQH3X7hicTQ/132');
