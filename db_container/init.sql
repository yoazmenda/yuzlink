--  Grant privileges
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO klix_admin;
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public to klix_admin;

-- insert roles
INSERT INTO ROLES (id, role_name) values (1, 'ADMIN');
INSERT INTO ROLES (id, role_name) values (2, 'GUEST');
INSERT INTO ROLES (id, role_name) values (3, 'USER');

-- insert new users:

-- ADMIN
INSERT INTO
user_metadata (first_name, last_name, email, created, username, password_hash)
       VALUES ('Yoaz', 'Menda', 'yoazmenda@gmail.com', CURRENT_TIMESTAMP, 'admin',  '$2a$06$bfiKHZ/prW3zf/3QE3XuiOb8Lo6rHYwLXw6a29IUrl1.lf7Am8R6a');

insert into users (user_metadata_id) values ((select username from user_metadata where username = 'admin'));

insert into user_roles (role_id, user_id) values (
(select id from roles where role_name = 'ADMIN'),
(select id from users where user_metadata_id = 'admin')
);
insert into user_roles (role_id, user_id) values (
(select id from roles where role_name = 'GUEST'),
(select id from users where user_metadata_id = 'admin')
);
insert into user_roles (role_id, user_id) values (
(select id from roles where role_name = 'USER'),
(select id from users where user_metadata_id = 'admin')
);

-- GUEST
INSERT INTO
user_metadata (first_name, last_name, email, created, username, password_hash)
      VALUES ('guest', 'guest', 'guest@gmail.com', CURRENT_TIMESTAMP, 'guest',  'NONE');

insert into users (user_metadata_id) values ((select username from user_metadata where username = 'guest'));

insert into user_roles (role_id, user_id) values (
(select id from roles where role_name = 'GUEST'),
(select id from users where user_metadata_id = 'guest')
);




