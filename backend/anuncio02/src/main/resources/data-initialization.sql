INSERT INTO tb_user (active, date_register, date_update, email, "name", "password")
VALUES
(false, '2021-07-18 18:17:06', '2021-07-18 18:17:10', 'vendedor@gmail.com', 'vendedor', '$2a$10$Iu7G/WWT7t7rMLPKs2AOzOwvTvDC34RuFd5Z1B7Vgg4iTytvD1EaK'),
(false, '2021-07-18 18:17:10', '2021-07-18 18:17:15', 'user@gmail.com', 'user', '$2a$10$Iu7G/WWT7t7rMLPKs2AOzOwvTvDC34RuFd5Z1B7Vgg4iTytvD1EaK'),
(false, '2021-07-18 18:17:15', '2021-07-18 18:17:25', 'admin@gmail.com', 'admin', '$2a$10$Iu7G/WWT7t7rMLPKs2AOzOwvTvDC34RuFd5Z1B7Vgg4iTytvD1EaK');

INSERT INTO tb_role (name_role)
values
('ADMIN'),
('USER');

INSERT INTO tb_users_roles (user_id, role_id)
values
(1, 2),
(2, 1),
(3, 1),
(3, 2);