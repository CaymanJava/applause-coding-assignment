CREATE TABLE applause.device
(
    id          BIGSERIAL              NOT NULL,
    description CHARACTER VARYING(255) NOT NULL,
    CONSTRAINT device_pkey PRIMARY KEY (id)
);

INSERT INTO applause.device
    VALUES (1, 'iPhone 4');

INSERT INTO applause.device
    VALUES (2, 'iPhone 4S');

INSERT INTO applause.device
    VALUES (3, 'iPhone 5');

INSERT INTO applause.device
    VALUES (4, 'Galaxy S3');

INSERT INTO applause.device
    VALUES (5, 'Galaxy S4');

INSERT INTO applause.device
    VALUES (6, 'Nexus 4');

INSERT INTO applause.device
    VALUES (7, 'Droid Razor');

INSERT INTO applause.device
    VALUES (8, 'Droid DNA');

INSERT INTO applause.device
    VALUES (9, 'HTC One');

INSERT INTO applause.device
    VALUES (10, 'iPhone 3');
