ALTER TABLE C##MACIEK.WLASCICIELE_MIESZKAN
    ADD Email VARCHAR2(100) NOT NULL;

ALTER TABLE C##MACIEK.WLASCICIELE_MIESZKAN
    ADD CONSTRAINT Email_WLASCICIELA UNIQUE (Email);
