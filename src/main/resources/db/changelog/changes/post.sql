-- liquibase formatted sql
--changeset vishnu:change_0001

CREATE TABLE post (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   created_at BIGINT,
   updated_at BIGINT,
   created_by VARCHAR(255),
   updated_by VARCHAR(255),
   user_uid BIGINT,
   content VARCHAR(255),
   location VARCHAR(255),
   post_type VARCHAR(255),
   status VARCHAR(255),
   CONSTRAINT pk_post PRIMARY KEY (id)
);

ALTER TABLE post ADD CONSTRAINT FK_POST_ON_USER_UID FOREIGN KEY (user_uid) REFERENCES users (id);