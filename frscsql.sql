CREATE DATABASE if not exists `frscommunity` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
use frscommunity;

CREATE TABLE `api` (
  `api_id` bigint NOT NULL,
  `method_name` varchar(12) DEFAULT NULL,
  `url` varchar(12) DEFAULT NULL COMMENT '请求aoi路径',
  `code` varchar(12) DEFAULT NULL COMMENT 'api 标识',
  PRIMARY KEY (`api_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `button` (
  `btn_id` bigint NOT NULL,
  `btn_name` varchar(12) DEFAULT NULL,
  `description` varchar(64) DEFAULT NULL,
  `code` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`btn_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `menu` (
  `menu_id` bigint NOT NULL,
  `parent_id` bigint DEFAULT NULL,
  `menu_name` varchar(12) DEFAULT NULL,
  `description` varchar(64) DEFAULT NULL COMMENT '菜单说明',
  `code` varchar(12) DEFAULT NULL COMMENT '菜单标识',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `permission` (
  `permission_id` bigint NOT NULL,
  `permission_name` varchar(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `code` varchar(20) DEFAULT NULL COMMENT '权限标识符',
  `type` tinyint DEFAULT NULL COMMENT '权限类型 0表示api 1表示按钮 2表示菜单',
  `type_id` bigint DEFAULT NULL COMMENT '类型的id',
  PRIMARY KEY (`permission_id`),
  KEY `index_type` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `permission_api` (
  `permission_id` bigint DEFAULT NULL,
  `api_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `permission_menu` (
  `permission_id` bigint DEFAULT NULL,
  `btn_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `permisson_button` (
  `permission_id` bigint DEFAULT NULL,
  `menu_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `role` (
  `role_id` bigint NOT NULL,
  `orle_name` varchar(20) DEFAULT NULL COMMENT '角色名',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `code` varchar(255) DEFAULT NULL COMMENT '标识码',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

drop table if exists 'user';
CREATE TABLE `user` (
  `user_id` bigint NOT NULL COMMENT '用户id',
  `user_info_id` bigint DEFAULT NULL COMMENT '用户id',
  `user_name` char(32) DEFAULT NULL COMMENT '用户名，登陆名',
  `email` char(64) DEFAULT NULL COMMENT '邮箱',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `phone_number` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `Index_phone` (`phone_number`),
  KEY `Index_email` (`email`),
  KEY `Index_username` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user_information` (
  `user_info_id` bigint NOT NULL COMMENT '用户id',
  `profile` varchar(255) DEFAULT NULL COMMENT '头像',
  `introduce` varchar(255) DEFAULT NULL COMMENT '个人介绍',
  `credit` bigint DEFAULT NULL COMMENT '用户积分',
  `gender` bit(1) DEFAULT NULL COMMENT '性别 0 为女 1为男',
  `created_time` datetime DEFAULT NULL COMMENT '用户创建时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '上次登陆时间',
  `login_time` datetime DEFAULT NULL COMMENT '本次登陆时间',
  `profession` varchar(255) DEFAULT NULL COMMENT '职业',
  `description` varbinary(255) DEFAULT NULL COMMENT '用户自我描述',
  `address_ip` varchar(255) DEFAULT NULL COMMENT '上次登陆ip地址',
  PRIMARY KEY (`user_info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user_role` (
  `role_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL COMMENT '用户id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user_status` (
  `user_id` bigint DEFAULT NULL COMMENT '用户id',
  `user_status_id` bigint DEFAULT NULL,
  `status_name` varbinary(64) DEFAULT NULL,
  `status_dated_tims` char(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
