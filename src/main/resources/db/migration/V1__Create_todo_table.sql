DROP SCHEMA todoEntry IF EXISTS;

CREATE SCHEMA todoEntry;

CREATE SEQUENCE todoEntry.s_todo_id START WITH 1;

CREATE TABLE todoEntry.todoEntry (
  id INT NOT NULL,
  title VARCHAR(256) NOT NULL,
  is_complete BOOLEAN,
  ordering INT,

  CONSTRAINT pk_t_todo PRIMARY KEY (id)
);