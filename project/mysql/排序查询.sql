-- 按年龄升序
SELECT * FROM stu ORDER BY age;
-- 按数学成绩降序
SELECT * FROM stu ORDER BY math DESC;

-- 按数学成绩降序,如果数学成绩一样，按英语成绩升序
SELECT * FROM stu ORDER BY math DESC, english ASC;