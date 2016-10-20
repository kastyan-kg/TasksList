CREATE DATABASE taskslist;
USE taskslist;
CREATE TABLE tasks(
  id   INTEGER NOT NULL PRIMARY KEY PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  deadline DATE NOT NULL,
  priority VARCHAR(8) NOT NULL,
  finished TINYINT(1),
  overdue TINYINT(1)
                    );

INSERT INTO tasks(name, deadline, priority, finished, overdue) VALUES ('TODO test assigment', '2016-10-19', 'high', 0, 0);
INSERT INTO tasks(name, deadline, priority, finished, overdue) VALUES ('TODO smth', '2016-10-19', 'medium', 0, 0);
INSERT INTO tasks(name, deadline, priority, finished, overdue) VALUES ('TODO buy a ticket', '2016-10-19', 'low', 0, 0);
INSERT INTO tasks(name, deadline, priority, finished, overdue) VALUES ('have a cuo of tea after', '2013-10-19', 'medium', 0, 0);
INSERT INTO tasks(name, deadline, priority, finished, overdue) VALUES ('get up early', '2013-10-19', 'low', 0, 0);