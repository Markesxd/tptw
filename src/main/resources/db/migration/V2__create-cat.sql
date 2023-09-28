CREATE TABLE cats (
	id bigint AUTO_INCREMENT,
	name varchar(100) NOT NULL,
	birthday DATE,
	gender varchar(10),
	user_id varbinary(36) NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(user_id) REFERENCES users(id)
)
