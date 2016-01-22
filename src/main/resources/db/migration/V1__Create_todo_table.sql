DROP SCHEMA todo IF EXISTS;

CREATE SCHEMA todo;

CREATE SEQUENCE todo.s_todo_id START WITH 1;

CREATE TABLE todo.todo (
  id INT NOT NULL,
  title VARCHAR(256) NOT NULL,
  is_complete BOOLEAN,
  ordering INT,

  CONSTRAINT pk_t_todo PRIMARY KEY (id)
);