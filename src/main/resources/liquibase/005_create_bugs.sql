CREATE TABLE applause.bug
(
    id        BIGSERIAL NOT NULL,
    device_id BIGINT    NOT NULL,
    tester_id BIGINT    NOT NULL,
    CONSTRAINT bug_pkey PRIMARY KEY (id),
    CONSTRAINT bug_device_id_fkey FOREIGN KEY (device_id)
        REFERENCES applause.device (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT bug_tester_id_fkey FOREIGN KEY (tester_id)
        REFERENCES applause.tester (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TEMP TABLE bug_tmp
(
    bug_id    TEXT,
    device_id TEXT,
    tester_id TEXT
) ON COMMIT DROP;


COPY bug_tmp (bug_id, device_id, tester_id)
    FROM '/docker-entrypoint-initdb.d/bugs.csv' WITH (
    DELIMITER ',', HEADER TRUE, FORMAT CSV );


INSERT INTO applause.bug (id, device_id, tester_id)
SELECT bug_id::BIGINT,
       device_id::BIGINT,
       tester_id::BIGINT
    FROM bug_tmp;
