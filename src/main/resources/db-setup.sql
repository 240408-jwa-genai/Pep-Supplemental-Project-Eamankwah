-- Use this script to set up your Planetarium database

-- needed for referential integrity enforcement
PRAGMA foreign_keys = 1;

create table users(
	id integer primary key,
	username text unique,
	password text
);

create table planets(
	id integer primary key,
	name text,
	ownerId integer references users(id)
);

create table moons(
	id integer primary key,
	name text,
	myPlanetId Integer references planets(id)
);

INSERT INTO users VALUES(1,'enzo','fernandes');

DELETE  FROM users WHERE id= 3;
INSERT INTO planets VALUES (1,'Neptune',1);
INSERT INTO planets VALUES (2,'Venus',1);
INSERT INTO planets VALUES (3,'Mars',1);

