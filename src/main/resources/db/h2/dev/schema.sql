DROP TABLE IF EXISTS member;

CREATE TABLE member (
       PRIMARY KEY (member_id),
       member_id      INT         NOT NULL AUTO_INCREMENT,
       phone_num      CHAR(11)    NOT NULL UNIQUE,
       password       VARCHAR(50) NOT NULL,
       nickname       VARCHAR(10) NOT NULL,
       birthday       DATE,
       gender         CHAR(1),
       auto_login_yn  BOOLEAN     NOT NULL DEFAULT false,
       join_date      TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP
);