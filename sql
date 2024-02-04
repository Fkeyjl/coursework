CREATE DATABASE enrollment_db;
CREATE TABLE role
(
	id INT auto_increment
		PRIMARY KEY,
	name VARCHAR(45) NOT NULL
);

CREATE TABLE speciality
(
	id INT auto_increment
		PRIMARY KEY,
	name VARCHAR(100) NOT NULL,
	is_available TINYINT(1) NOT NULL
);

CREATE TABLE state
(
	id INT auto_increment
		PRIMARY KEY,
	name VARCHAR(45) NOT NULL
);

CREATE TABLE period
(
	id INT auto_increment
		PRIMARY KEY,
	name VARCHAR(45) NOT NULL,
	state INT NOT NULL,
	CONSTRAINT fk_period_state1
		FOREIGN KEY (state) REFERENCES state (id)
);

CREATE INDEX fk_period_state1_idx
	ON period (state);

CREATE TABLE status
(
	id INT auto_increment
		PRIMARY KEY,
	name VARCHAR(45) NOT NULL
);

CREATE TABLE subject
(
	id INT auto_increment
		PRIMARY KEY,
	name VARCHAR(45) NOT NULL,
	is_available TINYINT(1) NULL
);

CREATE TABLE subject_speciality
(
	subject INT NOT NULL,
	speciality INT NOT NULL,
	PRIMARY KEY (subject, speciality),
	CONSTRAINT fk_subject_speciality_speciality1
		FOREIGN KEY (speciality) REFERENCES speciality (id),
	CONSTRAINT fk_subject_speciality_subject1
		FOREIGN KEY (subject) REFERENCES subject (id)
);

CREATE INDEX fk_subject_speciality_speciality1_idx
	ON subject_speciality (speciality);

CREATE TABLE user
(
	id INT auto_increment
		PRIMARY KEY,
	firstname VARCHAR(45) NOT NULL,
	surname VARCHAR(45) NOT NULL,
	password VARCHAR(100) NOT NULL,
	role INT NOT NULL,
	email VARCHAR(60) NOT NULL,
	CONSTRAINT email_UNIQUE
		UNIQUE (email),
	CONSTRAINT fk_user_role
		FOREIGN KEY (role) REFERENCES role (id)
);

CREATE TABLE applicant
(
	id INT auto_increment
		PRIMARY KEY,
	user INT NOT NULL,
	period INT NOT NULL,
	speciality NOT NULL,
	status INT NOT NULL,
	rating INT null,
	CONSTRAINT index_user_period
		UNIQUE (user, period),
	CONSTRAINT fk_aplicant_period1
		FOREIGN KEY (period) REFERENCES period (id),
	CONSTRAINT fk_aplicant_speciality1
		FOREIGN KEY (speciality) REFERENCES speciality (id),
	CONSTRAINT fk_aplicant_status1
		FOREIGN KEY (status) REFERENCES status (id),
	CONSTRAINT fk_aplicant_user1
		FOREIGN KEY (user) REFERENCES user (id)
);

CREATE INDEX fk_aplicant_speciality1_idx
	ON applicant (speciality);

CREATE INDEX fk_aplicant_status1_idx
	ON applicant (status);

CREATE TABLE applicant_subject
(
	applicant INT NOT NULL,
	subject INT NOT NULL,
	mark INT null,
	PRIMARY KEY (applicant, subject),
	CONSTRAINT fk_aplicant_subject_subject1
		FOREIGN KEY (subject) REFERENCES subject (id),
	CONSTRAINT fk_aplicant_sujbject_applicant
		FOREIGN KEY (applicant) REFERENCES applicant (id)
);

CREATE INDEX fk_aplicant_subject_subject1_idx
	ON applicant_subject (subject);

CREATE INDEX fk_user_role_idx
	ON user (role);

INSERT INTO role(name)
VALUES('admin'), ('user');
INSERT INTO state(name)
VALUES('choice subjects'), ('check tests'), ('choice speciality'), ('ended');
INSERT INTO period(name, state)
VALUES('Test period', 1);
INSERT INTO user (firstname, surname, password, role, email)
VALUES ('admin', 'admin', 'd4e0f6a3a42d6add30a3b37f2c495d35de3bf31b70a05e37144bb6b11ad2475a', 1, 'admin@gmail.com');
INSERT INTO status(name)
VALUES('candidate under consideration'), ('accepted'), ('denied');
INSERT INTO subject(name, is_available)
VALUES('Data Structures and Algorithms', 1);
