CREATE TABLE rooms
(
    ID              SERIAL PRIMARY KEY NOT NULL,
    NAME            VARCHAR(255) UNIQUE,
    INFO            TEXT NULL,
    MAX_CAPACITY    INTEGER NOT NULL
);

CREATE TABLE patient
(
    ID              SERIAL PRIMARY KEY NOT NULL,
    HASHED_PASSWORD VARCHAR           NOT NULL,
    PERSONAL_ID     VARCHAR(50)       NOT NULL,
    NAME            VARCHAR(50)       NOT NULL,
    PHONE           VARCHAR(50)       NOT NULL,
    ROOM_ID         INTEGER           NULL,
    FOREIGN KEY (ROOM_ID) REFERENCES rooms(ID)
);

CREATE TABLE health_records
(
    ID              SERIAL PRIMARY KEY NOT NULL,
    CREATED_ON      timestamp,
    PATIENT_ID      INTEGER NOT NULL,
    TEMPERATURE     DECIMAL NOT NULL,
    COUGH           BOOLEAN NOT NULL,
    HEADACHE        BOOLEAN NOT NULL,
    THROAT_ACHE     BOOLEAN NOT NULL,
    FOREIGN KEY (PATIENT_ID) REFERENCES patient(ID)
);

--CREATE TABLE supervisor
--(
--    ID            SERIAL PRIMARY KEY NOT NULL,
--    USERNAME      VARCHAR(200)       NOT NULL,
--    PASSWORD_HASH VARCHAR(200)       NOT NULL,
--    EXTRA_DATA    TEXT               NULL
--);

--
--INSERT INTO supervisor(username, password_hash)
--VALUES ('hotelcovid19@ticparabien.org', '$2a$12$5KiR0C/FopFFHk3m39xf6eQyiiBbHUSrYVnhbsFXX87Sg6zUGXue2');
--
--INSERT INTO Rooms_owners(Room, owner)
--VALUES (1, 1),
--       (2, 1),
--       (3, 1);
