CREATE TABLE tasks
(
    id          BIGSERIAL PRIMARY KEY,
    heading     VARCHAR(100) NOT NULL,
    description VARCHAR(1000),
    status      SMALLINT DEFAULT 1,
    priority    SMALLINT DEFAULT 1,
    author_id   BIGINT REFERENCES users (id),
    executor_id BIGINT REFERENCES users (id),
    created_at  TIMESTAMP WITHOUT TIME ZONE,
    updated_at  TIMESTAMP WITHOUT TIME ZONE
);

CREATE TABLE comments
(
    id         BIGSERIAL PRIMARY KEY,
    content    VARCHAR(1000),
    author_id  BIGINT REFERENCES users (id),
    task_id    BIGINT REFERENCES users (id),
    created_at TIMESTAMP WITHOUT TIME ZONE,
    updated_at TIMESTAMP WITHOUT TIME ZONE
);