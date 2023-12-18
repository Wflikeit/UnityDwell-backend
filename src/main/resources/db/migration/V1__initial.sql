-- Table Spoldzielnie
CREATE TABLE Spoldzielnie
(
    Id_spoldzielni CHAR(36)     NOT NULL,
    Nazwa          Varchar2(30) NOT NULL,
    Data_zalozenia Date         NOT NULL,
    NIP            Char(10)     NOT NULL,
    Id_adresu      CHAR(36)     NOT NULL
)
-- Create indexes for table Spoldzielnie

CREATE INDEX IX_Relationship4 ON Spoldzielnie (Id_adresu)
/

-- Add keys for table Spoldzielnie

ALTER TABLE Spoldzielnie
    ADD CONSTRAINT SpoldzielniaPK PRIMARY KEY (Id_spoldzielni)
/

ALTER TABLE Spoldzielnie
    ADD CONSTRAINT NIP UNIQUE (NIP)
/
-- Table Adresy

CREATE TABLE Adresy
(
    Id_adresu    CHAR(36)     NOT NULL,
    Miasto       Varchar2(20) NOT NULL,
    Ulica        Varchar2(50) NOT NULL,
    Nr_budynku   Varchar2(4)  NOT NULL,
    Kod_pocztowy Varchar2(6)  NOT NULL
)
/

-- Add keys for table Adresy

ALTER TABLE Adresy
    ADD CONSTRAINT PK_Adresy PRIMARY KEY (Id_adresu)
/