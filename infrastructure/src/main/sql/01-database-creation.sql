\c example_db

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE USER example_user
WITH PASSWORD 'password'
NOCREATEDB;

GRANT SELECT, INSERT, UPDATE, DELETE
      ON ALL TABLES
          IN SCHEMA public
          TO example_user;

CREATE TABLE IF NOT EXISTS "user"
(
    id       UUID    NOT NULL,
    password VARCHAR NOT NULL,
    CONSTRAINT user_pk PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS user_role
(
    user_id UUID NOT NULL,
    role    SMALLINT,
    CONSTRAINT user_role_pk PRIMARY KEY (user_id, role),
    CONSTRAINT user_role_fk_user_id FOREIGN KEY (user_id) REFERENCES "user" (id)
    );
CREATE INDEX user_role_fk_user_id_idx ON user_role (user_id);

CREATE TABLE IF NOT EXISTS refresh_token
(
    user_id         UUID      NOT NULL,
    value           UUID      NOT NULL,
    expiration_date TIMESTAMP NOT NULL,
    CONSTRAINT refresh_token_pk PRIMARY KEY (user_id),
    CONSTRAINT refresh_token_fk_user_id FOREIGN KEY (user_id) REFERENCES "user" (id)
    );
CREATE INDEX refresh_token_fk_user_id_idx ON user_role (user_id);