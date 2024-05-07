-- create_user_table.sql

-- 切换到数据库 guess
use guess;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 表的结构 `user`
--

CREATE TABLE IF NOT EXISTS `user` (
     `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `username` varchar(64) NOT NULL COMMENT '用户名',
    `password` varchar(64) NOT NULL COMMENT '密码',
    `phoneNumber` varchar(12) NOT NULL COMMENT '手机号',
    `address` varchar(256) DEFAULT NULL COMMENT '地址',
    `authority` int(11) DEFAULT 0 COMMENT '权限',
    `ip` varchar(32) DEFAULT NULL COMMENT 'IP地址',
    `point` int(11) DEFAULT 10 COMMENT '票数' ,
     PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4;

-- 运动员信息
CREATE TABLE IF NOT EXISTS `athlete` (
                                         `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '运动员id',
                                         `name` varchar(64) NOT NULL COMMENT '运动员姓名',
                                         `sport` varchar(64) DEFAULT NULL COMMENT '运动类型',
                                         `link` varchar(128) DEFAULT NULL COMMENT '运动员信息链接',
                                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

-- 投票活动信息
CREATE TABLE IF NOT EXISTS `vote_event` (
                                            `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '投票活动id',
                                            `name` varchar(64) NOT NULL COMMENT '投票活动名称',
                                            `start_time` datetime DEFAULT NULL COMMENT '投票活动开始时间',
                                            `end_time` datetime DEFAULT NULL COMMENT '投票活动截止时间',
                                            `max_votes_per_user` int(11) DEFAULT 0 COMMENT '每名用户可投票最大数量（0表示不限制）',
                                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

-- 用户投票记录
CREATE TABLE IF NOT EXISTS `vote` (
                                      `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '投票记录id',
                                      `user_id` int(11) NOT NULL COMMENT '用户id',
                                      `athlete_id` int(11) NOT NULL COMMENT '运动员id',
                                      `vote_event_id` int(11) NOT NULL COMMENT '投票活动id',
                                      `votes` int(11) DEFAULT 1 COMMENT '投票数量',
                                      PRIMARY KEY (`id`),
                                      UNIQUE KEY `unique_vote` (`user_id`,`athlete_id`,`vote_event_id`),
                                      FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                                      FOREIGN KEY (`athlete_id`) REFERENCES `athlete` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                                      FOREIGN KEY (`vote_event_id`) REFERENCES `vote_event` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
