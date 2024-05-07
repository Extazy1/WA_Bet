-- 创建库
create database if not exists guess;

-- 切换库
use guess;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";




/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `guess`
--

-- --------------------------------------------------------

--
-- 表的结构 `guessinglist`
--

CREATE TABLE IF NOT EXISTS `guessing` (
  `id` int(11) NOT NULL COMMENT '项id',
  `name` varchar(32) DEFAULT NULL COMMENT '比赛名称',
  `player_1` varchar(64) DEFAULT NULL COMMENT '比赛人1',
  `choose_1` int(11) DEFAULT NULL COMMENT '选择人数',
  `player_2` varchar(64) DEFAULT NULL COMMENT '比赛人2',
  `choose_2` int(11) DEFAULT NULL COMMENT '选择人数',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `max_join` int(11) DEFAULT NULL COMMENT '最大加入数',
  `result` int(11) DEFAULT '0' COMMENT '结果'
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- 表的结构 `player`
--

CREATE TABLE IF NOT EXISTS `player` (
  `id` int(11) NOT NULL COMMENT 'player_id',
  `name` varchar(64) DEFAULT NULL,
  `link` varchar(128) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- 表的结构 `record`
--

CREATE TABLE IF NOT EXISTS `record` (
  `id` int(11) NOT NULL COMMENT '条目id',
  `uid` int(11) DEFAULT NULL COMMENT '用户id',
  `rid` int(11) DEFAULT NULL COMMENT '比赛id',
  `choose` int(11) DEFAULT NULL COMMENT '选择',
  `point` int(11) DEFAULT NULL COMMENT '积分'
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL COMMENT '用户id',
  `username` varchar(64) NOT NULL COMMENT '用户名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `phoneNumber` varchar(12) NOT NULL COMMENT '手机号',
  `address` varchar(256) DEFAULT NULL COMMENT '地址',
  `authority` int(11) DEFAULT NULL COMMENT '权限',
  `ip` varchar(32) DEFAULT NULL COMMENT 'IP地址',
  `point` int(11) DEFAULT NULL COMMENT '分数'
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `guessing`
--
ALTER TABLE `guessing`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `player`
--
ALTER TABLE `player`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `record`
--
ALTER TABLE `record`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `guessinglist`
--
ALTER TABLE `guessing`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '项id',AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `player`
--
ALTER TABLE `player`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'player_id',AUTO_INCREMENT=43;
--
-- AUTO_INCREMENT for table `record`
--
ALTER TABLE `record`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '条目id',AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',AUTO_INCREMENT=25;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
