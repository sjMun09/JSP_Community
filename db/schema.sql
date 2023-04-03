# DB 생성
DROP DATABASE IF EXISTS JSP_Community;
CREATE DATABASE JSP_Community;

# DB 선택
USE JSP_Community;

# 게시물 테이블 생성
CREATE TABLE article (
	id INT(10) unsigned not null primary key auto_increment,
	regDate datetime not null,
    updateDate datetime not null,
    title CHAR(250) NOT NULL,
    `body` LONGTEXT NOT NULL
);

# 게시물 테이블 데이터 생성
insert into article
set regDate = NOW(),
updateDate = NOW(),
title = '제목1',
`body` = '내용1';

insert into article
set regDate = NOW(),
updateDate = NOW(),
title = '제목2',
`body` = '내용2';

insert into article
set regDate = NOW(),
updateDate = NOW(),
title = '제목3',
`body` = '내용3';
