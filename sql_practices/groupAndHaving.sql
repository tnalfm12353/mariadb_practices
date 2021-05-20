-- 집계쿼리 : select 절에 그룹함수가 적용된 경우
select avg(salary)
	from salaries;
    
-- select 절에 그룹 함수가 있는 경우, 어떤 컬럼도 select 절에 올 수 없다.
-- emp_no는 아무런 의미가 없다.
-- 오류!!!!!!!
select emp_no, avg(salary)
	from salaries;
    
-- query 실행 순서 숙지하기.
-- (1) from : 접근 테이블 확인
-- (2) where : 조건에 맞는 row 선택 -> 임시 테이블
-- (3) 집계
-- (4) projection
select emp_no, avg(salary)
	from salaries
    where emp_no='10060';
    
-- group by에 참여하고 있는 컬럼은 projection 가능 (select 절에 올 수 있다.)
select emp_no, avg(salary) 
	from salaries
group by emp_no;

-- having
-- 집계 결과 임시 테이블에서 row를 선택해야 하는 경우
-- 이미 where절은 실행이 되었기 때문에 having절에서 조건을 주어야 한다.
select emp_no, avg(salary) 
	from salaries
group by emp_no
	having avg(salary) > 60000;
    
select emp_no, avg(salary) as avg_salary
	from salaries
group by emp_no
	having avg_salary > 60000;
    
-- order by
select emp_no, avg(salary)
	from salaries
group by emp_no
	having avg(salary) > 60000
	order by avg(salary) asc;


-- 예제 : 사번이 10060인 직원의 급여 평균과 총 합계를 출력하되 
-- 최저 임금을 받은 시기와 최대 인금을 받는 시기도 출력하세요.
-- 문법적으로 오류 하지만 의미적으로는 맞음. (where절 때문에)
select emp_no, avg(salary), min(salary), max(salary)
	from salaries
    where emp_no='10060';
