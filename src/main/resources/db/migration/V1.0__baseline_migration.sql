CREATE TABLE IF NOT EXISTS message
(
    id serial,
    message character varying(100) NOT NULL,
    message_cipher character varying(100) NOT NULL,
    rotation_factor int NOT NULL,
    CONSTRAINT message_pkey PRIMARY KEY (id)
);