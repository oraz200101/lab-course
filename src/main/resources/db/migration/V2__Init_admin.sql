INSERT INTO lab_course.users (full_name, date_of_birth, email, balance, password, enabled)
VALUES ('admin', '11-12-2001', 'admin@mail.ru', 0, '$2a$10$rbMkdD8FOprunahG3S2n1.DZrt0SS/T.hQy6MbYxcVetyS8DWj.yW',
        true);
INSERT INTO lab_course.user_role (user_id, roles) values (1,'ADMIN')
