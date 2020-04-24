create table hospital (
id integer identity primary key,
name varchar(50),
address varchar(100));

create table department (
id integer identity primary key,
name varchar(30),
hospital integer references hospital(id));

create table doctor(
id integer identity primary key,
first_name varchar(30),
date_of_birth date,
department integer references department(id));

create table patient (
id integer identity primary key,
first_name varchar(30),
date_of_birth date,
doctor integer references doctor(id));

create table  patient_diagnosis (
id integer identity primary key,
details varchar(150),
remarks varchar(100),
date_confirmed date,
patient integer references patient(id),
patient_key integer );

create table  bill (
id integer identity primary key,
amount integer,
patient_name varchar(30),
doctor_name varchar(30),
patient integer references patient(id),
patient_key integer );






