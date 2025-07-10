/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  janco
 * Created: 10 Jul 2025
 */

-- PostgreSQL script to create the Users table
-- 
-- Fields:
-- - student_number: PRIMARY KEY
-- - name, surname, email, phone, password
-- Constraints:
-- - email: UNIQUE
-- - NOT NULL on all fields
-- - Use VARCHAR with appropriate lengths
--
-- Example:
CREATE TABLE Users (
    student_number VARCHAR(10) PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(15) NOT NULL,
    password TEXT NOT NULL
);
