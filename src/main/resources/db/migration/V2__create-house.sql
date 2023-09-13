CREATE TABLE houses (
	id bigint AUTO_INCREMENT,
	name varchar(100),
	owner varchar(32) NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(owner) REFERENCES user(id)
)