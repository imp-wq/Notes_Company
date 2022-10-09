-- 1. 从0开始查询，查询3条数据
SELECT * FROM stu LIMIT 0 ,3;
-- 2. 每页显示3条数据，查询第1页数据
SELECT * FROM stu LIMIT 0,3;
-- 3. 每页显示3条数据，查询第2页数据
SELECT * FROM stu LIMIT 3,3;
-- 4. 每页显示3条数据，查询第3页数据
SELECT * FROM stu LIMIT 6,3;
-- 起始索引 = （当前页码 - 1） * 每页显示条数