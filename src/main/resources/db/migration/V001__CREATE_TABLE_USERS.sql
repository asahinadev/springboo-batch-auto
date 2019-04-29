CREATE TABLE users (
	id       INT AUTO_INCREMENT PRIMARY KEY,
	username VARCHAR(255),
	email    VARCHAR(255),
	password VARCHAR(255),
	created  DATE,
	updated  DATE,
	deleted  DATE
);

CREATE UNIQUE INDEX idx_users_email    ON users(deleted, email);
CREATE UNIQUE INDEX idx_users_username ON users(deleted, username);
