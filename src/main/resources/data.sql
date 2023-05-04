insert into user_details(id, birth_date, name)
values(1001, current_date(), 'Andrey');

insert into user_details(id, birth_date, name)
values(1002, current_date(), 'Varabey');

insert into user_details(id, birth_date, name)
values(1003, current_date(), 'Sergey');


insert into post(id, description, user_id)
values(20001,'Hello1', 1001);

insert into post(id, description, user_id)
values(20002,'Hello2', 1001);

insert into post(id, description, user_id)
values(20003,'FuckOff1', 1002);

insert into post(id, description, user_id)
values(20004,'FuckOff2', 1002);
