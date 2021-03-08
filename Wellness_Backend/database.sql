use collaboration;

create table User(
UserId int not null auto_increment,
FirstName varchar(30),
LastName varchar(30),
UserName varchar(20),
Password varchar(20),
Email varchar(40),
Role varchar(5),
Status varchar(10),
IsOnline boolean,
Enabled boolean,
primary key (UserId)
);
drop table user;
select * from user;

create table Image (
id varchar(255) not null,
data longblob,
fileName varchar(255),
fileType varchar(255),
primary key (id)
);
select * from image;

create table Blog (
	BlogId int not null auto_increment,
    BlogTitle varchar(20),
    BlogContent varchar(300),
    BlogPosted date,
    Status varchar(10),
    NoOfLikes int,
    NoOfComments int,
    NoOfViews int,
    UserId int,
    UserName varchar(20),
    foreign key(UserId) references User(UserId),
    primary key(BlogId)
);
drop table blog;

create table BlogComments (
	BlogCommentsId int not null auto_increment,
    UserId int,
    UserName varchar(20),
    ProfileId int,
    Title varchar(20),
    NoOfLikes int,
    BlogComments varchar(300),
    CurrentDate date,
    BlogId int,
    foreign key(UserId) references User(UserId),
    primary key(BlogCommentsId)
);

select * from blogcomments;
drop table blogcomments;
create table Job(
	JobId int not null auto_increment,
    JobName varchar(30),
    JobDescription varchar(200),
    JobLocation varchar(20),
    IsActive boolean,
    primary key(JobId)
);
