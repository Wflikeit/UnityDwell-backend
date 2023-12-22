-- Table Pracownicy

CREATE TABLE C##MACIEK.Pracownicy
(
    Id_pracownika     CHAR(36)      NOT NULL,
    Imie              Varchar2(20)  NOT NULL,
    Nazwisko          Varchar2(30)  NOT NULL,
    Nr_telefonu       Varchar2(12)  NOT NULL,
    Data_zatrudnienia Date          NOT NULL,
    Data_konca_umowy  Date          NOT NULL,
    Plec              Char(1)       NOT NULL
        CHECK (Plec In ('M', 'K')),
    Placa             Number(10, 2) NOT NULL,
    Email             Varchar2(30)  NOT NULL,
    Id_spoldzielni    CHAR(36)      NOT NULL,
    Id_adresu         CHAR(36)      NOT NULL
)
/

-- Create indexes for table Pracownicy

CREATE INDEX IX_Pracuje_w ON C##MACIEK.Pracownicy (Id_spoldzielni)
/

CREATE INDEX IX_Relationship5 ON C##MACIEK.Pracownicy (Id_adresu)
/

-- Add keys for table Pracownicy

ALTER TABLE C##MACIEK.Pracownicy
    ADD CONSTRAINT PracownikPK PRIMARY KEY (Id_pracownika)
/

ALTER TABLE C##MACIEK.Pracownicy
    ADD CONSTRAINT Email UNIQUE (Email)
/

ALTER TABLE C##MACIEK.Pracownicy
    ADD CONSTRAINT Nr_telefonu UNIQUE (Nr_telefonu)
/
