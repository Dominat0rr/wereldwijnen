insert into soorten(naam, landid)
values ('test', (select id from landen where naam = 'test'));