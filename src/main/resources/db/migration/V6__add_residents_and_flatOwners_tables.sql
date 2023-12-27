-- Table Mieszkania_wlascicieli

CREATE TABLE C##MACIEK.Mieszkania_wlascicieli
(
    Id_mieszkania CHAR(36) NOT NULL,
    Nr_mieszkanca CHAR(36) NOT NULL,
    Data_od       Date     NOT NULL,
    Data_do       Date
);
/
-- Table Wlasciciele_mieszkan

CREATE TABLE C##MACIEK.Wlasciciele_mieszkan
(
    Nr_mieszkanca CHAR(36)     NOT NULL,
    PESEL         Char(11),
    NIP_firmy     Char(10)     NOT NULL,
    Nr_telefonu   Varchar2(12) NOT NULL
);
/

-- Add keys for table Wlasciciele_mieszkan

ALTER TABLE C##MACIEK.Wlasciciele_mieszkan
    ADD CONSTRAINT Nr_mieszkanca PRIMARY KEY (Nr_mieszkanca);
/

ALTER TABLE C##MACIEK.Wlasciciele_mieszkan
    ADD CONSTRAINT NIP_firmy UNIQUE (NIP_firmy);
/

ALTER TABLE C##MACIEK.Wlasciciele_mieszkan
    ADD CONSTRAINT Nr_telefonu_1 UNIQUE (Nr_telefonu);
/

-- Table Mieszkancy

CREATE TABLE C##MACIEK.Mieszkancy
(
    Nr_mieszkanca CHAR(36)     NOT NULL,
    Imie          Varchar2(20) NOT NULL,
    Nazwisko      Varchar2(30) NOT NULL,
    Id_mieszkania CHAR(36)     NOT NULL
);
/

-- Create indexes for table Mieszkancy

CREATE INDEX IX_Mieszka ON C##MACIEK.Mieszkancy (Id_mieszkania);
/

-- Add keys for table Mieszkancy

ALTER TABLE C##MACIEK.Mieszkancy
    ADD CONSTRAINT MieszkaniecPK PRIMARY KEY (Nr_mieszkanca);
/
