use testdbdb;

CREATE TABLE `tbl_category`
(
    `category_id` varchar(8)  NOT NULL,
    `type`        varchar(45) NOT NULL,
    `name`        varchar(45) NOT NULL,
    PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `tbl_member`
(
    `id`       int         NOT NULL AUTO_INCREMENT,
    `name`     varchar(45) NOT NULL,
    `email`    varchar(45) NOT NULL,
    `category` varchar(45) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

CREATE TABLE `tbl_member_detail`
(
    `id`           INT          NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `member_id`    INT          NOT NULL COMMENT '사용자 ID',
    `version`      VARCHAR(128) NOT NULL COMMENT '버전',
    `detail_info`  TEXT NULL COMMENT '정보',
    `created_time` DATETIME     NOT NULL COMMENT '생성시간',
    `created_id`   INT          NOT NULL COMMENT '생성자',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COMMENT = '멤버 디테일';


INSERT INTO `tbl_category` (`category_id`, `type`, `name`) VALUES ('M001', 'member', '학생');
INSERT INTO `tbl_category` (`category_id`, `type`, `name`) VALUES ('M002', 'member', '교사');
INSERT INTO `tbl_category` (`category_id`, `type`, `name`) VALUES ('M003', 'member', '기간제교사');

INSERT INTO `tbl_member` (id, name, email, category) VALUES (1, 'kang', 'kang@gmail.com', 'M001');
INSERT INTO `tbl_member` (id, name, email, category) VALUES (2, 'kim', 'kim@gmail.com', 'M002');

INSERT INTO `tbl_member_detail` (id, member_id, version, detail_info, created_time, created_id) VALUES (null, 1, '1.0', '{"p": "1234", "d": "1234", "c": 1}', '2022-10-10 10:10:10', 1);
INSERT INTO `tbl_member_detail` (id, member_id, version, detail_info, created_time, created_id) VALUES (null, 1, '1.1', '{"p": "56", "d": "56", "c": 1}', '2022-10-10 10:10:10', 1);
INSERT INTO `tbl_member_detail` (id, member_id, version, detail_info, created_time, created_id) VALUES (null, 2, '1.0', '{"p": "789", "d": "789", "c": 2}', '2022-10-10 10:10:10', 1);