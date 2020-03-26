DROP TABLE IF EXISTS Rooms;
CREATE TABLE Rooms
(
    ID        SERIAL PRIMARY KEY NOT NULL,
    NAME      VARCHAR(200)       NOT NULL,
    EXTRADATA TEXT               NULL
);


DROP TABLE IF EXISTS patient;
CREATE TABLE patient
(
    ID              SERIAL PRIMARY KEY NOT NULL,
    PERSONAL_ID     VARCHAR(50)       NOT NULL,
    NAME            VARCHAR(50)       NOT NULL,
    PHONE           VARCHAR(50)       NOT NULL
);

DROP TABLE IF EXISTS Rooms_owners;
CREATE TABLE Rooms_owners
(
    ID    SERIAL PRIMARY KEY NOT NULL,
    Room  INTEGER,
    OWNER INTEGER
);

DROP TABLE IF EXISTS supervisor;
CREATE TABLE supervisor
(
    ID            SERIAL PRIMARY KEY NOT NULL,
    USERNAME      VARCHAR(200)       NOT NULL,
    PASSWORD_HASH VARCHAR(200)       NOT NULL,
    EXTRA_DATA    TEXT               NULL
);

INSERT INTO Rooms(name)
VALUES ('Baberia Paco'),
       ('Tim Barbers'),
       ('Superbarbers');

INSERT INTO supervisor(username, password_hash)
VALUES ('hotelcovid19@ticparabien.org', '$2a$12$5KiR0C/FopFFHk3m39xf6eQyiiBbHUSrYVnhbsFXX87Sg6zUGXue2');

INSERT INTO Rooms_owners(Room, owner)
VALUES (1, 1),
       (2, 1),
       (3, 1);

DROP TABLE IF EXISTS health_records;
CREATE TABLE health_records
(
    ID         SERIAL PRIMARY KEY    NOT NULL,
    PATIENT_ID INTEGER               NULL,
    FEVER      DECIMAL DEFAULT 0.0   NULL,
    COUGH      BOOLEAN DEFAULT FALSE NULL,
    HEADACHE   BOOLEAN DEFAULT FALSE NULL,
    THROAT_ACHE BOOLEAN DEFAULT FALSE NULL
);
