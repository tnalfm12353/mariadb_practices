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

-- 문제5.
-- 평균 연봉이 가장 높은 부서는? (이름, 평균연봉)

-- 문제6.
-- 현재, 평균연봉이 가장 높은 부서의 사원들의 사번, 이름, 직책, 연봉을 조회하고 연봉 순으로 출력하세요.

-- 문제7.
-- 평균 연봉이 가장 높은 직책?

-- 문제8.
-- 현재 자신의 매니저보다 높은 연봉을 받고 있는 직원은?
-- 부서이름, 사원이름, 연봉, 매니저 이름, 메니저 연봉 순으로 출력합니다.