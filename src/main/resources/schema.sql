CREATE TABLE IF NOT EXISTS `REGIONS`(
    `id`                INT(11) AUTO_INCREMENT PRIMARY KEY,
    `name`              VARCHAR(20) NOT NULL UNIQUE ,
    `short_name`        VARCHAR(10) NOT NULL UNIQUE
);