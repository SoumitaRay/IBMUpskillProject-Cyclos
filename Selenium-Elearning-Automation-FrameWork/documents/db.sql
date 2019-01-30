create table login(
username varchar(50) not null,
password varchar(50));


insert into login values("admin", "admin@123"); 
insert into login values("naveen", "testing@123"); 



create table grantloan_admin(
memberlogin varchar(50) not null,
amount varchar(50),
loan_description varchar(20));


insert into grantloan_admin values("manzoor mehadi", "10","home loan"); 
insert into grantloan_admin values("SoumitaAAA", "20","personal loan"); 
