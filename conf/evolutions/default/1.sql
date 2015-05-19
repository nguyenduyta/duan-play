# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table academic (
  id                        bigint not null,
  name                      varchar(255),
  description               varchar(255),
  constraint pk_academic primary key (id))
;

create table contract (
  id                        bigint not null,
  name                      varchar(255),
  description               varchar(255),
  constraint pk_contract primary key (id))
;

create table degree (
  id                        bigint not null,
  name                      varchar(255),
  description               varchar(255),
  constraint pk_degree primary key (id))
;

create table dept (
  id                        bigint not null,
  name                      varchar(255),
  description               varchar(255),
  constraint pk_dept primary key (id))
;

create table employ (
  id                        bigint not null,
  name                      varchar(255),
  address                   varchar(255),
  email                     varchar(255),
  birthday                  timestamp,
  phone_number              varchar(255),
  gender                    boolean,
  workplace                 varchar(255),
  degree_id                 bigint,
  dept_id                   bigint,
  academic_id               bigint,
  contract_id               bigint,
  constraint pk_employ primary key (id))
;

create table users (
  id                        bigint not null,
  name                      varchar(255),
  email                     varchar(255),
  password                  varchar(255),
  admin                     boolean,
  constraint pk_users primary key (id))
;

create sequence academic_seq;

create sequence contract_seq;

create sequence degree_seq;

create sequence dept_seq;

create sequence employ_seq;

create sequence users_seq;

alter table employ add constraint fk_employ_degree_1 foreign key (degree_id) references degree (id);
create index ix_employ_degree_1 on employ (degree_id);
alter table employ add constraint fk_employ_dept_2 foreign key (dept_id) references dept (id);
create index ix_employ_dept_2 on employ (dept_id);
alter table employ add constraint fk_employ_academic_3 foreign key (academic_id) references academic (id);
create index ix_employ_academic_3 on employ (academic_id);
alter table employ add constraint fk_employ_contract_4 foreign key (contract_id) references contract (id);
create index ix_employ_contract_4 on employ (contract_id);



# --- !Downs

drop table if exists academic cascade;

drop table if exists contract cascade;

drop table if exists degree cascade;

drop table if exists dept cascade;

drop table if exists employ cascade;

drop table if exists users cascade;

drop sequence if exists academic_seq;

drop sequence if exists contract_seq;

drop sequence if exists degree_seq;

drop sequence if exists dept_seq;

drop sequence if exists employ_seq;

drop sequence if exists users_seq;

