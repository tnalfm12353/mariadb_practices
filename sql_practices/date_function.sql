-- 함수 : 날짜 함수

-- CURDATE(), CURRENT_DATE
select curdate(), CURRENT_DATE;

-- CURTIME(), CURRENT_TIME
select curtime(), CURRENT_TIME;

-- now() vs sysdate()
select now(), sysdate();
select now(), sleep(2), now();
select sysdate(), sleep(2), sysdate();

-- date_format (date, format)
select date_format(now(), '%Y년 %m월 %d일  %h시 %i분 %s초') time;
select date_format(now(), '%Y년 %c월 %d일  %h시 %i분 %s초') time;

-- period_diff
-- YYMM, YYYYMM
-- 예) 근무 개월 수를 출력
select 
	first_name,
	period_diff(date_format(curdate(), '%Y%m'),date_format(hire_date, '%Y%m')) 
	from employees;
    
-- date_add(adddate), date_sub(subdate)
-- 날짜 date에 type(day, month, year) 형식으로 expr 값을 더하거나 뺀다.
-- 예시 : 각 사원들의 근무 년 수가 5년이 되는 날은 언제 인가요?

select first_name,
	hire_date,
	date_add(hire_date, interval 5 year)
    from employees;
    
-- cast
select cast('2021-05-07' as date);
select cast('12345' as int) + 10;
select now(), cast(now() as date);
select cast(1-2 as unsigned);
select cast(cast(1-2 as unsigned) as signed);

-- mysql type
-- varchar, char, text
-- int(integer), medium int, big int, int(11), signed(unsigned)
-- float, double
-- time, date, datetime
-- LOB(Large Object), CLOB (character large object), BLOB(binary large object)