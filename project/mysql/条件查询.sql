SELECT * FROM `stu`;

-- && 与 AND 等价
SELECT * FROM stu WHERE age>=20 && age <= 30;
SELECT * FROM stu WHERE age>=20 AND age <= 30;
SELECT * FROM stu WHERE age BETWEEN 20 AND 30;

-- 日期也可以直接进行比较
SELECT * FROM stu WHERE hire_date BETWEEN '1998-9-1' AND '1999-9-1';
SELECT * FROM stu WHERE hire_date >= '1998-9-1' AND hire_date <= '1999-9-1';

-- `=`直接表示判断相等
SELECT * FROM stu WHERE age=18;
-- `!=`和`<>`都表示不等
SELECT * FROM stu WHERE age!=18;
SELECT * FROM stu WHERE age<>18;

-- or 和 || 均表示或
SELECT * FROM stu WHERE age=18 or age=20 or age=22;
SELECT * FROM stu WHERE age=18 || age=20 || age=22;
-- in 表示在某个范围中
SELECT * FROM stu WHERE age IN (18,20,22);

-- 注意，null不能用=和<>来比较，必须使用is和is not
SELECT * FROM stu WHERE english IS NULL;

-- 模糊查询,LIKE关键字
-- 通配符:_表示单个字符，%表示任意个字符（可以是0个）。
-- 姓`马`的
SELECT * FROM stu WHERE `name` LIKE '马%';
-- 第二个字是`花`的
SELECT * FROM stu WHERE `name` LIKE '_花%';
-- 包含`德`字的
SELECT * FROM stu WHERE `name` LIKE '%德%';