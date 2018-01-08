create table grade(
	gid int primary key,
	gname varchar(20) not null,
	gdesc varchar(50)
);

create table student(
	sid int primary key,
	sname varchar(20) not null,
	gid int
);
alter table student add constraint fk_student_gid foreign key(gid) references grade(gid);

create table project(
  proid int primary key,
  proname varchar(20) not null
);

create table employee(
  empid int primary key,
  empname varchar(20) not NULL
);

create table proemp(
  rproid int,
  rempid int
);
alter table proemp add constraint fk_rproid foreign key(rproid) references project(proid);
alter table proemp add constraint fk_rempid foreign key(rempid) references employee(empid);
