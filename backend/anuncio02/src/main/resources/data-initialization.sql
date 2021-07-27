INSERT INTO tb_user (active, date_register, date_update, email, "name", "password")
VALUES
(false, '2021-07-18 18:17:06', '2021-07-20 00:00:00', 'vendedor@gmail.com', 'vendedor', '$2a$10$viGn0N4xoVKfKGGRS1ov7eWVszZdvtI6zVqRqkTQhtaCO3y6jAOBy'),
(false, '2021-07-18 18:17:10', '2021-07-20 00:00:05', 'user@gmail.com', 'user', '$2a$10$viGn0N4xoVKfKGGRS1ov7eWVszZdvtI6zVqRqkTQhtaCO3y6jAOBy'),
(false, '2021-07-18 18:17:15', '2021-07-20 00:00:10', 'admin@gmail.com', 'admin', '$2a$10$viGn0N4xoVKfKGGRS1ov7eWVszZdvtI6zVqRqkTQhtaCO3y6jAOBy');

INSERT INTO tb_profile (name_profile)
values
('ADMIN'),
('USER');

INSERT INTO tb_users_profiles (user_id, profile_id)
values
(1, 2),
(2, 1),
(3, 1),
(3, 2);