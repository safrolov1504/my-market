drop table if exists products cascade;
create table products (id bigserial, title varchar(255), description varchar(5000), price int, categories_id bigint, primary key(id));
insert into products
(title, description, price, categories_id) values
('Cheese', 'Fresh cheese', 320, 1),
('Milk', 'Fresh milk', 80, 1),
('Apples', 'Fresh apples', 8, 1),
('Bread', 'Fresh bread', 30, 1),
('Lentil', 'Fresh', 35,1),
('Sweater', '', 200,1),
('Robe', '', 34,2),
('Coat', '', 567, 2),
('Accessories', '', 15,2),
('Microphone', '', 62,3),
('Control', '', 17,3),
('Shorts', '', 823, 2),
('Fuse', '', 9, 3),
('Speaker', '', 99,3),
('Celery', '', 112,1),
('Blouse', '', 142,2),
('Radish', '', 234,1),
('Casette', '', 14,3),
('Television', '', 1500,3),
('CD', '', 55,3),
('Garlic', '', 222,1),
('Chickpea', '', 145,1),
('Bikini', '', 107,2),
('Cap', '', 106,2),
('Sorrel', '', 456,1),
('Panties', '', 2224,2),
('Radio', '', 220,3);

drop table if exists categories cascade;
create table categories (id bigserial, name varchar(255), primary key(id));
insert into categories
(name) values
('product'), ('clothes'), ('device');

drop table if exists users;
create table users (
  id                    bigserial,
  name                 VARCHAR(30) not null UNIQUE,
  password              VARCHAR(80) not null,
  email                 VARCHAR(50) UNIQUE,
  first_name            VARCHAR(50),
  second_name             VARCHAR(50),
  status                VARCHAR(50),
  PRIMARY KEY (id)
);

drop table if exists roles;
create table roles (
  id                    serial,
  name                  VARCHAR(50) not null,
  primary key (id)
);

drop table if exists users_roles;
create table users_roles (
  user_id               INT NOT NULL,
  role_id               INT NOT NULL,
  primary key (user_id, role_id),
  FOREIGN KEY (user_id)
  REFERENCES users (id),
  FOREIGN KEY (role_id)
  REFERENCES roles (id)
);

insert into roles (name)
values
('ROLE_CUSTOMER'), ('ROLE_MANAGER'), ('ROLE_ADMIN');

insert into users (name, password, first_name, second_name, email,status)
values
('111','$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i','admin','admin','admin@gmail.com','true'),
('222','$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i','user1','user1','user1@gmail.com','true'),
('333','$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i','user2','user2','user2@gmail.com','false');

insert into users_roles (user_id, role_id)
values
(1, 1),
(1, 2),
(1, 3),
(2,1),
(3,1);