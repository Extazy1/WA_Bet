-- 切换库
use guess;

-- 插入样例数据到 guessing 表
INSERT INTO `guessing` (`name`, `player_1`, `choose_1`, `player_2`, `choose_2`, `start_time`, `end_time`, `max_join`, `result`)
VALUES
    ('Men\'s 100m Freestyle', '33', 10, '33', 15, '2024-07-20 10:00:00', '2024-07-20 12:00:00', 50, 0),
  ('Women\'s 200m Backstroke', '33', 8, '33', 20, '2024-07-21 14:00:00', '2024-07-21 16:00:00', 50, 0),
    ('Men\'s 50m Breaststroke', '33', 25, '33', 12, '2024-07-22 10:00:00', '2024-07-22 12:00:00', 50, 0),
  ('Women\'s 100m Butterfly', '33', 30, 51, '33', '2024-07-23 14:00:00', '2024-07-23 16:00:00', 50, 0),
    ('Men\'s 200m Medley', '33', 5, '33', 8, '2024-07-24 10:00:00', '2024-07-24 12:00:00', 50, 0);

-- 插入样例数据到 player 表
INSERT INTO `player` (`name`, `link`)
VALUES
  ('Caeleb Dressel', 'https://en.wikipedia.org/wiki/Caeleb_Dressel'),
  ('Kyle Chalmers', 'https://en.wikipedia.org/wiki/Kyle_Chalmers'),
  ('Katie Ledecky', 'https://en.wikipedia.org/wiki/Katie_Ledecky'),
  ('Ariarne Titmus', 'https://en.wikipedia.org/wiki/Ariarne_Titmus'),
  ('Adam Peaty', 'https://en.wikipedia.org/wiki/Adam_Peaty'),
  ('Nicolo Martinenghi', 'https://en.wikipedia.org/wiki/Nicolo_Martinenghi'),
  ('Sarah Sjöström', 'https://en.wikipedia.org/wiki/Sarah_Sj%C3%B6str%C3%B6m'),
  ('Emma McKeon', 'https://en.wikipedia.org/wiki/Emma_McKeon'),
  ('Daiya Seto', 'https://en.wikipedia.org/wiki/Daiya_Seto'),
  ('Michael Andrew', 'https://en.wikipedia.org/wiki/Michael_Andrew_(swimmer)');

-- 插入样例数据到 user 表
INSERT INTO `user` (`username`, `password`, `phoneNumber`, `address`, `authority`, `ip`, `point`)
VALUES
  ('john_doe', 'password123', '12345678901', '123 Main St, Anytown', 1, '192.168.1.1', 100),
  ('jane_smith', 'password456', '12345678902', '456 Elm St, Othertown', 1, '192.168.1.2', 200),
  ('alice_wonder', 'password789', '12345678903', '789 Oak St, Sometown', 2, '192.168.1.3', 150),
  ('bob_builder', 'password321', '12345678904', '321 Pine St, Hometown', 1, '192.168.1.4', 80),
  ('eve_hacker', 'password654', '12345678905', '654 Spruce St, Heretown', 1, '192.168.1.5', 50);

-- 插入样例数据到 record 表
INSERT INTO `record` (`uid`, `rid`, `choose`, `point`)
VALUES
  (1, 1, 1, 20),
  (2, 1, 2, 30),
  (3, 2, 4, 40),
  (4, 2, 3, 25),
  (5, 3, 6, 35),
  (1, 4, 7, 15),
  (2, 4, 8, 25),
  (3, 5, 10, 20),
  (4, 5, 9, 10),
  (5, 1, 2, 40);