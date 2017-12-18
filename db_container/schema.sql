-- tables
-- Table: link_metadata
CREATE TABLE link_metadata (
    id bigserial  NOT NULL,
    clicks bigint  NOT NULL,
    created timestamp  NOT NULL,
    user_id int8  NOT NULL,
    CONSTRAINT link_metadata_pk PRIMARY KEY (id)
);

-- Table: links
CREATE TABLE links (
    id char(6)  NOT NULL,
    url text  NOT NULL,
    link_metadata_id int8  NOT NULL,
    CONSTRAINT links_pk PRIMARY KEY (id)
);

CREATE UNIQUE INDEX url_index on links (url DESC);

-- Table: roles
CREATE TABLE roles (
    id int  NOT NULL,
    role_name varchar(32)  NOT NULL,
    CONSTRAINT roles_pk PRIMARY KEY (id)
);

-- Table: user_metadata
CREATE TABLE user_metadata (
    username varchar(32)  NOT NULL,
    first_name text  NOT NULL,
    last_name text  NOT NULL,
    email text  NOT NULL,
    created timestamp  NOT NULL,
    password_hash varchar(255)  NOT NULL,
    CONSTRAINT user_metadata_pk PRIMARY KEY (username)
);

-- Table: user_roles
CREATE TABLE user_roles (
    role_id int  NOT NULL,
    user_id int8  NOT NULL,
    CONSTRAINT user_roles_pk PRIMARY KEY (role_id,user_id)
);

-- Table: users
CREATE TABLE users (
    id bigserial  NOT NULL,
    user_metadata_id varchar(32)  NOT NULL,
    CONSTRAINT users_pk PRIMARY KEY (id)
);

-- foreign keys
-- Reference: link_metadata_users (table: link_metadata)
ALTER TABLE link_metadata ADD CONSTRAINT link_metadata_users
    FOREIGN KEY (user_id)
    REFERENCES users (id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: links_link_metadata (table: links)
ALTER TABLE links ADD CONSTRAINT links_link_metadata
    FOREIGN KEY (link_metadata_id)
    REFERENCES link_metadata (id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: user_roles_roles (table: user_roles)
ALTER TABLE user_roles ADD CONSTRAINT user_roles_roles
    FOREIGN KEY (role_id)
    REFERENCES roles (id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: user_roles_users (table: user_roles)
ALTER TABLE user_roles ADD CONSTRAINT user_roles_users
    FOREIGN KEY (user_id)
    REFERENCES users (id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: users_user_metadata (table: users)
ALTER TABLE users ADD CONSTRAINT users_user_metadata
    FOREIGN KEY (user_metadata_id)
    REFERENCES user_metadata (username)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- sequences
-- Sequence: short_key_seq
CREATE SEQUENCE short_key_seq
      INCREMENT BY 1
      MINVALUE 0
      NO MAXVALUE
      START WITH 0
      NO CYCLE
;

-- End of file.

