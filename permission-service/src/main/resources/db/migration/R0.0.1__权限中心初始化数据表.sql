/**
 * 菜单表
 */
CREATE TABLE IF NOT EXISTS `menus` (
   `id` bigint NOT NULL AUTO_INCREMENT,
   `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名',
   `parent_id` bigint NOT NULL DEFAULT '0' COMMENT '上一级菜单，0为根菜单',
   `type` tinyint(1) NOT NULL DEFAULT '1' COMMENT '菜单类型，1-页面，2-按钮',
   `level` tinyint NOT NULL DEFAULT '0' COMMENT '层级，0代表最顶级',
   `disabled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否被禁，1-禁用，0-启用',
   `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
   `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*
 *资源表
 */
CREATE TABLE IF NOT EXISTS `resources` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '资源名',
    `path` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '资源路径',
    `method` bigint NOT NULL DEFAULT '0' COMMENT '上一级菜单，0为根菜单',
    `disabled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否被禁，1-禁用，0-启用',
    `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/**
 * 菜单和用户关系表
 */
CREATE TABLE IF NOT EXISTS `menu_user_relation` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `menu_id` bigint NOT NULL COMMENT '菜单ID',
    `user_id` bigint NOT NULL COMMENT '用户ID',
    `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/**
 * 菜单和用户关系表
 */
CREATE TABLE IF NOT EXISTS `menu_resource_relation` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `menu_id` bigint NOT NULL COMMENT '菜单ID',
    `resource_id` bigint NOT NULL COMMENT '资源ID',
    `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;