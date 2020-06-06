create table userInfo(
userId int primary key auto_increment,
username varchar(20) unique,
password varchar(100) not null,
email varchar(40))
default charset = utf8;