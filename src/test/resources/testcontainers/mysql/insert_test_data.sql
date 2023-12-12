use testdbdb;

INSERT INTO `tbl_member` (id, name, email, category) VALUES (1, 'kang', 'kang@gmail.com', 'M001');
INSERT INTO `tbl_member` (id, name, email, category) VALUES (2, 'kim', 'kim@gmail.com', 'M002');
INSERT INTO `tbl_member` (id, name, email, category) VALUES (3, '한글테스트', 'gg@gmail.com', 'M002');

INSERT INTO `tbl_member_detail` (id, member_id, version, detail_info, created_time, created_id) VALUES (null, 1, '1.0', '{"p": "1234", "d": "1234", "c": 1}', '2022-10-10 10:10:10', 1);
INSERT INTO `tbl_member_detail` (id, member_id, version, detail_info, created_time, created_id) VALUES (null, 1, '1.1', '{"p": "56", "d": "56", "c": 1}', '2022-10-10 10:10:10', 1);
INSERT INTO `tbl_member_detail` (id, member_id, version, detail_info, created_time, created_id) VALUES (null, 2, '1.0', '{"p": "789", "d": "789", "c": 2}', '2022-10-10 10:10:10', 1);
INSERT INTO `tbl_member_detail` (id, member_id, version, detail_info, created_time, created_id) VALUES (null, 3, '1.0', '{"p": "한글테스트", "d": "한글테스트", "c": "한글테스트"}', '2022-10-10 10:10:10', 1);