
insert into Role (nome) values ('usuario');

insert into Permission (nome) values ('cadastra_Usuario');
insert into Permission (nome) values ('altera_Usuario');
insert into Permission (nome) values ('remove_Usuario');
insert into Permission (nome) values ('listagem_Usuario');

insert into role_permissions values (1, 1);
insert into role_permissions values (1, 2);
insert into role_permissions values (1, 3);
insert into role_permissions values (1, 4);

insert into Role (nome) values ('role');

insert into Permission (nome) values ('cadastra_Role');
insert into Permission (nome) values ('altera_Role');
insert into Permission (nome) values ('remove_Role');
insert into Permission (nome) values ('listagem_Role');

insert into role_permissions values (2, 5);
insert into role_permissions values (2, 6);
insert into role_permissions values (2, 7);
insert into role_permissions values (2, 8);

insert into Role (nome) values ('permission');

insert into Permission (nome) values ('cadastra_Permission');
insert into Permission (nome) values ('altera_Permission');
insert into Permission (nome) values ('remove_Permission');
insert into Permission (nome) values ('listagem_Permission');

insert into role_permissions values (3, 9);
insert into role_permissions values (3, 10);
insert into role_permissions values (3, 11);
insert into role_permissions values (3, 12);

insert into Role (nome) values ('admin');

insert into Usuario (id, login, senha, nome, sobrenome, email) values ('1', 'kleber', '202cb962ac59075b964b07152d234b70', 'Kleber', 'Mota', 'kleber@mail.com');

insert into role_members values (1, 1);
insert into role_members values (1, 2);
insert into role_members values (1, 3);
insert into role_members values (1, 4);
