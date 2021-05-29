BEGIN;

CREATE TABLE employees(
id SERIAL PRIMARY KEY,
name varchar(100) NOT NULL,
employee_id int NOT NULL UNIQUE,
mobile_number bigint NOT NULL UNIQUE,
email varchar(150) NOT NULL UNIQUE,
username varchar(50) NOT NULL UNIQUE,
password varchar(50) NOT NULL,
active boolean NOT NULL DEFAULT TRUE,
joined_date date NOT NULL,
modified_time timestamp without time zone
);


CREATE TABLE employee_leavebalance(
id SERIAL PRIMARY KEY,
employee_id int NOT NULL,
type_of_leave varchar(50) NOT NULL,
leave_balance int NOT NULL,
modified_time timestamp without time zone NOT NULL,
FOREIGN KEY (employee_id) REFERENCES employees(employee_id),
UNIQUE(employee_id,type_of_leave)
);

CREATE TABLE leave_requests(
id serial PRIMARY KEY,
employee_id int NOT NULL,
from_date date NOT NULL,
to_date date NOT NULL,
leave_type varchar(20) NOT NULL,
duration int NOT NULL,
status varchar(20) NOT NULL DEFAULT 'waiting for approval',
reason varchar(50) NOT NULL,
created_time timestamp without time zone NOT NULL,
modified_time timestamp without time zone,
FOREIGN KEY (employee_id) REFERENCES employees(employee_id)
);

SELECT * FROM employees;

SELECT * FROM employee_leavebalance;

SELECT * FROM leave_requests;

INSERT INTO employees (name,employee_id,mobile_number,email,username,password,joined_date) 
VALUES ('Mohamed',2627,9524896959,'mohamedhalith1994@gmail.com','moha2627','2627moha',now()),
('Halith',2628,9487617616,'mohamedhalith@gmail.com','hali2628','2628hali',now());

INSERT INTO employee_leavebalance (employee_id,type_of_leave,leave_balance,modified_time)
VALUES(2627,'sickleave',2,now()),
(2627,'casualleave',2,now()),
(2627,'earnedleave',1,now()),
(2628,'sickleave',2,now()),
(2628,'casualleave',2,now()),
(2628,'earnedleave',1,now());

INSERT INTO leave_requests(employee_id,from_date,to_date,leave_type,duration,reason,created_time)
VALUES(2627,'2021-05-27','2021-05-27','sickleave',1,'Static',now());

END;