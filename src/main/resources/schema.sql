DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS phone;

CREATE TABLE user
(
    id IDENTITY NOT NULL PRIMARY KEY,
    name       VARCHAR(50)        NOT NULL,
    email      VARCHAR(50) UNIQUE NOT NULL,
    password   VARCHAR(50)        NOT NULL,
    create_at  TIMESTAMP          NOT NULL,
    modified   TIMESTAMP,
    last_login TIMESTAMP,
    is_active  BOOLEAN DEFAULT true
);

CREATE TABLE phone
(
    id IDENTITY NOT NULL PRIMARY KEY,
    user_id      BIGINT NOT NULL,
    number       INT    NOT NULL,
    city_code    INT    NOT NULL,
    country_code INT    NOT NULL,
    CONSTRAINT fk_order_with_user FOREIGN KEY (user_id)
        REFERENCES user (id)
);