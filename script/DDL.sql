CREATE TABLE `tbl_category`
(
    `category_id` varchar(8)  NOT NULL,
    `type`        varchar(45) NOT NULL,
    `name`        varchar(45) NOT NULL,
    PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3

CREATE TABLE `tbl_member`
(
    `id`       int         NOT NULL AUTO_INCREMENT,
    `name`     varchar(45) NOT NULL,
    `email`    varchar(45) NOT NULL,
    `category` varchar(45) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3