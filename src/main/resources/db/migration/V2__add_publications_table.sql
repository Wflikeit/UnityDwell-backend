-- Add keys for table Ogloszenia
CREATE TABLE C##MACIEK.Ogloszenia
(
    Id_ogloszenia  CHAR(36)      NOT NULL,
    Data_wydania   Date          NOT NULL,
    Tresc          Varchar2(100) NOT NULL,
    Tytul          Varchar2(30)  NOT NULL,
    Id_spoldzielni CHAR(36)      NOT NULL
);
/

-- Create indexes for table Ogloszenia

CREATE INDEX IX_Wystawia_ogloszenie ON C##MACIEK.Ogloszenia (Id_spoldzielni);
/

-- Add keys for table Ogloszenia

ALTER TABLE C##MACIEK.Ogloszenia
    ADD CONSTRAINT OgloszeniePK PRIMARY KEY (Id_ogloszenia);
/
