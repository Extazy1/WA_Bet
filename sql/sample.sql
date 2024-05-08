-- 切换到数据库 guess
use guess;

-- 插入样例数据到 user 表
INSERT INTO `user` (`username`, `password`, `phoneNumber`, `address`, `authority`, `ip`, `point`)
VALUES
    ('john_doe', 'password123', '12345678901', '123 Main St, Anytown', 1, '192.168.1.1', 100),
    ('jane_smith', 'password456', '12345678902', '456 Elm St, Othertown', 1, '192.168.1.2', 200),
    ('alice_wonder', 'password789', '12345678903', '789 Oak St, Sometown', 2, '192.168.1.3', 150),
    ('bob_builder', 'password321', '12345678904', '321 Pine St, Hometown', 1, '192.168.1.4', 80),
    ('eve_hacker', 'password654', '12345678905', '654 Spruce St, Heretown', 1, '192.168.1.5', 50);

-- 插入样例数据到 athlete 表
INSERT INTO `athlete` (`name`, `sport`, `link`)
VALUES
    ('Usain Bolt', 'Sprinting', 'https://example.com/usain-bolt'),
    ('Michael Phelps', 'Swimming', 'https://example.com/michael-phelps'),
    ('Simone Biles', 'Gymnastics', 'https://example.com/simone-biles'),
    ('Roger Federer', 'Tennis', 'https://example.com/roger-federer'),
    ('Lionel Messi', 'Football', 'https://example.com/lionel-messi');

-- 插入样例数据到 vote_event 表
INSERT INTO `vote_event` (`name`, `start_time`, `end_time`, `max_votes_per_user`)
VALUES
    ('Olympic 2024', '2024-07-24 00:00:00', '2024-08-10 23:59:59', 0),
    ('World Cup 2024', '2024-11-20 00:00:00', '2024-12-18 23:59:59', 10),
    ('Grand Slam 2024', '2024-01-01 00:00:00', '2024-12-31 23:59:59', 5);

-- 插入样例数据到 vote 表
INSERT INTO `vote` (`user_id`, `athlete_id`, `vote_event_id`, `votes`)
VALUES
    (26, 1, 1, 3),
    (25, 2, 1, 5),
    (27, 3, 1, 2),
    (27, 4, 2, 4),
    (28, 5, 2, 1),
    (29, 5, 2, 7),
    (28, 3, 3, 3);
