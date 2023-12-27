-- Table Rachunki

CREATE TABLE C##MACIEK.Rachunki
(
    Id_rachunku        CHAR(36)      NOT NULL,
    Data_wystawienia   Date          NOT NULL,
    Kwota              Number(10, 2) NOT NULL,
    Id_tytulu_rachunku CHAR(36)      NOT NULL,
    Id_spoldzielni     CHAR(36)      NOT NULL,
    Nr_mieszkanca      CHAR(36)      NOT NULL
);
/

-- Create indexes for table Rachunki

CREATE INDEX IX_Otrzymuje_wlasciciel ON C##MACIEK.Rachunki (Nr_mieszkanca);
/

CREATE INDEX IX_Wystawia_rachunek ON C##MACIEK.Rachunki (Id_spoldzielni);
/

CREATE INDEX posiada_tytul ON C##MACIEK.Rachunki (Id_tytulu_rachunku);
/

-- Add keys for table Rachunki

ALTER TABLE C##MACIEK.Rachunki
    ADD CONSTRAINT RachunekPK PRIMARY KEY (Id_rachunku);
/

-- Table Tytuly_rachunku

CREATE TABLE C##MACIEK.Tytuly_rachunku
(
    Id_tytulu_rachunku CHAR(36)     NOT NULL,
    Tytul              Varchar2(30) NOT NULL
);
/

-- Add keys for table Tytuly_rachunku

ALTER TABLE C##MACIEK.Tytuly_rachunku
    ADD CONSTRAINT PK_Tytuly_rachunku PRIMARY KEY (Id_tytulu_rachunku);
/
