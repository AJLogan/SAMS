# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table student (
  id                        bigint auto_increment not null,
  student_id                varchar(255),
  student_forename          varchar(255),
  student_surname           varchar(255),
  constraint pk_student primary key (id))
;

create table task (
  id                        bigint auto_increment not null,
  label                     varchar(255),
  constraint pk_task primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table student;

drop table task;

SET FOREIGN_KEY_CHECKS=1;

