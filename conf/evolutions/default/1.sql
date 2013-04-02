# --- First database schema

# --- !Ups

create table course (
  id                        bigint not null auto_increment,
  course_id                 varchar(255) not null,
  start_year                varchar(4) not null,
  name                      varchar(255) not null,
  duration                  integer not null,
  mode_study                varchar(255) not null,
  constraint pk_course primary key (id)
) engine=innodb
;

create table student (
  id                        bigint not null auto_increment,
  number                varchar(255) not null,
  title                     varchar(255) not null,
  forename                  varchar(255) not null,
  surname                   varchar(255) not null,
  email                     varchar(255),
  gender                    varchar(255),
  address_line_1            varchar(255),
  address_line_2            varchar(255),
  address_line_3            varchar(255),
  postcode                  varchar(255),
  international             tinyint(1) default 0,
  course_id					        bigint,
  constraint pk_student primary key (id)
) engine=innodb
;

create table staff (
  id                        bigint not null auto_increment,
  number                  varchar(255) not null,
  title                     varchar(255) not null,
  forename                  varchar(255) not null,
  surname                   varchar(255) not null,
  office                    varchar(255),
  email                     varchar(255),
  phone                     varchar(255),
  constraint pk_staff primary key (id)
) engine=innodb
;

create table module (
  id                        bigint not null auto_increment,
  crn                varchar(255) not null,
  module_code               varchar(255) not null,
  name                      varchar(255) not null,
  staff_id                  bigint,
  constraint pk_module primary key (id)
) engine=innodb
;

create table attendance (
  id                        bigint not null auto_increment,
  attendance                tinyint not null,
  student_id                bigint,
  slot_id                   bigint,
  constraint pk_attendance primary key (id)
) engine=innodb
;

create table slot (
  id                        bigint not null auto_increment,
  number                   varchar(255) not null,
  room_id                   varchar(255) not null,
  year                      varchar(255) not null,
  semester                  int,
  week                      int,
  day                       int,
  start_time                varchar(4),
  duration                  int,
  module_id                 bigint,
  constraint pk_slot primary key (id)
) engine=innodb
;

create table course_module (
  id                        bigint not null auto_increment,
  course_id                 bigint,
  module_id                 bigint,
  constraint pk_course_module primary key (id)
)engine=innodb
;

alter table student add constraint fk_student_course_1 foreign key (course_id) references course (id) on delete set null on update restrict;
create index ix_student_course_1 on student (course_id);

alter table module add constraint fk_module_staff_1 foreign key (staff_id) references staff (id) on delete set null on update restrict;
create index ix_module_staff_1 on module (staff_id);

alter table slot add constraint fk_slot_module_1 foreign key (module_id) references module (id) on delete set null on update restrict;
create index ix_slot_module_1 on slot (module_id);

alter table attendance add constraint fk_attendance_slot_1 foreign key (slot_id) references slot (id) on delete set null on update restrict;
create index ix_attendance_slot_1 on attendance (slot_id);

alter table attendance add constraint fk_student_attendance_1 foreign key (student_id) references student (id) on delete set null on update restrict;
create index ix_attendance_student_1 on attendance (student_id);

alter table course_module add constraint fk_course_module_module_1 foreign key (module_id) references module (id) on delete set null on update restrict;
create index ix_course_module_module_1 on course_module (module_id);

alter table course_module add constraint fk_course_course_module_1 foreign key (course_id) references course (id) on delete set null on update restrict;
create index ix_course_module_course_1 on course_module (course_id);


# --- !Downs

SET FOREIGN_KEY_CHECKS = 0

drop table if exists course;
drop table if exists student;
drop table if exists staff;
drop table if exists module;
drop table if exists attendance;
drop table if exists slot;
drop table if exists course_module;

SET FOREIGN_KEY_CHECKS = 1

