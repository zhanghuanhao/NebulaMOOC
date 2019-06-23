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

 Date: 23/06/2019 10:56:08
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

SET FOREIGN_KEY_CHECKS = 1;
