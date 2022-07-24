insert into product (title, description, miniature, category, price, type, author) values ('Monstera deliciosa', 'Monstera deliciosa to okazałe, modne, silnie płożące się pnącze o bardzo okazałych, spektakularnych liściach.', 'https://i.ibb.co/4P2TYGc/monstera-deliciosa.jpg', 'Plant Monsteria', 2000, 'PLANTS', 'Admin');

insert into role (id, name) values(1, 'ADMIN');
insert into role (id, name) values(2, 'USER');

insert into user_table (id, username, password) values(1, 'admin', '$2a$12$c3ZaizMLwEc40BS/uqEYjuCBKXIrCrzDxW3i6Js8PGgTrZjH9RAg2');
insert into user_table (id, username, password) values(2, 'user', '$2a$12$96BUERE6i.WgeotUYMEWBOnrgptdGmV1HahwOFWqrJKjEOzEP5xBG');

insert into user_role (user_id, role_id) values(1,1);
insert into user_role (user_id, role_id) values(1,2);
insert into user_role (user_id, role_id) values(2,2);