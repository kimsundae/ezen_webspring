USE springweb;
DROP TABLE todo;
# 테이블 생성 코드 범위 지정후 컨트롤+엔터
CREATE TABLE  todo(
                      tno int auto_increment ,
                      tcontent varchar(100) ,
                      tstate boolean ,
                      primary key( tno )
);
use springweb;
select * from phone_entity;