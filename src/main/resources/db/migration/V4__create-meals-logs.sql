CREATE TABLE meal_logs (
  id bigint AUTO_INCREMENT,
  checked smallint NOT NULL DEFAULT 0,
  date date NOT NULL,
  meal_id bigint NOT NULL,
  PRIMARY KEY(id),
  FOREIGN KEY(meal_id) REFERENCES meals(id)
);
