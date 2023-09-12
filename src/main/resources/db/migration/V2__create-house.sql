CREATE TABLE houses (
	id bigint AUTO_INCREMENT,
	name varchar(100),
	owner int NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(owner) REFERENCES user(id)
)