-- 聚合函数
-- 一共有多少个学生
SELECT COUNT(id) FROM stu;
-- count不会统计null
SELECT COUNT(english) FROM stu;

SELECT MAX(math) FROM stu;
SELECT MIN(math) FROM stu;
SELECT AVG(math) FROM stu;

-- null不参与所有聚合函数的运算
SELECT MIN(english) FROM stu;

-- 查询男、女的数学平均分
SELECT sex, AVG(math) FROM stu GROUP BY sex;
SELECT sex,AVG(math),COUNT(*) FROM stu GROUP BY sex;

-- 查询男、女70分以上的数学平均分
SELECT sex,AVG(math),COUNT(*) FROM stu WHERE math>70 GROUP BY sex;

-- 查询男、女70分以上的数学平均分，要求分组后人数大于2
SELECT sex,AVG(math),COUNT(*) FROM stu WHERE math>70 GROUP BY sex HAVING COUNT(*)>2;
