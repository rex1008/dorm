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

CREATE TABLE `clazz` (
                         `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                         `clazz_no` varchar(50) NOT NULL COMMENT '班级编号',
                         `clazz_name` varchar(50) NOT NULL COMMENT '班级名称',
                         `dept_id` bigint(20) NOT NULL COMMENT '系别id',
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `uk_clazz_no` (`clazz_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='班级';

CREATE TABLE `student` (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                           `student_no` varchar(50) NOT NULL COMMENT '学号',
                           `student_name` varchar(50) NOT NULL COMMENT '姓名',
                           `sex` char(1) NOT NULL COMMENT 'M:男 F:女',
                           `photo_path` varchar(300) DEFAULT NULL COMMENT '照片存放路径',
                           `clazz_id` bigint(20) NOT NULL COMMENT '班级id',
                           `dorm_id` bigint(20) DEFAULT NULL COMMENT '宿舍id',
                           PRIMARY KEY (`id`),
                           UNIQUE KEY `uk_student_no` (`student_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='学生表';


CREATE TABLE `dorm_building` (
                                 `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                 `dorm_building_no` varchar(50) NOT NULL COMMENT '宿舍楼编号',
                                 `dorm_building_name` varchar(50) NOT NULL COMMENT '宿舍楼名称',
                                 `type` char(1) NOT NULL COMMENT 'M:男生宿舍 F:女生宿舍',
                                 `complete_time` datetime NOT NULL COMMENT '落成时间',
                                 PRIMARY KEY (`id`),
                                 UNIQUE KEY `uk_dorm_building_no` (`dorm_building_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='宿舍楼';

CREATE TABLE `dorm` (
                        `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                        `dorm_no` varchar(50) NOT NULL COMMENT '宿舍编号',
                        `capacity` smallint(6) NOT NULL COMMENT '容量',
                        `dorm_building_id` bigint(20) NOT NULL COMMENT '宿舍楼id',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='宿舍';