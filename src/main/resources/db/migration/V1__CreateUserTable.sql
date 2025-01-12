CREATE TABLE user
(
    user_ID         int            NOT NULL    AUTO_INCREMENT,
    first_name      varchar(20)    NOT NULL,
    last_name       varchar(30)    NOT NULL,
    email           varchar(25)    NOT NULL,
    phone_number    varchar(20)    NOT NULL,
    password        varchar(50)    NOT NULL,
    role            int            NOT NULL,
    PRIMARY KEY (user_ID),
    UNIQUE (email)
)


-- CREATE TABLE role
-- (
--     role_ID         int            NOT NULL    AUTO_INCREMENT,
--     role_name       varchar(10)    NOT NULL,
--     PRIMARY KEY (role_ID)
-- )