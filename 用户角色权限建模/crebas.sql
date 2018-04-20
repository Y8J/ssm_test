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
   id                   numeric(8,0) not null comment '主键ID',
   perm_name            varchar(1024) comment '权限名称',
   create_time          datetime comment '创建时间',
   note                 varchar(1024) comment '权限说明',
   primary key (id)
);

/*==============================================================*/
/* Table: t_role                                                */
/*==============================================================*/
create table t_role
(
   id                   numeric(8,0) not null comment '主键id',
   role_name            varchar(1024) comment '角色名称',
   create_time          datetime comment '创建时间',
   note                 varchar(1024) comment '角色说明',
   primary key (id)
);

/*==============================================================*/
/* Table: t_role_perm                                           */
/*==============================================================*/
create table t_role_perm
(
   id                   numeric(8,0) not null comment '主键ID',
   role_id              numeric(8,0) comment '角色ID',
   prem_id              numeric(8,0) comment '权限ID',
   perm_type            numeric(8,0) comment '权限类型(1:可用,2:不可用)',
   primary key (id)
);

/*==============================================================*/
/* Table: t_user                                                */
/*==============================================================*/
create table t_user
(
   id                   numeric(8,0) not null comment '主键id',
   user_uame            varchar(1024) comment '用户名',
   password             varchar(1024) comment '密码',
   sex                  numeric(8,0) comment '性别(1男:0女)',
   mobile               varchar(1024) comment '手机号',
   emil                 varchar(1024) comment '电子邮箱',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '修改时间',
   primary key (id)
);

/*==============================================================*/
/* Table: t_user_role                                           */
/*==============================================================*/
create table t_user_role
(
   id                   numeric(8,0) not null comment '主键ID',
   user_id              numeric(8,0) comment '用户ID',
   role_id              numeric(8,0) comment '角色ID',
   role_type            numeric(8,0) comment '角色类型(1:可使用2:不可用)',
   primary key (id)
);

