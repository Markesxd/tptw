CREATE TABLE health_events (
  id bigint AUTO_INCREMENT,
  date date,
  name varchar(100),
  repeat_interval int,
  user_id varbinary(36),
  PRIMARY KEY(id),
  FOREIGN KEY(user_id) REFERENCES users(id)
);

CREATE TABLE health_events_cats (
  health_event_id bigint,
  cat_id bigint,
  FOREIGN KEY(health_event_id) REFERENCES health_events(id),
  FOREIGN KEY(cat_id) REFERENCES cats(id)
);
