-- 切换库
use guess;

-- 插入样例数据到 user 表
INSERT INTO `user` (`username`, `password`, `phoneNumber`, `address`, `authority`, `ip`, `point`)
VALUES
  ('john_doe', 'password123', '12345678901', '123 Main St, Anytown', 1, '192.168.1.1', 100),
  ('jane_smith', 'password456', '12345678902', '456 Elm St, Othertown', 1, '192.168.1.2', 200),
  ('alice_wonder', 'password789', '12345678903', '789 Oak St, Sometown', 2, '192.168.1.3', 150),
  ('bob_builder', 'password321', '12345678904', '321 Pine St, Hometown', 1, '192.168.1.4', 80),
  ('eve_hacker', 'password654', '12345678905', '654 Spruce St, Heretown', 1, '192.168.1.5', 50);
