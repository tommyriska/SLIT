create table module(
module_id int not null auto_increment,
module_name varchar(50) not null,
description varchar(1000),
rights int,
primary key(module_id)
);

create table resource(
resource_id int not null auto_increment,
module_id int, 
description varchar(100),
primary key(resource_id),
foreign key(module_id) references module(module_id)
);

create table requirement(
requirement_id int not null auto_increment,
module_id int,
description varchar(100),
primary key(requirement_id),
foreign key(module_id) references module(module_id)
);

create table blog(
blog_id int not null auto_increment,
primary key(blog_id)
);

create table blog_post(
blog_post_id int not null auto_increment,
blog_id int,
blog_post_date date,
title varchar(100),
content varchar(1000),
primary key(blog_post_id),
foreign key(blog_id) references blog(blog_id)
);

create table semester_plan(
semester_plan_id int not null auto_increment,
primary key(semester_plan_id)
);

create table module_plan(
module_plan_id int not null auto_increment,
semester_plan_id int,
module_id int,
planned_date date,
primary key(module_plan_id),
foreign key(semester_plan_id) references semester_plan(semester_plan_id),
foreign key(module_id) references module(module_id)
);

create table users(
users_id int auto_increment,
first_name varchar(100),
last_name varchar(100),
email varchar(100),
phone_number int,
rights int,
blog_id int,
semester_plan_id int,
primary key(users_id),
foreign key(blog_id) references blog(blog_id),
foreign key(semester_plan_id) references semester_plan(semester_plan_id)
);

create table delivery(
delivery_id int not null auto_increment,
module_id int,
users_id int,
delivery_status int,
delivery_comment varchar(500),
date_delivered date,
date_approved date,
review_comment varchar(1000),
primary key(delivery_id),
foreign key(module_id) references module(module_id),
foreign key(users_id) references users(users_id)
);

create table delivery_file(
delivery_file_id int not null auto_increment,
delivery_id int,
content longblob,
filename varchar(100),
filetype varchar(10),
primary key(delivery_file_id),
foreign key(delivery_id) references delivery(delivery_id)
);

#TESTDATA
insert into module values(null, "Modul 1", "Testmodul", 2);
insert into resource values(null, 1, "www.test.no");
insert into requirement values(null, 1, "Læremål1");
insert into blog values(null);
insert into blog_post values(null, 1, 20161005, "Bloginnlegg 1", "Dette er en test");
insert into semester_plan values(null);
insert into module_plan values(null, 1, 1, 20161005);
insert into users values(null, "Ådne", "Pådne", "pådne@mail.com", 911, 1, 1, 1);
insert into delivery values(null, 1, 1, 1, "Test", 20161005, 20161005);
insert into delivery_file values(null, 1, 1110010110100011100101, "testfil", ".txt");

insert into delivery_file values(null, 1, "fissefarfar", "testfil133769", ".txt");

insert into blog values(null);
insert into blog values(null);
insert into blog values(null);
insert into blog values(null);
insert into blog values(null);
insert into blog values(null);
insert into blog values(null);
insert into blog values(null);
insert into blog values(null);
insert into blog values(null);
insert into blog values(null);
insert into blog values(null);
insert into blog values(null);
insert into blog values(null);
insert into blog values(null);
insert into blog values(null);
insert into blog values(null);
insert into blog values(null);
insert into blog values(null);
insert into blog values(null);

insert into semester_plan values(null);
insert into semester_plan values(null);
insert into semester_plan values(null);
insert into semester_plan values(null);
insert into semester_plan values(null);
insert into semester_plan values(null);
insert into semester_plan values(null);
insert into semester_plan values(null);
insert into semester_plan values(null);
insert into semester_plan values(null);
insert into semester_plan values(null);
insert into semester_plan values(null);
insert into semester_plan values(null);
insert into semester_plan values(null);
insert into semester_plan values(null);
insert into semester_plan values(null);
insert into semester_plan values(null);
insert into semester_plan values(null);
insert into semester_plan values(null);

insert into users values(null, "Tommy", "Riska", "tommyriska@mail.com", 90830416, 1, 2, 2);
insert into users values(null, "Tor", "Arve", "torarve@mail.com", 12345678, 1, 3, 3);
insert into users values(null, "Anders", "Berntsen", "berntsen@mail.com", 23456789, 0, 4, 4);
insert into users values(null, "Svein", "Bruce", "bruce@mail.com", 34567890, 0, 5, 5);
insert into users values(null, "Mikael", "Kile", "kile@mail.com", 45678901, 0, 6, 6);
insert into users values(null, "Ola", "Nordmann", "nordmann@mail.com", 91111111, 0, 7, 7);
insert into users values(null, "Pål", "Pådden", "pådden@mail.com", 91122222, 0, 8, 8);
insert into users values(null, "Vetle", "Horpestad", "horpis@mail.com", 91133333, 0, 9, 9);
insert into users values(null, "Øystein", "Traskjær", "traskjær@mail.com", 91144444, 0, 10, 10);
insert into users values(null, "Preben", "Tjemsland", "tjemsland@mail.com", 91155555, 0, 11, 11);
insert into users values(null, "Even", "Foreleser", "evenf@mail.com", 91166666, 2, 12, 12);
insert into users values(null, "Hallgeir", "Gud1", "gud1@mail.com", 91177777, 2, 13, 13);
insert into users values(null, "Hans Olav", "Olavsen", "olavsen@mail.com", 91188888, 0, 14, 14);
insert into users values(null, "Kjetil", "Kjetta", "kjetta@mail.com", 91199999, 0, 15, 15);
insert into users values(null, "Dank memes", "Bemes", "dankmemescantmeltdankmemes@mail.com", 91121212, 0, 16, 16);
insert into users values(null, "Erlend", "Nevestveit", "nevestveit@mail.com", 91113133, 0, 17, 17);
insert into users values(null, "Ørjan", "Vier", "vier@mail.com", 91113131, 0, 18, 18);
insert into users values(null, "Eirik", "Ravndal", "ravndal@mail.com", 91143434, 0, 19, 19);
insert into users values(null, "Erling", "Erlingsen", "erlingsen@mail.com", 91165656, 0, 20, 20);