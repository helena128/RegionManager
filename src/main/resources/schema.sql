CREATE TABLE IF NOT EXISTS `ARTICLES`(
    `id`                UUID PRIMARY KEY DEFAULT RANDOM_UUID(),
    `name`              VARCHAR(20) NOT NULL UNIQUE ,
    `short_name`        VARCHAR(10) NOT NULL UNIQUE
);