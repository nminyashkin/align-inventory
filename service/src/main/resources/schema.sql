-- DROP SCHEMA align IF EXISTS;
--
-- CREATE SCHEMA align;

DROP TABLE STOCK IF EXISTS;

-- we declare id field as INTEGER (not LONGINT) as expecting that number of stocks will never exceed 2^31
CREATE TABLE STOCK (
  id       INTEGER AUTO_INCREMENT,
  brand    VARCHAR(50),
  name     VARCHAR(50),
  quantity INTEGER
)
