-- practice04(혼합)

-- 문제1.
-- 현재 평균 연봉보다 많은 월급을 받는 직원은 몇 명이나 있습니까?
select count(e.emp_no)
	from employees e, salaries s
    where e.emp_no = s.emp_no
    and s.to_date = '9999-01-01'
    and s.salary > (select avg(s.salary)
						from salaries s
						where s.to_date = '9999-01-01');

-- 문제2. 
-- 현재, 각 부서별로 최고의 급여를 받는 사원의 사번, 이름, 부서 연봉을 조회하세요. 단 조회결과는 연봉의 내림차순으로 정렬되어 나타나야 합니다. 
select de.emp_no, de.dept_no, max(s.salary)
	from dept_emp de, salaries s
    where s.emp_no = de.emp_no
    and de.to_date = '9999-01-01'
    and s.to_date = '9999-01-01'
    group by(de.dept_no);

select e.emp_no, e.first_name, de.dept_no, s.salary
	from employees e, dept_emp de, salaries s,
		(select de.emp_no, de.dept_no, max(s.salary) as max_salary
			from dept_emp de, salaries s
			where s.emp_no = de.emp_no
			and de.to_date = '9999-01-01'
			and s.to_date = '9999-01-01'
			group by(de.dept_no))as result
	where e.emp_no = de.emp_no
    and de.emp_no = s.emp_no
    and de.dept_no = result.dept_no
    and de.to_date = '9999-01-01'
    and s.to_date = '9999-01-01'
    and s.salary = result.max_salary
    order by s.salary desc;
-- 문제3.
-- 현재, 자신의 부서 평균 급여보다 연봉(salary)이 많은 사원의 사번, 이름과 연봉을 조회하세요 
select de.dept_no, avg(s.salary)
	from dept_emp de, salaries s
    where de.emp_no = s.emp_no
    and de.to_date = '9999-01-01'
    and s.to_date = '9999-01-01'
    group by de.dept_no;

select e.emp_no, e.first_name, s.salary
	from employees e, dept_emp de, salaries s,
		(select de.dept_no, avg(s.salary) as avg_salary
			from dept_emp de, salaries s
			where de.emp_no = s.emp_no
			and de.to_date = '9999-01-01'
			and s.to_date = '9999-01-01'
			group by de.dept_no) as result
	where e.emp_no = de.emp_no
	and e.emp_no = s.emp_no
    and de.dept_no = result.dept_no
    and de.to_date = '9999-01-01'
    and s.to_date = '9999-01-01'
    and s.salary > result.avg_salary;
    
-- 문제4.
-- 현재, 사원들의 사번, 이름, 매니저 이름, 부서 이름으로 출력해 보세요.
select e.first_name as '매니저 이름', d.dept_name as '부서 이름'
	from dept_manager dm, departments d, employees e
    where dm.dept_no = d.dept_no
    and dm.emp_no = e.emp_no
    and dm.to_date = '9999-01-01'
    group by dm.emp_no;
    
select e.emp_no, e.first_name, manager.manager, manager.department
	from employees e, dept_emp de,
		(select dm.dept_no,e.first_name as manager, d.dept_name as department
			from dept_manager dm, departments d, employees e
			where dm.dept_no = d.dept_no
			and dm.emp_no = e.emp_no
			and dm.to_date = '9999-01-01'
			group by dm.emp_no)as manager
	where e.emp_no = de.emp_no
    and de.dept_no = manager.dept_no
    and de.to_date = '9999-01-01'
    group by e.emp_no;
-- 문제5.
-- 평균 연봉이 가장 높은 부서는? (이름, 평균연봉)

select de.dept_no, avg(s.salary) as avg_salary
	from dept_emp de, salaries s
    where de.emp_no = s.emp_no
    and de.to_date = '9999-01-01'
    and s.to_date = '9999-01-01'
    group by de.dept_no;

select max(dept_avg_salary.avg_salary) as max_salary
	from (select de.dept_no, avg(s.salary) as avg_salary
			from dept_emp de, salaries s
			where de.emp_no = s.emp_no
			and de.to_date = '9999-01-01'
			and s.to_date = '9999-01-01'
			group by de.dept_no)dept_avg_salary;

select d.dept_name, avg(s.salary) as avg_salary
	from departments d, salaries s, dept_emp de
    where de.dept_no = d.dept_no
    and de.emp_no = s.emp_no
    and de.to_date = '9999-01-01'
    and s.to_date = '9999-01-01'
    group by d.dept_name 
		having avg_salary = (select max(dept_avg_salary.avg_salary) as max_salary
								from (select de.dept_no, avg(s.salary) as avg_salary
										from dept_emp de, salaries s
										where de.emp_no = s.emp_no
										and de.to_date = '9999-01-01'
										and s.to_date = '9999-01-01'
										group by de.dept_no)dept_avg_salary);

-- 문제6.
-- 현재, 평균연봉이 가장 높은 부서의 사원들의 사번, 이름, 직책, 연봉을 조회하고 연봉 순으로 출력하세요.

select de.dept_no,e.emp_no, e.first_name, t.title, s.salary
	from employees e, salaries s, titles t, dept_emp de,
		(select de.dept_no, avg(s.salary) as avg_salary
			from salaries s, dept_emp de
			where de.emp_no = s.emp_no
			and de.to_date = '9999-01-01'
			and s.to_date = '9999-01-01'
			group by de.dept_no
				having avg_salary = (select max(dept_avg_salary.avg_salary) as max_salary
										from (select de.dept_no, avg(s.salary) as avg_salary
												from dept_emp de, salaries s
												where de.emp_no = s.emp_no
												and de.to_date = '9999-01-01'
												and s.to_date = '9999-01-01'
												group by de.dept_no)dept_avg_salary))max_dept
	where e.emp_no = s.emp_no
    and e.emp_no = de.emp_no
    and de.dept_no = max_dept.dept_no
    and t.emp_no = e.emp_no
	and s.to_date = '9999-01-01'
    and t.to_date = '9999-01-01'
    group by e.emp_no
    order by s.salary desc;
    
-- 문제7.
-- 평균 연봉이 가장 높은 직책?
select t.title ,avg(s.salary)
	from titles t, salaries s
    where t.emp_no = s.emp_no
    and t.to_date = '9999-01-01'
    and s.to_date = '9999-01-01'
    group by t.title;
    
select max(title_avg_salary.avg_salary) as title_max_salary
	from (select t.title ,avg(s.salary)as avg_salary
			from titles t, salaries s
			where t.emp_no = s.emp_no
			and t.to_date = '9999-01-01'
			and s.to_date = '9999-01-01'
			group by t.title) title_avg_salary;
            
select t.title, avg(s.salary)as avg_salary
	from titles t, salaries s
    where t.emp_no = s.emp_no
    and t.to_date = '9999-01-01'
    and s.to_date = '9999-01-01'
    group by t.title 
		having avg_salary = (select max(title_avg_salary.avg_salary) as title_max_salary
								from (select t.title ,avg(s.salary)as avg_salary
										from titles t, salaries s
										where t.emp_no = s.emp_no
										and t.to_date = '9999-01-01'
										and s.to_date = '9999-01-01'
										group by t.title) title_avg_salary);
-- 문제8.
-- 현재 자신의 매니저보다 높은 연봉을 받고 있는 직원은?
-- 부서이름, 사원이름, 연봉, 매니저 이름, 메니저 연봉 순으로 출력합니다.

select dm.dept_no, dm.emp_no, s.salary
	from dept_manager dm, salaries s
    where dm.emp_no = s.emp_no
    and dm.to_date = '9999-01-01'
    and s.to_date = '9999-01-01';
    
select d.dept_name, e.first_name, s.salary, manager.first_name, manager.salary
	from employees e, salaries s, dept_emp de, departments d,
		(select dm.dept_no, dm.emp_no, e.first_name, s.salary
			from employees e, dept_manager dm, salaries s
			where dm.emp_no = s.emp_no
			and dm.to_date = '9999-01-01'
			and s.to_date = '9999-01-01')as manager
    where e.emp_no = s.emp_no
    and e.emp_no = de.emp_no
    and de.dept_no = manager.dept_no
    and de.dept_no = d.dept_no
    and s.to_date = '9999-01-01'
    and de.to_date = '9999-01-01'
    and s.salary > manager.salary;
    