CREATE TABLE applause.country
(
    id   BIGSERIAL            NOT NULL,
    code CHARACTER VARYING(4) NOT NULL,
    CONSTRAINT country_pkey PRIMARY KEY (id)
);

INSERT INTO applause.country
    VALUES (1, 'US');
INSERT INTO applause.country
    VALUES (2, 'GB');
INSERT INTO applause.country
    VALUES (3, 'JP');
