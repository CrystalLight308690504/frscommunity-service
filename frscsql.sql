CREATE DATABASE if not exists `frscommunity` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
use frscommunity;
drop table if exists api;
CREATE TABLE `api`
(
    `api_id`      bigint NOT NULL,
    `method_name` varchar(12) DEFAULT NULL,
    `url`         varchar(12) DEFAULT NULL COMMENT '请求aoi路径',
    `code`        varchar(12) DEFAULT NULL COMMENT 'api 标识',
    PRIMARY KEY (`api_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

drop table if exists button;
CREATE TABLE `button`
(
    `btn_id`      bigint NOT NULL,
    `btn_name`    varchar(12) DEFAULT NULL,
    `description` varchar(64) DEFAULT NULL,
    `code`        varchar(12) DEFAULT NULL,
    PRIMARY KEY (`btn_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

drop table if exists menu;
CREATE TABLE `menu`
(
    `menu_id`     bigint NOT NULL,
    `parent_id`   bigint      DEFAULT NULL,
    `menu_name`   varchar(12) DEFAULT NULL,
    `description` varchar(64) DEFAULT NULL COMMENT '菜单说明',
    `code`        varchar(12) DEFAULT NULL COMMENT '菜单标识',
    PRIMARY KEY (`menu_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

drop table if exists permission;
CREATE TABLE `permission`
(
    `permission_id`   bigint NOT NULL,
    `permission_name` varchar(20)  DEFAULT NULL,
    `description`     varchar(255) DEFAULT NULL COMMENT '描述',
    `code`            varchar(20)  DEFAULT NULL COMMENT '权限标识符',
    `type`            tinyint      DEFAULT NULL COMMENT '权限类型 0表示api 1表示按钮 2表示菜单',
    `type_id`         bigint       DEFAULT NULL COMMENT '类型的id',
    PRIMARY KEY (`permission_id`),
    KEY `index_type` (`type`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

drop table if exists permission_api;
CREATE TABLE `permission_api`
(
    `permission_id` bigint DEFAULT NULL,
    `api_id`        bigint DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

drop table if exists permission_menu;
CREATE TABLE `permission_menu`
(
    `permission_id` bigint DEFAULT NULL,
    `btn_id`        bigint DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

drop table if exists permission_button;
CREATE TABLE `permission_button`
(
    `permission_id` bigint DEFAULT NULL,
    `menu_id`       bigint DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

drop table if exists role;
CREATE TABLE `role`
(
    `role_id`      bigint NOT NULL,
    `orle_name`    varchar(20)  DEFAULT NULL COMMENT '角色名',
    `description`  varchar(255) DEFAULT NULL COMMENT '描述',
    `created_time` datetime     DEFAULT NULL COMMENT '创建时间',
    `code`         varchar(255) DEFAULT NULL COMMENT '标识码',
    PRIMARY KEY (`role_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

drop table if exists user;
CREATE TABLE `user`
(
    `user_id`         bigint NOT NULL COMMENT '用户id',
    `user_info_id`    bigint         DEFAULT NULL COMMENT '用户id',
    `user_name`       char(32)       DEFAULT NULL COMMENT '用户名，登陆名',
    `email`           char(64)       DEFAULT NULL COMMENT '邮箱',
    `password`        varchar(32)    DEFAULT NULL COMMENT '密码',
    `phone_number`    varchar(32)    DEFAULT NULL,
    `profile`         text           DEFAULT Null comment '头像',
    `introduce`       varchar(255)   DEFAULT NULL COMMENT '个人介绍',
    `credit`          bigint         DEFAULT NULL COMMENT '用户积分',
    `gender`          char(255)      DEFAULT '男' COMMENT '性别 0 为女 1为男',
    `created_time`    datetime       DEFAULT NULL COMMENT '用户创建时间',
    `last_login_time` datetime       DEFAULT NULL COMMENT '上次登陆时间',
    `login_time`      datetime       DEFAULT NULL COMMENT '本次登陆时间',
    `profession`      varchar(255)   DEFAULT NULL COMMENT '职业',
    `description`     varbinary(255) DEFAULT NULL COMMENT '用户自我描述',
    `address_ip`      varchar(255)   DEFAULT NULL COMMENT '上次登陆ip地址',
    PRIMARY KEY (`user_id`),
    index `Index_phone` (`phone_number`),
    index `Index_email` (`email`),
    index `Index_username` (`user_name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

drop table if exists user_role;
CREATE TABLE `user_role`
(
    `role_id` bigint DEFAULT NULL,
    `user_id` bigint DEFAULT NULL COMMENT '用户id'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

drop table if exists user_status;
CREATE TABLE `user_status`
(
    `user_id`           bigint        DEFAULT NULL COMMENT '用户id',
    `user_status_id`    bigint        DEFAULT NULL,
    `status_name`       varbinary(64) DEFAULT NULL,
    `status_dated_tims` char(10)      DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

/*==============================================================*/
/* Table: skating_type                                         */
/*==============================================================*/
drop table if exists skating_type;
create table skating_type
(
    skating_type_id bigint      not null,
    name            varchar(12) null,
    description     varchar(64) null,
    constraint PK_SKATTING_TYPE primary key (skating_type_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*==============================================================*/
/* Table: self_category                                         */
/*==============================================================*/
drop table if exists blog_category;
create table blog_category
(
    category_id   bigint    not null,
    parent_id     bigint    null,
    user_id       bigint    null,
    category_name char(50)  null,
    description   char(100) null,
    created_time  datetime  null,

    constraint PK_SELF_CATEGORY primary key (category_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


/*==============================================================*/
/* Table: blog                                                  */
/*==============================================================*/
drop table if exists blog;
create table blog
(
    skating_type_id bigint       null,
    category_id     bigint       null,
    blog_id         bigint       not null,
    user_id         bigint       null,
    blog_title      varchar(100) null,
    content         text         null,
    next_content_id bigint       null,
    right_id        bigint       null,
    created_time    datetime     null,
    constraint PK_BLOG primary key (blog_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*==============================================================*/
/* Table: blog_criticism                                        */
/*==============================================================*/
drop table if exists blog_criticism;
create table blog_criticism
(
    criticism_id    bigint   not null,
    blog_id         bigint   null,
    user_id         bigint   null,
    content         text     null,
    next_content_id bigint   null,
    created_time    datetime null,
    constraint PK_BLOG_CRITICISM primary key (criticism_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*==============================================================*/
/* Table: blog_click_applause                                   */
/*==============================================================*/
drop table if exists blog_click_applause;
create table blog_click_applause
(
    click_applause_id bigint not null,
    user_id           bigint null,
    blog_id           bigint null,
    created_time      datetime   null,
    constraint PK_BLOG_CLICK_APPLAUSE primary key (click_applause_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

drop table if exists user_follower;

/*==============================================================*/
/* Table: user_follower                                         */
/*==============================================================*/
create table user_follower
(
    follower_id      bigint not null,
    user_id          bigint comment '关注的用户id',
    user_followed_id bigint comment '被关注的用户id',
    created_time     datetime comment '创建事件',
    primary key (follower_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

drop table if exists blog_collection;
/*==============================================================*/
/* Table: blog_collection                                       */
/*==============================================================*/
create table blog_collection
(
    collection_id bigint   not null,
    user_id       bigint   null,
    blog_id       bigint   null,
    created_time  datetime null,
    constraint PK_BLOG_COLLECTION primary key (collection_id)
)



