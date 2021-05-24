-- subquery
-- 1) from절의 서브쿼리
select now() as n, sysdate() as b, 3 + 1 as c;
select s.n, s.b, s.c
	from( select now() as n, sysdate() as b, 3 + 1 as c) s;
    
-- 2) where

-- 예제 : 현재 Fai Bale에 근무하는 부서에서 
-- 근무하는 직원의 사변, 전체 이름을 출력해보세요.

select *
	from employees e
    join dept_emp d on e.emp_no = d.emp_no
    and d.to_date = '9999-01-01'
    and concat(e.first_name, ' ', e.last_name) = 'Fai Bale';
    
select e.emp_no, e.first_name
	from employees e,
		 dept_emp b
	where e.emp_no = b.emp_no
    and b.dept_no = (select d.dept_no
						from employees e
					 join dept_emp d on e.emp_no = d.emp_no
						and d.to_date = '9999-01-01'
						and concat(e.first_name, ' ', e.last_name) = 'Fai Bale');
                        
-- 2-1) where 절의 서브쿼리 : 단일행
-- 단일행 연산자 : =, >, <, >=, <=, <>, !=

-- 실습문제 1:
-- 현재 전체사원의 평균 연봉보다 적은 급여를 받는 사원의 이름, 급여를 나타내세요.
select concat(e.first_name, ' ' ,e.last_name), s.salary
	from employees e, salaries s
    where e.emp_no = s.emp_no
    and s.to_date = '9999-01-01'
    and s.salary < (select avg(a.salary)
						from salaries a
                        where a.to_date = '9999-01-01');
 
 select concat(e.first_name, ' ' ,e.last_name), s.salary
	from employees e
    join salaries s on e.emp_no = s.emp_no
    and s.to_date = '9999-01-01'
    and s.salary < (select avg(a.salary)
						from salaries a
                        where a.to_date = '9999-01-01')
	order by s.salary desc;
    
-- 실습문제 2
-- 현재 가장적은 평균 급여를 받고 있는 직책의 평균 급여를 구하세요.
select t.title , avg(s.salary) as avg_salary
	from salaries s, titles t
		where s.emp_no = t.emp_no
		and s.to_date = '9999-01-01'
		and t.to_date = '9999-01-01'
    group by t.title; 
            
select min(a.avg_salary)
	from (select t.title, avg(s.salary) as avg_salary
			from titles t, salaries s
            where t.emp_no = s.emp_no
            and t.to_date = '9999-01-01'
            and s.to_date = '9999-01-01'
            group by t.title)a;
-- 강사님꺼
select t.title, avg(s.salary) as avg_salary
	from titles t, salaries s
    where t.emp_no = s.emp_no
    and t.to_date = '9999-01-01'
    and s.to_date = '9999-01-01'
    group by t.title 
		having round(avg_salary) = (select min(a.avg_salary)
										from (select round(avg(s.salary)) as avg_salary
												from titles t, salaries s
												where t.emp_no = s.emp_no
												and t.to_date = '9999-01-01'
												and s.to_date = '9999-01-01'
												group by t.title)a);
        
-- 돌아가는데 쓰면안됨.-> 수학적 함수를 사용하고 title을 가져올려면 group by로 묶어줘야함.
select result.title ,min(result.avg_salary)
	from (select 
			t.title as title , avg(s.salary) as avg_salary
			from salaries s, titles t
				where s.emp_no = t.emp_no
				and s.to_date = '9999-01-01'
				and t.to_date = '9999-01-01'
			group by t.title) as result;
            
-- test 안됨.. -> avg(b.salary)는 프로세스가 끝나고난 후 생성되는 것인데 바로 where절에서 사용할려니 알 수 없는 것이므로.
select a.title, avg(b.salary)
	from titles a, salaries b,
		(select min(a.avg_salary)as min_avg
			from ( select avg(b.salary) as avg_salary
						from titles a, salaries b
						where a.emp_no = b.emp_no
						and a.to_date = '9999-01-01'
						and b.to_date = '9999-01-01'
						group by a.title) a) c
   where a.emp_no = b.emp_no
     and a.to_date = '9999-01-01'
     and b.to_date = '9999-01-01'
     and avg(b.salary) = c.min_avg
     group by a.title;

-- solution 2 : top-k
select t.title , avg(s.salary) as avg_salary
	from salaries s, titles t
	where s.emp_no = t.emp_no
	and s.to_date = '9999-01-01'
	and t.to_date = '9999-01-01'
    group by t.title
    order by avg_salary asc
    limit 0, 1;
    
-- 2-2) where 절의 서브쿼리 : 다중행 (복수행)
-- 복수행 연산자 : in, not in, any, all

-- any 사용법
-- 1. =any : in과 동일
-- 2. >any, >=any : 최소값
-- 3. <any, <= any : 최대값
-- 4. <>any : not in 과 동일

-- all 사용법
-- 1. =all (x)
-- 2. >all, >= all : 최대값 
-- 3. <all, <= all : 최소값

-- 실습문제3
-- 현재 급여가 50000 이상인 직원 이름 출력
-- solution 1
select emp_no, salary
	from salaries
    where to_date = '9999-01-01'
	and salary > 50000;

select a.first_name, b.salary
	from employees a , salaries b
    where a.emp_no = b.emp_no
    and b.to_date = '9999-01-01'
    and (a.emp_no, b.salary) in (select 
									emp_no, salary
									from salaries
									where to_date = '9999-01-01'
									and salary > 50000)
	order by b.salary asc;
    
-- solution 2
select e.first_name, s.salary
	from employees e, salaries s
	where e.emp_no = s.emp_no
	and s.to_date = '9999-01-01'
    and s.salary > 50000
order by s.salary asc;

-- solution 3
select a.first_name, b.salary
	from employees a , salaries b
    where a.emp_no = b.emp_no
    and b.to_date = '9999-01-01'
    and (a.emp_no, b.salary) =any (select 
									emp_no, salary
									from salaries
									where to_date = '9999-01-01'
									and salary > 50000)
	order by b.salary asc;
    
-- 실습문제4 :
-- 각 부서별로 최고 월급을 받는 직원의 이름과 월급을 출력
-- answer EX) 둘리 40000

-- 부서별 최고 급여
select d.dept_no, max(s.salary)
	from dept_emp d, salaries s
    where d.emp_no = s.emp_no
    and d.to_date = '9999-01-01'
    and s.to_date = '9999-01-01'
    group by d.dept_no;

-- solution 1 : where subquery =any 
select d.dept_no, e.first_name, s.salary
	from dept_emp d, salaries s, employees e
	where d.emp_no = s.emp_no
    and s.emp_no = e.emp_no
    and d.to_date = '9999-01-01'
    and s.to_date = '9999-01-01'
    and (d.dept_no, s.salary) =any (select d.dept_no, max(s.salary)
										from dept_emp d, salaries s
										where d.emp_no = s.emp_no
										and d.to_date = '9999-01-01'
										and s.to_date = '9999-01-01'
										group by d.dept_no);
    
-- solution 2 : from subquery 
select 
	d.dept_no, e.first_name, s.salary
	from dept_emp d, salaries s, employees e,
		 (select 
			d.dept_no, max(s.salary) as max_salary
			from dept_emp d, salaries s
			where d.emp_no = s.emp_no
			and d.to_date = '9999-01-01'
			and s.to_date = '9999-01-01'
			group by d.dept_no) as m
	where d.emp_no = s.emp_no
    and s.emp_no = e.emp_no
    and d.dept_no = m.dept_no
    and d.to_date = '9999-01-01'
    and s.to_date = '9999-01-01'
    and s.salary = m.max_salary;