UPDATE pg_database SET datistemplate = FALSE WHERE datname = 'template1';
DROP DATABASE template1;
CREATE DATABASE template1 WITH TEMPLATE = template0 ENCODING = 'UNICODE';
UPDATE pg_database SET datistemplate = TRUE WHERE datname = 'template1';
\c template1
VACUUM FREEZE;

CREATE USER klix_admin WITH PASSWORD '4488';
CREATE DATABASE klix ENCODING 'UNICODE';
\connect klix
GRANT ALL PRIVILEGES ON DATABASE klix TO klix_admin;
