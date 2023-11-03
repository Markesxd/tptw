CREATE TABLE sandboxes (
  id bigint AUTO_INCREMENT,
  clean_date date,
  name varchar(100),
  user_id varbinary(36),
  PRIMARY KEY(id),
  FOREIGN KEY(user_id) REFERENCES users(id)
);
