CREATE TABLE plans (
	id bigint AUTO_INCREMENT,
	name varchar(100) NOT NULL,
	user_id varbinary(36) NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(user_id) REFERENCES users(id)
);

CREATE TABLE meals (
	id bigint AUTO_INCREMENT,
	label varchar(100) NOT NULL,
	plan_id bigint NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(plan_id) REFERENCES plans(id)
);

ALTER TABLE cats ADD COLUMN plan_id bigint;

ALTER TABLE cats ADD FOREIGN KEY (plan_id) REFERENCES plans(id); 