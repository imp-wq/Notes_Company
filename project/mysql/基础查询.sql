-- 查询name、age两列
SELECT `name`,age FROM stu;
-- 查询所有列，用*。 但在实际开发中不要使用。
SELECT * FROM stu;
-- 查询地址
SELECT address FROM stu;
-- 查询地址,去除重复记录
SELECT DISTINCT address FROM stu;
-- as关键字，起别名
SELECT `name` AS 姓名,math AS 数学成绩,english AS 英语成绩 FROM stu;
-- as可省略，用空格分隔
SELECT `name` 姓名,math 数学成绩,english 英语成绩 FROM stu;
