drop table if exists film CASCADE;

create table film 
(
	id integer PRIMARY KEY AUTO_INCREMENT, 
	runtime integer not null, 
	director varchar(255), 
	age_rating integer not null, 
	title varchar(255)
);