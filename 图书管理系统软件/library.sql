/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50015
Source Host           : localhost:3306
Source Database       : library

Target Server Type    : MYSQL
Target Server Version : 50015
File Encoding         : 65001

Date: 2019-06-05 08:53:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `Admin_id` bigint(20) NOT NULL auto_increment COMMENT 'Admin_id',
  `Admin_password` bigint(20) NOT NULL,
  `level` int(8) NOT NULL,
  PRIMARY KEY  (`Admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('123', '123', '0');

-- ----------------------------
-- Table structure for appointment
-- ----------------------------
DROP TABLE IF EXISTS `appointment`;
CREATE TABLE `appointment` (
  `reader_id` int(11) NOT NULL COMMENT '学号',
  `name` varchar(20) NOT NULL,
  `author` varchar(20) default NULL,
  `publish` varchar(20) default NULL,
  `appoint_time` date default NULL COMMENT '预约时间',
  `state` int(10) default NULL,
  PRIMARY KEY  (`name`),
  KEY `idx_appoint_time` (`appoint_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='预约图书表';

-- ----------------------------
-- Records of appointment
-- ----------------------------
INSERT INTO `appointment` VALUES ('1513032096', '数据库原理', '岩田阳一', '人民邮电出版社', '2019-05-06', '1');
INSERT INTO `appointment` VALUES ('1513032096', '都挺好', '阿耐', '江苏凤凰文艺出版社', '2019-04-21', '-1');
INSERT INTO `appointment` VALUES ('1513032096', '高等数学', '同济大学数学系', '高等教育出版社', '2013-10-12', '0');

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
  `name` varchar(20) NOT NULL,
  `level` int(8) NOT NULL,
  `lend_max` int(8) NOT NULL,
  `lend_time` date default NULL,
  PRIMARY KEY  (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authority
-- ----------------------------
INSERT INTO `authority` VALUES ('教职工', '3', '5', null);
INSERT INTO `authority` VALUES ('本科生', '1', '3', null);
INSERT INTO `authority` VALUES ('研究生', '2', '5', null);
INSERT INTO `authority` VALUES ('管理员', '0', '5', null);

-- ----------------------------
-- Table structure for book_info
-- ----------------------------
DROP TABLE IF EXISTS `book_info`;
CREATE TABLE `book_info` (
  `book_id` bigint(20) NOT NULL auto_increment,
  `name` varchar(50) NOT NULL,
  `pic` varchar(255) default NULL,
  `stock` int(6) default NULL,
  `author` varchar(50) default NULL,
  `publish` varchar(30) default NULL,
  `ISBN` varchar(13) default NULL,
  `introduction` text,
  `language` varchar(10) default NULL,
  `price` decimal(10,2) default NULL,
  `pubdate` date default NULL,
  `class_id` int(11) default NULL,
  `state` smallint(6) default NULL,
  PRIMARY KEY  (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book_info
-- ----------------------------
INSERT INTO `book_info` VALUES ('10001', 'Effective Java第三版', 'f03b4acf-6ea9-4c95-90f6-e1b51064f875.jpg', '4', '[美] 约书亚·布洛克', '机械工业出版社', '9787111612728', 'Java之父James Gosling鼎力推荐、Jolt获奖作品全新升级，针对Java 7、8、9全面更新，Java程序员参考书。包含大量完整的示例代码和透彻的技术分析，通过90条经验法则，探索新的设计模式和语言习惯用法，帮助读者更加有效地使用Java编程语言及其基本类库。', '英语', '112.00', '2019-01-01', '18', '1');
INSERT INTO `book_info` VALUES ('10002', '深入理解Java虚拟机', 'd60e2c66-9696-479f-abc8-00ad79dc1601.jpg', '3', '周志明', '机械工业出版社', '9787111421900', '由周志明所著，共分为五大部分，围绕内存管理、执行子系统、程序编译与优化、高效并发等核主题对JVM进行了全面而深入的分析，深刻揭示了JVM的工作原理。**部分从宏观的角度介绍了整个Java技术体系、Java和JVM的发展历程、模块化，以及JDK的编译，这对理解本书后面内容有重要帮助。第二部分讲解了JVM的自动内存管理，包括虚拟机内存区域的划分原理以及各种内存溢出异常产生的原因；常见的垃圾收集算法以及垃圾收集器的特点和工作原理；常见虚拟机监控与故障处理工具的原理和使用方法。第三部分分析了虚拟机的执行子系统，包括类文件...... ', '中文', '79.00', '2013-06-01', '18', '1');
INSERT INTO `book_info` VALUES ('10003', 'Java编程思想', '1f54df13-ae65-4fec-bdb8-37db5323da50.jpg', '4', '[美]Bruce Eckel', '机械工业出版社', '978711213826', '《计算机科学丛书：Java编程思想（第4版）》赢得了全球程序员的广泛赞誉，即使是晦涩的概念，在BruceEckel的文字亲和力和小而直接的编程示例面前也会化解于无形。从Java的基础语法到高级特性（深入的面向对象概念、多线程、自动项目构建、单元测试和调试等），本书都能逐步指导你轻松掌握。\r\n从《计算机科学丛书：Java编程思想（第4版）》获得的各项大奖以及来自世界各地的读者评论中，不难看出这是一本经典之作。本书的作者拥有多年教学经验，对C、C 以及Java语言都有独到、深入的见解，以通俗易懂及小而直接的示例解释了一个个晦涩抽象的概念。本书共22章，包括操作符、控制执行流程、访问权限控制、复用类、多态、接口、通过异常处理错误、字符串、泛型、数组、容器深入研究、JavaI/O系统、枚举类型、并发以及图形化用户界面等内容。这些丰富的内容，包含了Java语言基础语法以及高级特性，适合各个层次的Java程序员阅读，同时也是高等院校讲授面向对象程序设计语言以及Java语言的好教材和参考书。', '英语', '108.00', '2007-06-01', '18', '1');
INSERT INTO `book_info` VALUES ('10004', '无所畏', 'afac8dcb-62b8-4321-9a8e-7693b79e7c7a.jpg', '1', '冯唐', '北京联合出版社', '9787559623096', '全书分为如何获得成功、爱情如何对抗时间、生活怎么过才算有意义、自我价值如何体现四个章节，此书对冯唐来说，是一个新的开始，中年的冯唐在书中坦言自己的中年危机、父亲的去世、老人生哲学，以及对过去和未来的自我审视。活在这世上，什么都不要怕，做自己认为对的事儿，慢慢放下输赢和计算。\r\n', '中文', '49.80', '2018-09-01', '9', '1');
INSERT INTO `book_info` VALUES ('10005', 'Java核心技术卷1', '7ffea790-6b36-4426-824d-0b15cbf9647d.jpg', '5', '凯S.霍斯特曼', '机械工业出版社', '9787111547426', '通过这本书将快速掌握核心Java语法', '中文', '119.00', '2016-09-01', '18', '0');
INSERT INTO `book_info` VALUES ('10006', '我承认我不曾历经沧桑', '835180c7-7e75-40f9-a92a-f3d3a014606a.jpg', '3', '蒋方舟', '广西师范大学出版社', '9787549543519', '记录本身，即已是反抗。在书中，作者记录下对于成长与写作的反思，也记录下身旁被绑架的一代群像他们的童年早早消逝，青春期过早觉醒，他们过早地发现了成人世界的虚伪，更过早地被抛入一个充满竞争与争斗的世界。写作者并无能力和义务改变社会，但记录本身，即已是反抗。写下本身，即已是永恒。\r\n', '中文', '32.00', '2013-10-01', '9', '1');
INSERT INTO `book_info` VALUES ('10007', '细数那些叫思念的羊', '27b74bad-de9b-4393-966d-6b6bc91dd24c.jpg', '3', '张晓风', '青岛出版社', '9787555233077', '《细数那些叫思念的羊》中，张晓风从寻常小事切入，点绘人人拥有的幸福;俯视红尘，悲悯人世无常;探看大自然的奥妙，亦秀亦豪，从而深思人生种种际遇，出入大我与小我，用最悠游且积极的生命节奏与城市的律动同步。 \r\n本书是张晓风最新散文，精选50年文章菁华，超一半作品大陆首次出版，张晓风作序隆重推出。现世安稳，岁月静好，一个女人温柔了岁月，与这个世界握手言和。', '中文', '35.00', '2015-12-01', '9', '1');
INSERT INTO `book_info` VALUES ('10008', '皮囊', '3036c7d8-7ae4-4fc7-8098-9f534eae2337.jpg', '3', '蔡崇达', '天津人民出版社', '9787201088945', '蔡崇达随笔集，以人物肖像画的方式描绘了福建渔业小镇的风土人情和时代变迁，在温情而又残酷的故事讲述中阐述了作者对父母、家乡的缅怀，对朋友命运的关切。文集风格沉稳，表达了这一代理想膨胀却又深感现实骨感而无处安身的青年人对自己命运的深切思考。部分作品曾收录于《一个》书系。这部特别的“新人新作”，由韩寒监制，上市之初即广受好评。白岩松、阿来、阎连科等评价为当下写作中的一个惊喜。', '中文', '39.80', '2014-12-01', '9', '1');
INSERT INTO `book_info` VALUES ('10009', '设计模式之禅', 'bc961c55-870a-476f-895b-06a7e62cf52f.jpg', '5', '秦小波', '机械工业出版社', '9787111437871', '本书是设计模式领域公认的3本经典著作之一，“极具趣味，容易理解，但讲解又极为严谨和透彻”是本书的写作风格和方法的*特点。第1版2010年出版，畅销至今，广受好评，是该领域的里程碑著作。深刻解读6大设计原则和28种设计模式的准确定义、应用方法和*实践，全方位比较各种同类模式之间的异同，详细讲解将不同的模式组合使用的方法。第2版在第1版的基础上有两方面的改进，一方面结合读者的意见和建议对原有内容中的瑕疵进行了修正和完善，另一方面增加了4种新的设计模式，希望这一版能为广大程序员们奉上一场更加完美的设计模式盛宴！\r\n', '中文', '89.00', '2014-03-01', '18', '1');
INSERT INTO `book_info` VALUES ('10010', '自在独行', '12726692-6410-40d3-b66b-8d3454084b54.jpg', '2', '贾平凹', '长江文艺出版社', '9787535488473', '平凹先生素来喜静，怕有人来敲他的房门。让他觉得自在的，要么就是行走于西北的大地，要么就是隐居在自己的书房。先生其实也喜欢热闹，只是先生的热闹并不是灯火灿烂，而是内心的安宁与独行的自在。这本书写情感、聊爱好、谈社会、说人生。有俗世的智慧，也有生活的趣味。对于匆匆的路人，平凹先生这部文集只是用来附庸风雅的玩物。但这本书却要写给生命的行者。愿他们能懂得孤独的真义，在生活里多一些从容潇洒。\r\n\r\n', '中文', '39.00', '2016-05-01', '9', '1');
INSERT INTO `book_info` VALUES ('50030', '耶路撒冷三千年', '6795bf26-568b-4bbf-9047-a539112f1409.jpg', '2', '[英]西蒙·蒙迪菲奥里', '民主与建设出版社', '9787513903509', '耶路撒冷曾被视为世界的中心，是基督教、犹太教和伊斯兰教三大宗教的圣地，是文明冲突的战略要冲，是让世人魂牵梦绕的去处，是惑人的阴谋与虚构的网络传说和二十四小时新闻发生的地方。西蒙·蒙蒂菲奥里依年代顺序，以三大宗教围绕“圣城”的角逐，以几大家族的兴衰更迭为主线，生动讲述了耶路撒冷的前世今生。以客观、中立的角度，透过士兵与先知、诗人与国王、农民与音乐家的生活，以及创造耶路撒冷的家族来呈现这座城市的三千年瑰丽历史，还原真实的耶路撒冷……', '中文', '78.00', '2015-01-01', '11', '1');
INSERT INTO `book_info` VALUES ('50031', '橘子不是唯一的水果', '72500ae3-dc2b-460f-8e2d-ce3a78afec34.jpg', '2', '[英]珍妮特·温特森', '北京联合出版社', '9787559614711', '在英国一个偏远的小镇，女孩珍妮特生长在一个信奉福音派新教的家庭里。母亲是一个偏执虔诚的教徒，喜欢将事物分成敌友两派。恶魔，邻居，性是她的敌人，而上帝，她家的狗，夏洛特·勃朗特的小说是她的好友。在母亲的教育下，教会活动成了她日常生活的一切。随着她慢慢成长，珍妮特发现自己与小镇和母亲的偏执并不和谐。于是，当她爱上了另外一个女孩，并且这种超出常规之外的爱被发现后，两者的矛盾爆发了。这场她人生中最初的爱恋在教会和母亲的阻止下无疾而终，但让珍妮特理解了内心的渴望。她离开了小镇和母亲，同时也带着这份决绝进入了她的成年。', '英语', '49.00', '2018-04-01', '9', '1');
INSERT INTO `book_info` VALUES ('50032', '肖秀荣考研政治1000题', '264d6a85-546d-4a05-a057-7267a7a95408.jpg', '2', '肖秀荣', '国家开放大学出版社', '9787304095963', '政治考研复习人手一本，高分神器', '中文', '53.00', '2019-01-01', '4', '0');
INSERT INTO `book_info` VALUES ('50033', '肖秀荣四套卷', 'db12f225-00aa-4217-a195-09767c91ff57.jpg', '4', '肖秀荣', '中国开放大学出版社', '9787304096014', '政治考研人手必备神器', '中文', '31.00', '2019-01-01', '4', '0');

-- ----------------------------
-- Table structure for class_info
-- ----------------------------
DROP TABLE IF EXISTS `class_info`;
CREATE TABLE `class_info` (
  `class_id` int(11) NOT NULL,
  `class_name` varchar(45) NOT NULL,
  PRIMARY KEY  (`class_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of class_info
-- ----------------------------
INSERT INTO `class_info` VALUES ('1', '马克思主义');
INSERT INTO `class_info` VALUES ('2', '哲学');
INSERT INTO `class_info` VALUES ('3', '社会科学总论');
INSERT INTO `class_info` VALUES ('4', '政治');
INSERT INTO `class_info` VALUES ('5', '军事');
INSERT INTO `class_info` VALUES ('6', '经济');
INSERT INTO `class_info` VALUES ('7', '文化');
INSERT INTO `class_info` VALUES ('8', '语言');
INSERT INTO `class_info` VALUES ('9', '文学');
INSERT INTO `class_info` VALUES ('10', '艺术');
INSERT INTO `class_info` VALUES ('11', '历史');
INSERT INTO `class_info` VALUES ('12', '自然科学总论');
INSERT INTO `class_info` VALUES ('13', ' 数理科学和化学');
INSERT INTO `class_info` VALUES ('14', '天文学、地球科学');
INSERT INTO `class_info` VALUES ('15', '生物科学');
INSERT INTO `class_info` VALUES ('16', '医药、卫生');
INSERT INTO `class_info` VALUES ('17', '农业科学');
INSERT INTO `class_info` VALUES ('18', '计算机');
INSERT INTO `class_info` VALUES ('19', '交通运输');
INSERT INTO `class_info` VALUES ('20', '航空、航天');
INSERT INTO `class_info` VALUES ('21', '环境科学');
INSERT INTO `class_info` VALUES ('22', '综合');

-- ----------------------------
-- Table structure for lend_list
-- ----------------------------
DROP TABLE IF EXISTS `lend_list`;
CREATE TABLE `lend_list` (
  `sernum` bigint(20) NOT NULL auto_increment,
  `book_id` int(20) NOT NULL,
  `reader_id` int(11) NOT NULL,
  `lend_date` date default NULL COMMENT '创建时间',
  `back_should_date` date default NULL,
  `back_date` date default NULL,
  `state` tinyint(1) default NULL,
  PRIMARY KEY  (`sernum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lend_list
-- ----------------------------
INSERT INTO `lend_list` VALUES ('2019040158', '10001', '1513032096', '2019-01-14', '2019-03-14', '2019-04-07', '1');
INSERT INTO `lend_list` VALUES ('2019040164', '10003', '1513032096', '2019-03-14', '2019-05-14', '2019-04-08', '1');
INSERT INTO `lend_list` VALUES ('2019040165', '10006', '1513032096', '2019-03-08', '2019-05-08', '2019-04-08', '1');
INSERT INTO `lend_list` VALUES ('2019040189', '10006', '1513032096', '2019-04-14', '2019-06-14', '2019-04-14', '1');
INSERT INTO `lend_list` VALUES ('2019040193', '10001', '1513032000', '2019-04-20', '2019-06-20', '2019-04-20', '1');
INSERT INTO `lend_list` VALUES ('2019040194', '10006', '1513032000', '2019-04-20', '2019-06-20', '2019-05-11', '1');
INSERT INTO `lend_list` VALUES ('2019040196', '10002', '1513032000', '2019-04-20', '2019-06-20', '2019-05-16', '1');
INSERT INTO `lend_list` VALUES ('2019040197', '50031', '1513032000', '2019-04-20', '2019-06-20', null, '0');
INSERT INTO `lend_list` VALUES ('2019040198', '10006', '1513032000', '2019-04-20', '2019-06-20', '2019-05-11', '1');
INSERT INTO `lend_list` VALUES ('2019040199', '10006', '1513032001', '2019-04-21', '2019-06-21', '2019-05-11', '1');
INSERT INTO `lend_list` VALUES ('2019040200', '10004', '1513032001', '2019-04-21', '2019-06-21', '2019-05-10', '1');
INSERT INTO `lend_list` VALUES ('2019040201', '10010', '1513032001', '2019-04-21', '2019-06-21', '2019-05-17', '0');
INSERT INTO `lend_list` VALUES ('2019040202', '50030', '1513032001', '2019-04-21', '2019-06-21', null, '0');
INSERT INTO `lend_list` VALUES ('2019040203', '10004', '1513032001', '2019-04-21', '2019-06-21', '2019-05-10', '1');
INSERT INTO `lend_list` VALUES ('2019040207', '50032', '1513032000', '2019-05-11', '2019-07-11', null, '0');
INSERT INTO `lend_list` VALUES ('2019040221', '10001', '1513032000', '2019-05-17', '2019-07-17', '2019-05-20', '0');
INSERT INTO `lend_list` VALUES ('2019040222', '10008', '1513032000', '2019-05-17', '2019-07-17', '2019-05-21', '0');
INSERT INTO `lend_list` VALUES ('2019040223', '10010', '1513032000', '2019-05-17', '2019-07-17', null, '0');
INSERT INTO `lend_list` VALUES ('2019040229', '50032', '1513032096', '2019-05-17', '2019-07-17', null, '0');

-- ----------------------------
-- Table structure for myshelf
-- ----------------------------
DROP TABLE IF EXISTS `myshelf`;
CREATE TABLE `myshelf` (
  `reader_id` int(11) NOT NULL,
  `book_id` int(20) NOT NULL,
  `name` varchar(50) default NULL,
  `stock` int(11) default NULL,
  `author` varchar(50) default NULL,
  `publish` varchar(30) default NULL,
  `ISBN` varchar(13) default NULL,
  `introduction` text,
  `language` varchar(10) default NULL,
  `price` decimal(10,2) default NULL,
  `pubdate` date default NULL,
  `class_id` int(11) default NULL,
  PRIMARY KEY  (`book_id`,`reader_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of myshelf
-- ----------------------------
INSERT INTO `myshelf` VALUES ('1513032000', '10006', '我承认我不曾历经沧桑', '4', '蒋方舟', '广西师范大学出版社', '9787549543519', '记录本身，即已是反抗。在书中，作者记录下对于成长与写作的反思，也记录下身旁被绑架的一代群像他们的童年早早消逝，青春期过早觉醒，他们过早地发现了成人世界的虚伪，更过早地被抛入一个充满竞争与争斗的世界。写作者并无能力和义务改变社会，但记录本身，即已是反抗。写下本身，即已是永恒。\r\n', '中文', '32.00', '2013-10-01', '9');
INSERT INTO `myshelf` VALUES ('1513032001', '10006', '我承认我不曾历经沧桑', '3', '蒋方舟', '广西师范大学出版社', '9787549543519', '记录本身，即已是反抗。在书中，作者记录下对于成长与写作的反思，也记录下身旁被绑架的一代群像他们的童年早早消逝，青春期过早觉醒，他们过早地发现了成人世界的虚伪，更过早地被抛入一个充满竞争与争斗的世界。写作者并无能力和义务改变社会，但记录本身，即已是反抗。写下本身，即已是永恒。\r\n', '中文', '32.00', '2013-10-01', '9');
INSERT INTO `myshelf` VALUES ('1513032096', '10006', '我承认我不曾历经沧桑', '2', '蒋方舟', '广西师范大学出版社', '9787549543519', '记录本身，即已是反抗。在书中，作者记录下对于成长与写作的反思，也记录下身旁被绑架的一代群像他们的童年早早消逝，青春期过早觉醒，他们过早地发现了成人世界的虚伪，更过早地被抛入一个充满竞争与争斗的世界。写作者并无能力和义务改变社会，但记录本身，即已是反抗。写下本身，即已是永恒。\r\n', '中文', '32.00', '2013-10-01', '9');
INSERT INTO `myshelf` VALUES ('1513032096', '10007', '细数那些叫思念的羊', '3', '张晓风', '青岛出版社', '9787555233077', '《细数那些叫思念的羊》中，张晓风从寻常小事切入，点绘人人拥有的幸福;俯视红尘，悲悯人世无常;探看大自然的奥妙，亦秀亦豪，从而深思人生种种际遇，出入大我与小我，用最悠游且积极的生命节奏与城市的律动同步。 \r\n本书是张晓风最新散文，精选50年文章菁华，超一半作品大陆首次出版，张晓风作序隆重推出。现世安稳，岁月静好，一个女人温柔了岁月，与这个世界握手言和。', '中文', '35.00', '2015-12-01', '9');
INSERT INTO `myshelf` VALUES ('1513032096', '10009', '设计模式之禅', '5', '秦小波', '机械工业出版社', '9787111437871', '本书是设计模式领域公认的3本经典著作之一，“极具趣味，容易理解，但讲解又极为严谨和透彻”是本书的写作风格和方法的*特点。第1版2010年出版，畅销至今，广受好评，是该领域的里程碑著作。深刻解读6大设计原则和28种设计模式的准确定义、应用方法和*实践，全方位比较各种同类模式之间的异同，详细讲解将不同的模式组合使用的方法。第2版在第1版的基础上有两方面的改进，一方面结合读者的意见和建议对原有内容中的瑕疵进行了修正和完善，另一方面增加了4种新的设计模式，希望这一版能为广大程序员们奉上一场更加完美的设计模式盛宴！', '中文', '89.00', '2014-03-01', '18');
INSERT INTO `myshelf` VALUES ('1513032096', '50031', '橘子不是唯一的水果', '2', '[英]珍妮特·温特森', '北京联合出版社', '9787559614711', '在英国一个偏远的小镇，女孩珍妮特生长在一个信奉福音派新教的家庭里。母亲是一个偏执虔诚的教徒，喜欢将事物分成敌友两派。恶魔，邻居，性是她的敌人，而上帝，她家的狗，夏洛特·勃朗特的小说是她的好友。在母亲的教育下，教会活动成了她日常生活的一切。随着她慢慢成长，珍妮特发现自己与小镇和母亲的偏执并不和谐。于是，当她爱上了另外一个女孩，并且这种超出常规之外的爱被发现后，两者的矛盾爆发了。这场她人生中最初的爱恋在教会和母亲的阻止下无疾而终，但让珍妮特理解了内心的渴望。她离开了小镇和母亲，同时也带着这份决绝进入了她的成年。', '英语', '49.00', '2018-04-01', '9');

-- ----------------------------
-- Table structure for reader_card
-- ----------------------------
DROP TABLE IF EXISTS `reader_card`;
CREATE TABLE `reader_card` (
  `reader_id` int(11) NOT NULL,
  `name` varchar(16) NOT NULL,
  `passwd` varchar(15) NOT NULL default '111111',
  `card_state` tinyint(4) default '1',
  PRIMARY KEY  (`reader_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reader_card
-- ----------------------------
INSERT INTO `reader_card` VALUES ('1513032000', '蔡菜', '111111', '1');
INSERT INTO `reader_card` VALUES ('1513032001', '沈深', '111111', '1');
INSERT INTO `reader_card` VALUES ('1513032090', '苏牧', '111111', '1');
INSERT INTO `reader_card` VALUES ('1513032096', '常晓琴', '111111', '1');
INSERT INTO `reader_card` VALUES ('1513032120', '张三', '111111', '1');
INSERT INTO `reader_card` VALUES ('1513032890', '王五', '111111', '1');

-- ----------------------------
-- Table structure for reader_info
-- ----------------------------
DROP TABLE IF EXISTS `reader_info`;
CREATE TABLE `reader_info` (
  `reader_id` int(11) NOT NULL,
  `name` varchar(16) NOT NULL,
  `level` int(8) NOT NULL,
  `sex` varchar(2) default NULL,
  `birth` date default NULL,
  `address` varchar(50) default NULL,
  `telcode` varchar(11) NOT NULL,
  PRIMARY KEY  (`reader_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reader_info
-- ----------------------------
INSERT INTO `reader_info` VALUES ('1513032000', '蔡菜', '3', '女', '1990-05-30', '江苏省南通市', '12334125454');
INSERT INTO `reader_info` VALUES ('1513032001', '沈深', '3', '男', '1988-08-29', '江苏省南京市', '12345678904');
INSERT INTO `reader_info` VALUES ('1513032090', '苏牧', '1', '男', '1996-12-30', '上海市', '12345678901');
INSERT INTO `reader_info` VALUES ('1513032096', '常晓琴', '1', '女', '1997-12-26', '狼山镇街道啬园路9号（南通大学主校区）', '17805058340');
INSERT INTO `reader_info` VALUES ('1513032120', '张三', '2', '男', '1996-10-05', '江苏省苏州市', '12345678902');
INSERT INTO `reader_info` VALUES ('1513032890', '王五', '2', '男', '1995-03-19', '江苏省常州市', '12434235346');
