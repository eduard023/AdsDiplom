

CREATE TABLE users
(
    id         SERIAL PRIMARY KEY,
    username      VARCHAR(50)  NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    first_name VARCHAR(50)  NOT NULL,
    last_name  VARCHAR(50)  NOT NULL,
    phone      VARCHAR(20)  NOT NULL,
    role       VARCHAR(50)  NOT NULL,
    image      TEXT
);

CREATE TABLE ads
(
    id          SERIAL PRIMARY KEY,
    author_id   INT  NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    price       INT  NOT NULL,
    title       VARCHAR(100),
    description TEXT,
    image       TEXT
);

CREATE TABLE comments
(
    id         SERIAL PRIMARY KEY,
    ad_id      INT       NOT NULL REFERENCES ads (id) ON DELETE CASCADE,
    author_id  INT       NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    created_at TIMESTAMP NOT NULL,
    text       TEXT      NOT NULL
);