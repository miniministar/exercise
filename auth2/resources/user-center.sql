/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80019
Source Host           : localhost:3306
Source Database       : user-center

Target Server Type    : MYSQL
Target Server Version : 80019
File Encoding         : 65001

Date: 2020-10-09 09:51:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for oauth_access_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(256) NOT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  `authentication` blob,
  `refresh_token` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of oauth_access_token
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_approvals
-- ----------------------------
DROP TABLE IF EXISTS `oauth_approvals`;
CREATE TABLE `oauth_approvals` (
  `userId` varchar(256) DEFAULT NULL,
  `clientId` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `expiresAt` timestamp NULL DEFAULT NULL,
  `lastModifiedAt` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of oauth_approvals
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(256) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int DEFAULT NULL,
  `refresh_token_validity` int DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('c1', 'res1', '$2a$10$ntobkoGnp6PUtoeUbYToM.g.UdsCq.UcJd4plaX9WJVPxsIG0mdEa', 'all,ROLE_API,ROLE_ADMIN', 'authorization_code,password,client_credentials,implicit,refresh_token', 'http://www.baidu.com', null, null, null, null, 'false');
INSERT INTO `oauth_client_details` VALUES ('c2', 'res2', '$2a$10$ntobkoGnp6PUtoeUbYToM.g.UdsCq.UcJd4plaX9WJVPxsIG0mdEa', 'ROLE_API', 'authorization_code,password,client_credentials,implicit,refresh_token', 'http://www.baidu.com', null, null, null, null, 'false');

-- ----------------------------
-- Table structure for oauth_client_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_token`;
CREATE TABLE `oauth_client_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(256) NOT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of oauth_client_token
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `code` varchar(256) DEFAULT NULL,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of oauth_code
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of oauth_refresh_token
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `parent_id` int NOT NULL,
  `name` varchar(64) NOT NULL,
  `url` varchar(1024) DEFAULT NULL,
  `path` varchar(1024) DEFAULT NULL,
  `css` varchar(32) DEFAULT NULL,
  `sort` int NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `is_menu` int DEFAULT NULL COMMENT '是否菜单 1 是 2 不是',
  `hidden` int DEFAULT NULL COMMENT '是否隐藏,0 false 1 true',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '-1', '后台管理', 'javascript:;', '', 'layui-icon-set', '2', '2019-09-05 11:37:02', '2019-09-05 11:37:02', '1', '0');
INSERT INTO `sys_menu` VALUES ('2', '1', '用户管理', '#!user', 'system/user.html', 'layui-icon-friends', '2', '2017-11-17 16:56:59', '2019-09-05 11:37:26', '1', '0');
INSERT INTO `sys_menu` VALUES ('3', '1', '角色管理', '#!role', 'system/role.html', 'layui-icon-friends', '3', '2017-11-17 16:56:59', '2019-09-05 11:37:38', '1', '0');
INSERT INTO `sys_menu` VALUES ('4', '1', '菜单管理', '#!menus', 'system/menus.html', 'layui-icon-menu-fill', '4', '2017-11-17 16:56:59', '2019-09-05 11:37:57', '1', '0');
INSERT INTO `sys_menu` VALUES ('5', '1', '权限管理', '#!permissions', 'system/permissions.html', 'layui-icon-password', '5', '2017-11-17 16:56:59', '2019-09-05 11:37:47', '1', '0');
INSERT INTO `sys_menu` VALUES ('6', '1', '我的信息', '#!myInfo', 'system/myInfo.html', '', '10', '2017-11-17 16:56:59', '2018-09-02 06:12:24', '1', '1');
INSERT INTO `sys_menu` VALUES ('7', '-1', '认证中心', 'javascript:;', '', 'layui-icon-set', '1', '2017-11-17 16:56:59', '2019-03-26 06:56:46', '1', '0');
INSERT INTO `sys_menu` VALUES ('8', '7', '应用管理', '#!app', 'attestation/app.html', 'layui-icon-app', '9', '2017-11-17 16:56:59', '2018-08-25 10:57:42', '1', '0');
INSERT INTO `sys_menu` VALUES ('9', '7', '服务管理', '#!services', 'attestation/services.html', 'layui-icon-website', '8', '2017-11-17 16:56:59', '2018-09-02 09:34:13', '1', '0');
INSERT INTO `sys_menu` VALUES ('10', '7', '令牌管理', '#!token', 'attestation/token.html', 'layui-icon-util', '11', '2018-09-08 13:19:56', '2019-05-27 09:28:39', '1', '0');
INSERT INTO `sys_menu` VALUES ('11', '-1', '系统监控', 'javascript:;', '', 'layui-icon-set', '3', '2018-08-25 10:41:58', '2018-08-25 10:41:58', '1', '0');
INSERT INTO `sys_menu` VALUES ('12', '11', '注册中心', '#!register', 'http://127.0.0.1:1111/', 'layui-icon-engine', '2', '2017-11-17 16:56:59', '2019-05-25 20:34:50', '1', '0');
INSERT INTO `sys_menu` VALUES ('13', '11', '服务治理', '#!eureka', 'eureka/list.html', 'layui-icon-engine', '1', '2018-08-30 15:30:19', '2019-05-25 20:34:40', '1', '0');
INSERT INTO `sys_menu` VALUES ('14', '11', '慢查询', '#!sql', 'system/sql.html', 'layui-icon-util', '11', '2017-11-17 16:56:59', '2019-05-25 20:35:20', '1', '0');
INSERT INTO `sys_menu` VALUES ('15', '11', '文件中心', '#!files', 'files/files.html', 'layui-icon-file', '10', '2017-11-17 16:56:59', '2018-08-25 10:43:33', '1', '0');
INSERT INTO `sys_menu` VALUES ('16', '11', '文档中心', '#!swagger', 'http://127.0.0.1:9200/swagger-ui.html', 'layui-icon-app', '9', '2017-11-17 16:56:59', '2019-03-26 02:32:46', '1', '0');
INSERT INTO `sys_menu` VALUES ('17', '11', '代码生成器', '#!generator', 'generator/list.html', 'layui-icon-app', '999', '2018-09-05 13:43:06', '2019-05-26 17:01:46', '1', '0');
INSERT INTO `sys_menu` VALUES ('18', '11', '日志中心', '#!log', 'system/log.html', 'layui-icon-engine', '18', '2019-03-11 06:30:01', '2019-05-25 20:35:35', '1', '0');
INSERT INTO `sys_menu` VALUES ('19', '11', 'prometheus监控', '#!prometheus', 'http://127.0.0.1:9090', 'layui-icon-engine', '1111', '2019-03-27 11:23:31', '2019-05-25 21:13:08', '1', '0');
INSERT INTO `sys_menu` VALUES ('20', '-1', '任务中心', 'javascript:;', '', 'layui-icon-set', '4', '2018-08-28 16:59:44', '2018-08-28 17:00:19', '1', '0');
INSERT INTO `sys_menu` VALUES ('21', '20', '任务管理', '#!jobinfo', 'http://127.0.0.1:8888/jobinfo', 'layui-icon-senior', '1', '2018-08-28 17:02:00', '2018-08-28 18:24:23', '1', '0');
INSERT INTO `sys_menu` VALUES ('22', '20', '调度日志', '#!joblog', 'http://127.0.0.1:8888/joblog', 'layui-icon-senior', '2', '2018-08-28 18:20:53', '2018-08-28 18:24:32', '1', '0');
INSERT INTO `sys_menu` VALUES ('23', '20', '执行器管理', '#!jobgroup', 'http://127.0.0.1:8888/jobgroup', 'layui-icon-senior', '3', '2018-08-28 18:22:04', '2018-09-03 08:05:02', '1', '0');
INSERT INTO `sys_menu` VALUES ('24', '-1', '用户地图', '#!map', 'baiduMap/userMap.html', 'layui-icon-engine', '111111', '2019-06-14 21:28:54', '2019-06-14 21:28:54', '1', '0');
INSERT INTO `sys_menu` VALUES ('25', '-1', '路由管理', '#!route', 'route/route.html', 'layui-icon-engine', '111111', '2019-06-14 21:28:54', '2019-06-14 21:28:54', '1', '0');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `permission` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `permission` (`permission`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', 'permission:post/permissions', '保存权限标识', '2018-01-18 17:06:39', '2018-01-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('2', 'permission:put/permissions', '修改权限标识', '2018-01-18 17:06:39', '2018-01-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('3', 'permission:delete/permissions/{id}', '删除权限标识', '2018-01-18 17:06:39', '2018-01-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('4', 'permission:get/permissions', '查询权限标识', '2018-01-18 17:06:39', '2018-01-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('5', '/permissions/{roleId}/permissions', '查看角色权限', '2018-01-18 17:06:39', '2018-01-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('6', '/permissions/granted', '角色分配权限', '2018-01-18 17:06:39', '2018-01-18 17:06:39');
INSERT INTO `sys_permission` VALUES ('7', 'role:post/roles', '添加角色', '2018-01-18 17:06:39', '2018-01-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('8', 'role:put/roles', '修改角色', '2018-01-18 17:06:39', '2018-01-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('9', 'role:delete/roles/{id}', '删除角色', '2018-01-18 17:06:39', '2018-01-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('10', 'role:post/roles/{id}/permissions', '给角色分配权限', '2018-01-18 17:06:39', '2018-01-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('11', 'role:get/roles', '查询角色', '2018-01-18 17:06:39', '2018-01-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('12', 'role:get/roles/{id}/permissions', '获取角色的权限', '2018-01-18 17:06:39', '2018-01-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('13', 'user:post/users/{id}/roles', '给用户分配角色', '2018-01-18 17:06:39', '2018-01-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('14', 'user:post/users/{id}/resetPassword', '用户重置密码', '2018-01-18 17:06:39', '2018-01-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('15', 'user:get/users', '用户查询', '2018-01-18 17:12:00', '2018-01-18 17:12:05');
INSERT INTO `sys_permission` VALUES ('16', 'user:put/users/me', '修改用户', '2018-01-18 17:06:39', '2018-01-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('17', 'user:get/users/{id}/roles', '获取用户的角色', '2018-01-18 17:06:39', '2018-01-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('18', 'user:post/users/saveOrUpdate', '新增用户', '2018-01-18 17:06:39', '2018-01-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('19', 'user:post/users/exportUser', '导出用户', '2018-01-18 17:06:39', '2018-01-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('20', 'user:get/users/updateEnabled', '用户状态修改', '2018-01-18 17:06:39', '2018-01-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('21', 'user:put/users/password', '修改密码', '2018-01-18 17:06:39', '2018-01-18 17:06:39');
INSERT INTO `sys_permission` VALUES ('22', 'menu:get/menus/all', '查询菜单', '2018-01-18 17:06:39', '2018-01-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('23', 'menu:post/menus/granted', '给角色分配菜单', '2018-01-18 17:06:39', '2018-01-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('24', 'menu:get/menus/tree', '树形显示', '2018-01-18 17:06:39', '2018-01-18 17:06:39');
INSERT INTO `sys_permission` VALUES ('25', 'menu:get/menus/{roleId}/menus', '获取角色的菜单', '2018-01-18 17:06:39', '2018-01-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('26', 'menu:post/menus', '添加菜单', '2018-01-18 17:06:39', '2018-09-04 07:35:29');
INSERT INTO `sys_permission` VALUES ('27', 'menu:put/menus', '修改菜单', '2018-01-18 17:06:39', '2018-01-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('28', 'menu:delete/menus/{id}', '删除菜单', '2018-01-18 17:06:39', '2018-01-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('29', 'menu:get/menus/current', '当前用户菜单', '2018-01-18 17:06:39', '2018-01-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('30', 'menu:get/menus/findAlls', '所有菜单', '2018-01-18 17:06:39', '2018-01-18 17:06:39');
INSERT INTO `sys_permission` VALUES ('31', 'client:post/clients', '保存应用', '2018-01-18 17:06:39', '2018-01-18 17:06:39');
INSERT INTO `sys_permission` VALUES ('32', 'client:get/clients', '应用列表', '2018-01-18 17:06:39', '2018-01-18 17:06:39');
INSERT INTO `sys_permission` VALUES ('33', 'client:get/clients/{id}', '根据id获取应用', '2018-01-18 17:06:39', '2018-01-18 17:06:39');
INSERT INTO `sys_permission` VALUES ('34', 'client:delete/clients', '删除应用', '2018-01-18 17:06:39', '2018-01-18 17:06:42');
INSERT INTO `sys_permission` VALUES ('35', 'p1', '查询所有服务', '2018-01-18 17:06:39', '2018-09-03 08:05:09');
INSERT INTO `sys_permission` VALUES ('36', 'service:get/service/findOnes', '服务树', '2018-01-18 17:06:39', '2018-09-08 03:23:28');
INSERT INTO `sys_permission` VALUES ('37', 'menu:get/menus/findOnes', '获取菜单以及顶层菜单', '2019-05-09 23:48:13', '2019-05-09 23:48:13');
INSERT INTO `sys_permission` VALUES ('38', 'permission:get/permissions/{roleId}/permissions', '根据roleId获取权限', '2019-05-10 00:02:23', '2019-05-10 00:02:23');
INSERT INTO `sys_permission` VALUES ('39', 'file:query', '获取文件列表', '2019-05-17 21:34:05', '2019-05-17 21:34:08');
INSERT INTO `sys_permission` VALUES ('40', 'file:del', '删除文件', '2019-05-17 21:36:46', '2019-05-17 21:36:48');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(32) NOT NULL COMMENT '角色code',
  `name` varchar(50) NOT NULL COMMENT '角色名',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'ADMIN', '管理员', '2017-11-17 16:56:59', '2017-11-17 16:56:59');
INSERT INTO `sys_role` VALUES ('3', '002', '普通用户', '2019-03-27 02:52:00', '2019-03-27 02:52:00');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint NOT NULL,
  `menu_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_id` (`role_id`,`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('2', '1', '2');
INSERT INTO `sys_role_menu` VALUES ('3', '1', '3');
INSERT INTO `sys_role_menu` VALUES ('4', '1', '4');
INSERT INTO `sys_role_menu` VALUES ('5', '1', '5');
INSERT INTO `sys_role_menu` VALUES ('6', '1', '6');
INSERT INTO `sys_role_menu` VALUES ('7', '1', '7');
INSERT INTO `sys_role_menu` VALUES ('8', '1', '8');
INSERT INTO `sys_role_menu` VALUES ('9', '1', '9');
INSERT INTO `sys_role_menu` VALUES ('10', '1', '10');
INSERT INTO `sys_role_menu` VALUES ('11', '1', '11');
INSERT INTO `sys_role_menu` VALUES ('12', '1', '12');
INSERT INTO `sys_role_menu` VALUES ('13', '1', '13');
INSERT INTO `sys_role_menu` VALUES ('14', '1', '14');
INSERT INTO `sys_role_menu` VALUES ('15', '1', '15');
INSERT INTO `sys_role_menu` VALUES ('16', '1', '16');
INSERT INTO `sys_role_menu` VALUES ('17', '1', '17');
INSERT INTO `sys_role_menu` VALUES ('18', '1', '18');
INSERT INTO `sys_role_menu` VALUES ('19', '1', '19');
INSERT INTO `sys_role_menu` VALUES ('20', '1', '20');
INSERT INTO `sys_role_menu` VALUES ('21', '1', '21');
INSERT INTO `sys_role_menu` VALUES ('22', '1', '22');
INSERT INTO `sys_role_menu` VALUES ('23', '1', '23');
INSERT INTO `sys_role_menu` VALUES ('24', '1', '24');
INSERT INTO `sys_role_menu` VALUES ('25', '1', '25');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` int NOT NULL,
  `permission_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_id` (`role_id`,`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1', '1', '1');
INSERT INTO `sys_role_permission` VALUES ('2', '1', '2');
INSERT INTO `sys_role_permission` VALUES ('3', '1', '3');
INSERT INTO `sys_role_permission` VALUES ('4', '1', '4');
INSERT INTO `sys_role_permission` VALUES ('5', '1', '5');
INSERT INTO `sys_role_permission` VALUES ('6', '1', '6');
INSERT INTO `sys_role_permission` VALUES ('7', '1', '7');
INSERT INTO `sys_role_permission` VALUES ('8', '1', '8');
INSERT INTO `sys_role_permission` VALUES ('9', '1', '9');
INSERT INTO `sys_role_permission` VALUES ('10', '1', '10');
INSERT INTO `sys_role_permission` VALUES ('11', '1', '11');
INSERT INTO `sys_role_permission` VALUES ('12', '1', '12');
INSERT INTO `sys_role_permission` VALUES ('13', '1', '13');
INSERT INTO `sys_role_permission` VALUES ('14', '1', '14');
INSERT INTO `sys_role_permission` VALUES ('15', '1', '15');
INSERT INTO `sys_role_permission` VALUES ('16', '1', '16');
INSERT INTO `sys_role_permission` VALUES ('17', '1', '17');
INSERT INTO `sys_role_permission` VALUES ('18', '1', '18');
INSERT INTO `sys_role_permission` VALUES ('19', '1', '19');
INSERT INTO `sys_role_permission` VALUES ('20', '1', '20');
INSERT INTO `sys_role_permission` VALUES ('21', '1', '21');
INSERT INTO `sys_role_permission` VALUES ('22', '1', '22');
INSERT INTO `sys_role_permission` VALUES ('23', '1', '23');
INSERT INTO `sys_role_permission` VALUES ('24', '1', '24');
INSERT INTO `sys_role_permission` VALUES ('25', '1', '25');
INSERT INTO `sys_role_permission` VALUES ('26', '1', '26');
INSERT INTO `sys_role_permission` VALUES ('27', '1', '27');
INSERT INTO `sys_role_permission` VALUES ('28', '1', '29');
INSERT INTO `sys_role_permission` VALUES ('29', '1', '30');
INSERT INTO `sys_role_permission` VALUES ('30', '1', '31');
INSERT INTO `sys_role_permission` VALUES ('31', '1', '32');
INSERT INTO `sys_role_permission` VALUES ('32', '1', '33');
INSERT INTO `sys_role_permission` VALUES ('33', '1', '34');
INSERT INTO `sys_role_permission` VALUES ('34', '1', '35');
INSERT INTO `sys_role_permission` VALUES ('35', '1', '36');
INSERT INTO `sys_role_permission` VALUES ('36', '1', '37');
INSERT INTO `sys_role_permission` VALUES ('37', '1', '38');
INSERT INTO `sys_role_permission` VALUES ('38', '1', '39');

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`,`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES ('4', '1275397643669949952', '1');
INSERT INTO `sys_role_user` VALUES ('3', '1277056689447719936', '1');
INSERT INTO `sys_role_user` VALUES ('2', '1277120261867529984', '1');
INSERT INTO `sys_role_user` VALUES ('1', '1277137734524300032', '1');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(60) NOT NULL,
  `nick_name` varchar(255) DEFAULT NULL,
  `fullname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `head_img_url` varchar(1024) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `sex` tinyint(1) DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  `type` varchar(16) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1277137734524300034 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1275397643669949952', 'test', '$2a$10$Ma6HQIVZMaX.ygG3ZwG1vOr5NbjvulphmZaERI9ZsZX5tg4x6sHTi', '测试账户', '测试账户', 'http://payo7kq4i.bkt.clouddn.com/QQ%E5%9B%BE%E7%89%8720180819191900.jpg', '13851539156', '0', '0', 'APP', '2017-11-17 16:56:59', '2018-09-07 03:27:40');
INSERT INTO `sys_user` VALUES ('1277056689447719936', 'user', '$2a$10$Ma6HQIVZMaX.ygG3ZwG1vOr5NbjvulphmZaERI9ZsZX5tg4x6sHTi', '体验用户', '体验用户', 'http://payo7kq4i.bkt.clouddn.com/QQ%E5%9B%BE%E7%89%8720180819191900.jpg', null, '1', '0', 'APP', '2017-11-17 16:56:59', '2018-09-07 13:38:34');
INSERT INTO `sys_user` VALUES ('1277120261867529984', 'owen', '$2a$10$Ma6HQIVZMaX.ygG3ZwG1vOr5NbjvulphmZaERI9ZsZX5tg4x6sHTi', '欧文', '欧文', 'http://payo7kq4i.bkt.clouddn.com/QQ%E5%9B%BE%E7%89%8720180819191900.jpg', '18579068166', '1', '0', 'APP', '2017-11-17 16:56:59', '2018-09-12 06:00:31');
INSERT INTO `sys_user` VALUES ('1277137734524300032', 'admin', '$2a$10$Ma6HQIVZMaX.ygG3ZwG1vOr5NbjvulphmZaERI9ZsZX5tg4x6sHTi', '管理员', '管理员', 'http://payo7kq4i.bkt.clouddn.com/耳机.jpg', '13106975707', '1', '1', 'BACKEND', '2017-11-17 16:56:59', '2018-09-15 03:12:44');
INSERT INTO `sys_user` VALUES ('1277137734524300033', 'test2', '$2a$10$Ma6HQIVZMaX.ygG3ZwG1vOr5NbjvulphmZaERI9ZsZX5tg4x6sHTi', '体验用户2', '体验用户2', null, null, null, '1', 'APP', '2020-09-28 10:56:35', '2020-09-28 10:56:39');
