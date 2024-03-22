--liquibase formatted sql

--changeset misba7:initialization
insert into roles (name) values ('ADMIN'),('EMPLOYEE'),('PATIENT');
insert into users (firstname, lastname, email, password) values ('Abdeddaim', 'Mansar', 'a.mansar@nuitee.ma', '$2a$12$ZHI55/uNtjUxygh/8WGTPuDaFXZfdxrRKpdhvoV1aD4DZFxG9PYQi');
insert into users_roles(users_id, roles_id) values (1,1);
