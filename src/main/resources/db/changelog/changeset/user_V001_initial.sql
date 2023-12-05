CREATE TABLE users
(
    id         BIGSERIAL PRIMARY KEY ,
    first_name VARCHAR(255),
    last_name  VARCHAR(255),
    email      VARCHAR(255) UNIQUE,
    password   VARCHAR(1000)
);

CREATE TABLE user_role
(
    user_id BIGINT REFERENCES users (id),
    roles   VARCHAR(20)
);