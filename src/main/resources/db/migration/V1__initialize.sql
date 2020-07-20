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