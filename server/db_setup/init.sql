CREATE DATABASE improvised_university;

\c improvised_university;

-- Create a table with students
CREATE TABLE students (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    middle_name VARCHAR(50),
    date_of_birth DATE,
    group_name VARCHAR(20) NOT NULL
);


INSERT INTO students (first_name, last_name, middle_name, date_of_birth, group_name)
VALUES
('John', 'Doe', 'Smith', '1998-05-15', 'A'),
('Jane', 'Smith', 'Marie', '1999-08-20', 'B'),
('Michael', 'Johnson', 'David', '1997-12-10', 'A');


