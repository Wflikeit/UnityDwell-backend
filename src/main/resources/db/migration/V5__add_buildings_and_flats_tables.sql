-- Table Budynki

CREATE TABLE Budynki
(
    Id_budynku                   CHAR(36) NOT NULL,
    Data_modernizacji_termicznej Date     NOT NULL,
    Data_oddania_do_uzytku       Date     NOT NULL,
    Liczba_pieter                Integer  NOT NULL,
    Data_remontu_ogolnego        Date     NOT NULL,
    Czy_zdatny_do_mieszkania     Char(1)  NOT NULL,
    Id_spoldzielni               CHAR(36) NOT NULL,
    Id_adresu                    CHAR(36) NOT NULL
)
/

-- Create indexes for table Budynki

CREATE INDEX IX_posiadane_przez_spoldzielnie ON Budynki (Id_spoldzielni)
/

CREATE INDEX IX_Relationship1 ON Budynki (Id_adresu)
/

-- Add keys for table Budynki

ALTER TABLE Budynki
    ADD CONSTRAINT BudynekPK PRIMARY KEY (Id_budynku)
/

-- Table Mieszkania

CREATE TABLE Mieszkania
(
    Id_mieszkania         CHAR(36)    NOT NULL,
    Nr_mieszkania         Varchar2(5) NOT NULL,
    Powierzchnia          Integer     NOT NULL,
    Liczba_pokoi          Integer     NOT NULL,
    Data_kontroli_gazowej Date        NOT NULL,
    Id_budynku            CHAR(36)    NOT NULL
)
/

-- Create indexes for table Mieszkania

CREATE INDEX IX_ma ON Mieszkania (Id_budynku)
/

-- Add keys for table Mieszkania

ALTER TABLE Mieszkania
    ADD CONSTRAINT MieszkaniePK PRIMARY KEY (Id_mieszkania)
/
