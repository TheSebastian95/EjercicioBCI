INSERT INTO user (name, email, password, create_at)
VALUES ('Demo 1', 'sebastian1@gmail.com', '123', parsedatetime('17-09-2012 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'));

INSERT INTO user (name, email, password, create_at)
VALUES ('Demo 2', 'sebastian2@gmail.com', '123', parsedatetime('17-09-2012 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'));

INSERT INTO user (name, email, password, create_at)
VALUES ('Demo 3', 'sebastian3@gmail.com', '123', parsedatetime('17-09-2012 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'));

INSERT INTO user (name, email, password, create_at)
VALUES ('Demo 4', 'sebastian4@gmail.com', '123', parsedatetime('17-09-2012 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'));

INSERT INTO phone (user_id, number, city_code, country_code)
VALUES (1, 1234567, 1, 57);

INSERT INTO phone (user_id, number, city_code, country_code)
VALUES (1, 1234568, 3, 57);