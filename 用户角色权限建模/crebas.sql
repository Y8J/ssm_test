/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/4/20 10:48:39                           */
/*==============================================================*/


drop table if exists t_permissions;

drop table if exists t_role;

drop table if exists t_role_perm;

drop table if exists t_user;

drop table if exists t_user_role;

/*==============================================================*/
/* Table: t_permissions                                         */
/*==============================================================*/
create table t_permissions
(
   id                   numeric(8,0) not null comment '����ID',
   perm_name            varchar(1024) comment 'Ȩ������',
   create_time          datetime comment '����ʱ��',
   note                 varchar(1024) comment 'Ȩ��˵��',
   primary key (id)
);

/*==============================================================*/
/* Table: t_role                                                */
/*==============================================================*/
create table t_role
(
   id                   numeric(8,0) not null comment '����id',
   role_name            varchar(1024) comment '��ɫ����',
   create_time          datetime comment '����ʱ��',
   note                 varchar(1024) comment '��ɫ˵��',
   primary key (id)
);

/*==============================================================*/
/* Table: t_role_perm                                           */
/*==============================================================*/
create table t_role_perm
(
   id                   numeric(8,0) not null comment '����ID',
   role_id              numeric(8,0) comment '��ɫID',
   prem_id              numeric(8,0) comment 'Ȩ��ID',
   perm_type            numeric(8,0) comment 'Ȩ������(1:����,2:������)',
   primary key (id)
);

/*==============================================================*/
/* Table: t_user                                                */
/*==============================================================*/
create table t_user
(
   id                   numeric(8,0) not null comment '����id',
   user_uame            varchar(1024) comment '�û���',
   password             varchar(1024) comment '����',
   sex                  numeric(8,0) comment '�Ա�(1��:0Ů)',
   mobile               varchar(1024) comment '�ֻ���',
   emil                 varchar(1024) comment '��������',
   create_time          datetime comment '����ʱ��',
   update_time          datetime comment '�޸�ʱ��',
   primary key (id)
);

/*==============================================================*/
/* Table: t_user_role                                           */
/*==============================================================*/
create table t_user_role
(
   id                   numeric(8,0) not null comment '����ID',
   user_id              numeric(8,0) comment '�û�ID',
   role_id              numeric(8,0) comment '��ɫID',
   role_type            numeric(8,0) comment '��ɫ����(1:��ʹ��2:������)',
   primary key (id)
);

