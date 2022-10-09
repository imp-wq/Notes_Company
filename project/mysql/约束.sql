DROP TABLE employee;
CREATE TABLE employee(
	id INT PRIMARY KEY AUTO_INCREMENT, -- 员工id
	ename VARCHAR(50) NOT NULL UNIQUE, -- 员工姓名，非空且唯一
	joindate DATE NOT NULL, -- 入职日期
	salary DOUBLE(7,2) NOT NULL, -- 工资 非空
	bonus DOUBLE(7,2) DEFAULT 0 -- 奖金，默认为0
);

INSERT INTO employee(id,ename,joindate,salary,bonus) values(1,'张三','1999-11-11',8800,5000);

SELECT * FROM employee;

INSERT INTO employee(ename,joindate,salary,bonus) values('李四','1999-11-11',8800,5000);
INSERT INTO employee(id,ename,joindate,salary,bonus) values(null,'王五','1999-11-11',8800,5000);
