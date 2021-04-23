
CREATE SEQUENCE entity_id_sequence START 1 INCREMENT 1;
CREATE TABLE counter_entity (
    id BIGINT NOT NULL,
    value BIGINT NOT NULL
        CONSTRAINT value_within_accepted_range CHECK (value>=0 AND value<=10),
    PRIMARY KEY (id)
);
