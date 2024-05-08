/*
 Navicat Premium Data Transfer

 Source Server         : 本地连接
 Source Server Type    : MySQL
 Source Server Version : 80026 (8.0.26)
 Source Host           : localhost:3306
 Source Schema         : tieba

 Target Server Type    : MySQL
 Target Server Version : 80026 (8.0.26)
 File Encoding         : 65001

 Date: 08/05/2024 13:08:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '内容',
  `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作者ID',
  `topic_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'topic_id',
  `create_time` datetime NOT NULL COMMENT '发布时间',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `floor` int NULL DEFAULT NULL COMMENT '楼数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '评论表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1655458825200103426', '<p>7K-8K，要高了，绝对不要你，应聘的人那么多，hr都知道选要工资低的<img src=\"https://tb2.bdstatic.com/tb/editor/images/face/i_f33.png?t=20140803\"/><br/></p>', '1655457625457192961', '1655458758380646402', '2023-05-08 14:25:16', '2023-05-08 14:25:16', 2);
INSERT INTO `comment` VALUES ('1655458937246740481', '<p>往年的话8左右，今年估计6都够呛<br/></p>', '1655451876802850818', '1655458758380646402', '2023-05-08 14:25:42', '2023-05-08 14:25:42', 3);
INSERT INTO `comment` VALUES ('1655459725759115265', '<p>不懂帮顶</p>', '1655444813783658498', '1655459634289733633', '2023-05-08 14:28:50', '2023-05-08 14:28:50', 2);
INSERT INTO `comment` VALUES ('1655534162600685569', '<p>在后端技术领域，C++仍然有其应用价值和前景，注意不断学习和掌握新的技术和工具，以适应不断变化的市场需求和技术趋势。<br/><br/><br/>C++语言是一种高效、功能强大的编程语言，被广泛应用于系统级编程、游戏开发、图形图像处理、网络通信等领域。虽然在一些场景下被其它语言所替代，但对于某些性能要求极高或对内存管理、算法优化有严格要求的场景来说，C++仍然是无可替代的选择。<br/><br/><br/><br/>就后端技术而言，C++语言的应用场景相对较窄，但在需要高并发、高性能、低延迟的场景下仍然有其独特优势，例如金融、游戏、云计算等领域。此外，在很多高性能语言中（如Java和Python），也经常使用C++编写的库来提升程序运行效率。<br/></p>', '1655459176812802050', '1655534088571219969', '2023-05-08 19:24:37', '2023-05-08 19:24:37', 2);
INSERT INTO `comment` VALUES ('1655543531727888386', '<p>怎么办<img src=\"https://gsp0.baidu.com/5aAHeD3nKhI2p27j8IqW0jdnxx1xbK/tb/editor/images/client/image_emoticon9.png\"/><br/></p>', '1655444813783658498', '1655543344632569857', '2023-05-08 20:01:51', '2023-05-08 20:01:51', 2);
INSERT INTO `comment` VALUES ('1655544014114791425', '<p>专业技能里面写几个中间件<br/></p>', '1655444813783658498', '1655543937132535809', '2023-05-08 20:03:46', '2023-05-08 20:03:46', 2);
INSERT INTO `comment` VALUES ('1655544181631098882', '<p>简历虽然写了挺多计算机基础的，不过很少问的，可以多写几个中间件，框架啥的。如果想主打底层，底层深度还差了点<br/></p>', '1655446603392512001', '1655543937132535809', '2023-05-08 20:04:26', '2023-05-08 20:04:26', 3);
INSERT INTO `comment` VALUES ('1655544252133154817', '<p>rpc项目技术栈的lombok可以换一个，有点掉价<br/></p>', '1655446603392512001', '1655543937132535809', '2023-05-08 20:04:43', '2023-05-08 20:04:43', 4);
INSERT INTO `comment` VALUES ('1655546192510459906', '<p>热爱生活<br/></p>', '1655541038260318210', '1655546131059712002', '2023-05-08 20:12:26', '2023-05-08 20:12:26', 2);
INSERT INTO `comment` VALUES ('1655546299272273921', '<p>找到了爱自己的人<br/></p>', '1655444813783658498', '1655546131059712002', '2023-05-08 20:12:51', '2023-05-08 20:12:51', 3);
INSERT INTO `comment` VALUES ('1655546853138505730', '<p>处理了一个五一小长假里积攒的客户询盘，准备开始改论文。打开指导老师批注过的论文，看到要修改的东西，眼前一黑，好，好多啊😭看看我能改几天。<br/>这几天也看了很多前辈的经验贴，也和身边的朋友聊过，我以后想从事涉外方向。我的计划是研究生期间保持学业成绩前35%，继续精进英语和西班牙语，但是我还想再学一门语言，日语简单一些，但是我还蛮喜欢韩语的，这个到时再看。研0这段时间的打算是<br/>1⃣️实习<br/>2⃣️学习如何看文献并且做笔记📒<br/>3⃣️西班牙语等级考试<br/>4⃣️教资面试<br/>5⃣️CPA经济法<br/>6⃣️文献阅读10+<br/>7⃣️各个方向的书籍📚8+<br/>8⃣️理财知识学习<br/>9⃣️了解论文发表有关<br/>研一上的计划具体情况具体分析，一个阶段一个阶段的来，给自己打个气，加油丫💪<br/></p>', '1655444813783658498', '1655546796037251073', '2023-05-08 20:15:03', '2023-05-08 20:15:03', 2);
INSERT INTO `comment` VALUES ('1655546915885293569', '<p>通勤的时候在听网易云，我一般都听播客或者音乐🎵，这已经连续好几天在路上听音乐了，通勤时间差不多一个半小时左右每天，我一直听一首歌，是一首西班牙语歌-Loca。今天突然发现网易云可以看有几个人在听这首歌，出于好奇就点进去看了一下，知道了一个名词，布鲁斯，也叫蓝调，然后初步了解了一下，是一种音乐风格，我就去网易云搜索布鲁斯，然后听了一些里面的歌，突然发现我以前听过的很多歌在这里面。虽然对布鲁斯这种风格不是特别了解，但是现在有了了解的兴趣，就好开心！！！<br/></p>', '1655444813783658498', '1655546796037251073', '2023-05-08 20:15:18', '2023-05-08 20:15:18', 3);
INSERT INTO `comment` VALUES ('1655546950928703489', '<p>好强！<br/>仅仅通过这些计划我就知道lz必然会在研究生中大杀四方<br/>不过我认为lz缺了一样东西——松弛感<br/>不管怎么说，开学前该有gap month冲冲电，做一些未来生活上的规划，现在出国比暑假旺季也舒适很多，可以考虑去西班牙一下，哈哈哈<br/></p>', '1655541038260318210', '1655546796037251073', '2023-05-08 20:15:26', '2023-05-08 20:15:26', 4);
INSERT INTO `comment` VALUES ('1655546985795952642', '<p>好快乐！下班之后回学校，桌子上放了半熟芝士，是室友特意给我留的☺️，还有还有，我室友真的像是一个厨神，她把菠萝切成小块，中间夹了一点乌梅，真的好甜，甜到心坎里那种！现在有一股强烈的学做饭🍳的念头，昨天去我舅舅家新家开火，我舅舅，舅妈，我，三个人一中午做了6个菜，还有三个是买的现成的，我就煮了个🦐。暑假我就学着做饭，我妈妈做饭巨好吃的天赋应该多多少少遗传给我一点吧……<br/></p>', '1655444813783658498', '1655546796037251073', '2023-05-08 20:15:35', '2023-05-08 20:15:35', 5);
INSERT INTO `comment` VALUES ('1655547011922272257', '<p>复试完4.10去实习，到现在20多天，终于要开出第一单了，是一个秘鲁客户，虽然金额不多，几百美元，但是也算是迈出一大步了，今天果然是幸福的一天😺<br/></p>', '1655444813783658498', '1655546796037251073', '2023-05-08 20:15:41', '2023-05-08 20:15:41', 6);
INSERT INTO `comment` VALUES ('1655547061733826561', '<p>加油！努力！还有要开心！<br/></p>', '1655545466782621697', '1655546796037251073', '2023-05-08 20:15:53', '2023-05-08 20:15:53', 7);
INSERT INTO `comment` VALUES ('1655767902790754305', '<p>顶</p>', '1655545466782621697', '1655544800248995842', '2023-05-09 10:53:25', '2023-05-09 10:53:25', 2);
INSERT INTO `comment` VALUES ('1655773656851554306', '<p>祝lz毕业后一帆风顺，加油！！！<br/></p>', '1655444813783658498', '1655773412705312770', '2023-05-09 11:16:17', '2023-05-09 11:16:17', 2);

-- ----------------------------
-- Table structure for danmu
-- ----------------------------
DROP TABLE IF EXISTS `danmu`;
CREATE TABLE `danmu`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `danmu` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `color` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `isuse` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of danmu
-- ----------------------------
INSERT INTO `danmu` VALUES ('lixingyun', '呼吸之野', '大帅！‘', '5.33', '#000000', '0');
INSERT INTO `danmu` VALUES ('lixingyun', '呼吸之野', '呜呜呜', '10.74', '#000000', '0');
INSERT INTO `danmu` VALUES ('lixingyun', '呼吸之野', '大帅', '3.21', '#72E36C', '0');
INSERT INTO `danmu` VALUES ('gqt-2', '呼吸之野', '中华人民共和国万岁！', '17.95', '#ED7373', '0');
INSERT INTO `danmu` VALUES ('gqt-2', '呼吸之野', '111', '24.46', '#ED7373', '0');
INSERT INTO `danmu` VALUES ('gqt-2', '明天你好', '共产主义万岁', '17.15', '#DA7474', '0');
INSERT INTO `danmu` VALUES ('gqt-3', '明天你好', '11', '45.56', '#D3BCBC', '0');
INSERT INTO `danmu` VALUES ('gqt-3', '明天你好', '1', '4.20', '#000000', '0');
INSERT INTO `danmu` VALUES ('gqt-3', '明天你好', '12', '11.55', '#EEE5E5', '0');
INSERT INTO `danmu` VALUES ('1656267020902498306', '呼吸之野', '你好', '3.88', '#000000', '0');
INSERT INTO `danmu` VALUES ('1656267020902498306', '呼吸之野', '111', '9.56', '#B09999', '0');
INSERT INTO `danmu` VALUES ('1656267020902498306', '呼吸之野', '', '14.63', '#B09999', '0');
INSERT INTO `danmu` VALUES ('1656267020902498306', '呼吸之野', '111', '15.68', '#B09999', '0');
INSERT INTO `danmu` VALUES ('1656267020902498306', '呼吸之野', '', '19.68', '#B09999', '0');
INSERT INTO `danmu` VALUES ('1656267020902498306', '呼吸之野', '11', '12.45', '#000000', '0');
INSERT INTO `danmu` VALUES ('1656321810210676738', '呼吸之野', '你好', '2.68', '#000000', '0');
INSERT INTO `danmu` VALUES ('1656321810210676738', '呼吸之野', '哦也', '5.15', '#D6C7C7', '0');
INSERT INTO `danmu` VALUES ('1656321380546174978', '呼吸之野', '1', '3.74', '#E3DFDF', '0');
INSERT INTO `danmu` VALUES ('1656321380546174978', '呼吸之野', 'ssss', '9.71', '#B19E9E', '0');
INSERT INTO `danmu` VALUES ('1656321380546174978', '呼吸之野', '发发发', '4.41', '#000000', '0');
INSERT INTO `danmu` VALUES ('1656321380546174978', '呼吸之野', '无哈哈哈', '4.20', '#F7F0F0', '0');
INSERT INTO `danmu` VALUES ('1656321380546174978', '呼吸之野', '加油', '17.98', '#F7F0F0', '0');

-- ----------------------------
-- Table structure for follow
-- ----------------------------
DROP TABLE IF EXISTS `follow`;
CREATE TABLE `follow`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '被关注人ID',
  `follower_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '关注人ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 173 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户关注' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of follow
-- ----------------------------
INSERT INTO `follow` VALUES (163, '1436961178478804994', '1438108378097258498');
INSERT INTO `follow` VALUES (164, '1438108378097258498', '1436961178478804994');
INSERT INTO `follow` VALUES (166, '1436961178478804994', '1649314481341861890');
INSERT INTO `follow` VALUES (168, '1655451876802850818', '1655444813783658498');
INSERT INTO `follow` VALUES (170, '1655541038260318210', '1655444813783658498');
INSERT INTO `follow` VALUES (172, '1655545466782621697', '1655444813783658498');

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '标题',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT 'markdown内容',
  `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作者ID',
  `comments` int NOT NULL DEFAULT 0 COMMENT '评论统计',
  `collects` int NOT NULL DEFAULT 0 COMMENT '收藏统计',
  `view` int NOT NULL DEFAULT 0 COMMENT '浏览统计',
  `top` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否置顶，1-是，0-否',
  `essence` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否加精，1-是，0-否',
  `thumbsUp` int NULL DEFAULT 0 COMMENT '点赞数',
  `create_time` datetime NOT NULL COMMENT '发布时间',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  UNIQUE INDEX `title`(`title` ASC) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  INDEX `create_time`(`create_time` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '话题表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES ('1655543937132535809', '24届准备找实习', '<p>大哥们可以看看怎么改吗，会不会被问麻？<br/></p><p><img src=\"https://yufengling.oss-cn-shenzhen.aliyuncs.com/images/2023/05/08/5563126eaa684a049c2f41ae30d6d335.png\" contenteditable=\"false\"/></p>', '1655541038260318210', 3, 0, 7, b'0', b'0', 0, '2023-05-08 20:03:28', '2023-05-08 20:04:43');
INSERT INTO `post` VALUES ('1655458758380646402', '2年开发经验，在三线城市要多少合适啊？', '<p>求各位前辈指导，谢谢你们<br/></p>', '1655444813783658498', 2, 0, 8, b'0', b'0', 0, '2023-05-08 14:25:00', '2023-05-08 14:25:42');
INSERT INTO `post` VALUES ('1655452986456305665', '你好，我是周杰伦。我正式入驻虚拟社区了', '<p><img src=\"https://yufengling.oss-cn-shenzhen.aliyuncs.com/images/2023/05/08/3f749ab6cd0a458184102d4f9a5112c2.jpg\" contenteditable=\"false\"/></p>', '1655451876802850818', 0, 0, 14, b'0', b'0', 0, '2023-05-08 14:02:03', '2023-05-08 14:02:41');
INSERT INTO `post` VALUES ('1655450083645616130', '兔年你好 ​​​', '<p><img src=\"https://yufengling.oss-cn-shenzhen.aliyuncs.com/images/2023/05/08/3ee02d672ed6485f96c2fbb65535ec5c.jpg\" contenteditable=\"false\"/></p>', '1655444813783658498', 0, 0, 12, b'0', b'0', 0, '2023-05-08 13:50:31', '2023-05-08 19:58:53');
INSERT INTO `post` VALUES ('1655773412705312770', '只是一个幼稚的学生记录下他最后的学生时光', '<p><img src=\"https://yufengling.oss-cn-shenzhen.aliyuncs.com/images/2023/05/09/950e2589cde4439caa71bfd5231cfd26.png\" contenteditable=\"false\"/></p>', '1655545466782621697', 2, 0, 52, b'0', b'0', 0, '2023-05-09 11:15:19', '2023-05-09 11:16:17');
INSERT INTO `post` VALUES ('1655534088571219969', '小白求助c++后端现在前景怎么样？', '<p>如题</p>', '1655457625457192961', 1, 0, 4, b'0', b'0', 0, '2023-05-08 19:24:20', '2023-05-08 19:24:37');
INSERT INTO `post` VALUES ('1655546796037251073', '星河滚烫，人间理想', '<p>最近的各种社交媒体被同学的上岸刷屏，回顾了一下我的考研之路，除了几本书本，没有任何一张照片可以用来纪念。<br/>在备考期间，我觉得考研上岸就满足了，但是当我真正来到北京参加复试的时候才发现，在一个遍地都是人才的地方，一个研究生并没有值得骄傲的地方。复试完回来就对自己的未未来感到迷茫，迫切地想要学习各种知识以弥补大一大二时的混账生活，选择的手足无措，深夜甚至会想到两三点，不停地想要抓住些什么。<br/>昨天去实习的路上，在学校坐了公交车，因为放假人很多，公交车到我们学校的时候就已经满了，身边的同学们都是拉着行李箱奔向回家的车程，好像只有我背着书包赶着去实习公司。车上的座位几乎都满了，我背着书包想着就站个40分钟吧，一位奶奶突然拉了一下我的手，和我说“小姑娘，你坐我旁边吧，好吗？”当时我的心里一震，那位阿姨只要看到有空位置，就会和站着的同学们招手，让他们过来坐。我坐在她旁边之后，奶奶特别热情地和我搭话，我突然就特别难过，虽然是大学生，可是真的还有人把你当小孩子看，并不是所有人都逼迫你长大，我怕声音有些哽咽，就拿出了耳机，戴着耳机看向窗外。窗外的一幕幕就像一场人生，我试着回忆自己的人生，发现对于自己人生的记录少的可怜。<br/>我反思自己的性格，平常很少在社交媒体上发布动态，甚至连朋友圈都很少刷。我很喜欢独处的时间，因为有很多时间可以思考，但是我也意识到这对我将来工作可能会是一个麻烦，虽然还不知道具体会从事哪个方向，但是大方向上肯定是律师行业了，所以平常还是需要多与人交流，多交朋友，多表达自己。<br/>最近一直在思考未来的职业规划，本科西班牙语，研究生考的法律，英语也还在，但是对经济贸易方面的知识也非常感兴趣。还需要斟酌，不能太草率地决定，毕竟关乎一生的工作。<br/>这次就记录到这里，等我想好自己想从事的方向再来这个帖子下面继续记录，给我的人生留下一些以后可以回忆的痕迹。<br/></p>', '1655444813783658498', 6, 0, 24, b'0', b'0', 0, '2023-05-08 20:14:49', '2023-05-08 20:15:53');
INSERT INTO `post` VALUES ('1655544536016232449', '有没有老哥知道iiot边缘计算这个行业', '<p>互联网行业不景气反手躲回老家去找了个做工业物联网的，项目经理跟我讲就是涉及一堆传感器数据的出入库整理计算，数据治理<br/>不知道这个行业前景咋样 有没有老哥是做这类工作的<br/></p>', '1655446603392512001', 0, 0, 3, b'0', b'0', 0, '2023-05-08 20:05:51', '2023-05-08 20:05:51');
INSERT INTO `post` VALUES ('1655459634289733633', '萌新小白求助', '<p>为什么我的 visual studio不能编译运行</p><img src=\"https://yufengling.oss-cn-shenzhen.aliyuncs.com/images/2023/05/08/594ce71d4d64491e8bd2615f9a9c01e1.png\" contenteditable=\"false\"/>', '1655459176812802050', 2, 0, 7, b'0', b'0', 0, '2023-05-08 14:28:28', '2023-05-08 20:00:40');
INSERT INTO `post` VALUES ('1655543344632569857', '要体测了？？', '<p><img src=\"https://yufengling.oss-cn-shenzhen.aliyuncs.com/images/2023/05/08/26bcfb35f4444b608b6c84f531354686.png\" contenteditable=\"false\"/></p>', '1655541038260318210', 1, 0, 9, b'0', b'0', 0, '2023-05-08 20:01:07', '2023-05-08 20:01:51');
INSERT INTO `post` VALUES ('1655546131059712002', '说出一个你已经实现过的梦想', '<p>我先来，考上了理想的高中<br/></p><p><img src=\"https://yufengling.oss-cn-shenzhen.aliyuncs.com/images/2023/05/08/54588bd9e967400a9ff48680b8bbc1cc.png\" contenteditable=\"false\"/></p>', '1655545466782621697', 4, 0, 13, b'0', b'0', 0, '2023-05-08 20:12:11', '2023-05-08 20:13:43');
INSERT INTO `post` VALUES ('1655544800248995842', '输出答案没有错，提交不了怎么回事', '<p><img src=\"https://yufengling.oss-cn-shenzhen.aliyuncs.com/images/2023/05/08/feacd6a4623948b7b42632b77ba0b0d3.png\" contenteditable=\"false\"/></p><p><img src=\"https://yufengling.oss-cn-shenzhen.aliyuncs.com/images/2023/05/08/af064d0fd0b4457fbba52361a8ecd91c.png\" contenteditable=\"false\"/></p><p><img src=\"https://yufengling.oss-cn-shenzhen.aliyuncs.com/images/2023/05/08/47b47db10ae84a94a98383a2c4ff3e81.png\" contenteditable=\"false\"/></p>', '1655446603392512001', 1, 0, 7, b'0', b'0', 0, '2023-05-08 20:06:54', '2023-05-09 10:53:25');

-- ----------------------------
-- Table structure for post_tag
-- ----------------------------
DROP TABLE IF EXISTS `post_tag`;
CREATE TABLE `post_tag`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tag_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签ID',
  `topic_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '话题ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `tag_id`(`tag_id` ASC) USING BTREE,
  INDEX `topic_id`(`topic_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 99 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '话题-标签 中间表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of post_tag
-- ----------------------------
INSERT INTO `post_tag` VALUES (86, '1655449575258222593', '1655450083645616130');
INSERT INTO `post_tag` VALUES (87, '1655452268043333633', '1655452986456305665');
INSERT INTO `post_tag` VALUES (88, '1655458333225021441', '1655458758380646402');
INSERT INTO `post_tag` VALUES (89, '1655459333742686210', '1655459634289733633');
INSERT INTO `post_tag` VALUES (90, '1655459333742686210', '1655534088571219969');
INSERT INTO `post_tag` VALUES (92, '1655541269144170497', '1655543344632569857');
INSERT INTO `post_tag` VALUES (93, '1655458333225021441', '1655543937132535809');
INSERT INTO `post_tag` VALUES (94, '1655458333225021441', '1655544536016232449');
INSERT INTO `post_tag` VALUES (95, '1655459333742686210', '1655544800248995842');
INSERT INTO `post_tag` VALUES (96, '1655545661448658945', '1655546131059712002');
INSERT INTO `post_tag` VALUES (97, '1655545661448658945', '1655546796037251073');
INSERT INTO `post_tag` VALUES (98, '1655545661448658945', '1655773412705312770');

-- ----------------------------
-- Table structure for promotion
-- ----------------------------
DROP TABLE IF EXISTS `promotion`;
CREATE TABLE `promotion`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '广告标题',
  `link` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '广告链接',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '说明',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '广告推广表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of promotion
-- ----------------------------
INSERT INTO `promotion` VALUES (1, '哔哩哔哩', 'https://www.bilibili.com/', '国内知名的视频弹幕网站，这里有及时的动漫新番，活跃的ACG氛围，有创意的Up主。大家可以在这里找到许多欢乐');
INSERT INTO `promotion` VALUES (3, '学院官网', 'https://www.hnust.edu.cn/', '湖南科技大学官网');
INSERT INTO `promotion` VALUES (4, '4399', 'www.4399.com', '4399小游戏');

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '标签',
  `topic_count` int NOT NULL DEFAULT 0 COMMENT '关联话题',
  `bigManager` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '吧主',
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '吧头像',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '吧描述',
  `followCount` int NULL DEFAULT NULL COMMENT '关注此吧的人数',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '标签表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES ('1655449575258222593', '许嵩', 1, '呼吸之野', 'https://yufengling.oss-cn-shenzhen.aliyuncs.com/images/2023/05/08/b50d82450eb54c3bb4b9dda84c691b3fjpeg', '嵩鼠交流社区', 0);
INSERT INTO `tag` VALUES ('1655452268043333633', '周杰伦', 1, '周杰伦', 'https://yufengling.oss-cn-shenzhen.aliyuncs.com/images/2023/05/08/da9a8136e4d84614900962e21a3ef421jpeg', '中国周杰伦粉丝交流社区', 0);
INSERT INTO `tag` VALUES ('1655458333225021441', 'Java', 1, '李星云', 'https://yufengling.oss-cn-shenzhen.aliyuncs.com/images/2023/05/08/b55eff2c85ec4a91b1026b9317af7e63.jpg', '有问题为什么不先问问隔壁C++吧呢？', 0);
INSERT INTO `tag` VALUES ('1655459333742686210', 'C++', 1, '梁睿勤', 'https://yufengling.oss-cn-shenzhen.aliyuncs.com/images/2023/05/08/d84bdc8ed7924d71b982552ec55b5546.jpg', '有问题为什么不先问问隔壁C语言吧呢？', 0);
INSERT INTO `tag` VALUES ('1655541269144170497', '湖南科技大学', 1, '湖小科', 'https://yufengling.oss-cn-shenzhen.aliyuncs.com/images/2023/05/08/4f0742508a384d75989b863ae53c0bb6.jpg', '湖科大学子交流社区', 0);
INSERT INTO `tag` VALUES ('1655545661448658945', '天堂鸡汤', 1, '明天你好', 'https://yufengling.oss-cn-shenzhen.aliyuncs.com/images/2023/05/08/3b86ca6aeaa541b893267abcecd4c08b.jpg', '前路迢迢，有薄雾弥漫，亦有热茶两盏', 1);

-- ----------------------------
-- Table structure for tag_smallmanager
-- ----------------------------
DROP TABLE IF EXISTS `tag_smallmanager`;
CREATE TABLE `tag_smallmanager`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT ' 主键',
  ` userId` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '小吧主id',
  ` tagId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '吧',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tag_smallmanager
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户ID',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `alias` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '密码',
  `avatar` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '头像',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱',
  `mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机',
  `score` int NOT NULL DEFAULT 0 COMMENT '积分',
  `token` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'token',
  `bio` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个人简介',
  `admin` int NOT NULL DEFAULT 0 COMMENT '是否激活，1：是，0：否',
  `status` bit(1) NULL DEFAULT b'1' COMMENT '状态，1：使用，0：停用',
  `role_id` int NULL DEFAULT NULL COMMENT '用户角色',
  `create_time` datetime NOT NULL COMMENT '加入时间',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_name`(`username` ASC) USING BTREE,
  INDEX `user_email`(`email` ASC) USING BTREE,
  INDEX `user_create_time`(`create_time` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1111111', '谭翔宇', '谭翔宇', '330875cd3c6f6fe6a53ee92e340d2f62', 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png', 'txy@qq.com', NULL, 0, '', NULL, 1, b'1', NULL, '2023-05-10 15:22:03', NULL);
INSERT INTO `user` VALUES ('1655444813783658498', '呼吸之野', '呼吸之野', '827ccb0eea8a706c4c34a16891f84e7b', 'https://yufengling.oss-cn-shenzhen.aliyuncs.com/images/2023/05/08/01b489e4baf94bd582793155b43569bajpeg', '1793494101@qq.com', NULL, 15, '', '自由职业者', 0, b'1', NULL, '2023-05-08 13:29:35', NULL);
INSERT INTO `user` VALUES ('1655446603392512001', '蔡徐坤', '蔡徐坤', '827ccb0eea8a706c4c34a16891f84e7b', 'https://img0.baidu.com/it/u=2250892508,1709900654&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1682182800&t=0deb4ec7ec47af4937dd5d56ad9cf9c9', 'cxk@qq.com', NULL, 2, '', '自由职业者', 0, b'1', NULL, '2023-05-08 13:36:42', NULL);
INSERT INTO `user` VALUES ('1655447183330537474', '暗裔剑魔', '暗裔剑魔', '8f421bc34448730ab4576ba88476a114', 'https://img0.baidu.com/it/u=2250892508,1709900654&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1682182800&t=0deb4ec7ec47af4937dd5d56ad9cf9c9', 'yatrox@qq.com', NULL, 0, '', '自由职业者', 0, b'1', NULL, '2023-05-08 13:39:00', NULL);
INSERT INTO `user` VALUES ('1655447588366086146', '许嵩', '许嵩', '827ccb0eea8a706c4c34a16891f84e7b', 'https://img0.baidu.com/it/u=2250892508,1709900654&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1682182800&t=0deb4ec7ec47af4937dd5d56ad9cf9c9', 'xusong@qq.com', NULL, 0, '', '自由职业者', 0, b'1', NULL, '2023-05-08 13:40:36', NULL);
INSERT INTO `user` VALUES ('1655451876802850818', '周杰伦', '周杰论', '827ccb0eea8a706c4c34a16891f84e7b', 'https://yufengling.oss-cn-shenzhen.aliyuncs.com/images/2023/05/08/d44f2f7d4a374936af3869b23c79475fjpeg', 'zhoujielun@qq.com', NULL, 1, '', '自由职业者', 0, b'1', NULL, '2023-05-08 13:57:39', NULL);
INSERT INTO `user` VALUES ('1655457625457192961', '李星云', '李星云', '827ccb0eea8a706c4c34a16891f84e7b', 'https://yufengling.oss-cn-shenzhen.aliyuncs.com/images/2023/05/08/751524900bd74fc68c88ada887d84617.png', 'lixingyun@qq.com', NULL, 1, '', '自由职业者', 0, b'1', NULL, '2023-05-08 14:20:29', NULL);
INSERT INTO `user` VALUES ('1655459176812802050', '梁睿勤', '梁睿勤', '827ccb0eea8a706c4c34a16891f84e7b', 'https://img0.baidu.com/it/u=2250892508,1709900654&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1682182800&t=0deb4ec7ec47af4937dd5d56ad9cf9c9', 'lrq@qq.com', NULL, 1, '', '自由职业者', 0, b'1', NULL, '2023-05-08 14:26:39', NULL);
INSERT INTO `user` VALUES ('1655534319622844417', '我爱c++啊啊啊啊', '我爱c++啊啊啊啊', '827ccb0eea8a706c4c34a16891f84e7b', 'https://img0.baidu.com/it/u=2250892508,1709900654&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1682182800&t=0deb4ec7ec47af4937dd5d56ad9cf9c9', 'c++@qq.com', NULL, 0, '', '自由职业者', 0, b'1', NULL, '2023-05-08 19:25:15', NULL);
INSERT INTO `user` VALUES ('1655541038260318210', '湖小科', '湖小科', '827ccb0eea8a706c4c34a16891f84e7b', 'https://img0.baidu.com/it/u=2250892508,1709900654&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1682182800&t=0deb4ec7ec47af4937dd5d56ad9cf9c9', 'huxiaok@qq.com', NULL, 2, '', '自由职业者', 0, b'1', NULL, '2023-05-08 19:51:57', NULL);
INSERT INTO `user` VALUES ('1655545466782621697', '明天你好', '明天你好', '827ccb0eea8a706c4c34a16891f84e7b', 'https://yufengling.oss-cn-shenzhen.aliyuncs.com/images/2023/05/08/935dbac1621240ffa83f252d51d78e44.jpg', 'mtnh@qq.com', NULL, 2, '', '自由职业者', 0, b'1', NULL, '2023-05-08 20:09:32', NULL);
INSERT INTO `user` VALUES ('1656195211574263809', '123', '123', '827ccb0eea8a706c4c34a16891f84e7b', 'https://img0.baidu.com/it/u=2250892508,1709900654&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1682182800&t=0deb4ec7ec47af4937dd5d56ad9cf9c9', '123@qq.com', NULL, 0, '', '自由职业者', 0, b'1', NULL, '2023-05-10 15:11:24', NULL);
INSERT INTO `user` VALUES ('1656221911519518721', '1233', '1233', '330875cd3c6f6fe6a53ee92e340d2f62', 'https://img0.baidu.com/it/u=2250892508,1709900654&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1682182800&t=0deb4ec7ec47af4937dd5d56ad9cf9c9', '111111@qq.com', NULL, 0, '', '自由职业者', 0, b'1', NULL, '2023-05-10 16:57:29', NULL);

-- ----------------------------
-- Table structure for user_tag
-- ----------------------------
DROP TABLE IF EXISTS `user_tag`;
CREATE TABLE `user_tag`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `tag_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_tag
-- ----------------------------
INSERT INTO `user_tag` VALUES ('1655797573431468034', '1655444813783658498', '1655545661448658945');

-- ----------------------------
-- Table structure for validation
-- ----------------------------
DROP TABLE IF EXISTS `validation`;
CREATE TABLE `validation`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT ' 邮箱',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT ' 验证码',
  `type` int NULL DEFAULT NULL COMMENT ' 验证类型',
  `time` timestamp NULL DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of validation
-- ----------------------------

-- ----------------------------
-- Table structure for video
-- ----------------------------
DROP TABLE IF EXISTS `video`;
CREATE TABLE `video`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `img_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `count` int NULL DEFAULT NULL,
  `danmu_count` int NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of video
-- ----------------------------
INSERT INTO `video` VALUES ('1656288771967426561', '24年了，我们从未忘记', 'https://yufengling.oss-cn-shenzhen.aliyuncs.com/videos/2023/05/10/d881ec6f83c449e9a894451267a75013.mp4', 'https://yufengling.oss-cn-shenzhen.aliyuncs.com/images/2023/05/10/3cbdddec413442aba3443783fae7f63e.jpg', 0, 0);
INSERT INTO `video` VALUES ('1656289014465306626', '马克思曾预言：中华民族必将重新崛起', 'https://yufengling.oss-cn-shenzhen.aliyuncs.com/videos/2023/05/10/b8d090b5d31f424f9307f445f7b8626c.mp4', 'https://yufengling.oss-cn-shenzhen.aliyuncs.com/images/2023/05/10/af74786e8be74e3080b17091d0236735.jpg', 0, 0);
INSERT INTO `video` VALUES ('1656320112738099201', '【凡星x毛不易】平凡是我们，漫天的繁星也是我们！', 'https://yufengling.oss-cn-shenzhen.aliyuncs.com/videos/2023/05/10/fa7de075a159489b82d436c38d02978d.mp4', 'https://yufengling.oss-cn-shenzhen.aliyuncs.com/images/2023/05/10/c28ca46727174f1b98e7e820aa7f51b1.jpg', 5, 0);
INSERT INTO `video` VALUES ('1656321380546174978', '【微光星海】大步向前，我们终将奔赴星海！', 'https://yufengling.oss-cn-shenzhen.aliyuncs.com/videos/2023/05/10/fb04b1942e69447490d6bd7958110394.mp4', 'https://yufengling.oss-cn-shenzhen.aliyuncs.com/images/2023/05/10/dd74f76f67e24113b59f596eddd62bdf.jpg', 11, 5);
INSERT INTO `video` VALUES ('1656321810210676738', '“有我！”这是中国青年的答案！', 'https://yufengling.oss-cn-shenzhen.aliyuncs.com/videos/2023/05/10/71bd0ff1155e4091be1b5ec93a9369f2.mp4', 'https://yufengling.oss-cn-shenzhen.aliyuncs.com/images/2023/05/10/e3e83d0f36814fdc80d40696f4a8f201.jpg', 5, 2);
INSERT INTO `video` VALUES ('1656322508923002882', '生活没有意义也充满意义', 'https://yufengling.oss-cn-shenzhen.aliyuncs.com/videos/2023/05/10/2fc740e7771544eda8ab859b6ae42d2b.mp4', 'https://yufengling.oss-cn-shenzhen.aliyuncs.com/images/2023/05/10/ec7e0a13f4b2416b8dc3ef80b4a4c4ef.jpg', 5, 0);
INSERT INTO `video` VALUES ('1656323022028988418', '“生活中总有一些美好值得我们全力以赴!”', 'https://yufengling.oss-cn-shenzhen.aliyuncs.com/videos/2023/05/10/dfbf079dc0404d60a0fb2ff5084f254e.mp4', 'https://yufengling.oss-cn-shenzhen.aliyuncs.com/images/2023/05/10/acbaf7715b1545819a012aabe00977c4.jpg', 2, 0);

SET FOREIGN_KEY_CHECKS = 1;
