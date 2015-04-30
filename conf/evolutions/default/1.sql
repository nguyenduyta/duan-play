# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table employ (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  address                   varchar(255),
  email                     varchar(255),
  birthday                  datetime,
  phone_number              varchar(255),
  gender                    varchar(255),
  degree                    varchar(255),
  workplace                 varchar(255),
  admin                     tinyint(1) default 0,
  dept                      varchar(255),
  academic                  varchar(255),
  contract                  varchar(255),
  constraint pk_employ primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table employ;

SET FOREIGN_KEY_CHECKS=1;

