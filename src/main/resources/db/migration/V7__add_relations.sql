-- Create foreign keys (relationships) section -------------------------------------------------
ALTER TABLE C##MACIEK.Mieszkania
    ADD CONSTRAINT ma FOREIGN KEY (Id_budynku) REFERENCES Budynki (Id_budynku)
/



ALTER TABLE C##MACIEK.Budynki
    ADD CONSTRAINT posiadane_przez_spoldzielnie FOREIGN KEY (Id_spoldzielni) REFERENCES Spoldzielnie (Id_spoldzielni)
/



ALTER TABLE C##MACIEK.Rachunki
    ADD CONSTRAINT Otrzymuje_wlasciciel FOREIGN KEY (Nr_mieszkanca) REFERENCES Wlasciciele_mieszkan (Nr_mieszkanca)
/



ALTER TABLE C##MACIEK.Pracownicy
    ADD CONSTRAINT Pracuje_w_spoldzielni FOREIGN KEY (Id_spoldzielni) REFERENCES Spoldzielnie (Id_spoldzielni)
/



ALTER TABLE C##MACIEK.Mieszkancy
    ADD CONSTRAINT Mieszka FOREIGN KEY (Id_mieszkania) REFERENCES Mieszkania (Id_mieszkania)
/



ALTER TABLE C##MACIEK.Rachunki
    ADD CONSTRAINT Wystawia_rachunek FOREIGN KEY (Id_spoldzielni) REFERENCES Spoldzielnie (Id_spoldzielni)
/



ALTER TABLE C##MACIEK.Ogloszenia
    ADD CONSTRAINT Wystawia_ogloszenie FOREIGN KEY (Id_spoldzielni) REFERENCES Spoldzielnie (Id_spoldzielni)
/



ALTER TABLE C##MACIEK.Budynki
    ADD CONSTRAINT budynek_ma_adres FOREIGN KEY (Id_adresu) REFERENCES Adresy (Id_adresu)
/



ALTER TABLE C##MACIEK.Spoldzielnie
    ADD CONSTRAINT spoldzielnia_ma_adres FOREIGN KEY (Id_adresu) REFERENCES Adresy (Id_adresu)
/



ALTER TABLE C##MACIEK.Pracownicy
    ADD CONSTRAINT pracownik_ma_adres FOREIGN KEY (Id_adresu) REFERENCES Adresy (Id_adresu)
/



ALTER TABLE C##MACIEK.Rachunki
    ADD CONSTRAINT posiada_tytul FOREIGN KEY (Id_tytulu_rachunku) REFERENCES Tytuly_rachunku (Id_tytulu_rachunku)
/
