# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table course (
  id                        bigint auto_increment not null,
  courseid                  varchar(255),
  start_year                varchar(255),
  course_name               varchar(255),
  duration                  varchar(255),
  mode_study                varchar(255),
  constraint pk_course primary key (id))
;

create table module (
  id                        bigint auto_increment not null,
  crn                       varchar(255),
  module_code               varchar(255),
  module_name               varchar(255),
  constraint pk_module primary key (id))
;

create table staff (
  id                        bigint auto_increment not null,
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
  title                     varchar(255),
  forename                  varchar(255),
  surname                   varchar(255),
  gender                    varchar(255),
  postcode                  varchar(255),
  international             tinyint(1) default 0,
  constraint pk_student primary key (id))
;

create table task (
  id                        bigint auto_increment not null,
  label                     varchar(255),
  constraint pk_task primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table course;

drop table module;

drop table staff;

drop table student;

drop table task;

SET FOREIGN_KEY_CHECKS=1;

