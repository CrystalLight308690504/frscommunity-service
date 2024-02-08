CREATE DATABASE if not exists `frscommunity_user` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
use frscommunity_user;

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

drop table if exists role;
CREATE TABLE `role`
(
    `role_id`      bigint NOT NULL,
    `role_name`    varchar(20)  DEFAULT NULL COMMENT '角色名',
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
    `user_state_id`   bigint         DEFAULT NULL COMMENT '用户状态id',
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
    ·role_id·skating_type bigint DEFAULT 1 ,
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

drop table if exists user_state;
CREATE TABLE `user_state`
(
    `user_status_id`    bigint        primary key ,
    `status_name`       varchar(64) DEFAULT NULL,
    `description`       varchar(64) DEFAULT NULL,
    `constraint_time` long      DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
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


/*==============================================================*/
/* database: frscommunity_Blog                                         */
/*==============================================================*/
CREATE DATABASE if not exists `frscommunity_Blog` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
use frscommunity_Blog;
/*==============================================================*/
/* Table: skating_type                                         */
/*==============================================================*/
drop table if exists skating_type;
create table skating_type
(
    skating_type_id bigint      not null,
    name            varchar(12) null,
    description     varchar(64) null,
    created_time    DATETIME,
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
    is_showed    int default  1   null,
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
    user_of_blog_id  bigint null comment '博主id',
    blog_id           bigint null,
    created_time      datetime   null,
    constraint PK_BLOG_CLICK_APPLAUSE primary key (click_applause_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

drop table if exists user_follower;



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