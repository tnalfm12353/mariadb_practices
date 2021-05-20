-- 함수 : 수학 함수

-- abs() : 절대값
select abs(-1), abs(1);

-- mod() : 나머지 값
select mod(10, 3);

-- floor(x) : x 보다 크지 않은 가장 큰 정수를 변환
select floor(3.14);

-- ceil(x) : x 보다 큰 정수중에 가장 작은 정수를 반환
select ceiling(3.14);
select ceil(3.14);

-- round(x) : x에 가장 근접한 정수 반환
-- round(x, d) : x 값 중에서 소수점 d자리에 가장 근접한 실수 반환
select round(1.298), round(1.298, 1), round(1.298, 0);

-- pow(x, y), power(x, y) x의 y승을 반환
select pow(2, 10), power(10, 3);

-- sign(x)
-- x가 양수이면 1, 음수이면 -1, 0이면 0
select sign(2), sign(-2), sign(0);

-- greatest(x, y, .....), least(x, y, .....)
select greatest(10, 40, 20, 30), least(10, 40, 20, 30);
select greatest('b', 'a', 'c', 'A');
