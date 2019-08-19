insert into soorten(naam, landid)
values ('test', (select id from landen where naam = 'test'));
insert into soorten(naam, landid)
values ('test2', (select id from landen where naam = 'test'));