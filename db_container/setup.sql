UPDATE pg_database SET datistemplate = FALSE WHERE datname = 'template1';
DROP DATABASE template1;
CREATE DATABASE template1 WITH TEMPLATE = template0 ENCODING = 'UNICODE';
UPDATE pg_database SET datistemplate = TRUE WHERE datname = 'template1';
\c template1
VACUUM FREEZE;

CREATE USER yuzlink_admin WITH PASSWORD '4488';
CREATE DATABASE yuzlink ENCODING 'UNICODE';
\connect yuzlink
GRANT ALL PRIVILEGES ON DATABASE yuzlink TO yuzlink_admin;
