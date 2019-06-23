/*
 Navicat Premium Data Transfer

 Source Server         : Mooc
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : 119.23.63.134:3306
 Source Schema         : mooc

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 23/06/2019 10:07:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`
(
    `ID`           bigint(20) NOT NULL AUTO_INCREMENT,
    `USER_ID`      bigint(20) NOT NULL,
    `KIND_NAME`    varchar(15) CHARACTER SET utf8  DEFAULT NULL,
    `TITLE`        varchar(63) CHARACTER SET utf8  DEFAULT '',
    `INTRODUCTION` varchar(255) CHARACTER SET utf8 DEFAULT '',
    `LIKE`         int(11)                         DEFAULT '0',
    `STAR`         int(11)                         DEFAULT '0',
    `HEAD_URL`     varchar(63) CHARACTER SET utf8  DEFAULT 'default',
    `CREATED_TIME` timestamp  NOT NULL             DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`ID`) USING BTREE,
    KEY `user_index` (`USER_ID`) USING BTREE,
    KEY `kind_index` (`KIND_NAME`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 29
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of course
-- ----------------------------
BEGIN;
INSERT INTO `course`
VALUES (1, 1, 'C++', 'C++精通指南', '教你3天速成C++', 2, 2, 'M00/00/00/rBExSlz_uuGALrS9AAFgG0NhN1s731.png',
        '2019-06-11 22:29:53');
INSERT INTO `course`
VALUES (2, 6, 'Java', 'Java核心进阶',
        'Java不仅是一门程序设计语言，而且发展为一个庞大的生态圈。Java被广泛应用到企业级系统、手机、桌面软件等开发，是就业市场最急需的技术。本课程讲授Java技术进阶部分，涉及多方位应用，并通过大量程序进行佐证讲解。学生学习课程后，可完成常见的功能应用开发。本课程是Java/EE、安卓等的先导课程。',
        2, 0, 'M00/00/00/rBExSlz_w1KAXlNXAACvDan9jf4459.png', '2019-06-11 23:05:54');
INSERT INTO `course`
VALUES (3, 6, 'Java', 'Java核心技术',
        'Java是一门程序设计语言，长期位居编程语言排行榜TIOBE的首位。从1995年面世以来，已经被广泛应用到企业级系统、手机、桌面软件等开发。据Oracle统计，已有超过30亿电子设备运行Java程序。本课程主要讲授Java核心技术原理，并通过大量程序进行佐证讲解。本课程也是Java EE、Android等技术的先导课程。',
        1, 0, 'M00/00/00/rBExSl0ATD-AQAOPAACvDan9jf4366.png', '2019-06-12 08:50:07');
INSERT INTO `course`
VALUES (4, 6, 'Java', 'Java面向对象程序设计',
        '本课程由Nebular计算机工程学院智慧教育团队倾情打造，在讲授Java面向对象编程知识的同时，努力培养学生的计算思维和创新思维，让同学们在快乐中学习，在学习中实践，在实践中进步，在进步中成才！本课程已经开设3期，累计选课人数近2.5万，受到学习者的普遍欢迎。欢迎更多学习者参加第4期学习！',
        0, 1, 'M00/00/00/rBExSl0ATPiAf3S0AAFeSlbENKM265.png', '2019-06-12 08:53:12');
INSERT INTO `course`
VALUES (5, 7, 'Java', 'Java一天速成指南', '教你如何一天成为Java大神', 0, 0, 'M00/00/00/rBExSl0AVciAYTw1ABH2XVXhAxQ394.jpg',
        '2019-06-12 09:30:48');
INSERT INTO `course`
VALUES (6, 7, 'Java', 'Java大神宝典', 'Java大神宝典', 1, 0, 'M00/00/00/rBExSl0AVj-ALzrKAEomjjGj58g735.jpg',
        '2019-06-12 09:32:47');
INSERT INTO `course`
VALUES (7, 6, 'Java', 'Java程序设计',
        'Java是一种优秀的面向对象的语言，具有跨平台性、用途广泛、容易学习等特点，众多的开源项目都是用Java实现的，可以说Java是程序设计必学的语言。这门课程掌握Java语言、面向对象的特点，掌握Java在多线程、图形用户界面、网络等方面的应用，同时要养成良好的编程习惯，能够编写有一定规模的应用程序。',
        2, 1, 'M00/00/00/rBExSl0AWgaANkIrAAHQBWkcuY0911.png', '2019-06-12 09:48:54');
INSERT INTO `course`
VALUES (8, 6, 'C', 'C语言程序设计精髓',
        '学习程序设计是一件充满挑战、更充满乐趣的事情，然而学习之初，它之所以给你枯燥乏味的感觉，那是因为没有发掘出它的趣味来。本课程力图用最简明的语言、最典型的实例及最通俗的解释将这种趣味性挖掘出来，带给你全新的学习体验，和你一起欣赏C语言之美，领悟C语言之妙，体会学习C语言之无穷乐趣。',
        1, 0, 'M00/00/00/rBExSl0AXT-AZQDKAAVY_U3U3N4639.png', '2019-06-12 10:02:39');
INSERT INTO `course`
VALUES (9, 6, 'C', 'C语言程序设计(上) 国家',
        'Kenneeth Lane Thompson为了方便的玩自己编写的游戏程序，用汇编语言写了UNIX操作系统，又为了更方便的写这个UNIX而创造了C。创造在游戏中产生，业绩在创造中成就！从此，C便一发不可收拾，独领风骚！当计算遇上了困惑，当编程选择了C，当面向问题的计算机程序设计加入了优秀团队，就是这门课程！',
        1, 1, 'M00/00/00/rBExSl0AXcSAClPMAAGgUVfAKR4736.png', '2019-06-12 10:04:52');
INSERT INTO `course`
VALUES (10, 7, 'Java', 'Java开发指南',
        '本课程以提高学生Java开发能力为主要目标。通过课程教学，使学生全面了解Java开发的基础技术；熟悉业界广泛使用的开发工具和服务器产品；掌握应用Java技术开发Web应用程序的技能。\n课程的目标是学Java以致用，希望通过这门课让同学们在学完Java语言之后能够了解如何用Java语言开发Web应用程序。',
        1, 1, 'M00/00/00/rBExSl0AXfOAaGn9AB7Nc2_mTGw097.jpg', '2019-06-12 10:05:39');
INSERT INTO `course`
VALUES (11, 6, 'C', 'C语言程序设计',
        'C语言是古老而长青的编程语言，它具备了现代程序设计的基础要求，它的语法是很多其他编程语言的基础，在系统程序、嵌入式系统等领域依然是无可替代的编程语言，在各类编程语言排行榜上常年占据前两名的位置。 本课程是后续的操作系统、编译原理、体系结构等课程的基石。',
        2, 2, 'M00/00/00/rBExSl0AXpOAH3TOAAAtrVPH4ig677.png', '2019-06-12 10:08:19');
INSERT INTO `course`
VALUES (12, 6, 'C', 'C编程方法学',
        'C语言在各类编程语言排行榜上一直位居前两位，历史悠久，但依然生命力旺盛，它是很多其他编程语言的基础，在系统程序、嵌入式系统、物联网应用等领域依然是无可替代的。本课程是零基础的编程入门课，是后续学习其它计算机课程的基础。学习它，掌握它，你将发现计算机编程原来也是一件很有趣的事情。',
        1, 0, 'M00/00/00/rBExSl0AX3yAJi1TAAERNW_9tKU310.png', '2019-06-12 10:12:12');
INSERT INTO `course`
VALUES (13, 6, 'C', 'C语言编程进阶',
        'C语言有许多独特的地方。从1970年代诞生起，它的历史使命就是编写系统程序，它被设计成非常贴近底层、贴近硬件。它的很多独特的设计都是为了能够准确地反映硬件操作。但是历史又和C语言开了一个玩笑，它被当作了第一个通用型语言，曾经广泛地用于各种场合，解决各种问题。它有哪些神秘之处呢？',
        0, 0, 'M00/00/00/rBExSl0AX-2AE1NiAAFaxq3BPn4444.png', '2019-06-12 10:14:05');
INSERT INTO `course`
VALUES (14, 6, 'Python', 'python语言程序设计',
        '计算机是运算工具，更是创新平台，高效有趣地利用计算机需要更简洁实用的编程语言。Python简洁却强大、简单却专业，它是当今世界最受欢迎的编程语言，学好它终身受用。请跟随我们，学习并掌握Python语言，一起动起来，站在风口、享受创新！',
        0, 0, 'M00/00/00/rBExSl0AYPeAICZ8AACnYy1FkDY624.png', '2019-06-12 10:18:31');
INSERT INTO `course`
VALUES (15, 6, 'Python', '零基础学python语言',
        '计算机是计算工具，更是创新平台，高效有趣地利用计算机需要更简洁实用的编程语言。Python语言已经成为当今世界最受欢迎的编程语言。本课程面向编程零基础同学，以兴趣为驱动，学习并实践Python语言，“轻松编程、享受创新”。',
        2, 1, 'M00/00/00/rBExSl0AYaWAQBNUAABodSefUsM852.png', '2019-06-12 10:21:25');
INSERT INTO `course`
VALUES (16, 6, 'Python', 'python网络爬虫与信息提取',
        '互联网是功能集合，更是存储空间；海量数据孕育巨大价值，数据采集需求迫切。网络爬虫已经成为自动获取互联网数据的主要方式，数据就在那里，它是你的吗？请跟随我们，5周时间，掌握利用Python爬取网络数据并提取信息的\"小\"本领。还等什么？快写个爬虫探索世界吧！',
        1, 1, 'M00/00/00/rBExSl0AYleAT9ieAAE3_Z3zHhQ630.png', '2019-06-12 10:24:23');
INSERT INTO `course`
VALUES (17, 6, 'Python', 'python语言基础与应用',
        '本课基于主讲教师在北京大学讲授数据结构与算法课（Python版）的多年教学实践经验，面向零编程基础的大学生和社会公众，全面讲授Python语言基础，培养学生计算思维的能力，并讲解Python语言中经典的扩展模块，让学生能用Python语言解决各种常见问题。',
        1, 1, 'M00/00/00/rBExSl0AYrKALO7pAAEoxyNcc3k752.png', '2019-06-12 10:25:54');
INSERT INTO `course`
VALUES (18, 6, 'Python', '用python玩转数据',
        '欢迎来到《用Python玩转数据》，这是第7次开课，大壮老师会尽量用非计算机专业小伙伴们能听懂的方式讲述如何利用简单易学优雅的Python便捷地获取、表示、分析和展示数据，让大家愉快地学习用Python玩转数据。本次课程将对以往小伙伴要求的部分内容进行扩展并提供更多基础题和实用项目供学习。',
        2, 1, 'M00/00/00/rBExSl0AYwyAAs5LAAHU6XuYQjA845.png', '2019-06-12 10:27:24');
INSERT INTO `course`
VALUES (19, 6, 'Python', 'python云端系统开发入门',
        '分久必合、合久必分，移动互联网应用进入了一个云计算时代，云端掌控一切，云端技术范畴庞大繁杂，多重技术交织发展，貌似神秘，一起来揭开云端的神秘面纱吧！ ——“弹指之间·享受创新”，通过4周学习，你将掌握利用Python语言设计和开发简易云后端系统的全栈能力。',
        1, 2, 'M00/00/00/rBExSl0AY4WATrYyAAEmdNxFEqw432.png', '2019-06-12 10:29:25');
INSERT INTO `course`
VALUES (20, 8, 'C', 'C语言入门', 'C语言是一门面向过程、抽象化的通用程序设计语言，广泛应用于底层开发。本课程会由浅入深地教你了解和学习C语言。', 0, 0,
        'M00/00/00/rBExSl0AZLaAOyT2ABLgRR8Fx5I243.png', '2019-06-12 10:34:30');
INSERT INTO `course`
VALUES (21, 6, 'C#', 'C#程序设计',
        '如果你以前学过程序设计，但就是编不出一个像“背单词”、“2048”、“酒店管理”这样的实用程序，那就请你加入到《C#程序设计》课程中来吧。在这里，你不仅可以学到面向对象的语言和思想，还会学到如何编写图形化的用户界面、文本处理、图形图像、网络访问、多媒体、数据库应用等各类应用程序。\n',
        2, 1, 'M00/00/00/rBExSl0AZauAZ5YlAAFHu1azz8c974.png', '2019-06-12 10:38:35');
INSERT INTO `course`
VALUES (22, 6, 'C#', 'C#程序设计',
        '越来越数字化的世界决定了我们“要么编程，要么被编程”，不想被阿尔法狗打败，那就让我们成为它的创造者！颜值与身材并存的C#语言无疑是零起步编程的一把利器。它完全面向对象，同时“简单、现代、通用”，能满足多种平台的开发需求。来吧，一起用C#码出我们的魅力人生！\n',
        2, 2, 'M00/00/00/rBExSl0AZhuAYNm7AAFL8YT5mu8256.png', '2019-06-12 10:40:27');
INSERT INTO `course`
VALUES (23, 6, 'C#', 'C#程序设计',
        '在未来，编程将会成为每个人的必会技能，就如同每个人都会使用手机一样。C#语言是目前为止，最适合开发Windows程序的语言。开发工具Visual Studio是世界上，乃至全宇宙最好用的编译器和开发平台。本门课程就是讲述如何使用c#简单高效的编写程序。\n',
        1, 1, 'M00/00/00/rBExSl0AZpOANwjiAAG3UBLaEqI838.png', '2019-06-12 10:42:27');
INSERT INTO `course`
VALUES (24, 6, 'C#', '面向对象程序设计',
        '面向对象程序设计具有封装、继承、多态等优点，能显著提高程序的可重用性和可扩展性。本课程按程序设计基础、面向对象概念、实用化编程、综合应用开发等四个阶段逐层递进，从知识源头梳理概念、从应用程序架构解析实用技术，用C#语言阐述机理，基于.NET框架实现功能，概念与实用齐步，严谨与趣味同在。',
        2, 1, 'M00/00/00/rBExSl0AZ0qAfZ_yAASE3UCcNnM859.png', '2019-06-12 10:45:30');
INSERT INTO `course`
VALUES (25, 7, 'Java', 'Java 入门指南', 'Java 入门指南', 1, 1, 'M00/00/00/rBExSl0AaPWAOo3QAABYCTqswWk297.jpg',
        '2019-06-12 10:52:37');
INSERT INTO `course`
VALUES (26, 7, 'C', 'C 入门教学', 'C讲义', 2, 2, 'M00/00/00/rBExSl0AaVmAD_GZAABKKampy9w397.jpg', '2019-06-12 10:54:17');
INSERT INTO `course`
VALUES (27, 6, 'VB', 'VB程序设计基础',
        '程序设计基础（VB）是面向大学生开设的第一门程序设计语言类课程，在高等教学改革“重基础，强能力，突创新”的新形势下，我们致力于优化课程体系，重组教学内容，缩短课堂教学，扩大实习教学，为新时代的大学生提供优质的网络在线教育共享服务。',
        2, 1, 'M00/00/00/rBExSl0AhjGAZj0uAAbBNXwodlU884.png', '2019-06-12 12:57:21');
INSERT INTO `course`
VALUES (28, 2, 'Java', 'Java核心教程', 'Java核心教程', 0, 0, 'M00/00/00/rBExSl0OYBmAA2fiAAAdd3HqFqU501.png',
        '2019-06-23 01:06:33');
COMMIT;

-- ----------------------------
-- Table structure for course_chapter
-- ----------------------------
DROP TABLE IF EXISTS `course_chapter`;
CREATE TABLE `course_chapter`
(
    `ID`           bigint(20) NOT NULL AUTO_INCREMENT,
    `COURSE_ID`    bigint(20) NOT NULL,
    `TITLE`        varchar(63) CHARACTER SET utf8 DEFAULT NULL,
    `CREATED_TIME` timestamp  NOT NULL            DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`ID`) USING BTREE,
    KEY `course_index` (`COURSE_ID`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 33
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of course_chapter
-- ----------------------------
BEGIN;
INSERT INTO `course_chapter`
VALUES (1, 1, '第一章', '2019-06-11 22:29:53');
INSERT INTO `course_chapter`
VALUES (2, 2, '第一章', '2019-06-11 23:05:54');
INSERT INTO `course_chapter`
VALUES (3, 3, '第一章', '2019-06-12 08:50:07');
INSERT INTO `course_chapter`
VALUES (4, 4, '第一章', '2019-06-12 08:53:12');
INSERT INTO `course_chapter`
VALUES (5, 5, '第一章', '2019-06-12 09:30:49');
INSERT INTO `course_chapter`
VALUES (6, 5, '第二章', '2019-06-12 09:30:49');
INSERT INTO `course_chapter`
VALUES (7, 6, '第一章', '2019-06-12 09:32:47');
INSERT INTO `course_chapter`
VALUES (8, 7, 'Java语言与面向对象的程序设计', '2019-06-12 09:48:54');
INSERT INTO `course_chapter`
VALUES (9, 8, '第一章', '2019-06-12 10:02:39');
INSERT INTO `course_chapter`
VALUES (10, 9, '第一章', '2019-06-12 10:04:52');
INSERT INTO `course_chapter`
VALUES (11, 10, '第一章', '2019-06-12 10:05:39');
INSERT INTO `course_chapter`
VALUES (12, 11, '第一章', '2019-06-12 10:08:19');
INSERT INTO `course_chapter`
VALUES (13, 12, '第一章', '2019-06-12 10:12:12');
INSERT INTO `course_chapter`
VALUES (14, 13, '第一章', '2019-06-12 10:14:05');
INSERT INTO `course_chapter`
VALUES (15, 14, '第一章', '2019-06-12 10:18:31');
INSERT INTO `course_chapter`
VALUES (16, 15, '第一章', '2019-06-12 10:21:25');
INSERT INTO `course_chapter`
VALUES (17, 16, '第一章', '2019-06-12 10:24:23');
INSERT INTO `course_chapter`
VALUES (18, 17, '第一章', '2019-06-12 10:25:54');
INSERT INTO `course_chapter`
VALUES (19, 18, '第一章', '2019-06-12 10:27:24');
INSERT INTO `course_chapter`
VALUES (20, 19, '第一章', '2019-06-12 10:29:25');
INSERT INTO `course_chapter`
VALUES (21, 20, '第一章 为什么要学C语言', '2019-06-12 10:34:30');
INSERT INTO `course_chapter`
VALUES (22, 20, '第二章 认识C语言', '2019-06-12 10:34:31');
INSERT INTO `course_chapter`
VALUES (23, 20, '第三章 C语言的结构', '2019-06-12 10:34:31');
INSERT INTO `course_chapter`
VALUES (24, 20, '第四章 数组', '2019-06-12 10:34:31');
INSERT INTO `course_chapter`
VALUES (25, 21, '第一章', '2019-06-12 10:38:35');
INSERT INTO `course_chapter`
VALUES (26, 22, '第一章', '2019-06-12 10:40:27');
INSERT INTO `course_chapter`
VALUES (27, 23, '第一章', '2019-06-12 10:42:27');
INSERT INTO `course_chapter`
VALUES (28, 24, '第一章', '2019-06-12 10:45:30');
INSERT INTO `course_chapter`
VALUES (29, 25, '第一章', '2019-06-12 10:52:37');
INSERT INTO `course_chapter`
VALUES (30, 26, '第一章', '2019-06-12 10:54:17');
INSERT INTO `course_chapter`
VALUES (31, 27, '第一章', '2019-06-12 12:57:21');
INSERT INTO `course_chapter`
VALUES (32, 28, '第一章', '2019-06-23 01:06:33');
COMMIT;

-- ----------------------------
-- Table structure for course_comment
-- ----------------------------
DROP TABLE IF EXISTS `course_comment`;
CREATE TABLE `course_comment`
(
    `ID`           bigint(20)              NOT NULL AUTO_INCREMENT,
    `USER_ID`      bigint(20)              NOT NULL,
    `COURSE_ID`    bigint(20)              NOT NULL,
    `CONTENT`      text CHARACTER SET utf8 NOT NULL,
    `STAR`         int(11)                          DEFAULT '0',
    `CREATED_TIME` timestamp               NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`ID`) USING BTREE,
    KEY `user_course_index` (`USER_ID`, `COURSE_ID`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of course_comment
-- ----------------------------
BEGIN;
INSERT INTO `course_comment`
VALUES (1, 1, 26, '我请你们', 0, '2019-06-14 09:53:13');
INSERT INTO `course_comment`
VALUES (2, 8, 27, 'ef', 0, '2019-06-22 20:00:54');
INSERT INTO `course_comment`
VALUES (3, 8, 27, 'feef', 0, '2019-06-22 20:01:01');
COMMIT;

-- ----------------------------
-- Table structure for course_comment_star
-- ----------------------------
DROP TABLE IF EXISTS `course_comment_star`;
CREATE TABLE `course_comment_star`
(
    `ID`           bigint(20) NOT NULL AUTO_INCREMENT,
    `USER_ID`      bigint(20) NOT NULL,
    `COMMENT_ID`   bigint(20) NOT NULL,
    `CREATED_TIME` timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`ID`) USING BTREE,
    KEY `USER_COMMIT_ID` (`USER_ID`, `COMMENT_ID`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for course_like
-- ----------------------------
DROP TABLE IF EXISTS `course_like`;
CREATE TABLE `course_like`
(
    `ID`           bigint(20) NOT NULL AUTO_INCREMENT,
    `USER_ID`      bigint(20) NOT NULL,
    `COURSE_ID`    bigint(20) NOT NULL,
    `CREATED_TIME` timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`ID`) USING BTREE,
    KEY `USER_COURSE_ID` (`USER_ID`, `COURSE_ID`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 35
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of course_like
-- ----------------------------
BEGIN;
INSERT INTO `course_like`
VALUES (1, 5, 1, '2019-06-11 22:49:48');
INSERT INTO `course_like`
VALUES (2, 6, 2, '2019-06-12 08:58:49');
INSERT INTO `course_like`
VALUES (3, 1, 11, '2019-06-12 10:12:16');
INSERT INTO `course_like`
VALUES (4, 1, 10, '2019-06-12 10:12:27');
INSERT INTO `course_like`
VALUES (5, 1, 7, '2019-06-12 10:13:04');
INSERT INTO `course_like`
VALUES (6, 6, 9, '2019-06-12 10:22:30');
INSERT INTO `course_like`
VALUES (7, 8, 7, '2019-06-12 12:51:28');
INSERT INTO `course_like`
VALUES (8, 8, 26, '2019-06-12 12:52:08');
INSERT INTO `course_like`
VALUES (9, 9, 18, '2019-06-12 13:20:21');
INSERT INTO `course_like`
VALUES (10, 9, 15, '2019-06-12 13:20:49');
INSERT INTO `course_like`
VALUES (11, 9, 11, '2019-06-12 13:27:26');
INSERT INTO `course_like`
VALUES (12, 8, 27, '2019-06-12 14:14:27');
INSERT INTO `course_like`
VALUES (13, 8, 25, '2019-06-12 14:14:36');
INSERT INTO `course_like`
VALUES (14, 8, 24, '2019-06-12 14:14:40');
INSERT INTO `course_like`
VALUES (15, 8, 23, '2019-06-12 14:14:51');
INSERT INTO `course_like`
VALUES (16, 8, 22, '2019-06-12 14:14:56');
INSERT INTO `course_like`
VALUES (17, 8, 21, '2019-06-12 14:15:00');
INSERT INTO `course_like`
VALUES (18, 8, 19, '2019-06-12 14:15:05');
INSERT INTO `course_like`
VALUES (19, 8, 18, '2019-06-12 14:15:09');
INSERT INTO `course_like`
VALUES (20, 8, 17, '2019-06-12 14:15:18');
INSERT INTO `course_like`
VALUES (21, 8, 16, '2019-06-12 14:15:27');
INSERT INTO `course_like`
VALUES (23, 8, 15, '2019-06-12 14:15:36');
INSERT INTO `course_like`
VALUES (24, 8, 12, '2019-06-12 14:15:40');
INSERT INTO `course_like`
VALUES (25, 8, 8, '2019-06-12 14:15:46');
INSERT INTO `course_like`
VALUES (26, 8, 3, '2019-06-12 14:15:51');
INSERT INTO `course_like`
VALUES (27, 8, 1, '2019-06-12 14:15:54');
INSERT INTO `course_like`
VALUES (28, 8, 2, '2019-06-12 14:16:08');
INSERT INTO `course_like`
VALUES (29, 2, 27, '2019-06-12 14:18:10');
INSERT INTO `course_like`
VALUES (30, 2, 24, '2019-06-12 14:18:13');
INSERT INTO `course_like`
VALUES (31, 2, 22, '2019-06-12 14:18:16');
INSERT INTO `course_like`
VALUES (32, 2, 21, '2019-06-12 14:18:19');
INSERT INTO `course_like`
VALUES (33, 3, 6, '2019-06-13 15:47:04');
INSERT INTO `course_like`
VALUES (34, 1, 26, '2019-06-14 09:53:01');
COMMIT;

-- ----------------------------
-- Table structure for course_score
-- ----------------------------
DROP TABLE IF EXISTS `course_score`;
CREATE TABLE `course_score`
(
    `ID`           bigint(20) NOT NULL AUTO_INCREMENT,
    `COURSE_ID`    bigint(20) NOT NULL,
    `USER_ID`      bigint(20) NOT NULL,
    `SCORE`        int(11)    NOT NULL DEFAULT '0',
    `CREATED_TIME` timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`ID`) USING BTREE,
    KEY `course_user_index` (`COURSE_ID`, `USER_ID`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 53
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of course_score
-- ----------------------------
BEGIN;
INSERT INTO `course_score`
VALUES (1, 1, 6, 3, '2019-06-11 23:01:52');
INSERT INTO `course_score`
VALUES (2, 2, 1, 3, '2019-06-11 23:40:41');
INSERT INTO `course_score`
VALUES (3, 4, 6, 7, '2019-06-12 08:58:32');
INSERT INTO `course_score`
VALUES (4, 3, 6, 3, '2019-06-12 08:58:38');
INSERT INTO `course_score`
VALUES (5, 2, 6, 8, '2019-06-12 08:58:45');
INSERT INTO `course_score`
VALUES (6, 7, 1, 12, '2019-06-12 10:02:54');
INSERT INTO `course_score`
VALUES (7, 11, 1, 12, '2019-06-12 10:12:14');
INSERT INTO `course_score`
VALUES (8, 10, 1, 12, '2019-06-12 10:12:22');
INSERT INTO `course_score`
VALUES (9, 12, 1, 3, '2019-06-12 10:12:46');
INSERT INTO `course_score`
VALUES (10, 13, 6, 3, '2019-06-12 10:21:52');
INSERT INTO `course_score`
VALUES (11, 11, 6, 7, '2019-06-12 10:22:25');
INSERT INTO `course_score`
VALUES (12, 9, 6, 8, '2019-06-12 10:22:29');
INSERT INTO `course_score`
VALUES (13, 20, 8, 3, '2019-06-12 10:34:38');
INSERT INTO `course_score`
VALUES (14, 19, 8, 12, '2019-06-12 10:34:57');
INSERT INTO `course_score`
VALUES (15, 7, 8, 8, '2019-06-12 12:51:24');
INSERT INTO `course_score`
VALUES (16, 26, 8, 12, '2019-06-12 12:52:06');
INSERT INTO `course_score`
VALUES (17, 8, 6, 3, '2019-06-12 13:12:10');
INSERT INTO `course_score`
VALUES (18, 19, 9, 7, '2019-06-12 13:20:15');
INSERT INTO `course_score`
VALUES (19, 18, 9, 8, '2019-06-12 13:20:20');
INSERT INTO `course_score`
VALUES (20, 16, 9, 3, '2019-06-12 13:20:24');
INSERT INTO `course_score`
VALUES (21, 14, 9, 3, '2019-06-12 13:20:36');
INSERT INTO `course_score`
VALUES (22, 15, 9, 8, '2019-06-12 13:20:46');
INSERT INTO `course_score`
VALUES (23, 22, 9, 7, '2019-06-12 13:20:56');
INSERT INTO `course_score`
VALUES (24, 21, 9, 3, '2019-06-12 13:21:01');
INSERT INTO `course_score`
VALUES (25, 11, 9, 8, '2019-06-12 13:27:24');
INSERT INTO `course_score`
VALUES (26, 27, 8, 12, '2019-06-12 14:14:25');
INSERT INTO `course_score`
VALUES (27, 25, 8, 12, '2019-06-12 14:14:34');
INSERT INTO `course_score`
VALUES (28, 24, 8, 12, '2019-06-12 14:14:38');
INSERT INTO `course_score`
VALUES (29, 23, 8, 12, '2019-06-12 14:14:49');
INSERT INTO `course_score`
VALUES (30, 22, 8, 12, '2019-06-12 14:14:54');
INSERT INTO `course_score`
VALUES (31, 21, 8, 12, '2019-06-12 14:14:58');
INSERT INTO `course_score`
VALUES (32, 18, 8, 12, '2019-06-12 14:15:08');
INSERT INTO `course_score`
VALUES (33, 17, 8, 12, '2019-06-12 14:15:17');
INSERT INTO `course_score`
VALUES (34, 16, 8, 12, '2019-06-12 14:15:26');
INSERT INTO `course_score`
VALUES (35, 15, 8, 12, '2019-06-12 14:15:30');
INSERT INTO `course_score`
VALUES (36, 12, 8, 8, '2019-06-12 14:15:39');
INSERT INTO `course_score`
VALUES (37, 9, 8, 7, '2019-06-12 14:15:42');
INSERT INTO `course_score`
VALUES (38, 8, 8, 8, '2019-06-12 14:15:45');
INSERT INTO `course_score`
VALUES (39, 3, 8, 8, '2019-06-12 14:15:50');
INSERT INTO `course_score`
VALUES (40, 1, 8, 8, '2019-06-12 14:15:53');
INSERT INTO `course_score`
VALUES (41, 2, 8, 8, '2019-06-12 14:16:07');
INSERT INTO `course_score`
VALUES (42, 27, 2, 8, '2019-06-12 14:18:08');
INSERT INTO `course_score`
VALUES (43, 24, 2, 8, '2019-06-12 14:18:12');
INSERT INTO `course_score`
VALUES (44, 22, 2, 8, '2019-06-12 14:18:15');
INSERT INTO `course_score`
VALUES (45, 21, 2, 8, '2019-06-12 14:18:18');
INSERT INTO `course_score`
VALUES (46, 9, 7, 3, '2019-06-12 15:43:41');
INSERT INTO `course_score`
VALUES (47, 23, 3, 3, '2019-06-13 15:46:25');
INSERT INTO `course_score`
VALUES (48, 1, 3, 7, '2019-06-13 15:46:50');
INSERT INTO `course_score`
VALUES (49, 6, 3, 8, '2019-06-13 15:47:02');
INSERT INTO `course_score`
VALUES (50, 26, 1, 12, '2019-06-14 09:52:51');
INSERT INTO `course_score`
VALUES (51, 18, 1, 3, '2019-06-22 19:30:37');
INSERT INTO `course_score`
VALUES (52, 28, 2, 3, '2019-06-23 01:06:45');
COMMIT;

-- ----------------------------
-- Table structure for course_section
-- ----------------------------
DROP TABLE IF EXISTS `course_section`;
CREATE TABLE `course_section`
(
    `ID`           bigint(20) NOT NULL AUTO_INCREMENT,
    `CHAPTER_ID`   bigint(20) NOT NULL,
    `TITLE`        varchar(63) CHARACTER SET utf8  DEFAULT NULL,
    `INTRODUCTION` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
    `VIDEO_URL`    varchar(63) CHARACTER SET utf8  DEFAULT NULL,
    `CREATED_TIME` timestamp  NOT NULL             DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`ID`) USING BTREE,
    KEY `chapter_index` (`CHAPTER_ID`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 48
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of course_section
-- ----------------------------
BEGIN;
INSERT INTO `course_section`
VALUES (1, 1, '第一节', 'C++是什么', 'M00/00/00/rBExSlz_upyARBECAAdan9B3T1Y233.mp4', '2019-06-11 22:29:53');
INSERT INTO `course_section`
VALUES (2, 2, '第一节', '导学', 'M00/00/00/rBExSlz_wzuAT6iKAAlwm9RH6aA875.mp4', '2019-06-11 23:05:54');
INSERT INTO `course_section`
VALUES (3, 3, '第一节', '导学', 'M00/00/00/rBExSlz_wpeANJwMAA2WWsVUsX0853.mp4', '2019-06-12 08:50:07');
INSERT INTO `course_section`
VALUES (4, 4, 'Java概述', '1.了解Java程序的工作机制与开发步骤；2.掌握在TextPad环境中用JDK命令编辑与运行Java程序的方法；',
        'M00/00/00/rBExSlz_w-SAaxTaAAl2l5bbj2w376.mp4', '2019-06-12 08:53:12');
INSERT INTO `course_section`
VALUES (5, 5, '葵花宝典', '若要成功，必先XX', 'M00/00/00/rBExSl0AVUuAEW0qAA2WWsVUsX0839.mp4', '2019-06-12 09:30:49');
INSERT INTO `course_section`
VALUES (6, 6, '第一节', '导学', 'M00/00/00/rBExSl0AVVaAFuZmAAlwm9RH6aA794.mp4', '2019-06-12 09:30:49');
INSERT INTO `course_section`
VALUES (7, 6, '第二节', '导学2', 'M00/00/00/rBExSl0AVVmAKm4iAAl2l5bbj2w333.mp4', '2019-06-12 09:30:49');
INSERT INTO `course_section`
VALUES (8, 7, '第一节', 'Java大神宝典', 'M00/00/00/rBExSl0AVUuAEW0qAA2WWsVUsX0839.mp4', '2019-06-12 09:32:47');
INSERT INTO `course_section`
VALUES (9, 8, 'Java语言的历史与特点', '导学', 'M00/00/00/rBExSl0AWFOAfb0HABCHQ5UGvwI143.mp4', '2019-06-12 09:48:54');
INSERT INTO `course_section`
VALUES (10, 9, '第一节', '导学', 'M00/00/00/rBExSl0AXN6AdeCKAAusIQa_HPo837.mp4', '2019-06-12 10:02:39');
INSERT INTO `course_section`
VALUES (11, 10, '第一节', '导学', 'M00/00/00/rBExSl0AXVKAMQ-rAAqZxIJC28o732.mp4', '2019-06-12 10:04:52');
INSERT INTO `course_section`
VALUES (12, 11, '第一节', '理解注释与注解的区别应用注释自动生成自己的帮助文档了解注解在代码分析工具、开发工具和部署工具中的应用。',
        'M00/00/00/rBExSl0AVUuAEW0qAA2WWsVUsX0839.mp4', '2019-06-12 10:05:39');
INSERT INTO `course_section`
VALUES (13, 12, '第一节', '导学', 'M00/00/00/rBExSl0AXVyAG2mwAA82jQSL53A454.mp4', '2019-06-12 10:08:19');
INSERT INTO `course_section`
VALUES (14, 13, '第一节', '导学', 'M00/00/00/rBExSl0AXWCAK2msAApQwPPYluY265.mp4', '2019-06-12 10:12:12');
INSERT INTO `course_section`
VALUES (15, 14, '第一节', '导学', 'M00/00/00/rBExSl0AX6KAeY53ABN_ZHiQDP4566.mp4', '2019-06-12 10:14:05');
INSERT INTO `course_section`
VALUES (16, 15, '第一节', '导学', 'M00/00/00/rBExSl0AYMGAHAbjABCWAm8AxG8515.mp4', '2019-06-12 10:18:31');
INSERT INTO `course_section`
VALUES (17, 16, '第一节', '导学', 'M00/00/00/rBExSl0AYQ6AIq5xAA3JIM8K06I707.mp4', '2019-06-12 10:21:25');
INSERT INTO `course_section`
VALUES (18, 17, '第一节', '导学', 'M00/00/00/rBExSl0AYRKAfcKIAAnIxmWOR0Q922.mp4', '2019-06-12 10:24:23');
INSERT INTO `course_section`
VALUES (19, 18, '第一节', '导学', 'M00/00/00/rBExSl0AYRWAYW2xAA2SaetI57o837.mp4', '2019-06-12 10:25:54');
INSERT INTO `course_section`
VALUES (20, 19, '第一节', '导学', 'M00/00/00/rBExSl0AYRiAccawAAep6ymuUKQ684.mp4', '2019-06-12 10:27:24');
INSERT INTO `course_section`
VALUES (21, 20, '第一节', '导学', 'M00/00/00/rBExSl0AY0WADoNUAAxWLW-kenM339.mp4', '2019-06-12 10:29:25');
INSERT INTO `course_section`
VALUES (22, 21, '第一节 计算机语言的发展', '介绍各种语言的特性以及C语言的优点', 'M00/00/00/rBExSl0AXJGALXSrAAusIQa_HPo562.mp4',
        '2019-06-12 10:34:30');
INSERT INTO `course_section`
VALUES (23, 21, '第二节 C语言简介', '简单地介绍C语言的特性', 'M00/00/00/rBExSl0AXKCAVJJPAAqZxIJC28o462.mp4', '2019-06-12 10:34:30');
INSERT INTO `course_section`
VALUES (24, 22, '第一节 C语言的历史与特点', '详细介绍C语言的历史和特点', 'M00/00/00/rBExSl0AXKWAaEzZAA82jQSL53A080.mp4',
        '2019-06-12 10:34:31');
INSERT INTO `course_section`
VALUES (25, 22, '第二节 算法及其表示', '什么是算法，算法要怎么表示出来', 'M00/00/00/rBExSl0AXKmAHE7PAApQwPPYluY128.mp4', '2019-06-12 10:34:31');
INSERT INTO `course_section`
VALUES (26, 22, '第三节 C程序的操作过程', '什么是程序，程序是如何操作的', 'M00/00/00/rBExSl0AXK6Ab8FoAApN5ojQkX8410.mp4',
        '2019-06-12 10:34:31');
INSERT INTO `course_section`
VALUES (27, 22, '第四节 C程序的基本结构', '了解C程序的基本结构', 'M00/00/00/rBExSl0AXLSAZM4lABN_ZHiQDP4224.mp4', '2019-06-12 10:34:31');
INSERT INTO `course_section`
VALUES (28, 22, '第五节 标识符、关键字和保留字', '了解C语言的基本元素标识符、关键字和保留字', 'M00/00/00/rBExSl0AXL-AVNDrAAwhPl3I4WU819.mp4',
        '2019-06-12 10:34:31');
INSERT INTO `course_section`
VALUES (29, 22, '第六节 常量和变量', '了解C语言的基本元素常量和变量', 'M00/00/00/rBExSl0AXMSASQHlABKAZ1F103U803.mp4', '2019-06-12 10:34:31');
INSERT INTO `course_section`
VALUES (30, 22, '第七节 数据类型', '学习C语言的数据类型', 'M00/00/00/rBExSl0AXMmAXkpUAAue5rEr40I267.mp4', '2019-06-12 10:34:31');
INSERT INTO `course_section`
VALUES (31, 22, '第八节 C语言的语法规范和编程规范', '学习C语言的语法和规范', 'M00/00/00/rBExSl0AXM-APqoVAAoLJGbtjvA755.mp4',
        '2019-06-12 10:34:31');
INSERT INTO `course_section`
VALUES (32, 23, '第一节 顺序结构', '学习语句与表达式，算术运算，赋值运算，位运算，增量运算和逗号运算，数据输出，数据输入',
        'M00/00/00/rBExSl0AXNWAW5GJABU_t6-emtA506.mp4', '2019-06-12 10:34:31');
INSERT INTO `course_section`
VALUES (33, 23, '第二节 选择结构', '学习选择与条件构造，if语句，switch语句，选择结构嵌套，switch语句编程，选择结构常见错误分析',
        'M00/00/00/rBExSl0AXNqAeQI3ABNa7SCsGsw345.mp4', '2019-06-12 10:34:31');
INSERT INTO `course_section`
VALUES (34, 23, '第三节 循环结构', '学习常用的三种循环语句，循环嵌套，异常出口语句', 'M00/00/00/rBExSl0AXN-AUIPfAA8wg4B8YUo667.mp4',
        '2019-06-12 10:34:31');
INSERT INTO `course_section`
VALUES (35, 24, '第一节 一维数组的定义及引用', '介绍一维数组', 'M00/00/00/rBExSl0AXOOAccL4AA3Z5K18Kk8844.mp4', '2019-06-12 10:34:31');
INSERT INTO `course_section`
VALUES (36, 24, '第二节 一维数组应用--冒泡排序', '使用数组对数据进行冒泡排序', 'M00/00/00/rBExSl0AXOaAQWYJAA7iNc4OoNY783.mp4',
        '2019-06-12 10:34:31');
INSERT INTO `course_section`
VALUES (37, 24, '第三节 一维数组的典型应用--查找', '使用数组对数据进行查找', 'M00/00/00/rBExSl0AXO2AKqSVAAncyOKUoco623.mp4',
        '2019-06-12 10:34:31');
INSERT INTO `course_section`
VALUES (38, 24, '第四节 矩阵及其应用', '介绍二维数组', 'M00/00/00/rBExSl0AXJGALXSrAAusIQa_HPo562.mp4', '2019-06-12 10:34:31');
INSERT INTO `course_section`
VALUES (39, 25, '第一节', '导学', 'M00/00/00/rBExSl0AZWaAaXz1AAwYd3z7Mnk591.mp4', '2019-06-12 10:38:35');
INSERT INTO `course_section`
VALUES (40, 26, '第一节', '导学', 'M00/00/00/rBExSl0AZWuAehLiABB6tJrNooY092.mp4', '2019-06-12 10:40:27');
INSERT INTO `course_section`
VALUES (41, 27, '第一节', '导学', 'M00/00/00/rBExSl0AZW6AGNWWAAtTvSQ2Oiw225.mp4', '2019-06-12 10:42:27');
INSERT INTO `course_section`
VALUES (42, 28, '第一节', '导学', 'M00/00/00/rBExSl0AZXKAZeCfABP0jPsgXdM584.mp4', '2019-06-12 10:45:30');
INSERT INTO `course_section`
VALUES (43, 29, '第一节', 'Java 入门指南', 'M00/00/00/rBExSl0AVVaAFuZmAAlwm9RH6aA794.mp4', '2019-06-12 10:52:37');
INSERT INTO `course_section`
VALUES (44, 30, '第一节', '入门指引', 'M00/00/00/rBExSlz_yZyAIpk9AAqZxIJC28o306.mp4', '2019-06-12 10:54:17');
INSERT INTO `course_section`
VALUES (45, 30, '第二节', '在入门一次', 'M00/00/00/rBExSl0AVPuAbdcdAA8i9qNhoyE591.mp4', '2019-06-12 10:54:17');
INSERT INTO `course_section`
VALUES (46, 31, '第一节', 'VB介绍', 'M00/00/00/rBExSl0AZ66AAjVPAAqgpZHVNsw411.mp4', '2019-06-12 12:57:21');
INSERT INTO `course_section`
VALUES (47, 32, '第一节', '简介', 'M00/00/00/rBExSl0OX9aAOy8tAAqgpZHVNsw892.mp4', '2019-06-23 01:06:33');
COMMIT;

-- ----------------------------
-- Table structure for course_section_comment
-- ----------------------------
DROP TABLE IF EXISTS `course_section_comment`;
CREATE TABLE `course_section_comment`
(
    `ID`           bigint(20)              NOT NULL AUTO_INCREMENT,
    `USER_ID`      bigint(20)              NOT NULL,
    `SECTION_ID`   bigint(20)              NOT NULL,
    `CONTENT`      text CHARACTER SET utf8 NOT NULL,
    `STAR`         int(11)                          DEFAULT '0',
    `CREATED_TIME` timestamp               NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`ID`) USING BTREE,
    KEY `user_index` (`USER_ID`, `SECTION_ID`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of course_section_comment
-- ----------------------------
BEGIN;
INSERT INTO `course_section_comment`
VALUES (1, 8, 46, 'ef', 0, '2019-06-22 20:00:30');
COMMIT;

-- ----------------------------
-- Table structure for course_section_comment_reply
-- ----------------------------
DROP TABLE IF EXISTS `course_section_comment_reply`;
CREATE TABLE `course_section_comment_reply`
(
    `ID`           bigint(20) NOT NULL AUTO_INCREMENT,
    `COMMENT_ID`   bigint(20) NOT NULL,
    `CONTENT`      text CHARACTER SET utf8,
    `FROM_ID`      bigint(20) NOT NULL,
    `TO_ID`        bigint(20) NOT NULL,
    `CREATED_TIME` timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`ID`) USING BTREE,
    KEY `comment_from_index` (`FROM_ID`, `COMMENT_ID`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for course_section_comment_star
-- ----------------------------
DROP TABLE IF EXISTS `course_section_comment_star`;
CREATE TABLE `course_section_comment_star`
(
    `ID`           bigint(20) NOT NULL AUTO_INCREMENT,
    `USER_ID`      bigint(20) NOT NULL,
    `COMMENT_ID`   bigint(20) NOT NULL,
    `CREATED_TIME` timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`ID`) USING BTREE,
    KEY `USER_COMMIT_ID` (`USER_ID`, `COMMENT_ID`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for course_star
-- ----------------------------
DROP TABLE IF EXISTS `course_star`;
CREATE TABLE `course_star`
(
    `ID`           bigint(20) NOT NULL AUTO_INCREMENT,
    `USER_ID`      bigint(20) NOT NULL,
    `COURSE_ID`    bigint(20) NOT NULL,
    `CREATED_TIME` timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`ID`) USING BTREE,
    KEY `USER_COURSE_ID` (`USER_ID`, `COURSE_ID`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 27
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of course_star
-- ----------------------------
BEGIN;
INSERT INTO `course_star`
VALUES (1, 5, 1, '2019-06-11 22:49:45');
INSERT INTO `course_star`
VALUES (2, 6, 4, '2019-06-12 08:58:34');
INSERT INTO `course_star`
VALUES (4, 1, 7, '2019-06-12 10:02:57');
INSERT INTO `course_star`
VALUES (5, 1, 11, '2019-06-12 10:12:18');
INSERT INTO `course_star`
VALUES (6, 1, 10, '2019-06-12 10:12:23');
INSERT INTO `course_star`
VALUES (7, 6, 11, '2019-06-12 10:22:26');
INSERT INTO `course_star`
VALUES (8, 9, 19, '2019-06-12 13:20:17');
INSERT INTO `course_star`
VALUES (10, 9, 22, '2019-06-12 13:20:58');
INSERT INTO `course_star`
VALUES (12, 8, 27, '2019-06-12 14:14:27');
INSERT INTO `course_star`
VALUES (13, 8, 26, '2019-06-12 14:14:32');
INSERT INTO `course_star`
VALUES (14, 8, 25, '2019-06-12 14:14:35');
INSERT INTO `course_star`
VALUES (15, 8, 24, '2019-06-12 14:14:40');
INSERT INTO `course_star`
VALUES (16, 8, 23, '2019-06-12 14:14:50');
INSERT INTO `course_star`
VALUES (17, 8, 22, '2019-06-12 14:14:55');
INSERT INTO `course_star`
VALUES (18, 8, 21, '2019-06-12 14:14:59');
INSERT INTO `course_star`
VALUES (19, 8, 19, '2019-06-12 14:15:04');
INSERT INTO `course_star`
VALUES (20, 8, 18, '2019-06-12 14:15:09');
INSERT INTO `course_star`
VALUES (21, 8, 17, '2019-06-12 14:15:18');
INSERT INTO `course_star`
VALUES (22, 8, 16, '2019-06-12 14:15:27');
INSERT INTO `course_star`
VALUES (23, 8, 15, '2019-06-12 14:15:31');
INSERT INTO `course_star`
VALUES (24, 8, 9, '2019-06-12 14:15:43');
INSERT INTO `course_star`
VALUES (25, 3, 1, '2019-06-13 15:46:52');
INSERT INTO `course_star`
VALUES (26, 1, 26, '2019-06-14 09:52:58');
COMMIT;

-- ----------------------------
-- Table structure for number
-- ----------------------------
DROP TABLE IF EXISTS `number`;
CREATE TABLE `number`
(
    `ID`        bigint(20) NOT NULL AUTO_INCREMENT,
    `TYPE`      int(11)     DEFAULT NULL,
    `KIND_NAME` varchar(15) DEFAULT NULL,
    `NUM`       bigint(20)  DEFAULT '0',
    PRIMARY KEY (`ID`) USING BTREE,
    KEY `type_kind_index` (`TYPE`, `KIND_NAME`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 23
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of number
-- ----------------------------
BEGIN;
INSERT INTO `number`
VALUES (1, 1, 'TOTAL', 28);
INSERT INTO `number`
VALUES (2, 1, 'Java', 9);
INSERT INTO `number`
VALUES (3, 1, 'C', 3);
INSERT INTO `number`
VALUES (4, 1, 'C++', 2);
INSERT INTO `number`
VALUES (5, 1, 'PHP', 2);
INSERT INTO `number`
VALUES (6, 1, 'C#', 2);
INSERT INTO `number`
VALUES (7, 1, 'Python', 2);
INSERT INTO `number`
VALUES (8, 1, 'SQL', 2);
INSERT INTO `number`
VALUES (9, 1, 'VB', 2);
INSERT INTO `number`
VALUES (10, 1, 'GO', 2);
INSERT INTO `number`
VALUES (11, 1, 'Shell', 2);
INSERT INTO `number`
VALUES (12, 2, 'TOTAL', 28);
INSERT INTO `number`
VALUES (13, 2, 'Java', 9);
INSERT INTO `number`
VALUES (14, 2, 'C', 7);
INSERT INTO `number`
VALUES (15, 2, 'C++', 1);
INSERT INTO `number`
VALUES (16, 2, 'PHP', 0);
INSERT INTO `number`
VALUES (17, 2, 'C#', 4);
INSERT INTO `number`
VALUES (18, 2, 'Python', 6);
INSERT INTO `number`
VALUES (19, 2, 'SQL', 0);
INSERT INTO `number`
VALUES (20, 2, 'VB', 1);
INSERT INTO `number`
VALUES (21, 2, 'GO', 0);
INSERT INTO `number`
VALUES (22, 2, 'Shell', 0);
COMMIT;

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post`
(
    `ID`           bigint(20)                      NOT NULL AUTO_INCREMENT,
    `USER_ID`      bigint(20)                      NOT NULL,
    `KIND_NAME`    varchar(15) CHARACTER SET utf8           DEFAULT NULL,
    `TITLE`        varchar(127) CHARACTER SET utf8 NOT NULL,
    `CONTENT`      text CHARACTER SET utf8         NOT NULL,
    `CREATED_TIME` timestamp                       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `LIKE`         int(11)                                  DEFAULT '0',
    `STAR`         int(11)                                  DEFAULT '0',
    PRIMARY KEY (`ID`) USING BTREE,
    KEY `kind_index` (`KIND_NAME`) USING BTREE,
    KEY `user_index` (`USER_ID`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 30
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of post
-- ----------------------------
BEGIN;
INSERT INTO `post`
VALUES (1, 1, 'Java', 'Java是什么', '来水一下？', '2019-06-11 20:57:03', 0, 0);
INSERT INTO `post`
VALUES (2, 1, 'Java', 'Java是什么', '？？？', '2019-06-11 21:00:12', 0, 0);
INSERT INTO `post`
VALUES (3, 1, 'Java', 'Java是什么', '？？？？', '2019-06-11 21:03:29', 1, 1);
INSERT INTO `post`
VALUES (5, 1, 'C', 'C入门', '请看课程C语言版块', '2019-06-11 21:11:44', 0, 1);
INSERT INTO `post`
VALUES (6, 7, 'Shell', '我爱SHELL', 'SHELL使我快乐呀！', '2019-06-11 23:29:34', 1, 0);
INSERT INTO `post`
VALUES (7, 1, 'Java', '有大佬带我学C++吗', '求带，想学C++', '2019-06-12 10:04:08', 0, 1);
INSERT INTO `post`
VALUES (8, 1, 'SQL', 'SQL优化', 'SQL语句怎么优化比较好，求大佬指点一下', '2019-06-12 10:07:07', 0, 1);
INSERT INTO `post`
VALUES (9, 1, 'Java', 'PHP是什么', 'PHP是什么呀', '2019-06-12 10:08:24', 0, 0);
INSERT INTO `post`
VALUES (10, 1, 'VB', 'VB是什么', 'vb是啥啊？', '2019-06-12 10:10:07', 1, 1);
INSERT INTO `post`
VALUES (11, 1, 'Java', 'GO是什么', 'GO是啥？', '2019-06-12 10:11:34', 1, 1);
INSERT INTO `post`
VALUES (12, 8, 'C++', 'C++怎么写红黑树呀', '红黑树怎么写呀，啦啦啦', '2019-06-12 12:37:36', 1, 1);
INSERT INTO `post`
VALUES (13, 1, 'Java', 'GO是什么', 'GO是啥', '2019-06-12 12:57:47', 0, 0);
INSERT INTO `post`
VALUES (14, 1, 'GO', 'GO是什么', 'GO是啥？？？', '2019-06-12 12:58:14', 0, 0);
INSERT INTO `post`
VALUES (15, 1, 'GO', '谁会GO', '谁会GO？？', '2019-06-12 12:58:40', 0, 0);
INSERT INTO `post`
VALUES (16, 1, 'VB', 'VBVBVBVB', 'VB怎么写？', '2019-06-12 12:59:00', 0, 0);
INSERT INTO `post`
VALUES (17, 1, 'SQL', 'SQL与MYSQL', 'SQL和MYSQL有什么区别？？', '2019-06-12 12:59:25', 0, 0);
INSERT INTO `post`
VALUES (18, 1, 'Python', 'Python是什么', 'python是啥', '2019-06-12 12:59:56', 0, 0);
INSERT INTO `post`
VALUES (19, 1, 'Python', 'python用什么编辑器？', '如题，IDEA？VSCODE？', '2019-06-12 13:00:54', 0, 0);
INSERT INTO `post`
VALUES (20, 1, 'C#', 'C#', 'C#是什么？', '2019-06-12 13:01:09', 0, 1);
INSERT INTO `post`
VALUES (21, 1, 'PHP', 'PHP是什么', 'PHP是啥？', '2019-06-12 13:01:25', 0, 0);
INSERT INTO `post`
VALUES (22, 1, 'C', 'C语言', 'C语言用什么编辑器比较好？', '2019-06-12 13:02:11', 1, 1);
INSERT INTO `post`
VALUES (23, 1, 'C', 'C语言的用途', 'C语言怎么用？', '2019-06-12 13:02:47', 0, 0);
INSERT INTO `post`
VALUES (24, 1, 'C++', 'C++大作业求助', '有大佬带飞吗？', '2019-06-12 13:03:33', 0, 0);
INSERT INTO `post`
VALUES (25, 1, 'PHP', 'PHP怎么写', 'PHP怎么写', '2019-06-12 13:04:13', 1, 1);
INSERT INTO `post`
VALUES (26, 1, 'C#', 'C#', 'C#怎么写', '2019-06-12 13:04:28', 0, 0);
INSERT INTO `post`
VALUES (27, 1, 'Shell', 'shell是什么', 'shell是什么', '2019-06-12 13:05:00', 1, 1);
INSERT INTO `post`
VALUES (28, 8, 'Java', 'Java Spring框架简介', '啦啦啦啦啦啦', '2019-06-12 14:00:25', 2, 2);
INSERT INTO `post`
VALUES (29, 8, 'Java', 'java Spring深入理解', '啦啦啦啦啦啦啦啦啦', '2019-06-12 14:01:38', 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for post_comment
-- ----------------------------
DROP TABLE IF EXISTS `post_comment`;
CREATE TABLE `post_comment`
(
    `ID`           bigint(20)              NOT NULL AUTO_INCREMENT,
    `USER_ID`      bigint(20)              NOT NULL,
    `POST_ID`      bigint(20)              NOT NULL,
    `CONTENT`      text CHARACTER SET utf8 NOT NULL,
    `STAR`         int(11)                          DEFAULT '0',
    `CREATED_TIME` timestamp               NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`ID`) USING BTREE,
    KEY `user_index` (`USER_ID`, `POST_ID`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 9
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of post_comment
-- ----------------------------
BEGIN;
INSERT INTO `post_comment`
VALUES (1, 1, 3, '不知道啊', 0, '2019-06-12 09:18:59');
INSERT INTO `post_comment`
VALUES (2, 1, 7, '我带你啊', 0, '2019-06-12 10:04:20');
INSERT INTO `post_comment`
VALUES (3, 1, 7, '这是java啊', 0, '2019-06-12 10:05:04');
INSERT INTO `post_comment`
VALUES (4, 1, 8, '我也不知道啊', 0, '2019-06-12 10:07:20');
INSERT INTO `post_comment`
VALUES (5, 8, 11, '沙发沙发', 0, '2019-06-12 12:52:56');
INSERT INTO `post_comment`
VALUES (6, 1, 12, '不会', 0, '2019-06-12 12:57:20');
INSERT INTO `post_comment`
VALUES (7, 7, 29, 'eddefee', 1, '2019-06-12 15:44:17');
INSERT INTO `post_comment`
VALUES (8, 2, 21, '123', 0, '2019-06-23 00:18:18');
COMMIT;

-- ----------------------------
-- Table structure for post_comment_reply
-- ----------------------------
DROP TABLE IF EXISTS `post_comment_reply`;
CREATE TABLE `post_comment_reply`
(
    `ID`           bigint(20) NOT NULL AUTO_INCREMENT,
    `COMMENT_ID`   bigint(20) NOT NULL,
    `CONTENT`      text CHARACTER SET utf8,
    `FROM_ID`      bigint(20) NOT NULL,
    `TO_ID`        bigint(20) NOT NULL,
    `CREATED_TIME` timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`ID`) USING BTREE,
    KEY `comment_from_index` (`FROM_ID`, `COMMENT_ID`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 6
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of post_comment_reply
-- ----------------------------
BEGIN;
INSERT INTO `post_comment_reply`
VALUES (1, 2, '哈哈哈', 1, 1, '2019-06-12 10:04:31');
INSERT INTO `post_comment_reply`
VALUES (2, 3, '啊发错了', 1, 1, '2019-06-12 10:05:14');
INSERT INTO `post_comment_reply`
VALUES (3, 5, '挤一挤', 8, 8, '2019-06-12 12:53:20');
INSERT INTO `post_comment_reply`
VALUES (4, 5, '你已经是一个成熟的评论了要学会自己捞', 8, 8, '2019-06-12 12:55:02');
INSERT INTO `post_comment_reply`
VALUES (5, 5, '三连捞，不能沉', 8, 8, '2019-06-12 12:55:42');
COMMIT;

-- ----------------------------
-- Table structure for post_comment_star
-- ----------------------------
DROP TABLE IF EXISTS `post_comment_star`;
CREATE TABLE `post_comment_star`
(
    `ID`           bigint(20) NOT NULL AUTO_INCREMENT,
    `USER_ID`      bigint(20) NOT NULL,
    `COMMENT_ID`   bigint(20) NOT NULL,
    `CREATED_TIME` timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`ID`) USING BTREE,
    KEY `USER_COMMIT_ID` (`USER_ID`, `COMMENT_ID`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of post_comment_star
-- ----------------------------
BEGIN;
INSERT INTO `post_comment_star`
VALUES (1, 7, 7, '2019-06-12 15:44:24');
COMMIT;

-- ----------------------------
-- Table structure for post_like
-- ----------------------------
DROP TABLE IF EXISTS `post_like`;
CREATE TABLE `post_like`
(
    `ID`           bigint(20) NOT NULL AUTO_INCREMENT,
    `USER_ID`      bigint(20) NOT NULL,
    `POST_ID`      bigint(20) NOT NULL,
    `CREATED_TIME` timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`ID`) USING BTREE,
    KEY `USER_POST_ID` (`USER_ID`, `POST_ID`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 16
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of post_like
-- ----------------------------
BEGIN;
INSERT INTO `post_like`
VALUES (4, 8, 10, '2019-06-12 12:51:10');
INSERT INTO `post_like`
VALUES (5, 8, 6, '2019-06-12 12:52:32');
INSERT INTO `post_like`
VALUES (6, 8, 11, '2019-06-12 13:22:50');
INSERT INTO `post_like`
VALUES (7, 8, 29, '2019-06-12 14:16:22');
INSERT INTO `post_like`
VALUES (8, 8, 28, '2019-06-12 14:16:26');
INSERT INTO `post_like`
VALUES (9, 8, 27, '2019-06-12 14:16:32');
INSERT INTO `post_like`
VALUES (10, 8, 25, '2019-06-12 14:16:36');
INSERT INTO `post_like`
VALUES (11, 8, 22, '2019-06-12 14:16:41');
INSERT INTO `post_like`
VALUES (12, 8, 12, '2019-06-12 14:16:50');
INSERT INTO `post_like`
VALUES (13, 8, 3, '2019-06-12 14:17:00');
INSERT INTO `post_like`
VALUES (15, 2, 28, '2019-06-23 00:19:53');
COMMIT;

-- ----------------------------
-- Table structure for post_score
-- ----------------------------
DROP TABLE IF EXISTS `post_score`;
CREATE TABLE `post_score`
(
    `ID`           bigint(20) NOT NULL AUTO_INCREMENT,
    `POST_ID`      bigint(20) NOT NULL,
    `USER_ID`      bigint(20) NOT NULL,
    `SCORE`        int(11)    NOT NULL DEFAULT '0',
    `CREATED_TIME` timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`ID`) USING BTREE,
    KEY `post_user_index` (`POST_ID`, `USER_ID`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 24
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of post_score
-- ----------------------------
BEGIN;
INSERT INTO `post_score`
VALUES (1, 3, 1, 3, '2019-06-11 23:45:16');
INSERT INTO `post_score`
VALUES (2, 5, 1, 7, '2019-06-12 10:02:17');
INSERT INTO `post_score`
VALUES (3, 7, 1, 7, '2019-06-12 10:04:10');
INSERT INTO `post_score`
VALUES (4, 8, 1, 3, '2019-06-12 10:07:13');
INSERT INTO `post_score`
VALUES (5, 11, 1, 3, '2019-06-12 10:11:41');
INSERT INTO `post_score`
VALUES (6, 10, 1, 3, '2019-06-12 10:15:13');
INSERT INTO `post_score`
VALUES (7, 11, 8, 12, '2019-06-12 12:50:09');
INSERT INTO `post_score`
VALUES (8, 10, 8, 12, '2019-06-12 12:51:09');
INSERT INTO `post_score`
VALUES (9, 6, 8, 8, '2019-06-12 12:52:31');
INSERT INTO `post_score`
VALUES (10, 12, 1, 3, '2019-06-12 12:57:15');
INSERT INTO `post_score`
VALUES (11, 27, 8, 12, '2019-06-12 13:21:50');
INSERT INTO `post_score`
VALUES (12, 26, 8, 3, '2019-06-12 13:22:00');
INSERT INTO `post_score`
VALUES (13, 28, 8, 12, '2019-06-12 14:00:34');
INSERT INTO `post_score`
VALUES (14, 29, 8, 12, '2019-06-12 14:16:18');
INSERT INTO `post_score`
VALUES (15, 25, 8, 12, '2019-06-12 14:16:34');
INSERT INTO `post_score`
VALUES (16, 22, 8, 12, '2019-06-12 14:16:39');
INSERT INTO `post_score`
VALUES (17, 20, 8, 7, '2019-06-12 14:16:43');
INSERT INTO `post_score`
VALUES (18, 12, 8, 12, '2019-06-12 14:16:49');
INSERT INTO `post_score`
VALUES (19, 3, 8, 12, '2019-06-12 14:16:58');
INSERT INTO `post_score`
VALUES (20, 29, 7, 3, '2019-06-12 15:44:02');
INSERT INTO `post_score`
VALUES (21, 21, 2, 3, '2019-06-23 00:18:14');
INSERT INTO `post_score`
VALUES (22, 28, 2, 12, '2019-06-23 00:19:51');
INSERT INTO `post_score`
VALUES (23, 29, 2, 3, '2019-06-23 00:33:47');
COMMIT;

-- ----------------------------
-- Table structure for post_star
-- ----------------------------
DROP TABLE IF EXISTS `post_star`;
CREATE TABLE `post_star`
(
    `ID`           bigint(20) NOT NULL AUTO_INCREMENT,
    `USER_ID`      bigint(20) NOT NULL,
    `POST_ID`      bigint(20) NOT NULL,
    `CREATED_TIME` timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`ID`) USING BTREE,
    KEY `USER_COMMENT_ID` (`USER_ID`, `POST_ID`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 18
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of post_star
-- ----------------------------
BEGIN;
INSERT INTO `post_star`
VALUES (1, 1, 5, '2019-06-12 10:02:39');
INSERT INTO `post_star`
VALUES (2, 1, 7, '2019-06-12 10:04:24');
INSERT INTO `post_star`
VALUES (3, 7, 8, '2019-06-12 12:25:55');
INSERT INTO `post_star`
VALUES (6, 8, 10, '2019-06-12 12:51:10');
INSERT INTO `post_star`
VALUES (8, 8, 11, '2019-06-12 13:22:23');
INSERT INTO `post_star`
VALUES (9, 8, 29, '2019-06-12 14:16:21');
INSERT INTO `post_star`
VALUES (10, 8, 28, '2019-06-12 14:16:25');
INSERT INTO `post_star`
VALUES (11, 8, 27, '2019-06-12 14:16:31');
INSERT INTO `post_star`
VALUES (12, 8, 25, '2019-06-12 14:16:35');
INSERT INTO `post_star`
VALUES (13, 8, 22, '2019-06-12 14:16:40');
INSERT INTO `post_star`
VALUES (14, 8, 20, '2019-06-12 14:16:45');
INSERT INTO `post_star`
VALUES (15, 8, 12, '2019-06-12 14:16:50');
INSERT INTO `post_star`
VALUES (16, 8, 3, '2019-06-12 14:16:59');
INSERT INTO `post_star`
VALUES (17, 2, 28, '2019-06-23 00:19:53');
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `ID`           bigint(20)                      NOT NULL AUTO_INCREMENT,
    `NICKNAME`     varchar(127) CHARACTER SET utf8          DEFAULT NULL,
    `PASSWORD`     varchar(127) CHARACTER SET utf8 NOT NULL,
    `EMAIL`        varchar(127) CHARACTER SET utf8 NOT NULL,
    `PHONE`        varchar(11) CHARACTER SET utf8           DEFAULT NULL,
    `SEC_QUESTION` varchar(255) CHARACTER SET utf8          DEFAULT NULL,
    `SEC_ANSWER`   varchar(255) CHARACTER SET utf8          DEFAULT NULL,
    `HEAD_URL`     varchar(63) CHARACTER SET utf8           DEFAULT NULL,
    `CREATED_TIME` timestamp                       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `MAJOR`        varchar(15) CHARACTER SET utf8           DEFAULT NULL,
    `AGE`          int(3)                                   DEFAULT NULL,
    `SEX`          tinyint(1)                               DEFAULT '0',
    PRIMARY KEY (`ID`) USING BTREE,
    KEY `INDEX` (`EMAIL`, `PASSWORD`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 10
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user`
VALUES (1, 'Garlan', 'e10adc3949ba59abbe56e057f20f883e', '1572279033@qq.com', NULL, NULL, NULL,
        'M00/00/00/rBExSl0AXSKAHvqRAAAhw3LzC8w171.jpg', '2019-06-11 19:57:36', 'Java', 20, 1);
INSERT INTO `user`
VALUES (2, '东云1号', 'e10adc3949ba59abbe56e057f20f883e', 'a@qq.com', NULL, NULL, NULL,
        'M00/00/00/rBExSl0OXwmASqGJAACTq7kwQF8746.jpg', '2019-06-11 22:34:36', 'Java', 1, 2);
INSERT INTO `user`
VALUES (3, '东云2号', 'e10adc3949ba59abbe56e057f20f883e', 'b@qq.com', NULL, NULL, NULL, NULL, '2019-06-11 22:34:36', NULL,
        NULL, 0);
INSERT INTO `user`
VALUES (4, '东云3号', 'e10adc3949ba59abbe56e057f20f883e', 'c@qq.com', NULL, NULL, NULL, NULL, '2019-06-11 22:34:36', NULL,
        NULL, 0);
INSERT INTO `user`
VALUES (5, '东云基号', 'e10adc3949ba59abbe56e057f20f883e', 'd@qq.com', NULL, NULL, NULL,
        'M00/00/00/rBExSlz_wjiAAC-IAAAe2Gop4GU943.jpg', '2019-06-11 22:34:36', 'Java', 2, 1);
INSERT INTO `user`
VALUES (6, 'Charles', '399423ff652ebb6a6701be7ec3202fc6', 'xingzhang1999@163.com', NULL, NULL, NULL,
        'M00/00/00/rBExSlz_ycGAD5YrAAAv-2oPGhU158.jpg', '2019-06-11 22:56:28', 'Python', 18, 1);
INSERT INTO `user`
VALUES (7, 'Cheung', 'e10adc3949ba59abbe56e057f20f883e', '254892464@qq.com', NULL, NULL, NULL,
        'M00/00/00/rBExSl0AjTmACZ_rAAAdxWpK8f8921.jpg', '2019-06-11 23:28:38', 'Java', 20, 1);
INSERT INTO `user`
VALUES (8, '回忆', 'e10adc3949ba59abbe56e057f20f883e', '2048772100@qq.com', NULL, NULL, NULL,
        'M00/00/00/rBExSl0AgzOAQDMnAABKDn7efT4748.jpg', '2019-06-12 09:52:24', 'Java', 20, 1);
INSERT INTO `user`
VALUES (9, 'Charles1', 'b02a4bb1ed88da43d26ab99c5bbbd9ed', '1615678473@qq.com', NULL, NULL, NULL,
        'M00/00/00/rBExSl0Ai4GAT60_AAA6v4SsTDE529.jpg', '2019-06-12 13:17:17', 'Python', 18, 1);
COMMIT;

-- ----------------------------
-- Table structure for video
-- ----------------------------
DROP TABLE IF EXISTS `video`;
CREATE TABLE `video`
(
    `ID`           bigint(20) NOT NULL AUTO_INCREMENT,
    `USER_ID`      bigint(20) NOT NULL,
    `FILENAME`     varchar(63) CHARACTER SET utf8 DEFAULT NULL,
    `URL`          varchar(63) CHARACTER SET utf8 DEFAULT NULL,
    `ISUPLOAD`     tinyint(1)                     DEFAULT '0',
    `CREATED_TIME` timestamp  NOT NULL            DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`ID`) USING BTREE,
    KEY `user_index` (`USER_ID`, `ISUPLOAD`) USING BTREE,
    KEY `filename_index` (`FILENAME`),
    KEY `url_index` (`URL`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 82
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of video
-- ----------------------------
BEGIN;
INSERT INTO `video`
VALUES (3, 1, '1.mp4', 'M00/00/00/rBExSlz_upyARBECAAdan9B3T1Y233.mp4', 1, '2019-06-11 22:28:44');
INSERT INTO `video`
VALUES (4, 6, 'java0.mp4', 'M00/00/00/rBExSlz_wpeANJwMAA2WWsVUsX0853.mp4', 1, '2019-06-11 23:02:47');
INSERT INTO `video`
VALUES (5, 6, 'java1.mp4', 'M00/00/00/rBExSlz_wzuAT6iKAAlwm9RH6aA875.mp4', 1, '2019-06-11 23:05:31');
INSERT INTO `video`
VALUES (6, 6, 'java2.mp4', 'M00/00/00/rBExSlz_w-SAaxTaAAl2l5bbj2w376.mp4', 1, '2019-06-11 23:08:19');
INSERT INTO `video`
VALUES (34, 7, 'java2.mp4', 'M00/00/00/rBExSl0AVVmAKm4iAAl2l5bbj2w333.mp4', 1, '2019-06-12 09:28:57');
INSERT INTO `video`
VALUES (36, 6, 'java4.mp4', 'M00/00/00/rBExSl0AWh-AQhmTAA4J1EUiORk448.mp4', 1, '2019-06-12 09:49:19');
INSERT INTO `video`
VALUES (54, 8, 'C16.mp4', 'M00/00/00/rBExSl0AXPGASAlHAA5pWZYuXaE842.mp4', 1, '2019-06-12 10:01:21');
INSERT INTO `video`
VALUES (59, 6, 'C6.mp4', 'M00/00/00/rBExSl0AYA2AR2OZAAwhPl3I4WU675.mp4', 1, '2019-06-12 10:14:37');
INSERT INTO `video`
VALUES (66, 8, 'C17.mp4', 'M00/00/00/rBExSl0AZGqABDjzAAxavdYJmLs196.mp4', 1, '2019-06-12 10:33:13');
INSERT INTO `video`
VALUES (67, 8, 'C18.mp4', 'M00/00/00/rBExSl0AZG-AYJqxAA1QtbM8wG8407.mp4', 1, '2019-06-12 10:33:18');
INSERT INTO `video`
VALUES (68, 8, 'C19.mp4', 'M00/00/00/rBExSl0AZHOAPQdkAAzRhm324Rs839.mp4', 1, '2019-06-12 10:33:22');
INSERT INTO `video`
VALUES (69, 8, 'C20.mp4', 'M00/00/00/rBExSl0AZHiAcLjrABJowwjCMeU339.mp4', 1, '2019-06-12 10:33:27');
INSERT INTO `video`
VALUES (74, 6, 'C#4.mp4', 'M00/00/00/rBExSl0AZXSAZi8OAAvJ03iuGew685.mp4', 1, '2019-06-12 10:37:40');
INSERT INTO `video`
VALUES (76, 7, 'php0.mp4', 'M00/00/00/rBExSl0AaRSATy5AACfwFlrfkXY979.mp4', 1, '2019-06-12 10:53:05');
INSERT INTO `video`
VALUES (77, 6, 'vb1.mp4', 'M00/00/00/rBExSl0AhnuAE-hmAAqgpZHVNsw669.mp4', 1, '2019-06-12 12:58:35');
INSERT INTO `video`
VALUES (78, 6, 'php0.mp4', 'M00/00/00/rBExSl0Ahw6AY-FcACfwFlrfkXY327.mp4', 1, '2019-06-12 13:00:59');
INSERT INTO `video`
VALUES (79, 9, 'php0.mp4', 'M00/00/00/rBExSl0Ai_iAfDKSACfwFlrfkXY930.mp4', 1, '2019-06-12 13:21:57');
INSERT INTO `video`
VALUES (80, 9, 'javascript0.mp4', 'M00/00/00/rBExSl0AjJWAQEAaAB0_cjaiYS4738.mp4', 1, '2019-06-12 13:24:36');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
