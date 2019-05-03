INSERT INTO adres (id,miasto,ulica,nr_domu,nr_mieszkania) VALUES
    (1,'Rzeszow','Hetmanska',11,21),
    (2,'Warszawa','Wiejska',3,1);

INSERT INTO autor (id,imie,nazwisko) VALUES
    (1,'Dmitrij','Gluchowski'),
    (2,'Oliver','Bowden');

INSERT INTO gatunek (id,nazwa,opis) VALUES
    (1,'Fantasy','Przykladowy opis'),
    (2,'Sci-Fi','Inny opis');

INSERT INTO klient (id,imie,nazwisko) VALUES
    (1,'Przemyslaw','Palinski'),
    (2,'Adam','Nowak');

INSERT INTO ksiazka(id,tytul,id_gatunek) VALUES
    (1,'Metro 2033',2),
    (2,'Assassins Creed Renesans',1);

INSERT INTO zamowienia (id, status, id_klient) VALUES
    (1,'W trakcie realizacji',1),
    (2,'Zrealizowano',2);

INSERT INTO klient_adres (id_adres, id_klient) VALUES
    (1,1),
    (2,2);

INSERT INTO ksiazka_autor (id_ksiazka, id_autor) VALUES
    (1,1),
    (2,2);

INSERT INTO zamowienia_ksiazka (id_zamowienia, id_ksiazka) VALUES
    (1,1),
    (1,2),
    (2,1);