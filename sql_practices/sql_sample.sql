-- 테이블 삭제하기
drop table cat;

-- 테이블 만들기 --
create table pet(
	name 	varchar(20),
    owner 	varchar(20),
    species	varchar(20),
    gender  char(1),
    birth 	date,
    death	date
);

-- schme 확인
desc pet;

-- 조회
select * from pet;

-- 데이터 넣기 (생성, create)
insert 
	into pet 
values ('peach','hong','matiz','f','2011-3-3','2021-1-1');

-- 데이터 삭제 (delete)
delete from pet where death = 'null';

-- 조회 연습
-- 1990년 이후에 태어난 아이들은?
select * from pet where birth > 1990-12-31;

-- Gwen와 함께 사는 아이들은?
select * from pet where owner = 'Gwen';

-- null 다루기 : 살아있는 애들은?
select * from pet where death is null;
-- null 다루기2 : 죽은 애들은?
select * from pet where death is not null;

-- 조회 연습2 : order by
-- 어린순으로 정렬
select * 
	from pet
order by birth desc;
-- 어린순으로 정렬하고 생일이 같으면 이름순으로 다시 정렬
select * 
	from pet
order by birth desc, name asc;

-- like 검색(패턴 매칭) 
-- 이름이 b로 시작하는 아이들은?
select * from pet where name like 'b%';
-- 이름이 b로 시작하는 아이들중 이름이 6자인 아이는?
select * from pet where name like 'b_____';

-- 집계(통계) 함수
select count(*) from pet;
-- 지정된 칼럼중 null인 경우는 count하지 않음.
select count(death) from pet;
select count(*) from pet where death is not null;