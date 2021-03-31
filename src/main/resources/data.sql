INSERT INTO USERS (username,password,enabled) VALUES ('happy','12345',1);
INSERT INTO AUTHORITIES (username,authority) VALUES ('happy', 'write');
INSERT INTO CUSTOMERS (email,pwd,role) VALUES ('test@example.com', '54321','admin');
INSERT INTO CUSTOMERS (email,pwd,role) VALUES ('happy@example.com', '$2y$12$QmAW40KKjUEXCzn3fZQzM.J2GeqfanKAWJaJL0JgH2hAp4DaUzUum','admin');