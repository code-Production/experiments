DROP SCHEMA IF EXISTS experiments;
CREATE SCHEMA IF NOT EXISTS experiments;

SET search_path = "experiments";

DROP TABLE IF EXISTS Students;
CREATE TABLE IF NOT EXISTS Students(
    id bigserial PRIMARY KEY,
    name varchar(255) NOT NULL,
    mark real NOT NULL
);