-- Table Spoldzielnie
CREATE TABLE C##MACIEK.Adresy
(
    Id_adresu    CHAR(36)     NOT NULL,
    Miasto       Varchar2(20) NOT NULL,
    Ulica        Varchar2(50) NOT NULL,
    Nr_budynku   Varchar2(4)  NOT NULL,
    Kod_pocztowy Varchar2(6)  NOT NULL
);
/
-- Table Adresy

ALTER TABLE C##MACIEK.Adresy
    ADD CONSTRAINT PK_Adresy PRIMARY KEY (Id_adresu)
/

-- Add keys for table Adresy

CREATE TABLE C##MACIEK.Spoldzielnie
(
    Id_spoldzielni CHAR(36)     NOT NULL,
    Nazwa          Varchar2(30) NOT NULL,
    Data_zalozenia Date         NOT NULL,
    NIP            Char(10)     NOT NULL,
    Id_adresu      CHAR(36)     NOT NULL
);
-- Create indexes for table Spoldzielnie

CREATE INDEX IX_Relationship4 ON C##MACIEK.Spoldzielnie (Id_adresu);
/

-- Add keys for table Spoldzielnie

ALTER TABLE C##MACIEK.Spoldzielnie
    ADD CONSTRAINT SpoldzielniaPK PRIMARY KEY (Id_spoldzielni);
/

ALTER TABLE C##MACIEK.Spoldzielnie
    ADD CONSTRAINT NIP UNIQUE (NIP);
/
