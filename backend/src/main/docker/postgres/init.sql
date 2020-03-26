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
  ID           SERIAL PRIMARY KEY NOT NULL,
  USERNAME     VARCHAR(200)       NOT NULL,
  PASSWORDHASH VARCHAR(200)       NOT NULL,
  EXTRADATA    TEXT               NULL
);

DROP TABLE IF EXISTS Rooms_owners;
CREATE TABLE Rooms_owners
(
  ID        SERIAL PRIMARY KEY NOT NULL,
  Room INTEGER,
  OWNER INTEGER
);

INSERT INTO Rooms(name)
VALUES ('Baberia Paco'),
  ('Tim Barbers'),
  ('Superbarbers');

INSERT INTO patient(username, passwordhash)
VALUES ('hotelcovid19@ticparabien.org', '$2a$12$5KiR0C/FopFFHk3m39xf6eQyiiBbHUSrYVnhbsFXX87Sg6zUGXue2');

INSERT INTO Rooms_owners(Room, owner)
VALUES (1, 1),
  (2, 1),
  (3, 1);
