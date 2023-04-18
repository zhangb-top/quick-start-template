/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : internet_competition

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 18/04/2023 16:46:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_question
-- ----------------------------
DROP TABLE IF EXISTS `tb_question`;
CREATE TABLE `tb_question`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) NOT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `answered` int(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_user`(`userID`) USING BTREE,
  CONSTRAINT `fk_user` FOREIGN KEY (`userID`) REFERENCES `tb_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_question
-- ----------------------------
INSERT INTO `tb_question` VALUES (3, 14, '我想去杭州旅游三天两夜，预算2000左右，请问有什么好的安排吗？', 1);
INSERT INTO `tb_question` VALUES (4, 14, '我想去上海旅游三天两夜，预算2000左右，请问有什么好的安排吗？', 1);
INSERT INTO `tb_question` VALUES (5, 14, '我想去北京旅游三天两夜，预算2000左右，请问有什么好的安排吗？', 1);
INSERT INTO `tb_question` VALUES (6, 14, '我想去山东旅游三天两夜，预算2000左右，请问有什么好的安排吗？', 0);
INSERT INTO `tb_question` VALUES (7, 14, '我想去南京旅游三天两夜，预算2000左右，请问有什么好的安排吗？', 0);
INSERT INTO `tb_question` VALUES (8, 14, '我想去三亚旅游三天两夜，预算2000左右，请问有什么好的安排吗？', 0);
INSERT INTO `tb_question` VALUES (9, 14, '我想去新疆旅游三天两夜，预算2000左右，请问有什么好的安排吗？', 0);
INSERT INTO `tb_question` VALUES (10, 14, '我想去天津旅游三天两夜，预算2000左右，请问有什么好的安排吗？', 0);
INSERT INTO `tb_question` VALUES (11, 14, '我想去乌鲁木齐旅游三天两夜，预算2000左右，请问有什么好的安排吗？', 0);
INSERT INTO `tb_question` VALUES (12, 17, '我想去苏州游玩，有什么建议吗', 0);
INSERT INTO `tb_question` VALUES (13, 19, '给我一份去苏州旅游的计划安排', 1);
INSERT INTO `tb_question` VALUES (14, 14, '我想去杭州旅游三天两夜，预算2000左右，请问有什么好的安排吗？', 0);
INSERT INTO `tb_question` VALUES (15, 14, '我想去杭州旅游三天两夜，预算2000左右，请问有什么好的安排吗？', 0);
INSERT INTO `tb_question` VALUES (16, 14, '我想去杭州旅游三天两夜，预算2000左右，请问有什么好的安排吗？', 0);
INSERT INTO `tb_question` VALUES (17, 14, '我想去杭州旅游三天两夜，预算2000左右，请问有什么好的安排吗？', 0);
INSERT INTO `tb_question` VALUES (18, 14, '我想去杭州旅游三天两夜，预算2000左右，请问有什么好的安排吗？', 0);

-- ----------------------------
-- Table structure for tb_travel
-- ----------------------------
DROP TABLE IF EXISTS `tb_travel`;
CREATE TABLE `tb_travel`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `theme` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `location` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `itinerary` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `hotel` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `budget` decimal(10, 2) NOT NULL COMMENT '预算',
  `questionID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_question`(`questionID`) USING BTREE,
  CONSTRAINT `fk_question` FOREIGN KEY (`questionID`) REFERENCES `tb_question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_travel
-- ----------------------------
INSERT INTO `tb_travel` VALUES (2, '古镇游', '中国杭州西湖', '第一天：抵达杭州，入住酒店；第二天：游览西湖；第三天：游览乌镇', '杭州西湖凯悦酒店', 20000.00, NULL);
INSERT INTO `tb_travel` VALUES (3, '自然探险', '美国黄石国家公园', '第一天：抵达黄石国家公园，入住酒店；第二天：游览黄石大峡谷', '黄石国家公园大峡谷旅馆', 20000.00, NULL);
INSERT INTO `tb_travel` VALUES (4, '文化之旅', '意大利罗马', '第一天：抵达罗马，入住酒店；第二天：游览梵蒂冈博物馆', '罗马希尔顿酒店', 20000.00, NULL);
INSERT INTO `tb_travel` VALUES (5, '浪漫之旅', '法国巴黎', '第一天：抵达巴黎，入住酒店；第二天：游览埃菲尔铁塔', '巴黎香榭丽舍大酒店', 20000.00, NULL);
INSERT INTO `tb_travel` VALUES (6, '城市观光', '日本东京', '第一天：抵达东京，入住酒店；第二天：游览东京塔', '东京帝国酒店', 20000.00, NULL);
INSERT INTO `tb_travel` VALUES (7, '高山滑雪', '瑞士阿尔卑斯山', '第一天：抵达阿尔卑斯山，入住酒店；第二天：滑雪', '阿尔卑斯山滑雪度假村', 20000.00, NULL);
INSERT INTO `tb_travel` VALUES (8, '热带雨林之旅', '印度尼西亚巴厘岛', '第一天：抵达巴厘岛，入住酒店；第二天：游览热带雨林', '巴厘岛翡翠度假酒店', 20000.00, NULL);
INSERT INTO `tb_travel` VALUES (9, '沙漠徒步', '摩洛哥撒哈拉沙漠', '第一天：抵达撒哈拉沙漠，入住帐篷；第二天：徒步穿越沙漠', '撒哈拉沙漠帐篷营地', 20000.00, NULL);
INSERT INTO `tb_travel` VALUES (10, '风情小镇', '西班牙托莱多', '第一天：抵达托莱多，入住酒店；第二天：游览托莱多老城区', '托莱多希尔顿酒店', 20000.00, NULL);
INSERT INTO `tb_travel` VALUES (11, '海滨度假', '美国加州圣塔芭芭拉', '第一天：抵达圣塔芭芭拉，入住酒店；第二天：游泳海滩', '圣塔芭芭拉希尔顿海滨度假村', 20000.00, NULL);
INSERT INTO `tb_travel` VALUES (12, '古堡游', '英国爱丁堡', '第一天：抵达爱丁堡，入住酒店；第二天：游览爱丁堡城堡', '爱丁堡卡尔顿酒店', 20000.00, NULL);
INSERT INTO `tb_travel` VALUES (13, '美食之旅', '法国里昂', '第一天：抵达里昂，入住酒店；第二天：品尝美食', '里昂希尔顿酒店', 20000.00, NULL);
INSERT INTO `tb_travel` VALUES (14, '草原马术', '蒙古国乌兰巴托', '第一天：抵达乌兰巴托，入住酒店；第二天：骑马穿越草原', '乌兰巴托马术营地', 20000.00, NULL);
INSERT INTO `tb_travel` VALUES (15, '水上活动', '澳大利亚黄金海岸', '第一天：抵达黄金海岸，入住酒店；第二天：水上摩托艇', '黄金海岸希尔顿度假村', 20000.00, NULL);
INSERT INTO `tb_travel` VALUES (16, '森林徒步', '加拿大不列颠哥伦比亚省', '第一天：抵达不列颠哥伦比亚省，入住酒店；第二天：森林徒步', '不列颠哥伦比亚省森林小屋', 20000.00, NULL);
INSERT INTO `tb_travel` VALUES (17, '名城古迹', '中国北京', '第一天：抵达北京，入住酒店；第二天：游览故宫', '北京王府井希尔顿酒店', 20000.00, NULL);
INSERT INTO `tb_travel` VALUES (18, '雪山滑雪', '加拿大温哥华', '第一天：抵达温哥华，入住酒店；第二天：滑雪', '温哥华滑雪度假村', 20000.00, NULL);
INSERT INTO `tb_travel` VALUES (19, '游轮之旅', '美国佛罗里达州', '第一天：抵达佛罗里达州，登上游轮；第二天：游轮出海', '佛罗里达州皇家加勒比游轮', 20000.00, NULL);
INSERT INTO `tb_travel` VALUES (20, '沙滩度假', '泰国普吉岛', '第一天：抵达普吉岛，入住酒店；第二天：沙滩休闲', '普吉岛希尔顿度假酒店', 20000.00, NULL);
INSERT INTO `tb_travel` VALUES (24, '古韵杭州', '西湖、灵隐寺、宋城千古情、南宋御街、龙井村、曲院风荷、吴山天风、雷峰塔等', '<p>第一天： 上午：游西湖，可以选择坐游船或自行租赁自行车，游览“十景”。 中午：在南宋御街附近品尝当地小吃。 下午：游灵隐寺，感受佛教文化。 晚上：去宋城千古情观看演出。 第二天： 上午：去龙井村品尝龙井茶，并游览茶园。 中午：在曲院风荷附近品尝当地小吃。 下午：游吴山天风，欣赏杭州城市全景。 晚上：在杭州市区的餐厅品尝当地美食。 第三天： 上午：游雷峰塔，了解杭州历史文化。 中午：在西湖边的餐厅品尝当地美食。 下午：自由活动，可以去购物或者游览其他景点。 晚上：结束旅游，返回家中。</p>', '推荐杭州市区的链式酒店，如锦江之星、如家快捷酒店等，价格在200元/晚左右', 2000.00, 3);
INSERT INTO `tb_travel` VALUES (30, '上海之旅', '上海', '<h2>test666</h2>', '四季酒店', 2000.00, 4);
INSERT INTO `tb_travel` VALUES (31, '北京之旅', '北京', '<h2>test北京</h2>', '酒店名称未知', 2200.00, 5);
INSERT INTO `tb_travel` VALUES (33, 'test', 'test', '<h2>test123</h2>', 'test', 2000.00, 13);

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `identity` int(11) NULL DEFAULT 2,
  `status` int(1) NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (14, 'promise', 'b991b3269ebb6f416565802935184737d01500b542c628e4402c0523b45e3bd6', 1, 1);
INSERT INTO `tb_user` VALUES (15, 'test666', 'ceb588b4ba47c60042a00cd55646f6c2d30fd224c5013c749a3425b310ed5788', 2, 1);
INSERT INTO `tb_user` VALUES (16, 'user666', 'bef57ec7f53a6d40beb640a780a639c83bc29ac8a9816f1fc6c5c6dcd93c4721', 2, 1);
INSERT INTO `tb_user` VALUES (17, 'test', 'ffec2b04828a94f08d28b1d93bd9702f0e3395930cc3f3593d1182beedc23561', 2, 1);
INSERT INTO `tb_user` VALUES (18, 'test66666', '92925488b28ab12584ac8fcaa8a27a0f497b2c62940c8f4fbc8ef19ebc87c43e', 2, 1);
INSERT INTO `tb_user` VALUES (19, 'abcd', '92925488b28ab12584ac8fcaa8a27a0f497b2c62940c8f4fbc8ef19ebc87c43e', 2, 1);

SET FOREIGN_KEY_CHECKS = 1;
