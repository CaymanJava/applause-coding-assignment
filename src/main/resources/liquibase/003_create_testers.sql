CREATE TABLE applause.tester
(
    id         BIGSERIAL              NOT NULL,
    first_name CHARACTER VARYING(255) NOT NULL,
    last_name  CHARACTER VARYING(255) NOT NULL,
    country_id BIGINT                 NOT NULL,
    last_login TIMESTAMP WITH TIME ZONE,
    CONSTRAINT tester_pkey PRIMARY KEY (id),
    CONSTRAINT tester_country_id_fkey FOREIGN KEY (country_id)
        REFERENCES applause.country (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);

INSERT INTO applause.tester
    VALUES (1, 'Miguel', 'Bautista', 1, '2013-08-04 23:57:38');

INSERT INTO applause.tester
    VALUES (2, 'Michael', 'Lubavin', 1, '2013-07-12 13:27:18');

INSERT INTO applause.tester
    VALUES (3, 'Leonard', 'Sutton', 2, '2013-07-16 21:17:28');

INSERT INTO applause.tester
    VALUES (4, 'Taybin', 'Rutkin', 1, '2013-01-01 10:57:38');

INSERT INTO applause.tester
    VALUES (5, 'Mingquan', 'Zheng', 3, '2013-08-04 22:07:38');

INSERT INTO applause.tester
    VALUES (6, 'Stanley', 'Chen', 2, '2013-08-04 21:57:38');

INSERT INTO applause.tester
    VALUES (7, 'Lucas', 'Lowry', 3, '2013-07-12 23:57:38');

INSERT INTO applause.tester
    VALUES (8, 'Sean', 'Wellington', 3, '2013-08-05 13:27:38');

INSERT INTO applause.tester
    VALUES (9, 'Darshini', 'Thiagarajan', 2, '2013-08-05 15:00:38');
