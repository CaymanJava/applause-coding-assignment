CREATE TABLE applause.tester_device
(
    tester_id BIGINT NOT NULL,
    device_id BIGINT NOT NULL,
    CONSTRAINT tester_device_pkey PRIMARY KEY (tester_id, device_id),
    CONSTRAINT tester_device_tester_id_fkey FOREIGN KEY (tester_id)
        REFERENCES applause.tester (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT tester_device_device_id_fkey FOREIGN KEY (device_id)
        REFERENCES applause.device (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TEMP TABLE tester_devices_tmp
(
    tester_id TEXT,
    device_id TEXT
) ON COMMIT DROP;


COPY tester_devices_tmp (tester_id, device_id)
    FROM '/docker-entrypoint-initdb.d/tester_device.csv' WITH (
    DELIMITER ',', HEADER TRUE, FORMAT CSV );


INSERT INTO applause.tester_device (tester_id, device_id)
SELECT tester_id::BIGINT,
       device_id::BIGINT
    FROM tester_devices_tmp;
