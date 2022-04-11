
#Create the database
CREATE DATABASE IF NOT EXISTS `19120469_attendance_manager`;
USE `19120469_attendance_manager`;

#Create user and grante permission on the created database
CREATE USER '19120469'@'localhost' IDENTIFIED BY '19120469';
GRANT ALL PRIVILEGES ON `19120469_attendance_manager`.* TO '19120469'@'localhost';
