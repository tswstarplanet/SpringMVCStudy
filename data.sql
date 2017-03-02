CREATE TABLE users(
  userid INTEGER(11) NOT NULL AUTO_INCREMENT,
  username VARCHAR(45) NOT NULL ,
  email VARCHAR(255) NOT NULL ,
  password VARCHAR(60) NOT NULL ,
  online_status INTEGER(1) NOT NULL ,
  PRIMARY KEY (userid)
)

SELECT * FROM users;

SELECT * FROM user_roles;

CREATE TABLE spittle(
  id INTEGER(11) NOT NULL AUTO_INCREMENT,
  userid INTEGER(11) NOT NULL ,
  content VARCHAR(10000) NOT NULL ,
  PRIMARY KEY (id),
  CONSTRAINT FOREIGN KEY (userid) REFERENCES users (userid)
)

SELECT * FROM spittle;

CREATE TABLE friend(
  id INTEGER(11) NOT NULL AUTO_INCREMENT,
  userid INTEGER(11) NOT NULL ,
  friendid INTEGER(11) NOT NULL ,
  status INTEGER(1) NOT NULL ,
  actionid INTEGER(11) NOT NULL ,
  PRIMARY KEY (id),
  CONSTRAINT FOREIGN KEY (userid) REFERENCES users (userid) ON DELETE CASCADE ON UPDATE CASCADE ,
  CONSTRAINT FOREIGN KEY (friendid) REFERENCES users (userid) ON DELETE CASCADE ON UPDATE CASCADE
)

SELECT * FROM friend;

DELETE FROM friend;

SELECT userid, friendid, actionid FROM friend WHERE (userid = 4 OR friendid = 4) AND status = 0 AND actionid != 4

SELECT * FROM friend;

DROP TABLE spittle;

DROP TABLE friend;

DROP TABLE user_roles;

DROP TABLE users;

CREATE TABLE user_roles(
  user_role_id INTEGER(11) NOT NULL AUTO_INCREMENT,
  userid INTEGER(11) NOT NULL ,
  role VARCHAR(45) NOT NULL ,
  PRIMARY KEY (user_role_id),
  UNIQUE KEY uni_userid_role (role, userid),
  CONSTRAINT FOREIGN KEY (userid) REFERENCES users (userid)
);

CREATE TABLE spittle_notice(
  id INTEGER(11) NOT NULL AUTO_INCREMENT,
  userid INTEGER(11) NOT NULL,
  friendid INTEGER(11) NOT NULL,
  spittle_id INTEGER(11) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FOREIGN KEY (userid) REFERENCES users (userid),
  CONSTRAINT FOREIGN KEY (friendid) REFERENCES users (userid),
  CONSTRAINT FOREIGN KEY (spittle_id) REFERENCES spittle (id)
)

SELECT * FROM spittle_notice;

DROP TABLE spittle_notice;

SHOW INDEX FROM user_roles;

INSERT INTO user_roles (userid, role) VALUES (1, "ROLE_ADMIN");
INSERT INTO user_roles (userid, role) VALUES (2, "ROLE_USER");