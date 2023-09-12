CREATE TABLE cats (
	id bigint AUTO_INCREMENT,
	name varchar(100) NOT NULL,
	birthday DATE,
	gender ENUM('male', 'female'),
	house bigint NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(house) REFERENCES houses(id)
)