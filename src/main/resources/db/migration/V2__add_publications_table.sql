-- Add keys for table Ogloszenia
CREATE TABLE Ogloszenia
(
    Id_ogloszenia  CHAR(36)      NOT NULL
        CONSTRAINT ValidValuesId_ogloszenia CHECK ((Id_ogloszenia >= 1)),
    Data_wydania   Date          NOT NULL,
    Tresc          Varchar2(100) NOT NULL,
    Tytul          Varchar2(30)  NOT NULL,
    Id_spoldzielni CHAR(36)      NOT NULL
)
/

-- Create indexes for table Ogloszenia

CREATE INDEX IX_Wystawia_ogloszenie ON Ogloszenia (Id_spoldzielni)
/

-- Add keys for table Ogloszenia

ALTER TABLE Ogloszenia
    ADD CONSTRAINT OgloszeniePK PRIMARY KEY (Id_ogloszenia)
/
