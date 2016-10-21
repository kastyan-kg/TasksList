CREATE DATABASE taskslist;
USE taskslist;
CREATE TABLE tasks(
  id   INTEGER NOT NULL PRIMARY KEY PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  deadline DATE NOT NULL,
  priority VARCHAR(8) NOT NULL,
  finished TINYINT(1)
                    );

INSERT INTO tasks(name, deadline, priority, finished) VALUES ('TODO test assigment', '2016-10-19', 'HIGH', 0);
INSERT INTO tasks(name, deadline, priority, finished) VALUES ('TODO smth', '2016-10-19', 'MEDIUM', 0);
INSERT INTO tasks(name, deadline, priority, finished) VALUES ('TODO buy a ticket', '2016-10-19', 'LOW', 0);
INSERT INTO tasks(name, deadline, priority, finished) VALUES ('have a cuo of tea after', '2013-10-19', 'MEDIUM', 0);
INSERT INTO tasks(name, deadline, priority, finished) VALUES ('get up early', '2013-10-19', 'LOW', 0);