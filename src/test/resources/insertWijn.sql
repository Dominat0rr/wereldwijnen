insert into wijnen(soortid, jaar, beoordeling, prijs, inBestelling)
values((select id from soorten where naam = 'test'), 1985, 5, 5.57, 5);
insert into wijnen(soortid, jaar, beoordeling, prijs, inBestelling)
values((select id from soorten where naam = 'test'), 1985, 3, 3.57, 10);