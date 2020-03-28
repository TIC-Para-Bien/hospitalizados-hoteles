CREATE TABLE patient
(
    ID              SERIAL PRIMARY KEY NOT NULL,
    PERSONAL_ID     VARCHAR(50)       NOT NULL,
    NAME            VARCHAR(50)       NOT NULL,
    PHONE           VARCHAR(50)       NOT NULL
);

CREATE TABLE health_records
(
    ID              SERIAL PRIMARY KEY NOT NULL,
    CREATED_ON      timestamp,
    PATIENT_ID      INTEGER NOT NULL,
    TEMPERATURE     DECIMAL NOT NULL,
    COUGH           BOOLEAN NOT NULL,
    HEADACHE        BOOLEAN NOT NULL,
    THROAT_ACHE     BOOLEAN NOT NULL
);

--CREATE TABLE Rooms_owners
--(
--    ID    SERIAL PRIMARY KEY NOT NULL,
--    Room  INTEGER,
--    OWNER INTEGER
--);

--CREATE TABLE Rooms
--(
--    ID        SERIAL PRIMARY KEY NOT NULL,
--    NAME      VARCHAR(200)       NOT NULL,
--    EXTRADATA TEXT               NULL
--);

--CREATE TABLE supervisor
--(
--    ID            SERIAL PRIMARY KEY NOT NULL,
--    USERNAME      VARCHAR(200)       NOT NULL,
--    PASSWORD_HASH VARCHAR(200)       NOT NULL,
--    EXTRA_DATA    TEXT               NULL
--);

--
--INSERT INTO Rooms(name)
--VALUES ('Baberia Paco'),
--       ('Tim Barbers'),
--       ('Superbarbers');
--
--INSERT INTO supervisor(username, password_hash)
--VALUES ('hotelcovid19@ticparabien.org', '$2a$12$5KiR0C/FopFFHk3m39xf6eQyiiBbHUSrYVnhbsFXX87Sg6zUGXue2');
--
--INSERT INTO Rooms_owners(Room, owner)
--VALUES (1, 1),
--       (2, 1),
--       (3, 1);
