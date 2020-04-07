DROP TABLE IF EXISTS task;

CREATE TABLE task (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  description VARCHAR(250) NOT NULL
);

INSERT INTO task (name, description) VALUES
  ('name1', 'This is name 1'),
  ('name2', 'This is name 2'),
  ('name3', 'This is name 3');