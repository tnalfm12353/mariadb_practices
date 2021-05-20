-- inner join (equal join)

-- 예제 1
-- employees 테이블과 titles 테이블을 join하여
-- 직원의 이름과 직책을 모두 출력하세요.
-- equal join
select e.emp_no, e.first_name, t.title
	from employees e, titles t
    where e.emp_no = t.emp_no;	-- join condition
    
-- 예제 2
-- employees 테이블과 titles 테이블을 join하여
-- 직원의 이름과 직책을 출력하되 여성 엔지니어만 출력하세요.
select 
	e.emp_no, e.first_name, t.title, e.gender
    from employees e, titles t
    where e.emp_no = t.emp_no	-- join condition
    and e.gender = 'f' 			-- row-select condition
    and t.title = 'Engineer';	-- row-select condition

-- 
-- ANSI / ISO SQL1999  JOIN 표준 문법
--
-- 1) natural join (쓰지마)
-- 두 테이블 간의 공통 컬럼이 있으면 별다른 조건없이 묵시적으로 조인됨.
-- 쓸 일이 없음.. 
select 
	e.first_name, t.title
	from employees e
    natural join titles t;
-- on e.emp_no = t.emp_no; -- 가 생략됨. 컬럼 이름이 같기 때문에.

-- 현재 근무하고 있는 직원의 타이틀, 월급을 출력하세요.
-- 이건 emp_no만 비교하지만
select count(*)
	from titles t 
    join salaries s on t.emp_no = s.emp_no;

-- 이건 emp_no, to_date, from_date 3개의 컬럼을 비교함.    
select count(*)
	from titles t
    natural join salaries s;

-- 2) join - using
select 
	e.first_name, t.title
	from employees e
    join titles t using(emp_no);
    
-- 3) join ~ on
select 
	e.first_name, t.title
	from employees e
    join titles t on e.emp_no = t.emp_no;

    
--
-- outer join
-- 

-- insert into dept values(null, '총무');
-- insert into dept values(null, '개발팀');
-- insert into dept values(null, '영업');
-- insert into dept values(null, '기획');
select * from dept;
-- insert into emp values(null, '둘리', 1);
-- insert into emp values(null, '마이콜', 2);
-- insert into emp values(null, '또치', 3);
-- insert into emp values(null, '길동', null);
select * from emp; 

select a.name as '이름', ifnull(b.name, '없음') as '부서'
	from emp a 
left join dept b on a.dept_no = b.no;

select ifnull(a.name,'없음')as '사원', b.name as '부서'
	from emp a
right join dept b on a.dept_no = b.no;

-- 실습문제 1
-- 현재 회사 상황을 반영한 직원별 근무부서를
-- 사번, 직원 전체이름, 근무 부서 형태로 출력해주세요.
select a.emp_no, a.first_name, c.dept_name
	from employees a,
		 dept_emp b,
         departments c
	where a.emp_no = b.emp_no
    and b.dept_no = c.dept_no
    and b.to_date ='9999-01-01';
    
-- 실습문제 2
-- 현재 회사에서 지급되고 있는 급여체계를 반영한 결과를 출력하세요.
-- 사번, 전체이름, 연봉 이런 형태로 출력하세요.
select a.emp_no, a.first_name, b.salary
	from employees a, salaries b
    where a.emp_no = b.emp_no
    and b.to_date = '9999-01-01';
    