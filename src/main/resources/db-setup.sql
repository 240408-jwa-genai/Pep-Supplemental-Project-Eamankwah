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
	ownerId integer references users(id) ON DELETE CASCADE
);

create table moons(
	id integer primary key,
	name text,
	myPlanetId Integer references planets(id) ON DELETE CASCADE
);

INSERT INTO users VALUES(1,'enzo','fernandes');

DELETE  FROM users WHERE id= 3;
INSERT INTO planets VALUES (1,'Neptune',1);
INSERT INTO planets VALUES (2,'Venus',1);
INSERT INTO planets VALUES (3,'Mars',1);

SELECT u.username as [Owner Name],m.name as [Moon Name], p.name as [Planet Name]
FROM users u 
join planets p on u.id =p.ownerId 
join moons m on p.id = m.myPlanetId ;

SELECT m.id ,m.name,m.myPlanetId 
FROM users u 
join planets p on u.id =p.ownerId 
join moons m on p.id = m.myPlanetId 
WHERE u.id = 1;

DELETE FROM moons WHERE myPlanetId= 5;



INSERT INTO moons (name, myPlanetId) VALUES ('Neso',2);
INSERT INTO moons (name, myPlanetId) VALUES ('Gentile',2);


SELECT m.id,m.name ,m.myPlanetId  FROM moons m 
join planets p on m.myPlanetId = p.id 
WHERE m.myPlanetId =2;

