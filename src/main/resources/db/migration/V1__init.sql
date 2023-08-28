CREATE TABLE files (
    id BIGINT AUTO_INCREMENT NOT NULL,
    filename VARCHAR(255) NOT NULL,
    hash VARCHAR(255) NOT NULL,
    CONSTRAINT pk_files PRIMARY KEY (id)
    CONSTRAINT uc_files_hash UNIQUE (hash)
);