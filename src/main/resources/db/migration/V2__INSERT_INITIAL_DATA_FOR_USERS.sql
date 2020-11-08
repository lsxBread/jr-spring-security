insert into users (username, encoded_password) values
('student','$2a$10$BRMZmPOOaLp5ksyMZMY8rOCphXq8xZtgcsi8svVIeSQnEVMp4LY0a'),
('admin', '$2a$10$BRMZmPOOaLp5ksyMZMY8rOCphXq8xZtgcsi8svVIeSQnEVMp4LY0a'),
('admin-trainee','$2a$10$BRMZmPOOaLp5ksyMZMY8rOCphXq8xZtgcsi8svVIeSQnEVMp4LY0a');

insert into authorities (permission) values
('student:read'),
('student:write'),
('ROLE_ADMIN'),
('ROLE_ADMINTRAINEE'),
('ROLE_STUDENT');

insert into users_authorities(user_id, authority_id)
select users.id, authorities.id from users, authorities
where users.username='student' and authorities.permission='ROLE_STUDENT';

insert into users_authorities(user_id, authority_id)
select users.id, authorities.id from users, authorities
where users.username='admin' and authorities.permission='ROLE_ADMIN';

insert into users_authorities(user_id, authority_id)
select users.id, authorities.id from users, authorities
where users.username='admin' and authorities.permission='student:read';

insert into users_authorities(user_id, authority_id)
select users.id, authorities.id from users, authorities
where users.username='admin' and authorities.permission='student:write';

insert into users_authorities(user_id, authority_id)
select users.id, authorities.id from users, authorities
where users.username='admin-trainee' and authorities.permission='ROLE_ADMINTRAINEE';

insert into users_authorities(user_id, authority_id)
select users.id, authorities.id from users, authorities
where users.username='admin-trainee' and authorities.permission='student:read';