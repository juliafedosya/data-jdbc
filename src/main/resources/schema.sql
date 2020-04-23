create table doctor(
id integer identity primary key,
first_name varchar(30),
date_of_birth date);

create table patient (
id integer identity primary key,
first_name varchar(30),
date_of_birth date,
doctor integer references doctor(id));

create table address(
id integer identity primary key,
patient integer references patient(id),
address_line varchar(30),
postal_code varchar (30));




