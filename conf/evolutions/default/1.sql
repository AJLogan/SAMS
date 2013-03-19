# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table attendance (
  id                        bigint auto_increment not null,
  attendance                tinyint(1) default 0,
  slot_id                   bigint,
  student_id                bigint,
  constraint pk_attendance primary key (id))
;

create table course (
  id                        bigint auto_increment not null,
  course_id                 varchar(255),
  start_year                varchar(255),
  name                      varchar(255),
  duration                  varchar(255),
  mode_study                varchar(255),
  constraint pk_course primary key (id))
;

create table module (
  id                        bigint auto_increment not null,
  crn                       varchar(255),
  module_code               varchar(255),
  module_name               varchar(255),
  staff_id                  bigint,
  constraint pk_module primary key (id))
;

create table slot (
  id                        bigint auto_increment not null,
  slot_id                   varchar(255),
  room_id                   varchar(255),
  year                      varchar(255),
  semester                  integer,
  week                      integer,
  day                       integer,
  start_time                integer,
  duration                  integer,
  module_id                 bigint,
  constraint pk_slot primary key (id))
;

create table staff (
  id                        bigint auto_increment not null,
  staff_num                 varchar(255),
  title                     varchar(255),
  forename                  varchar(255),
  surname                   varchar(255),
  office                    varchar(255),
  email                     varchar(255),
  phone                     varchar(255),
  constraint pk_staff primary key (id))
;

create table student (
  id                        bigint auto_increment not null,
  student_id                varchar(255),
  title                     varchar(255),
  forename                  varchar(255),
  surname                   varchar(255),
  email                     varchar(255),
  gender                    varchar(255),
  address_line1             varchar(255),
  address_line2             varchar(255),
  address_line3             varchar(255),
  postcode                  varchar(255),
  international             tinyint(1) default 0,
  course_id                 bigint,
  constraint pk_student primary key (id))
;


create table module_course (
  module_id                      bigint not null,
  course_id                      bigint not null,
  constraint pk_module_course primary key (module_id, course_id))
;
alter table attendance add constraint fk_attendance_slot_1 foreign key (slot_id) references slot (id) on delete restrict on update restrict;
create index ix_attendance_slot_1 on attendance (slot_id);
alter table attendance add constraint fk_attendance_student_2 foreign key (student_id) references student (id) on delete restrict on update restrict;
create index ix_attendance_student_2 on attendance (student_id);
alter table module add constraint fk_module_staff_3 foreign key (staff_id) references staff (id) on delete restrict on update restrict;
create index ix_module_staff_3 on module (staff_id);
alter table slot add constraint fk_slot_module_4 foreign key (module_id) references module (id) on delete restrict on update restrict;
create index ix_slot_module_4 on slot (module_id);
alter table student add constraint fk_student_course_5 foreign key (course_id) references course (id) on delete restrict on update restrict;
create index ix_student_course_5 on student (course_id);



alter table module_course add constraint fk_module_course_module_01 foreign key (module_id) references module (id) on delete restrict on update restrict;

alter table module_course add constraint fk_module_course_course_02 foreign key (course_id) references course (id) on delete restrict on update restrict;

# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table attendance;

drop table course;

drop table module_course;

drop table module;

drop table slot;

drop table staff;

drop table student;

SET FOREIGN_KEY_CHECKS=1;

