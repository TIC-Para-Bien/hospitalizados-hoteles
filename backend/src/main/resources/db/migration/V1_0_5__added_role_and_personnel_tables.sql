CREATE TABLE personnel
(
    ID              SERIAL          PRIMARY KEY NOT NULL,
    USERNAME        VARCHAR         UNIQUE,
    HASHED_PASSWORD VARCHAR         NOT NULL,
    PERSONAL_ID     VARCHAR         UNIQUE,
    NAME            VARCHAR(50)     NOT NULL,
    PHONE           VARCHAR(50)     UNIQUE
);

CREATE TABLE roles
(
    ID              SERIAL          PRIMARY KEY NOT NULL,
    NAME            VARCHAR(50)     UNIQUE NOT NULL
);

CREATE TABLE user_roles
(
    USER_ID     INTEGER NOT NULL,
    ROLE_ID     INTEGER NOT NULL,
    PRIMARY KEY (USER_ID, ROLE_ID)
);

INSERT INTO roles VALUES (1, 'ADMIN');
INSERT INTO roles VALUES (2, 'PATIENT');
INSERT INTO roles VALUES (3, 'PERSONNEL');

create table hibernate_sequences (
    sequence_name VARCHAR(255) NOT NULL,
    next_val NUMERIC(19,0),
    PRIMARY KEY (sequence_name)
)
