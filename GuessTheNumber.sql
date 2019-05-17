DROP DATABASE IF EXISTS GuessTheNumber;
CREATE DATABASE GuessTheNumber;
USE GuessTheNumber;
 
CREATE TABLE games(
gameId INT PRIMARY KEY AUTO_INCREMENT,
isGameOver boolean NOT NULL default 0,
targetNumber INT NOT NULL
);
 
CREATE TABLE rounds(
roundId INT PRIMARY KEY AUTO_INCREMENT,
roundNumber INT NOT NULL,
guessNumber INT NOT NULL,
gameId INT NOT NULL,
exact INT,
`partial` INT,
`time` datetime,
FOREIGN KEY (gameId) REFERENCES games(gameId));