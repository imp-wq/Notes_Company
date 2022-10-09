-- 1.查询工资比猪八戒高的
-- 查询猪八戒的工资
SELECT salary FROM emp WHERE `NAME`='猪八戒'
SELECT * FROM emp WHERE salary >(SELECT salary FROM emp WHERE `NAME`='猪八戒');

-- 2. 查询`财务部`和`市场部`所有员工的信息
-- 查询`财务部`和`市场部`的did
SELECT did FROM dept WHERE dname='财务部' OR dname='市场部';
SELECT * FROM emp WHERE dep_id IN (SELECT did FROM dept WHERE dname='财务部' OR dname='市场部');

-- 3. 查询入职日期`2011-11-11`之后员工的员工信息和部门信息
-- 查询入职日期`2011-11-11`之后员工的员工信息
SELECT * FROM emp WHERE join_date > '2011-11-11';
-- 查询员工信息和部门信息
SELECT * FROM emp,dept WHERE emp.dep_id=dept.did;
-- 将第一次查询结果作为虚拟表，放入第二次查询
SELECT * FROM (SELECT * FROM emp WHERE join_date > '2011-11-11') t1,dept WHERE t1.dep_id=dept.did;