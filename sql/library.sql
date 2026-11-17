-- Base de données de l'application Library Accounting System
-- Usage : mysql -u root < sql/library.sql
-- Attention : supprime et recrée la base `library`.

DROP DATABASE IF EXISTS `library`;
CREATE DATABASE `library` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `library`;

-- Comptes autorisés à se connecter (écran de connexion)
CREATE TABLE admin (
    id       INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

-- Catalogue des livres (onglet « Add Books »)
CREATE TABLE books (
    id           INT AUTO_INCREMENT PRIMARY KEY,
    bookId       INT NOT NULL UNIQUE,
    bookName     VARCHAR(255) NOT NULL,
    bookCategory VARCHAR(100) NOT NULL,
    bookPrice    DOUBLE NOT NULL,
    date         VARCHAR(20),          -- format dd/MM/yy, écrit par l'application
    bookAuthor   VARCHAR(255) NOT NULL
);

-- Lignes d'achat en cours / passées (onglet « Purchase »)
-- bookPrice contient le prix total de la ligne (prix unitaire x quantité)
CREATE TABLE customerlent (
    id           INT AUTO_INCREMENT PRIMARY KEY,
    customer_id  INT NOT NULL,
    bookId       INT NOT NULL,
    bookName     VARCHAR(255) NOT NULL,
    bookCategory VARCHAR(100),
    bookAuthor   VARCHAR(255),
    bookQuantity INT NOT NULL,
    bookDate     DATE,
    bookPrice    DOUBLE NOT NULL
);

-- Paiements validés (alimente le chiffre d'affaires du tableau de bord)
CREATE TABLE customer_info (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT NOT NULL,
    total       DOUBLE NOT NULL,
    date        DATE NOT NULL
);

-- Compte de démonstration : erwan / erwan
INSERT INTO admin (username, password) VALUES ('erwan', 'erwan');

-- Quelques livres de démonstration
INSERT INTO books (bookId, bookName, bookCategory, bookPrice, date, bookAuthor) VALUES
    (1, 'Le Petit Prince',          'Fun',       8.50, DATE_FORMAT(CURDATE(), '%d/%m/%y'), 'Antoine de Saint-Exupéry'),
    (2, 'Les Misérables',           'Love',     12.90, DATE_FORMAT(CURDATE(), '%d/%m/%y'), 'Victor Hugo'),
    (3, 'Une brève histoire du temps', 'Science', 10.00, DATE_FORMAT(CURDATE(), '%d/%m/%y'), 'Stephen Hawking'),
    (4, 'Le Comte de Monte-Cristo', 'Action',   15.50, DATE_FORMAT(CURDATE(), '%d/%m/%y'), 'Alexandre Dumas');
