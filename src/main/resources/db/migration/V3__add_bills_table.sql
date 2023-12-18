-- Table Rachunki

CREATE TABLE Rachunki
(
    Id_rachunku        CHAR(36)      NOT NULL
        CONSTRAINT ValidValuesId_rachunku CHECK ((Id_rachunku >= 1)),
    Data_wystawienia   Date          NOT NULL,
    Kwota              Number(10, 2) NOT NULL,
    Id_tytulu_rachunku CHAR(36)      NOT NULL,
    Id_spoldzielni     CHAR(36)      NOT NULL,
    Nr_mieszkanca      CHAR(36)      NOT NULL
)
/

-- Create indexes for table Rachunki

CREATE INDEX IX_Otrzymuje_wlasciciel ON Rachunki (Nr_mieszkanca)
/

CREATE INDEX IX_Wystawia_rachunek ON Rachunki (Id_spoldzielni)
/

CREATE INDEX posiada_tytul ON Rachunki (Id_tytulu_rachunku)
/

-- Add keys for table Rachunki

ALTER TABLE Rachunki
    ADD CONSTRAINT RachunekPK PRIMARY KEY (Id_rachunku)
/

-- Table Tytuly_rachunku

CREATE TABLE Tytuly_rachunku
(
    Id_tytulu_rachunku CHAR(36)     NOT NULL,
    Tytul              Varchar2(30) NOT NULL
)
/

-- Add keys for table Tytuly_rachunku

ALTER TABLE Tytuly_rachunku
    ADD CONSTRAINT PK_Tytuly_rachunku PRIMARY KEY (Id_tytulu_rachunku)
/
