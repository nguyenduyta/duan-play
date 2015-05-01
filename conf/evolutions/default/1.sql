# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table academic (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  description               varchar(255),
  constraint pk_academic primary key (id))
;

create table contract (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  description               varchar(255),
  constraint pk_contract primary key (id))
;

create table degree (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  description               varchar(255),
  constraint pk_degree primary key (id))
;

create table dept (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  description               varchar(255),
  constraint pk_dept primary key (id))
;

create table employ (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  address                   varchar(255),
  email                     varchar(255),
  birthday                  datetime,
  phone_number              varchar(255),
  gender                    tinyint(1) default 0,
  workplace                 varchar(255),
  degree_id                 bigint,
  dept_id                   bigint,
  academic_id               bigint,
  contract_id               bigint,
  constraint pk_employ primary key (id))
;

create table user (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  email                     varchar(255),
  password                  varchar(255),
  admin                     tinyint(1) default 0,
  constraint pk_user primary key (id))
;

alter table employ add constraint fk_employ_degree_1 foreign key (degree_id) references degree (id) on delete restrict on update restrict;
create index ix_employ_degree_1 on employ (degree_id);
alter table employ add constraint fk_employ_dept_2 foreign key (dept_id) references dept (id) on delete restrict on update restrict;
create index ix_employ_dept_2 on employ (dept_id);
alter table employ add constraint fk_employ_academic_3 foreign key (academic_id) references academic (id) on delete restrict on update restrict;
create index ix_employ_academic_3 on employ (academic_id);
alter table employ add constraint fk_employ_contract_4 foreign key (contract_id) references contract (id) on delete restrict on update restrict;
create index ix_employ_contract_4 on employ (contract_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table academic;

drop table contract;

drop table degree;

drop table dept;

drop table employ;

drop table user;

SET FOREIGN_KEY_CHECKS=1;

