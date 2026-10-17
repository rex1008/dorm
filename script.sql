CREATE TABLE `manager` (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                           `username` varchar(50) NOT NULL COMMENT '账号',
                           `real_name` varchar(50) DEFAULT NULL COMMENT '名称',
                           `pwd` varchar(50) NOT NULL COMMENT '密码',
                           `sex` char(1) NOT NULL COMMENT '性别',
                           `avatar_path` varchar(300) DEFAULT NULL COMMENT '头像文件路径',
                           PRIMARY KEY (`id`),
                           UNIQUE KEY `uk_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='管理员表';


CREATE TABLE `dept` (
                        `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                        `dept_name` varchar(50) NOT NULL COMMENT '系别名称',
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `uk_dept_name` (`dept_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系别';