CREATE TABLE user (
    id varbinary(36),
    name varchar(100) NOT NULL,
    password varchar(100) NOT NULL,
    email varchar(100) NOT NULL,
    PRIMARY KEY(id)
)
