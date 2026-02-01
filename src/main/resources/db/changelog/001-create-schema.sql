CREATE TABLE clients
(
    id         BIGSERIAL PRIMARY KEY,
    first_name TEXT        NOT NULL,
    last_name  TEXT        NOT NULL,
    phone      TEXT,
    email      TEXT        NOT NULL UNIQUE,
    language   TEXT,
    created_at TIMESTAMPTZ NOT NULL
);