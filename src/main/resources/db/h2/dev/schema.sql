DROP TABLE IF EXISTS member;
DROP TABLE IF EXISTS laundry_visit_history;
DROP TABLE IF EXISTS laundry_like;


CREATE TABLE member (
       PRIMARY KEY (member_id),
       member_id        INT         NOT NULL AUTO_INCREMENT,
       phone_num        CHAR(11)    NOT NULL UNIQUE,
       password         VARCHAR(50) NOT NULL,
       nickname         VARCHAR(10) NOT NULL,
       birthday         DATE,
       gender           CHAR(1),
       auto_login_yn    BOOLEAN     NOT NULL DEFAULT false,
       join_date        TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
       withdrawal_date  TIMESTAMP
);

CREATE TABLE laundry_visit_history(
        PRIMARY KEY (visit_history_seq),
        visit_history_seq INT         NOT NULL AUTO_INCREMENT,
        laundry_id        INT         NOT NULL,
        facility_id       INT         NOT NULL,
        member_id         INT         NOT NULL,
        visit_date        TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE laundry_like(
        PRIMARY KEY(laundry_like_id),
        laundry_like_id   INT     NOT NULL AUTO_INCREMENT,
        member_id         INT     NOT NULL,
        laundry_id        INT     NOT NULL,
        like_date         INT     NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE laundry (
       PRIMARY KEY (laundry_id),
       laundry_id              INT            NOT NULL AUTO_INCREMENT,
       name                    VARCHAR(30)    NOT NULL,
       jibun_address           VARCHAR(100),
       jibun_address_detail    VARCHAR(100),
       doro_address            VARCHAR(100),
       doro_address_detail     VARCHAR(100),
       latitude                DECIMAL(13,10),
       longitude               DECIMAL(13,10),
       partnership             BOOLEAN        NOT NULL
);

CREATE TABLE facility (
       PRIMARY KEY (facility_id),
       facility_id	INT NOT NULL AUTO_INCREMENT,
       name	VARCHAR(20) NOT NULL,
       price	INT
);

CREATE TABLE facility_holding (
       laundry_id INT,
       facility_id INT
);
