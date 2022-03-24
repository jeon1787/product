----계정삭제
--DROP USER c##product CASCADE;
----계정생성
--CREATE USER c##product IDENTIFIED BY product1234
--    DEFAULT TABLESPACE users 
--    TEMPORARY TABLESPACE temp 
--    PROFILE DEFAULT;
----계정권한부여    
--GRANT CONNECT, RESOURCE TO c##product; 
--GRANT CREATE VIEW, CREATE SYNONYM TO c##product; 
--GRANT UNLIMITED TABLESPACE TO c##product; 
----락 풀기
--ALTER USER c##product ACCOUNT UNLOCK;

--테이블 삭제
drop table product;
--테이블 생성
create table product (
    product_id  number,       --상품ID
    name        varchar2(50), --상품명
    quantity    number,       --상품수량
    price       number(10)    --상품가격
);

--시퀀스 삭제
drop sequence product_product_id_seq;
--시퀀스 생성
create sequence product_product_id_seq;

--기본키생성
alter table product add constraint product_product_id_pk primary key (product_id);

--샘플데이터
delete product;
insert into product (product_id, name, quantity, price)
values (product_product_id_seq.nextval, 'mouse', 50, 10000);
insert into product (product_id, name, quantity, price)
values (product_product_id_seq.nextval, 'keyboard', 35, 20000);
insert into product (product_id, name, quantity, price)
values (product_product_id_seq.nextval, 'monitor', 15, 150000);

commit;

----상품등록
--insert into product (product_id, name, quantity, price)
--values (product_product_id_seq.nextval, 'monitor', 150000, 15);
--
----상품조회
--select product_id, name, quantity, price
--  from product
-- where product_id = 1;
--
----전체조회
--select product_id, name, quantity, price
--  from product
-- order by product_id;
--
----상품수정
--update product
--   set name = 'speaker', quantity = 70, price = 5000
-- where product_id = 24;
--
----상품삭제
--delete from product
-- where product_id = 24;